// detalles-pedido.js

document.addEventListener("DOMContentLoaded", cargarDetalles);
document.getElementById("detalleForm").addEventListener("submit", guardarDetalle);

async function cargarDetalles() {
    try {
        const detalles = await apiRequest("/detallespedido", "GET"); 
        const tabla = document.getElementById("tablaDetalles");
        tabla.innerHTML = "";

        detalles.forEach(d => {
            tabla.innerHTML += `
                <tr>
                    <td>${d.id_detalle}</td>
                    <td>${d.pedido ? d.pedido.id_pedido : '-'}</td>
                    <td>${d.producto ? d.producto.nombre : '-'}</td>
                    <td>${d.cantidad}</td>
                    <td>${d.precio_unitario}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="eliminar(${d.id_detalle})">
                            Eliminar
                        </button>
                    </td>
                </tr>`;
        });
    } catch (err) {
        console.error("Error al cargar detalles:", err);
        alert("No se pudieron cargar los detalles de pedido.");
    }
}

async function guardarDetalle(e) {
    e.preventDefault();

    const detalle = {
        pedido: { id_pedido: parseInt(document.getElementById("id_pedido").value) },
        producto: { id_producto: parseInt(document.getElementById("id_producto").value) },
        cantidad: parseInt(document.getElementById("cantidad").value),
        precio_unitario: parseFloat(document.getElementById("precio_unitario").value)
    };

    try {
        await apiRequest("/detallespedido", "POST", detalle);
        e.target.reset();
        cargarDetalles();
    } catch (err) {
        console.error("Error al guardar detalle:", err);
        alert("No se pudo guardar el detalle del pedido.");
    }
}

async function eliminar(id) {
    if (!confirm("¿Deseas eliminar este detalle de pedido?")) return;
    try {
        await apiRequest(`/detallespedido/${id}`, "DELETE"); 
        cargarDetalles();
    } catch (err) {
        console.error("Error al eliminar detalle:", err);
        alert("No se pudo eliminar el detalle del pedido.");
    }
}
