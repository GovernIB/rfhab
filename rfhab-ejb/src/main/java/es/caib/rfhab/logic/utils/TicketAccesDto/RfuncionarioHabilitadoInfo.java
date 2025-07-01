package es.caib.rfhab.logic.utils.TicketAccesDto;

import com.fasterxml.jackson.annotation.JsonInclude;

import es.caib.rfhab.model.entity.Funcionari;

/**
 * 
 * @autor jpou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
public class RfuncionarioHabilitadoInfo implements java.io.Serializable {
	private String nif = null;
	private String nombre = null;
	private String apellido1 = null;
	private String apellido2 = null;
	private String dir3 = null;
	private String username = null;

	public RfuncionarioHabilitadoInfo() {
		super();
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

	public String getDir3() {
		return dir3;
	}

	public void setDir3(String dir3) {
		this.dir3 = dir3;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static RfuncionarioHabilitadoInfo fromFuncionari(Funcionari funcionari, String codiDir3) {
		RfuncionarioHabilitadoInfo info = new RfuncionarioHabilitadoInfo();
		info.setNif(funcionari.getIdentificador());
		info.setNombre(funcionari.getNom());
		info.setApellido1(funcionari.getLlinatge1());
		info.setApellido2(funcionari.getLlinatge2());
		info.setDir3(codiDir3);
		info.setUsername(funcionari.getUsuari());
		return info;
	}
}