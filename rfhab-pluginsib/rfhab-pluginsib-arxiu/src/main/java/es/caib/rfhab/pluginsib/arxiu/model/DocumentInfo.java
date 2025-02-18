package es.caib.rfhab.pluginsib.arxiu.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.caib.rfhab.model.entity.Fitxer;

public class DocumentInfo implements Serializable{

	private String nom; 
	private List<String> organs;
	private List<String> interessats;
	private Map<String,Object> metadades; 
	private String origen;
	private String estatElaboracio;
	private String tipusDocumental;
	private Fitxer fitxer;
	private String numeroRegistre;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<String> getOrgans() {
		return organs;
	}

	public void setOrgans(List<String> organs) {
		this.organs = organs;
	}

	public List<String> getInteressats() {
		return interessats;
	}

	public void setInteressats(List<String> interessats) {
		this.interessats = interessats;
	}

	public Map<String, Object> getMetadades() {
		return metadades;
	}

	public void setMetadades(Map<String, Object> metadades) {
		this.metadades = metadades;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getEstatElaboracio() {
		return estatElaboracio;
	}

	public void setEstatElaboracio(String estatElaboracio) {
		this.estatElaboracio = estatElaboracio;
	}

	public String getTipusDocumental() {
		return tipusDocumental;
	}

	public void setTipusDocumental(String tipusDocumental) {
		this.tipusDocumental = tipusDocumental;
	}

	public Fitxer getFitxer() {
		return fitxer;
	}

	public void setFitxer(Fitxer fitxer) {
		this.fitxer = fitxer;
	}

	public String getNumeroRegistre() {
		return numeroRegistre;
	}

	public void setNumeroRegistre(String numeroRegistre) {
		this.numeroRegistre = numeroRegistre;
	}

	public DocumentInfo() {
		super();
	}
	
	public DocumentInfo(String nom, List<String> organs, List<String> interessats, Map<String, Object> metadades,
			String origen, String estatElaboracio, String tipusDocumental, Fitxer fitxer, String numeroRegistre) {
		super();
		this.nom = nom;
		this.organs = organs;
		this.interessats = interessats;
		this.metadades = metadades;
		this.origen = origen;
		this.estatElaboracio = estatElaboracio;
		this.tipusDocumental = tipusDocumental;
		this.fitxer = fitxer;
		this.numeroRegistre = numeroRegistre;
	}

	@Override
	public String toString() {
		return "DocumentInfo [nom=" + nom + ", organs=" + organs + ", interessats=" + interessats + ", metadades="
				+ metadades + ", origen=" + origen + ", estatElaboracio=" + estatElaboracio + ", tipusDocumental="
				+ tipusDocumental + ", fitxer=" + fitxer + ", numeroRegistre=" + numeroRegistre + "]";
	}
	
	
}
