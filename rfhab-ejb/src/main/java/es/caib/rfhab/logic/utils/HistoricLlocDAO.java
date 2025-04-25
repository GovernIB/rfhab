package es.caib.rfhab.logic.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Lloc;

/**
 * 
 * @autor jagarcia
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
public class HistoricLlocDAO {

	private Long llocID = null;
	private String codiLloc = null;
	private String nom = null;
	private Long unitatID = null;
	private Integer personalOamr = null;
	private Long entitatID = null;
	private String observacions = null;
	private String numeroCai = null;
	private Timestamp dataCreacio = null;
	private Timestamp dataBaixa = null;

	@JsonIgnore
	private List<Funcionari> funcionaris = new ArrayList<>();

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

	public Long getUnitatID() {
		return unitatID;
	}

	public void setUnitatID(Long unitatID) {
		this.unitatID = unitatID;
	}

	public Integer getPersonalOamr() {
		return personalOamr;
	}

	public void setPersonalOamr(Integer personalOamr) {
		this.personalOamr = personalOamr;
	}

	public Long getEntitatID() {
		return entitatID;
	}

	public void setEntitatID(Long entitatID) {
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

	// Constructors
	public HistoricLlocDAO() {
		super();
	}

	public HistoricLlocDAO(Lloc lloc) {
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

	public HistoricLlocDAO(Lloc lloc, List<Funcionari> funcionaris) {
		this(lloc);
		this.funcionaris = funcionaris;
	}
}
