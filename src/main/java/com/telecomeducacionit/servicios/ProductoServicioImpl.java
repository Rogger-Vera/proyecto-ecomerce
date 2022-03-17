package com.telecomeducacionit.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telecomeducacionit.entidades.Producto;
import com.telecomeducacionit.repositorio.IProductoRepositorio;

@Service
public class ProductoServicioImpl implements IProductoServicio {
	
	@Autowired
	
	private IProductoRepositorio productoRepositorio;

	@Override
	public Producto guardarProducto(Producto producto) {
		return productoRepositorio.save(producto);
	}

	@Override
	public Optional<Producto> buscarProducto(Integer id) {
		return productoRepositorio.findById(id);
	}

	@Override
	public void actualizarProducto(Producto producto) {
		productoRepositorio.save(producto);
	}

	@Override
	public void borrarProducto(Integer id) {
		productoRepositorio.deleteById(id);
	}

	@Override
	public List<Producto> listarProductos() {
		return (List<Producto>) productoRepositorio.findAll();
	}


	
	
	
	

}
