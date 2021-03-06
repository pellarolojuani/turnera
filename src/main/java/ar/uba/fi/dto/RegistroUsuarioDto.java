package ar.uba.fi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class RegistroUsuarioDto {
	@Id
	private String id;
	private String nombreUsuario;
	private String contrasenia;
	private String tipoDocumento;
	private String documento;
	private String sexo;
	private String nombreApellido;
	private String fechaNacimiento;
	private String mail;
	private String telefono;

	public RegistroUsuarioDto(String nombreUsuario, String contrasenia, String tipoDocumento, String documento,
			String sexo, String nombreApellido, String fechaNacimiento) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.sexo = sexo;
		this.nombreApellido = nombreApellido;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public RegistroUsuarioDto(String nombreUsuario, String contrasenia, String tipoDocumento, String documento,
			String sexo, String nombreApellido, String fechaNacimiento, String mail) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.sexo = sexo;
		this.nombreApellido = nombreApellido;
		this.fechaNacimiento = fechaNacimiento;
		this.mail = mail;
	}
	
	
	public RegistroUsuarioDto(String nombreUsuario, String contrasenia, String tipoDocumento, String documento,
			String sexo, String nombreApellido, String fechaNacimiento, String mail, String telefono) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.sexo = sexo;
		this.nombreApellido = nombreApellido;
		this.fechaNacimiento = fechaNacimiento;
		this.mail = mail;
		this.telefono = telefono;
	}

	public RegistroUsuarioDto() {
		super();
	};

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
