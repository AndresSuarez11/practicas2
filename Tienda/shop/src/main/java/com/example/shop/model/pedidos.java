package com.example.shop.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class pedidos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer idCliente;
	private Date fechaPedido;
	private String estadoPedido;
	
	
	@ManyToOne
	private Cliente cliente;
	
	
	@OneToOne(mappedBy = "pedidos")
	private detalles_de_pedido detalles_de_pedido;
	
	
	public pedidos(Integer id, Integer idCliente, Date fechaPedido, String estadoPedido) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.fechaPedido = fechaPedido;
		this.estadoPedido = estadoPedido;
	}
	
	public pedidos() {
		super();
	}

	@Override
	public String toString() {
		return "pedidos [id=" + id + ", idCliente=" + idCliente + ", fechaPedido=" + fechaPedido + ", estadoPedido="
				+ estadoPedido + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public String getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public detalles_de_pedido getDetalles_de_pedido() {
		return detalles_de_pedido;
	}

	public void setDetalles_de_pedido(detalles_de_pedido detalles_de_pedido) {
		this.detalles_de_pedido = detalles_de_pedido;
	}
	
}
