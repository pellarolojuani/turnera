package ar.uba.fi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comprobante")
public class ComprobanteDto {
	
	@Id
	private String id;
	private Integer contador;
	
	public ComprobanteDto(Integer contador) {
		super();
		this.contador = contador;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getContador() {
		return contador;
	}
	public void setContador(Integer contador) {
		this.contador = contador;
	}

}
