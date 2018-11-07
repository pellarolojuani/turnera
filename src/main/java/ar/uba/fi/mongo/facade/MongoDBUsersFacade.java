package ar.uba.fi.mongo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.mongo.repository.UserRepository;


@Service
public class MongoDBUsersFacade {
	@Autowired
	private UserRepository userRepository;

	public void crearUsuario(UsuarioDto usuario) {
		try {
			userRepository.save(usuario);
		} catch (MongoException ex) {
		} 
	}
}
