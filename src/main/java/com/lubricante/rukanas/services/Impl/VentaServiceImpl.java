package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.VentaDto;
import com.lubricante.rukanas.model.entities.Venta;
import com.lubricante.rukanas.repositories.VentaRepository;
import com.lubricante.rukanas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> findAllVentas() {
        return null;
    }

    @Override
    public Optional<VentaDto> findIdVenta(Long id) {
        return Optional.empty();
    }

    @Override
    public VentaDto saveVenta(Venta venta) {
        return null;
    }

    @Override
    public Optional<VentaDto> updateVenta(Venta venta, Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteVenta(Long id) {

    }
}
