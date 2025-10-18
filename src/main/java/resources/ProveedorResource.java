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
    private ProveedorService service;

    @GET public List<Proveedor> list() { return service.findAll(); }
    @GET @Path("/{id}") public Proveedor get(@PathParam("id") Long id) { return service.find(id); }
    @POST public Response create(Proveedor p) { service.create(p); return Response.status(Response.Status.CREATED).entity(p).build(); }
    @PUT @Path("/{id}") public Proveedor update(@PathParam("id") Long id, Proveedor p) { p.setId_proveedor(id); return service.update(p); }
    @DELETE @Path("/{id}") public Response delete(@PathParam("id") Long id) { service.delete(id); return Response.noContent().build(); }
}
