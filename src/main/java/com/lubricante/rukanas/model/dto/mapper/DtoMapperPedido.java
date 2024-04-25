package com.lubricante.rukanas.model.dto.mapper;

import com.lubricante.rukanas.model.dto.PedidoDto;
import com.lubricante.rukanas.model.entities.Pedido;
public class DtoMapperPedido {
    private Pedido pedido;

    public DtoMapperPedido(){}

    public static DtoMapperPedido builder(){
        return new DtoMapperPedido();
    }

    public DtoMapperPedido setPedido(Pedido pedido){
        this.pedido = pedido;
        return this;
    }

    public PedidoDto build(){
        if(pedido == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY PEDIDO");
        }
        Long usuarioId = pedido.getUsuario() != null ? pedido.getUsuario().getId() : null;
        Long ventaId = pedido.getVenta() != null ? pedido.getVenta().getId() : null;

        return new PedidoDto(
                pedido.getId(),
                usuarioId,
                ventaId,
                pedido.getEstado(),
                pedido.getFechaPedido(),
                pedido.getTotalPedido()
        );
    }
}
