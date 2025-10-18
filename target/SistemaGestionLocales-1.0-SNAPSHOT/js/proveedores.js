// proveedores.js

document.addEventListener("DOMContentLoaded", cargarProveedores);
document.getElementById("proveedorForm").addEventListener("submit", guardarProveedor);

async function cargarProveedores() {
    try {
        const proveedores = await apiRequest("/proveedores");
        const tabla = document.getElementById("tablaProveedores");
        tabla.innerHTML = "";

        proveedores.forEach(p => {
            tabla.innerHTML += `
                <tr>
                    <td>${p.id_proveedor}</td>
                    <td>${p.nombre}</td>
                    <td>${p.contacto}</td>
                    <td>${p.telefono}</td>
                    <td>${p.email}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="eliminar(${p.id_proveedor})">
                            Eliminar
                        </button>
                    </td>
                </tr>`;
        });
    } catch (error) {
        console.error("Error al cargar proveedores:", error);
        alert("No se pudieron cargar los proveedores.");
    }
}

async function guardarProveedor(e) {
    e.preventDefault();

    const proveedor = {
        nombre: nombre.value,
        contacto: contacto.value,
        telefono: telefono.value,
        email: email.value
    };

    try {
        await apiRequest("/proveedores", "POST", proveedor);
        e.target.reset();
        cargarProveedores();
    } catch (error) {
        console.error("Error al guardar proveedor:", error);
        alert("No se pudo guardar el proveedor.");
    }
}

async function eliminar(id) {
    if (!confirm("¿Seguro que deseas eliminar este proveedor?")) return;

    try {
        await apiRequest(`/proveedores/${id}`, "DELETE");
        cargarProveedores();
    } catch (error) {
        console.error("Error al eliminar proveedor:", error);
        alert("No se pudo eliminar el proveedor.");
    }
}
