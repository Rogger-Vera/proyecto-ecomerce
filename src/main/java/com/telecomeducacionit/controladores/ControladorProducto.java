package com.telecomeducacionit.controladores;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.telecomeducacionit.entidades.Producto;
import com.telecomeducacionit.entidades.Usuario;
import com.telecomeducacionit.servicios.ProductoImpl;
import com.telecomeducacionit.servicios.SubirImagen;

@Controller
@CrossOrigin(origins= {"*"})
@RequestMapping("/productos")
public class ControladorProducto {
	
	private final Logger logger = LoggerFactory.getLogger(ControladorProducto.class);
	
	@Autowired
	private ProductoImpl productoImpl;
	
	@Autowired
	private SubirImagen subirImagen;
	
	@GetMapping("")
	public String vistaProductos(Model model) {
		model.addAttribute("productos", productoImpl.listarProductos());
		
		return "productos/listaProductos";
	}
	
	@GetMapping("/crearProducto")
	public String crearProducto() {
		return "productos/crearProducto";
	}
	
	@PostMapping("/guardar")
	public String guardarProducto(Producto producto, @RequestParam("img") MultipartFile archivo) throws IOException {
		logger.info("Este es el objeto producto {}", producto);
		Usuario usuario = new Usuario(1, "","","","","","","");
		producto.setUsuario(usuario);
		
		//imagen
		//cuando creamos un producto por primera vez
		if(producto.getId()==null) {
			String nombreImagen = subirImagen.guardarImagen(archivo);
			producto.setImagen(nombreImagen);
		}else {
			
		}
		
		productoImpl.guardarProducto(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/editarProducto/{id}")
	public String editarProducto(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optProducto = productoImpl.buscarProducto(id);
		producto = optProducto.get();
		
		logger.info("Producto buscado: {}", producto);
		model.addAttribute("producto", producto);
		
		return "productos/editarProducto";
	}
	
	@PostMapping("/actualizarProducto")
	public String actualizarProducto(Producto producto, @RequestParam("img") MultipartFile archivo) throws IOException {
		Producto prod = new Producto();
		prod = productoImpl.buscarProducto(producto.getId()).get();
		
		//cuando editamos el producto y no cambiamos la imagen
		if(archivo.isEmpty()) {
			producto.setImagen(prod.getImagen());
		}else {
			//cuando editamos el producto y si cambiamos la imagen
			//tambien eliminamos la imagen anterior
			if(!prod.getImagen().equals("default.jpg")) {
				subirImagen.eliminarImagen(prod.getImagen());
			}
			
			String nombreImagen = subirImagen.guardarImagen(archivo);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(prod.getUsuario());
		productoImpl.actualizarProducto(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable Integer id) {
		
		Producto prod = new Producto();
		prod = productoImpl.buscarProducto(id).get();
		
		//eliminar imagen cuando no sea la imagen por defecto
		if(!prod.getImagen().equals("default.jpg")) {
			subirImagen.eliminarImagen(prod.getImagen());
		}
		
		productoImpl.borrarProducto(id);
		return "redirect:/productos";
	}
	
	
	
	
	
	
	
	
	
	

}
