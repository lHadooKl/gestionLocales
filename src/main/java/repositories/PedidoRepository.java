package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import entities.Pedido;
import java.util.List;

@Stateless
public class PedidoRepository {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public Pedido find(Long id) {
        return em.find(Pedido.class, id);
    }

    public List<Pedido> findAll() {
        TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
        return q.getResultList();
    }

    public Pedido save(Pedido p) {
        if (p.getId_pedido() == null) {
            em.persist(p);
            return p;
        } else {
            return em.merge(p);
        }
    }

    public void delete(Long id) {
        Pedido p = em.find(Pedido.class, id);
        if (p != null) em.remove(p);
    }
}

