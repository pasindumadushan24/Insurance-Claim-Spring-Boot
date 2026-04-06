document.addEventListener("DOMContentLoaded", load);

function load() {
    fetch("http://localhost:8080/api/home-policy/all")
        .then(res => res.json())
        .then(data => {

            const tbody = document.querySelector("#homePolicyTable tbody");
            tbody.innerHTML = "";

            data.forEach(p => {

                const status = p.status || "PENDING";

                const tr = document.createElement("tr");

                tr.innerHTML = `
                    <td>${p.policyCode}</td>
                    <td>${p.fullName}</td>
                    <td>${p.houseType}</td>
                    <td>${p.houseValue}</td>
                    <td>${p.plan}</td>
                    <td>${p.monthlyPremium}</td>
                    <td>${status}</td>
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
        });
}

function approve(id) {
    fetch(`http://localhost:8080/api/home-policy/approve/${id}`, { method: "PUT" })
        .then(load);
}

function reject(id) {
    fetch(`http://localhost:8080/api/home-policy/reject/${id}`, { method: "PUT" })
        .then(load);
}

// function pay(id) {
//     fetch(`http://localhost:8080/api/home-policy/pay/${id}`, { method: "PUT" })
//         .then(load);
// }