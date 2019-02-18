package ar.uba.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.uba.fi.facade.AlumnoFacade;
import ar.uba.fi.facade.DocenteFacade;
import ar.uba.fi.facade.TutorFacade;
import ar.uba.fi.facade.UsuariosFacade;

@Controller
public class MensajeController {
	@Autowired
	private AlumnoFacade alumnoFacade;
	@Autowired
	private DocenteFacade docenteFacade;
	@Autowired
	private TutorFacade tutorFacade;
	@Autowired
	private UsuariosFacade usuarioFacade;
	
	
	@RequestMapping(value = "/buzonDeEntrada", method = RequestMethod.GET)
	public String init(Model model) {
		return "buzonDeEntrada";
	}
}
