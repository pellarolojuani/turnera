package ar.uba.fi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicos")
public class MedicoDto {
	@Id
	private String id;
	private EspecialidadDto especialidad;
	private String nombre;
	private String matricula;
	private String usuario;
	
	public MedicoDto(String id, EspecialidadDto especialidad, String nombre, String matricula) {
		super();
		this.id = id;
		this.especialidad = especialidad;
		this.nombre = nombre;
		this.matricula = matricula;
	}
	
	public MedicoDto(EspecialidadDto especialidad, String nombre, String matricula) {
		super();
		this.especialidad = especialidad;
		this.nombre = nombre;
		this.matricula = matricula;
	}

	public MedicoDto(String nombre, String matricula) {
		super();
		this.nombre = nombre;
		this.matricula = matricula;
	}

	public MedicoDto() {}
	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public MedicoDto(EspecialidadDto especialidad, String nombre) {
		super();
		this.especialidad = especialidad;
		this.nombre = nombre;
	}
	public EspecialidadDto getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(EspecialidadDto especialidad) {
		this.especialidad = especialidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
