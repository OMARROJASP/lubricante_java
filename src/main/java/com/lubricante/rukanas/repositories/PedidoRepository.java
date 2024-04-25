package com.lubricante.rukanas.repositories;

import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

       List<Pedido> findPedidoByUsuario(Usuario usuario);

       List<Pedido> findPedidoByUsuarioAndAndEstado(Usuario usuario, Long estado);

       @Query("SELECT p FROM Pedido p WHERE p.usuario.id = :usuario AND p.estado = 0")
       List<Pedido> encontrarPedidoPorUsuarioYEstado(Long usuario);

//       SELECT * FROM lubricantes_rukanas.pedidos
//       WHERE estado = 0 and usuario_id = 1;
}
