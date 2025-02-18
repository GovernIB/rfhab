
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Unitat;


public class UnitatBean implements Unitat {



	long unitatID;// PK
	java.lang.String codi;
	int versio;
	java.lang.String denominacio;
	java.lang.String cooficial;
	java.lang.String arrel;
	java.lang.Integer arrelVersio;
	java.lang.String superior;
	java.lang.Integer superiorVersio;
	java.lang.String estat;


  /** Constructor Buit */
  public UnitatBean() {
  }

  /** Constructor amb tots els camps  */
  public UnitatBean(long unitatID , java.lang.String codi , int versio , java.lang.String denominacio , java.lang.String cooficial , java.lang.String arrel , java.lang.Integer arrelVersio , java.lang.String superior , java.lang.Integer superiorVersio , java.lang.String estat) {
    this.unitatID=unitatID;
    this.codi=codi;
    this.versio=versio;
    this.denominacio=denominacio;
    this.cooficial=cooficial;
    this.arrel=arrel;
    this.arrelVersio=arrelVersio;
    this.superior=superior;
    this.superiorVersio=superiorVersio;
    this.estat=estat;
}
  /** Constructor sense valors autoincrementals */
  public UnitatBean(java.lang.String codi , int versio , java.lang.String denominacio , java.lang.String cooficial , java.lang.String arrel , java.lang.Integer arrelVersio , java.lang.String superior , java.lang.Integer superiorVersio , java.lang.String estat) {
    this.codi=codi;
    this.versio=versio;
    this.denominacio=denominacio;
    this.cooficial=cooficial;
    this.arrel=arrel;
    this.arrelVersio=arrelVersio;
    this.superior=superior;
    this.superiorVersio=superiorVersio;
    this.estat=estat;
}
  /** Constructor dels valors Not Null */
  public UnitatBean(long unitatID , java.lang.String codi , int versio , java.lang.String denominacio) {
    this.unitatID=unitatID;
    this.codi=codi;
    this.versio=versio;
    this.denominacio=denominacio;
}
  public UnitatBean(Unitat __bean) {
    this.setUnitatID(__bean.getUnitatID());
    this.setCodi(__bean.getCodi());
    this.setVersio(__bean.getVersio());
    this.setDenominacio(__bean.getDenominacio());
    this.setCooficial(__bean.getCooficial());
    this.setArrel(__bean.getArrel());
    this.setArrelVersio(__bean.getArrelVersio());
    this.setSuperior(__bean.getSuperior());
    this.setSuperiorVersio(__bean.getSuperiorVersio());
    this.setEstat(__bean.getEstat());
	}

	public long getUnitatID() {
		return(unitatID);
	};
	public void setUnitatID(long _unitatID_) {
		this.unitatID = _unitatID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public int getVersio() {
		return(versio);
	};
	public void setVersio(int _versio_) {
		this.versio = _versio_;
	};

	public java.lang.String getDenominacio() {
		return(denominacio);
	};
	public void setDenominacio(java.lang.String _denominacio_) {
		this.denominacio = _denominacio_;
	};

	public java.lang.String getCooficial() {
		return(cooficial);
	};
	public void setCooficial(java.lang.String _cooficial_) {
		this.cooficial = _cooficial_;
	};

	public java.lang.String getArrel() {
		return(arrel);
	};
	public void setArrel(java.lang.String _arrel_) {
		this.arrel = _arrel_;
	};

	public java.lang.Integer getArrelVersio() {
		return(arrelVersio);
	};
	public void setArrelVersio(java.lang.Integer _arrelVersio_) {
		this.arrelVersio = _arrelVersio_;
	};

	public java.lang.String getSuperior() {
		return(superior);
	};
	public void setSuperior(java.lang.String _superior_) {
		this.superior = _superior_;
	};

	public java.lang.Integer getSuperiorVersio() {
		return(superiorVersio);
	};
	public void setSuperiorVersio(java.lang.Integer _superiorVersio_) {
		this.superiorVersio = _superiorVersio_;
	};

	public java.lang.String getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.String _estat_) {
		this.estat = _estat_;
	};



  // ======================================

  public static UnitatBean toBean(Unitat __bean) {
    if (__bean == null) { return null;}
    UnitatBean __tmp = new UnitatBean();
    __tmp.setUnitatID(__bean.getUnitatID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setDenominacio(__bean.getDenominacio());
    __tmp.setCooficial(__bean.getCooficial());
    __tmp.setArrel(__bean.getArrel());
    __tmp.setArrelVersio(__bean.getArrelVersio());
    __tmp.setSuperior(__bean.getSuperior());
    __tmp.setSuperiorVersio(__bean.getSuperiorVersio());
    __tmp.setEstat(__bean.getEstat());
		return __tmp;
	}



}
