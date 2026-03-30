// Load all payments into table
async function loadPayments() {
    try {
        const res = await fetch("http://localhost:8080/api/payment/all");

        if (!res.ok) throw new Error("Failed to fetch payments");

        const payments = await res.json();

        const tbody = document.querySelector("#paymentTableBody");
        if (!tbody) return; // table not found, avoid error

        tbody.innerHTML = "";

        payments.forEach(p => {
            tbody.innerHTML += `
            <tr>
                <td>${p.id}</td>
                <td>${p.claimId}</td>
                <td>${p.amount}</td>
                <td>${p.method}</td>
                <td>${p.status}</td>
            </tr>
            `;
        });
    } catch (err) {
        console.error("Failed to load payments:", err);
    }
}

loadPayments();

// Card Payment
async function payCard() {
    const claimId = document.getElementById("claimId").value;
    const amount = parseFloat(document.getElementById("amount").value);

    if (!claimId || isNaN(amount) || amount <= 0) {
        alert("Please enter valid Claim ID and Amount");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/payment/card", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ claimId, amount })
        });

        if (response.ok) {
            alert("Card Payment Success");
            loadPayments(); // Refresh table
        } else {
            const text = await response.text();
            alert("Payment Failed: " + text);
        }
    } catch (err) {
        console.error(err);
        alert("Payment Failed: Server error");
    }
}

// Cash Payment
async function payCash() {
    const claimId = document.getElementById("claimId").value;
    const amount = parseFloat(document.getElementById("amount").value);

    if (!claimId || isNaN(amount) || amount <= 0) {
        alert("Please enter valid Claim ID and Amount");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/payment/cash", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ claimId, amount })
        });

        if (response.ok) {
            alert("Cash Payment Recorded");
            loadPayments(); // Refresh table
        } else {
            const text = await response.text();
            alert("Payment Failed: " + text);
        }
    } catch (err) {
        console.error(err);
        alert("Payment Failed: Server error");
    }
}



// Switch Tabs
function showTab(tab) {
    document.querySelectorAll(".tab").forEach(t => t.classList.remove("active"));
    document.querySelectorAll(".tab-content").forEach(c => c.classList.remove("active"));

    if (tab === 'card') {
        document.getElementById("cardTab").classList.add("active");
        document.querySelector(".tab[onclick*='card']").classList.add("active");
    } else {
        document.getElementById("cashTab").classList.add("active");
        document.querySelector(".tab[onclick*='cash']").classList.add("active");
    }
}