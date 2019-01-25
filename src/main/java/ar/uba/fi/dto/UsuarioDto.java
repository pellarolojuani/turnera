package ar.uba.fi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class UsuarioDto {
	@Id
	private String id;
	private String nombreUsuario;
	private String contrasenia;
	private String permiso;		// puede ser alumno, docente, tutor o admin
	
	public UsuarioDto(String nombreUsuario, String contrasenia) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
	}
	
	public UsuarioDto() {
		super();
	}
	
	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
