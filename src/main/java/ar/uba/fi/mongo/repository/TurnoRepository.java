package ar.uba.fi.mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.TurnosDto;

public interface TurnoRepository  extends MongoRepository<TurnosDto, String>{

	public List<TurnosDto> findByFecha(Date fecha);

	public List<TurnosDto> findByEspecialidad(EspecialidadDto especialidad);
	
	public List<TurnosDto> findByEstado(String estado);
	
	public List<TurnosDto> findByMedico(MedicoDto medico);
	
	public List<TurnosDto> findByPaciente(PacienteDto paciente);

	public List<TurnosDto> findByMedicoAndEstadoIsTrue(MedicoDto medico);

	public List<TurnosDto> findByMedicoAndEstadoIsFalse(MedicoDto medico);

	public List<TurnosDto> findByMedicoAndFechaAndEstadoIsFalse(MedicoDto medico, Date date);
	
	public List<TurnosDto> findByMedicoAndFechaAndAndEspecialidadAndEstadoIsFalse(MedicoDto medico, Date date, EspecialidadDto especialidad);
	
	public List<TurnosDto> findByMedicoAndFechaAndAndEspecialidadAndEstadoIsTrue(MedicoDto medico, Date date, EspecialidadDto especialidad);
	
	public List<TurnosDto> findByPacienteAndAndEstadoIsTrue(PacienteDto paciente);
	
	public List<TurnosDto> findByMedicoAndEspecialidadAndEstadoIsTrue(MedicoDto medico, EspecialidadDto especialidad);
	
	public List<TurnosDto> findByMedicoAndEspecialidadAndEstadoIsFalse(MedicoDto medico, EspecialidadDto especialidad);

}