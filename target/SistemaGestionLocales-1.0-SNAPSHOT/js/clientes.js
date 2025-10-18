// clientes.js
document.addEventListener("DOMContentLoaded", cargarClientes);
document.getElementById("clienteForm").addEventListener("submit", guardarCliente);

async function cargarClientes() {
    try {
        const clientes = await apiRequest("/clientes", "GET");
        const tabla = document.getElementById("tablaClientes");
        tabla.innerHTML = "";
        clientes.forEach(c => {
            tabla.innerHTML += `
            <tr>
                <td>${c.id_cliente}</td>
                <td>${c.nombre}</td>
                <td>${c.direccion}</td>
                <td>${c.telefono}</td>
                <td>${c.email}</td>
                <td>
                    <button class="btn btn-sm btn-danger" onclick="eliminar(${c.id_cliente})">
                        Eliminar
                    </button>
                </td>
            </tr>`;
        });
    } catch (err) {
        console.error("Error al cargar clientes:", err);
        alert("Error al cargar los clientes.");
    }
}

async function guardarCliente(e) {
    e.preventDefault();

    const cliente = {
        nombre: document.getElementById("nombre").value,
        direccion: document.getElementById("direccion").value,
        telefono: document.getElementById("telefono").value,
        email: document.getElementById("email").value
    };

    try {
        await apiRequest("/clientes", "POST", cliente);
        e.target.reset();
        cargarClientes();
    } catch (err) {
        console.error("Error al guardar cliente:", err);
        alert("No se pudo guardar el cliente.");
    }
}

async function eliminar(id) {
    if (!confirm("¿Deseas eliminar este cliente?")) return;

    try {
        await apiRequest(`/clientes/${id}`, "DELETE");
        cargarClientes();
    } catch (err) {
        console.error("Error al eliminar cliente:", err);
        alert("No se pudo eliminar el cliente.");
    }
}
