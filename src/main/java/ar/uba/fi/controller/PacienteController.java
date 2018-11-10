package ar.uba.fi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.uba.fi.dto.EspecialidadDto;
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
	public ModelAndView initSolicitarTurno(ModelMap model) {
		String name = getLoggedInUserName(model);

		//TODO aca cargar las especialidades que vienen de la db
		EspecialidadDto esp1 = new EspecialidadDto("CAR", "Cardiologia");
		EspecialidadDto esp2 = new EspecialidadDto("PSI", "Psiquiatria");

		Map<String, String> especialidades = new HashMap<String, String>();
		especialidades.put(esp1.getCodigo(), esp1.getDescripcion());
		especialidades.put(esp2.getCodigo(), esp2.getDescripcion());

		ModelAndView mv = new ModelAndView("solicitarTurno");
		mv.addObject("especialidades", especialidades);

		return mv;
	}

	@RequestMapping(value = "/misTurnos", method = RequestMethod.GET)
	public String verTurnos(ModelMap model) {
		String name = getLoggedInUserName(model);

		// TODO aca deberia ir a la base y recuperar los turnos.
		// definir si muestra o no lo turnos anulados(porque le podemos poner estados)
		TurnosDto turno1 = new TurnosDto(new Date(), "Médico Clínico", "Aprobado", "Ezequiel Bergamo");
		turno1.setId("1");
		TurnosDto turno2 = new TurnosDto(new Date(), "Cardiología", "Rechazado", "Eze Bergamo");
		turno2.setId("2");
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
	
	@RequestMapping(value = "/buscarTurnosDisponibles", method = RequestMethod.GET)
	public void buscarTurnosDisponibles(@RequestParam("fechaTurno") String fechaTurno,
            @RequestParam("especialidad") String especialidad, ModelMap model) {
		
		TurnosDto turno1 = new TurnosDto(new Date(), "Médico Clínico", "Aprobado", "Ezequiel Bergamo");
		turno1.setId("1");
		TurnosDto turno2 = new TurnosDto(new Date(), "Cardiología", "Rechazado", "Eze Bergamo");
		turno2.setId("2");
		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		turnos.add(turno1);
		turnos.add(turno2);

		model.put("turnos", turnos);
			
	//	return "redirect:/solicitarTurno";
	}
	
	@RequestMapping(value = "/solicitar", method = RequestMethod.GET)
	public String guardarTurno(@RequestParam int id) {

		// TODO aca voy a la base y le cambio el estado al turno.
		// inyectar servicios
		System.out.println("voy a anular el turno id:" + id);
		return "redirect:/solicitarTurno";
	}

}
