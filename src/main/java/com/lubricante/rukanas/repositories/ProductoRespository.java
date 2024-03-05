package com.lubricante.rukanas.repositories;

import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.entities.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRespository extends CrudRepository<Producto, Long> {

    List<Producto> findProductoByCategoria(Categoria categoria);
}
