package com.telecomeducacionit.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecomeducacionit.DAO.UsuarioDAO;
import com.telecomeducacionit.entidades.Usuario;

@Service
public class UsuarioImpl {
	
	@Autowired
	
	private UsuarioDAO usuarioDao;
	
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
	
	
	
	

}
