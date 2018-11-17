package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.ComprobanteDto;

public interface ComprobanteRepository extends MongoRepository<ComprobanteDto, String>{
	
	public ComprobanteDto findFirstByOrderByContador();

}
