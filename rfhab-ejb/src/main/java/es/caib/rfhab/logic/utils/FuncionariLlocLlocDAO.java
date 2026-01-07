package es.caib.rfhab.logic.utils;

import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;

/**
 * 
 * @autor jpou
 *
 */
public class FuncionariLlocLlocDAO implements java.io.Serializable {

	private Lloc lloc = null;
	private java.sql.Date dataInici = null;
	private java.sql.Date dataFi = null;
	private String numeroCai = null;

	// Getters i setters
	public Lloc getLloc() {
		return lloc;
	}

	public void setLlocID(Lloc lloc) {
		this.lloc = lloc;
	}

	public java.sql.Date getDataInici() {
		return dataInici;
	}

	public void setDataInici(java.sql.Date dataInici) {
		this.dataInici = dataInici;
	}

	public java.sql.Date getDataFi() {
		return dataFi;
	}

	public void setDataFi(java.sql.Date dataFi) {
		this.dataFi = dataFi;
	}

	public String getNumeroCai() {
		return numeroCai;
	}

	public void setNumeroCai(String numeroCai) {
		this.numeroCai = numeroCai;
	}

	// Constructors
	public FuncionariLlocLlocDAO() {
		super();
	}

	public FuncionariLlocLlocDAO(Lloc lloc, FuncionariLloc funcionariLloc, String numeroCai) {
		super();
		if (lloc != null) {
			this.lloc = lloc;
			this.dataInici = funcionariLloc.getDataInici();
			this.dataFi = funcionariLloc.getDataFi();
		}
		this.numeroCai = numeroCai;
	}
}
