const API_URL_CLI = "http://localhost:8080/SistemaGestionLocales/api/clientes";

document.addEventListener("DOMContentLoaded", () => {
  cargarClientes();

  document.getElementById("clienteForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const nuevo = {
      nombre: document.getElementById("nombre").value,
      direccion: document.getElementById("direccion").value,
      telefono: document.getElementById("telefono").value,
      email: document.getElementById("email").value
    };

    const resp = await fetch(API_URL_CLI, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevo)
    });

    if (resp.ok) {
      alert("Cliente agregado");
      e.target.reset();
      cargarClientes();
    } else {
      alert("Error al agregar cliente");
    }
  });
});

async function cargarClientes() {
  const resp = await fetch(API_URL_CLI);
  const clientes = await resp.json();

  const tbody = document.querySelector("#tablaClientes tbody");
  tbody.innerHTML = "";

  clientes.forEach(c => {
    tbody.innerHTML += `
      <tr>
        <td>${c.idCliente}</td>
        <td>${c.nombre}</td>
        <td>${c.direccion}</td>
        <td>${c.telefono}</td>
        <td>${c.email}</td>
        <td><button onclick="eliminarCliente(${c.idCliente})">Eliminar</button></td>
      </tr>
    `;
  });
}

async function eliminarCliente(id) {
  if (confirm("¿Eliminar cliente?")) {
    const resp = await fetch(`${API_URL_CLI}/${id}`, { method: "DELETE" });
    if (resp.ok) {
      alert("Cliente eliminado");
      cargarClientes();
    } else {
      alert("Error al eliminar cliente");
    }
  }
}
