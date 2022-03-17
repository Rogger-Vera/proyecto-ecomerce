package com.telecomeducacionit.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecomeducacionit.entidades.OrdenCompra;
import com.telecomeducacionit.entidades.Usuario;
import com.telecomeducacionit.repositorio.IOrdenCompraRepositorio;

@Service
public class OrdenCompraServicioImpl implements IOrdenCompraServicio {
	@Autowired
	private IOrdenCompraRepositorio ordenCompraRepositorio;
	
	@Override
	public OrdenCompra guardarOrdenCompra(OrdenCompra ordenCompra) {
		
		return ordenCompraRepositorio.save(ordenCompra);
	}

	@Override
	public List<OrdenCompra> listarOrdenesCompra() {
		
		return ordenCompraRepositorio.findAll();
	}
	
	
	public String generarNumeroOrdenCompra() {
		
		int numero = 0;
		String numeroConcatenado = "";
		
		List<OrdenCompra> ordenesCompra = listarOrdenesCompra();
		List<Integer> numerosOrden = new ArrayList<Integer>();
		
		ordenesCompra.stream().forEach(ordenes -> numerosOrden.add(Integer.parseInt(ordenes.getNumero())));
		
		if(ordenesCompra.isEmpty()) {
			numero = 1;
			
		}else {
			numero = numerosOrden.stream().max(Integer::compare).get();
			numero++;
		}
		
		if(numero < 10) {
			numeroConcatenado = "000000000" + String.valueOf(numero);
		} else if(numero < 100) {
			numeroConcatenado = "00000000" + String.valueOf(numero);
		} else if(numero < 1000) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		} else if(numero < 10000) {
			numeroConcatenado = "000000" + String.valueOf(numero);
		}
		
		return numeroConcatenado;
	}

	@Override
	public List<OrdenCompra> buscarPorUsuario(Usuario usuario) {

		return ordenCompraRepositorio.findByUsuario(usuario);
	}

	@Override
	public Optional<OrdenCompra> buscarPorId(Integer id) {

		return ordenCompraRepositorio.findById(id);
	}

	

}
