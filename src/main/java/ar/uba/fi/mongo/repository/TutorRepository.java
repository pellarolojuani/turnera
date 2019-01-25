package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.TutorDto;

public interface TutorRepository  extends MongoRepository<TutorDto, String>{

	public TutorDto findOneByDni(Long dni);

}
