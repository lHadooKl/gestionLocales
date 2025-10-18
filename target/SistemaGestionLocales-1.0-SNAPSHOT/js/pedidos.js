const API_URL_PED = "http://localhost:8080/SistemaGestionLocales/api/pedidos";

document.addEventListener("DOMContentLoaded", () => {
  cargarPedidos();

  document.getElementById("pedidoForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const nuevo = {
      idCliente: document.getElementById("idCliente").value,
      estado: document.getElementById("estado").value
    };

    const resp = await fetch(API_URL_PED, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevo)
    });

    if (resp.ok) {
      alert("Pedido agregado");
      e.target.reset();
      cargarPedidos();
    } else {
      alert("Error al agregar pedido");
    }
  });
});

async function cargarPedidos() {
  const resp = await fetch(API_URL_PED);
  const pedidos = await resp.json();

  const tbody = document.querySelector("#tablaPedidos tbody");
  tbody.innerHTML = "";

  pedidos.forEach(p => {
    tbody.innerHTML += `
      <tr>
        <td>${p.idPedido}</td>
        <td>${p.idCliente}</td>
        <td>${p.fechaPedido}</td>
        <td>${p.estado}</td>
        <td><button onclick="eliminarPedido(${p.idPedido})">Eliminar</button></td>
      </tr>
    `;
  });
}

async function eliminarPedido(id) {
  if (confirm("¿Eliminar pedido?")) {
    const resp = await fetch(`${API_URL_PED}/${id}`, { method: "DELETE" });
    if (resp.ok) {
      alert("Pedido eliminado");
      cargarPedidos();
    } else {
      alert("Error al eliminar pedido");
    }
  }
}
