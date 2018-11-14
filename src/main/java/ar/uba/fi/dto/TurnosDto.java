package ar.uba.fi.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "turnos")
public class TurnosDto {
	@Id
	private String id;
	private Date fecha;
	private EspecialidadDto especialidad;
	private String estado;
	private MedicoDto medico;
	private String fechaString;
	private PacienteDto paciente;
	private String numeroComprobante;
	
	
	public TurnosDto() {}
	
	
	public TurnosDto(Date fecha, EspecialidadDto especialidad, String estado, MedicoDto medico) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.estado = estado;
		this.medico = medico;
	}
	
	public TurnosDto(Date fecha, String estado, PacienteDto paciente) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.paciente = paciente;
	}
	
	
	public TurnosDto(Date fecha, EspecialidadDto especialidad, MedicoDto medico) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.medico = medico;
	}
	
	
	
	public TurnosDto(Date fecha, EspecialidadDto especialidad, String estado, MedicoDto medico, PacienteDto paciente,
			String numeroComprobante) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.estado = estado;
		this.medico = medico;
		this.paciente = paciente;
		this.numeroComprobante = numeroComprobante;
	}

	public TurnosDto(String id, Date fecha, EspecialidadDto especialidad, String estado, MedicoDto medico,
			String fechaString, PacienteDto paciente, String numeroComprobante) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.estado = estado;
		this.medico = medico;
		this.fechaString = fechaString;
		this.paciente = paciente;
		this.numeroComprobante = numeroComprobante;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public EspecialidadDto getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(EspecialidadDto espacialidad) {
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


	public MedicoDto getMedico() {
		return medico;
	}


	public void setMedico(MedicoDto medico) {
		this.medico = medico;
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public PacienteDto getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteDto paciente) {
		this.paciente = paciente;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

}
