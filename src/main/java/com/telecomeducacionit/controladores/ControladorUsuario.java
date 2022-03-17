package com.telecomeducacionit.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecomeducacionit.entidades.OrdenCompra;
import com.telecomeducacionit.entidades.Usuario;
import com.telecomeducacionit.servicios.IOrdenCompraServicio;
import com.telecomeducacionit.servicios.IUsuarioServicio;
import com.telecomeducacionit.servicios.UsuarioServicioImpl;

@Controller
@CrossOrigin(origins= {"*"})
@RequestMapping("/usuario")
public class ControladorUsuario {
	
	private final Logger log = LoggerFactory.getLogger(ControladorUsuario.class);
	
	@Autowired
	private IUsuarioServicio usuarioServico;
	
	@Autowired
	private IOrdenCompraServicio ordenCompraServicio;
	
	
	BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	
	@GetMapping("/registro")
	public String crearRegistro() {
		
		return "usuario/registro";
	}
	
	@PostMapping("/save")
	public String guardarUsuario(Usuario usuario) {
		log.info("Registro usuario: {}", usuario);
		usuario.setTipo("USER");
		usuario.setPassword(passEncode.encode(usuario.getPassword()));
		usuarioServico.guardarUsuario(usuario);
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@GetMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		log.info("Accesos: {}", usuario);
		
		Optional<Usuario> user = usuarioServico.buscarUsuario(Integer.parseInt(session.getAttribute("idUsuario").toString()));
		//log.info("User db: {}", user.get());
		
		if (user.isPresent()) {
			session.setAttribute("idUsuario", user.get().getId());
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			} else {
				return "redirect:/";
			}
		}else {
			log.info("El usuario no existe");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		
		model.addAttribute("sesionId", session.getAttribute("idUsuario"));
		
		Usuario usuario = usuarioServico.buscarUsuario(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
		List<OrdenCompra> ordenes = ordenCompraServicio.buscarPorUsuario(usuario);
		
		model.addAttribute("ordenes", ordenes);
		
		return "usuario/compras";
	}
	
	@GetMapping("/detalleCompra/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		log.info("Id de la orden: {}", id);
		Optional<OrdenCompra> orden = ordenCompraServicio.buscarPorId(id);
		
		model.addAttribute("detallesCompras", orden.get().getDetalleOrden());
		
		//session
		model.addAttribute("sesionId", session.getAttribute("idUsuario"));
		
		return "usuario/detalleCompra";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		
		session.removeAttribute("idUsuario");
		return "redirect:/";
	}
	

	
	
	

}
