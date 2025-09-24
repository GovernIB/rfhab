package es.caib.rfhab.logic.utils.TicketAccesDto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @autor jpou
 *
 */
public class InfoTicketDto implements java.io.Serializable {

	private String idActuacionFH = null;
	private RfuncionarioHabilitadoInfo funcionarioHabilitado = null;
	private RpersonaInfo interesado = null;
	private RpersonaInfo representante = null;
	private RtramiteFH tramiteFH = null;

	// Getters i setters
	@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
	public String getIdActuacionFH() {
		return idActuacionFH;
	}

	public void setIdActuacionFH(String idActuacionFH) {
		this.idActuacionFH = idActuacionFH;
	}

	public RfuncionarioHabilitadoInfo getFuncionarioHabilitado() {
		return funcionarioHabilitado;
	}

	public void setFuncionarioHabilitado(RfuncionarioHabilitadoInfo funcionarioHabilitado) {
		this.funcionarioHabilitado = funcionarioHabilitado;
	}

	public RpersonaInfo getInteresado() {
		return interesado;
	}

	public void setInteresado(RpersonaInfo interesado) {
		this.interesado = interesado;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
	public RpersonaInfo getRepresentante() {
		return representante;
	}

	public void setRepresentante(RpersonaInfo representante) {
		this.representante = representante;
	}

	public RtramiteFH getTramiteFH() {
		return tramiteFH;
	}

	public void setTramiteFH(RtramiteFH tramiteFH) {
		this.tramiteFH = tramiteFH;
	}

	// Constructors
	public InfoTicketDto() {
		super();
	}

	public InfoTicketDto(String idActuacionFH, RfuncionarioHabilitadoInfo funcionarioHabilitado,
			RpersonaInfo interesado,
			RpersonaInfo representante, RtramiteFH tramiteFH) {
		super();
		this.idActuacionFH = idActuacionFH;
		this.funcionarioHabilitado = funcionarioHabilitado;
		this.interesado = interesado;
		this.representante = representante;
		this.tramiteFH = tramiteFH;
	}
}
