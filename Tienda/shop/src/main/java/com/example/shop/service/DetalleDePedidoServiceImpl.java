package com.example.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.model.DetallesDePedido;
import com.example.shop.repository.IDetallePedidoRepository;

@Service
public class DetalleDePedidoServiceImpl implements IDetallePedidoService{

	
	@Autowired
	private IDetallePedidoRepository detallePedidoRepository;
	
	@Override
	public DetallesDePedido save(DetallesDePedido detalleDePedido) {
		return detallePedidoRepository.save(detalleDePedido);
	}

}
