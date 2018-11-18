package ar.uba.fi.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.TurnosDto;
import ar.uba.fi.mongo.facade.MongoDBTurnosFacade;

@Service
public class TurnosFacade {
	@Autowired
	private MongoDBTurnosFacade turnoFacade;

	public void crearTurno(TurnosDto turno) {
		turnoFacade.crearTurno(turno);
	}

	public void editarTurno(TurnosDto turno) {
		turnoFacade.editarTurno(turno);
	}

	public void removerTurno(TurnosDto turno) {
		turnoFacade.removerTurno(turno);
	}

	public List<TurnosDto> getAllTurnos() {
		return turnoFacade.getAllTurnos();
	}

	public TurnosDto getTurnoById(String id) {
		return turnoFacade.getTurnoById(id);
	}

	public List<TurnosDto> getTurnosByDate(Date fecha) {
		return turnoFacade.getTurnosByDate(fecha);
	}
	
	public List<TurnosDto> getTurnosLibresByMedicoAndDate(MedicoDto medico, Date date) {
		return turnoFacade.getTurnosLibresByMedicoAndDate(medico, date);
	}

	public List<TurnosDto> getTurnosBetweenDates(Date inicio, Date fin, String ocupado) {
		return turnoFacade.getTurnosBetweenDates(inicio, fin, ocupado);
	}

	public List<TurnosDto> getTurnosByEspecialidad(EspecialidadDto especialidad) {
		return turnoFacade.getTurnosByEspecialidad(especialidad);
	}

	public List<TurnosDto> getTurnosByEstado(String estado) {
		return turnoFacade.getTurnosByEstado(estado);
	}

	public List<TurnosDto> getTurnosByMedico(MedicoDto medico) {
		return turnoFacade.getTurnosByMedico(medico);
	}
	
	public List<TurnosDto> getTurnosByMedicoAndEstado(MedicoDto medico, Boolean estado) {
		return turnoFacade.getTurnosByMedicoAndEstado(medico, estado);
	}
	
	public List<TurnosDto> getTurnosByMedicoAndEstadoAndEspecialidadAndFecha(MedicoDto medico, Boolean estado, EspecialidadDto especialidad, Date fecha) {
		return turnoFacade.getTurnosByMedicoAndEstadoAndEspecialidadAndFecha(medico, estado,especialidad, fecha );
	}
	
	public List<TurnosDto> getTurnosByMedicoAndEstadoAndEspecialidad(MedicoDto medico, Boolean estado, EspecialidadDto especialidad) {
		return turnoFacade.getTurnosByMedicoAndEstadoAndEspecialidad(medico, estado,especialidad);
	}
	
	public List<TurnosDto> getTurnosByPacienteAndEstadoIsTrue(PacienteDto paciente) {
		return turnoFacade.getTurnosByPacienteAndEstadoIsTrue(paciente);
	}
	
}
