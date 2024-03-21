package com.lubricante.rukanas.model.request;

import com.lubricante.rukanas.model.entities.Usuario;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.Date;

public class PedidoRequest {

    private Long id;
    private Integer estado;
    private LocalDateTime  fechaPedido;
    private Usuario usuario;
    private Float totalPedido;

    public LocalDateTime  getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime  fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Float getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Float totalPedido) {
        this.totalPedido = totalPedido;
    }
}
