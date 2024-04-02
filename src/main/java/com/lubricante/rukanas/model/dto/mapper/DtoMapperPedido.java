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
        return new PedidoDto(this.
                pedido.getId(),
                pedido.getUsuario().getId(),
                pedido.getEstado(),
                pedido.getFechaPedido(),
                pedido.getTotalPedido()
                );
    }



}
