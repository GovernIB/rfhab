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
public class HistoricCanvisFuncionariDAO {

	private Long historicId = null;
	private String usuariId = null;
	private String observacions = null;
	private String numeroCai = null;
	private Timestamp dataCreacio = null;
	private HistoricFuncionariDAO nou = null;
	private HistoricFuncionariDAO vell = null;

	// Getters i setters
	public Long getHistoricId() {
		return historicId;
	}

	public void setHistoricId(Long historicId) {
		this.historicId = historicId;
	}

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

	public HistoricFuncionariDAO getNou() {
		return nou;
	}

	public void setNou(HistoricFuncionariDAO nou) {
		this.nou = nou;
	}

	public HistoricFuncionariDAO getVell() {
		return vell;
	}

	public void setVell(HistoricFuncionariDAO vell) {
		this.vell = vell;
	}

	// Constructors
	public HistoricCanvisFuncionariDAO() {
		super();
	}

	public HistoricCanvisFuncionariDAO(Long historicId, String usuariId, String observacions, String numeroCai,
			Timestamp dataCreacio)
			throws I18NException {
		this.historicId = historicId;
		this.usuariId = usuariId;
		this.numeroCai = numeroCai;
		this.dataCreacio = dataCreacio;

		if (observacions != null) {
			try {
				List<HistoricFuncionariDAO> historicLloc = JsonUtils
						.listFromJson(observacions, HistoricFuncionariDAO[].class);
				HistoricFuncionariDAO oldLloc = historicLloc.get(0);
				// log.info("oldLloc: " + oldLloc.toString());
				this.vell = oldLloc;
				HistoricFuncionariDAO nouLloc = historicLloc.get(1);
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
