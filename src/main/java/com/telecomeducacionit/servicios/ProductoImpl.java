package com.telecomeducacionit.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecomeducacionit.DAO.ProductoDAO;
import com.telecomeducacionit.entidades.Producto;

@Service
public class ProductoImpl {
	
	@Autowired
	
	private ProductoDAO productoDao;
	
	//Buscar un producto
	@Transactional(readOnly=true)
	public Optional<Producto> buscarProducto(Integer id) {
		return productoDao.findById(id);
	}
	
	//Guardar un producto
	@Transactional(readOnly=false)
	public Producto guardarProducto(Producto producto) {
		return productoDao.save(producto);
	}
	
	//Actualizar producto
	@Transactional(readOnly=false)
	public void actualizarProducto(Producto producto) {
		productoDao.save(producto);
	}
	
	//listar los productos
	@Transactional(readOnly=true)
	public List<Producto> listarProductos(){
		return (List<Producto>) productoDao.findAll();
	}
	
	//Eliminar producto
	@Transactional(readOnly=false)
	public void borrarProducto(Integer id) {
		productoDao.deleteById(id);
	}
	
	
	
	
	

}
