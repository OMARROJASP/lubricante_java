package com.lubricante.rukanas.repositories;

import com.lubricante.rukanas.model.entities.Pedido;
import com.lubricante.rukanas.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

       List<Pedido> findPedidoByUsuario(Usuario usuario);

       List<Pedido> findPedidoByUsuarioAndAndEstado(Usuario usuario, Long estado);
}
