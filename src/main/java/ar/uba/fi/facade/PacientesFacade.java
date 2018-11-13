package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.mongo.facade.MongoDBPacienteFacade;

@Service
public class PacientesFacade {
	@Autowired
	private MongoDBPacienteFacade pacienteFacade;

	public void crearPaciente(PacienteDto paciente) {
		Integer numeroAfiliado = obtenerNumeroAfiliado();
		if (numeroAfiliado != null) {
			paciente.setNumeroAfiliado(numeroAfiliado + 1);			
		} else {
			paciente.setNumeroAfiliado(1);
		}
		pacienteFacade.crearPaciente(paciente);
	}

	public void editarPaciente(PacienteDto paciente) {
		pacienteFacade.editarPaciente(paciente);
	}

	public void removerPaciente(PacienteDto paciente) {
		pacienteFacade.removerPaciente(paciente);
	}

	public List<PacienteDto> getAllPacientes() {
		return pacienteFacade.getAllPacientes();
	}

	public PacienteDto getPacienteById(String id) {
		return pacienteFacade.getPacienteById(id);
	}

	public PacienteDto getPacienteByDocuemento(String documento) {
		return pacienteFacade.getPacienteByDocuemento(documento);
	}

	public PacienteDto getPacienteByNumeroAfiliado(String numeroAfiliado) {
		return pacienteFacade.getPacienteByNumeroAfiliado(numeroAfiliado);
	}
	
	public Integer obtenerNumeroAfiliado() {
		PacienteDto paciente = pacienteFacade.getUltimoPaciente();
		if (paciente != null) {
			return paciente.getNumeroAfiliado();
		}
		return null;
	}
}
