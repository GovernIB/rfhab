package es.caib.rfhab.logic.utils.TicketAccesDto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @autor jpou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
public class RpersonaInfo implements java.io.Serializable {
	private String apellido1 = null;
	private String apellido2 = null;
	private String nif = null;
	private String nombre = null;

	public RpersonaInfo() {
		super();
	}

	public RpersonaInfo(String apellido1, String apellido2, String nif, String nombre) {
		super();
		// Inicialitza els camps
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nif = nif;
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
