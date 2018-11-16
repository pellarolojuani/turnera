package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.TurnosTodosDto;
import ar.uba.fi.mongo.repository.TurnoTodosRepository;

@Service
public class MongoDBTurnosTodosRepository {
	@Autowired
	private TurnoTodosRepository turnoTodosRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	public void crearTurno(TurnosTodosDto turno) {
		try {
			turnoTodosRepository.save(turno);
		} catch (MongoException ex) {
		}
	}

	public void editarTurno(TurnosTodosDto turno) {
		try {
			turnoTodosRepository.save(turno);
		} catch (MongoException ex) {
		}
	}

	public void removerTurno(TurnosTodosDto turno) {
		try {
			turnoTodosRepository.delete(turno);
		} catch (MongoException ex) {
		}
	}

	public List<TurnosTodosDto> getAllTurnos() {
		try {
			return turnoTodosRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}

	public TurnosTodosDto getTurnoById(String id) {
		try {
			return turnoTodosRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}
	
//	public List<TurnosDto> getTurnosBetweenDates(Date inicio, Date fin) {
//		Criteria cri = null;
//		if (inicio != null) {
//			cri = Criteria.where("fecha").gte(inicio);
//		}
//		if (cri == null) {
//			if (fin != null) {
//				cri = Criteria.where("fecha").lte(fin);
//			}
//		} else {
//			if (fin != null) {
//				cri = cri.lte(fin);
//			}
//		}
//		Query query;
//		if (cri != null) {
//			query = new Query(cri);
//		} else {
//			query = new Query();
//		}
//		return mongoTemplate.find(query, TurnosDto.class);
//	}
	
	
//	private String id;
//	private String medico;
//	private String paciente;
//	private String fecha;
//	private String hora;
//	private String minutos;
//	private Boolean libre;
	
	public List<TurnosTodosDto> getTurnosTodosByMedico(String medico) {
		try {
			return turnoTodosRepository.findByMedico(medico);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<TurnosTodosDto> getTurnosLibresByMedico(String medico) {
		try {
			return turnoTodosRepository.findByMedicoAndLibreIsTrue(medico);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<TurnosTodosDto> getTurnosOcupadosByMedico(String medico) {
		try {
			return turnoTodosRepository.findByMedicoAndLibreIsFalse(medico);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosTodosDto> getTurnosByEspecialidad(String especialidad) {
		try {
			return turnoTodosRepository.findByEspecialidad(especialidad);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<TurnosTodosDto> getTurnosLibresByMedicoBetween(String medico, String fecha, Integer desde, Integer hasta) {
		try {
			return turnoTodosRepository.findByMedicoAndFechaAndHoraBetween(medico, fecha, desde, hasta);
		} catch (MongoException ex) {
		}
		return null;
	}
}
