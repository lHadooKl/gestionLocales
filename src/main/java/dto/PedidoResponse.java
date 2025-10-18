package dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoResponse {
    public Long idPedido;
    public Long idCliente;
    public LocalDate fechaPedido;
    public String estado;
    public List<Item> items;

    public static class Item {
        public Long idProducto;
        public Integer cantidad;
        public String nombreProducto;
        public java.math.BigDecimal precioUnitario;
    }
}
