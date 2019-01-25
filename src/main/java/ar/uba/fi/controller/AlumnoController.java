package ar.uba.fi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import ar.uba.fi.dto.AlumnoDto;
import ar.uba.fi.dto.DocenteDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.AlumnoFacade;
import ar.uba.fi.facade.DocenteFacade;
import ar.uba.fi.facade.TutorFacade;
import ar.uba.fi.facade.UsuariosFacade;
import ar.uba.fi.util.AjaxResult;

@Controller
public class AlumnoController {
	@Autowired
	private AlumnoFacade alumnoFacade;
	@Autowired
	private DocenteFacade docenteFacade;
	@Autowired
	private TutorFacade tutorFacade;
	@Autowired
	private UsuariosFacade usuarioFacade;

	@RequestMapping(value = "/registrarAlumno", method = RequestMethod.GET)
	public String init(Model model) {
		return "registrarAlumno";
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/registroAlumno")
	private void registrar(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			String nombreUsuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("contrasenia");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			Long dni = Long.valueOf(request.getParameter("dni"));
			Integer anio = Integer.valueOf(request.getParameter("anio"));
			String tutor = request.getParameter("tutor");

			if (usuarioFacade.getUsuarioByNombreUsuario(nombreUsuario) == null) {
				UsuarioDto usuario = new UsuarioDto(nombreUsuario, contrasenia);
				usuario.setPermiso("alumno");
				AlumnoDto alumno = new AlumnoDto();
				alumno.setUsuario(usuario);
				alumno.setNombre(nombre);
				alumno.setApellido(apellido);
				alumno.setDni(dni);
				alumno.setAnio(anio);
				DocenteDto docente = docenteFacade.getDocenteByAnio(anio);
				alumno.setDocente(docente.getId());	//segun el año en el que se registra el alumno, el docente que se le asigna
				alumno.setTutor(tutor);
				usuarioFacade.crearUsuario(usuario);
				alumnoFacade.crearAlumno(alumno);
				result.setResult(true);
			} else {
				result.setResult(false);
				result.setMessage("Nombre de usuario existente.");
			}
		} catch (Exception ex) {
			result.setResult(false);
			result.setMessage("Error al registrar Alumno. Verifique los datos ingresados.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/getDocentes")
	private void getDocentes(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			result.setResult(docenteFacade.getAllDocentes());
		} catch (Exception ex) {
			result.setResult(false);
			result.setMessage("Error al cargar docentes.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/getTutores")
	private void getTutores(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			result.setResult(tutorFacade.getAllTutors());
		} catch (Exception ex) {
			result.setResult(false);
			result.setMessage("Error al cargar tutores.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

}
