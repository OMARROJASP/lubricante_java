package com.lubricante.rukanas.controllers;

import com.lubricante.rukanas.model.dto.PedidoDto;
import com.lubricante.rukanas.model.dto.VentaDto;
import com.lubricante.rukanas.model.entities.Venta;
import com.lubricante.rukanas.services.VentaService;
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
@RequestMapping("/ventas")
@CrossOrigin(originPatterns = "*")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaDto> obtenerVentas(){
        return ventaService.findAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Long id){
        Optional<VentaDto> ventaDtoOptional= ventaService.findIdVenta(id);
        if(ventaDtoOptional.isPresent()){
            return ResponseEntity.ok(ventaDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearVenta(@Valid @RequestBody Venta venta, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ventaService.saveVenta(venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id){
        Optional<VentaDto> ventaDtoOptional = ventaService.findIdVenta(id);
        if(ventaDtoOptional.isPresent()){
            ventaService.deleteVenta(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
