package ar.uba.fi.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.UsuarioDto;

public interface MedicoRepository  extends MongoRepository<MedicoDto, String>{

	public MedicoDto findOneByNombre(String nombre);

	public List<MedicoDto> findByEspecialidad(EspecialidadDto especialidad);

	public MedicoDto findByUsuario(UsuarioDto usuario);

}
