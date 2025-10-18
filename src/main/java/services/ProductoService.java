package services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositories.ProductoRepository;
import entities.Producto;
import java.util.List;

@Stateless
public class ProductoService {

    @Inject
    private ProductoRepository productoRepo;

    public Producto find(Long id) {
        return productoRepo.find(id);
    }

    public List<Producto> findAll() {
        return productoRepo.findAll();
    }

    public Producto createOrUpdate(Producto p) {
        return productoRepo.save(p);
    }

    public void delete(Long id) {
        productoRepo.delete(id);
    }
}
