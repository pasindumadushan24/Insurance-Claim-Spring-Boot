$(document).ready(function () {

    getAllCustomers();

});



// ===================== GET ALL CUSTOMERS =====================

function getAllCustomers() {

    $.ajax({

        method: "GET",

        url: "http://localhost:8080/api/v1/Customer",

        success: function (response) {

            let list = response.data;

            let tbody = $('#customer-list');

            tbody.empty();



            if (!list || list.length === 0) {

                tbody.html('<tr><td colspan="3" style="text-align:center;">No customers found in Database</td></tr>');

                return;

            }



            let rows = '';

            list.forEach(c => {

// String arguments pass කරන නිසා quotes ('') අනිවාර්යයි

                rows += `<tr onclick="fillForm('${c.cid}', '${c.name}', '${c.address}')" style="cursor:pointer">

<td>${c.cid}</td>

<td>${c.name}</td>

<td>${c.address}</td>

</tr>`;

            });

            tbody.html(rows);

        },

        error: function (xhr) {

            console.error("AJAX Error:", xhr);

        }

    });

}



// ===================== SAVE CUSTOMER =====================

$(document).ready(function () {

    getAllCustomers();

    setNextId(); // මුලින්ම පේජ් එක ලෝඩ් වෙද්දී ID එක ගන්න

});



// Next ID එක Backend එකෙන් අරන් Field එකට දාන Function එක

function setNextId() {

    $.ajax({

        method: "GET",

        url: "http://localhost:8080/api/v1/Customer/nextId",

        success: function (res) {

            $('#cid').val(res.data); // Next ID එක field එකට සෙට් කරනවා

        },

        error: function (xhr) {

            console.error("Next ID fetching failed");

        }

    });

}



// Update කරපු Save Function එක

function saveCustomer() {

    let name = $('#name').val().trim();

    let address = $('#address').val().trim();



    if (name === "" || address === "") {

        alert("Fields cannot be empty!");

        return;

    }



    $.ajax({

        method: "POST",

        url: "http://localhost:8080/api/v1/Customer",

        contentType: "application/json",

        data: JSON.stringify({ name: name, address: address }),

        success: function (res) {

            alert("Customer Saved Successfully!");



            clearFields(); // ඔක්කොම ක්ලියර් කරනවා

            getAllCustomers(); // ටේබල් එක රිප්‍රෙෂ් කරනවා

            setNextId(); // ආයෙත් අලුත් Next ID එක අරන් ෆීල්ඩ් එකට දානවා

        },

        error: function (xhr) {

            alert("Save Failed!");

        }

    });

}

// ===================== UPDATE CUSTOMER =====================

function updateCustomer() {

    let cid = $('#cid').val();

    let name = $('#name').val().trim();

    let address = $('#address').val().trim();



    if (cid === "") {

        alert("Please select a customer from the table first!");

        return;

    }



    $.ajax({

        method: "PUT",

        url: "http://localhost:8080/api/v1/Customer",

        contentType: "application/json",

        data: JSON.stringify({

            cid: cid,

            name: name,

            address: address

        }),

        success: function (res) {

            alert("Customer Updated Successfully!");

            clearFields();

            getAllCustomers();

        },

        error: function (xhr) {

            alert("Update Failed!");

        }


    });

}



// ===================== DELETE CUSTOMER =====================

function deleteCustomer() {

    let cid = $('#cid').val();



    if (cid === "") {

        alert("Please select a customer to delete!");

        return;

    }



    if (confirm("Are you sure you want to delete this customer?")) {

        $.ajax({

            method: "DELETE",

            url: "http://localhost:8080/api/v1/Customer/" + cid,

            success: function (res) {

                alert("Customer Deleted Successfully!");

                clearFields();

                getAllCustomers();

            },

            error: function (xhr) {

                alert("Delete Failed!");

            }

        });

    }

}
// ===================== UTILS =====================
function fillForm(cid, name, address) {
    $('#cid').val(cid);
    $('#name').val(name);
    $('#address').val(address);
}

function clearFields() {
    $('#cid').val('');
    $('#name').val('');
    $('#address').val('');
}