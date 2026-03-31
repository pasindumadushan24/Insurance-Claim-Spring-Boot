const insuranceType = document.getElementById('insuranceType');
const insuranceCategory = document.getElementById('insuranceCategory');
const heroImage = document.getElementById('heroImage');
const insureBtn = document.getElementById('insureNowBtn');
const helpFormContainer = document.getElementById('helpFormContainer');
const helpForm = document.getElementById('helpForm');

// Update image & category when insurance type changes
insuranceType.addEventListener('change', function() {
    helpFormContainer.style.display = 'none';
    insuranceCategory.innerHTML = '';

    switch(this.value) {
        case 'Motor':
            heroImage.src = "https://images.unsplash.com/photo-1494976388531-d1058494cdd8?auto=format&fit=crop&w=500";
            heroImage.alt = "Vehicle Insurance";
            insuranceCategory.innerHTML = `
                <option value="Third Party">Third Party</option>
                <option value="Comprehensive">Comprehensive</option>
            `;
            break;
        case 'Home':
            heroImage.src = "https://images.unsplash.com/photo-1582268611958-ebfd161ef9cf?auto=format&fit=crop&w=500";
            heroImage.alt = "Home Insurance";
            insuranceCategory.innerHTML = `<option value="Home">Home</option>`;
            break;
        case 'Life':
            heroImage.src = "https://images.unsplash.com/photo-1516733725897-1aa73b87c8e8?auto=format&fit=crop&w=500";
            heroImage.alt = "Life Insurance";
            insuranceCategory.innerHTML = `<option value="Life">Life</option>`;
            break;
    }
});

// Handle "Insure Now" button
insureBtn.addEventListener('click', function() {
    const type = insuranceType.value;
    const category = insuranceCategory.value;

    if(type === 'Motor' && category === 'Third Party') {
        window.location.href = "../Front_End/POS/pages/M-main2.html";
        return;
    }

    helpFormContainer.style.display = 'block';
    helpForm.scrollIntoView({behavior: 'smooth'});

    const productSelect = document.getElementById('productType');
    if(type === 'Motor') productSelect.value = 'Motor Insurance';
    else if(type === 'Home') productSelect.value = 'Home Insurance';
    else if(type === 'Life') productSelect.value = 'Life Insurance';
});

// Handle help form submission
helpForm.addEventListener('submit', function(e) {
    e.preventDefault();
    const formData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        address: document.getElementById('address').value,
        phone: document.getElementById('phone').value,
        productType: document.getElementById('productType').value
    };

    console.log('Form submitted:', formData);
    alert(`Thank you, ${formData.name}! Our team will contact you shortly.`);


    // ✅ Save the form data in localStorage
    let requests = JSON.parse(localStorage.getItem('helpRequests')) || [];
    requests.push(formData);
    localStorage.setItem('helpRequests', JSON.stringify(requests));


    helpForm.reset();
    helpFormContainer.style.display = 'none';
});

