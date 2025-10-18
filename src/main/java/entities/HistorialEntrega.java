package entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_entregas")
public class HistorialEntrega {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_entrega;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    private LocalDateTime fecha_entrega;
    private String estado_entrega;

    // getters y setters
    public Long getId_entrega() { return id_entrega; }
    public void setId_entrega(Long id_entrega) { this.id_entrega = id_entrega; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public LocalDateTime getFecha_entrega() { return fecha_entrega; }
    public void setFecha_entrega(LocalDateTime fecha_entrega) { this.fecha_entrega = fecha_entrega; }
    public String getEstado_entrega() { return estado_entrega; }
    public void setEstado_entrega(String estado_entrega) { this.estado_entrega = estado_entrega; }
}

