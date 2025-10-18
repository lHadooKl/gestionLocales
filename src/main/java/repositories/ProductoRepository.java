package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import entities.Producto;
import entities.Proveedor; // ✅ Import necesario
import java.util.List;

@Stateless
public class ProductoRepository {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public Producto find(Long id) {
        return em.find(Producto.class, id);
    }

    public List<Producto> findAll() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    public Producto save(Producto p) {
        // ✅ Si viene un proveedor, se asegura de obtener una referencia persistente
        if (p.getProveedor() != null && p.getProveedor().getIdProveedor() != null) {
            p.setProveedor(em.getReference(Proveedor.class, p.getProveedor().getIdProveedor()));
        }

        if (p.getId_producto() == null) {
            em.persist(p);
            return p;
        } else {
            return em.merge(p);
        }
    }

    public void delete(Long id) {
        Producto p = em.find(Producto.class, id);
        if (p != null) em.remove(p);
    }
}
