package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.TutorDto;
import ar.uba.fi.mongo.facade.MongoDBTutorFacade;

@Service
public class TutorFacade {
	@Autowired
	private MongoDBTutorFacade tutorFacade;

	public void crearTutor(TutorDto tutor) {
		tutorFacade.crearTutor(tutor);
	}

	public void editarTutor(TutorDto tutor) {
		tutorFacade.editarTutor(tutor);
	}

	public void removerTutor(TutorDto tutor) {
		tutorFacade.removerTutor(tutor);
	}

	public List<TutorDto> getAllTutors() {
		return tutorFacade.getAllTutors();
	}

	public TutorDto getTutorById(String id) {
		return tutorFacade.getTutorById(id);
	}

	public TutorDto getTutorsByDni(Long dni) {
		return tutorFacade.getTutorsByDni(dni);
	}
}
