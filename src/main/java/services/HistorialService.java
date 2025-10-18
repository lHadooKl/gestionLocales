package services;

import entities.HistorialEntrega;
import entities.Pedido;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class HistorialService {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public HistorialEntrega create(HistorialEntrega h) {
        // Vincular el pedido existente si tiene ID
        if (h.getPedido() != null && h.getPedido().getId_pedido() != null) {
            Pedido pedido = em.find(Pedido.class, h.getPedido().getId_pedido());
            h.setPedido(pedido);
        }
        em.persist(h);
        return h;
    }

    public List<HistorialEntrega> findAll() {
        return em.createQuery("SELECT h FROM HistorialEntrega h", HistorialEntrega.class)
                 .getResultList();
    }

    public void delete(Long id) {
        HistorialEntrega h = em.find(HistorialEntrega.class, id);
        if (h != null) em.remove(h);
    }
}
