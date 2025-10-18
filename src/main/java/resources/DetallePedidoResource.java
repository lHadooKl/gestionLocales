package resources;

import entities.DetallePedido;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import services.DetallePedidoService;
import java.util.List;

@Path("/detallespedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetallePedidoResource {

    @EJB
    private DetallePedidoService service;

    @GET
    public List<DetallePedido> getAll() {
        return service.findAll();
    }

    @POST
    public DetallePedido create(DetallePedido d) {
        return service.create(d);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }
}
