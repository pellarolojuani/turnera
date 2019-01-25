package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.DocenteDto;
import ar.uba.fi.mongo.repository.DocenteRepository;

@Service
public class MongoDBDocenteFacade {
	@Autowired
	private DocenteRepository docenteRepository;

	public void crearDocente(DocenteDto docente) {
		try {
			docenteRepository.save(docente);
		} catch (MongoException ex) {
		}
	}

	public void editarDocente(DocenteDto docente) {
		try {
			docenteRepository.save(docente);
		} catch (MongoException ex) {
		}
	}

	public void removerDocente(DocenteDto docente) {
		try {
			docenteRepository.delete(docente);
		} catch (MongoException ex) {
		}
	}

	public List<DocenteDto> getAllDocentes() {
		try {
			return docenteRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}

	public DocenteDto getDocenteById(String id) {
		try {
			return docenteRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}

	public DocenteDto getDocentesByDni(Long dni) {
		try {
			return docenteRepository.findOneByDni(dni);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<DocenteDto> getDocentesByMateria(String materia) {
		try {
			return docenteRepository.findByMateria(materia);
		} catch (MongoException ex) {
		}
		return null;
	}

	public DocenteDto getDocenteByAnio(Integer anio) {
		try {
			return docenteRepository.findOneByAnio(anio);
		} catch (MongoException ex) {
		}
		return null;
	}
}
