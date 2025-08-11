package es.caib.rfhab.logic.utils;

import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;

/**
 * 
 * @autor jpou
 *
 */
public class FuncionariLlocDAO implements java.io.Serializable {

	private Long funcionariID = null;
	private String numero = null;
	private String identificador = null;
	private Integer tipusIdentificador = null;
	private String nom = null;
	private String llinatge1 = null;
	private String llinatge2 = null;
	private java.sql.Date dataInici = null;
	private java.sql.Date dataFi = null;

	// Getters i setters
	public Long getFuncionariID() {
		return funcionariID;
	}

	public void setFuncionariID(Long funcionariID) {
		this.funcionariID = funcionariID;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getTipusIdentificador() {
		return tipusIdentificador;
	}

	public void setTipusIdentificador(Integer tipusIdentificador) {
		this.tipusIdentificador = tipusIdentificador;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLlinatge1() {
		return llinatge1;
	}

	public void setLlinatge1(String llinatge1) {
		this.llinatge1 = llinatge1;
	}

	public String getLlinatge2() {
		return llinatge2;
	}

	public void setLlinatge2(String llinatge2) {
		this.llinatge2 = llinatge2;
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
	public FuncionariLlocDAO() {
		super();
	}

	public FuncionariLlocDAO(Funcionari funcionari, FuncionariLloc funcionariLloc) {
		super();
		if (funcionari != null) {
			this.funcionariID = funcionari.getFuncionariID();
			this.numero = funcionari.getNumero();
			this.identificador = funcionari.getIdentificador();
			this.tipusIdentificador = funcionari.getTipusIdentificador();
			this.nom = funcionari.getNom();
			this.llinatge1 = funcionari.getLlinatge1();
			this.llinatge2 = funcionari.getLlinatge2();
			this.dataInici = funcionariLloc.getDataInici();
			this.dataFi = funcionariLloc.getDataFi();
		}
	}
}
