
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Funcionari;


public class FuncionariBean implements Funcionari {



	long funcionariID;// PK
	int numero;
	java.lang.String nom;
	java.lang.String llinatge1;
	java.lang.String llinatge2;
	int tipusIdentificador;
	java.lang.String identificador;
	java.lang.String usuari;
	java.lang.String correu;
	java.sql.Timestamp dataCreacio;
	java.lang.String observacions;
	java.sql.Timestamp dataBaixa;
	long entitatID;


  /** Constructor Buit */
  public FuncionariBean() {
  }

  /** Constructor amb tots els camps  */
  public FuncionariBean(long funcionariID , int numero , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , int tipusIdentificador , java.lang.String identificador , java.lang.String usuari , java.lang.String correu , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.sql.Timestamp dataBaixa , long entitatID) {
    this.funcionariID=funcionariID;
    this.numero=numero;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.tipusIdentificador=tipusIdentificador;
    this.identificador=identificador;
    this.usuari=usuari;
    this.correu=correu;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.dataBaixa=dataBaixa;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public FuncionariBean(int numero , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , int tipusIdentificador , java.lang.String identificador , java.lang.String usuari , java.lang.String correu , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.sql.Timestamp dataBaixa , long entitatID) {
    this.numero=numero;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.tipusIdentificador=tipusIdentificador;
    this.identificador=identificador;
    this.usuari=usuari;
    this.correu=correu;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.dataBaixa=dataBaixa;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public FuncionariBean(long funcionariID , int numero , java.lang.String nom , java.lang.String llinatge1 , int tipusIdentificador , java.lang.String identificador , java.lang.String usuari , java.lang.String correu , java.sql.Timestamp dataCreacio , long entitatID) {
    this.funcionariID=funcionariID;
    this.numero=numero;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.tipusIdentificador=tipusIdentificador;
    this.identificador=identificador;
    this.usuari=usuari;
    this.correu=correu;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  public FuncionariBean(Funcionari __bean) {
    this.setFuncionariID(__bean.getFuncionariID());
    this.setNumero(__bean.getNumero());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setTipusIdentificador(__bean.getTipusIdentificador());
    this.setIdentificador(__bean.getIdentificador());
    this.setUsuari(__bean.getUsuari());
    this.setCorreu(__bean.getCorreu());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setObservacions(__bean.getObservacions());
    this.setDataBaixa(__bean.getDataBaixa());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};

	public int getNumero() {
		return(numero);
	};
	public void setNumero(int _numero_) {
		this.numero = _numero_;
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

	public int getTipusIdentificador() {
		return(tipusIdentificador);
	};
	public void setTipusIdentificador(int _tipusIdentificador_) {
		this.tipusIdentificador = _tipusIdentificador_;
	};

	public java.lang.String getIdentificador() {
		return(identificador);
	};
	public void setIdentificador(java.lang.String _identificador_) {
		this.identificador = _identificador_;
	};

	public java.lang.String getUsuari() {
		return(usuari);
	};
	public void setUsuari(java.lang.String _usuari_) {
		this.usuari = _usuari_;
	};

	public java.lang.String getCorreu() {
		return(correu);
	};
	public void setCorreu(java.lang.String _correu_) {
		this.correu = _correu_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static FuncionariBean toBean(Funcionari __bean) {
    if (__bean == null) { return null;}
    FuncionariBean __tmp = new FuncionariBean();
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setNumero(__bean.getNumero());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setTipusIdentificador(__bean.getTipusIdentificador());
    __tmp.setIdentificador(__bean.getIdentificador());
    __tmp.setUsuari(__bean.getUsuari());
    __tmp.setCorreu(__bean.getCorreu());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setDataBaixa(__bean.getDataBaixa());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
