package ar.uba.fi.mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ar.uba.fi.dto.MensajeDto;

public interface MensajeRepository  extends MongoRepository<MensajeDto, String>{

	public List<MensajeDto> findByEmisor(String emisor);

	public List<MensajeDto> findByReceptor(String receptor);

	public List<MensajeDto> findByFecha(Date fecha);

	public List<MensajeDto> findByLeidoIsTrue();

	public List<MensajeDto> findByLeidoIsFalse();

}
