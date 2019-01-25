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
import ar.uba.fi.facade.DocenteFacade;
import ar.uba.fi.facade.UsuariosFacade;
import ar.uba.fi.util.AjaxResult;

@Controller
public class DocenteController {
	@Autowired
	private DocenteFacade docenteFacade;
	@Autowired
	private UsuariosFacade usuarioFacade;
	
	@RequestMapping(value = "/registrarDocente", method = RequestMethod.GET)
	public String init(Model model) {
		return "registrarDocente";
	}
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/registroDocente")
	private void registrar(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			String nombreUsuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("contrasenia");
			String nombre= request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			Long dni = Long.valueOf(request.getParameter("dni"));
			Integer anio = Integer.valueOf(request.getParameter("anio"));
			String materia = request.getParameter("materia");
			
			if (usuarioFacade.getUsuarioByNombreUsuario(nombreUsuario) == null) {
				UsuarioDto usuario = new UsuarioDto(nombreUsuario, contrasenia);
				usuario.setPermiso("docente");
				DocenteDto docente = new DocenteDto();
				docente.setUsuario(usuario);
				docente.setNombre(nombre);
				docente.setApellido(apellido);
				docente.setDni(dni);
				docente.setAnio(anio);
				docente.setMateria(materia);
				usuarioFacade.crearUsuario(usuario);
				docenteFacade.crearDocente(docente);
				result.setResult(true);
			} else {
				result.setResult(false);
				result.setMessage("Nombre de usuario existente.");
			}
		} catch (Exception ex) {
			result.setResult(false);
			result.setMessage("Error al registrar docente. Verifique los datos ingresados.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
