package ar.uba.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.uba.fi.dto.UsuarioDto;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@PostMapping("/checkearUsuario")
	public String checkearUsuario(@ModelAttribute("userForm") UsuarioDto user) {

		// a
		// model.addAttribute("errorMsg", "Your username and password are invalid.");

		return "welcome";
	}

}
