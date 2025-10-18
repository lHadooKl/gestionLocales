// historial.js
document.addEventListener("DOMContentLoaded", cargarHistorial);
document.getElementById("historialForm").addEventListener("submit", guardarHistorial);

async function cargarHistorial() {
    try {
        const historiales = await apiRequest("/historial");
        const tabla = document.getElementById("tablaHistorial");
        tabla.innerHTML = "";

        if (!historiales || historiales.length === 0) {
            tabla.innerHTML = "<tr><td colspan='4'>No hay entregas registradas.</td></tr>";
            return;
        }

        historiales.forEach(h => {
            tabla.innerHTML += `
                <tr>
                    <td>${h.id_entrega}</td>
                    <td>${h.pedido ? h.pedido.id_pedido : '-'}</td>
                    <td>${h.fecha_entrega ?? '-'}</td>
                    <td>${h.estado_entrega ?? '-'}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="eliminar(${h.id_entrega})">
                            Eliminar
                        </button>
                    </td>
                </tr>`;
        });
    } catch (err) {
        console.error("Error al cargar historial:", err);
        alert("No se pudo cargar el historial de entregas.");
    }
}

async function guardarHistorial(e) {
    e.preventDefault();

    const historial = {
        pedido: { id_pedido: parseInt(document.getElementById("id_pedido").value) },
        fecha_entrega: document.getElementById("fecha_entrega").value,
        estado_entrega: document.getElementById("estado_entrega").value
    };

    try {
        await apiRequest("/historial", "POST", historial);
        e.target.reset();
        cargarHistorial();
    } catch (err) {
        console.error("Error al guardar historial:", err);
        alert("No se pudo guardar la entrega en el historial.");
    }
}

async function eliminar(id) {
    if (!confirm("¿Deseas eliminar esta entrega del historial?")) return;
    try {
        await apiRequest(`/historial/${id}`, "DELETE");
        cargarHistorial();
    } catch (err) {
        console.error("Error al eliminar entrega:", err);
        alert("No se pudo eliminar el registro del historial.");
    }
}
