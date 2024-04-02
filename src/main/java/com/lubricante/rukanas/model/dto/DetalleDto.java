package com.lubricante.rukanas.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Producto;


public class DetalleDto {

    private Long id;
    private Long pedido;
    private Long producto;
    private Long cantidad;
    private Float precioUnitario;
    private Float subTotal;


    public DetalleDto(Long id, Long pedido, Long producto, Long cantidad, Float precioUnitario, Float subTotal) {
        this.id = id;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public DetalleDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Long getProducto() {
        return producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }
}