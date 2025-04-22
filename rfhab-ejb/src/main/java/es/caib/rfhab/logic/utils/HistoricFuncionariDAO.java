package es.caib.rfhab.logic.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Rol;

/**
 * 
 * @autor jagarcia
 *
 */

public class HistoricFuncionariDAO implements java.io.Serializable{

	private String numero;
	private String nom;
	private String llinatge1;
	private String llinatge2;
	private int tipusIdentificador;
	private String identificador;
	private String usuari;
	private String correu;
	private String observacions;
	
	@JsonIgnore
	private List<Rol> rols;

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

	public int getTipusIdentificador() {
		return tipusIdentificador;
	}

	public void setTipusIdentificador(int tipusIdentificador) {
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
		this.numero = funcionari.getNumero();
		this.nom = funcionari.getNom();
		this.llinatge1 = funcionari.getLlinatge1();
		this.llinatge2 = funcionari.getLlinatge2();
		this.tipusIdentificador = funcionari.getTipusIdentificador();
		this.identificador = funcionari.getIdentificador();
		this.usuari = funcionari.getUsuari();
		this.correu = funcionari.getCorreu();
		this.observacions = funcionari.getObservacions();
		this.rols = rols;
	}

}
