document.addEventListener("DOMContentLoaded", () => {
    loadLifePolicies();
});

function loadLifePolicies() {
    fetch("http://localhost:8080/api/life-policy/all")
        .then(res => res.json())
        .then(data => populateTable(data))
        .catch(err => console.error("Error:", err));
}

function populateTable(policies) {
    const tbody = document.querySelector("#lifePolicyTable tbody");
    tbody.innerHTML = "";

    policies.forEach(policy => {

        const status = policy.status || "PENDING";

        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${policy.id}</td>
            <td>${policy.fullName}</td>
            <td>${policy.nic}</td>
            <td>${policy.gender}</td>
            <td>${policy.dob}</td>
            <td>${policy.mobile}</td>
            <td>${policy.plan}</td>
            <td>${policy.monthlyPremium}</td>
            <td>
                <span class="status-badge ${status.toLowerCase()}">${status}</span>
            </td>
            <td>
                ${
            status === "PENDING"
                ? `
                        <button class="action-btn approve-btn" onclick="approve(${policy.id})">Approve</button>
                        <button class="action-btn reject-btn" onclick="reject(${policy.id})">Reject</button>
                        <button class="action-btn pay-btn" onclick="pay(${policy.id})">Pay</button>
                      `
                : `<span class="done-text">Done</span>`
        }
            </td>
        `;

        tbody.appendChild(tr);
    });
}

/* ===== ACTIONS ===== */

function approve(id) {
    fetch(`http://localhost:8080/api/life-policy/approve/${id}`, {
        method: "PUT"
    })
        .then(() => {
            alert("Approved ✅");
            loadLifePolicies();
        });
}

function reject(id) {
    fetch(`http://localhost:8080/api/life-policy/reject/${id}`, {
        method: "PUT"
    })
        .then(() => {
            alert("Rejected ❌");
            loadLifePolicies();
        });
}

function pay(id) {
    fetch(`http://localhost:8080/api/life-policy/pay/${id}`, {
        method: "PUT"
    })
        .then(() => {
            alert("Payment Done 💰");
            loadLifePolicies();
        });
}