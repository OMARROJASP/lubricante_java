package com.lubricante.rukanas.repositories;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.entities.Detalles;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DetalleRepository extends CrudRepository<Detalles, Long> {

  List<Detalles> findDetallesByPedido(Pedido pedido);

  List<DetalleDto> findDetallesByProducto(Producto producto);
}
