package com.telecomeducacionit.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.telecomeducacionit.entidades.DetalleOrden;
import com.telecomeducacionit.entidades.OrdenCompra;
import com.telecomeducacionit.entidades.Producto;
import com.telecomeducacionit.entidades.Usuario;
import com.telecomeducacionit.servicios.IDetalleOrdenServicio;
import com.telecomeducacionit.servicios.IOrdenCompraServicio;
import com.telecomeducacionit.servicios.IProductoServicio;
import com.telecomeducacionit.servicios.IUsuarioServicio;

@Controller
@CrossOrigin(origins= {"*"})
@RequestMapping("/")
public class ControladorHome {
	
	private final Logger log = LoggerFactory.getLogger(ControladorHome.class);
	
	@Autowired
	private IProductoServicio productoServicio;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private IOrdenCompraServicio ordenCompraServicio;
	
	@Autowired
	private IDetalleOrdenServicio detalleOrdenServicio;
	
	//para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	//datos de la orden
	OrdenCompra ordenCompra = new OrdenCompra();
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del usuario: {}", session.getAttribute("idUsuario"));
		
		model.addAttribute("productos", productoServicio.listarProductos());
		
		//session
		model.addAttribute("sesionId", session.getAttribute("idUsuario"));		
		return "usuario/home";
	}
	
	@GetMapping("productoHome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		
		Producto producto = new Producto();
		Optional<Producto> productoOpt = productoServicio.buscarProducto(id);
		producto = productoOpt.get();
		model.addAttribute("producto", producto);
		
		return "usuario/productoHome";
	}
	
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;
		
		Optional<Producto> optionalProducto = productoServicio.buscarProducto(id);
		
		
		producto = optionalProducto.get();
		
		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);
		
		//validamos que un mismo producto no se añada 2 veces o mas al carrito
		
		Integer idProducto = producto.getId();
		boolean ingresado = detalles.stream().anyMatch(product -> product.getProducto().getId() == idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		//suma de totales de los productos de la lista
		sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		ordenCompra.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("ordenCompra", ordenCompra);
		
		return "usuario/carrito";
	}
	
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {
		
		List<DetalleOrden> detalleOrdenNueva = new ArrayList<DetalleOrden>();
		
		
		for(DetalleOrden detalleOrden: detalles) {
			if(detalleOrden.getProducto().getId() != id) {
				detalleOrdenNueva.add(detalleOrden);
			}
		}
		
		//lista nueva con los productos restantes del carrito
		detalles = detalleOrdenNueva;
		
		double sumaTotal = 0;
		
		//suma de totales de los productos de la lista
		sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		ordenCompra.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("ordenCompra", ordenCompra);
				
		
		return "usuario/carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("ordenCompra", ordenCompra);
		
		//session
		model.addAttribute("sesionId", session.getAttribute("idUsuario"));
		
		return "/usuario/carrito";
	}
	
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
		Usuario usuario = usuarioServicio.buscarUsuario(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("ordenCompra", ordenCompra);
		model.addAttribute("usuario", usuario);
		
		return "usuario/resumenOrden";
	}
	
	@GetMapping("/saveOrder")
	public String guardarOrdenCompra(HttpSession session) {
		
		List<OrdenCompra> listaOr = ordenCompraServicio.listarOrdenesCompra();
		
		log.info("ordenes: {}", listaOr);
		
		Date fechaCreacion = new Date();
		ordenCompra.setFechaCreacion(fechaCreacion);
		ordenCompra.setNumero(ordenCompraServicio.generarNumeroOrdenCompra());
		
		//guardar Usuario
		Usuario usuario = usuarioServicio.buscarUsuario(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
		ordenCompra.setUsuario(usuario);
		ordenCompraServicio.guardarOrdenCompra(ordenCompra);
		
		//guardar detalles de ordenCompra
		for (DetalleOrden detalleOrden : detalles) {
			detalleOrden.setOrdencompra(ordenCompra);
			detalleOrdenServicio.guardarDetalleOrden(detalleOrden);
		}
		
		//Limpiar lista y orden compra
		ordenCompra = new OrdenCompra();
		detalles.clear();
		
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String buscarProducto(@RequestParam String nombre, Model model) {
		log.info("nombre del producto: {}", nombre);
		
		List<Producto> productos = productoServicio.listarProductos().stream().filter(produ -> (produ.getNombre().toLowerCase()).contains(nombre.toLowerCase())).collect(Collectors.toList());
		
		model.addAttribute("productos", productos);
		
		return "usuario/home";
		
	}
	
	
	
	

}
