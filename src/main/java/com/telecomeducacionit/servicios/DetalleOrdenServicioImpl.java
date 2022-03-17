package com.telecomeducacionit.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecomeducacionit.entidades.DetalleOrden;
import com.telecomeducacionit.repositorio.IDetalleOrdenRepositorio;

@Service
public class DetalleOrdenServicioImpl implements IDetalleOrdenServicio {
	
	@Autowired
	private IDetalleOrdenRepositorio detalleOrdenRepositorio;
	
	
	@Override
	public DetalleOrden guardarDetalleOrden(DetalleOrden detalleOrden) {
		
		return detalleOrdenRepositorio.save(detalleOrden);
	}

}
