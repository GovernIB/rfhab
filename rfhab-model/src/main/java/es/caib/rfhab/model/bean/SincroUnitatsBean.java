
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.SincroUnitats;


public class SincroUnitatsBean implements SincroUnitats {



	long sincrounitatsId;// PK
	java.sql.Timestamp dataCreacio;
	java.sql.Timestamp dataDarreraSincro;
	java.sql.Timestamp dataPrimeraSincro;
	java.lang.String codiEntitat;
	java.lang.String observacions;
	java.lang.Long usuariId;


  /** Constructor Buit */
  public SincroUnitatsBean() {
  }

  /** Constructor amb tots els camps  */
  public SincroUnitatsBean(long sincrounitatsId , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataDarreraSincro , java.sql.Timestamp dataPrimeraSincro , java.lang.String codiEntitat , java.lang.String observacions , java.lang.Long usuariId) {
    this.sincrounitatsId=sincrounitatsId;
    this.dataCreacio=dataCreacio;
    this.dataDarreraSincro=dataDarreraSincro;
    this.dataPrimeraSincro=dataPrimeraSincro;
    this.codiEntitat=codiEntitat;
    this.observacions=observacions;
    this.usuariId=usuariId;
}
  /** Constructor sense valors autoincrementals */
  public SincroUnitatsBean(java.sql.Timestamp dataCreacio , java.sql.Timestamp dataDarreraSincro , java.sql.Timestamp dataPrimeraSincro , java.lang.String codiEntitat , java.lang.String observacions , java.lang.Long usuariId) {
    this.dataCreacio=dataCreacio;
    this.dataDarreraSincro=dataDarreraSincro;
    this.dataPrimeraSincro=dataPrimeraSincro;
    this.codiEntitat=codiEntitat;
    this.observacions=observacions;
    this.usuariId=usuariId;
}
  /** Constructor dels valors Not Null */
  public SincroUnitatsBean(long sincrounitatsId , java.sql.Timestamp dataCreacio , java.lang.String codiEntitat , java.lang.Long usuariId) {
    this.sincrounitatsId=sincrounitatsId;
    this.dataCreacio=dataCreacio;
    this.codiEntitat=codiEntitat;
    this.usuariId=usuariId;
}
  public SincroUnitatsBean(SincroUnitats __bean) {
    this.setSincrounitatsId(__bean.getSincrounitatsId());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataDarreraSincro(__bean.getDataDarreraSincro());
    this.setDataPrimeraSincro(__bean.getDataPrimeraSincro());
    this.setCodiEntitat(__bean.getCodiEntitat());
    this.setObservacions(__bean.getObservacions());
    this.setUsuariId(__bean.getUsuariId());
	}

	public long getSincrounitatsId() {
		return(sincrounitatsId);
	};
	public void setSincrounitatsId(long _sincrounitatsId_) {
		this.sincrounitatsId = _sincrounitatsId_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.sql.Timestamp getDataDarreraSincro() {
		return(dataDarreraSincro);
	};
	public void setDataDarreraSincro(java.sql.Timestamp _dataDarreraSincro_) {
		this.dataDarreraSincro = _dataDarreraSincro_;
	};

	public java.sql.Timestamp getDataPrimeraSincro() {
		return(dataPrimeraSincro);
	};
	public void setDataPrimeraSincro(java.sql.Timestamp _dataPrimeraSincro_) {
		this.dataPrimeraSincro = _dataPrimeraSincro_;
	};

	public java.lang.String getCodiEntitat() {
		return(codiEntitat);
	};
	public void setCodiEntitat(java.lang.String _codiEntitat_) {
		this.codiEntitat = _codiEntitat_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public java.lang.Long getUsuariId() {
		return(usuariId);
	};
	public void setUsuariId(java.lang.Long _usuariId_) {
		this.usuariId = _usuariId_;
	};



  // ======================================

  public static SincroUnitatsBean toBean(SincroUnitats __bean) {
    if (__bean == null) { return null;}
    SincroUnitatsBean __tmp = new SincroUnitatsBean();
    __tmp.setSincrounitatsId(__bean.getSincrounitatsId());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataDarreraSincro(__bean.getDataDarreraSincro());
    __tmp.setDataPrimeraSincro(__bean.getDataPrimeraSincro());
    __tmp.setCodiEntitat(__bean.getCodiEntitat());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setUsuariId(__bean.getUsuariId());
		return __tmp;
	}



}
