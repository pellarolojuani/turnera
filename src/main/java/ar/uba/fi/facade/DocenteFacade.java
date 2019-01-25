package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.DocenteDto;
import ar.uba.fi.mongo.facade.MongoDBDocenteFacade;

@Service
public class DocenteFacade {

	@Autowired
	private MongoDBDocenteFacade docenteFacade;

	public void crearDocente(DocenteDto docente) {
		docenteFacade.crearDocente(docente);
	}

	public void editarDocente(DocenteDto docente) {
		docenteFacade.editarDocente(docente);
	}

	public void removerDocente(DocenteDto docente) {
		docenteFacade.removerDocente(docente);
	}

	public List<DocenteDto> getAllDocentes() {
		return docenteFacade.getAllDocentes();
	}

	public DocenteDto getDocenteById(String id) {
		return docenteFacade.getDocenteById(id);
	}

	public DocenteDto getDocentesByDni(Long dni) {
		return docenteFacade.getDocentesByDni(dni);
	}

	public List<DocenteDto> getDocentesByMateria(String materia) {
		return docenteFacade.getDocentesByMateria(materia);
	}

	public DocenteDto getDocenteByAnio(Integer anio) {
		return docenteFacade.getDocenteByAnio(anio);
	}
}
