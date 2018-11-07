package ar.uba.fi.controller;

public class MedicoDto {
	
	private String especialidad;
	private String nombre;
	
		
	public MedicoDto(String especialidad, String nombre) {
		super();
		this.especialidad = especialidad;
		this.nombre = nombre;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
