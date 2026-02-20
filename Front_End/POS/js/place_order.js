let allItems = [];

$(document).ready(function () {
    loadCustomers();
    loadItems();
    setNextOrderId();


    $('#order-date').val(new Date().toISOString().split('T')[0]);
});

function setNextOrderId() {
    $.get("http://localhost:8080/api/v1/Order/nextId", function (res) {
        $('#order-id').text(res.data);
    });
}

function loadCustomers() {
    $.get("http://localhost:8080/api/v1/Customer", function (res) {
        let s = $('#order-customer');
        s.empty().append('<option value="">Select Customer</option>');
        res.data.forEach(c => s.append(`<option value="${c.cid}">${c.name}</option>`));
    });
}

function loadItems() {
    $.get("http://localhost:8080/api/v1/Item", function (res) {
        allItems = res.data;
    });
}

function addItemRow() {
    let options = allItems.map(i => `<option value="${i.iid}">${i.iName}</option>`).join('');
    let row = `
        <tr>
            <td><select class="item-select" onchange="updateRowPrice(this)">
                <option value="">Select Item</option>${options}</select></td>
            <td><input type="number" class="unit-price" readonly></td>
            <td><input type="number" class="buy-qty" oninput="calculateSubTotal(this)" value="1"></td>
            <td class="sub-total">0.00</td>
            <td><button onclick="$(this).closest('tr').remove(); calculateTotal();">X</button></td>
        </tr>`;
    $('#order-item-list').append(row);
}

function updateRowPrice(element) {
    let id = $(element).val();
    let item = allItems.find(i => i.iid == id);
    let row = $(element).closest('tr');
    if (item) {
        row.find('.unit-price').val(item.price);
        calculateSubTotal(element);
    }
}

function calculateSubTotal(element) {
    let row = $(element).closest('tr');
    let price = parseFloat(row.find('.unit-price').val()) || 0;
    let qty = parseInt(row.find('.buy-qty').val()) || 0;
    row.find('.sub-total').text((price * qty).toFixed(2));
    calculateTotal();
}

function calculateTotal() {
    let total = 0;
    $('.sub-total').each(function () {
        total += parseFloat($(this).text());
    });
    $('#order-total').text(total.toFixed(2));
}

function saveOrder() {
    let orderDetails = [];
    $('#order-item-list tr').each(function () {
        let iid = $(this).find('.item-select').val();
        let qty = $(this).find('.buy-qty').val();
        let price = $(this).find('.unit-price').val();
        if (iid) {
            orderDetails.push({ iid: iid, qty: parseInt(qty), unitPrice: parseFloat(price) });
        }
    });

    let orderData = {
        date: $('#order-date').val(),
        cid: $('#order-customer').val(),
        orderDetails: orderDetails
    };

    if (!orderData.cid || orderDetails.length === 0) {
        alert("Please select customer and items!");
        return;
    }

    $.ajax({
        method: "POST",
        url: "http://localhost:8080/api/v1/Order",
        contentType: "application/json",
        data: JSON.stringify(orderData),
        success: function () {
            alert("Order Placed Successfully!");
            location.reload();
        },
        error: function (xhr) {
            alert("Error: " + xhr.responseJSON.message);
        }
    });
}

// ===================== GET ALL ORDERS =====================

function getAllOrders() {

    $.ajax({

        method: "GET",

        url: "http://localhost:8080/api/v1/Order",

        success: function (response) {

            let list = response.data;

            let tbody = $('#order-history-list');

            tbody.empty();

            if (!list || list.length === 0) {
                tbody.html('<tr><td colspan="3" style="text-align:center;">No Orders found in Database</td></tr>');
                return;
            }

            let rows = '';

            list.forEach(o => {

                rows += `
                <tr onclick="fillOrderForm('${o.oid}', '${o.date}', '${o.cid}')" style="cursor:pointer">
                    <th>${o.oid}</th>
                    <th>${o.date}</th>
                    <th>${o.cid}</th>
                    
                </tr>
                `;

            });

            tbody.html(rows);

        },

        error: function (xhr) {

            console.error("AJAX Error:", xhr);
            alert("Failed to load Orders!");

        }

    });

}


getAllOrders();
