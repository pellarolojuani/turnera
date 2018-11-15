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

import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.PacientesFacade;
import ar.uba.fi.facade.UsuariosFacade;
import ar.uba.fi.util.AjaxResult;

@Controller
public class RegistrarUsuarioController {

	@Autowired
	private UsuariosFacade usersFacade;
	@Autowired
	private PacientesFacade pacientesFacade;

	@RequestMapping(value = "/registrarUsuario", method = RequestMethod.GET)
	public String init(Model model) {
		return "registrarUsuario";
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/registrar")
	private void registrar(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			String nombreUsuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("contrasenia");
			String nombreApellido = request.getParameter("nombreApellido");
			String fechaNacimiento = request.getParameter("fechaNacimiento");
			String tipoDocumento = request.getParameter("tipoDocumento");
			String documento = request.getParameter("documento");
			String sexo = request.getParameter("sexo");
			String mail = request.getParameter("mail");
			String telefono = request.getParameter("telefono");
			
			if (usersFacade.getUsuarioByNombreUsuario(nombreUsuario) == null) {
				UsuarioDto usuario = new UsuarioDto(nombreUsuario, contrasenia);
				usuario.setPermiso("usuario");
				usersFacade.crearUsuario(usuario);
				PacienteDto paciente = new PacienteDto(tipoDocumento, documento, usuario);
				paciente.setNombreApellido(nombreApellido);
				paciente.setSexo(sexo);
				paciente.setFechaNacimiento(fechaNacimiento);
				paciente.setMail(mail);
				paciente.setTelefono(telefono);
				pacientesFacade.crearPaciente(paciente);
				result.setResult(true);				
			} else {
				result.setResult(false);
				result.setMessage("Usuario existente.");
			}
		} catch (Exception ex) {
			result.setErrorCode(1);
			result.setMessage("Error al crear el usuario. Verifique los datos ingresados.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/crearUsuario")
	private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// HARDCODEO
			UsuarioDto usuario = new UsuarioDto("pepito", "holachau");
			usersFacade.crearUsuario(usuario);
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			response.setContentType("application/json");
		}
	}

}
