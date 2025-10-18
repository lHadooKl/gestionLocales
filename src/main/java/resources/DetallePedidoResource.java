package resources;

import entities.DetallePedido;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.DetallePedidoService;
import java.util.List;

@Path("/detalles_pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetallePedidoResource {

    @Inject
    private DetallePedidoService service;

    @GET
    public List<DetallePedido> listar() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") Long id) {
        DetallePedido detalle = service.obtenerPorId(id);
        if (detalle == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(detalle).build();
    }

    @POST
    public Response crear(DetallePedido detalle) {
        DetallePedido nuevo = service.guardar(detalle);
        return Response.status(Response.Status.CREATED).entity(nuevo).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, DetallePedido detalle) {
        DetallePedido existente = service.obtenerPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        detalle.setId_detalle(id);
        DetallePedido actualizado = service.guardar(detalle);
        return Response.ok(actualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        DetallePedido existente = service.obtenerPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        service.eliminar(id);
        return Response.noContent().build();
    }
}
