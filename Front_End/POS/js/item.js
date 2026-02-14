$(document).ready(function () {
    getAllItems();
    setNextItemId();
});

// ===================== GET ALL ITEMS =====================
function getAllItems() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/Item",
        success: function (response) {
            let list = response.data;
            let tbody = $('#item-list');
            tbody.empty();

            if (!list || list.length === 0) {
                tbody.html('<tr><td colspan="4" style="text-align:center;">No items found in Database</td></tr>');
                return;
            }

            let rows = '';
            list.forEach(item => {
                // Table row එක click කරද්දී fillForm එකට data යවනවා
                rows += `<tr onclick="fillItemForm('${item.iid}', '${item.iName}', '${item.qty}', '${item.price}')" style="cursor:pointer">
                            <td>${item.iid}</td>
                            <td>${item.iName}</td>
                            <td>${item.qty}</td>
                            <td>${item.price}</td>
                         </tr>`;
            });
            tbody.html(rows);
        },
        error: function (xhr) {
            console.error("AJAX Error:", xhr);
        }
    });
}

// ===================== GET NEXT ITEM ID =====================
function setNextItemId() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/Item/nextId",
        success: function (res) {
            $('#iid').val(res.data);
        },
        error: function (xhr) {
            console.error("Next Item ID fetching failed");
        }
    });
}

// ===================== SAVE ITEM =====================
function saveItem() {
    let iid = $('#iid').val(); // Auto-generated ID එක
    let iName = $('#iName').val().trim();
    let qty = $('#qty').val().trim();
    let price = $('#price').val().trim();

    if (iName === "" || qty === "" || price === "") {
        alert("Fields cannot be empty!");
        return;
    }

    $.ajax({
        method: "POST",
        url: "http://localhost:8080/api/v1/Item",
        contentType: "application/json",
        data: JSON.stringify({
            iid: iid,
            iName: iName,
            qty: parseInt(qty),
            price: parseFloat(price)
        }),
        success: function (res) {
            alert("Item Saved Successfully!");
            clearItemFields();
            getAllItems();
            setNextItemId();
        },
        error: function (xhr) {
            alert("Save Failed!");
        }
    });
}

// ===================== UPDATE ITEM =====================
function updateItem() {
    let iid = $('#iid').val();
    let iName = $('#iName').val().trim();
    let qty = $('#qty').val().trim();
    let price = $('#price').val().trim();

    if (iid === "") {
        alert("Please select an item from the table first!");
        return;
    }

    $.ajax({
        method: "PUT",
        url: "http://localhost:8080/api/v1/Item",
        contentType: "application/json",
        data: JSON.stringify({
            iid: iid,
            iName: iName,
            qty: parseInt(qty),
            price: parseFloat(price)
        }),
        success: function (res) {
            alert("Item Updated Successfully!");
            clearItemFields();
            getAllItems();
            setNextItemId();
        },
        error: function (xhr) {
            alert("Update Failed!");
        }
    });
}

// ===================== DELETE ITEM =====================
function deleteItem() {
    let iid = $('#iid').val();

    if (iid === "") {
        alert("Please select an item to delete!");
        return;
    }

    if (confirm("Are you sure you want to delete this item?")) {
        $.ajax({
            method: "DELETE",
            url: "http://localhost:8080/api/v1/Item/" + iid,
            success: function (res) {
                alert("Item Deleted Successfully!");
                clearItemFields();
                getAllItems();
                setNextItemId();
            },
            error: function (xhr) {
                alert("Delete Failed!");
            }
        });
    }
}

// ===================== UTILS =====================
function fillItemForm(iid, name, qty, price) {
    $('#iid').val(iid);
    $('#iName').val(name);
    $('#qty').val(qty);
    $('#price').val(price);
}

function clearItemFields() {
    $('#iid').val('');
    $('#iName').val('');
    $('#qty').val('');
    $('#price').val('');
}