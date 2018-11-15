package ar.uba.fi.dto;

public class ResultadoDto {
	
	private Boolean resultado;
	private String mensaje;
	
	public ResultadoDto(Boolean resultado, String mensaje) {
		super();
		this.resultado = resultado;
		this.mensaje = mensaje;
	}
	public Boolean getResultado() {
		return resultado;
	}
	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
