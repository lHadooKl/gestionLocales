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
    public List<Producto> list() { return productoService.findAll(); }

    @GET
    @Path("/{id}")
    public Producto get(@PathParam("id") Long id) { return productoService.find(id); }

    @POST
    public Response create(Producto p) {
        productoService.create(p);
        return Response.status(Response.Status.CREATED).entity(p).build();
    }

    @PUT
    @Path("/{id}")
    public Producto update(@PathParam("id") Long id, Producto p) {
        p.setId_producto(id);
        return productoService.update(p);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        productoService.delete(id);
        return Response.noContent().build();
    }
}
