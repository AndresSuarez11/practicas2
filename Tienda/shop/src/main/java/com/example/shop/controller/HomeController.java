package com.example.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop.model.Cliente;
import com.example.shop.model.Producto;
import com.example.shop.model.DetallesDePedido;
import com.example.shop.model.Pedido;
import com.example.shop.service.IClienteService;
import com.example.shop.service.IDetallePedidoService;
import com.example.shop.service.IPedidoService;
import com.example.shop.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log=LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IDetallePedidoService detallePedidoService;
	
	List<DetallesDePedido> detalles = new ArrayList<DetallesDePedido>();
	
	Pedido pedido = new Pedido(); 
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("sesion del usuario: {}", session.getAttribute("idcliente"));
		model.addAttribute("productos", productoService.findAll());
		
		//session
		
		model.addAttribute("sesion", session.getAttribute("idcliente"));
		return "cliente/home";
	}
	
	@GetMapping("homeproducto/{id}")
	public String homeProducto(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parametro {}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();
		
		model.addAttribute("producto", producto);
		return "cliente/homeproducto";
	}
	
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
	    DetallesDePedido detalleDePedido = new DetallesDePedido();
	    Producto producto = new Producto();
	    double sumaTotal = 0;

	    Optional<Producto> optionalProducto = productoService.get(id);
	    log.info("Producto añadido: {} ", optionalProducto.get());
	    log.info("Cantidad:  {}", cantidad);
	    producto = optionalProducto.get();

	    detalleDePedido.setCantidad(cantidad);
	    detalleDePedido.setPrecio(producto.getPrecio());
	    detalleDePedido.setNombre(producto.getNombre());
	    detalleDePedido.setTotal(producto.getPrecio() * cantidad);
	    detalleDePedido.setProducto(producto);

	    // Validar que el producto no se pueda añadir dos veces
	    Integer idProducto = producto.getId();
	    boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

	    if (!ingresado) {
	        detalles.add(detalleDePedido);
	    }

	    sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

	    detalleDePedido.setTotal(sumaTotal);
	    pedido.setTotal(sumaTotal); // Asignar el total al pedido

	    model.addAttribute("cart", detalles);
	    model.addAttribute("pedido", pedido);

	    return "cliente/carrito";
	}
	
	//Quitar un producto del cart
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {
		
		//Nueva lista de productos
		List<DetallesDePedido> detalleDePedidoNuevo = new ArrayList<DetallesDePedido>();
		
		for(DetallesDePedido detalleDePedido: detalles) {
			if(detalleDePedido.getProducto().getId() != id ) {
				detalleDePedidoNuevo.add(detalleDePedido);
			}
		}
		
		//Poner la nueva lista con los productos restantes
		detalles=detalleDePedidoNuevo;
		
		double sumaTotal=0;
		
		sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		pedido.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("pedido", pedido);
		
		return "cliente/carrito";
	}
	
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("pedido", pedido);
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idcliente"));
		
		return "cliente/carrito";
	}
		
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
		Cliente cliente = clienteService.findById(Integer.parseInt(session.getAttribute("idcliente").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("pedido", pedido);
		model.addAttribute("cliente", cliente);
		
		return "cliente/resumenpedido";
	}

	//guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date();
		pedido.setFechaPedido(fechaCreacion);
		pedido.setNumero(pedidoService.generarNumeroPedido());
		
		//Usuario
		Cliente cliente = clienteService.findById(Integer.parseInt(session.getAttribute("idcliente").toString())).get();
		
		pedido.setCliente(cliente);
		pedidoService.Save(pedido);
		
		//guardar detalles
		for(DetallesDePedido dt:detalles) {
			dt.setPedidos(pedido);
			detallePedidoService.save(dt);
		}
		
		//Limpiar lista y orden
		
		pedido = new Pedido();
		detalles.clear();
		return "redirect:/";
	}
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		log.info("nombre del producto: {}", nombre);
		List<Producto> productos= productoService.findAll().stream().filter(p -> p.getImagen().contains(nombre)).collect(Collectors.toList());
		model.addAttribute("productos", productos);
		return "cliente/home";
	}
	

}
