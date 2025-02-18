
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Lloc;


public class LlocBean implements Lloc {



	long llocID;// PK
	java.lang.String codiLloc;
	java.lang.String nom;
	int personalOamr;
	long entitatID;
	java.sql.Timestamp dataCreacio;
	java.sql.Timestamp dataBaixa;
	java.lang.String observacions;
	long unitatID;


  /** Constructor Buit */
  public LlocBean() {
  }

  /** Constructor amb tots els camps  */
  public LlocBean(long llocID , java.lang.String codiLloc , java.lang.String nom , int personalOamr , long entitatID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataBaixa , java.lang.String observacions , long unitatID) {
    this.llocID=llocID;
    this.codiLloc=codiLloc;
    this.nom=nom;
    this.personalOamr=personalOamr;
    this.entitatID=entitatID;
    this.dataCreacio=dataCreacio;
    this.dataBaixa=dataBaixa;
    this.observacions=observacions;
    this.unitatID=unitatID;
}
  /** Constructor sense valors autoincrementals */
  public LlocBean(java.lang.String codiLloc , java.lang.String nom , int personalOamr , long entitatID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataBaixa , java.lang.String observacions , long unitatID) {
    this.codiLloc=codiLloc;
    this.nom=nom;
    this.personalOamr=personalOamr;
    this.entitatID=entitatID;
    this.dataCreacio=dataCreacio;
    this.dataBaixa=dataBaixa;
    this.observacions=observacions;
    this.unitatID=unitatID;
}
  /** Constructor dels valors Not Null */
  public LlocBean(long llocID , java.lang.String codiLloc , java.lang.String nom , int personalOamr , long entitatID , java.sql.Timestamp dataCreacio , long unitatID) {
    this.llocID=llocID;
    this.codiLloc=codiLloc;
    this.nom=nom;
    this.personalOamr=personalOamr;
    this.entitatID=entitatID;
    this.dataCreacio=dataCreacio;
    this.unitatID=unitatID;
}
  public LlocBean(Lloc __bean) {
    this.setLlocID(__bean.getLlocID());
    this.setCodiLloc(__bean.getCodiLloc());
    this.setNom(__bean.getNom());
    this.setPersonalOamr(__bean.getPersonalOamr());
    this.setEntitatID(__bean.getEntitatID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataBaixa(__bean.getDataBaixa());
    this.setObservacions(__bean.getObservacions());
    this.setUnitatID(__bean.getUnitatID());
	}

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public java.lang.String getCodiLloc() {
		return(codiLloc);
	};
	public void setCodiLloc(java.lang.String _codiLloc_) {
		this.codiLloc = _codiLloc_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public int getPersonalOamr() {
		return(personalOamr);
	};
	public void setPersonalOamr(int _personalOamr_) {
		this.personalOamr = _personalOamr_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public long getUnitatID() {
		return(unitatID);
	};
	public void setUnitatID(long _unitatID_) {
		this.unitatID = _unitatID_;
	};



  // ======================================

  public static LlocBean toBean(Lloc __bean) {
    if (__bean == null) { return null;}
    LlocBean __tmp = new LlocBean();
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setCodiLloc(__bean.getCodiLloc());
    __tmp.setNom(__bean.getNom());
    __tmp.setPersonalOamr(__bean.getPersonalOamr());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataBaixa(__bean.getDataBaixa());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setUnitatID(__bean.getUnitatID());
		return __tmp;
	}



}
