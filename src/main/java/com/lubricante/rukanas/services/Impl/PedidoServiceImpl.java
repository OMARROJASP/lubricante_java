package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.PedidoDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperPedido;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperProducto;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Usuario;
import com.lubricante.rukanas.model.request.PedidoRequest;
import com.lubricante.rukanas.repositories.PedidoRepository;
import com.lubricante.rukanas.repositories.UsuarioRepository;
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
    public PedidoDto savePedido(Pedido pedido) {
        return DtoMapperPedido.builder().setPedido(pedidoRepository.save(pedido)).build();
    }



    @Override
    public Optional<PedidoDto> updatePedido(PedidoRequest pedido, Long id) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        Pedido pedidoAux = null;
        if (optionalPedido.isPresent()) {
            Pedido pedidoDB = optionalPedido.orElseThrow();
            pedidoDB.setUsuario(pedido.getUsuario());
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
