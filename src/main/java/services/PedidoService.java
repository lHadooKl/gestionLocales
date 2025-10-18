package services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import repositories.PedidoRepository;
import repositories.ProductoRepository;
import repositories.DetallePedidoRepository;
import repositories.HistorialEntregaRepository;
import repositories.ClienteRepository;
import dto.PedidoRequest;
import entities.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PedidoService {

    @Inject
    private PedidoRepository pedidoRepo;

    @Inject
    private ProductoRepository productoRepo;

    @Inject
    private DetallePedidoRepository detalleRepo;

    @Inject
    private HistorialEntregaRepository historialRepo;

    @Inject
    private ClienteRepository clienteRepo;

    public Pedido crearPedidoConItems(PedidoRequest req) {
        // validar cliente
        Cliente cliente = clienteRepo.find(req.idCliente);
        if (cliente == null) throw new IllegalArgumentException("Cliente no existe");

        // crear pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha_pedido(LocalDate.now());
        pedido.setEstado(req.estado != null ? req.estado : "pendiente");
        pedido = pedidoRepo.save(pedido);

        // procesar items
        List<DetallePedido> detalles = new ArrayList<>();
        for (PedidoRequest.Item item : req.items) {
            Producto prod = productoRepo.find(item.idProducto);
            if (prod == null) throw new IllegalArgumentException("Producto no existe: " + item.idProducto);

            if (item.cantidad == null || item.cantidad <= 0) throw new IllegalArgumentException("Cantidad inválida");

            if (prod.getStock() < item.cantidad) {
                throw new IllegalStateException("Stock insuficiente para producto " + prod.getId_producto());
            }

            // actualizar stock
            prod.setStock(prod.getStock() - item.cantidad);
            productoRepo.save(prod);

            // crear detalle
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(prod);
            detalle.setCantidad(item.cantidad);
            detalle.setPrecio_unitario(item.precioUnitario != null ? item.precioUnitario : prod.getPrecio());
            detalle = detalleRepo.save(detalle);
            detalles.add(detalle);
        }

        // actualizar pedido con detalles
        pedido.setDetalles(detalles);
        pedido = pedidoRepo.save(pedido);

        // crear historial inicial (opcional: podrías crear sólo cuando se entrega)
        HistorialEntrega he = new HistorialEntrega();
        he.setPedido(pedido);
        he.setFecha_entrega(LocalDate.now());
        he.setEstado_entrega("registrado");
        historialRepo.save(he);

        return pedido;
    }

    public Pedido find(Long id) {
        return pedidoRepo.find(id);
    }

    public List<Pedido> findAll() {
        return pedidoRepo.findAll();
    }
}
