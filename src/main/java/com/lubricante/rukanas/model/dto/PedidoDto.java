package com.lubricante.rukanas.model.dto;

import com.lubricante.rukanas.model.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Date;

public class PedidoDto {
    private Long id;

    private Usuario usuario;

    private Integer estado;
    private LocalDateTime  fechaPedido;

    private Float totalPedido;

    public PedidoDto() {
    }

    public PedidoDto(Long id, Usuario usuario, Integer estado,LocalDateTime  fechaPedido, Float totalPedido) {
        this.id = id;
        this.usuario = usuario;
        this.estado = estado;
        this.fechaPedido= fechaPedido;
        this.totalPedido = totalPedido;
    }

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

    public Float getTotalPedido() {
        return totalPedido;
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
}
