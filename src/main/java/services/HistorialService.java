package services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositories.HistorialEntregaRepository;
import entities.HistorialEntrega;
import java.util.List;

@Stateless
public class HistorialService {

    @Inject
    private HistorialEntregaRepository historialRepo;

    public List<HistorialEntrega> historialPorCliente(Long idCliente) {
        return historialRepo.findByClienteId(idCliente);
    }
}
