package ar.uba.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.uba.fi.dto.UsuarioDto;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("userForm") UsuarioDto user) {
//        if (error != null)
//            model.addAttribute("errorMsg", "Your username and password are invalid.");
//
//        if (logout != null)
//            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
}

}
