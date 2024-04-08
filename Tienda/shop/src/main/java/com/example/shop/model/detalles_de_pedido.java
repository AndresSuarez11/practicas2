package com.example.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_de_pedido")
public class detalles_de_pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer idPedido;
	private Integer idProducto;
	private int cantidad;
	
	@OneToOne
	private pedidos pedidos;
	
	@ManyToOne
	private Producto producto;
	
	

	public detalles_de_pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public detalles_de_pedido(Integer id, Integer idPedido, Integer idProducto, int cantidad,
			com.example.shop.model.pedidos pedidos, Producto producto) {
		super();
		this.id = id;
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.pedidos = pedidos;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "detalles_de_pedido [id=" + id + ", idPedido=" + idPedido + ", idProducto=" + idProducto + ", cantidad="
				+ cantidad + ", pedidos=" + pedidos + ", producto=" + producto + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public pedidos getPedidos() {
		return pedidos;
	}

	public void setPedidos(pedidos pedidos) {
		this.pedidos = pedidos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	

}
