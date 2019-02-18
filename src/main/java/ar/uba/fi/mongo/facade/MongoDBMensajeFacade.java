package ar.uba.fi.mongo.facade;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.mongodb.MongoException;
import ar.uba.fi.dto.MensajeDto;
import ar.uba.fi.mongo.repository.MensajeRepository;

@Service
public class MongoDBMensajeFacade {
	@Autowired
	private MensajeRepository mensajeRepository;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void crearMensaje(MensajeDto mensaje) {
		try {
			mensajeRepository.save(mensaje);
		} catch (MongoException ex) {
		}
	}

	public void editarMensaje(MensajeDto mensaje) {
		try {
			mensajeRepository.save(mensaje);
		} catch (MongoException ex) {
		}
	}

	public void removerMensaje(MensajeDto mensaje) {
		try {
			mensajeRepository.delete(mensaje);
		} catch (MongoException ex) {
		}
	}

	public List<MensajeDto> getAllMensajes() {
		try {
			return mensajeRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}

	public MensajeDto getMensajeById(String id) {
		try {
			return mensajeRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}

	public List<MensajeDto> getMensajesByEmisor(String emisor) {
		try {
			return mensajeRepository.findByEmisor(emisor);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<MensajeDto> getMensajesByReceptor(String receptor) {
		try {
			return mensajeRepository.findByReceptor(receptor);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<MensajeDto> getMensajesByFecha(Date fecha) {
		try {
			return mensajeRepository.findByFecha(fecha);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<MensajeDto> getMensajesLeidos() {
		try {
			return mensajeRepository.findByLeidoIsTrue();
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<MensajeDto> getMensajesNoLeidos() {
		try {
			return mensajeRepository.findByLeidoIsFalse();
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public List<MensajeDto> getMensajesBetweenDates(Date inicio, Date fin) {
		Criteria cri = null;
		if (inicio != null) {
			cri = Criteria.where("fecha").gte(inicio);
		}
		if (cri == null) {
			if (fin != null) {
				cri = Criteria.where("fecha").lte(fin);
			}
		} else {
			if (fin != null) {
				cri = cri.lte(fin);
			}
		}
		Query query;
		if (cri != null) {
			query = new Query(cri);
		} else {
			query = new Query();
		}
		return mongoTemplate.find(query, MensajeDto.class);
	}
}
