package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperDetalle;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperProducto;
import com.lubricante.rukanas.model.entities.Detalles;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Producto;
import com.lubricante.rukanas.model.request.DetalleRequest;
import com.lubricante.rukanas.repositories.DetalleRepository;
import com.lubricante.rukanas.repositories.PedidoRepository;
import com.lubricante.rukanas.services.DetallesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetallesServiceImpl implements DetallesService {

    @Autowired
    private DetalleRepository detalleRepository;
    @Autowired
    private PedidoRepository pedidoRepository;


    @Override
        public List<DetalleDto> findAllDetalle() {

        List<Detalles> detalles = (List<Detalles>) detalleRepository.findAll();
        return detalles
                .stream()
                .map(n -> DtoMapperDetalle.builder().setDetalle(n).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleDto> findAllDetallesByPedido(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        Pedido pedidoAux = null;
        if(pedido.isPresent()){
            pedidoAux = pedido.get();
        }

        List<Detalles> detalles = (List<Detalles>)detalleRepository.findDetallesByPedido(pedidoAux);
        return detalles
                .stream()
                .map(n -> DtoMapperDetalle.builder().setDetalle(n).build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DetalleDto> findByIdDetalle(Long id) {
        return detalleRepository.findById(id)
                .map(u-> DtoMapperDetalle.builder().setDetalle(u).build());

    }

    @Override
    public DetalleDto saveDetalles(Detalles detalles) {
        return DtoMapperDetalle.builder().setDetalle(detalleRepository.save(detalles)).build();
    }

    @Override
    public Optional<DetalleDto> updateDetalle(DetalleRequest detalles, Long id) {
        Optional<Detalles> detallesOptional = detalleRepository.findById(id);
        Detalles detalles1 = null;

        if (detallesOptional.isPresent()){
            Detalles detallesDB = detallesOptional.orElseThrow();
            detallesDB.setProducto(detalles.getProducto());
            detallesDB.setPedido(detalles.getPedido());
            detallesDB.setCantidad(detalles.getCantidad());
            detallesDB.setPrecioUnitario(detalles.getPrecioUnitario());
            detallesDB.setSubTotal(detalles.getSubTotal());
            detalles1 = detalleRepository.save(detallesDB);
        }
        return Optional.ofNullable(DtoMapperDetalle.builder().setDetalle(detalles1).build());

    }

    @Override
    public void deleteDetalles(Long id) {
        detalleRepository.deleteById(id);
    }




    /*
        public List<DetalleDto> findAllDetalle() {
        List<Detalles> detalles = (List<Detalles>)detalleRepository.findAll();
        return detalles.stream()
                .map(detalle -> DtoMapperDetalle.builder().setDetalle(detalle).build())
                .collect(Collectors.toList());
    }
     */
}
