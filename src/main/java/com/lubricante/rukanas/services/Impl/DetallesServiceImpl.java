package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.dto.PedidoDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperDetalle;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperProducto;
import com.lubricante.rukanas.model.entities.Detalles;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Producto;
import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.model.request.DetalleRequest;
import com.lubricante.rukanas.repositories.DetalleRepository;
import com.lubricante.rukanas.repositories.PedidoRepository;
import com.lubricante.rukanas.repositories.ProductoRespository;
import com.lubricante.rukanas.repositories.UsuarioRepository;
import com.lubricante.rukanas.services.DetallesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetallesServiceImpl implements DetallesService {

    @Autowired
    private DetalleRepository detalleRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRespository productoRespository;

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
    public List<DetalleDto> findAllDetallesByVenta(String nombre) {

        Optional<Usuario> userOptional = usuarioRepository.findByNombre(nombre);
        Usuario usuario = userOptional.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        List<Pedido> pedidoDtos = pedidoRepository.encontrarPedidoPorUsuarioYEstado(usuario.getId());
        List<Detalles> detalles = new ArrayList<>();
        for(Pedido pedido: pedidoDtos){
            List<Detalles> detallesPedidos = detalleRepository.findDetallesByPedido(pedido);

            detalles.addAll(detallesPedidos);
        }
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
    public DetalleDto saveDetalles(DetalleDto detalles) {
        Optional<Producto> productoOptional = productoRespository.findById(detalles.getProducto());
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(detalles.getPedido());

        Producto producto = productoOptional.orElseThrow();
        Pedido pedido = pedidoOptional.orElseThrow();

        Detalles detalleAux = new Detalles();
        detalleAux.setProducto(producto);
        detalleAux.setPedido(pedido);
        detalleAux.setSubTotal(detalles.getSubTotal());
        detalleAux.setCantidad(detalles.getCantidad());
        detalleAux.setPrecioUnitario(detalles.getPrecioUnitario());
        return DtoMapperDetalle.builder().setDetalle(detalleRepository.save(detalleAux)).build();
    }

    @Override
    public Optional<DetalleDto> updateDetalle(DetalleRequest detalles, Long id) {
        Optional<Detalles> detallesOptional = detalleRepository.findById(id);

        Optional<Producto> productoOptional = productoRespository.findById(detalles.getProducto());
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(detalles.getPedido());

        Producto producto = productoOptional.orElseThrow();
        Pedido pedido = pedidoOptional.orElseThrow();
        Detalles detalles1 = null;

        if (detallesOptional.isPresent()){
            Detalles detallesDB = detallesOptional.orElseThrow();
            detallesDB.setProducto(producto);
            detallesDB.setPedido(pedido);
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
