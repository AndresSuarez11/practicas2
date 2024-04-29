package com.example.shop.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shop.model.Cliente;
import com.example.shop.model.Pedido;
import com.example.shop.service.IClienteService;
import com.example.shop.service.IPedidoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	
	@GetMapping("/registro")
	public String create() {
		return "cliente/registro";
	}
	
	@PostMapping("/save")
	public String save(Cliente cliente) {
		logger.info("Usuario registro: {}", cliente);
		
		cliente.setTipo("USER");
		clienteService.save(cliente);
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "cliente/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Cliente cliente, HttpSession session) {
		logger.info("Acceso: {}", cliente);
		
		Optional<Cliente> user=clienteService.findBycorreoElectronico(cliente.getCorreoElectronico());
		//logger.info("Usuario de db: {}", user.get());
		
		if(user.isPresent()) {
			session.setAttribute("idcliente", user.get().getId());
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			} else {
				return "redirect:/";
			}
		} else {
			logger.info("cliente no existe");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		
		model.addAttribute("sesion", session.getAttribute("idcliente"));
		Cliente cliente = clienteService.findById(Integer.parseInt(session.getAttribute("idcliente").toString())).get();
		List<Pedido> pedidos= pedidoService.findByCliente(cliente);
		
		model.addAttribute("pedidos", pedidos);
		return "cliente/compras";
	}

	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("id de la orden: {}", id);
		
		Optional<Pedido> pedido = pedidoService.findById(id);
		
		model.addAttribute("detalles", pedido.get().getDetalle());
		
		//sesion
		model.addAttribute("sesion", model.getAttribute("idcliente"));
		
		return "cliente/detallecompra";
	}
	
	
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idcliente");
		return "redirect:/";
	}
	
}
