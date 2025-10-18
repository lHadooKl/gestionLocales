package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.ProductoService;
import entities.Producto;
import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @Inject
    private ProductoService productoService;

    @GET
    public Response listar() {
        List<Producto> lista = productoService.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Producto p = productoService.find(id);
        if (p == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(p).build();
    }

    @POST
    public Response crear(Producto p) {
        Producto creado = productoService.createOrUpdate(p);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Producto p) {
        Producto exist = productoService.find(id);
        if (exist == null) return Response.status(Response.Status.NOT_FOUND).build();
        p.setId_producto(id);
        Producto actualizado = productoService.createOrUpdate(p);
        return Response.ok(actualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        productoService.delete(id);
        return Response.noContent().build();
    }
}
