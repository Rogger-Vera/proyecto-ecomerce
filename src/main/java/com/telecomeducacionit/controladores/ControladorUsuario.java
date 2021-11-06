package com.telecomeducacionit.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecomeducacionit.entidades.Usuario;
import com.telecomeducacionit.servicios.UsuarioImpl;

@RestController
@CrossOrigin(origins= {"*"})
@RequestMapping("/usuarios")
public class ControladorUsuario {
	
	@Autowired
	private UsuarioImpl usuarioImpl;
	
	@GetMapping("{id}")
	public Usuario buscarUsuario(@PathVariable Integer id) {
		return usuarioImpl.buscarUsuario(id);
	}
	
	//el metodo guardar usuario tambien nos permite actualizar el usuario
	//solo hay que incorporar el id en la peticion.
	@PostMapping
	public Usuario guardarUsuario(@RequestBody Usuario usuario) {
		return usuarioImpl.guardarUsuario(usuario);
	}
	
	@GetMapping
	public List<Usuario> listarUsuarios(){
		return usuarioImpl.listarUsuarios();
	}
	
	@DeleteMapping("{id}")
	public String borrarUsuario(@PathVariable Integer id) {
		boolean borrado = usuarioImpl.borrarUsuario(id);
		if (borrado) {
			return "Se borro el usuario con el id " + id;
		}else {
			return "No se pudo borrar el usuario con id " + id;
		}
	}
	
	
	
	
	

}
