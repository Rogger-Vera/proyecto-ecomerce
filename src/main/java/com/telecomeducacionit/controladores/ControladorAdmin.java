package com.telecomeducacionit.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@CrossOrigin(origins= {"*"})
@RequestMapping("/administrador")
public class ControladorAdmin {
	
	@GetMapping("")
	public String home() {
		return "administrador/home";
	}
	
	
}
