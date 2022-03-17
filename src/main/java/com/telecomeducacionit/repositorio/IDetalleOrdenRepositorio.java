package com.telecomeducacionit.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecomeducacionit.entidades.DetalleOrden;

@Repository
public interface IDetalleOrdenRepositorio extends JpaRepository<DetalleOrden, Integer> {

}
