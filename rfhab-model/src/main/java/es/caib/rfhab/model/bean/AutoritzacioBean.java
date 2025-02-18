
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Autoritzacio;


public class AutoritzacioBean implements Autoritzacio {



	long autoritzacioID;// PK
	long llocID;
	java.lang.String codiSia;
	java.lang.String procediment;
	java.lang.String cai;
	java.sql.Date dataInici;
	java.sql.Date dataFi;
	java.sql.Timestamp dataCreacio;
	java.lang.String observacions;
	java.lang.Long usuariID;
	java.lang.Long funcionariID;


  /** Constructor Buit */
  public AutoritzacioBean() {
  }

  /** Constructor amb tots els camps  */
  public AutoritzacioBean(long autoritzacioID , long llocID , java.lang.String codiSia , java.lang.String procediment , java.lang.String cai , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.lang.Long usuariID , java.lang.Long funcionariID) {
    this.autoritzacioID=autoritzacioID;
    this.llocID=llocID;
    this.codiSia=codiSia;
    this.procediment=procediment;
    this.cai=cai;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.usuariID=usuariID;
    this.funcionariID=funcionariID;
}
  /** Constructor sense valors autoincrementals */
  public AutoritzacioBean(long llocID , java.lang.String codiSia , java.lang.String procediment , java.lang.String cai , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.lang.Long usuariID , java.lang.Long funcionariID) {
    this.llocID=llocID;
    this.codiSia=codiSia;
    this.procediment=procediment;
    this.cai=cai;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.usuariID=usuariID;
    this.funcionariID=funcionariID;
}
  /** Constructor dels valors Not Null */
  public AutoritzacioBean(long autoritzacioID , long llocID , java.lang.String codiSia , java.lang.String procediment , java.lang.String cai , java.sql.Timestamp dataCreacio) {
    this.autoritzacioID=autoritzacioID;
    this.llocID=llocID;
    this.codiSia=codiSia;
    this.procediment=procediment;
    this.cai=cai;
    this.dataCreacio=dataCreacio;
}
  public AutoritzacioBean(Autoritzacio __bean) {
    this.setAutoritzacioID(__bean.getAutoritzacioID());
    this.setLlocID(__bean.getLlocID());
    this.setCodiSia(__bean.getCodiSia());
    this.setProcediment(__bean.getProcediment());
    this.setCai(__bean.getCai());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setObservacions(__bean.getObservacions());
    this.setUsuariID(__bean.getUsuariID());
    this.setFuncionariID(__bean.getFuncionariID());
	}

	public long getAutoritzacioID() {
		return(autoritzacioID);
	};
	public void setAutoritzacioID(long _autoritzacioID_) {
		this.autoritzacioID = _autoritzacioID_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public java.lang.String getCodiSia() {
		return(codiSia);
	};
	public void setCodiSia(java.lang.String _codiSia_) {
		this.codiSia = _codiSia_;
	};

	public java.lang.String getProcediment() {
		return(procediment);
	};
	public void setProcediment(java.lang.String _procediment_) {
		this.procediment = _procediment_;
	};

	public java.lang.String getCai() {
		return(cai);
	};
	public void setCai(java.lang.String _cai_) {
		this.cai = _cai_;
	};

	public java.sql.Date getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Date _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Date getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Date _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.Long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(java.lang.Long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};



  // ======================================

  public static AutoritzacioBean toBean(Autoritzacio __bean) {
    if (__bean == null) { return null;}
    AutoritzacioBean __tmp = new AutoritzacioBean();
    __tmp.setAutoritzacioID(__bean.getAutoritzacioID());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setCodiSia(__bean.getCodiSia());
    __tmp.setProcediment(__bean.getProcediment());
    __tmp.setCai(__bean.getCai());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
		return __tmp;
	}



}
