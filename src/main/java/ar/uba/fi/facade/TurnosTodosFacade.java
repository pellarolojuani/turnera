package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.TurnosTodosDto;
import ar.uba.fi.mongo.facade.MongoDBTurnosTodosRepository;

@Service
public class TurnosTodosFacade {
	@Autowired
	private MongoDBTurnosTodosRepository turnosTodosRepsitory;

	public void crearTurno(TurnosTodosDto turno) {
		turnosTodosRepsitory.crearTurno(turno);
	}

	public void editarTurno(TurnosTodosDto turno) {
		turnosTodosRepsitory.editarTurno(turno);
	}

	public void removerTurno(TurnosTodosDto turno) {
		turnosTodosRepsitory.removerTurno(turno);
	}

	public List<TurnosTodosDto> getAllTurnos() {
		return turnosTodosRepsitory.getAllTurnos();
	}

	public TurnosTodosDto getTurnoById(String id) {
		return turnosTodosRepsitory.getTurnoById(id);
	}

	public List<TurnosTodosDto> getTurnosTodosByMedico(String medico) {
		return turnosTodosRepsitory.getTurnosTodosByMedico(medico);
	}

	public List<TurnosTodosDto> getTurnosLibresByMedico(String medico) {
		return turnosTodosRepsitory.getTurnosLibresByMedico(medico);
	}

	public List<TurnosTodosDto> getTurnosOcupadosByMedico(String medico) {
		return turnosTodosRepsitory.getTurnosOcupadosByMedico(medico);
	}

	public List<TurnosTodosDto> getTurnosByEspecialidad(String especialidad) {
		return turnosTodosRepsitory.getTurnosByEspecialidad(especialidad);
	}

	public List<TurnosTodosDto> getTurnosLibresByMedicoEntreHorarios(String medico, String fecha, Integer desde, Integer hasta) {
		return turnosTodosRepsitory.getTurnosLibresByMedicoBetween(medico, fecha, desde, hasta);
	}
}
