
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.LlocHabilitacio;


public class LlocHabilitacioBean implements LlocHabilitacio {



	long llocHabilitacioID;// PK
	java.sql.Timestamp dataCreacio;
	long llocID;
	long habilitacioId;


  /** Constructor Buit */
  public LlocHabilitacioBean() {
  }

  /** Constructor amb tots els camps  */
  public LlocHabilitacioBean(long llocHabilitacioID , java.sql.Timestamp dataCreacio , long llocID , long habilitacioId) {
    this.llocHabilitacioID=llocHabilitacioID;
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.habilitacioId=habilitacioId;
}
  /** Constructor sense valors autoincrementals */
  public LlocHabilitacioBean(java.sql.Timestamp dataCreacio , long llocID , long habilitacioId) {
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.habilitacioId=habilitacioId;
}
  public LlocHabilitacioBean(LlocHabilitacio __bean) {
    this.setLlocHabilitacioID(__bean.getLlocHabilitacioID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setLlocID(__bean.getLlocID());
    this.setHabilitacioId(__bean.getHabilitacioId());
	}

	public long getLlocHabilitacioID() {
		return(llocHabilitacioID);
	};
	public void setLlocHabilitacioID(long _llocHabilitacioID_) {
		this.llocHabilitacioID = _llocHabilitacioID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public long getHabilitacioId() {
		return(habilitacioId);
	};
	public void setHabilitacioId(long _habilitacioId_) {
		this.habilitacioId = _habilitacioId_;
	};



  // ======================================

  public static LlocHabilitacioBean toBean(LlocHabilitacio __bean) {
    if (__bean == null) { return null;}
    LlocHabilitacioBean __tmp = new LlocHabilitacioBean();
    __tmp.setLlocHabilitacioID(__bean.getLlocHabilitacioID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setHabilitacioId(__bean.getHabilitacioId());
		return __tmp;
	}



}
