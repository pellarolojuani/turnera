package ar.uba.fi.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "turnos")
public class TurnosDto {
	@Id
	private String id;
	private Date fecha;
	private String especialidad;
	private String estado;
	private String medico;
	
	
	public TurnosDto(Date fecha, String especialidad, String estado, String medico) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.estado = estado;
		this.medico = medico;
	}
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String espacialidad) {
		this.especialidad = espacialidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getMedico() {
		return medico;
	}


	public void setMedico(String medico) {
		this.medico = medico;
	}

}
