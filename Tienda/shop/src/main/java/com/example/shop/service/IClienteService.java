package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import com.example.shop.model.Cliente;

public interface IClienteService {
	List<Cliente> findAll();
	Optional<Cliente> findById(Integer id);
	Cliente save(Cliente cliente);
	Optional<Cliente> findBycorreoElectronico(String correoElectronico);
}
