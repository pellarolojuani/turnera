package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.UsuarioDto;


public interface UserRepository  extends MongoRepository<UsuarioDto, String>{

}
