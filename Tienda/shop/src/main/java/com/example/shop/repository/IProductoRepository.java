package com.example.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.Producto;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByCategoriaId(Integer idCategoria);
}
