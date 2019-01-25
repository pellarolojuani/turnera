package ar.uba.fi.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.DocenteDto;

public interface DocenteRepository  extends MongoRepository<DocenteDto, String>{

	public DocenteDto findOneByDni(Long dni);

	public List<DocenteDto> findByMateria(String materia);

	public DocenteDto findOneByAnio(Integer anio);

}
