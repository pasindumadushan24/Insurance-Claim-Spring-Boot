function saveLifePolicy(){

    const data = {
        fullName: document.getElementById("fullName").value,
        nic: document.getElementById("nic").value,
        gender: document.getElementById("gender").value,
        dob: document.getElementById("dob").value,
        email: document.getElementById("email").value,
        mobile: document.getElementById("mobile").value,
        address: document.getElementById("address").value,

        height: document.getElementById("height").value,
        weight: document.getElementById("weight").value,
        smoker: document.querySelector('input[name="smoker"]:checked')?.value === "true",
        diseases: document.getElementById("diseases").value,

        sumAssured: document.getElementById("sumAssured").value,
        policyTerm: document.getElementById("policyTerm").value,
        monthlyPremium: document.getElementById("monthlyPremium").value,
        policyStart: document.getElementById("policyStart").value,
        plan: document.getElementById("plan").value
    };

    fetch("http://localhost:8080/api/life-policy/save", {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(res => {
            alert("Life Policy Saved Successfully ✅");
            console.log(res);
        })
        .catch(err => {
            console.error(err);
            alert("Error saving policy ❌");
        });
}