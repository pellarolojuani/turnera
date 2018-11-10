package ar.uba.fi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pacientes")
public class PacienteDto {
	@Id
	private String id;
	private String tipoDocumento;
	private String documento;
	private String numeroAfiliado;
	private UsuarioDto usuario;
	
	
	public PacienteDto(String tipoDocumento, String documento, String numeroAfiliado, UsuarioDto usuario) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.numeroAfiliado = numeroAfiliado;
		this.usuario = usuario;
	}
	
	
	public PacienteDto(String tipoDocumento, String documento, String numeroAfiliado) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.numeroAfiliado = numeroAfiliado;
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
	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}
	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
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
	

}
