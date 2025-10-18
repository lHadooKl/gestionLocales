package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import entities.Proveedor;
import java.util.List;

@Stateless
public class ProveedorRepository {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public Proveedor find(Long id) {
        return em.find(Proveedor.class, id);
    }

    public List<Proveedor> findAll() {
        TypedQuery<Proveedor> q = em.createQuery("SELECT pr FROM Proveedor pr", Proveedor.class);
        return q.getResultList();
    }

    public Proveedor save(Proveedor pr) {
        if (pr.getIdProveedor() == null) {  // ✅ nombre corregido
            em.persist(pr);
            return pr;
        } else {
            return em.merge(pr);
        }
    }

    public void delete(Long id) {
        Proveedor p = em.find(Proveedor.class, id);
        if (p != null) em.remove(p);
    }
}
