package com.cfgs.appvetspring.service.impl;

import com.cfgs.appvetspring.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	
	private final static String DIRECTORIO_UPLOAD = "IMAGENES";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		
		Path rutaArchivo = getPath(nombreFoto);
		log.info(rutaArchivo.toString());
		
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			
			recurso = new UrlResource(rutaArchivo.toUri());
			
			log.error("Error no se pudo cargar la imagen: " + nombreFoto);
			
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		System.out.println("estoy en copiar archivo");
		System.out.println("archivo.getOriginalFilename().replac0sss"+archivo.getOriginalFilename().toString());
		String nombreArchivo = UUID.randomUUID().toString() + "_" +  archivo.getOriginalFilename().replace(" ", "");
		System.out.println("nombre concatena"+ nombreArchivo);
		Path rutaArchivo = getPath(nombreArchivo);
		log.info(rutaArchivo.toString());
		System.out.println("antes" +archivo.getInputStream() +rutaArchivo);
		Files.copy(archivo.getInputStream(), rutaArchivo);
		System.out.println("*********************nombreArchivo return"+ nombreArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		
		if(nombreFoto !=null && nombreFoto.length() >0) {
			Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
