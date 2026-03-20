const BASE_URL = "http://localhost:8080/api/insurance/view";

document.addEventListener("DOMContentLoaded", ()=>{
    loadPolicies();
});

// Load all policies
async function loadPolicies(){
    const token = localStorage.getItem("jwtToken");

    const res = await fetch(BASE_URL, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    const result = await res.json();

    const tableBody = document.getElementById("policyTableBody");
    if(!tableBody) return;

    tableBody.innerHTML="";

    result.data.forEach(p=>{
        const row = `
        <tr>
            <td>${p.custName}</td>
            <td>${p.policyNo}</td>
            <td>${p.insuranceType}</td>
            <td>${p.premium}</td>
            <td>${p.startDate}</td>
            <td>${p.endDate}</td>
            <td>
                <button onclick="deletePolicy(${p.id})">Delete</button>
            </td>
        </tr>`;
        tableBody.innerHTML += row;
    });
}

// Delete policy
async function deletePolicy(id){
    const token = localStorage.getItem("jwtToken");

    const res = await fetch(`${BASE_URL}/${id}`,{
        method: "DELETE",
        headers:{
            "Authorization": `Bearer ${token}`
        }
    });

    if(res.ok){
        alert("Policy deleted successfully!");
        loadPolicies();
    } else {
        alert("Failed to delete policy");
    }
}