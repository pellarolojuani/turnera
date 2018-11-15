package ar.uba.fi.controller;

import java.io.IOException;
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

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.TurnosDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.EspecialidadFacade;
import ar.uba.fi.facade.MedicoFacade;
import ar.uba.fi.facade.TurnosFacade;
import ar.uba.fi.util.AjaxResult;

@Controller
public class MedicoController {

	@Autowired
	private TurnosFacade turnosFacade;
	@Autowired
	private EspecialidadFacade especialidadFacade;

	@Autowired
	private MedicoFacade medicoFacade;

	@RequestMapping(value = "/verTurnos", method = RequestMethod.GET)
	public ModelAndView initSolicitarTurno(ModelMap model) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("verTurnos");

		return mv;
	}

	@RequestMapping(value = "/verProximosTurnos", method = RequestMethod.GET)
	public @ResponseBody List<TurnosDto> verProximosTurnos(@RequestParam(name = "fechaDesde") String fechaDesde, @RequestParam(name = "fechaHasta") String fechaHasta) {

		PacienteDto p1 = new PacienteDto("1", "DNI", "22454666", "Masculino", null, "20/01/1965", null, "Jose Argento", "pepe@argento.com", "453543-45345");

		PacienteDto p2 = new PacienteDto("2", "DNI", "26455666", "Femenino", null, "30/03/1970", null, "Moni Argento", "moni@argento.com", "453543-45345");

		// TODO aca voy a la base y traigo los turnos segun los parametros.
		TurnosDto turno1 = new TurnosDto(new Date(), "Aprobado", p1);
		turno1.setId("1");
		turno1.setFechaString("20/12/2018" + " 11:00");
		TurnosDto turno2 = new TurnosDto(new Date(), "Aprobado", p2);
		turno2.setId("2");
		turno2.setFechaString("21/12/2018" + " 11:20");
		List<TurnosDto> turnos = new ArrayList<TurnosDto>();
		turnos.add(turno1);
		turnos.add(turno2);

		return turnos;
	}

	@RequestMapping(value = "/anularPorMedico", method = RequestMethod.GET)
	public @ResponseBody Boolean anularPorMedico(@RequestParam String id) {

		// TODO aca voy a la base y le cambio el estado al turno.
		// inyectar servicios
		System.out.println("voy a anular el turno id:" + id);

		// TurnosDto turno = turnosFacade.getTurnoById(id);
		// turno.setEstado("???");
		// turnosFacade.editarTurno(turno);

		Boolean resultadoAnularTurno = true;

		return resultadoAnularTurno;
	}

	@RequestMapping(value = "/infoPaciente", method = RequestMethod.GET)
	public @ResponseBody PacienteDto infoPaciente(@RequestParam String id) {
		PacienteDto p1 = new PacienteDto("1", "DNI", "22454666", "Masculino", null, "20/01/1965", null, "Jose Argento", "pepe@argento.com", "453543-45345");
		return p1;
	}

	@RequestMapping(value = "/cargarEspecialidades", method = RequestMethod.GET)
	public @ResponseBody List<EspecialidadDto> cargarEspecialidades() {
		return especialidadFacade.getAllEspecialidads();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/cargarMedicos")
	private void registrar(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
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

}
