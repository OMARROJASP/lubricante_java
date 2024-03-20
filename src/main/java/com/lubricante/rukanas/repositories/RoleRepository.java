package com.lubricante.rukanas.repositories;

import com.lubricante.rukanas.model.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository  extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
