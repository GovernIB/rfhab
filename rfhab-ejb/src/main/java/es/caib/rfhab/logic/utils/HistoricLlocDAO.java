package es.caib.rfhab.logic.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Lloc;

/**
 * 
 * @autor jagarcia
 *
 */

public class HistoricLlocDAO {

	private long llocID;
	private String codiLloc;
	private String nom;
	private long unitatID;
	private int personalOamr;
	private long entitatID;
	private String observacions;
	private String numeroCai;
	private Timestamp dataCreacio;
	private Timestamp dataBaixa;
	
	@JsonIgnore
	private List<Funcionari> funcionaris = new ArrayList<Funcionari>();

	public long getLlocID() {
		return llocID;
	}

	public void setLlocID(long llocID) {
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

	public long getUnitatID() {
		return unitatID;
	}

	public void setUnitatID(long unitatID) {
		this.unitatID = unitatID;
	}

	public int getPersonalOamr() {
		return personalOamr;
	}

	public void setPersonalOamr(int personalOamr) {
		this.personalOamr = personalOamr;
	}

	public long getEntitatID() {
		return entitatID;
	}

	public void setEntitatID(long entitatID) {
		this.entitatID = entitatID;
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

	public Timestamp getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Timestamp dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public List<Funcionari> getFuncionaris() {
		return funcionaris;
	}

	public void setFuncionaris(List<Funcionari> funcionaris) {
		this.funcionaris = funcionaris;
	}
	
	public void addFuncionari(Funcionari funcionari) {
		this.funcionaris.add(funcionari);
	}
	
	public void removeFuncionari(Funcionari funcionari) {
		this.funcionaris.remove(funcionari);
	}

	public HistoricLlocDAO() {
		super();
	}

	public HistoricLlocDAO ( Lloc lloc ) {
		this.llocID = lloc.getLlocID();
        this.codiLloc = lloc.getCodiLloc();
        this.nom = lloc.getNom();
        this.unitatID = lloc.getUnitatID();
        this.personalOamr = lloc.getPersonalOamr();
        this.entitatID = lloc.getEntitatID();
        this.observacions = lloc.getObservacions();
        this.dataCreacio = lloc.getDataCreacio();
        this.dataBaixa = lloc.getDataBaixa();
    }
	
	public HistoricLlocDAO ( Lloc lloc, List<Funcionari> funcionaris ) {
		this.llocID = lloc.getLlocID();
        this.codiLloc = lloc.getCodiLloc();
        this.nom = lloc.getNom();
        this.unitatID = lloc.getUnitatID();
        this.personalOamr = lloc.getPersonalOamr();
        this.entitatID = lloc.getEntitatID();
        this.observacions = lloc.getObservacions();
        this.dataCreacio = lloc.getDataCreacio();
        this.dataBaixa = lloc.getDataBaixa();
        this.funcionaris = funcionaris;
    }
	
		
}
