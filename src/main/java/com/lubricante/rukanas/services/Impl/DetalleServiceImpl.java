package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperCategoria;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperDetalle;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperPedido;
import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.entities.Detalle;
import com.lubricante.rukanas.model.request.DetalleRequest;
import com.lubricante.rukanas.repositories.DetalleRepository;
import com.lubricante.rukanas.services.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleServiceImpl implements DetalleService {


    @Autowired
    private DetalleRepository detalleRepository;

    @Override
    public List<DetalleDto> findAllDetalle() {
        try {
            List<Detalle> detalles = (List<Detalle>)detalleRepository.findAll();
            return detalles.stream()
                    .map(detalle -> DtoMapperDetalle.builder().setDetalle(detalle).build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Manejo de excepciones aquí
            e.printStackTrace();
            return Collections.emptyList(); // Devuelve una lista vacía si hay un error
        }
    }

    @Override
    public Optional<DetalleDto> findByIdDetalle(Long id) {
        return detalleRepository.findById(id)
                .map(u -> DtoMapperDetalle.builder().setDetalle(u).build());
    }

    @Override
    public DetalleDto saveDetalle(Detalle detalle) {
        return DtoMapperDetalle.builder().setDetalle(detalleRepository.save(detalle)).build();
    }

    @Override
    public Optional<DetalleDto> updateDetalle(DetalleRequest detalle, Long id) {
        Optional<Detalle> detalleOptional = detalleRepository.findById(id);
      Detalle  detalleAux= null;
        Optional<Detalle> detalleOptional1 = null;
        if (detalleOptional.isPresent()){
            Detalle detalleDB = detalleOptional.orElseThrow();
            detalleDB.setCantidad(detalle.getCantidad());
            detalleDB.setPedido(detalle.getPedido());
            detalleDB.setProducto(detalle.getProducto());
            detalleDB.setPrecioUnitario(detalle.getPrecioUnitario());
            detalleDB.setSubTotal(detalle.getSubTotal());
            detalleAux= detalleRepository.save(detalleDB);
        }
       return  Optional.ofNullable(DtoMapperDetalle.builder().setDetalle(detalleAux).build());
    }

    @Override
    public void deleteDetalle(Long id) {
        detalleRepository.deleteById(id);
    }
}
