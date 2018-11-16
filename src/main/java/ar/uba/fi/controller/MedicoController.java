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
	public @ResponseBody List<TurnosDto> verProximosTurnos(@RequestParam(name = "fechaDesde") String fechaDesde,
			@RequestParam(name = "fechaHasta") String fechaHasta) {

		PacienteDto p1 = new PacienteDto("1", "DNI", "22454666", "Masculino", null, "20/01/1965", null, "Jose Argento",
				"pepe@argento.com", "453543-45345");

		PacienteDto p2 = new PacienteDto("2", "DNI", "26455666", "Femenino", null, "30/03/1970", null, "Moni Argento",
				"moni@argento.com", "453543-45345");

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
		PacienteDto p1 = new PacienteDto("1", "DNI", "22454666", "Masculino", null, "20/01/1965", null, "Jose Argento",
				"pepe@argento.com", "453543-45345");
		return p1;
	}

	@RequestMapping(value = "/cargarEspecialidades", method = RequestMethod.GET)
	public @ResponseBody List<EspecialidadDto> cargarEspecialidades() {
		return especialidadFacade.getAllEspecialidads();
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
	public @ResponseBody ResultadoDto registrarTurnos(
			@RequestParam(name = "horaDesde", required = false) Integer horaDesde,
			@RequestParam(name = "horaHasta", required = false) Integer horaHasta,
			@RequestParam(name = "fechaRegistroTurno", required = false) String fechaRegistroTurno,
			@RequestParam(name = "duracionTurno", required = false) String duracionTurno,HttpServletRequest request) {
		Integer duracionInt = Integer.valueOf(duracionTurno);
		Date targetTimeFecha = DateUtil.stringToDate(fechaRegistroTurno, "dd/MM/yyyy");
		request.getAttribute("usuario");
		//MedicoDto medico = medicoFacade.getMedicoByUsuario(nombreUsuario);
		MedicoDto medico = null;
		if (medico != null) {
			// *
			// *
			// LUCAS: LEERRRRRRRR!!!
			// *
			// *
			// con tu permiso, voy a hardcodear un poquito!
			// saque la hora del Date tambien... y puse hora y minuto como atributos
			// separados..... considerando q nadie nos va a ver el codigo, creo q va a ser
			// mas facil
			// de manipular de esta manera..
			// Otra cosa, no se cual era tu idea del atributo "estado", de los turnos.. pero
			// lo cambie a true o false, considerando que indica si el turno ya esta
			// tomado o no. Si no es asi, volve a cambiarlo como estaba antes.
			// y la idea es que, cuando un paciente selecciona un turno, el estado pasa a
			// true y se carga el paciente en su respectivo atributo en el turno, no?

			//
			// Una cosa mas, fijate que en turnosFacade ya hice varios metodos para
			// consultar turnos por medico, turnos libres, turnos between fechas.. etc...
			// Igual estan sin probar, asique no pongo las manos en el fuego con esos
			// queries.... Asique en la medida q se van usando los vamos probando y de
			// ultima los arreglo.
			// no se me ocurre, pero cualquier otro que necesites y no este, anotamelo y lo
			// hago!
			//
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
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(10);
					turno2.setDuracion(10);
					turnosFacade.crearTurno(turno2);
					TurnosDto turno3 = new TurnosDto();
					turno3.setMedico(medico);
					turno3.setEspecialidad(medico.getEspecialidad());
					turno3.setEstado(false);
					turno3.setFecha(targetTimeFecha);
					turno3.setHora(i);
					turno3.setMinutos(20);
					turno3.setDuracion(10);
					turnosFacade.crearTurno(turno3);
					TurnosDto turno4 = new TurnosDto();
					turno4.setMedico(medico);
					turno4.setEspecialidad(medico.getEspecialidad());
					turno4.setEstado(false);
					turno4.setFecha(targetTimeFecha);
					turno4.setHora(i);
					turno4.setMinutos(30);
					turno4.setDuracion(10);
					turnosFacade.crearTurno(turno4);
					TurnosDto turno5 = new TurnosDto();
					turno5.setMedico(medico);
					turno5.setEspecialidad(medico.getEspecialidad());
					turno5.setEstado(false);
					turno5.setFecha(targetTimeFecha);
					turno5.setHora(i);
					turno5.setMinutos(40);
					turno5.setDuracion(10);
					turnosFacade.crearTurno(turno5);
					TurnosDto turno6 = new TurnosDto();
					turno6.setMedico(medico);
					turno6.setEspecialidad(medico.getEspecialidad());
					turno6.setEstado(false);
					turno6.setFecha(targetTimeFecha);
					turno6.setHora(i);
					turno6.setMinutos(60);
					turno6.setDuracion(10);
					turnosFacade.crearTurno(turno5);
				}
			} else if (duracionInt == 15) {
				for (Integer i = horaDesde; i <= horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(15);
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(15);
					turno2.setDuracion(15);
					turnosFacade.crearTurno(turno2);
					TurnosDto turno3 = new TurnosDto();
					turno3.setMedico(medico);
					turno3.setEspecialidad(medico.getEspecialidad());
					turno3.setEstado(false);
					turno3.setFecha(targetTimeFecha);
					turno3.setHora(i);
					turno3.setMinutos(30);
					turno3.setDuracion(15);
					turnosFacade.crearTurno(turno3);
					TurnosDto turno4 = new TurnosDto();
					turno4.setMedico(medico);
					turno4.setEspecialidad(medico.getEspecialidad());
					turno4.setEstado(false);
					turno4.setFecha(targetTimeFecha);
					turno4.setHora(i);
					turno4.setMinutos(45);
					turno4.setDuracion(15);
					turnosFacade.crearTurno(turno4);
				}
			} else if (duracionInt == 20) {
				for (Integer i = horaDesde; i <= horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(20);
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(20);
					turno2.setDuracion(20);
					turnosFacade.crearTurno(turno2);
					TurnosDto turno3 = new TurnosDto();
					turno3.setMedico(medico);
					turno3.setEspecialidad(medico.getEspecialidad());
					turno3.setEstado(false);
					turno3.setFecha(targetTimeFecha);
					turno3.setHora(i);
					turno3.setMinutos(40);
					turno3.setDuracion(20);
					turnosFacade.crearTurno(turno3);
				}
			} else if (duracionInt == 30) {
				for (Integer i = horaDesde; i <= horaHasta; i++) {
					TurnosDto turno1 = new TurnosDto();
					turno1.setMedico(medico);
					turno1.setEspecialidad(medico.getEspecialidad());
					turno1.setEstado(false);
					turno1.setFecha(targetTimeFecha);
					turno1.setHora(i);
					turno1.setMinutos(0);
					turno1.setDuracion(30);
					turnosFacade.crearTurno(turno1);
					TurnosDto turno2 = new TurnosDto();
					turno2.setMedico(medico);
					turno2.setEspecialidad(medico.getEspecialidad());
					turno2.setEstado(false);
					turno2.setFecha(targetTimeFecha);
					turno2.setHora(i);
					turno2.setMinutos(30);
					turno2.setDuracion(30);
					turnosFacade.crearTurno(turno2);
				}
			}
		}
		return null;
	}

}
