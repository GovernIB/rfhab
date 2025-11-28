
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Habilitacio;


public class HabilitacioBean implements Habilitacio {



	long habilitacioID;// PK
	java.lang.Long nomID;
	java.lang.String codi;
	java.sql.Timestamp dataCreacio;
	java.lang.Long entitatID;


  /** Constructor Buit */
  public HabilitacioBean() {
  }

  /** Constructor amb tots els camps  */
  public HabilitacioBean(long habilitacioID , java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.habilitacioID=habilitacioID;
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public HabilitacioBean(java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  public HabilitacioBean(Habilitacio __bean) {
    this.setHabilitacioID(__bean.getHabilitacioID());
    this.setNomID(__bean.getNomID());
    this.setCodi(__bean.getCodi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getHabilitacioID() {
		return(habilitacioID);
	};
	public void setHabilitacioID(long _habilitacioID_) {
		this.habilitacioID = _habilitacioID_;
	};

	public java.lang.Long getNomID() {
		return(nomID);
	};
	public void setNomID(java.lang.Long _nomID_) {
		this.nomID = _nomID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static HabilitacioBean toBean(Habilitacio __bean) {
    if (__bean == null) { return null;}
    HabilitacioBean __tmp = new HabilitacioBean();
    __tmp.setHabilitacioID(__bean.getHabilitacioID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
