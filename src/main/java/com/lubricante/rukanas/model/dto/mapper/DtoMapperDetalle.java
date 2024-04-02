package com.lubricante.rukanas.model.dto.mapper;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.entities.Detalles;

public class DtoMapperDetalle {

    private Detalles detalle;

    public DtoMapperDetalle(){}

    public static DtoMapperDetalle builder(){
        return new DtoMapperDetalle();
    }

    public DtoMapperDetalle setDetalle(Detalles detalles){
        this.detalle = detalles;
        return this;
    }

    public DetalleDto build(){
        if(detalle == null ){
            throw new RuntimeException("DEBE PASAR EL ENTITY DETALLES");
        }
        Long pedidoId = detalle.getPedido() != null ? detalle.getPedido().getId() : null;
        Long productoId = detalle.getProducto() != null ? detalle.getProducto().getId() : null;
        return new DetalleDto(this.
                detalle.getId(),
                pedidoId,
                productoId,
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubTotal()
        );
    }
}
