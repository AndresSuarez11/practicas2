package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.model.DetallesDePedido;

@Repository
public interface IDetallePedidoRepository extends JpaRepository<DetallesDePedido, Integer>{

}
