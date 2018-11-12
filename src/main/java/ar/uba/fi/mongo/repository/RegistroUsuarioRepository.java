package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.RegistroUsuarioDto;

public interface RegistroUsuarioRepository  extends MongoRepository<RegistroUsuarioDto, String>{

	public RegistroUsuarioDto findOneByNombreUsuario(String nombreUsuario);

	public RegistroUsuarioDto findOneByDocumento(String documento);

	public RegistroUsuarioDto findOneByNombreApellido(String nombreApellido);

}
