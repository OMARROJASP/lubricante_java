package com.lubricante.rukanas.repositories;

import com.lubricante.rukanas.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {


        Optional<Usuario> findByNombre(String nombre);


        }