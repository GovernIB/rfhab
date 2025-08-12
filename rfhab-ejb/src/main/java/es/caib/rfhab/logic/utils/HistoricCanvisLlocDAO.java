package es.caib.rfhab.logic.utils;

import java.sql.Timestamp;
import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;

/**
 * 
 * @autor jpou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
public class HistoricCanvisLlocDAO {

	private String usuariId = null;
	private String observacions = null;
	private String numeroCai = null;
	private Timestamp dataCreacio = null;
	private HistoricLlocDAO nou = null;
	private HistoricLlocDAO vell = null;

	// Getters i setters
	public String getUsuariId() {
		return usuariId;
	}

	public void setUsuariId(String usuariId) {
		this.usuariId = usuariId;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public String getNumeroCai() {
		return numeroCai;
	}

	public void setNumeroCai(String numeroCai) {
		this.numeroCai = numeroCai;
	}

	public Timestamp getDataCreacio() {
		return dataCreacio;
	}

	public void setDataCreacio(Timestamp dataCreacio) {
		this.dataCreacio = dataCreacio;
	}

	public HistoricLlocDAO getNou() {
		return nou;
	}

	public void setNou(HistoricLlocDAO nou) {
		this.nou = nou;
	}

	public HistoricLlocDAO getVell() {
		return vell;
	}

	public void setVell(HistoricLlocDAO vell) {
		this.vell = vell;
	}

	// Constructors
	public HistoricCanvisLlocDAO() {
		super();
	}

	public HistoricCanvisLlocDAO(String usuariId, String observacions, String numeroCai, Timestamp dataCreacio)
			throws I18NException {
		this.usuariId = usuariId;
		this.numeroCai = numeroCai;
		this.dataCreacio = dataCreacio;

		if (observacions != null) {
			try {
				List<HistoricLlocDAO> historicLloc = JsonUtils
						.listFromJson(observacions, HistoricLlocDAO[].class);
				HistoricLlocDAO oldLloc = historicLloc.get(0);
				// log.info("oldLloc: " + oldLloc.toString());
				this.vell = oldLloc;
				HistoricLlocDAO nouLloc = historicLloc.get(1);
				// log.info("nouLloc: " + nouLloc.toString());
				this.nou = nouLloc;
			} catch (JsonParseException | IndexOutOfBoundsException e) {
				this.observacions = observacions;
			} catch (Exception e) {
				// log.error("Error al parsejar el JSON de les observacions", e);
				throw new I18NException(e, "historiclloc.error.parsejason");
			}
		}

	}
}
