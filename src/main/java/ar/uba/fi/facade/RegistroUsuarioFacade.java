package ar.uba.fi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.RegistroUsuarioDto;
import ar.uba.fi.mongo.facade.MongoDBRegistroUsuarioFacade;

@Service
public class RegistroUsuarioFacade {
	@Autowired
	private MongoDBRegistroUsuarioFacade registroUsuarioFacade;

	public void crearRegistroUsuario(RegistroUsuarioDto registroUsuario) {
		registroUsuarioFacade.crearRegistroUsuario(registroUsuario);
	}

	public void editarRegistroUsuario(RegistroUsuarioDto registroUsuario) {
		registroUsuarioFacade.editarRegistroUsuario(registroUsuario);
	}

	public void removerRegistroUsuario(RegistroUsuarioDto registroUsuario) {
		registroUsuarioFacade.removerRegistroUsuario(registroUsuario);
	}

	public List<RegistroUsuarioDto> getAllRegistroUsuarios() {
		return registroUsuarioFacade.getAllRegistroUsuarios();
	}

	public RegistroUsuarioDto getRegistroUsuarioById(String id) {
		return registroUsuarioFacade.getRegistroUsuarioById(id);
	}

	public RegistroUsuarioDto getRegistroUsuarioByNombreUsuario(String nombreUsuario) {
		return registroUsuarioFacade.getRegistroUsuarioByNombreUsuario(nombreUsuario);
	}

	public RegistroUsuarioDto getRegistroUsuarioByDocumento(String documento) {
		return registroUsuarioFacade.getRegistroUsuarioByDocumento(documento);
	}

	public RegistroUsuarioDto getRegistroUsuarioByNombreApellido(String nombreApellido) {
		return registroUsuarioFacade.getRegistroUsuarioByNombreApellido(nombreApellido);
	}
}
