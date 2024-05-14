package com.lubricante.rukanas.model.dto;

import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class VentaDto {
    private Long id;

    private Long usuario;
    private LocalDateTime fechaPedido;
    private Float montoVenta;
    private String metodoVenta;

    public VentaDto(){}
    public VentaDto(Long id, Long usuario, LocalDateTime fechaPedido, Float montoVenta, String metodoVenta) {
        this.id = id;
        this.usuario = usuario;
        this.fechaPedido = fechaPedido;
        this.montoVenta = montoVenta;
        this.metodoVenta = metodoVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
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
        metodoVenta = metodoVenta;
    }
}
