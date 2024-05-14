package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.model.Producto;
import com.example.shop.repository.IProductoRepository;


@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id);
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		productoRepository.save(producto);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productoRepository.deleteById(id);
	}
	
	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> findByCategoriaId(Integer idCategoria) {
		// TODO Auto-generated method stub
		return productoRepository.findByCategoriaId(idCategoria);
	}
	

}
