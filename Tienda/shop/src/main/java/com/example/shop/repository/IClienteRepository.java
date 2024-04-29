package com.example.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.Cliente;
import java.util.List;


@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
	Optional<Cliente> findByCorreoElectronico(String correoElectronico);
	
}
