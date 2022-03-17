package com.telecomeducacionit.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecomeducacionit.entidades.OrdenCompra;
import com.telecomeducacionit.entidades.Usuario;

@Repository
public interface IOrdenCompraRepositorio extends JpaRepository<OrdenCompra, Integer> {
	
	List<OrdenCompra> findByUsuario(Usuario usuario);

}
