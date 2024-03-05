package com.lubricante.rukanas.controllers;

import com.lubricante.rukanas.model.entities.Imagen;
import com.lubricante.rukanas.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/imagen")
@CrossOrigin(originPatterns = "*")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping()
    public List<Imagen> traerImagen (){
        return imagenService.findAllImagen();
    }





}
