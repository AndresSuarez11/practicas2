package com.example.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.model.Cliente;
import com.example.shop.model.Pedido;
import com.example.shop.repository.IPedidoRepository;

@Service
public class PedidoServiceImpl implements IPedidoService{
	
	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Override
	public Pedido Save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public String generarNumeroPedido() {
		int numero=0;
		String numeroConcatenado="";
		
		List<Pedido> pedidos = findAll();
		
		List<Integer> numeros= new ArrayList<Integer>();
		
		pedidos.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
		
		if(pedidos.isEmpty()) {
			numero=1;
		} else {
			numero= numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if(numero<10) {
			numeroConcatenado="000000000"+String.valueOf(numero);
		} else if(numero<100){
			numeroConcatenado="00000000"+String.valueOf(numero);
		} else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		} else if(numero<10000) {
			numeroConcatenado="000000"+String.valueOf(numero);
		} else if(numero<100000) {
			numeroConcatenado="00000"+String.valueOf(numero);
		} else if(numero<1000000) {
			numeroConcatenado="0000"+String.valueOf(numero);	
		} else if(numero<10000000) {
			numeroConcatenado="000"+String.valueOf(numero);
		} else if(numero<100000000) {
			numeroConcatenado="00"+String.valueOf(numero);	
		} else if(numero<1000000000) {
			numeroConcatenado="0"+String.valueOf(numero);
		}
		
		return numeroConcatenado;
	}

	@Override
	public List<Pedido> findByCliente(Cliente cliente) {
		
		return pedidoRepository.findByCliente(cliente);
	}

	@Override
	public Optional<Pedido> findById(Integer id) {
		return pedidoRepository.findById(id);
	}
}
