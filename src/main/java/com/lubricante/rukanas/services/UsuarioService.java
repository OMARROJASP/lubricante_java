package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.dto.UsuarioDto;
import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.model.request.UsuarioRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDto> obtenerUsuarios() ;
    Optional<UsuarioDto> encontrarUsuario(Long id);

    UsuarioDto guardarUsuario(Usuario usuario);

    Optional<UsuarioDto> actualizarUsuario(UsuarioRequest user, Long id);

    void eliminarUsuario(Long id);

    Optional<UsuarioDto> encontrarUsuarioPorNombre(String username);
}
