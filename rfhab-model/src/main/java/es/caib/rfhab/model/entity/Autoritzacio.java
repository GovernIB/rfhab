package es.caib.rfhab.model.entity;

public interface Autoritzacio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAutoritzacioID();
	public void setAutoritzacioID(long _autoritzacioID_);

	public long getLlocID();
	public void setLlocID(long _llocID_);

	public java.lang.String getCodiSia();
	public void setCodiSia(java.lang.String _codiSia_);

	public java.lang.String getProcediment();
	public void setProcediment(java.lang.String _procediment_);

	public java.lang.String getCai();
	public void setCai(java.lang.String _cai_);

	public java.sql.Date getDataInici();
	public void setDataInici(java.sql.Date _dataInici_);

	public java.sql.Date getDataFi();
	public void setDataFi(java.sql.Date _dataFi_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.String getObservacions();
	public void setObservacions(java.lang.String _observacions_);

	public java.lang.Long getUsuariID();
	public void setUsuariID(java.lang.Long _usuariID_);

	public java.lang.Long getFuncionariID();
	public void setFuncionariID(java.lang.Long _funcionariID_);



  // ======================================

}
