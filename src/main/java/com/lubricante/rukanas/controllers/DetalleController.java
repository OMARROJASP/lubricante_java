package com.lubricante.rukanas.controllers;


import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.dto.ProductoDto;
import com.lubricante.rukanas.model.entities.Detalle;

import com.lubricante.rukanas.services.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/detalles")
@CrossOrigin(originPatterns = "*")
public class DetalleController {

    @Autowired
    private DetalleService detalleService;

    @GetMapping
    public List<DetalleDto> obtenerDetalles(){
        return detalleService.findAllDetalle();
    }


    @PostMapping
    public ResponseEntity<?> guardarCategoria(
            @RequestBody Detalle detalle,
            BindingResult result            ){

        if(result.hasErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(detalleService.saveDetalle(detalle));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDetalle(@PathVariable("id") Long id){
        Optional<DetalleDto> detalleDtoOptional = detalleService.findByIdDetalle(id);
        if(detalleDtoOptional.isPresent()){
            return ResponseEntity.ok(detalleDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return  ResponseEntity.badRequest().body(errors);
    }
}
