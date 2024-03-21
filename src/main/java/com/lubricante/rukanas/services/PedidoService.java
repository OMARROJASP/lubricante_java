package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.dto.PedidoDto;
import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.request.PedidoRequest;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<PedidoDto> findAllPedidos();
    Optional<PedidoDto> findByPedido(Long id);
    PedidoDto savePedido(Pedido pedido);
    Optional<PedidoDto> updatePedido(PedidoRequest pedido, Long id);
    void deletePedido(Long id);
}
