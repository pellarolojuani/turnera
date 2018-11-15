package ar.uba.fi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.ResultadoDto;
import ar.uba.fi.dto.TurnosDto;
import ar.uba.fi.facade.EspecialidadFacade;
import ar.uba.fi.facade.MedicoFacade;
import ar.uba.fi.facade.PacientesFacade;
import ar.uba.fi.facade.TurnosFacade;
import ar.uba.fi.util.DateUtil;

@Controller
public class PacienteController {
	@Autowired
	private TurnosFacade turnosFacade;
	
	@Autowired
	private PacientesFacade pacientesFacade;

	@Autowired
	private EspecialidadFacade especialidadFacade;
	
	@Autowired
	private MedicoFacade medicoFacade;

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
		
		ObjectId id= new ObjectId("5bec9f5e401cf3214078550e");   
		
		PacienteDto pacienteDto = pacientesFacade.getPacienteById(id.toString());
		
		//EspecialidadDto esp1 = new EspecialidadDto("PSI", "Pisiquiatria");
	//	especialidadFacade.crearEspecialidad(esp1);
		
		//MedicoDto medico = new MedicoDto(esp1, "Medico de Prueba 1", "45678");
	//	medicoFacade.crearMedico(medico);
		
		
		//TurnosDto turno1 = new TurnosDto(new Date(),esp1, "Aprobado", medico, pacienteDto, "100");
		
		//turnosFacade.crearTurno(turno1);

		//TODO aca cargar las especialidades que vienen de la db
//		EspecialidadDto esp1 = new EspecialidadDto("CAR", "Cardiologia");
//		EspecialidadDto esp2 = new EspecialidadDto("PSI", "Psiquiatria");
		
//		Map<String, String> especialidades = new HashMap<String, String>();
//		especialidades.put(esp1.getCodigo(), esp1.getDescripcion());
//		especialidades.put(esp2.getCodigo(), esp2.getDescripcion());
		
		//especialidades = especialidadFacade.getAllEspecialidads();
		
		//medicos = medicoFacade.getAllMedicos();

		ModelAndView mv = new ModelAndView("solicitarTurno");
		
		//TODO las especialidades y medicos se cargar por ajax, pero esto tiene que estar!!!!
		List<EspecialidadDto> especialidades = new ArrayList<EspecialidadDto>();
		List<MedicoDto> medicos = new ArrayList<MedicoDto>();
		mv.addObject("especialidades", especialidades);
		mv.addObject("medicos", medicos);

		return mv;
	}

	@RequestMapping(value = "/misTurnos", method = RequestMethod.GET)
	public String verTurnos(ModelMap model) {
		String name = getLoggedInUserName(model);

		// TODO aca deberia ir a la base y recuperar los turnos.
		// definir si muestra o no lo turnos anulados(porque le podemos poner estados)
//		TurnosDto turno1 = new TurnosDto(new Date(), "Médico Clínico", "Aprobado", "Ezequiel Bergamo");
//		turno1.setId("1");
//		TurnosDto turno2 = new TurnosDto(new Date(), "Cardiología", "Rechazado", "Eze Bergamo");
//		turno2.setId("2");
		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		List<EspecialidadDto> especialidades = new ArrayList<EspecialidadDto>();
		List<MedicoDto> medicos = new ArrayList<MedicoDto>();
		List<PacienteDto> pacientes = new ArrayList<PacienteDto>();
		
//		turnos.add(turno1);
//		turnos.add(turno2);
		
		especialidades = especialidadFacade.getAllEspecialidads();
		
		medicos = medicoFacade.getAllMedicos();
		
		pacientes = pacientesFacade.getAllPacientes();
		
		
		turnos = turnosFacade.getAllTurnos();	

		model.put("turnos", turnos);
		return "misTurnos";
	}
	
	@RequestMapping(value = "/buscarTurnosDisponibles", method = RequestMethod.GET)
	public @ResponseBody List<TurnosDto> buscarTurnosDisponibles(@RequestParam(name = "especialidad") String especialidad,
			@RequestParam(name = "fechaTurno") String fechaTurno, @RequestParam(name = "medico") String medico ) {
		
		//TODO aca voy a la base y traigo los turnos segun los parametros.
		
		EspecialidadDto esp1 = especialidadFacade.getEspecialidadById("5beca7db401cf325c03e1808");
		MedicoDto medico2 = medicoFacade.getMedicoById("5beca7dd401cf325c03e1809");
		TurnosDto turno1 = new TurnosDto(DateUtil.stringToDate(fechaTurno, "dd/MM/yyyy"), esp1, medico2);
		turno1.setId("1");
		turno1.setNumeroComprobante("101");
		turno1.setFechaString(fechaTurno + " 11:00");
		TurnosDto turno2 = new TurnosDto(DateUtil.stringToDate(fechaTurno, "dd/MM/yyyy"), esp1, "Rechazado", medico2);
		turno2.setId("2");
		turno1.setNumeroComprobante("102");
		turno2.setFechaString(fechaTurno + " 11:20");
		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		turnos.add(turno1);
		turnos.add(turno2);

		return turnos;
	}
	
	@RequestMapping(value = "/solicitar", method = RequestMethod.GET)
	public @ResponseBody ResultadoDto guardarTurno(@RequestParam int id) {

		// TODO voy a la base y le asociado el turno al paciente GUARDAR ID DE PACIENTE EN LA SESSION
		System.out.println("voy a sacar el turno id:" + id);
		ResultadoDto resultado = new ResultadoDto(true, "101");
		
		return resultado;
	}
	
	@RequestMapping(value = "/anularTurno", method = RequestMethod.GET)
	public @ResponseBody ResultadoDto anularTurno(@RequestParam String id) {

		// TODO aca voy a la base y le cambio el estado al turno.
		// inyectar servicios
		System.out.println("voy a anular el turno id:" + id);
		
		TurnosDto turno = turnosFacade.getTurnoById(id);
		turno.setEstado("???");
		turnosFacade.editarTurno(turno);
		
		Boolean resultadoAnularTurno = true;
		//aca le pasamos el número de comprobante
		ResultadoDto resultado = new ResultadoDto(true, "101");
		
		return resultado;
	}

}
