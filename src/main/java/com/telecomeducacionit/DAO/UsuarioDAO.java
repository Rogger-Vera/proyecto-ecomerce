package com.telecomeducacionit.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.telecomeducacionit.entidades.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
	

}
