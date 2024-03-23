package com.lubricante.rukanas.model.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@EntityListeners(AuditingEntityListener.class)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Integer estado;


    @Column(name = "fecha_pedido")
    private LocalDateTime  fechaPedido;

    @PrePersist
    private void onCreate(){
        fechaPedido = LocalDateTime.now();
    }

    @Column(name = "total_pedido")
    private Float totalPedido;

    @OneToMany(mappedBy = "pedido")
    private List<Detalle> detalles;

    // Getters y Setters



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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public LocalDateTime  getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime  fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setTotalPedido(Float totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Float getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(float totalPedido) {
        this.totalPedido = totalPedido;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }
}

