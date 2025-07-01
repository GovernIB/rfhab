package es.caib.rfhab.logic.utils.TicketAccesDto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @autor jpou
 *
 */
public class RtramiteFH implements java.io.Serializable {
	private String idTramiteCatalogo = null;
	private String idioma = null;
	private String parametros = null;
	private Boolean servicioCatalogo = null;
	private String tramite = null;
	private Integer version = null;

	public RtramiteFH() {
		super();
	}

	public RtramiteFH(String idTramiteCatalogo, String idioma, String parametros, Boolean servicioCatalogo,
			String tramite, Integer version) {
		super();
		// Inicialitza els camps
		this.idTramiteCatalogo = idTramiteCatalogo;
		this.idioma = idioma;
		this.parametros = parametros;
		this.servicioCatalogo = servicioCatalogo;
		this.tramite = tramite;
		this.version = version;
	}

	public String getIdTramiteCatalogo() {
		return idTramiteCatalogo;
	}

	public void setIdTramiteCatalogo(String idTramiteCatalogo) {
		this.idTramiteCatalogo = idTramiteCatalogo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public Boolean getServicioCatalogo() {
		return servicioCatalogo;
	}

	public void setServicioCatalogo(Boolean servicioCatalogo) {
		this.servicioCatalogo = servicioCatalogo;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
