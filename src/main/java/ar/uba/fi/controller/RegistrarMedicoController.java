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

import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.EspecialidadFacade;
import ar.uba.fi.facade.MedicoFacade;
import ar.uba.fi.facade.UsuariosFacade;
import ar.uba.fi.util.AjaxResult;

@Controller
public class RegistrarMedicoController {
	@Autowired
	private MedicoFacade medicoFacade;
	@Autowired
	private EspecialidadFacade especialidadFacade;
	@Autowired
	private UsuariosFacade usuarioFacade;

	@RequestMapping(value = "/registrarMedico", method = RequestMethod.GET)
	public String init(Model model) {
		return "registrarMedico";
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/registroMedico")
	private void registrar(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			String nombreUsuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("contrasenia");
			String nombreApellido = request.getParameter("nombre");
			String especialidad = request.getParameter("especialidad");
			String matricula = request.getParameter("matricula");
			
			if (usuarioFacade.getUsuarioByNombreUsuario(nombreUsuario) == null) {
				UsuarioDto usuario = new UsuarioDto(nombreUsuario, contrasenia);
				usuario.setPermiso("medico");
				MedicoDto medico = new MedicoDto();
				medico.setNombre(nombreApellido);
				medico.setUsuario(usuario);
				medico.setMatricula(matricula);
				medico.setEspecialidad(especialidadFacade.getEspecialidadByCodigo(especialidad));
				usuarioFacade.crearUsuario(usuario);
				medicoFacade.crearMedico(medico);
				result.setResult(true);
			} else {
				result.setResult(false);
				result.setMessage("Nombre de usuario existente.");
			}
		} catch (Exception ex) {
			result.setResult(false);
			result.setMessage("Error al registrar medico. Verifique los datos ingresados.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
