package ar.uba.fi.mongo.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.TurnosDto;
import ar.uba.fi.mongo.repository.TurnoRepository;

@Service
public class MongoDBTurnosFacade {
	@Autowired
	private TurnoRepository turnoRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	public void crearTurno(TurnosDto turno) {
		try {
			turnoRepository.save(turno);
		} catch (MongoException ex) {
		}
	}

	public void editarTurno(TurnosDto turno) {
		try {
			turnoRepository.save(turno);
		} catch (MongoException ex) {
		}
	}

	public void removerTurno(TurnosDto turno) {
		try {
			turnoRepository.delete(turno);
		} catch (MongoException ex) {
		}
	}

	public List<TurnosDto> getAllTurnos() {
		try {
			return turnoRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}

	public TurnosDto getTurnoById(String id) {
		try {
			return turnoRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosByDate(Date fecha) {
		try {
			// TODO revisar que onda el date!!!
			return turnoRepository.findByFecha(fecha);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosBetweenDates(Date inicio, Date fin, String ocupado) {
		Criteria cri = null;
		if (inicio != null) {
			cri = Criteria.where("fecha").gte(inicio);
		}
		if (cri == null) {
			if (fin != null) {
				cri = Criteria.where("fecha").lte(fin);
			}
		} else {
			if (fin != null) {
				cri = cri.lte(fin);
			}
		}
		if("on".equals(ocupado)) {
			cri = cri.and("estado").is(true);
		}
		
		Query query;
		if (cri != null) {
			query = new Query(cri);
		} else {
			query = new Query();
		}
		return mongoTemplate.find(query, TurnosDto.class);
	}
	
	
	public List<TurnosDto> getTurnosBetweenDatesAndMedico(Date inicio, Date fin, String ocupado, MedicoDto medico) {
		Criteria cri = null;
		if (inicio != null) {
			cri = Criteria.where("fecha").gte(inicio);
		}
		if (cri == null) {
			if (fin != null) {
				cri = Criteria.where("fecha").lte(fin);
			}
		} else {
			if (fin != null) {
				cri = cri.lte(fin);
			}
		}
		if("on".equals(ocupado)) {
			cri = cri.and("estado").is(true);
		}
		
		if(medico != null) {
			cri = cri.and("medico").in(medico);
		}
		
		Query query;
		if (cri != null) {
			query = new Query(cri);
		} else {
			query = new Query();
		}
		return mongoTemplate.find(query, TurnosDto.class);
	}

	public List<TurnosDto> getTurnosByEspecialidad(EspecialidadDto especialidad) {
		try {
			return turnoRepository.findByEspecialidad(especialidad);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosByEstado(String estado) {
		try {
			return turnoRepository.findByEstado(estado);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosByMedico(MedicoDto medico) {
		try {
			return turnoRepository.findByMedico(medico);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosByMedicoAndEstado(MedicoDto medico, Boolean estado) {
		try {
			if (estado) {
				return turnoRepository.findByMedicoAndEstadoIsTrue(medico);
			}
			return turnoRepository.findByMedicoAndEstadoIsFalse(medico);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosByMedicoAndEstadoAndEspecialidadAndFecha(MedicoDto medico, Boolean estado,
			EspecialidadDto especialidad, Date fecha) {
		try {
			if (estado) {
				return turnoRepository.findByMedicoAndFechaAndAndEspecialidadAndEstadoIsTrue(medico, fecha,
						especialidad);
			}
			return turnoRepository.findByMedicoAndFechaAndAndEspecialidadAndEstadoIsFalse(medico, fecha, especialidad);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosLibresByMedicoAndDate(MedicoDto medico, Date date) {
		try {
			return turnoRepository.findByMedicoAndFechaAndEstadoIsFalse(medico, date);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<TurnosDto> getTurnosByPacienteAndEstadoIsTrue(PacienteDto paciente) {
		try {
			return turnoRepository.findByPacienteAndAndEstadoIsTrue(paciente);
		} catch (MongoException ex) {
		}
		return null;
	}
		
	public List<TurnosDto> getTurnosByMedicoAndEstadoAndEspecialidad(MedicoDto medico, Boolean estado,
			EspecialidadDto especialidad) {
		try {
			if (estado) {
				return turnoRepository.findByMedicoAndEspecialidadAndEstadoIsTrue(medico,
						especialidad);
			}
			return turnoRepository.findByMedicoAndEspecialidadAndEstadoIsFalse(medico, especialidad);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	

}
