package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import com.example.shop.model.Categoria;


public interface ICategoriaService {
    public List<Categoria> findAll();
	Categoria save(Categoria categoria);
	void delete(Integer id);
	Optional<Categoria> findById(Integer id);
	
}
