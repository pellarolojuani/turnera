package ar.uba.fi.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.UsuarioDto;
import ar.uba.fi.mongo.facade.MongoDBUsuarioFacade;


@Service
public class UsuariosFacade {
	@Autowired
	private MongoDBUsuarioFacade mongoDBCUsersFacade;

	public void crearUsuario(UsuarioDto usuario) {
		mongoDBCUsersFacade.crearUsuario(usuario);
	}
	
	
}
