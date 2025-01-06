const themeToggle = document.getElementById('theme-toggle');
const currentTheme = localStorage.getItem('theme') || 'light';

document.documentElement.setAttribute('data-theme', currentTheme);

const updateToggleText = () => {
    if (themeToggle) { // Check if the toggle exists
        themeToggle.textContent = document.documentElement.getAttribute('data-theme') === 'dark'
            ? 'Switch to Light Mode'
            : 'Switch to Dark Mode';
    }
};

// Only add the event listener if the toggle exists
if (themeToggle) {
    updateToggleText(); // Update the text initially

    themeToggle.addEventListener('click', (event) => {
        event.preventDefault(); // Prevent default behavior
        const currentTheme = document.documentElement.getAttribute('data-theme');
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        document.documentElement.setAttribute('data-theme', newTheme);
        localStorage.setItem('theme', newTheme); // Save the user's preference
        updateToggleText(); // Update the text after changing the theme
    });
}


document.addEventListener('DOMContentLoaded', () => {
    const header = document.querySelector('body > header');

    window.addEventListener('scroll', () => {
        if (header) {
            if (window.scrollY > 0) {
                header.classList.add('is-fixed');
            } else {
                header.classList.remove('is-fixed');
            }
        }
    });
});