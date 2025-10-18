package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.HistorialService;
import entities.HistorialEntrega;
import java.util.List;

@Path("/entregas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntregaResource {

    @Inject
    private HistorialService historialService;

    @GET
    @Path("/cliente/{idCliente}")
    public Response historialPorCliente(@PathParam("idCliente") Long idCliente) {
        List<HistorialEntrega> lista = historialService.historialPorCliente(idCliente);
        return Response.ok(lista).build();
    }
}
