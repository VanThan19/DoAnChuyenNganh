document.addEventListener('DOMContentLoaded', () => {

    // 1. Toggle Show/Hide Password
    const togglePassword = document.querySelector('#togglePassword');
    const passwordField = document.querySelector('#password');

    if (togglePassword && passwordField) {
        togglePassword.addEventListener('click', () => {
            // Toggle the type attribute
            const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordField.setAttribute('type', type);

            // Toggle the eye icon
            togglePassword.classList.toggle('fa-eye');
            togglePassword.classList.toggle('fa-eye-slash');

            // Micro-interaction click effect
            togglePassword.style.transform = 'translateY(-50%) scale(0.9)';
            setTimeout(() => togglePassword.style.transform = 'translateY(-50%) scale(1)', 150);
        });
    }

    // 2. Micro-interactions: Click feedback on Login Button
    const loginBtn = document.querySelector('button[type="submit"]');
    if (loginBtn) {
        loginBtn.addEventListener('mousedown', () => {
            loginBtn.style.transform = 'translateY(-1px) scale(0.98)';
        });
        loginBtn.addEventListener('mouseup', () => {
            loginBtn.style.transform = 'translateY(-3px) scale(1)';
        });
    }
});