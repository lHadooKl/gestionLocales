package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.ClienteService;
import entities.Cliente;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
    @Inject private ClienteService service;

    @GET public List<Cliente> list() { return service.findAll(); }
    @GET @Path("/{id}") public Cliente get(@PathParam("id") Long id) { return service.find(id); }
    @POST public Response create(Cliente c) { service.create(c); return Response.status(Response.Status.CREATED).entity(c).build(); }
    @PUT @Path("/{id}") public Cliente update(@PathParam("id") Long id, Cliente c) { c.setId_cliente(id); return service.update(c); }
    @DELETE @Path("/{id}") public Response delete(@PathParam("id") Long id) { service.delete(id); return Response.noContent().build(); }
}
