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

    @Inject
    private ClienteService clienteService;

    @GET
    public Response listar() {
        List<Cliente> lista = clienteService.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Cliente c = clienteService.find(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(c).build();
    }

    @POST
    public Response crear(Cliente c) {
        Cliente creado = clienteService.save(c);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Cliente c) {
        Cliente exist = clienteService.find(id);
        if (exist == null) return Response.status(Response.Status.NOT_FOUND).build();
        c.setId_cliente(id);
        Cliente actualizado = clienteService.save(c);
        return Response.ok(actualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.noContent().build();
    }
}
