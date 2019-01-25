package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.AlumnoDto;
import ar.uba.fi.mongo.facade.MongoDBAlumnoFacade;

@Service
public class AlumnoFacade {

	@Autowired
	private MongoDBAlumnoFacade alumnoFacade;

	public void crearAlumno(AlumnoDto alumno) {
		alumnoFacade.crearAlumno(alumno);
	}

	public void editarAlumno(AlumnoDto alumno) {
		alumnoFacade.editarAlumno(alumno);
	}

	public void removerAlumno(AlumnoDto alumno) {
		alumnoFacade.removerAlumno(alumno);
	}

	public List<AlumnoDto> getAllAlumnos() {
		return alumnoFacade.getAllAlumnos();
	}

	public AlumnoDto getAlumnoById(String id) {
		return alumnoFacade.getAlumnoById(id);
	}

	public AlumnoDto getAlumnosByDni(Long dni) {
		return alumnoFacade.getAlumnosByDni(dni);
	}

	public List<AlumnoDto> getAlumnosByDocente(String docente) {
		return alumnoFacade.getAlumnosByDocente(docente);
	}

	public List<AlumnoDto> getAlumnosByTutor(String tutor) {
		return alumnoFacade.getAlumnosByTutor(tutor);
	}
}
