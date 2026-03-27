const BASE_URL = "http://localhost:8080/api/insurance";
let editingId = null;

// 🔥 Generate next sequential Policy Number
async function generatePolicyNo() {
    try {
        const res = await fetch(BASE_URL);
        const data = await res.json();

        if (data.length === 0) return "POL001"; // first policy

        // Get last policy number
        const lastPolicy = data[data.length - 1].policyNo; // e.g. "POL005"
        const num = parseInt(lastPolicy.replace("POL", "")) + 1; // increment
        const formatted = num.toString().padStart(3, "0"); // pad to 3 digits
        return `POL${formatted}`;

    } catch (err) {
        console.error("Error generating policy number:", err);
        return "POL001"; // fallback
    }
}

document.addEventListener("DOMContentLoaded", async () => {
    const form = document.getElementById("insuranceForm");

    if (form) {
        // Page load: set new policy number
        if (!editingId) {
            document.getElementById("policyNo").value = await generatePolicyNo();
        }

        loadPolicies();

        form.addEventListener("submit", async (e) => {
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

            try {
                if (editingId) {
                    // Update existing policy
                    await fetch(`${BASE_URL}/${editingId}`, {
                        method: "PUT",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(insurance)
                    });

                    editingId = null;
                    document.getElementById("formTitle").innerText = "Add Insurance Policy";
                    document.getElementById("submitBtn").innerText = "Add Policy";

                } else {
                    // Add new policy
                    await fetch(BASE_URL, {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(insurance)
                    });
                }

                form.reset();

                // 🔥 generate next policy number
                document.getElementById("policyNo").value = await generatePolicyNo();

                loadPolicies();

            } catch (err) {
                console.error("Error saving:", err);
                alert("Error saving data!");
            }
        });
    }
});

// 🔥 LOAD ALL POLICIES
async function loadPolicies() {
    const table = document.getElementById("insuranceTableBody");
    if (!table) return;

    table.innerHTML = "";

    // Active button highlight (ALL)
    document.querySelectorAll(".filter-btn").forEach(btn => {
        btn.classList.remove("active");
        if (btn.innerText === "All") btn.classList.add("active");
    });

    try {
        const res = await fetch(BASE_URL);
        const data = await res.json();

        data.forEach(ins => {
            table.innerHTML += createRow(ins, true);
        });

    } catch (err) {
        console.error("Error loading:", err);
    }
}

// 🔥 FILTER LOAD (Life / Vehicle / Home / Pet)
async function loadByType(type) {
    const table = document.getElementById("insuranceTableBody");
    if (!table) return;

    table.innerHTML = "";

    // Active button highlight
    document.querySelectorAll(".filter-btn").forEach(btn => {
        btn.classList.remove("active");
        if (btn.innerText === type) btn.classList.add("active");
    });

    try {
        const res = await fetch(`${BASE_URL}/type/${type}`);
        const data = await res.json();

        data.forEach(ins => {
            table.innerHTML += createRow(ins, false);
        });

    } catch (err) {
        console.error("Error filtering:", err);
    }
}

// 🔥 COMMON ROW BUILDER
function createRow(ins, showEdit) {
    return `
    <tr>
        <td>${ins.custName}</td>
        <td>${ins.policyNo}</td>
        <td><span class="badge">${ins.insuranceType}</span></td>
        <td>${ins.premium}</td>
        <td>
            ${showEdit ? `
                <button class="btn btn-edit" onclick="editPolicy(${ins.id}, \`${ins.custName}\`, \`${ins.custContact}\`, \`${ins.policyNo}\`, \`${ins.insuranceType}\`, \`${ins.premium}\`, \`${ins.startDate}\`, \`${ins.endDate}\`)">Edit</button>
            ` : ``}
            <button class="delete-btn" onclick="deletePolicy(${ins.id})">Delete</button>
        </td>
    </tr>
    `;
}

// 🔥 DELETE POLICY
async function deletePolicy(id) {
    if (!confirm("Are you sure to delete this policy?")) return;

    try {
        await fetch(`${BASE_URL}/${id}`, { method: "DELETE" });
        loadPolicies();
    } catch (err) {
        console.error("Delete error:", err);
    }
}

// 🔥 EDIT POLICY
function editPolicy(id, name, contact, policyNo, type, premium, start, end) {
    document.getElementById("custName").value = name;
    document.getElementById("custContact").value = contact;
    document.getElementById("policyNo").value = policyNo; // keep existing number
    document.getElementById("insuranceType").value = type;
    document.getElementById("premium").value = premium;
    document.getElementById("startDate").value = start;
    document.getElementById("endDate").value = end;

    editingId = id;

    document.getElementById("formTitle").innerText = "Update Insurance Policy";
    document.getElementById("submitBtn").innerText = "Update Policy";

    window.scrollTo({ top: 0, behavior: "smooth" });
}