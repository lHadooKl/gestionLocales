const API_URL = "http://localhost:8080/SistemaGestionLocales/api/productos";

document.addEventListener("DOMContentLoaded", () => {
  cargarProductos();

  document.getElementById("productoForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const nuevoProducto = {
      nombre: document.getElementById("nombre").value,
      descripcion: document.getElementById("descripcion").value,
      precio: parseFloat(document.getElementById("precio").value),
      stock: parseInt(document.getElementById("stock").value),
      proveedor: { id_proveedor: parseInt(document.getElementById("idProveedor").value) }
    };

    const resp = await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevoProducto)
    });

    if (resp.ok) {
      alert("Producto registrado correctamente");
      e.target.reset();
      cargarProductos();
    } else {
      alert("Error al registrar producto");
    }
  });
});

async function cargarProductos() {
  const resp = await fetch(API_URL);
  const productos = await resp.json();

  const tbody = document.querySelector("#tablaProductos tbody");
  tbody.innerHTML = "";

  productos.forEach(p => {
    const fila = `
      <tr>
        <td>${p.idProducto}</td>
        <td>${p.nombre}</td>
        <td>${p.descripcion}</td>
        <td>${p.precio}</td>
        <td>${p.stock}</td>
        <td>${p.proveedor?.nombre ?? "-"}</td>
        <td><button onclick="eliminarProducto(${p.idProducto})">Eliminar</button></td>
      </tr>
    `;
    tbody.innerHTML += fila;
  });
}

async function eliminarProducto(id) {
  if (confirm("¿Eliminar producto?")) {
    const resp = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    if (resp.ok) {
      alert("Producto eliminado");
      cargarProductos();
    } else {
      alert("Error al eliminar producto");
    }
  }
}
