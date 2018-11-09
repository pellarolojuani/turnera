package ar.uba.fi.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.PacienteDto;

public interface PacienteRepository  extends MongoRepository<PacienteDto, String>{

	public PacienteDto findOneByDocumento(String documento);

	public PacienteDto findOneByNumeroAfiliado(String numeroAfiliado);

}
