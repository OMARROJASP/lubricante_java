package com.lubricante.rukanas.model.dto;

import com.lubricante.rukanas.model.entities.Producto;

import java.util.List;

public class CategoriaDto {

    private Long id;

    private String nombre;

    private String imagen;

    private List<Producto> productos;



    public CategoriaDto(Long id, String nombre, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public CategoriaDto(Long id, String nombre, String imagen, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.productos = productos;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
