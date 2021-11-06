package com.telecomeducacionit.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.telecomeducacionit.entidades.Producto;

@Repository
public interface ProductoDAO extends CrudRepository<Producto, Integer> {

}
