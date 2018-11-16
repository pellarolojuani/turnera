package ar.uba.fi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.UsuariosFacade;
import ar.uba.fi.util.AjaxResult;

@Controller
public class LoginController {
	@Autowired
	private UsuariosFacade usuariosFacade;
	private final Gson gson = new Gson();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/checkearUsuario")
	private void saveSettings(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		AjaxResult result = new AjaxResult();
		try {
			String nombreUsuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("contrasenia");
			UsuarioDto usuario = usuariosFacade.getUsuarioByNombreUsuarioAndContrasenia(nombreUsuario, contrasenia);
			if (usuario != null) {
				model.put("name", usuario.getNombreUsuario());
				result.setResult(usuario);
				request.getSession().setAttribute("usuario", usuario);
			} else {
				result.setMessage("Usuario y/o contrasenia incorrectos.");
			}
		} catch (Exception ex) {
			result.setErrorCode(1);
			result.setMessage(ex.getMessage());
		}
		Gson gson = new Gson();
		String json = gson.toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
