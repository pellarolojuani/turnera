package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.mongo.facade.MongoDBMedicoFacade;

@Service
public class MedicoFacade {
	@Autowired
	private MongoDBMedicoFacade medicoFacade;

	public void crearMedico(MedicoDto medico) {
		medicoFacade.crearMedico(medico);
	}

	public void editarMedico(MedicoDto medico) {
		medicoFacade.editarMedico(medico);
	}

	public void removerMedico(MedicoDto medico) {
		medicoFacade.removerMedico(medico);
	}

	public List<MedicoDto> getAllMedicos() {
		return medicoFacade.getAllMedicos();
	}

	public MedicoDto getMedicoById(String id) {
		return medicoFacade.getMedicoById(id);
	}

	public MedicoDto getMedicoByNombre(String nombre) {
		return medicoFacade.getMedicoByNombre(nombre);
	}

	public List<MedicoDto> getMedicoByEspecialidad(EspecialidadDto especialidad) {
		return medicoFacade.getMedicoByEspecialidad(especialidad);
	}

	public MedicoDto getMedicoByUsuario(String usuario) {
		return medicoFacade.getMedicoByUsuario(usuario);
	}
}
