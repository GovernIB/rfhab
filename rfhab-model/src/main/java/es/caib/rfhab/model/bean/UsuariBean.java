
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Usuari;


public class UsuariBean implements Usuari {



	long usuariID;// PK
	java.lang.String nom;
	java.lang.String llinatge1;
	java.lang.String llinatge2;
	java.lang.String nif;
	java.lang.String username;
	java.lang.String correu;
	java.lang.String idiomaID;
	boolean actiu;
	java.sql.Timestamp dataCreacio;
	java.lang.Long darreraEntitat;
	java.sql.Timestamp dataBaixa;


  /** Constructor Buit */
  public UsuariBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariBean(long usuariID , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String username , java.lang.String correu , java.lang.String idiomaID , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.Long darreraEntitat , java.sql.Timestamp dataBaixa) {
    this.usuariID=usuariID;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.username=username;
    this.correu=correu;
    this.idiomaID=idiomaID;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.darreraEntitat=darreraEntitat;
    this.dataBaixa=dataBaixa;
}
  /** Constructor sense valors autoincrementals */
  public UsuariBean(java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String username , java.lang.String correu , java.lang.String idiomaID , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.Long darreraEntitat , java.sql.Timestamp dataBaixa) {
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.username=username;
    this.correu=correu;
    this.idiomaID=idiomaID;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.darreraEntitat=darreraEntitat;
    this.dataBaixa=dataBaixa;
}
  /** Constructor dels valors Not Null */
  public UsuariBean(long usuariID , java.lang.String nom , java.lang.String llinatge1 , java.lang.String nif , java.lang.String correu , java.lang.String idiomaID , boolean actiu , java.sql.Timestamp dataCreacio) {
    this.usuariID=usuariID;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.nif=nif;
    this.correu=correu;
    this.idiomaID=idiomaID;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
}
  public UsuariBean(Usuari __bean) {
    this.setUsuariID(__bean.getUsuariID());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setNif(__bean.getNif());
    this.setUsername(__bean.getUsername());
    this.setCorreu(__bean.getCorreu());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setActiu(__bean.isActiu());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDarreraEntitat(__bean.getDarreraEntitat());
    this.setDataBaixa(__bean.getDataBaixa());
	}

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatge1() {
		return(llinatge1);
	};
	public void setLlinatge1(java.lang.String _llinatge1_) {
		this.llinatge1 = _llinatge1_;
	};

	public java.lang.String getLlinatge2() {
		return(llinatge2);
	};
	public void setLlinatge2(java.lang.String _llinatge2_) {
		this.llinatge2 = _llinatge2_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getCorreu() {
		return(correu);
	};
	public void setCorreu(java.lang.String _correu_) {
		this.correu = _correu_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.Long getDarreraEntitat() {
		return(darreraEntitat);
	};
	public void setDarreraEntitat(java.lang.Long _darreraEntitat_) {
		this.darreraEntitat = _darreraEntitat_;
	};

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
	};



  // ======================================

  public static UsuariBean toBean(Usuari __bean) {
    if (__bean == null) { return null;}
    UsuariBean __tmp = new UsuariBean();
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setNif(__bean.getNif());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setCorreu(__bean.getCorreu());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDarreraEntitat(__bean.getDarreraEntitat());
    __tmp.setDataBaixa(__bean.getDataBaixa());
		return __tmp;
	}



}
