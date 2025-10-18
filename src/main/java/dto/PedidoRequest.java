package dto;

import java.util.List;
import java.math.BigDecimal;

public class PedidoRequest {
    public Long idCliente;
    public String estado; 
    public List<Item> items;

    public static class Item {
        public Long idProducto;
        public Integer cantidad;
        public BigDecimal precioUnitario;
    }
}
