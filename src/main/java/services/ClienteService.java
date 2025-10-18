package services;

import jakarta.ejb.Stateless;
import entities.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ClienteService {
    @PersistenceContext(unitName = "jpaGestionLocales")
    
    private EntityManager em;

    public Cliente create(Cliente c) { em.persist(c); return c; }
    public Cliente update(Cliente c) { return em.merge(c); }
    public void delete(Long id) { Cliente c = em.find(Cliente.class, id); if (c != null) em.remove(c); }
    public Cliente find(Long id) { return em.find(Cliente.class, id); }
    public List<Cliente> findAll() { return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList(); }
}
