package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.EspecialidadDto;

public interface EspecialidadRepository  extends MongoRepository<EspecialidadDto, String>{

}
