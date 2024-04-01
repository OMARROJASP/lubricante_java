package com.lubricante.rukanas.services;

import com.lubricante.rukanas.model.dto.ProductoDto;
import com.lubricante.rukanas.model.entities.Categoria;
import com.lubricante.rukanas.model.entities.Producto;
import com.lubricante.rukanas.model.request.ProductoRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<ProductoDto> findAllProducts();

   // ProductoDto saveProduct(Producto producto);


    Optional<ProductoDto> findIdProducto(Long id);
    ProductoDto saveProduct(Producto producto);

    Optional<ProductoDto> updateProducto(ProductoRequest producto, Long id);

    List<Producto> allProductByCategory(Categoria categoria);
    Optional findById(Long id);

    void EliminarProducto(Long id);

    List<Producto> allProductByDescuento();

}
