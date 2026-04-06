let currentStep = 1;
const totalSteps = 5;

function showStep(step){
    for(let i=1;i<=totalSteps;i++){
        document.getElementById('step'+i).classList.remove('active');
    }
    document.getElementById('step'+step).classList.add('active');

    if(step===3){
        document.getElementById("summary").innerHTML = `
            <p>Vehicle: ${document.getElementById("vehicleType").value}</p>
            <p>Make: ${document.getElementById("vehicleMake").value}</p>
            <p>Model: ${document.getElementById("vehicleModel").value}</p>
            <p>Fuel: ${document.getElementById("fuelType").value}</p>
            <p>Plan: ${document.getElementById("plans").value}</p>
        `;
    }
}

// NAVIGATION
document.getElementById('nextBtn1').onclick = ()=>{ currentStep++; showStep(currentStep); };
document.getElementById('nextBtn2').onclick = ()=>{ currentStep++; showStep(currentStep); };
document.getElementById('nextBtn3').onclick = ()=>{ currentStep++; showStep(currentStep); };
document.getElementById('nextBtn5').onclick = ()=>{ currentStep++; showStep(currentStep); };

document.getElementById('prevBtn2').onclick = ()=>{ currentStep--; showStep(currentStep); };
document.getElementById('prevBtn3').onclick = ()=>{ currentStep--; showStep(currentStep); };
document.getElementById('prevBtn4').onclick = ()=>{ currentStep--; showStep(currentStep); };
document.getElementById('prevBtn5').onclick = ()=>{ currentStep--; showStep(currentStep); };

// SAVE
function savePolicy(){

    const data = {
        nic: document.getElementById("nicNumber").value,
        title: document.getElementById("title").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        mobile: document.getElementById("mobileNumber").value,
        address1: document.getElementById("address1").value,
        address2: document.getElementById("address2").value,
        holderType: document.querySelector('input[name="holderType"]:checked').value,

        vehicleType: document.getElementById("vehicleType").value,
        vehicleMake: document.getElementById("vehicleMake").value,
        vehicleModel: document.getElementById("vehicleModel").value,
        fuelType: document.getElementById("fuelType").value,
        purpose: document.querySelector('input[name="purpose"]:checked').value,
        regNumber: document.getElementById("regNumber").value,
        engineCapacity: document.getElementById("engineCapacity").value,
        chassisNumber: document.getElementById("chassisNumber").value,
        manufactureYear: document.getElementById("manufactureYear").value,

        policyStart: document.getElementById("policyStart").value,
        plan: document.getElementById("plans").value
    };

    fetch("http://localhost:8080/api/vehicle-full/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(() => {
            alert("Saved Successfully ✅");
            window.location.href = "payment.html";
        })
        .catch(err => {
            console.error(err);
            alert("Error ❌");
        });
}