package services;

import entities.DetallePedido;
import entities.Pedido;
import entities.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DetallePedidoService {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public DetallePedido create(DetallePedido d) {
        if (d.getPedido() != null && d.getPedido().getId_pedido() != null) {
            d.setPedido(em.find(Pedido.class, d.getPedido().getId_pedido()));
        }
        if (d.getProducto() != null && d.getProducto().getId_producto() != null) {
            d.setProducto(em.find(Producto.class, d.getProducto().getId_producto()));
        }

        em.persist(d);
        return d;
    }

    public List<DetallePedido> findAll() {
        return em.createQuery("SELECT d FROM DetallePedido d", DetallePedido.class).getResultList();
    }

    public void delete(int id) {
        DetallePedido d = em.find(DetallePedido.class, id);
        if (d != null) em.remove(d);
    }
}
