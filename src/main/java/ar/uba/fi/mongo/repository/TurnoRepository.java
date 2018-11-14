package ar.uba.fi.mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.TurnosDto;

public interface TurnoRepository  extends MongoRepository<TurnosDto, String>{

	public List<TurnosDto> findByFecha(Date fecha);

	public List<TurnosDto> findByEspecialidad(String especialidad);
	
	public List<TurnosDto> findByEstado(String estado);
	
	public List<TurnosDto> findByMedico(String medico);
	
	public List<TurnosDto> findByPaciente(PacienteDto paciente);

}
