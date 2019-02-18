package ar.uba.fi.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.MensajeDto;
import ar.uba.fi.mongo.facade.MongoDBMensajeFacade;

@Service
public class MensajeFacade {
	@Autowired
	private MongoDBMensajeFacade mensajeFacade;

	public void crearMensaje(MensajeDto mensaje) {
		mensajeFacade.crearMensaje(mensaje);
	}

	public void editarMensaje(MensajeDto mensaje) {
		mensajeFacade.editarMensaje(mensaje);
	}

	public void removerMensaje(MensajeDto mensaje) {
		mensajeFacade.removerMensaje(mensaje);
	}

	public List<MensajeDto> getAllMensajes() {
		return mensajeFacade.getAllMensajes();
	}

	public MensajeDto getMensajeById(String id) {
		return mensajeFacade.getMensajeById(id);
	}

	public List<MensajeDto> getMensajesByEmisor(String emisor) {
		return mensajeFacade.getMensajesByEmisor(emisor);
	}

	public List<MensajeDto> getMensajesByReceptor(String receptor) {
		return mensajeFacade.getMensajesByReceptor(receptor);
	}

	public List<MensajeDto> getMensajesByFecha(Date fecha) {
		return mensajeFacade.getMensajesByFecha(fecha);
	}

	public List<MensajeDto> getMensajesBetweenDates(Date inicio, Date fin) {
		return mensajeFacade.getMensajesBetweenDates(inicio, fin);
	}

	public List<MensajeDto> getMensajesLeidos() {
		return mensajeFacade.getMensajesLeidos();
	}

	public List<MensajeDto> getMensajesNoLeidos() {
		return mensajeFacade.getMensajesNoLeidos();
	}
}
