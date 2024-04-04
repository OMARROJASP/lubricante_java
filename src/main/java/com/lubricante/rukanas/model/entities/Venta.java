package com.lubricante.rukanas.model.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@EntityListeners(AuditingEntityListener.class)
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "venta")
    private List<Pedido> pedido;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @PrePersist
    private void onCreate(){
        fechaPedido = LocalDateTime.now();
    }
    @Column(name = "monto_venta")
    private Float montoVenta;
    @Column(name = "metodo_venta")
    private String metodoVenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Float getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(Float montoVenta) {
        this.montoVenta = montoVenta;
    }

    public String getMetodoVenta() {
        return metodoVenta;
    }

    public void setMetodoVenta(String metodoVenta) {
        this.metodoVenta = metodoVenta;
    }
}
