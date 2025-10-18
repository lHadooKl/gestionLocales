package resources;

import entities.Pedido;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.PedidoService;

import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    private PedidoService pedidoService;

    @GET
    public Response listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return Response.ok(pedidos).build();
    }

    @POST
    public Response crearPedido(Pedido pedido) {
        try {
            Pedido nuevo = pedidoService.crearPedido(pedido);
            return Response.status(Response.Status.CREATED).entity(nuevo).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarPedido(@PathParam("id") Long id) {
        try {
            pedidoService.eliminarPedido(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
