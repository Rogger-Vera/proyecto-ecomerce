package com.telecomeducacionit.servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SubirImagen {
	
	private String carpeta = "imagenes//";
	
	public String guardarImagen(MultipartFile archivo) throws IOException {
		
		if (!archivo.isEmpty()) {
			byte[] bytes = archivo.getBytes();
			Path ruta = Paths.get(carpeta + archivo.getOriginalFilename());
			Files.write(ruta, bytes);
			return archivo.getOriginalFilename();
		}
		
		return "default.jpg";
	}
	
	public void eliminarImagen(String nombre) {
		
		String carpeta = "imagenes//";
		File archivo = new File(carpeta + nombre);
		archivo.delete();
	}

}
