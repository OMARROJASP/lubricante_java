package com.lubricante.rukanas.model.dto.mapper;

import com.lubricante.rukanas.model.dto.DetalleDto;
import com.lubricante.rukanas.model.entities.Detalle;

public class DtoMapperDetalle {
    private Detalle detalle;


    public DtoMapperDetalle(){}

    public static DtoMapperDetalle builder(){
        return new DtoMapperDetalle();
    }

    public DtoMapperDetalle setDetalle(Detalle detalle){
        this.detalle = detalle;
        return this;
    }

    public DetalleDto build(){
        if(detalle == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY PEDIDO");
        }
        return new DetalleDto(this.
                detalle.getId(),
                detalle.getPedido(),
                detalle.getProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubTotal()
        );
    }

}
