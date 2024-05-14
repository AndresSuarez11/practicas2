package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.Producto;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
