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
import ar.uba.fi.dto.ResultadoDto;
import ar.uba.fi.dto.TurnosDto;
import ar.uba.fi.facade.EspecialidadFacade;
import ar.uba.fi.facade.MedicoFacade;
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
	public @ResponseBody List<TurnosDto> verProximosTurnos(@RequestParam(name = "fechaDesde") String fechaDesde, @RequestParam(name = "fechaHasta") String fechaHasta) {

		PacienteDto p1 = new PacienteDto("1", "DNI", "22454666", "Masculino", null, "20/01/1965", null, "Jose Argento", "pepe@argento.com", "453543-45345");

		PacienteDto p2 = new PacienteDto("2", "DNI", "26455666", "Femenino", null, "30/03/1970", null, "Moni Argento", "moni@argento.com", "453543-45345");

		// TODO aca voy a la base y traigo los turnos segun los parametros.
		TurnosDto turno1 = new TurnosDto(new Date(), true, p1);
		turno1.setId("1");
		turno1.setFechaString("20/12/2018" + " 11:00");
		TurnosDto turno2 = new TurnosDto(new Date(), true, p2);
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

	@RequestMapping(value = "/registrarTurnosNuevos", method = RequestMethod.GET)
	public @ResponseBody ResultadoDto registrarTurnos(@RequestParam(name = "horaDesde", required = false) Integer horaDesde, @RequestParam(name = "horaHasta", required = false) Integer horaHasta, @RequestParam(name = "fechaRegistroTurno", required = false) String fechaRegistroTurno, @RequestParam(name = "duracionTurno", required = false) String duracionTurno, @RequestParam(name = "nombreUsuario", required = false) String nombreUsuario) {

//		private String fechaString;
//		private PacienteDto paciente;
//		private String numeroComprobante;
//		private String numeroComprobanteAnulado;
//		private String duracion;
		
		Integer duracionInt = Integer.valueOf(duracionTurno);
		Date targetTimeFecha = DateUtil.stringToDate(fechaRegistroTurno, "dd/MM/yyyy");
		MedicoDto medico = medicoFacade.getMedicoByUsuario(nombreUsuario);
		if (medico != null) {
			// LUCAS: con tu permiso, voy a hardcodear un poquito!
			// saque la hora del Date tambien... y puse hora y minuto como atributos separados..... considerando q nadie nos va a ver el codigo, creo q va a ser mas facil
			// de manipular de esta manera..
			if (duracionInt == 10) {
				for (Integer i = horaDesde; i <= horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(10);
					
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();

					turnosFacade.crearTurno(turno2);
					TurnosDto turno3 = new TurnosDto();

					turnosFacade.crearTurno(turno3);
					TurnosDto turno4 = new TurnosDto();

					turnosFacade.crearTurno(turno4);
					TurnosDto turno5 = new TurnosDto();

					turnosFacade.crearTurno(turno5);
				}
			} else if (duracionInt == 15) {
				TurnosDto turno1 = new TurnosDto();

				turnosFacade.crearTurno(turno1);
				TurnosDto turno2 = new TurnosDto();

				turnosFacade.crearTurno(turno2);
				TurnosDto turno3 = new TurnosDto();

				turnosFacade.crearTurno(turno3);
				TurnosDto turno4 = new TurnosDto();

				turnosFacade.crearTurno(turno4);
			} else if (duracionInt == 20) {
				TurnosDto turno1 = new TurnosDto();

				turnosFacade.crearTurno(turno1);
				TurnosDto turno2 = new TurnosDto();

				turnosFacade.crearTurno(turno2);
				TurnosDto turno3 = new TurnosDto();

				turnosFacade.crearTurno(turno3);
			} else if (duracionInt == 30) {
				TurnosDto turno1 = new TurnosDto();

				turnosFacade.crearTurno(turno1);
				TurnosDto turno2 = new TurnosDto();

				turnosFacade.crearTurno(turno2);
			}
		}
		return null;

	}

}
