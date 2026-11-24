
const API = "/api/v1/users";

// GET ALL USERS

async function loadUsers() {

    const table = document.getElementById("userTable");
    const totalUsers = document.getElementById("totalUsers");

    if (!table) return;

    try {

        table.innerHTML = `
            <tr><td colspan="4">Loading users...</td></tr>
        `;

        const response = await fetch(`${API}/all`);

        if (!response.ok) {
            throw new Error("Failed to retrieve users");
        }

        const users = await response.json();

        table.innerHTML = "";

        if (totalUsers) {
            totalUsers.textContent = users.length;
        }

        if (users.length === 0) {
            table.innerHTML = `
                <tr><td colspan="4">No registered users found.</td></tr>
            `;
            return;
        }

        users.forEach(user => {

            const row = document.createElement("tr");

            const id = document.createElement("td");
            id.textContent = user.id;

            const name = document.createElement("td");
            name.textContent = user.name;

            const email = document.createElement("td");
            email.textContent = user.email;

            const actions = document.createElement("td");

            // VIEW
            const viewButton = document.createElement("button");
            viewButton.textContent = "View";

            viewButton.onclick = function() {
                window.location.href =
                    "/searchUser.html?id=" + user.id;
            };

            // EDIT
            const editButton = document.createElement("button");
            editButton.textContent = "Edit";

            editButton.onclick = function() {
                window.location.href =
                    "/edit.html?id=" + user.id;
            };

            // DELETE
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";

            deleteButton.onclick = function() {
                deleteUser(user.id);
            };

            actions.append(
                viewButton,
                document.createTextNode(" "),
                editButton,
                document.createTextNode(" "),
                deleteButton
            );

            row.append(id, name, email, actions);

            table.appendChild(row);

        });

    } catch (error) {

        console.error(error);

        table.innerHTML = `
            <tr><td colspan="4">Error loading users</td></tr>
        `;
    }
}
//create
document.addEventListener("DOMContentLoaded", function() {

    const createForm = document.getElementById("createForm");

    if (createForm) {

        createForm.addEventListener("submit", async function(event) {

            event.preventDefault();

            const user = {
                name: document.getElementById("createName").value.trim(),
                email: document.getElementById("createEmail").value.trim()
            };

            try {

                const response = await fetch(`${API}/register`, {

                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(user)

                });

                if (!response.ok) {
                    throw new Error(await response.text());
                }

                const result = await response.json();

                alert("User registered successfully! ID: " + result.id);

                createForm.reset();

            } catch(error) {

                console.error(error);
                alert("Error: " + error.message);

            }

        });

    }

    // Automatically load all users
    if (document.getElementById("userTable")) {
        loadUsers();
    }

});


// =====================================
// VIEW USER BY ID
// =====================================

async function viewUserById() {

    const id = document.getElementById("searchId").value;

    const table = document.getElementById("singleUserTable");

    if (!id || Number(id) < 1) {
        alert("Enter a valid user ID");
        return;
    }

    try {

        const response = await fetch(`${API}/us/${id}`);

        if (!response.ok) {
            throw new Error("User not found");
        }

        const user = await response.json();

        table.innerHTML = "";

        const row = document.createElement("tr");

        [user.id, user.name, user.email].forEach(value => {

            const cell = document.createElement("td");
            cell.textContent = value;
            row.appendChild(cell);

        });

        table.appendChild(row);

    } catch(error) {

        console.error(error);
        alert(error.message);

    }

}

// UPDATE USER

async function loadUserForEdit() {

    const editId = document.getElementById("editId");

    if (!editId) return;

    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (!id) {
        alert("User ID is missing");
        return;
    }

    try {

        const response = await fetch(`${API}/us/${id}`);

        if (!response.ok) {
            throw new Error("User not found");
        }

        const user = await response.json();

        editId.value = user.id;
        document.getElementById("editName").value = user.name;
        document.getElementById("editEmail").value = user.email;

    } catch(error) {

        console.error(error);
        alert(error.message);

    }

}


// UPDATE FORM
document.addEventListener("DOMContentLoaded", function() {

    const editForm = document.getElementById("editForm");

    if (editForm) {

        loadUserForEdit();

        editForm.addEventListener("submit", async function(event) {

            event.preventDefault();

            const id = document.getElementById("editId").value;

            const user = {
                name: document.getElementById("editName").value.trim(),
                email: document.getElementById("editEmail").value.trim()
            };

            try {

                const response = await fetch(`${API}/update/${id}`, {

                    method: "PUT",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(user)

                });

                if (!response.ok) {
                    throw new Error(await response.text());
                }

                alert("User updated successfully!");

                window.location.href = "/index.html";

            } catch(error) {

                console.error(error);
                alert(error.message);

            }

        });

    }

});


// DELETE USER


async function deleteUser(id) {

    if (!confirm("Are you sure you want to delete user " + id + "?")) {
        return;
    }

    try {

        const response = await fetch(`${API}/${id}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Failed to delete user");
        }

        alert("User deleted successfully!");

        loadUsers();

    } catch(error) {

        console.error(error);
        alert(error.message);

    }

}
