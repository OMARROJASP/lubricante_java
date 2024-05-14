package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.VentaDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperVenta;
import com.lubricante.rukanas.model.entities.Venta;
import com.lubricante.rukanas.repositories.VentaRepository;
import com.lubricante.rukanas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<VentaDto> findAllVentas() {
        List<Venta> ventas = (List<Venta>) ventaRepository.findAll();

        return ventas
                .stream()
                .map(n-> DtoMapperVenta.builder().setVenta(n).build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VentaDto> findIdVenta(Long id) {
        return ventaRepository.findById(id)
                .map(u->DtoMapperVenta.builder().setVenta(u).build());
    }

    @Override
    public VentaDto saveVenta(Venta venta) {
        return DtoMapperVenta.builder().setVenta(ventaRepository.save(venta)).build();
    }

    @Override
    public Optional<VentaDto> updateVenta(Venta venta, Long id) {
        Optional<Venta> optionalVenta = ventaRepository.findById(id);
        Venta venta1 =null;
        if(optionalVenta.isPresent()){
            Venta ventaDB = optionalVenta.orElseThrow();
            ventaDB.setPedido( venta.getPedido());
            ventaDB.setMetodoVenta(venta.getMetodoVenta());
            ventaDB.setMontoVenta(venta.getMontoVenta());
            ventaDB.setUsuario(venta.getUsuario());
            ventaDB.setFechaPedido(venta.getFechaPedido());
            venta1 = ventaRepository.save(ventaDB);
        }

        return Optional.ofNullable(DtoMapperVenta.builder().setVenta(venta1).build());
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
