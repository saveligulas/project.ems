document.addEventListener('DOMContentLoaded', function() {
    let debounceTimer;

    const searchInput = document.getElementById('searchInput');
    const resultsContainer = document.getElementById('resultsContainer');
    const tableBody = resultsContainer.querySelector('tbody');
    const financerIdInput = document.querySelector('input[name="financer"]');

    // Verify elements are found
    if (!searchInput || !resultsContainer || !tableBody || !financerIdInput) {
        console.error('Required elements not found. Check your HTML IDs.');
        return;
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
        try {
            const response = await fetch(`/api/customers/search?query=${encodeURIComponent(query)}`);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            console.log('Search query:', query);
            console.log('Received data:', data);

            const customers = Array.isArray(data) ? data : (data.content || []);

            if (customers.length > 0) {
                displayResults(customers);
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

            const fullName = `${customer.firstName || ''} ${customer.lastName || ''}`.trim();

            row.innerHTML = `
                <td>${customer.id || ''}</td>
                <td>${fullName}</td>
                <td>${customer.phoneNumber || ''}</td>
                <td>${customer.secret || ''}</td>
            `;

            row.addEventListener('click', () => {
                // Update the financerId input value
                financerIdInput.value = customer.id;

                // Optionally, highlight the selected row
                const previouslySelected = tableBody.querySelector('.selected');
                if (previouslySelected) {
                    previouslySelected.classList.remove('contrast');
                }
                row.classList.add('contrast');
            });

            tableBody.appendChild(row);
        });
    }
});