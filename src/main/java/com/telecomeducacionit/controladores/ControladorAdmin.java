package com.telecomeducacionit.controladores;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecomeducacionit.entidades.OrdenCompra;
import com.telecomeducacionit.entidades.Producto;
import com.telecomeducacionit.servicios.IOrdenCompraServicio;
import com.telecomeducacionit.servicios.IUsuarioServicio;
import com.telecomeducacionit.servicios.ProductoServicioImpl;


@Controller
@CrossOrigin(origins= {"*"})
@RequestMapping("/administrador")
public class ControladorAdmin {
	
	@Autowired
	private ProductoServicioImpl productoImpl;
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private IOrdenCompraServicio ordenCompraServicio;
	
	private Logger log = LoggerFactory.getLogger(ControladorAdmin.class);
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Producto> productos = productoImpl.listarProductos();
		model.addAttribute("productos", productos);
		
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		
		model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
		
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		
		model.addAttribute("ordenes", ordenCompraServicio.listarOrdenesCompra());

		
		
		return "administrador/ordenesCompraUsuarios";
	}
	
	@GetMapping("/detalleOrden/{id}")
	public String detalleOrden(Model model, @PathVariable Integer id) {
		
		log.info("id orden {}", id);
		
		OrdenCompra orden = ordenCompraServicio.buscarPorId(id).get();
		
		model.addAttribute("detallesOrden", orden.getDetalleOrden());
		
		return "administrador/detalleOrdenCompra";
	}
	
	
}
