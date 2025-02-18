
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Historic;


public class HistoricBean implements Historic {



	long historicID;// PK
	long funcionariID;
	java.lang.String numeroCai;
	java.lang.String observacions;
	java.sql.Timestamp dataCreacio;
	java.lang.Long usuariID;


  /** Constructor Buit */
  public HistoricBean() {
  }

  /** Constructor amb tots els camps  */
  public HistoricBean(long historicID , long funcionariID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.historicID=historicID;
    this.funcionariID=funcionariID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public HistoricBean(long funcionariID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.funcionariID=funcionariID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public HistoricBean(long historicID , long funcionariID , java.lang.String numeroCai , java.sql.Timestamp dataCreacio) {
    this.historicID=historicID;
    this.funcionariID=funcionariID;
    this.numeroCai=numeroCai;
    this.dataCreacio=dataCreacio;
}
  public HistoricBean(Historic __bean) {
    this.setHistoricID(__bean.getHistoricID());
    this.setFuncionariID(__bean.getFuncionariID());
    this.setNumeroCai(__bean.getNumeroCai());
    this.setObservacions(__bean.getObservacions());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getHistoricID() {
		return(historicID);
	};
	public void setHistoricID(long _historicID_) {
		this.historicID = _historicID_;
	};

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
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

  public static HistoricBean toBean(Historic __bean) {
    if (__bean == null) { return null;}
    HistoricBean __tmp = new HistoricBean();
    __tmp.setHistoricID(__bean.getHistoricID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setNumeroCai(__bean.getNumeroCai());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}



}
