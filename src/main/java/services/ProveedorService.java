package services;

import jakarta.ejb.Stateless;
import entities.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProveedorService {
    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public Proveedor create(Proveedor p) { em.persist(p); return p; }
    public Proveedor update(Proveedor p) { return em.merge(p); }
    public void delete(Long id) {
        Proveedor p = em.find(Proveedor.class, id); if (p != null) em.remove(p);
    }
    public Proveedor find(Long id) { return em.find(Proveedor.class, id); }
    public List<Proveedor> findAll() { return em.createQuery("SELECT p FROM Proveedor p", Proveedor.class).getResultList(); }
}
