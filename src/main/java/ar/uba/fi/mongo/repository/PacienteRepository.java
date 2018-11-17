package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.UsuarioDto;

public interface PacienteRepository  extends MongoRepository<PacienteDto, String>{

	public PacienteDto findOneByDocumento(String documento);

	public PacienteDto findOneByNumeroAfiliado(String numeroAfiliado);
	
	public PacienteDto findOneByUsuario(UsuarioDto usuario);

}
