const API_URL_DETALLES = "http://localhost:8080/SistemaGestionLocales/api/detalles_pedido";

document.addEventListener("DOMContentLoaded", () => {
  cargarDetalles();

  document.getElementById("detalleForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const nuevoDetalle = {
      idPedido: parseInt(document.getElementById("idPedido").value),
      idProducto: parseInt(document.getElementById("idProducto").value),
      cantidad: parseInt(document.getElementById("cantidad").value),
      precioUnitario: parseFloat(document.getElementById("precioUnitario").value)
    };

    const resp = await fetch(API_URL_DETALLES, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevoDetalle)
    });

    if (resp.ok) {
      alert("Detalle agregado correctamente");
      e.target.reset();
      cargarDetalles();
    } else {
      alert("Error al agregar detalle");
    }
  });
});

async function cargarDetalles() {
  const resp = await fetch(API_URL_DETALLES);
  const detalles = await resp.json();

  const tbody = document.querySelector("#tablaDetalles tbody");
  tbody.innerHTML = "";

  detalles.forEach(d => {
    tbody.innerHTML += `
      <tr>
        <td>${d.idDetalle}</td>
        <td>${d.idPedido}</td>
        <td>${d.idProducto}</td>
        <td>${d.cantidad}</td>
        <td>${d.precioUnitario.toFixed(2)}</td>
        <td>
          <button class="btn btn-danger btn-sm" onclick="eliminarDetalle(${d.idDetalle})">Eliminar</button>
        </td>
      </tr>
    `;
  });
}

async function eliminarDetalle(id) {
  if (confirm("¿Eliminar este detalle?")) {
    const resp = await fetch(`${API_URL_DETALLES}/${id}`, { method: "DELETE" });
    if (resp.ok) {
      alert("Detalle eliminado correctamente");
      cargarDetalles();
    } else {
      alert("Error al eliminar detalle");
    }
  }
}
