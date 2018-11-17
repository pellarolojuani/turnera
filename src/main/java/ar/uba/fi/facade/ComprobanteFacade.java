package ar.uba.fi.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.dto.ComprobanteDto;
import ar.uba.fi.mongo.facade.MongoDBComprobanteFacade;

@Service
public class ComprobanteFacade {
	
	@Autowired
	private MongoDBComprobanteFacade comprobanteFacade;

	public void crearComprobante(ComprobanteDto comprobante) {
		comprobanteFacade.crearComprobante(comprobante);
	}
	
	public ComprobanteDto getMaxComprobante() {
		return comprobanteFacade.getMaxComprobante();
	}
	
}
