document.addEventListener('DOMContentLoaded', () => {
    const signinForm = document.getElementById('signinForm');
    const signupForm = document.getElementById('signupForm');

    const BASE_URL = "http://localhost:8080/api/v1/auth";

    // --- SIGN UP LOGIC ---
    if (signupForm) {
        signupForm.addEventListener('submit', async (e) => {
            e.preventDefault();

            const userData = {
                username: document.getElementById('reg_username').value,
                password: document.getElementById('reg_password').value,
                role: document.getElementById('reg_role').value // Sends "USER" as per DTO
            };

            try {
                const response = await fetch(`${BASE_URL}/signup`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(userData)
                });

                const result = await response.json();

                if (response.ok) {
                    alert("Registration Successful! Please Sign In.");
                    window.location.href = "signin.html";
                } else {
                    alert("Registration Failed: " + (result.data || result.message));
                }
            } catch (error) {
                console.error("Error:", error);
                alert("Server Error. Make sure Backend is running.");
            }
        });
    }

    // --- SIGN IN LOGIC ---
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
                    // result.data contains AuthResponseDTO which has accessToken
                    const token = result.data.accessToken;
                    localStorage.setItem('jwtToken', token); // Save token for future API calls

                    alert("Login Successful!");
                    window.location.href = "../../index.html";
                } else {
                    alert("Login Failed: " + result.message);
                }
            } catch (error) {
                console.error("Error:", error);
                alert("Server connection failed.");
            }
        });
    }
});