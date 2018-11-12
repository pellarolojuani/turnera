package ar.uba.fi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.TurnosDto;
import ar.uba.fi.util.DateUtil;

@Controller
public class PacienteController {

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
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
	
	@RequestMapping(value = "/buscarTurnosDisponibles", method = RequestMethod.GET)
	public @ResponseBody List<TurnosDto> buscarTurnosDisponibles(@RequestParam(name = "especialidad") String especialidad,
			@RequestParam(name = "fechaTurno") String fechaTurno) {
		
		//TODO aca voy a la base y traigo los turnos segun los parametros.
		TurnosDto turno1 = new TurnosDto(DateUtil.stringToDate(fechaTurno, "dd/MM/yyyy"), especialidad,  "Ezequiel Bergamo");
		turno1.setId("1");
		turno1.setFechaString(fechaTurno + " 11:00");
		TurnosDto turno2 = new TurnosDto(DateUtil.stringToDate(fechaTurno, "dd/MM/yyyy"), especialidad, "Rechazado", "Eze Bergamo");
		turno2.setId("2");
		turno2.setFechaString(fechaTurno + " 11:20");
		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		turnos.add(turno1);
		turnos.add(turno2);

		return turnos;
	}
	
	@RequestMapping(value = "/solicitar", method = RequestMethod.GET)
	public @ResponseBody Boolean guardarTurno(@RequestParam int id) {

		// TODO voy a la base y le asociado el turno al paciente GUARDAR ID DE PACIENTE EN LA SESSION
		System.out.println("voy a sacar el turno id:" + id);
		Boolean resultadoGuardarTurno = false;
		
		return resultadoGuardarTurno;
	}
	
	@RequestMapping(value = "/anularTurno", method = RequestMethod.GET)
	public @ResponseBody Boolean anularTurno(@RequestParam int id) {

		// TODO aca voy a la base y le cambio el estado al turno.
		// inyectar servicios
		System.out.println("voy a anular el turno id:" + id);
		Boolean resultadoAnularTurno = true;
		
		return resultadoAnularTurno;
		
	}

}
