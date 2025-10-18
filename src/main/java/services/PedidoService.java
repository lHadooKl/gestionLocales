package services;

import entities.Cliente;
import entities.Pedido;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class PedidoService {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public List<Pedido> listarPedidos() {
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    public Pedido crearPedido(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getCliente().getId_cliente() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }

        Cliente cliente = em.find(Cliente.class, pedido.getCliente().getId_cliente());
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        pedido.setCliente(cliente);
        pedido.setFecha_pedido(LocalDateTime.now());
        em.persist(pedido);
        return pedido;
    }

    public void eliminarPedido(Long id) {
        Pedido pedido = em.find(Pedido.class, id);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado");
        }
        em.remove(pedido);
    }
}
