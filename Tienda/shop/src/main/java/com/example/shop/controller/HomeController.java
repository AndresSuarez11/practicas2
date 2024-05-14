package com.example.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop.model.Categoria;
import com.example.shop.model.Cliente;
import com.example.shop.model.Producto;
import com.example.shop.model.DetallesDePedido;
import com.example.shop.model.Pedido;
import com.example.shop.service.ICategoriaService;
import com.example.shop.service.IClienteService;
import com.example.shop.service.IDetallePedidoService;
import com.example.shop.service.IPedidoService;
import com.example.shop.service.IProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
	
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IDetallePedidoService detallePedidoService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	List<DetallesDePedido> detalles = new ArrayList<DetallesDePedido>();
	
	Pedido pedido = new Pedido(); 
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
	    List<Producto> productos = productoService.findAll();
	    List<Categoria> categorias = categoriaService.findAll();
	    model.addAttribute("productos", productos);
	    model.addAttribute("categorias", categorias);
		
		//session
		
		model.addAttribute("sesion", session.getAttribute("idcliente"));
		return "cliente/home";
	}
	
	@GetMapping("/filtrar")
	public String filtrarPorCategoria(@RequestParam("categoria") Integer idCategoria, Model model, HttpSession session) {
		
		List<Producto> productosFiltrados = productoService.findByCategoriaId(idCategoria);
	    List<Categoria> categorias = categoriaService.findAll();
	    model.addAttribute("productos", productosFiltrados);
	    model.addAttribute("categorias", categorias);
	    
	    model.addAttribute("sesion", session.getAttribute("idcliente"));
	    return "cliente/home";
	}
	
	
	@GetMapping("homeproducto/{id}")
	public String homeProducto(@PathVariable Integer id, Model model, HttpSession session) {
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();
		
		model.addAttribute("producto", producto);
		model.addAttribute("sesion", session.getAttribute("idcliente"));
		return "cliente/homeproducto";
	}
	
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model, HttpSession session) {
	    DetallesDePedido detalleDePedido = new DetallesDePedido();
	    Producto producto = new Producto();
	    double sumaTotal = 0;

	    Optional<Producto> optionalProducto = productoService.get(id);
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
		model.addAttribute("sesion", session.getAttribute("idcliente"));
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
		model.addAttribute("sesion", session.getAttribute("idcliente"));
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
	


}
