package com.in28minutes.springboot.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.facade.UsersFacade;

@Controller
public class RegistrarUsuarioController {
	
	@Autowired
	private UsersFacade usersFacade;
	
	@RequestMapping(value = "/registrarInit", method = RequestMethod.GET)
    public String init(Model model) {
        return "registrarUsuario";
	}
		
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
    public void registrar(ModelMap model) {
		System.out.println("aca logica para validar y registrar usuario");
		model.put("mensaje", "El usuario se registro correctamente" );
	}
	
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/crearUsuario")
	private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//HARDCODEO
			UsuarioDto usuario = new UsuarioDto();
			usuario.setNombre("pepe");
			usuario.setApellido("asd");
			usuario.setDni(1233l);
			usuario.setUsername("pepito");
			usersFacade.crearUsuario(usuario);
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			response.setContentType("application/json");
		}
	}
	

}
