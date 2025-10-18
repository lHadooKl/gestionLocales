package resources;

import entities.HistorialEntrega;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.HistorialService;
import java.util.List;

@Path("/historial")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistorialResource {

    @Inject
    private HistorialService service;

    @GET
    public List<HistorialEntrega> getAll() {
        return service.findAll();
    }

    @POST
    public Response create(HistorialEntrega h) {
        HistorialEntrega nueva = service.create(h);
        return Response.status(Response.Status.CREATED).entity(nueva).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
