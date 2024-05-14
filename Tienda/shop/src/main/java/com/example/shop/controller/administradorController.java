package com.example.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shop.model.Pedido;
import com.example.shop.model.Producto;
import com.example.shop.service.IClienteService;
import com.example.shop.service.IPedidoService;
import com.example.shop.service.IProductoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	
	@GetMapping("")
	public String home(Model model) {
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
	return "administrador/home";
	}
	
	@GetMapping("/clientes")
	public String clientes(Model model) {
		
		model.addAttribute("clientes", clienteService.findAll());
		
		return "administrador/clientes";
	}
	
	@GetMapping("/pedidos")
	public String pedidos(Model model) {
		
		model.addAttribute("pedidos", pedidoService.findAll());
		
		return "administrador/pedidos";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		
		Pedido pedido=pedidoService.findById(id).get();
		
		model.addAttribute("detalles", pedido.getDetalle());
		return "administrador/detallepedido";
	}
}
