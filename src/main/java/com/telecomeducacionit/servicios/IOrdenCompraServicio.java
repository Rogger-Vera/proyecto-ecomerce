package com.telecomeducacionit.servicios;

import java.util.List;
import java.util.Optional;

import com.telecomeducacionit.entidades.OrdenCompra;
import com.telecomeducacionit.entidades.Usuario;

public interface IOrdenCompraServicio {
	
	List<OrdenCompra> listarOrdenesCompra();
	OrdenCompra guardarOrdenCompra(OrdenCompra ordenCompra);
	String generarNumeroOrdenCompra();
	List<OrdenCompra> buscarPorUsuario(Usuario usuario);
	Optional<OrdenCompra> buscarPorId(Integer id);
	
}
