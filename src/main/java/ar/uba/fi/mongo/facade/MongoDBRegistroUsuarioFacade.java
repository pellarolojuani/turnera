package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.RegistroUsuarioDto;
import ar.uba.fi.mongo.repository.RegistroUsuarioRepository;

@Service
public class MongoDBRegistroUsuarioFacade {
	@Autowired
	private RegistroUsuarioRepository registroUsuarioRepository;

	public void crearRegistroUsuario(RegistroUsuarioDto registroUsuario) {
		try {
			registroUsuarioRepository.save(registroUsuario);
		} catch (MongoException ex) {
		}
	}

	public void editarRegistroUsuario(RegistroUsuarioDto registroUsuario) {
		try {
			registroUsuarioRepository.save(registroUsuario);
		} catch (MongoException ex) {
		}
	}

	public void removerRegistroUsuario(RegistroUsuarioDto registroUsuario) {
		try {
			registroUsuarioRepository.delete(registroUsuario);
		} catch (MongoException ex) {
		}
	}

	public List<RegistroUsuarioDto> getAllRegistroUsuarios() {
		try {
			return registroUsuarioRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public RegistroUsuarioDto getRegistroUsuarioById(String id) {
		try {
			return registroUsuarioRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public RegistroUsuarioDto getRegistroUsuarioByNombreUsuario(String nombreUsuario) {
		try {
			return registroUsuarioRepository.findOneByNombreUsuario(nombreUsuario);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public RegistroUsuarioDto getRegistroUsuarioByDocumento(String documento) {
		try {
			return registroUsuarioRepository.findOneByDocumento(documento);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public RegistroUsuarioDto getRegistroUsuarioByNombreApellido(String nombreApellido) {
		try {
			return registroUsuarioRepository.findOneByNombreApellido(nombreApellido);
		} catch (MongoException ex) {
		}
		return null;
	}
}
