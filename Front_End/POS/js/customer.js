const BASE_URL = "http://localhost:8080/api/v1/customers";

document.addEventListener("DOMContentLoaded", () => {

    const customerForm = document.getElementById("customerForm");

    loadCustomers();

    if(customerForm){
        customerForm.addEventListener("submit", async (e)=>{
            e.preventDefault();

            const token = localStorage.getItem("jwtToken");

            const customerData = {
                name: document.getElementById("custName").value,
                address: document.getElementById("custAddress").value,
                contactNo: document.getElementById("custContact").value,
                insuranceType: document.getElementById("insuranceType").value,
                policyNumber: "POL-" + Math.floor(1000 + Math.random() * 9000)
            };

            await fetch(`${BASE_URL}/save`,{
                method:"POST",
                headers:{
                    "Authorization": `Bearer ${token}`,
                    "Content-Type":"application/json"
                },
                body: JSON.stringify(customerData)
            });

            customerForm.reset();
            loadCustomers();
        });
    }

});

// -------------------------
// Delete Customer
// -------------------------
async function deleteCustomer(id){
    const token = localStorage.getItem("jwtToken");

    const res = await fetch(`${BASE_URL}/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    if(res.ok){
        alert("Customer deleted successfully!");
        loadCustomers();
    } else {
        const err = await res.json();
        alert("Failed to delete customer: " + (err?.message || res.statusText));
    }
}

// -------------------------
// Load Customers
// -------------------------
async function loadCustomers(){

    const token = localStorage.getItem("jwtToken");

    const res = await fetch(`${BASE_URL}/all`,{
        headers:{
            "Authorization":`Bearer ${token}`
        }
    });

    const result = await res.json();

    const tableBody = document.getElementById("customerTableBody");
    if(!tableBody) return;

    tableBody.innerHTML="";

    result.data.forEach(c=>{
        const row = `
        <tr>
            <td>${c.name}</td>
            <td>${c.insuranceType}</td>
            <td>${c.policyNumber}</td>
            <td>
                <button onclick="deleteCustomer(${c.id})">Delete</button>
            </td>
        </tr>`;
        tableBody.innerHTML += row;
    });
}