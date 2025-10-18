// productos.js

document.addEventListener("DOMContentLoaded", cargarProductos);
document.getElementById("productoForm").addEventListener("submit", guardarProducto);

async function cargarProductos() {
    try {
        const productos = await apiRequest("/productos");
        const tabla = document.getElementById("tablaProductos");
        tabla.innerHTML = "";

        productos.forEach(p => {
            tabla.innerHTML += `
                <tr>
                    <td>${p.id_producto}</td>
                    <td>${p.nombre}</td>
                    <td>${p.descripcion}</td>
                    <td>${p.precio}</td>
                    <td>${p.stock}</td>
                    <td>${p.proveedor ? p.proveedor.id_proveedor : '-'}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="eliminar(${p.id_producto})">Eliminar</button>
                    </td>
                </tr>`;
        });
    } catch (error) {
        console.error("Error al cargar productos:", error);
        alert("No se pudieron cargar los productos.");
    }
}

async function guardarProducto(e) {
    e.preventDefault();

    const producto = {
        nombre: nombre.value,
        descripcion: descripcion.value,
        precio: parseFloat(precio.value),
        stock: parseInt(stock.value),
        proveedor: { id_proveedor: parseInt(id_proveedor.value) }
    };

    try {
        await apiRequest("/productos", "POST", producto);
        e.target.reset();
        cargarProductos();
    } catch (error) {
        console.error("Error al guardar producto:", error);
        alert("No se pudo guardar el producto.");
    }
}

async function eliminar(id) {
    if (!confirm("¿Seguro que deseas eliminar este producto?")) return;

    try {
        await apiRequest(`/productos/${id}`, "DELETE");
        cargarProductos();
    } catch (error) {
        console.error("Error al eliminar producto:", error);
        alert("No se pudo eliminar el producto.");
    }
}
