package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.model.Cliente;
import com.example.shop.repository.IClienteRepository;


@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	public Optional<Cliente> findById(Integer id) {

		return clienteRepository.findById(id);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Optional<Cliente> findBycorreoElectronico(String correoElectronico) {
		return clienteRepository.findByCorreoElectronico(correoElectronico);
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

}
