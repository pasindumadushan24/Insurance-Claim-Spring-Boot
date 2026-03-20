const form = document.getElementById("userForm");
const table = document.getElementById("userTable");

loadUsers();

form.addEventListener("submit", async function(e){
    e.preventDefault();

    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        role: document.getElementById("role").value
    };

    await fetch("http://localhost:8080/api/users",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(data)
    });

    form.reset();
    loadUsers();
});

async function loadUsers(){
    table.innerHTML="";
    const res = await fetch("http://localhost:8080/api/users");
    const users = await res.json();

    users.forEach(u=>{
        const row = `
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.role}</td>
            <td>
                <button onclick="deleteUser(${u.id})">Delete</button>
            </td>
        </tr>
        `;
        table.innerHTML += row;
    });
}

async function deleteUser(id){
    await fetch(`http://localhost:8080/api/users/${id}`,{method:"DELETE"});
    loadUsers();
}