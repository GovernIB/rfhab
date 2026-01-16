
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Lloc;


public class LlocBean implements Lloc {



	long llocID;// PK
	java.lang.String codiLlocPropi;
	java.lang.String codiLloc;
	java.lang.String expansio;
	java.lang.String nom;
	long entitatID;
	long unitatID;
	java.sql.Timestamp dataalta;
	java.sql.Timestamp dataCreacio;
	java.sql.Timestamp dataBaixa;
	int personalOamr;
	java.lang.String observacions;


  /** Constructor Buit */
  public LlocBean() {
  }

  /** Constructor amb tots els camps  */
  public LlocBean(long llocID , java.lang.String codiLlocPropi , java.lang.String codiLloc , java.lang.String expansio , java.lang.String nom , long entitatID , long unitatID , java.sql.Timestamp dataalta , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataBaixa , int personalOamr , java.lang.String observacions) {
    this.llocID=llocID;
    this.codiLlocPropi=codiLlocPropi;
    this.codiLloc=codiLloc;
    this.expansio=expansio;
    this.nom=nom;
    this.entitatID=entitatID;
    this.unitatID=unitatID;
    this.dataalta=dataalta;
    this.dataCreacio=dataCreacio;
    this.dataBaixa=dataBaixa;
    this.personalOamr=personalOamr;
    this.observacions=observacions;
}
  /** Constructor sense valors autoincrementals */
  public LlocBean(java.lang.String codiLlocPropi , java.lang.String codiLloc , java.lang.String expansio , java.lang.String nom , long entitatID , long unitatID , java.sql.Timestamp dataalta , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataBaixa , int personalOamr , java.lang.String observacions) {
    this.codiLlocPropi=codiLlocPropi;
    this.codiLloc=codiLloc;
    this.expansio=expansio;
    this.nom=nom;
    this.entitatID=entitatID;
    this.unitatID=unitatID;
    this.dataalta=dataalta;
    this.dataCreacio=dataCreacio;
    this.dataBaixa=dataBaixa;
    this.personalOamr=personalOamr;
    this.observacions=observacions;
}
  /** Constructor dels valors Not Null */
  public LlocBean(long llocID , java.lang.String codiLlocPropi , java.lang.String nom , long entitatID , long unitatID , java.sql.Timestamp dataCreacio , int personalOamr) {
    this.llocID=llocID;
    this.codiLlocPropi=codiLlocPropi;
    this.nom=nom;
    this.entitatID=entitatID;
    this.unitatID=unitatID;
    this.dataCreacio=dataCreacio;
    this.personalOamr=personalOamr;
}
  public LlocBean(Lloc __bean) {
    this.setLlocID(__bean.getLlocID());
    this.setCodiLlocPropi(__bean.getCodiLlocPropi());
    this.setCodiLloc(__bean.getCodiLloc());
    this.setExpansio(__bean.getExpansio());
    this.setNom(__bean.getNom());
    this.setEntitatID(__bean.getEntitatID());
    this.setUnitatID(__bean.getUnitatID());
    this.setDataalta(__bean.getDataalta());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataBaixa(__bean.getDataBaixa());
    this.setPersonalOamr(__bean.getPersonalOamr());
    this.setObservacions(__bean.getObservacions());
	}

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public java.lang.String getCodiLlocPropi() {
		return(codiLlocPropi);
	};
	public void setCodiLlocPropi(java.lang.String _codiLlocPropi_) {
		this.codiLlocPropi = _codiLlocPropi_;
	};

	public java.lang.String getCodiLloc() {
		return(codiLloc);
	};
	public void setCodiLloc(java.lang.String _codiLloc_) {
		this.codiLloc = _codiLloc_;
	};

	public java.lang.String getExpansio() {
		return(expansio);
	};
	public void setExpansio(java.lang.String _expansio_) {
		this.expansio = _expansio_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public long getUnitatID() {
		return(unitatID);
	};
	public void setUnitatID(long _unitatID_) {
		this.unitatID = _unitatID_;
	};

	public java.sql.Timestamp getDataalta() {
		return(dataalta);
	};
	public void setDataalta(java.sql.Timestamp _dataalta_) {
		this.dataalta = _dataalta_;
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

	public int getPersonalOamr() {
		return(personalOamr);
	};
	public void setPersonalOamr(int _personalOamr_) {
		this.personalOamr = _personalOamr_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};



  // ======================================

  public static LlocBean toBean(Lloc __bean) {
    if (__bean == null) { return null;}
    LlocBean __tmp = new LlocBean();
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setCodiLlocPropi(__bean.getCodiLlocPropi());
    __tmp.setCodiLloc(__bean.getCodiLloc());
    __tmp.setExpansio(__bean.getExpansio());
    __tmp.setNom(__bean.getNom());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setUnitatID(__bean.getUnitatID());
    __tmp.setDataalta(__bean.getDataalta());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataBaixa(__bean.getDataBaixa());
    __tmp.setPersonalOamr(__bean.getPersonalOamr());
    __tmp.setObservacions(__bean.getObservacions());
		return __tmp;
	}



}
