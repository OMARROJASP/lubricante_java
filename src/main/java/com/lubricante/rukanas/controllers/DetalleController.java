package com.lubricante.rukanas.controllers;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.entities.Detalles;
import com.lubricante.rukanas.model.request.DetalleRequest;
import com.lubricante.rukanas.services.DetallesService;
import jakarta.validation.Valid;
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
@RequestMapping("/detalle")
@CrossOrigin(origins = "https://rukanas.netlify.app")

public class DetalleController {

    @Autowired
    private DetallesService detalleService;

    @GetMapping
    private List<DetalleDto> obtenerDetalles(){
        return detalleService.findAllDetalle();
    }

    @GetMapping("/pedido/{pedidoId}")
    private List<DetalleDto> obtenerDetallesPorPedido(@PathVariable long pedidoId){
        return detalleService.findAllDetallesByPedido(pedidoId);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> obtenerDetalle(@PathVariable ("id") Long id){
        Optional<DetalleDto> detallesOptional= detalleService.findByIdDetalle(id);
        if(detallesOptional.isPresent()){
            return ResponseEntity.ok(detallesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ventas/{nombre}")
    private List<DetalleDto> obtenerDetallePorEstado(@PathVariable ("nombre") String nombre) {
            return detalleService.findAllDetallesByVenta(nombre);
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarDetalles(@Valid @RequestBody DetalleDto detalles, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleService.saveDetalles(detalles));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarDetalles(@Valid @RequestBody DetalleRequest detalles, BindingResult result, @PathVariable("id") Long id){
        if (result.hasErrors()){
            return validation(result);
        }
        Optional<DetalleDto> detallesOptional = detalleService.updateDetalle(detalles, id);
        if(detallesOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(detallesOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDetalles(@PathVariable("id") Long id) {
        Optional<DetalleDto> o = detalleService.findByIdDetalle(id);
        if (o.isPresent()) {
            detalleService.deleteDetalles(id);
            return ResponseEntity.ok().build(); // Cambio aquí
        }
        return ResponseEntity.notFound().build();
    }
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
