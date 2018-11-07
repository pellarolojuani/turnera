package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.TurnosDto;

public interface TurnoRepository  extends MongoRepository<TurnosDto, String>{

}
