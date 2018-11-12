package ar.uba.fi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.dto.RegistroUsuarioDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.PacientesFacade;
import ar.uba.fi.facade.UsuariosFacade;

@Controller
public class RegistrarUsuarioController {
	
	@Autowired
	private UsuariosFacade usersFacade;
	@Autowired
	private PacientesFacade pacientesFacade;
	
	@RequestMapping(value = "/registrarInit", method = RequestMethod.GET)
    public String init(Model model) {
        return "registrarUsuario";
	}
		
	@PostMapping("/registrar")
    public ModelAndView registrar(@ModelAttribute("registroUsuario") RegistroUsuarioDto registroUsuario) {
		
		//TODO aca registro el usuario y envio mensaje de creacion. 
		//En caso de que por ejemplo el usuario ya esté registrado lo pongo en el mensaje tambien.
		UsuarioDto usuario = new UsuarioDto(registroUsuario.getNombreUsuario(), registroUsuario.getContrasenia());
		usersFacade.crearUsuario(usuario);
		PacienteDto paciente = new PacienteDto(registroUsuario.getTipoDocumento(), registroUsuario.getDocumento(), usuario);
		paciente.setSexo(registroUsuario.getSexo());
		paciente.setFechaNacimiento(registroUsuario.getFechaNacimiento());
		pacientesFacade.crearPaciente(paciente);
		//falta agrgar la logica para q le cargue el numero de afiliado
		
		
		ModelAndView model = new ModelAndView();
	    model.addObject("errMsg","El Usuario se registro correctamente.");
	    model.setViewName("registrarUsuario");
//	    model.addObject("registroUsuario", registroUsuario);
	    
	    return model;
	}
	

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/crearUsuario")
	private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//HARDCODEO
			UsuarioDto usuario = new UsuarioDto("pepito", "holachau");
			usersFacade.crearUsuario(usuario);
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			response.setContentType("application/json");
		}
	}
	

}
