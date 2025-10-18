package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import entities.DetallePedido;
import java.util.List;

@Stateless
public class DetallePedidoRepository {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public DetallePedido find(Long id) {
        return em.find(DetallePedido.class, id);
    }

    public List<DetallePedido> findAll() {
        TypedQuery<DetallePedido> q = em.createQuery("SELECT d FROM DetallePedido d", DetallePedido.class);
        return q.getResultList();
    }

    public DetallePedido save(DetallePedido d) {
        if (d.getId_detalle() == null) {
            em.persist(d);
            return d;
        } else {
            return em.merge(d);
        }
    }

    public void delete(Long id) {
        DetallePedido d = em.find(DetallePedido.class, id);
        if (d != null) em.remove(d);
    }
}
