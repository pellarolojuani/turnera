package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.EspecialidadDto;
import ar.uba.fi.dto.MedicoDto;
import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.mongo.repository.MedicoRepository;

@Service
public class MongoDBMedicoFacade {
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void crearMedico(MedicoDto medico) {
		try {
			medicoRepository.save(medico);
		} catch (MongoException ex) {
		}
	}

	public void editarMedico(MedicoDto medico) {
		try {
			medicoRepository.save(medico);
		} catch (MongoException ex) {
		}
	}

	public void removerMedico(MedicoDto medico) {
		try {
			medicoRepository.delete(medico);
		} catch (MongoException ex) {
		}
	}

	public List<MedicoDto> getAllMedicos() {
		try {
			return medicoRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public MedicoDto getMedicoById(String id) {
		try {
			return medicoRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public MedicoDto getMedicoByNombre(String nombre) {
		try {
			return medicoRepository.findOneByNombre(nombre);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public MedicoDto getMedicoByUsuario(UsuarioDto usuario) {
		try {
			return medicoRepository.findByUsuario(usuario);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<MedicoDto> getMedicoByEspecialidad(EspecialidadDto especialidad) {
		try {
			return medicoRepository.findByEspecialidad(especialidad);
		} catch (MongoException ex) {
		}
		return null;
	}
}
