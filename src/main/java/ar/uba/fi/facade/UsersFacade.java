package ar.uba.fi.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.mongo.facade.MongoDBUsersFacade;


@Service
public class UsersFacade {
	@Autowired
	private MongoDBUsersFacade mongoDBCUsersFacade;

	public void crearUsuario(UsuarioDto usuario) {
		mongoDBCUsersFacade.crearUsuario(usuario);
	}
	
	
}
