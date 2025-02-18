
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.FuncionariLloc;


public class FuncionariLlocBean implements FuncionariLloc {



	long funcionarillocID;// PK
	long llocID;
	long funcionariID;
	java.sql.Date dataInici;
	java.sql.Date dataFi;
	java.sql.Timestamp dataCreacio;
	java.lang.Long usuariID;


  /** Constructor Buit */
  public FuncionariLlocBean() {
  }

  /** Constructor amb tots els camps  */
  public FuncionariLlocBean(long funcionarillocID , long llocID , long funcionariID , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.funcionarillocID=funcionarillocID;
    this.llocID=llocID;
    this.funcionariID=funcionariID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public FuncionariLlocBean(long llocID , long funcionariID , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.llocID=llocID;
    this.funcionariID=funcionariID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public FuncionariLlocBean(long funcionarillocID , long llocID , long funcionariID , java.sql.Timestamp dataCreacio) {
    this.funcionarillocID=funcionarillocID;
    this.llocID=llocID;
    this.funcionariID=funcionariID;
    this.dataCreacio=dataCreacio;
}
  public FuncionariLlocBean(FuncionariLloc __bean) {
    this.setFuncionarillocID(__bean.getFuncionarillocID());
    this.setLlocID(__bean.getLlocID());
    this.setFuncionariID(__bean.getFuncionariID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getFuncionarillocID() {
		return(funcionarillocID);
	};
	public void setFuncionarillocID(long _funcionarillocID_) {
		this.funcionarillocID = _funcionarillocID_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
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

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};



  // ======================================

  public static FuncionariLlocBean toBean(FuncionariLloc __bean) {
    if (__bean == null) { return null;}
    FuncionariLlocBean __tmp = new FuncionariLlocBean();
    __tmp.setFuncionarillocID(__bean.getFuncionarillocID());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}



}
