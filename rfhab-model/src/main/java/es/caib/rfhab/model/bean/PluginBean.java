
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Plugin;


public class PluginBean implements Plugin {



	long pluginID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	java.lang.String classe;
	long entitatID;
	java.lang.String properties;
	boolean actiu;
	java.sql.Timestamp dataCreacio;
	java.lang.String tipus;


  /** Constructor Buit */
  public PluginBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginBean(long pluginID , java.lang.String nom , java.lang.String descripcio , java.lang.String classe , long entitatID , java.lang.String properties , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.String tipus) {
    this.pluginID=pluginID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.classe=classe;
    this.entitatID=entitatID;
    this.properties=properties;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public PluginBean(java.lang.String nom , java.lang.String descripcio , java.lang.String classe , long entitatID , java.lang.String properties , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.String tipus) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.classe=classe;
    this.entitatID=entitatID;
    this.properties=properties;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public PluginBean(long pluginID , java.lang.String nom , java.lang.String descripcio , long entitatID , boolean actiu) {
    this.pluginID=pluginID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  public PluginBean(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setClasse(__bean.getClasse());
    this.setEntitatID(__bean.getEntitatID());
    this.setProperties(__bean.getProperties());
    this.setActiu(__bean.isActiu());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setTipus(__bean.getTipus());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getProperties() {
		return(properties);
	};
	public void setProperties(java.lang.String _properties_) {
		this.properties = _properties_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
	};



  // ======================================

  public static PluginBean toBean(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginBean __tmp = new PluginBean();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setProperties(__bean.getProperties());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}



}
