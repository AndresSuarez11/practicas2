package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shop.model.Producto;


public interface IProductoService {
	public Producto save(Producto producto);
	public Optional<Producto> get(Integer id);
	public void update(Producto producto);
	public void delete(Integer id);
	public List<Producto> findAll();
	public List<Producto> findByCategoriaId(Integer idCategoria);
}
