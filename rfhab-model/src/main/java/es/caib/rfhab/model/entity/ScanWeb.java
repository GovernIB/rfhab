package es.caib.rfhab.model.entity;

public interface ScanWeb extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getDigitalID();
	public void setDigitalID(long _digitalID_);

	public long getTransactionID();
	public void setTransactionID(long _transactionID_);

	public java.lang.String getTransactionWebID();
	public void setTransactionWebID(java.lang.String _transactionWebID_);

	public long getStatus();
	public void setStatus(long _status_);

	public long getFitxerID();
	public void setFitxerID(long _fitxerID_);

	public java.lang.String getFileInfo();
	public void setFileInfo(java.lang.String _fileInfo_);

	public java.lang.String getSignedFileInfo();
	public void setSignedFileInfo(java.lang.String _signedFileInfo_);

	public java.lang.String getMetadades();
	public void setMetadades(java.lang.String _metadades_);

	public java.lang.String getMissatge();
	public void setMissatge(java.lang.String _missatge_);

	public java.lang.Long getUsuariID();
	public void setUsuariID(java.lang.Long _usuariID_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();


  // ======================================

}
