package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Optional<Usuario> o = repository.findByNombre(username);

      if(!o.isPresent()){
          throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema!", username));
      }
      Usuario usuario = o.orElseThrow();
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
        return new User(
                usuario.getNombre(),
                usuario.getContrasena(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
