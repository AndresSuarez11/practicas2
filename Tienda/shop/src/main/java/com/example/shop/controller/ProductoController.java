package com.example.shop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.model.Categoria;
import com.example.shop.model.Cliente;
import com.example.shop.model.Producto;
import com.example.shop.service.ICategoriaService;
import com.example.shop.service.IClienteService;
import com.example.shop.service.IProductoService;
import com.example.shop.service.UploadFileService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private UploadFileService upload;
	
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
	    List<Categoria> categorias = categoriaService.findAll();
	    model.addAttribute("categorias", categorias);
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session, @RequestParam("categoria") Integer categoriaId) throws IOException {
		Cliente C = clienteService.findById(Integer.parseInt(session.getAttribute("idcliente").toString())).get();
		producto.setCliente(C);
		
		Categoria categoria = categoriaService.findById(categoriaId).orElseThrow();
		producto.setCategoria(categoria);
		
		//imagen
		if(producto.getId()==null) { //Cuando se crea un producto
			String nombreImagen= upload.saveImage(file);
			producto.setImagen(nombreImagen);
		} else {
		}
		
		productoService.save(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto=optionalProducto.get();
		List<Categoria> categorias=categoriaService.findAll();
		model.addAttribute("producto", producto);
		model.addAttribute("categorias", categorias);
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		
		Producto p = new Producto();
		p=productoService.get(producto.getId()).get();
		
if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem
			
			producto.setImagen(p.getImagen());
		}else {// cuando se edita tbn la imagen			
			//eliminar cuando no sea la imagen por defecto
			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			String nombreImagen= upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setCliente(p.getCliente());
		
		productoService.update(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Producto p = new Producto();
		p=productoService.get(id).get();
	
		//eliminar cuando no sea la imagen por defecto
				if (!p.getImagen().equals("default.jpg")) {
					upload.deleteImage(p.getImagen());
				}
				
				
		productoService.delete(id);
		return "redirect:/productos";
	}
	

	
}
