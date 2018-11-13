package ar.uba.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.facade.EspecialidadFacade;
import ar.uba.fi.facade.MedicoFacade;

@Controller
public class RegistrarMedicoController {
	@Autowired
	private MedicoFacade medicoFacade;
	@Autowired
	private EspecialidadFacade especialidadFacade;
	
	@RequestMapping(value = "/registrarMedico", method = RequestMethod.GET)
	public String init(Model model) {
		return "registrarMedico";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/registroMedico")
    public ModelAndView registrar(@ModelAttribute("registroMedico") MedicoDto medico) {
		ModelAndView model = new ModelAndView();
		try {
			medicoFacade.crearMedico(medico);
			model.addObject("errMsg","El Medico se registro correctamente.");
			model.setViewName("registrarMedico");
//	    model.addObject("registroUsuario", registroUsuario);
			return model;	
		} catch (Exception ex) {
			model.addObject("errMsg","El Medico no ha sido registrado.");
		}
		return model;
	}
}
