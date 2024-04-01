package com.lubricante.rukanas.services.Impl;

import com.lubricante.rukanas.model.dto.ProductoDto;
import com.lubricante.rukanas.model.dto.mapper.DtoMapperProducto;
import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.entities.Producto;
import com.lubricante.rukanas.model.request.ProductoRequest;
import com.lubricante.rukanas.repositories.ProductoRespository;
import com.lubricante.rukanas.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRespository productoRespository;



    @Override
    public List<ProductoDto> findAllProducts() {
       List<Producto> producto = (List<Producto>) productoRespository.findAll();
       return producto
               .stream()
               .map(n -> DtoMapperProducto.builder().setProducto(n).build())
               .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoDto> findIdProducto(Long id) {
        return productoRespository.findById(id)
                .map(u-> DtoMapperProducto.builder().setProducto(u).build());
    }


    @Override
    public ProductoDto saveProduct(Producto producto) {
     return DtoMapperProducto.builder().setProducto(productoRespository.save(producto)).build();
    }

    @Override
    public Optional<ProductoDto> updateProducto(ProductoRequest producto, Long id) {
       Optional<Producto> productoOptional = productoRespository.findById(id);
       Producto productoAux = null;
       if(productoOptional.isPresent()){
           Producto productoDB = productoOptional.orElseThrow();
           productoDB.setNombre(producto.getNombre());
           productoDB.setMarca(producto.getMarca());
           productoDB.setCantidad(producto.getCantidad());
           productoDB.setImagen(producto.getImagen());
           productoDB.setDescuento(producto.getDescuento());
           productoDB.setCategoria(productoDB.getCategoria());
           productoAux = productoRespository.save(productoDB);
       }
       return Optional.ofNullable(DtoMapperProducto.builder().setProducto(productoAux).build());
    }

    @Override
    public List<Producto> allProductByCategory(Categoria categoria) {
        return productoRespository.findProductoByCategoria(categoria);
    }

    @Override
    public Optional findById(Long id) {
      return productoRespository.findById(id).map(u->DtoMapperProducto
              .builder()
              .setProducto(u)
              .build());
    }

    @Override
    public void EliminarProducto(Long id) {
        productoRespository.deleteById(id);
    }

    @Override
    public List<Producto> allProductByDescuento() {

        return productoRespository.findProductoByDescuento();
    }


}
