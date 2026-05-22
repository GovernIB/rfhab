
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.Activitat;


public class ActivitatBean implements Activitat {



	long activitatID;// PK
	long funcionariID;
	int tipus;
	java.lang.String registre;
	java.lang.String tramit;
	java.lang.String codiSia;
	java.lang.Long autoritzacioID;
	java.sql.Timestamp dataCreacio;
	java.lang.String interessatNom;
	java.lang.String interessatLlinatge1;
	java.lang.String interessatLlinatge2;
	java.lang.Integer interessatTipus;
	java.lang.String interessatIdentificacio;
	java.lang.String representantNom;
	java.lang.String representantLlinatge1;
	java.lang.String representantLlinatge2;
	java.lang.Integer representantTipus;
	java.lang.String representantIdentificacio;
	java.lang.Integer tramitVersio;
	java.lang.String arxiuDocumentID;
	java.lang.String arxiuExpedientID;
	int estat;
	java.lang.String url;
	java.sql.Timestamp dataActivitat;
	java.lang.String idActuacioTramit;
	java.lang.String procediment;
	java.lang.Integer arxiuReintents;
	java.lang.Integer arxiuEstat;


  /** Constructor Buit */
  public ActivitatBean() {
  }

  /** Constructor amb tots els camps  */
  public ActivitatBean(long activitatID , long funcionariID , int tipus , java.lang.String registre , java.lang.String tramit , java.lang.String codiSia , java.lang.Long autoritzacioID , java.sql.Timestamp dataCreacio , java.lang.String interessatNom , java.lang.String interessatLlinatge1 , java.lang.String interessatLlinatge2 , java.lang.Integer interessatTipus , java.lang.String interessatIdentificacio , java.lang.String representantNom , java.lang.String representantLlinatge1 , java.lang.String representantLlinatge2 , java.lang.Integer representantTipus , java.lang.String representantIdentificacio , java.lang.Integer tramitVersio , java.lang.String arxiuDocumentID , java.lang.String arxiuExpedientID , int estat , java.lang.String url , java.sql.Timestamp dataActivitat , java.lang.String idActuacioTramit , java.lang.String procediment , java.lang.Integer arxiuReintents , java.lang.Integer arxiuEstat) {
    this.activitatID=activitatID;
    this.funcionariID=funcionariID;
    this.tipus=tipus;
    this.registre=registre;
    this.tramit=tramit;
    this.codiSia=codiSia;
    this.autoritzacioID=autoritzacioID;
    this.dataCreacio=dataCreacio;
    this.interessatNom=interessatNom;
    this.interessatLlinatge1=interessatLlinatge1;
    this.interessatLlinatge2=interessatLlinatge2;
    this.interessatTipus=interessatTipus;
    this.interessatIdentificacio=interessatIdentificacio;
    this.representantNom=representantNom;
    this.representantLlinatge1=representantLlinatge1;
    this.representantLlinatge2=representantLlinatge2;
    this.representantTipus=representantTipus;
    this.representantIdentificacio=representantIdentificacio;
    this.tramitVersio=tramitVersio;
    this.arxiuDocumentID=arxiuDocumentID;
    this.arxiuExpedientID=arxiuExpedientID;
    this.estat=estat;
    this.url=url;
    this.dataActivitat=dataActivitat;
    this.idActuacioTramit=idActuacioTramit;
    this.procediment=procediment;
    this.arxiuReintents=arxiuReintents;
    this.arxiuEstat=arxiuEstat;
}
  /** Constructor sense valors autoincrementals */
  public ActivitatBean(long funcionariID , int tipus , java.lang.String registre , java.lang.String tramit , java.lang.String codiSia , java.lang.Long autoritzacioID , java.sql.Timestamp dataCreacio , java.lang.String interessatNom , java.lang.String interessatLlinatge1 , java.lang.String interessatLlinatge2 , java.lang.Integer interessatTipus , java.lang.String interessatIdentificacio , java.lang.String representantNom , java.lang.String representantLlinatge1 , java.lang.String representantLlinatge2 , java.lang.Integer representantTipus , java.lang.String representantIdentificacio , java.lang.Integer tramitVersio , java.lang.String arxiuDocumentID , java.lang.String arxiuExpedientID , int estat , java.lang.String url , java.sql.Timestamp dataActivitat , java.lang.String idActuacioTramit , java.lang.String procediment , java.lang.Integer arxiuReintents , java.lang.Integer arxiuEstat) {
    this.funcionariID=funcionariID;
    this.tipus=tipus;
    this.registre=registre;
    this.tramit=tramit;
    this.codiSia=codiSia;
    this.autoritzacioID=autoritzacioID;
    this.dataCreacio=dataCreacio;
    this.interessatNom=interessatNom;
    this.interessatLlinatge1=interessatLlinatge1;
    this.interessatLlinatge2=interessatLlinatge2;
    this.interessatTipus=interessatTipus;
    this.interessatIdentificacio=interessatIdentificacio;
    this.representantNom=representantNom;
    this.representantLlinatge1=representantLlinatge1;
    this.representantLlinatge2=representantLlinatge2;
    this.representantTipus=representantTipus;
    this.representantIdentificacio=representantIdentificacio;
    this.tramitVersio=tramitVersio;
    this.arxiuDocumentID=arxiuDocumentID;
    this.arxiuExpedientID=arxiuExpedientID;
    this.estat=estat;
    this.url=url;
    this.dataActivitat=dataActivitat;
    this.idActuacioTramit=idActuacioTramit;
    this.procediment=procediment;
    this.arxiuReintents=arxiuReintents;
    this.arxiuEstat=arxiuEstat;
}
  /** Constructor dels valors Not Null */
  public ActivitatBean(long activitatID , long funcionariID , int tipus , java.sql.Timestamp dataCreacio , int estat , java.sql.Timestamp dataActivitat) {
    this.activitatID=activitatID;
    this.funcionariID=funcionariID;
    this.tipus=tipus;
    this.dataCreacio=dataCreacio;
    this.estat=estat;
    this.dataActivitat=dataActivitat;
}
  public ActivitatBean(Activitat __bean) {
    this.setActivitatID(__bean.getActivitatID());
    this.setFuncionariID(__bean.getFuncionariID());
    this.setTipus(__bean.getTipus());
    this.setRegistre(__bean.getRegistre());
    this.setTramit(__bean.getTramit());
    this.setCodiSia(__bean.getCodiSia());
    this.setAutoritzacioID(__bean.getAutoritzacioID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setInteressatNom(__bean.getInteressatNom());
    this.setInteressatLlinatge1(__bean.getInteressatLlinatge1());
    this.setInteressatLlinatge2(__bean.getInteressatLlinatge2());
    this.setInteressatTipus(__bean.getInteressatTipus());
    this.setInteressatIdentificacio(__bean.getInteressatIdentificacio());
    this.setRepresentantNom(__bean.getRepresentantNom());
    this.setRepresentantLlinatge1(__bean.getRepresentantLlinatge1());
    this.setRepresentantLlinatge2(__bean.getRepresentantLlinatge2());
    this.setRepresentantTipus(__bean.getRepresentantTipus());
    this.setRepresentantIdentificacio(__bean.getRepresentantIdentificacio());
    this.setTramitVersio(__bean.getTramitVersio());
    this.setArxiuDocumentID(__bean.getArxiuDocumentID());
    this.setArxiuExpedientID(__bean.getArxiuExpedientID());
    this.setEstat(__bean.getEstat());
    this.setUrl(__bean.getUrl());
    this.setDataActivitat(__bean.getDataActivitat());
    this.setIdActuacioTramit(__bean.getIdActuacioTramit());
    this.setProcediment(__bean.getProcediment());
    this.setArxiuReintents(__bean.getArxiuReintents());
    this.setArxiuEstat(__bean.getArxiuEstat());
	}

	public long getActivitatID() {
		return(activitatID);
	};
	public void setActivitatID(long _activitatID_) {
		this.activitatID = _activitatID_;
	};

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getRegistre() {
		return(registre);
	};
	public void setRegistre(java.lang.String _registre_) {
		this.registre = _registre_;
	};

	public java.lang.String getTramit() {
		return(tramit);
	};
	public void setTramit(java.lang.String _tramit_) {
		this.tramit = _tramit_;
	};

	public java.lang.String getCodiSia() {
		return(codiSia);
	};
	public void setCodiSia(java.lang.String _codiSia_) {
		this.codiSia = _codiSia_;
	};

	public java.lang.Long getAutoritzacioID() {
		return(autoritzacioID);
	};
	public void setAutoritzacioID(java.lang.Long _autoritzacioID_) {
		this.autoritzacioID = _autoritzacioID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getInteressatNom() {
		return(interessatNom);
	};
	public void setInteressatNom(java.lang.String _interessatNom_) {
		this.interessatNom = _interessatNom_;
	};

	public java.lang.String getInteressatLlinatge1() {
		return(interessatLlinatge1);
	};
	public void setInteressatLlinatge1(java.lang.String _interessatLlinatge1_) {
		this.interessatLlinatge1 = _interessatLlinatge1_;
	};

	public java.lang.String getInteressatLlinatge2() {
		return(interessatLlinatge2);
	};
	public void setInteressatLlinatge2(java.lang.String _interessatLlinatge2_) {
		this.interessatLlinatge2 = _interessatLlinatge2_;
	};

	public java.lang.Integer getInteressatTipus() {
		return(interessatTipus);
	};
	public void setInteressatTipus(java.lang.Integer _interessatTipus_) {
		this.interessatTipus = _interessatTipus_;
	};

	public java.lang.String getInteressatIdentificacio() {
		return(interessatIdentificacio);
	};
	public void setInteressatIdentificacio(java.lang.String _interessatIdentificacio_) {
		this.interessatIdentificacio = _interessatIdentificacio_;
	};

	public java.lang.String getRepresentantNom() {
		return(representantNom);
	};
	public void setRepresentantNom(java.lang.String _representantNom_) {
		this.representantNom = _representantNom_;
	};

	public java.lang.String getRepresentantLlinatge1() {
		return(representantLlinatge1);
	};
	public void setRepresentantLlinatge1(java.lang.String _representantLlinatge1_) {
		this.representantLlinatge1 = _representantLlinatge1_;
	};

	public java.lang.String getRepresentantLlinatge2() {
		return(representantLlinatge2);
	};
	public void setRepresentantLlinatge2(java.lang.String _representantLlinatge2_) {
		this.representantLlinatge2 = _representantLlinatge2_;
	};

	public java.lang.Integer getRepresentantTipus() {
		return(representantTipus);
	};
	public void setRepresentantTipus(java.lang.Integer _representantTipus_) {
		this.representantTipus = _representantTipus_;
	};

	public java.lang.String getRepresentantIdentificacio() {
		return(representantIdentificacio);
	};
	public void setRepresentantIdentificacio(java.lang.String _representantIdentificacio_) {
		this.representantIdentificacio = _representantIdentificacio_;
	};

	public java.lang.Integer getTramitVersio() {
		return(tramitVersio);
	};
	public void setTramitVersio(java.lang.Integer _tramitVersio_) {
		this.tramitVersio = _tramitVersio_;
	};

	public java.lang.String getArxiuDocumentID() {
		return(arxiuDocumentID);
	};
	public void setArxiuDocumentID(java.lang.String _arxiuDocumentID_) {
		this.arxiuDocumentID = _arxiuDocumentID_;
	};

	public java.lang.String getArxiuExpedientID() {
		return(arxiuExpedientID);
	};
	public void setArxiuExpedientID(java.lang.String _arxiuExpedientID_) {
		this.arxiuExpedientID = _arxiuExpedientID_;
	};

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public java.lang.String getUrl() {
		return(url);
	};
	public void setUrl(java.lang.String _url_) {
		this.url = _url_;
	};

	public java.sql.Timestamp getDataActivitat() {
		return(dataActivitat);
	};
	public void setDataActivitat(java.sql.Timestamp _dataActivitat_) {
		this.dataActivitat = _dataActivitat_;
	};

	public java.lang.String getIdActuacioTramit() {
		return(idActuacioTramit);
	};
	public void setIdActuacioTramit(java.lang.String _idActuacioTramit_) {
		this.idActuacioTramit = _idActuacioTramit_;
	};

	public java.lang.String getProcediment() {
		return(procediment);
	};
	public void setProcediment(java.lang.String _procediment_) {
		this.procediment = _procediment_;
	};

	public java.lang.Integer getArxiuReintents() {
		return(arxiuReintents);
	};
	public void setArxiuReintents(java.lang.Integer _arxiuReintents_) {
		this.arxiuReintents = _arxiuReintents_;
	};

	public java.lang.Integer getArxiuEstat() {
		return(arxiuEstat);
	};
	public void setArxiuEstat(java.lang.Integer _arxiuEstat_) {
		this.arxiuEstat = _arxiuEstat_;
	};



  // ======================================

  public static ActivitatBean toBean(Activitat __bean) {
    if (__bean == null) { return null;}
    ActivitatBean __tmp = new ActivitatBean();
    __tmp.setActivitatID(__bean.getActivitatID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setRegistre(__bean.getRegistre());
    __tmp.setTramit(__bean.getTramit());
    __tmp.setCodiSia(__bean.getCodiSia());
    __tmp.setAutoritzacioID(__bean.getAutoritzacioID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setInteressatNom(__bean.getInteressatNom());
    __tmp.setInteressatLlinatge1(__bean.getInteressatLlinatge1());
    __tmp.setInteressatLlinatge2(__bean.getInteressatLlinatge2());
    __tmp.setInteressatTipus(__bean.getInteressatTipus());
    __tmp.setInteressatIdentificacio(__bean.getInteressatIdentificacio());
    __tmp.setRepresentantNom(__bean.getRepresentantNom());
    __tmp.setRepresentantLlinatge1(__bean.getRepresentantLlinatge1());
    __tmp.setRepresentantLlinatge2(__bean.getRepresentantLlinatge2());
    __tmp.setRepresentantTipus(__bean.getRepresentantTipus());
    __tmp.setRepresentantIdentificacio(__bean.getRepresentantIdentificacio());
    __tmp.setTramitVersio(__bean.getTramitVersio());
    __tmp.setArxiuDocumentID(__bean.getArxiuDocumentID());
    __tmp.setArxiuExpedientID(__bean.getArxiuExpedientID());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setUrl(__bean.getUrl());
    __tmp.setDataActivitat(__bean.getDataActivitat());
    __tmp.setIdActuacioTramit(__bean.getIdActuacioTramit());
    __tmp.setProcediment(__bean.getProcediment());
    __tmp.setArxiuReintents(__bean.getArxiuReintents());
    __tmp.setArxiuEstat(__bean.getArxiuEstat());
		return __tmp;
	}



}
