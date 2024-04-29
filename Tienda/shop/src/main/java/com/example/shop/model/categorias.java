package com.example.shop.model;

public class categorias {
	private Integer id;
	private String nombre;
	private String descripcion;
	
	
	
	public categorias(Integer id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public categorias() {
		super();
	}

	@Override
	public String toString() {
		return "categorias [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
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
}
