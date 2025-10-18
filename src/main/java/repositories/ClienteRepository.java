package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import entities.Cliente;
import java.util.List;

@Stateless
public class ClienteRepository {

    @PersistenceContext(unitName = "jpaGestionLocales")
    private EntityManager em;

    public Cliente find(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> findAll() {
        TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return q.getResultList();
    }

    public Cliente save(Cliente c) {
        if (c.getId_cliente() == null) {
            em.persist(c);
            return c;
        } else {
            return em.merge(c);
        }
    }

    public void delete(Long id) {
        Cliente c = em.find(Cliente.class, id);
        if (c != null) em.remove(c);
    }
}
