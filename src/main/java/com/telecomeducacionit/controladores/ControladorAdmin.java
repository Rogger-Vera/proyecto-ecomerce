package com.telecomeducacionit.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecomeducacionit.entidades.Producto;
import com.telecomeducacionit.servicios.ProductoImpl;


@Controller
@CrossOrigin(origins= {"*"})
@RequestMapping("/administrador")
public class ControladorAdmin {
	
	@Autowired
	private ProductoImpl productoImpl;
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Producto> productos = productoImpl.listarProductos();
		model.addAttribute("productos", productos);
		
		return "administrador/home";
	}
	
	
	
	
}
