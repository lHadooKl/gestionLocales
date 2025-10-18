package services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositories.ClienteRepository;
import entities.Cliente;
import java.util.List;

@Stateless
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepo;

    public Cliente find(Long id) {
        return clienteRepo.find(id);
    }

    public List<Cliente> findAll() {
        return clienteRepo.findAll();
    }

    public Cliente save(Cliente c) {
        return clienteRepo.save(c);
    }

    public void delete(Long id) {
        clienteRepo.delete(id);
    }
}
