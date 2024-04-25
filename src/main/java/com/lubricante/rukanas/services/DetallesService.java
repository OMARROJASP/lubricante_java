package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.entities.Detalles;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.model.request.DetalleRequest;

import java.util.List;
import java.util.Optional;

public interface DetallesService {
    List<DetalleDto> findAllDetalle();

    List<DetalleDto> findAllDetallesByPedido(Long pedido);

    List<DetalleDto> findAllDetallesByVenta(String usuario);

    Optional<DetalleDto> findByIdDetalle(Long id);

    DetalleDto saveDetalles(DetalleDto detalles);

    Optional<DetalleDto> updateDetalle(DetalleRequest detalles, Long id);

    void deleteDetalles(Long id);




}
