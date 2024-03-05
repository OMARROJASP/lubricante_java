package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.dto.CategoriaDto;
import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.request.CategoriaRequest;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<CategoriaDto> findAllCategory();

    Optional<CategoriaDto> findIdCategoria(Long id);

    CategoriaDto saveCategory(Categoria categoria);

    Optional<CategoriaDto> updateCategoria(CategoriaRequest categoria, Long id);

    void EliminarCategoria(Long id);

}
