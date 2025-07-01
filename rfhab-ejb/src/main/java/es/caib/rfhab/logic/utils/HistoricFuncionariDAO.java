package es.caib.rfhab.logic.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Rol;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
public class HistoricFuncionariDAO implements java.io.Serializable {

	private String numero = null;
	private String nom = null;
	private String llinatge1 = null;
	private String llinatge2 = null;
	private Integer tipusIdentificador = null;
	private String identificador = null;
	private String usuari = null;
	private String correu = null;
	private String observacions = null;

	@JsonIgnore
	private List<Rol> rols;

	// Getters i setters
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Integer getTipusIdentificador() {
		return tipusIdentificador;
	}

	public void setTipusIdentificador(Integer tipusIdentificador) {
		this.tipusIdentificador = tipusIdentificador;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getCorreu() {
		return correu;
	}

	public void setCorreu(String correu) {
		this.correu = correu;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public List<Rol> getRols() {
		return rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	// Constructors
	public HistoricFuncionariDAO() {
		super();
	}

	public HistoricFuncionariDAO(Funcionari funcionari) {
		this.numero = funcionari.getNumero();
		this.nom = funcionari.getNom();
		this.llinatge1 = funcionari.getLlinatge1();
		this.llinatge2 = funcionari.getLlinatge2();
		this.tipusIdentificador = funcionari.getTipusIdentificador();
		this.identificador = funcionari.getIdentificador();
		this.usuari = funcionari.getUsuari();
		this.correu = funcionari.getCorreu();
		this.observacions = funcionari.getObservacions();
	}

	public HistoricFuncionariDAO(Funcionari funcionari, List<Rol> rols) {
		this(funcionari);
		this.rols = rols;
	}
}
