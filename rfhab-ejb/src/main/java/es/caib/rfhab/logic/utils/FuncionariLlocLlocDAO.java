package es.caib.rfhab.logic.utils;

import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;

/**
 * 
 * @autor jpou
 *
 */
public class FuncionariLlocLlocDAO implements java.io.Serializable {

	private Long llocID = null;
	private String codiLloc = null;
	private String nom = null;
	private Integer personalOamr = null;
	private java.sql.Date dataInici = null;
	private java.sql.Date dataFi = null;

	// Getters i setters
	public Long getLlocID() {
		return llocID;
	}

	public void setLlocID(Long llocID) {
		this.llocID = llocID;
	}

	public String getCodiLloc() {
		return codiLloc;
	}

	public void setCodiLloc(String codiLloc) {
		this.codiLloc = codiLloc;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getPersonalOamr() {
		return personalOamr;
	}

	public void setPersonalOamr(Integer personalOamr) {
		this.personalOamr = personalOamr;
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

	// Constructors
	public FuncionariLlocLlocDAO() {
		super();
	}

	public FuncionariLlocLlocDAO(Lloc lloc, FuncionariLloc funcionariLloc) {
		super();
		if (lloc != null) {
			this.llocID = lloc.getLlocID();
			this.codiLloc = lloc.getCodiLloc();
			this.nom = lloc.getNom();
			this.personalOamr = lloc.getPersonalOamr();
			this.dataInici = funcionariLloc.getDataInici();
			this.dataFi = funcionariLloc.getDataFi();
		}
	}
}
