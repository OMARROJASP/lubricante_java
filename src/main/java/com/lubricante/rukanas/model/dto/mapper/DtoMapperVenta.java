package com.lubricante.rukanas.model.dto.mapper;

import com.lubricante.rukanas.model.dto.VentaDto;
import com.lubricante.rukanas.model.entities.Venta;

public class DtoMapperVenta {

    private Venta venta;

    public DtoMapperVenta(){}

    public static DtoMapperVenta builder(){
        return new DtoMapperVenta();
    }

    public DtoMapperVenta setVenta(Venta venta){
        this.venta = venta;
        return this;
    }

    public VentaDto build(){
        if(venta == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY VENTA");
        }
        Long usuarioId = venta.getUsuario() != null ? venta.getUsuario().getId() : null;
        return new VentaDto(this.
                venta.getId(),
                usuarioId,
                venta.getFechaPedido(),
                venta.getMontoVenta(),
                venta.getMetodoVenta()
                );
    }

}
