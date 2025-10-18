package services;

import jakarta.ejb.Stateless;
import entities.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductoService {
    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public Producto create(Producto p) {
        em.persist(p);
        return p;
    }

    public Producto update(Producto p) {
        return em.merge(p);
    }

    public void delete(Long id) {
        Producto p = em.find(Producto.class, id);
        if (p != null) em.remove(p);
    }

    public Producto find(Long id) { return em.find(Producto.class, id); }

    public List<Producto> findAll() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }
}
