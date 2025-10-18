package services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositories.ProveedorRepository;
import entities.Proveedor;
import java.util.List;

@Stateless
public class ProveedorService {

    @Inject
    private ProveedorRepository proveedorRepo;

    public Proveedor find(Long id) {
        return proveedorRepo.find(id);
    }

    public List<Proveedor> findAll() {
        return proveedorRepo.findAll();
    }

    public Proveedor save(Proveedor p) {
        return proveedorRepo.save(p);
    }

    public void delete(Long id) {
        proveedorRepo.delete(id);
    }
}
