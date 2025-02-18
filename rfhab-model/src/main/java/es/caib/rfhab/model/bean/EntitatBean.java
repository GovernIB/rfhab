
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Entitat;


public class EntitatBean implements Entitat {



	long entitatID;// PK
	java.lang.String nom;
	boolean actiu;
	java.lang.Long unitatID;
	java.sql.Timestamp dataBaixa;


  /** Constructor Buit */
  public EntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public EntitatBean(long entitatID , java.lang.String nom , boolean actiu , java.lang.Long unitatID , java.sql.Timestamp dataBaixa) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.actiu=actiu;
    this.unitatID=unitatID;
    this.dataBaixa=dataBaixa;
}
  /** Constructor sense valors autoincrementals */
  public EntitatBean(java.lang.String nom , boolean actiu , java.lang.Long unitatID , java.sql.Timestamp dataBaixa) {
    this.nom=nom;
    this.actiu=actiu;
    this.unitatID=unitatID;
    this.dataBaixa=dataBaixa;
}
  /** Constructor dels valors Not Null */
  public EntitatBean(long entitatID , boolean actiu) {
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  public EntitatBean(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNom(__bean.getNom());
    this.setActiu(__bean.isActiu());
    this.setUnitatID(__bean.getUnitatID());
    this.setDataBaixa(__bean.getDataBaixa());
	}

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.Long getUnitatID() {
		return(unitatID);
	};
	public void setUnitatID(java.lang.Long _unitatID_) {
		this.unitatID = _unitatID_;
	};

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
	};



  // ======================================

  public static EntitatBean toBean(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatBean __tmp = new EntitatBean();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setUnitatID(__bean.getUnitatID());
    __tmp.setDataBaixa(__bean.getDataBaixa());
		return __tmp;
	}



}
