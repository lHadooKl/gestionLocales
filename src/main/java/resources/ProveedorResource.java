package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.ProveedorService;
import entities.Proveedor;
import java.util.List;

@Path("/proveedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProveedorResource {

    @Inject
    private ProveedorService proveedorService;

    // ✅ Listar todos los proveedores
    @GET
    public Response listar() {
        List<Proveedor> lista = proveedorService.findAll();
        return Response.ok(lista).build();
    }

    // ✅ Obtener un proveedor por su ID
    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Proveedor proveedor = proveedorService.find(id);
        if (proveedor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Proveedor no encontrado\"}")
                    .build();
        }
        return Response.ok(proveedor).build();
    }

    // ✅ Crear un nuevo proveedor
    @POST
    public Response crear(Proveedor proveedor) {
        try {
            Proveedor creado = proveedorService.save(proveedor);
            return Response.status(Response.Status.CREATED)
                    .entity(creado)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"No se pudo crear el proveedor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // ✅ Actualizar un proveedor existente
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Proveedor proveedor) {
        Proveedor existente = proveedorService.find(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Proveedor no encontrado\"}")
                    .build();
        }

        proveedor.setIdProveedor(id);
        Proveedor actualizado = proveedorService.save(proveedor);
        return Response.ok(actualizado).build();
    }

    // ✅ Eliminar un proveedor
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        Proveedor existente = proveedorService.find(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Proveedor no encontrado\"}")
                    .build();
        }

        proveedorService.delete(id);
        return Response.noContent().build();
    }
}
