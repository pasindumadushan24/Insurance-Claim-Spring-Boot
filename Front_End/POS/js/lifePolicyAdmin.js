document.addEventListener("DOMContentLoaded", load);

function load() {
    fetch("http://localhost:8080/api/life-policy/all")
        .then(res => res.json())
        .then(data => {

            const tbody = document.querySelector("#lifePolicyTable tbody");
            tbody.innerHTML = "";

            data.forEach(p => {

                // Status default PENDING
                const status = p.status || "PENDING";

                // Decide status badge color class
                let statusClass = "";
                if (status === "PENDING") statusClass = "pending";
                else if (status === "APPROVED") statusClass = "approved";
                else if (status === "REJECTED") statusClass = "rejected";

                const tr = document.createElement("tr");

                tr.innerHTML = `
                    <td>${p.policyCode}</td>
                    <td>${p.fullName}</td>
                    <td>${p.gender}</td>
                    <td>${p.sumAssured}</td>
                    <td>${p.plan}</td>
                    <td>${p.monthlyPremium}</td>
                    <td><span class="status-badge ${statusClass}">${status}</span></td>
                    <td>
                        ${
                    status === "PENDING"
                        ? `
                                <button class="action-btn approve-btn" onclick="approve(${p.id})">Approve</button>
                                <button class="action-btn reject-btn" onclick="reject(${p.id})">Reject</button>
                              `
                        : `<span class="done-text">Done</span>`
                }
                    </td>
                `;

                tbody.appendChild(tr);
            });
        })
        .catch(err => console.error("Error fetching life policies:", err));
}

// ✅ Approve
function approve(id) {
    fetch(`http://localhost:8080/api/life-policy/approve/${id}`, {
        method: "PUT"
    })
        .then(() => load())
        .catch(err => console.error("Error approving policy:", err));
}

// ❌ Reject
function reject(id) {
    fetch(`http://localhost:8080/api/life-policy/reject/${id}`, {
        method: "PUT"
    })
        .then(() => load())
        .catch(err => console.error("Error rejecting policy:", err));
}

// 💰 Pay (optional)
function pay(id) {
    fetch(`http://localhost:8080/api/life-policy/pay/${id}`, {
        method: "PUT"
    })
        .then(() => load())
        .catch(err => console.error("Error paying policy:", err));
}