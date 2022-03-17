package com.telecomeducacionit.servicios;

import java.util.List;
import java.util.Optional;

import com.telecomeducacionit.entidades.Usuario;

public interface IUsuarioServicio {
	
	List<Usuario> listarUsuarios();
	Optional<Usuario> buscarUsuario(Integer id);
	Usuario guardarUsuario(Usuario usuario);
	Optional<Usuario> buscarUsuarioPorEmail(String email);

}
