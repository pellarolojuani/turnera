package ar.uba.fi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ar.uba.fi.dto.TurnosTodosDto;
import ar.uba.fi.util.AjaxResult;

@Controller
public class RegistrarTurnosController {

//	@RequestMapping(value = "/registrarTurnos", method = RequestMethod.GET)
//	public ModelAndView initRegistrarTurnos(ModelMap model) {
//
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("registrarTurnos");
//
//		return mv;
//	}
//
//	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/registrarTurnos")
//	private void registrarTurnos(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
//		AjaxResult result = new AjaxResult();
//		try {
//			//Aca vamos a hardcodear un poquito...
//			TurnosTodosDto turnos = new TurnosTodosDto();
//				
//			result.setResult(true);
//			result.setMessage("Turnos registrados correctamente.");
//		} catch (Exception ex) {
//			result.setErrorCode(1);
//			result.setMessage("Error al registrar turnos. Verifique los datos ingresados.");
//		}
//		Gson gson = new Gson();
//		String json = gson.toJson(result);
//		response.setContentType("application/json");
//		response.getWriter().write(json);
//	}

}
