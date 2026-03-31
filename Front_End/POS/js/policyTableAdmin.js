document.addEventListener("DOMContentLoaded", () => {
    loadPolicies();
});

function loadPolicies() {
    fetch("http://localhost:8080/api/policy/all") // 🔹 backend URL
        .then(res => res.json())
        .then(data => populateTable(data))
        .catch(err => console.error("Error fetching policies:", err));
}

function populateTable(policies) {
    const tbody = document.querySelector("#policyTable tbody");
    tbody.innerHTML = "";

    policies.forEach(policy => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${policy.id}</td>
            <td>${policy.policyHolder.nic}</td>
            <td>${policy.policyHolder.fullName}</td>
            <td>${policy.policyHolder.email || '-'}</td>
            <td>${policy.policyHolder.mobile}</td>
            <td>${policy.policyHolder.address1 || '-'} ${policy.policyHolder.address2 || ''}</td>
            <td>${policy.policyHolder.holderType}</td>
            <td>${policy.vehicleType}</td>
            <td>${policy.vehicleMake}</td>
            <td>${policy.vehicleModel}</td>
            <td>${policy.fuelType}</td>
            <td>${policy.purpose}</td>
            <td>${policy.regNumber}</td>
            <td>${policy.engineCapacity}</td>
            <td>${policy.chassisNumber}</td>
            <td>${policy.manufactureYear}</td>
            <td>${policy.policyStart}</td>
            <td>${policy.plan}</td>
             <td>
                <button class="action-btn approve-btn" onclick="approvePolicy(${policy.id})">Approve</button>
                <button class="action-btn reject-btn" onclick="rejectPolicy(${policy.id})">Reject</button>
                <button class="action-btn approve-btn" onclick="payPolicy(${policy.id})">Pay Now</button>
            </td>
        `;

        tbody.appendChild(tr);
    });
}


function payPolicy(id){
    fetch(`http://localhost:8080/api/policy/pay/${id}`, {
        method: 'POST'
    })
        .then(res => res.json())
        .then(data => {
            alert(`Policy ID ${id} paid successfully`);
            loadPolicies(); // refresh table
        })
        .catch(err => console.error("Error paying policy:", err));
}

function approvePolicy(id) {
    alert("Approve policy ID: " + id);

}

function rejectPolicy(id) {
    alert("Reject policy ID: " + id);

}