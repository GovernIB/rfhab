
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.LlocRol;


public class LlocRolBean implements LlocRol {



	long llocRolID;// PK
	java.sql.Timestamp dataCreacio;
	long llocID;
	long rolID;


  /** Constructor Buit */
  public LlocRolBean() {
  }

  /** Constructor amb tots els camps  */
  public LlocRolBean(long llocRolID , java.sql.Timestamp dataCreacio , long llocID , long rolID) {
    this.llocRolID=llocRolID;
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.rolID=rolID;
}
  /** Constructor sense valors autoincrementals */
  public LlocRolBean(java.sql.Timestamp dataCreacio , long llocID , long rolID) {
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.rolID=rolID;
}
  public LlocRolBean(LlocRol __bean) {
    this.setLlocRolID(__bean.getLlocRolID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setLlocID(__bean.getLlocID());
    this.setRolID(__bean.getRolID());
	}

	public long getLlocRolID() {
		return(llocRolID);
	};
	public void setLlocRolID(long _llocRolID_) {
		this.llocRolID = _llocRolID_;
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

	public long getRolID() {
		return(rolID);
	};
	public void setRolID(long _rolID_) {
		this.rolID = _rolID_;
	};



  // ======================================

  public static LlocRolBean toBean(LlocRol __bean) {
    if (__bean == null) { return null;}
    LlocRolBean __tmp = new LlocRolBean();
    __tmp.setLlocRolID(__bean.getLlocRolID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setRolID(__bean.getRolID());
		return __tmp;
	}



}
