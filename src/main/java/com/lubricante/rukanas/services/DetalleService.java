package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.entities.Detalle;
import com.lubricante.rukanas.model.request.DetalleRequest;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface DetalleService {

    List<DetalleDto> findAllDetalle();
    Optional<DetalleDto> findByIdDetalle(Long id);
    DetalleDto saveDetalle (Detalle detalle);
    Optional<DetalleDto> updateDetalle(DetalleRequest detalle, Long id);
    void  deleteDetalle(Long id);
}
