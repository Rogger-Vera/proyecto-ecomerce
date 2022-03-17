package com.telecomeducacionit.servicios;

import java.util.List;
import java.util.Optional;

import com.telecomeducacionit.entidades.Producto;

public interface IProductoServicio {
	
	Producto guardarProducto(Producto producto);
	Optional<Producto> buscarProducto(Integer id);
	void actualizarProducto(Producto producto);
	void borrarProducto(Integer id);
	List<Producto> listarProductos();
	
	
}
