package com.telecomeducacionit.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Integer id;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String usuario;
	@Column(nullable=false)
	private String contraseña;
	@Column(nullable=false)
	private String domicilio;
	@Column(nullable=false)
	private String tipo;
	
	@OneToMany(mappedBy = "usuario")
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "usuario")
	private List<OrdenCompra> ordenes;
	
	public Usuario() {
		
	}

	public Usuario(Integer id, String nombre, String apellido, String email, String usuario, String contraseña,
			String domicilio, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.domicilio = domicilio;
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<OrdenCompra> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<OrdenCompra> ordenes) {
		this.ordenes = ordenes;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", usuario="
				+ usuario + ", contraseña=" + contraseña + ", domicilio=" + domicilio + ", tipo=" + tipo
				+ ", productos=" + productos + ", ordenes=" + ordenes + "]";
	}

	
	
	
	
}
