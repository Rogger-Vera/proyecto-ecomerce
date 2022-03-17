package com.telecomeducacionit.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecomeducacionit.entidades.Usuario;
import com.telecomeducacionit.repositorio.IUsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {
	
	@Autowired
	private IUsuarioRepositorio usuarioRepositorio;

	@Override
	public Optional<Usuario> buscarUsuario(Integer id) {
		
		return usuarioRepositorio.findById(id);
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarUsuarioPorEmail(String email) {
		
		return usuarioRepositorio.findByEmail(email);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		
		return usuarioRepositorio.findAll();
	}
	
	
	
	/*
	@Transactional(readOnly=true)
	public Usuario buscarUsuario (Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=false)
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}
	
	@Transactional(readOnly=true)
	public List<Usuario> listarUsuarios(){
		return (List<Usuario>) usuarioDao.findAll();
	}
	
	@Transactional(readOnly=false)
	public boolean borrarUsuario(Integer id) {
		try {
			usuarioDao.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	*/
	
	
	

}
