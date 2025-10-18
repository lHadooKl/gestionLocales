const API_URL_PROV = "http://localhost:8080/SistemaGestionLocales/api/proveedores";

document.addEventListener("DOMContentLoaded", () => {
  cargarProveedores();21

  document.getElementById("proveedorForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const nuevo = {
        
      nombre: document.getElementById("nombre").value,
      contacto: document.getElementById("contacto").value,
      telefono: document.getElementById("telefono").value,
      email: document.getElementById("email").value
      
    };

    const resp = await fetch(API_URL_PROV, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevo)
    });

    if (resp.ok) {
      alert("Proveedor agregado");
      e.target.reset();
      cargarProveedores();
    } else {
      alert("Error al agregar proveedor");
    }
  });
});

async function cargarProveedores() {
  const resp = await fetch(API_URL_PROV);
  const proveedores = await resp.json();

  const tbody = document.querySelector("#tablaProveedores tbody");
  tbody.innerHTML = "";

  proveedores.forEach(p => {
    tbody.innerHTML += `
      <tr>
        <td>${p.idProveedor}</td>
        <td>${p.nombre}</td>
        <td>${p.contacto}</td>
        <td>${p.telefono}</td>
        <td>${p.email}</td>
        <td><button onclick="eliminarProveedor(${p.idProveedor})">Eliminar</button></td>
      </tr>
    `;
  });
}

async function eliminarProveedor(id) {
  if (confirm("¿Eliminar proveedor?")) {
    const resp = await fetch(`${API_URL_PROV}/${id}`, { method: "DELETE" });
    if (resp.ok) {
      alert("Proveedor eliminado");
      cargarProveedores();
    } else {
      alert("Error al eliminar proveedor");
    }
  }
}
