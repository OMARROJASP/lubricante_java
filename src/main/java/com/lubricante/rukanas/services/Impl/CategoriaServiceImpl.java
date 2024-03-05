package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.CategoriaDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperCategoria;
import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.request.CategoriaRequest;
import com.lubricante.rukanas.repositories.CategoriaRepository;
import com.lubricante.rukanas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public List<CategoriaDto> findAllCategory() {
        List<Categoria> categoria = (List<Categoria>) categoriaRepository.findAll();
        return categoria
                .stream()
                .map(m-> DtoMapperCategoria.builder().setCategoria(m).build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriaDto> findIdCategoria(Long id) {
       return categoriaRepository.findById(id)
               .map(u -> DtoMapperCategoria.builder().setCategoria(u).build());
    }


    @Override
    public CategoriaDto saveCategory(Categoria categoria) {
        return DtoMapperCategoria.builder().setCategoria(categoriaRepository.save(categoria)).build();
    }

    @Override
    public Optional<CategoriaDto> updateCategoria(CategoriaRequest categoria, Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        Categoria categoriaAux = null;
        if(categoriaOptional.isPresent()){
            Categoria categoriaDB = categoriaOptional.orElseThrow();
            categoriaDB.setNombre(categoria.getNombre());
            categoriaDB.setImagen(categoria.getImagen());
            categoriaAux = categoriaRepository.save(categoriaDB);
        }
        return Optional.ofNullable(DtoMapperCategoria.builder().setCategoria(categoriaAux).build());
    }

    @Override
    public void EliminarCategoria(Long id) {
       categoriaRepository.deleteById(id);
    }


}
