package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.MedicoDto;

public interface MedicoRepository  extends MongoRepository<MedicoDto, String>{

}
