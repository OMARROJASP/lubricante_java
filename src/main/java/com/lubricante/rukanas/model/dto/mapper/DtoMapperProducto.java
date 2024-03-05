package com.lubricante.rukanas.model.dto.mapper;

import com.lubricante.rukanas.model.dto.ProductoDto;
import com.lubricante.rukanas.model.entities.Producto;

public class DtoMapperProducto {

    private Producto producto;

    public  DtoMapperProducto(){

    }

    public static DtoMapperProducto builder(){
        return new DtoMapperProducto();
    }

    public DtoMapperProducto setProducto(Producto producto){
        this.producto = producto;
        return this;
    }

    public ProductoDto build(){
        if(producto == null){
            throw new RuntimeException("DEBE PASAR EL ENTITY PRODUCTO");
        }
        return new ProductoDto(this.
                producto.getId(),
                producto.getNombre(),
                producto.getMarca(),
                producto.getCantidad(),
                producto.getPrecio(),
                producto.getDescuento(),
                producto.getImagen(),
                producto.getCategoria()
                );
    }

}
