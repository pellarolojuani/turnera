package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.AlumnoDto;
import ar.uba.fi.mongo.repository.AlumnoRepository;

@Service
public class MongoDBAlumnoFacade {
	@Autowired
	private AlumnoRepository alumnoRepository;

	public void crearAlumno(AlumnoDto alumno) {
		try {
			alumnoRepository.save(alumno);
		} catch (MongoException ex) {
		}
	}

	public void editarAlumno(AlumnoDto alumno) {
		try {
			alumnoRepository.save(alumno);
		} catch (MongoException ex) {
		}
	}

	public void removerAlumno(AlumnoDto alumno) {
		try {
			alumnoRepository.delete(alumno);
		} catch (MongoException ex) {
		}
	}

	public List<AlumnoDto> getAllAlumnos() {
		try {
			return alumnoRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}

	public AlumnoDto getAlumnoById(String id) {
		try {
			return alumnoRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}

	public AlumnoDto getAlumnosByDni(Long dni) {
		try {
			return alumnoRepository.findOneByDni(dni);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<AlumnoDto> getAlumnosByDocente(String docente) {
		try {
			return alumnoRepository.findByDocente(docente);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<AlumnoDto> getAlumnosByTutor(String tutor) {
		try {
			return alumnoRepository.findByTutor(tutor);
		} catch (MongoException ex) {
		}
		return null;
	}
}
