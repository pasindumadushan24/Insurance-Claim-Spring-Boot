const tableBody = document.getElementById("tableBody");

// Load all policies
function loadPolicies() {
    fetch("http://localhost:8080/api/vehicle-full/all")
        .then(res => res.json())
        .then(data => {
            tableBody.innerHTML = "";
            data.forEach(policy => {
                const tr = document.createElement("tr");

                tr.innerHTML = `
                    <td>${policy.id}</td>
                    <td>${policy.policyHolder?.nic || ""}</td>
                    <td>${policy.policyHolder?.fullName || ""}</td>
                    <td>${policy.vehicleType}</td>
                    <td>${policy.vehicleMake}</td>
                    <td>${policy.vehicleModel}</td>
                    <td>${policy.fuelType}</td>
                    <td>${policy.regNumber}</td>
                    <td>${policy.plan}</td>
                    <td>${policy.policyStart}</td>
                    <td>
                        <button onclick="deletePolicy(${policy.id})">Delete</button>
                    </td>
                `;
                tableBody.appendChild(tr);
            });
        })
        .catch(err => console.error(err));
}

// Delete policy
function deletePolicy(id) {
    if (!confirm("Are you sure you want to delete this policy?")) return;

    fetch(`http://localhost:8080/api/vehicle-full/delete/${id}`, {
        method: "DELETE"
    })
        .then(res => {
            if (res.ok) {
                alert("Deleted successfully ✅");
                loadPolicies();
            } else {
                res.text().then(text => alert("Error: " + text));
            }
        })
        .catch(err => console.error(err));
}

// Initialize table
loadPolicies();