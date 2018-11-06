package com.in28minutes.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrarUsuarioController {
	
	@RequestMapping(value = "/registrarInit", method = RequestMethod.GET)
    public String init(Model model) {
        return "registrarUsuario";
	}
		
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
    public void registrar(Model model) {
		System.out.println("aca logica para validar y registrar usuario");
	}
	

}
