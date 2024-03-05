package com.lubricante.rukanas.model.dto.mapper;

import com.lubricante.rukanas.model.dto.CategoriaDto;
import com.lubricante.rukanas.model.entities.Categoria;

public class DtoMapperCategoria {

    private Categoria categoria;

    public DtoMapperCategoria(){

    }

    public static DtoMapperCategoria builder(){
        return new DtoMapperCategoria();
    }

    public DtoMapperCategoria setCategoria(Categoria categoria){
        this.categoria = categoria;
        return this;
    }

    public CategoriaDto build(){
        if(categoria == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY CATEGORIA");
        }
        return new CategoriaDto(this.categoria.getId(),categoria.getNombre(), categoria.getImagen());
    }


}
