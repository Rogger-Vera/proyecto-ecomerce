package com.telecomeducacionit.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecomeducacionit.entidades.Producto;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {

}
