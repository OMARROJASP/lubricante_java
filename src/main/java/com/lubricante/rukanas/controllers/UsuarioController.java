package com.lubricante.rukanas.controllers;

import com.lubricante.rukanas.model.dto.UsuarioDto;
import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.model.request.UsuarioRequest;
import com.lubricante.rukanas.services.UsuarioService;
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
@RequestMapping("/usuarios")
@CrossOrigin(origins = "https://rukanas.netlify.app")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDto> list(){
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<UsuarioDto> userDtoOptional = usuarioService.encontrarUsuario(id);

        if(userDtoOptional.isPresent()){
            return ResponseEntity.ok(userDtoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{identidad}")

    public ResponseEntity<?> encontrarUser (@PathVariable String identidad){
        Optional<UsuarioDto> user = usuarioService.encontrarUsuarioPorNombre(identidad);
        return ResponseEntity.ok(user.orElseThrow());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioRequest user, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<UsuarioDto> o = usuarioService.actualizarUsuario(user, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<UsuarioDto> o = usuarioService.encontrarUsuario(id);

        if(o.isPresent()) {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err-> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
