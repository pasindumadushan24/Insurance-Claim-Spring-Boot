// 🔹 Page load උනාම next policy code fetch කරන්න
window.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8080/api/home-policy/next-policy-code')
        .then(response => response.text())
        .then(code => {
            document.getElementById('policyNo').value = code; // POLH001, POLH002 ...
        })
        .catch(err => console.error('Policy code fetch error:', err));
});

function saveHomePolicy() {
    const data = {
        fullName: document.getElementById("fullName").value,
        nic: document.getElementById("nic").value,
        email: document.getElementById("email").value,
        mobile: document.getElementById("mobile").value,
        address: document.getElementById("address").value,

        houseType: document.getElementById("houseType").value,
        houseValue: document.getElementById("houseValue").value,
        yearBuilt: document.getElementById("yearBuilt").value,
        hasSecurity: document.querySelector('input[name="security"]:checked')?.value === "true",

        coverageAmount: document.getElementById("coverageAmount").value,
        policyTerm: document.getElementById("policyTerm").value,
        monthlyPremium: document.getElementById("monthlyPremium").value,
        policyStart: document.getElementById("policyStart").value,
        plan: document.getElementById("plan").value
    };

    fetch("http://localhost:8080/api/home-policy/save", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(res => res.text())
        .then(policyCode => {
            document.getElementById("policyNo").value = policyCode;
            alert("Saved ✅ Policy No: " + policyCode);

            // Optional: redirect
            setTimeout(() => {
                window.location.href = "payment.html";
            }, 1000);
        })
        .catch(err => {
            console.error("Save failed:", err);
            alert("Save failed, check console.");
        });
}