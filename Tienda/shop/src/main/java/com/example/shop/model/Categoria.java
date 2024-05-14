package com.example.shop.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	
	@OneToMany(mappedBy = "categoria")
	private List<Producto> Productos;
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(Integer id, String nombre, String descripcion, List<Producto> productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		Productos = productos;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", Productos="
				+ Productos + "]";
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return Productos;
	}

	public void setProductos(List<Producto> productos) {
		Productos = productos;
	}

	
	
	
	
}
