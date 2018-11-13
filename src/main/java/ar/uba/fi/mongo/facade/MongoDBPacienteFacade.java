package ar.uba.fi.mongo.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import ar.uba.fi.dto.PacienteDto;
import ar.uba.fi.mongo.repository.PacienteRepository;

@Service
public class MongoDBPacienteFacade {
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void crearPaciente(PacienteDto paciente) {
		try {
			pacienteRepository.save(paciente);
		} catch (MongoException ex) {
		}
	}

	public void editarPaciente(PacienteDto paciente) {
		try {
			pacienteRepository.save(paciente);
		} catch (MongoException ex) {
		}
	}

	public void removerPaciente(PacienteDto paciente) {
		try {
			pacienteRepository.delete(paciente);
		} catch (MongoException ex) {
		}
	}

	public List<PacienteDto> getAllPacientes() {
		try {
			return pacienteRepository.findAll();
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public PacienteDto getPacienteById(String id) {
		try {
			return pacienteRepository.findOne(id);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public PacienteDto getPacienteByDocuemento(String documento) {
		try {
			return pacienteRepository.findOneByDocumento(documento);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public PacienteDto getPacienteByNumeroAfiliado(String numeroAfiliado) {
		try {
			return pacienteRepository.findOneByNumeroAfiliado(numeroAfiliado);
		} catch (MongoException ex) {
		}
		return null;
	}
	
	public PacienteDto getUltimoPaciente() {
		try {
			List<PacienteDto> listaPacientes = pacienteRepository.findAll(new Sort(Direction.DESC, "numeroAfiliado"));
			if (listaPacientes != null && listaPacientes.size() != 0) {
				return listaPacientes.get(0);				
			}
		} catch (MongoException ex) {
		}
		return null;
	}
}
