package ar.uba.fi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ar.uba.fi.dto.TurnosDto;

@Controller
public class PacienteController {

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/solicitarTurno", method = RequestMethod.GET)
	public String initSolicitarTurno(ModelMap model) {
		String name = getLoggedInUserName(model);
		// model.put("todos", service.retrieveTodos(name));
		return "solicitarTurno";
	}

	@RequestMapping(value = "/misTurnos", method = RequestMethod.GET)
	public String verTurnos(ModelMap model) {
		String name = getLoggedInUserName(model);

		// TODO aca deberia ir a la base y recuperar los turnos.
		// definir si muestra o no lo turnos anulados(porque le podemos poner estados)
		TurnosDto turno1 = new TurnosDto(new Date(), "Médico Clínico", "Aprobado", "Ezequiel Bergamo");
		TurnosDto turno2 = new TurnosDto(new Date(), "Cardiología", "Rechazado", "Eze Bergamo");
		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		turnos.add(turno1);
		turnos.add(turno2);

		model.put("turnos", turnos);
		return "misTurnos";
	}

	@RequestMapping(value = "/anularTurno", method = RequestMethod.GET)
	public String anularTurno(@RequestParam int id) {

		// TODO aca voy a la base y le cambio el estado al turno.
		// inyectar servicios
		System.out.println("voy a anular el turno id:" + id);
		return "redirect:/misTurnos";
	}

}
