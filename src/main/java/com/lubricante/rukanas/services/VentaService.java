package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.dto.VentaDto;
import com.lubricante.rukanas.model.entities.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {

    List<Venta> findAllVentas();

    Optional<VentaDto> findIdVenta(Long id);

    VentaDto saveVenta(Venta venta);

    Optional<VentaDto> updateVenta(Venta venta, Long id);

    void deleteVenta(Long id);
}
