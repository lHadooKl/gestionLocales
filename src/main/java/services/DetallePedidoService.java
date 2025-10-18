package services;

import entities.DetallePedido;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositories.DetallePedidoRepository;
import java.util.List;

@Stateless
public class DetallePedidoService {

    @Inject
    private DetallePedidoRepository repository;

    public List<DetallePedido> listarTodos() {
        return repository.findAll();
    }

    public DetallePedido obtenerPorId(Long id) {
        return repository.find(id);
    }

    public DetallePedido guardar(DetallePedido detalle) {
        return repository.save(detalle);
    }

    public void eliminar(Long id) {
        repository.delete(id);
    }
}
