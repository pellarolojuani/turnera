package ar.uba.fi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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
import ar.uba.fi.util.AjaxResult;
import ar.uba.fi.util.DateUtil;

@Controller
public class MedicoController {

	@Autowired
	private TurnosFacade turnosFacade;
	@Autowired
	private EspecialidadFacade especialidadFacade;
	@Autowired
	private MedicoFacade medicoFacade;
	@Autowired
	private ComprobanteFacade comprobanteFacade;
	
	@Autowired
	private PacientesFacade pacientesFacade;


	@RequestMapping(value = "/verTurnos", method = RequestMethod.GET)
	public ModelAndView initSolicitarTurno(ModelMap model) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("verTurnos");
		return mv;
	}

	@RequestMapping(value = "/registrarTurnosInit", method = RequestMethod.GET)
	public ModelAndView initRegistrarTurnos(ModelMap model) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("registrarTurnos");

		return mv;
	}

	@RequestMapping(value = "/verProximosTurnos", method = RequestMethod.GET)
	public @ResponseBody List<TurnosDto> verProximosTurnos(@RequestParam(name = "fechaDesde") String fechaDesde,
			@RequestParam(name = "fechaHasta") String fechaHasta, @RequestParam(name = "ocupado") String ocupado, HttpServletRequest request) {

		UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuario");
		MedicoDto medico = medicoFacade.getMedicoByUsuario(usuario);

		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
		try {
			Date fechaDesdeDate = dt.parse(fechaDesde);
			Date fechaHastaDate = dt.parse(fechaHasta); 
			
			turnos = turnosFacade.getTurnosBetweenDatesAndMedico(fechaDesdeDate, fechaHastaDate, ocupado, medico);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		return turnos;
	}

	@RequestMapping(value = "/anularPorMedico", method = RequestMethod.GET)
	public @ResponseBody ResultadoDto anularPorMedico(@RequestParam String id) {

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

	@RequestMapping(value = "/infoPaciente", method = RequestMethod.GET)
	public @ResponseBody PacienteDto infoPaciente(@RequestParam String id) {
		PacienteDto paciente = pacientesFacade.getPacienteById(id);	
		return paciente;
	}

	@RequestMapping(value = "/cargarEspecialidades", method = RequestMethod.GET)
	public @ResponseBody List<EspecialidadDto> cargarEspecialidades() {
		return especialidadFacade.getAllEspecialidads();
	}
	
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/cargarTodosLosMedicos")
	private void cargarMedicos(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			
			result.setResult(medicoFacade.getAllMedicos());
			result.setMessage("DONE.");
		} catch (Exception ex) {
			result.setErrorCode(1);
			result.setMessage("Error al cargar medicos");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/cargarMedicos")
	private void registrar(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			String codEspecialidad = request.getParameter("especialidad");
			EspecialidadDto especialidad = especialidadFacade.getEspecialidadByCodigo(codEspecialidad);
			result.setResult(medicoFacade.getMedicoByEspecialidad(especialidad));
			result.setMessage("DONE.");
		} catch (Exception ex) {
			result.setErrorCode(1);
			result.setMessage("Error al cargar medicos");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	@RequestMapping(value = "/registrarTurnosNuevos", method = RequestMethod.GET)
	public @ResponseBody List<TurnosDto> registrarTurnos(
			@RequestParam(name = "horaDesde", required = false) Integer horaDesde,
			@RequestParam(name = "horaHasta", required = false) Integer horaHasta,
			@RequestParam(name = "fechaRegistroTurno", required = false) String fechaRegistroTurno,
			@RequestParam(name = "duracionTurno", required = false) String duracionTurno,HttpServletRequest request) {
		Integer duracionInt = Integer.valueOf(duracionTurno);
		Date targetTimeFecha = DateUtil.stringToDate(fechaRegistroTurno, "dd/MM/yyyy");
		UsuarioDto usuario = (UsuarioDto) request.getSession().getAttribute("usuario");
		MedicoDto medico = medicoFacade.getMedicoByUsuario(usuario);
		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		if (medico != null) {
			if (duracionInt == 10) {
				for (Integer i = horaDesde; i < horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(10);
					turnos.add(turno1);
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(10);
					turno2.setDuracion(10);
					turnos.add(turno2);
					turnosFacade.crearTurno(turno2);
					TurnosDto turno3 = new TurnosDto();
					turno3.setMedico(medico);
					turno3.setEspecialidad(medico.getEspecialidad());
					turno3.setEstado(false);
					turno3.setFecha(targetTimeFecha);
					turno3.setHora(i);
					turno3.setMinutos(20);
					turno3.setDuracion(10);
					turnos.add(turno3);
					turnosFacade.crearTurno(turno3);
					TurnosDto turno4 = new TurnosDto();
					turno4.setMedico(medico);
					turno4.setEspecialidad(medico.getEspecialidad());
					turno4.setEstado(false);
					turno4.setFecha(targetTimeFecha);
					turno4.setHora(i);
					turno4.setMinutos(30);
					turno4.setDuracion(10);
					turnos.add(turno4);
					turnosFacade.crearTurno(turno4);
					TurnosDto turno5 = new TurnosDto();
					turno5.setMedico(medico);
					turno5.setEspecialidad(medico.getEspecialidad());
					turno5.setEstado(false);
					turno5.setFecha(targetTimeFecha);
					turno5.setHora(i);
					turno5.setMinutos(40);
					turno5.setDuracion(10);
					turnos.add(turno5);
					turnosFacade.crearTurno(turno5);
					TurnosDto turno6 = new TurnosDto();
					turno6.setMedico(medico);
					turno6.setEspecialidad(medico.getEspecialidad());
					turno6.setEstado(false);
					turno6.setFecha(targetTimeFecha);
					turno6.setHora(i);
					turno6.setMinutos(50);
					turno6.setDuracion(10);
					turnos.add(turno6);
					turnosFacade.crearTurno(turno6);
				}
			} else if (duracionInt == 15) {
				for (Integer i = horaDesde; i < horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(15);
					turnos.add(turno1);
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(15);
					turno2.setDuracion(15);
					turnos.add(turno2);
					turnosFacade.crearTurno(turno2);
					TurnosDto turno3 = new TurnosDto();
					turno3.setMedico(medico);
					turno3.setEspecialidad(medico.getEspecialidad());
					turno3.setEstado(false);
					turno3.setFecha(targetTimeFecha);
					turno3.setHora(i);
					turno3.setMinutos(30);
					turno3.setDuracion(15);
					turnos.add(turno3);
					turnosFacade.crearTurno(turno3);
					TurnosDto turno4 = new TurnosDto();
					turno4.setMedico(medico);
					turno4.setEspecialidad(medico.getEspecialidad());
					turno4.setEstado(false);
					turno4.setFecha(targetTimeFecha);
					turno4.setHora(i);
					turno4.setMinutos(45);
					turno4.setDuracion(15);
					turnos.add(turno4);
					turnosFacade.crearTurno(turno4);
				}
			} else if (duracionInt == 20) {
				for (Integer i = horaDesde; i < horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(20);
					turnos.add(turno1);
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(20);
					turno2.setDuracion(20);
					turnos.add(turno2);
					turnosFacade.crearTurno(turno2);
					TurnosDto turno3 = new TurnosDto();
					turno3.setMedico(medico);
					turno3.setEspecialidad(medico.getEspecialidad());
					turno3.setEstado(false);
					turno3.setFecha(targetTimeFecha);
					turno3.setHora(i);
					turno3.setMinutos(40);
					turno3.setDuracion(20);
					turnos.add(turno3);
					turnosFacade.crearTurno(turno3);
				}
			} else if (duracionInt == 30) {
				for (Integer i = horaDesde; i < horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(30);
					turnos.add(turno1);
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(30);
					turno2.setDuracion(30);
					turnos.add(turno2);
					turnosFacade.crearTurno(turno2);
				}
			}
		}
		return turnos;
	}
	
	@RequestMapping(value = "/eliminarMedico", method = RequestMethod.GET)
	public ModelAndView initEliminarMedico(ModelMap model) {
		
		ModelAndView mv = new ModelAndView("eliminarMedico");
		
		List<MedicoDto> medicos = medicoFacade.getAllMedicos();
		
		mv.addObject("medicos", medicos);

		return mv;
	}
	@RequestMapping(value = "/darBajaMedico", method = RequestMethod.GET)
	public @ResponseBody ResultadoDto eliminarMedico(@RequestParam(name = "medico") String medicoId) {

		MedicoDto medico=medicoFacade.getMedicoById(medicoId);
		medicoFacade.removerMedico(medico);
		
		ResultadoDto resultado = new ResultadoDto(true, "El medico se ha dado de baja correctamente.");
		return resultado;
	}

}
