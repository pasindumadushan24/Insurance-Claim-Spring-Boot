const BASE_URL = "http://localhost:8080/api/insurance";

document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("insuranceForm");

    loadPolicies();

    if(form){

        form.addEventListener("submit", async (e)=>{

            e.preventDefault();

            const insurance = {
                custName: document.getElementById("custName").value,
                custContact: document.getElementById("custContact").value,
                policyNo: document.getElementById("policyNo").value,
                insuranceType: document.getElementById("insuranceType").value,
                premium: document.getElementById("premium").value,
                startDate: document.getElementById("startDate").value,
                endDate: document.getElementById("endDate").value
            };

            await fetch(BASE_URL,{
                method:"POST",
                headers:{
                    "Content-Type":"application/json"
                },
                body: JSON.stringify(insurance)
            });

            form.reset();
            loadPolicies();

        });

    }

});


async function loadPolicies(){

    const table = document.getElementById("insuranceTableBody");

    if(!table) return;

    table.innerHTML="";

    const res = await fetch(BASE_URL);
    const data = await res.json();

    data.forEach(ins=>{

        const row = `
        <tr>
        <td>${ins.custName}</td>
        <td>${ins.policyNo}</td>
        <td>${ins.insuranceType}</td>
        <td>${ins.premium}</td>
        <td>
        <button onclick="deletePolicy(${ins.id})">Delete</button>
        </td>
        </tr>
        `;

        table.innerHTML += row;

    });

}


async function deletePolicy(id){

    await fetch(`${BASE_URL}/${id}`,{
        method:"DELETE"
    });

    loadPolicies();

}