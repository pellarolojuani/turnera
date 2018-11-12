package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.mongo.repository.EspecialidadRepository;

@Service
public class MongoDBEspecialidadFacade {
	@Autowired
	private EspecialidadRepository especialidadRepository;
	
	public void crearEspecialidad(EspecialidadDto especialidad) {
		try {
			especialidadRepository.save(especialidad);
		} catch (MongoException ex) {
		}
	}

	public void editarEspecialidad(EspecialidadDto especialidad) {
		try {
			especialidadRepository.save(especialidad);
		} catch (MongoException ex) {
		}
	}

	public void removerEspecialidad(EspecialidadDto especialidad) {
		try {
			especialidadRepository.delete(especialidad);
		} catch (MongoException ex) {
		}
	}

	public List<EspecialidadDto> getAllEspecialidads() {
		try {
			return especialidadRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public EspecialidadDto getEspecialidadById(String id) {
		try {
			return especialidadRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public EspecialidadDto getEspecialidadByCodigo(String codigo) {
		try {
			return especialidadRepository.findOneByCodigo(codigo);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public EspecialidadDto getEspecialidadByDescripcion(String descripcion) {
		try {
			return especialidadRepository.findOneByDescripcion(descripcion);
		} catch (MongoException ex) {
		}
		return null;
	}
}
