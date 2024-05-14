package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.PedidoDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperPedido;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperProducto;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.model.entities.Venta;
import com.lubricante.rukanas.model.request.PedidoRequest;
import com.lubricante.rukanas.repositories.PedidoRepository;
import com.lubricante.rukanas.repositories.UsuarioRepository;
import com.lubricante.rukanas.repositories.VentaRepository;
import com.lubricante.rukanas.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentaRepository ventaRepository;


    @Override
    public List<PedidoDto> findAllPedidos() {
    List<Pedido> pedido = (List<Pedido>) pedidoRepository.findAll();
    return pedido
            .stream()
            .map(n-> DtoMapperPedido.builder().setPedido(n).build())
            .collect(Collectors.toList());
    }

    @Override
    public Optional<PedidoDto> findByPedido(Long id) {
        return pedidoRepository.findById(id)
                .map(u->DtoMapperPedido.builder().setPedido(u).build());
    }
    @Override
    public List<PedidoDto> findAllPedidoByUsuario(Usuario usuario) {

        List<Pedido> pedido = (List<Pedido>) pedidoRepository.findPedidoByUsuario(usuario);
        return pedido
                .stream()
                .map(n-> DtoMapperPedido.builder().setPedido(n).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoDto> findAllPedidoByUsuarioAndEstado(Usuario usuario, Long estado) {
        List<Pedido> pedido = (List<Pedido>) pedidoRepository.findPedidoByUsuarioAndAndEstado(usuario,estado);
        return pedido
                .stream()
                .map(n-> DtoMapperPedido.builder().setPedido(n).build())
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDto savePedido(PedidoDto pedido) {
        //Optional<Pedido> optionalPedido = pedidoRepository.findById(pedido.getId());

        Optional<Usuario> user = usuarioRepository.findById(pedido.getUsuario());
       // Optional<Venta> venta = ventaRepository.findById(pedido.getVenta());

        Usuario usuario = user.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrada"));
    //    Venta vent = venta.orElseThrow(() -> new IllegalArgumentException("venta no encontrada"));

        Pedido pedidoAux = new Pedido();
        pedidoAux.setUsuario(usuario);
        //pedidoAux.setVenta(vent);
        pedidoAux.setEstado(pedido.getEstado());
        pedidoAux.setFechaPedido(pedido.getFechaPedido());
        pedidoAux.setTotalPedido(pedido.getTotalPedido());

        return DtoMapperPedido.builder().setPedido(pedidoRepository.save(pedidoAux)).build();
    }



    @Override
    public Optional<PedidoDto> updatePedido(PedidoRequest pedido, Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        Optional<Usuario> user = usuarioRepository.findById(pedido.getUsuario());
        Optional<Venta> venta = ventaRepository.findById(pedido.getVenta());

        Usuario usuario = user.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrada"));
        Venta vent = venta.orElseThrow(() -> new IllegalArgumentException("venta no encontrada"));


        Pedido pedidoAux = null;
        if (optionalPedido.isPresent()) {
            Pedido pedidoDB = optionalPedido.orElseThrow();
            pedidoDB.setUsuario(usuario);
            pedidoDB.setVenta(vent);
            pedidoDB.setEstado(pedidoDB.getEstado());
            pedidoDB.setTotalPedido(pedido.getTotalPedido());
            pedidoAux = pedidoRepository.save(pedidoDB);
        }
        return Optional.ofNullable(DtoMapperPedido.builder().setPedido(pedidoAux).build());
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }


}
