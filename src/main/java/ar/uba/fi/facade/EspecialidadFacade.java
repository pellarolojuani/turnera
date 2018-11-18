package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.mongo.facade.MongoDBEspecialidadFacade;

@Service
public class EspecialidadFacade {
	@Autowired
	private MongoDBEspecialidadFacade especialidadFacade;

	public void crearEspecialidad(EspecialidadDto especialidad) {
		especialidadFacade.crearEspecialidad(especialidad);
	}

	public void editarEspecialidad(EspecialidadDto especialidad) {
		especialidadFacade.editarEspecialidad(especialidad);
	}

	public void removerEspecialidad(EspecialidadDto especialidad) {
		especialidadFacade.removerEspecialidad(especialidad);
	}

	public List<EspecialidadDto> getAllEspecialidads() {
		return especialidadFacade.getAllEspecialidads();
	}

	public EspecialidadDto getEspecialidadById(String id) {
		return especialidadFacade.getEspecialidadById(id);
	}

	public EspecialidadDto getEspecialidadByCodigo(String codigo) {
		return especialidadFacade.getEspecialidadByCodigo(codigo);
	}

	public EspecialidadDto getEspecialidadByDescripcion(String descripcion) {
		return especialidadFacade.getEspecialidadByDescripcion(descripcion);
	}
}
