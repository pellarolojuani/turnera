package ar.uba.fi.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "turnos")
public class TurnosDto {
	@Id
	private String id;
	private Date fecha;
	private Integer hora;
	private Integer minutos;
	private EspecialidadDto especialidad;
	private Boolean estado;
	private MedicoDto medico;
	private String fechaString;
	private PacienteDto paciente;
	private String numeroComprobante;
	private String numeroComprobanteAnulado;
	private Integer duracion;
	
	
	public TurnosDto() {}
	
	
	public TurnosDto(Date fecha, EspecialidadDto especialidad, Boolean estado, MedicoDto medico) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.estado = estado;
		this.medico = medico;
	}
	
	public TurnosDto(Date fecha, Boolean estado, PacienteDto paciente) {
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
	
	
	
	public TurnosDto(Date fecha, EspecialidadDto especialidad, Boolean estado, MedicoDto medico, PacienteDto paciente,
			String numeroComprobante) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.estado = estado;
		this.medico = medico;
		this.paciente = paciente;
		this.numeroComprobante = numeroComprobante;
	}

	public TurnosDto(String id, Date fecha, EspecialidadDto especialidad, Boolean estado, MedicoDto medico,
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

	public TurnosDto(Date fecha, EspecialidadDto especialidad, Boolean estado, MedicoDto medico, PacienteDto paciente,
			String numeroComprobante, String numeroComprobanteAnulado, Integer duracion) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.estado = estado;
		this.medico = medico;
		this.paciente = paciente;
		this.numeroComprobante = numeroComprobante;
		this.numeroComprobanteAnulado = numeroComprobanteAnulado;
		this.duracion = duracion;
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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
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


	public String getNumeroComprobanteAnulado() {
		return numeroComprobanteAnulado;
	}


	public void setNumeroComprobanteAnulado(String numeroComprobanteAnulado) {
		this.numeroComprobanteAnulado = numeroComprobanteAnulado;
	}


	public Integer getDuracion() {
		return duracion;
	}


	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}


	public Integer getHora() {
		return hora;
	}


	public void setHora(Integer hora) {
		this.hora = hora;
	}


	public Integer getMinutos() {
		return minutos;
	}


	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}
	
}
