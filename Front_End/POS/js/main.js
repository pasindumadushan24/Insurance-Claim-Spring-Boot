document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');

    if(loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            // MEKA PASSE SPRING BOOT API EKKA REPLACE KARANNA OONE
            console.log("Logging in with:", email);

            // Mock Logic for demonstration:
            if (email === 'admin@test.com') {
                // Employee/Admin log wunoth
                alert("Login Successful as Employee!");
                window.location.href = "employee-dashboard.html";
            } else {
                // Customer log wunoth
                alert("Login Successful as Customer!");
                window.location.href = "customer-dashboard.html";
            }
        });
    }
});

function logout() {
    alert("You have been logged out.");
    // Clear session storage if used later
}