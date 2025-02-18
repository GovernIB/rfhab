
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Rol;


public class RolBean implements Rol {



	long rolID;// PK
	java.lang.Long nomID;
	java.lang.String codi;
	java.sql.Timestamp dataCreacio;
	java.lang.Long entitatID;


  /** Constructor Buit */
  public RolBean() {
  }

  /** Constructor amb tots els camps  */
  public RolBean(long rolID , java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.rolID=rolID;
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public RolBean(java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public RolBean(long rolID , java.lang.Long nomID , java.lang.String codi) {
    this.rolID=rolID;
    this.nomID=nomID;
    this.codi=codi;
}
  public RolBean(Rol __bean) {
    this.setRolID(__bean.getRolID());
    this.setNomID(__bean.getNomID());
    this.setCodi(__bean.getCodi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getRolID() {
		return(rolID);
	};
	public void setRolID(long _rolID_) {
		this.rolID = _rolID_;
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

  public static RolBean toBean(Rol __bean) {
    if (__bean == null) { return null;}
    RolBean __tmp = new RolBean();
    __tmp.setRolID(__bean.getRolID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
