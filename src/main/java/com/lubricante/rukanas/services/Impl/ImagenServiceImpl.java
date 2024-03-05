package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.entities.Imagen;
import com.lubricante.rukanas.repositories.ImagenRepository;
import com.lubricante.rukanas.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenServiceImpl  implements ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Override
    public List<Imagen> findAllImagen() {
        return (List<Imagen>) imagenRepository.findAll();
    }

    @Override
    public Imagen saveImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }
}
