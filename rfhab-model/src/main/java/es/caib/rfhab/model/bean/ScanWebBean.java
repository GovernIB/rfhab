
package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.ScanWeb;


public class ScanWebBean implements ScanWeb {



	long digitalID;// PK
	java.lang.String transactionID;
	java.lang.String transactionWebID;
	long status;
	long fitxerID;
	java.lang.String fileInfo;
	java.lang.String signedFileInfo;
	java.lang.String metadades;
	java.lang.String missatge;
	java.lang.Long usuariID;
	java.sql.Timestamp dataCreacio;
	long entitatID;


  /** Constructor Buit */
  public ScanWebBean() {
  }

  /** Constructor amb tots els camps  */
  public ScanWebBean(long digitalID , java.lang.String transactionID , java.lang.String transactionWebID , long status , long fitxerID , java.lang.String fileInfo , java.lang.String signedFileInfo , java.lang.String metadades , java.lang.String missatge , java.lang.Long usuariID , java.sql.Timestamp dataCreacio , long entitatID) {
    this.digitalID=digitalID;
    this.transactionID=transactionID;
    this.transactionWebID=transactionWebID;
    this.status=status;
    this.fitxerID=fitxerID;
    this.fileInfo=fileInfo;
    this.signedFileInfo=signedFileInfo;
    this.metadades=metadades;
    this.missatge=missatge;
    this.usuariID=usuariID;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public ScanWebBean(java.lang.String transactionID , java.lang.String transactionWebID , long status , long fitxerID , java.lang.String fileInfo , java.lang.String signedFileInfo , java.lang.String metadades , java.lang.String missatge , java.lang.Long usuariID , java.sql.Timestamp dataCreacio , long entitatID) {
    this.transactionID=transactionID;
    this.transactionWebID=transactionWebID;
    this.status=status;
    this.fitxerID=fitxerID;
    this.fileInfo=fileInfo;
    this.signedFileInfo=signedFileInfo;
    this.metadades=metadades;
    this.missatge=missatge;
    this.usuariID=usuariID;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public ScanWebBean(long digitalID , long status , long fitxerID , java.lang.Long usuariID , long entitatID) {
    this.digitalID=digitalID;
    this.status=status;
    this.fitxerID=fitxerID;
    this.usuariID=usuariID;
    this.entitatID=entitatID;
}
  public ScanWebBean(ScanWeb __bean) {
    this.setDigitalID(__bean.getDigitalID());
    this.setTransactionID(__bean.getTransactionID());
    this.setTransactionWebID(__bean.getTransactionWebID());
    this.setStatus(__bean.getStatus());
    this.setFitxerID(__bean.getFitxerID());
    this.setFileInfo(__bean.getFileInfo());
    this.setSignedFileInfo(__bean.getSignedFileInfo());
    this.setMetadades(__bean.getMetadades());
    this.setMissatge(__bean.getMissatge());
    this.setUsuariID(__bean.getUsuariID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setEntitatID(__bean.getEntitatID());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
	}

	public long getDigitalID() {
		return(digitalID);
	};
	public void setDigitalID(long _digitalID_) {
		this.digitalID = _digitalID_;
	};

	public java.lang.String getTransactionID() {
		return(transactionID);
	};
	public void setTransactionID(java.lang.String _transactionID_) {
		this.transactionID = _transactionID_;
	};

	public java.lang.String getTransactionWebID() {
		return(transactionWebID);
	};
	public void setTransactionWebID(java.lang.String _transactionWebID_) {
		this.transactionWebID = _transactionWebID_;
	};

	public long getStatus() {
		return(status);
	};
	public void setStatus(long _status_) {
		this.status = _status_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.String getFileInfo() {
		return(fileInfo);
	};
	public void setFileInfo(java.lang.String _fileInfo_) {
		this.fileInfo = _fileInfo_;
	};

	public java.lang.String getSignedFileInfo() {
		return(signedFileInfo);
	};
	public void setSignedFileInfo(java.lang.String _signedFileInfo_) {
		this.signedFileInfo = _signedFileInfo_;
	};

	public java.lang.String getMetadades() {
		return(metadades);
	};
	public void setMetadades(java.lang.String _metadades_) {
		this.metadades = _metadades_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static ScanWebBean toBean(ScanWeb __bean) {
    if (__bean == null) { return null;}
    ScanWebBean __tmp = new ScanWebBean();
    __tmp.setDigitalID(__bean.getDigitalID());
    __tmp.setTransactionID(__bean.getTransactionID());
    __tmp.setTransactionWebID(__bean.getTransactionWebID());
    __tmp.setStatus(__bean.getStatus());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setFileInfo(__bean.getFileInfo());
    __tmp.setSignedFileInfo(__bean.getSignedFileInfo());
    __tmp.setMetadades(__bean.getMetadades());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setEntitatID(__bean.getEntitatID());
    // Fitxer
    __tmp.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
		return __tmp;
	}

  protected FitxerBean fitxer;
  public FitxerBean getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerBean __field) {
    this. fitxer = __field;
  }


}
