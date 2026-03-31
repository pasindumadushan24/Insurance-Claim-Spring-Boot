document.addEventListener('DOMContentLoaded', function() {
    const tableBody = document.querySelector('#requestsTable tbody');

    // Get stored requests from localStorage
    let requests = JSON.parse(localStorage.getItem('helpRequests')) || [];

    // Populate table
    requests.forEach(req => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${req.name}</td>
            <td>${req.email}</td>
            <td>${req.address}</td>
            <td>${req.phone}</td>
            <td>${req.productType}</td>
        `;
        tableBody.appendChild(row);
    });
});