package ar.uba.fi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pacientes")
public class PacienteDto {
	@Id
	private String id;
	private String tipoDocumento;
	private String documento;
	private String sexo;
	private String numeroAfiliado;
	private String fechaNacimiento;
	private UsuarioDto usuario;
	
	
	public PacienteDto(String tipoDocumento, String documento, UsuarioDto usuario) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.usuario = usuario;
	}
	
	
	public PacienteDto(String tipoDocumento, String documento) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
	}


	public PacienteDto(String id, String tipoDocumento, String documento, String sexo, String fechaNacimiento,
			UsuarioDto usuario) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.usuario = usuario;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public UsuarioDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}


	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}

}
