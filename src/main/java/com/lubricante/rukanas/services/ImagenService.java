package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.entities.Imagen;

import java.util.List;

public interface ImagenService {

    List<Imagen> findAllImagen();

    Imagen saveImagen(Imagen imagen);
}
