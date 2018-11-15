package ar.uba.fi.facade;

import java.util.List;

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

	public void editarUsuario(UsuarioDto usuario) {
		mongoDBCUsersFacade.editarUsuario(usuario);
	}

	public void removerUsuario(UsuarioDto usuario) {
		mongoDBCUsersFacade.removerUsuario(usuario);
	}

	public List<UsuarioDto> getAllUsuarios() {
		return mongoDBCUsersFacade.getAllUsuarios();
	}

	public UsuarioDto getUsuarioById(String id) {
		return mongoDBCUsersFacade.getUsuarioById(id);
	}

	public UsuarioDto getUsuarioByNombreUsuario(String nombreUsuario) {
		return mongoDBCUsersFacade.getUsuarioByNombreUsuario(nombreUsuario);
	}
	
	public UsuarioDto getUsuarioByNombreUsuarioAndContrasenia(String nombreUsuario, String contrasenia) {
		return mongoDBCUsersFacade.getUsuarioByNombreUsuarioAndContrasenia(nombreUsuario, contrasenia);
	}
}
