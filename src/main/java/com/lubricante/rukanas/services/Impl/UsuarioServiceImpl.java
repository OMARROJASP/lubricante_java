package com.lubricante.rukanas.services.Impl;


import com.lubricante.rukanas.model.dto.UsuarioDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperUsuario;
import com.lubricante.rukanas.model.entities.Role;
import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.model.request.UsuarioRequest;
import com.lubricante.rukanas.repositories.RoleRepository;
import com.lubricante.rukanas.repositories.UsuarioRepository;
import com.lubricante.rukanas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        List<Usuario>  user = (List<Usuario>) usuarioRepository.findAll();
        return user
                .stream()
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public Optional<UsuarioDto> encontrarUsuario(Long id) {

        return usuarioRepository.findById(id)
                .map(u-> DtoMapperUsuario
                        .builder()
                        .setUsuario(u)
                        .build());
    }

    @Override
    public UsuarioDto guardarUsuario(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        Optional<Role> o = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        if(o.isPresent()){
            roles.add(o.orElseThrow());
        }

        usuario.setRoles(roles);
        return DtoMapperUsuario.builder().setUsuario(usuarioRepository.save(usuario)).build();
    }

    @Override
    public Optional<UsuarioDto> actualizarUsuario(UsuarioRequest user, Long id) {
        Optional<Usuario> o = usuarioRepository.findById(id);

        Usuario usuarioOptional = null;
        if(o.isPresent()){
            Usuario usuarioDb= o.orElseThrow();
            usuarioDb.setNombre(user.getNombre());
            usuarioDb.setCorreo(user.getCorreo());
            usuarioOptional = usuarioRepository.save(usuarioDb);
        }
        return Optional.ofNullable(DtoMapperUsuario.builder().setUsuario(usuarioOptional).build());
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<UsuarioDto> encontrarUsuarioPorNombre(String username) {
        Optional<Usuario> user = usuarioRepository.findByNombre(username);

        if (user.isPresent()){
            return usuarioRepository.findById(user.get().getId())
                    .map(u-> DtoMapperUsuario
                            .builder()
                            .setUsuario(u)
                            .build());
        }
        return Optional.empty();
    }
}