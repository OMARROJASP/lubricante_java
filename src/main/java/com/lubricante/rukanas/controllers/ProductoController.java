package com.lubricante.rukanas.controllers;


import com.lubricante.rukanas.model.dto.CategoriaDto;
import com.lubricante.rukanas.model.dto.ProductoDto;
import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.entities.Producto;
import com.lubricante.rukanas.services.CategoriaService;
import com.lubricante.rukanas.services.ProductoService;
import jakarta.validation.Valid;
import com.lubricante.rukanas.model.request.ProductoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "https://rukanas.netlify.app")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<ProductoDto> obtenerProductos() {
        return productoService.findAllProducts();
    }

    @PostMapping( "/guardar")
    public ResponseEntity<?> guardarProducto(
            @Valid @RequestBody ProductoDto producto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.saveProduct(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable("id") Long id){
        Optional<ProductoDto> productoDtoOptional = productoService.findIdProducto(id);
        if(productoDtoOptional.isPresent()){
            return ResponseEntity.ok(productoDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista/{id}")
    public List<Producto> listaProductosByCategoria(@PathVariable Long id){
        Optional<CategoriaDto> categoriaDtoOptional = categoriaService.findIdCategoria(id);

        if (categoriaDtoOptional.isPresent()){
            CategoriaDto categoriaDto = categoriaDtoOptional.get();
            Categoria categoria = new Categoria();
            categoria.setId(categoriaDto.getId());
            categoria.setNombre(categoriaDto.getNombre());
            categoria.setImagen(categoriaDto.getImagen());
            return productoService.allProductByCategory(categoria);

        }else{
            return Collections.emptyList();
        }

    }

    @GetMapping("/descuento")
    public List<Producto> listaProductoPorDescuento(){
        return productoService.allProductByDescuento();
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> actualizarProducto(@Valid @RequestBody ProductoRequest producto,
                                                BindingResult result, @PathVariable("id") Long id){
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<ProductoDto> productoDto = productoService.updateProducto(producto, id);
        if(productoDto.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productoDto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("id") Long id){
        Optional<ProductoDto> o = productoService.findIdProducto(id);
        if(o.isPresent()){
            productoService.EliminarProducto(id);
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
