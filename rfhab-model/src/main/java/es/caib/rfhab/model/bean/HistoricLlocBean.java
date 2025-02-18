
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.HistoricLloc;


public class HistoricLlocBean implements HistoricLloc {



	long historicllocID;// PK
	long llocID;
	java.lang.String numeroCai;
	java.lang.String observacions;
	java.sql.Timestamp dataCreacio;
	java.lang.Long usuariID;


  /** Constructor Buit */
  public HistoricLlocBean() {
  }

  /** Constructor amb tots els camps  */
  public HistoricLlocBean(long historicllocID , long llocID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.historicllocID=historicllocID;
    this.llocID=llocID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public HistoricLlocBean(long llocID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.llocID=llocID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public HistoricLlocBean(long historicllocID , long llocID , java.lang.String numeroCai , java.sql.Timestamp dataCreacio) {
    this.historicllocID=historicllocID;
    this.llocID=llocID;
    this.numeroCai=numeroCai;
    this.dataCreacio=dataCreacio;
}
  public HistoricLlocBean(HistoricLloc __bean) {
    this.setHistoricllocID(__bean.getHistoricllocID());
    this.setLlocID(__bean.getLlocID());
    this.setNumeroCai(__bean.getNumeroCai());
    this.setObservacions(__bean.getObservacions());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getHistoricllocID() {
		return(historicllocID);
	};
	public void setHistoricllocID(long _historicllocID_) {
		this.historicllocID = _historicllocID_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public java.lang.String getNumeroCai() {
		return(numeroCai);
	};
	public void setNumeroCai(java.lang.String _numeroCai_) {
		this.numeroCai = _numeroCai_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};



  // ======================================

  public static HistoricLlocBean toBean(HistoricLloc __bean) {
    if (__bean == null) { return null;}
    HistoricLlocBean __tmp = new HistoricLlocBean();
    __tmp.setHistoricllocID(__bean.getHistoricllocID());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setNumeroCai(__bean.getNumeroCai());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}



}
