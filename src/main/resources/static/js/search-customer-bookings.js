document.addEventListener('DOMContentLoaded', function() {
    let debounceTimer;
    // Store cache in sessionStorage to persist through page reloads
    const getCache = () => {
        const cached = sessionStorage.getItem('searchCache');
        return cached ? JSON.parse(cached) : { results: null, query: null };
    };

    const setCache = (cache) => {
        sessionStorage.setItem('searchCache', JSON.stringify(cache));
    };

    const searchInput = document.getElementById('searchInput');
    const resultsContainer = document.getElementById('resultsContainer');
    const tableBody = resultsContainer.querySelector('tbody');

    if (!searchInput || !resultsContainer || !tableBody) {
        console.error('Required elements not found. Check your HTML IDs.');
        return;
    }

    // Initialize search input with query param if it exists
    const urlParams = new URLSearchParams(window.location.search);
    const financerId = urlParams.get('financerId');
    const searchCache = getCache();

    if (searchCache.results) {
        displayResults(searchCache.results);
        if (financerId) {
            highlightSelectedRow(financerId);
        }
    } else if (financerId) {
        performSearch(financerId);
    }

    searchInput.addEventListener('input', function(e) {
        clearTimeout(debounceTimer);
        const searchValue = e.target.value.trim();

        if (searchValue.length < 2 && isNaN(searchValue)) {
            tableBody.innerHTML = '';
            return;
        }

        debounceTimer = setTimeout(() => {
            performSearch(searchValue);
        }, 300);
    });

    async function performSearch(query) {
        const searchCache = getCache();
        if (searchCache.query === query && searchCache.results) {
            displayResults(searchCache.results);
            if (financerId) {
                highlightSelectedRow(financerId);
            }
            return;
        }

        try {
            const response = await fetch(`/api/customers/search?query=${encodeURIComponent(query)}`);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            const customers = Array.isArray(data) ? data : (data.content || []);

            // Update cache
            setCache({ query, results: customers });

            if (customers.length > 0) {
                displayResults(customers);
                if (financerId) {
                    highlightSelectedRow(financerId);
                }
            } else {
                tableBody.innerHTML = `
                    <tr>
                        <td colspan="4" class="text-center">No results found</td>
                    </tr>`;
            }
        } catch (error) {
            console.error('Search failed:', error);
            tableBody.innerHTML = `
                <tr>
                    <td colspan="4" class="text-center">Search failed. Please try again.</td>
                </tr>`;
        }
    }

    function displayResults(customers) {
        tableBody.innerHTML = '';

        customers.forEach(customer => {
            const row = document.createElement('tr');
            row.style.cursor = 'pointer';
            row.dataset.financerId = customer.id;

            const fullName = `${customer.firstName || ''} ${customer.lastName || ''}`.trim();

            row.innerHTML = `
                <td>${customer.id || ''}</td>
                <td>${fullName}</td>
                <td>${customer.phoneNumber || ''}</td>
                <td>${customer.secret || ''}</td>
            `;

            row.addEventListener('click', () => {
                // Reload the page with the financer ID as a query parameter
                window.location.href = `${window.location.pathname}?financerId=${customer.id}`;
            });

            tableBody.appendChild(row);
        });
    }

    function highlightSelectedRow(financerId) {
        const selectedRow = tableBody.querySelector(`[data-financer-id="${financerId}"]`);
        if (selectedRow) {
            selectedRow.classList.add('contrast');
            selectedRow.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
    }
});