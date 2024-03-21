package com.lubricante.rukanas.controllers;

import com.lubricante.rukanas.model.dto.PedidoDto;
import com.lubricante.rukanas.model.dto.ProductoDto;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.request.PedidoRequest;
import com.lubricante.rukanas.services.PedidoService;
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
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
   public List<PedidoDto> obtenerPedidos(){
        return pedidoService.findAllPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable Long id) {
        Optional<PedidoDto> pedidoDtoOptional = pedidoService.findByPedido(id);
        if(pedidoDtoOptional.isPresent()){
            return ResponseEntity.ok(pedidoDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createPedido(@Valid @RequestBody Pedido pedido,
                                          BindingResult result
    ) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.savePedido(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@Valid @RequestBody PedidoRequest pedido,
                                          BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()){
            return validation(result);
        }

        Optional<PedidoDto> pedidoActualizado = pedidoService.updatePedido(pedido, id);
        if(pedidoActualizado.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoActualizado.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        Optional<PedidoDto> o = pedidoService.findByPedido(id);
        if(o.isPresent()){
            pedidoService.deletePedido(id);
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
