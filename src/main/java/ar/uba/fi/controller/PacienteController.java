package ar.uba.fi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import ar.uba.fi.dto.ComprobanteDto;
import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.ResultadoDto;
import ar.uba.fi.dto.TurnosDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.ComprobanteFacade;
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

	@Autowired
	private ComprobanteFacade comprobanteFacade;

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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/solicitarTurno", method = RequestMethod.GET)
	public ModelAndView initSolicitarTurno(ModelMap model) {
		String name = getLoggedInUserName(model);

		ModelAndView mv = new ModelAndView("solicitarTurno");

		// TODO las especialidades y medicos se cargar por ajax, pero esto tiene que
		// estar!!!!
		List<EspecialidadDto> especialidades = new ArrayList<EspecialidadDto>();
		List<MedicoDto> medicos = new ArrayList<MedicoDto>();
		mv.addObject("especialidades", especialidades);
		mv.addObject("medicos", medicos);

		return mv;
	}

	@RequestMapping(value = "/misTurnos", method = RequestMethod.GET)
	public String verTurnos(ModelMap model, HttpServletRequest request) {
		String name = getLoggedInUserName(model);

		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuario");
		PacienteDto paciente = pacientesFacade.getPacienteByUsuario(usuario);

		turnos = turnosFacade.getTurnosByPacienteAndEstadoIsTrue(paciente);
		this.turnosAMostrarEnMisTurnos(turnos);

		model.put("turnos", turnos);
		return "misTurnos";
	}

	@RequestMapping(value = "/buscarTurnosDisponibles", method = RequestMethod.GET)
	public @ResponseBody List<TurnosDto> buscarTurnosDisponibles(
			@RequestParam(name = "especialidad") String especialidad,
			@RequestParam(name = "fechaTurno") String fechaTurno, @RequestParam(name = "medico") String medicoId) {

		List<TurnosDto> turnos = null;


		EspecialidadDto esp = especialidadFacade.getEspecialidadByCodigo(especialidad);
		MedicoDto medico = medicoFacade.getMedicoById(medicoId);

		if (fechaTurno != null && !fechaTurno.isEmpty()) {
			Date fechaDate = DateUtil.stringToDate(fechaTurno, "dd/MM/yyyy");
			turnos = turnosFacade.getTurnosByMedicoAndEstadoAndEspecialidadAndFecha(medico, false, esp, fechaDate);
		} else {
			turnos = turnosFacade.getTurnosByMedicoAndEstadoAndEspecialidad(medico, false, esp);
		}

		return turnos;
	}

	@RequestMapping(value = "/solicitar", method = RequestMethod.GET)
	public @ResponseBody ResultadoDto guardarTurno(@RequestParam String id, HttpServletRequest request) {

		UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuario");
		PacienteDto paciente = pacientesFacade.getPacienteByUsuario(usuario);

		ComprobanteDto comprobante = comprobanteFacade.getMaxComprobante();
		ComprobanteDto comprobanteNuevo = null;

		if (comprobante == null) {
			comprobanteNuevo = new ComprobanteDto(1);
		} else {
			Integer contador = comprobante.getContador() + 1;
			comprobanteNuevo = new ComprobanteDto(contador);
		}
		comprobanteFacade.crearComprobante(comprobanteNuevo);
		TurnosDto turno = turnosFacade.getTurnoById(id);
		turno.setEstado(true);
		turno.setPaciente(paciente);
		turno.setNumeroComprobante(comprobanteNuevo.getContador().toString());
		turno.setNumeroComprobanteAnulado(null);
		turnosFacade.editarTurno(turno);

		ResultadoDto resultado = new ResultadoDto(true, turno.getNumeroComprobante());

		return resultado;
	}

	@RequestMapping(value = "/anularTurno", method = RequestMethod.GET)
	public @ResponseBody ResultadoDto anularTurno(@RequestParam String id, HttpServletRequest request) {

		UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuario");
		// PacienteDto paciente = pacientesFacade.getPacienteByUsuario(usuario);

		ComprobanteDto comprobante = comprobanteFacade.getMaxComprobante();
		ComprobanteDto comprobanteNuevo = null;

		if (comprobante == null) {
			comprobanteNuevo = new ComprobanteDto(1);
		} else {
			Integer contador = comprobante.getContador() + 1;
			comprobanteNuevo = new ComprobanteDto(contador);
		}
		comprobanteFacade.crearComprobante(comprobanteNuevo);
		TurnosDto turno = turnosFacade.getTurnoById(id);
		turno.setEstado(false);
		turno.setPaciente(null);
		turno.setNumeroComprobanteAnulado(comprobanteNuevo.getContador().toString());
		turnosFacade.editarTurno(turno);

		ResultadoDto resultado = new ResultadoDto(true, turno.getNumeroComprobanteAnulado());

		return resultado;
	}

	private void turnosAMostrarEnMisTurnos(List<TurnosDto> turnos) {

		for (TurnosDto turno : turnos) {

			turno.setFechaString(DateUtil.dateToStringHoraMinuto(turno.getFecha(), "dd/MM/yyyy", turno.getHora(),
					turno.getMinutos()));

			if (DateUtil.isFechaAnteriorAFechaHoy(DateUtil.stringToDate(turno.getFechaString(), "dd/MM/yyyy HH:mm"),
					new Date())) {
				// En este caso la consulta ya fue realizada.
				turno.setEstadoMostrar("Consumido");
				turno.setMostrarBotonAnular(false);
			} else {
				turno.setEstadoMostrar("Pendiente");
				turno.setMostrarBotonAnular(true);
			}

		}
	}

}
