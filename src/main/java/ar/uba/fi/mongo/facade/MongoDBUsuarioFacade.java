package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.mongo.repository.UsuarioRepository;

@Service
public class MongoDBUsuarioFacade {
	@Autowired
	private UsuarioRepository userRepository;

	public void crearUsuario(UsuarioDto usuario) {
		try {
			userRepository.save(usuario);
		} catch (MongoException ex) {
		}
	}

	public void editarUsuario(UsuarioDto usuario) {
		try {
			userRepository.save(usuario);
		} catch (MongoException ex) {
		}
	}

	public void removerUsuario(UsuarioDto usuario) {
		try {
			userRepository.delete(usuario);
		} catch (MongoException ex) {
		}
	}

	public List<UsuarioDto> getAllUsuarios() {
		try {
			return userRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public UsuarioDto getUsuarioById(String id) {
		try {
			return userRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public UsuarioDto getUsuarioByNombreUsuario(String nombreUsuario) {
		try {
			return userRepository.findOneByNombreUsuario(nombreUsuario);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public UsuarioDto getUsuarioByNombreUsuarioAndContrasenia(String nombreUsuario, String contrasenia) {
		try {
			return userRepository.findOneByNombreUsuarioAndContrasenia(nombreUsuario, contrasenia);
		} catch (MongoException ex) {
		}
		return null;
	}
}
