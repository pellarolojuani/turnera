package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.TutorDto;
import ar.uba.fi.mongo.repository.TutorRepository;

@Service
public class MongoDBTutorFacade {
	@Autowired
	private TutorRepository tutorRepository;

	public void crearTutor(TutorDto tutor) {
		try {
			tutorRepository.save(tutor);
		} catch (MongoException ex) {
		}
	}

	public void editarTutor(TutorDto tutor) {
		try {
			tutorRepository.save(tutor);
		} catch (MongoException ex) {
		}
	}

	public void removerTutor(TutorDto tutor) {
		try {
			tutorRepository.delete(tutor);
		} catch (MongoException ex) {
		}
	}

	public List<TutorDto> getAllTutors() {
		try {
			return tutorRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}

	public TutorDto getTutorById(String id) {
		try {
			return tutorRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}

	public TutorDto getTutorsByDni(Long dni) {
		try {
			return tutorRepository.findOneByDni(dni);
		} catch (MongoException ex) {
		}
		return null;
	}
}
