package com.lubricante.rukanas.controllers;

import com.lubricante.rukanas.model.dto.CategoriaDto;
import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.request.CategoriaRequest;
import com.lubricante.rukanas.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(originPatterns = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDto> obtenerCategorias(){
        return categoriaService.findAllCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> traerCategoria(@PathVariable("id") Long id){
        Optional<CategoriaDto> categoriaDtoOptional = categoriaService.findIdCategoria(id);

        if(categoriaDtoOptional.isPresent()){
            return ResponseEntity.ok(categoriaDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCategoria(
            @RequestBody Categoria categoria,
            BindingResult result            ){

        if(result.hasErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaService.saveCategory(categoria));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCategoria(@Valid @RequestBody CategoriaRequest categoria,
                                                 BindingResult result, @PathVariable("id") Long id){
        if(result.hasErrors()){
            return validation(result);
        }

        Optional<CategoriaDto>categoriaDto = categoriaService.updateCategoria(categoria, id);
        if(categoriaDto.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable("id") Long id){
        Optional<CategoriaDto> o = categoriaService.findIdCategoria(id);
        if(o.isPresent()){
            categoriaService.EliminarCategoria(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return  ResponseEntity.badRequest().body(errors);
    }

}