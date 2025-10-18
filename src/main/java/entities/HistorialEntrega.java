package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "historial_entregas")
public class HistorialEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_entrega;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    private LocalDate fecha_entrega;
    private String estado_entrega;

    // Getters y Setters
    public Long getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(Long id_entrega) {
        this.id_entrega = id_entrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDate getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(LocalDate fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getEstado_entrega() {
        return estado_entrega;
    }

    public void setEstado_entrega(String estado_entrega) {
        this.estado_entrega = estado_entrega;
    }
}
