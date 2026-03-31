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
        .then(() => {
            alert("Saved ✅");
            window.location.href = "payment.html";
        });
}