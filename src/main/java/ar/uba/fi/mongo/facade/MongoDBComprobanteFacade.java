package ar.uba.fi.mongo.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.ComprobanteDto;
import ar.uba.fi.mongo.repository.ComprobanteRepository;

@Service
public class MongoDBComprobanteFacade {
	
	@Autowired
	private ComprobanteRepository comprobanteRepository;
	
	public void crearComprobante(ComprobanteDto comprobante) {
		try {
			comprobanteRepository.save(comprobante);
		} catch (MongoException ex) {
		}
	}
	
	public ComprobanteDto getMaxComprobante() {		
		try {
			return comprobanteRepository.findFirstByOrderByContadorDesc();
		} catch (MongoException ex) {
		}
		return null;
	}

}
