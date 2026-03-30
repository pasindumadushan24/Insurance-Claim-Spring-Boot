document.addEventListener('DOMContentLoaded', () => {

    const signinForm = document.getElementById('signinForm');
    const signupForm = document.getElementById('signupForm');

    const BASE_URL = "http://localhost:8080/api/v1/auth";

    // --- SIGN UP ---
    if (signupForm) {
        signupForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const userData = {
                username: document.getElementById('reg_username').value,
                password: document.getElementById('reg_password').value,
                role: document.getElementById('reg_role').value
            };

            try {
                const response = await fetch(`${BASE_URL}/signup`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(userData)
                });

                const result = await response.json();

                if (response.ok) {
                    alert("Registration Successful!");
                    window.location.href = "signin.html";
                } else {
                    alert("Registration Failed: " + (result.data || result.message));
                }

            } catch (error) {
                console.error(error);
                alert("Server Error");
            }
        });
    }

    // --- SIGN IN ---
    if (signinForm) {
        signinForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const authData = {
                username: document.getElementById('username').value,
                password: document.getElementById('password').value
            };

            try {
                const response = await fetch(`${BASE_URL}/signin`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(authData)
                });

                const result = await response.json();

                if (response.ok) {
                    const token = result.data.accessToken;
                    const role = result.data.role;

                    localStorage.setItem("jwtToken", token);
                    localStorage.setItem("userRole", role);

                    // ✅ redirect
                    if (role === "USER") {
                        window.location.href = "../pages/dashboard2.html";
                    } else if (role === "ADMIN") {
                        window.location.href = "../pages/dashboard.html";
                    } else if (role === "OWNER") {
                        window.location.href = "../pages/dashboard2.html";
                    }

                } else {
                    alert("Login Failed: " + result.message);
                }

            } catch (error) {
                console.error(error);
                alert("Server Error");
            }

        }); // ✅ THIS WAS MISSING
    }

});