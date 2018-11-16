package ar.uba.fi.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.TurnosTodosDto;

public interface TurnoTodosRepository extends MongoRepository<TurnosTodosDto, String>{

	public List<TurnosTodosDto> findByMedico(String medico);

	public List<TurnosTodosDto> findByMedicoAndLibreIsTrue(String medico);

	public List<TurnosTodosDto> findByMedicoAndLibreIsFalse(String medico);

	public List<TurnosTodosDto> findByEspecialidad(String especialidad);

	public List<TurnosTodosDto> findByMedicoAndFechaAndHoraBetween(String medico, String fecha, Integer desde, Integer hasta);

}
