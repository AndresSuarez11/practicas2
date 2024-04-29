package com.example.shop.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer idCliente;
	private Date fechaPedido;
	private String numero;
	
	private double total;
	
	
	@ManyToOne
	private Cliente cliente;
	
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	private List<DetallesDePedido> detalle;


	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Pedido(Integer id, Integer idCliente, Date fechaPedido, String numero, double total, Cliente cliente,
			List<DetallesDePedido> detalle) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.fechaPedido = fechaPedido;
		this.numero = numero;
		this.total = total;
		this.cliente = cliente;
		this.detalle = detalle;
	}


	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idCliente=" + idCliente + ", fechaPedido=" + fechaPedido + ", numero=" + numero
				+ ", total=" + total + ", cliente=" + cliente + ", detalle=" + detalle + "]";
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


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<DetallesDePedido> getDetalle() {
		return detalle;
	}


	public void setDetalle(List<DetallesDePedido> detalle) {
		this.detalle = detalle;
	}


	
}
