// pedidos.js
document.addEventListener("DOMContentLoaded", cargarPedidos);
document.getElementById("pedidoForm").addEventListener("submit", guardarPedido);

async function cargarPedidos() {
    const pedidos = await apiRequest("/pedidos"); 
    const tabla = document.getElementById("tablaPedidos");
    tabla.innerHTML = "";

    if (!pedidos || pedidos.length === 0) {
        tabla.innerHTML = `<tr><td colspan="5" class="text-center">No hay pedidos registrados</td></tr>`;
        return;
    }

    pedidos.forEach(p => {
        tabla.innerHTML += `
        <tr>
            <td>${p.id_pedido}</td>
            <td>${p.cliente ? p.cliente.nombre : '-'}</td>
            <td>${p.fecha_pedido ? p.fecha_pedido.replace('T', ' ').substring(0, 19) : '-'}</td>
            <td>${p.estado}</td>
            <td>
                <button class="btn btn-sm btn-danger" onclick="eliminar(${p.id_pedido})">
                    Eliminar
                </button>
            </td>
        </tr>`;
    });
}

async function guardarPedido(e) {
    e.preventDefault();

    const pedido = {
        cliente: { id_cliente: parseInt(document.getElementById("id_cliente").value) },
        estado: document.getElementById("estado").value
    };

    try {
        await apiRequest("/pedidos", "POST", pedido); 
        alert("Pedido creado correctamente");
        e.target.reset();
        cargarPedidos();
    } catch (err) {
        alert("Error al crear el pedido: " + err.message);
    }
}

async function eliminar(id) {
    if (!confirm("¿Deseas eliminar este pedido?")) return;

    try {
        await apiRequest(`/pedidos/${id}`, "DELETE"); 
        alert("Pedido eliminado correctamente");
        cargarPedidos();
    } catch (err) {
        alert("Error al eliminar el pedido: " + err.message);
    }
}

// 🔧 Función genérica para llamadas a la API
async function apiRequest(endpoint, method = "GET", body = null) {
    const baseUrl = "http://localhost:8080/gestion-inventarios/api"; 
    const options = {
        method,
        headers: { "Content-Type": "application/json" }
    };
    if (body) options.body = JSON.stringify(body);

    const res = await fetch(baseUrl + endpoint, options);
    if (!res.ok) {
        const msg = await res.text();
        throw new Error(msg || "Error en la solicitud");
    }
    return res.status !== 204 ? await res.json() : null;
}
