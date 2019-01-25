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

import ar.uba.fi.dto.TutorDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.TutorFacade;
import ar.uba.fi.facade.UsuariosFacade;
import ar.uba.fi.util.AjaxResult;

@Controller
public class TutorController {
	@Autowired
	private TutorFacade tutorFacade;
	@Autowired
	private UsuariosFacade usuarioFacade;
	
	@RequestMapping(value = "/registrarTutor", method = RequestMethod.GET)
	public String init(Model model) {
		return "registrarTutor";
	}
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/registroTutor")
	private void registrar(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			String nombreUsuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("contrasenia");
			String nombre= request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			Long dni = Long.valueOf(request.getParameter("dni"));
			
			if (usuarioFacade.getUsuarioByNombreUsuario(nombreUsuario) == null) {
				UsuarioDto usuario = new UsuarioDto(nombreUsuario, contrasenia);
				usuario.setPermiso("tutor");
				TutorDto tutor = new TutorDto();
				tutor.setUsuario(usuario);
				tutor.setNombre(nombre);
				tutor.setApellido(apellido);
				tutor.setDni(dni);
				usuarioFacade.crearUsuario(usuario);
				tutorFacade.crearTutor(tutor);
				result.setResult(true);
			} else {
				result.setResult(false);
				result.setMessage("Nombre de usuario existente.");
			}
		} catch (Exception ex) {
			result.setResult(false);
			result.setMessage("Error al registrar tutor. Verifique los datos ingresados.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
