package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import com.example.shop.model.Cliente;
import com.example.shop.model.Pedido;

public interface IPedidoService {
	List<Pedido> findAll();
	Optional<Pedido> findById(Integer id);
	Pedido Save (Pedido pedido);
	String generarNumeroPedido();
	List<Pedido> findByCliente(Cliente cliente);
	
}
