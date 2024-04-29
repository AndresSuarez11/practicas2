package com.example.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.Pedido;
import com.example.shop.model.Cliente;


@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Integer>{
	List<Pedido> findByCliente(Cliente cliente);
}
