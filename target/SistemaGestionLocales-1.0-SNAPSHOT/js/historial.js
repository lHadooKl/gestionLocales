const API_URL_HIST = "http://localhost:8080/SistemaGestionLocales/api/entregas";

document.getElementById("buscarHistorialForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const idCliente = document.getElementById("idCliente").value;
  const resp = await fetch(`${API_URL_HIST}/cliente/${idCliente}`);

  if (!resp.ok) {
    alert("No se encontró historial para ese cliente");
    return;
  }

  const entregas = await resp.json();
  const tbody = document.querySelector("#tablaHistorial tbody");
  tbody.innerHTML = "";

  entregas.forEach(ent => {
    tbody.innerHTML += `
      <tr>
        <td>${ent.idEntrega}</td>
        <td>${ent.idPedido}</td>
        <td>${ent.fechaEntrega}</td>
        <td>${ent.estadoEntrega}</td>
      </tr>
    `;
  });
});
