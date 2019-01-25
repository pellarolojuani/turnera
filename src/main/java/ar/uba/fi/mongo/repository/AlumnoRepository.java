package ar.uba.fi.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.AlumnoDto;

public interface AlumnoRepository  extends MongoRepository<AlumnoDto, String>{

	public AlumnoDto findOneByDni(Long dni);

	public List<AlumnoDto> findByDocente(String docente);

	public List<AlumnoDto> findByTutor(String tutor);

}
