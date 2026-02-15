let allItems = []; // සියලුම items තබා ගැනීමට

$(document).ready(function () {
    loadCustomers();
    loadItems();
    setNextOrderId();

    // අද දිනය set කිරීම
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
        let itemId = $(this).find('.item-select').val();
        let qty = $(this).find('.buy-qty').val();
        let price = $(this).find('.unit-price').val();
        if (itemId) {
            orderDetails.push({ itemId: itemId, qty: parseInt(qty), unitPrice: parseFloat(price) });
        }
    });

    let orderData = {
        date: $('#order-date').val(),
        customerId: $('#order-customer').val(),
        orderDetails: orderDetails
    };

    if (!orderData.customerId || orderDetails.length === 0) {
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