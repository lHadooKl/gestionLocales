package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import entities.HistorialEntrega;
import java.util.List;

@Stateless
public class HistorialEntregaRepository {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public HistorialEntrega find(Long id) {
        return em.find(HistorialEntrega.class, id);
    }

    public List<HistorialEntrega> findAll() {
        TypedQuery<HistorialEntrega> q = em.createQuery("SELECT h FROM HistorialEntrega h", HistorialEntrega.class);
        return q.getResultList();
    }

    public HistorialEntrega save(HistorialEntrega h) {
        if (h.getId_entrega() == null) {
            em.persist(h);
            return h;
        } else {
            return em.merge(h);
        }
    }

    public void delete(Long id) {
        HistorialEntrega h = em.find(HistorialEntrega.class, id);
        if (h != null) em.remove(h);
    }

    public List<HistorialEntrega> findByClienteId(Long idCliente) {
        TypedQuery<HistorialEntrega> q = em.createQuery(
            "SELECT he FROM HistorialEntrega he WHERE he.pedido.cliente.id_cliente = :idCliente ORDER BY he.fecha_entrega DESC",
            HistorialEntrega.class);
        q.setParameter("idCliente", idCliente);
        return q.getResultList();
    }
}
