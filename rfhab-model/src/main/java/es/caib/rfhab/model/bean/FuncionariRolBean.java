
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.FuncionariRol;


public class FuncionariRolBean implements FuncionariRol {



	long funcionariRolID;// PK
	long funcionariID;
	long rolID;
	java.sql.Timestamp dataCreacio;


  /** Constructor Buit */
  public FuncionariRolBean() {
  }

  /** Constructor amb tots els camps  */
  public FuncionariRolBean(long funcionariRolID , long funcionariID , long rolID , java.sql.Timestamp dataCreacio) {
    this.funcionariRolID=funcionariRolID;
    this.funcionariID=funcionariID;
    this.rolID=rolID;
    this.dataCreacio=dataCreacio;
}
  /** Constructor sense valors autoincrementals */
  public FuncionariRolBean(long funcionariID , long rolID , java.sql.Timestamp dataCreacio) {
    this.funcionariID=funcionariID;
    this.rolID=rolID;
    this.dataCreacio=dataCreacio;
}
  public FuncionariRolBean(FuncionariRol __bean) {
    this.setFuncionariRolID(__bean.getFuncionariRolID());
    this.setFuncionariID(__bean.getFuncionariID());
    this.setRolID(__bean.getRolID());
    this.setDataCreacio(__bean.getDataCreacio());
	}

	public long getFuncionariRolID() {
		return(funcionariRolID);
	};
	public void setFuncionariRolID(long _funcionariRolID_) {
		this.funcionariRolID = _funcionariRolID_;
	};

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};

	public long getRolID() {
		return(rolID);
	};
	public void setRolID(long _rolID_) {
		this.rolID = _rolID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};



  // ======================================

  public static FuncionariRolBean toBean(FuncionariRol __bean) {
    if (__bean == null) { return null;}
    FuncionariRolBean __tmp = new FuncionariRolBean();
    __tmp.setFuncionariRolID(__bean.getFuncionariRolID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setRolID(__bean.getRolID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
		return __tmp;
	}



}
