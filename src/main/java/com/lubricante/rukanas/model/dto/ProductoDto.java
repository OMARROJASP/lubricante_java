package com.lubricante.rukanas.model.dto;

import com.lubricante.rukanas.model.entities.Categoria;

public class ProductoDto {

    private Long id;

    private String nombre;

    private String marca;

    private Long cantidad;

    private Float precio;

    private int descuento;

    private String imagen;

    private Long categoria;

    public ProductoDto() {
    }

    public ProductoDto(Long id, String nombre, String marca,Long cantidad, Float precio, int descuento, String imagen, Long categoria) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoriaDto(Long categoria) {
        this.categoria = categoria;
    }
}
