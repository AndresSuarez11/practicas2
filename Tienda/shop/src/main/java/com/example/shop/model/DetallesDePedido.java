package com.example.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_de_pedido")
public class DetallesDePedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private double precio;
	private double total;
	private int cantidad;
	
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne
	private Producto producto;

	public DetallesDePedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetallesDePedido(Integer id, String nombre, Integer idPedido, Integer idProducto, double precio,
			double total, int cantidad, com.example.shop.model.Pedido pedidos, Producto producto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.total = total;
		this.cantidad = cantidad;
		this.pedido = pedidos;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "detalles_de_pedido [id=" + id + ", nombre=" + nombre + ", idPedido=" + ", idProducto="
				+ ", precio=" + precio + ", total=" + total + ", cantidad=" + cantidad + ", pedidos="
				+ pedido + ", producto=" + producto + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedidos() {
		return pedido;
	}

	public void setPedidos(Pedido pedidos) {
		this.pedido = pedidos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
}