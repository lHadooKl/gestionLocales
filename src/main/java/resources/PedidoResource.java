package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.PedidoService;
import dto.PedidoRequest;
import entities.Pedido;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    private PedidoService pedidoService;

    @POST
    public Response crear(PedidoRequest req) {
        try {
            Pedido p = pedidoService.crearPedidoConItems(req);
            return Response.status(Response.Status.CREATED).entity(p).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        Pedido p = pedidoService.find(id);
        if (p == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(p).build();
    }
}
