package com.lubricante.rukanas.model.request;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    private String nombre;

    private String imagen;

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
