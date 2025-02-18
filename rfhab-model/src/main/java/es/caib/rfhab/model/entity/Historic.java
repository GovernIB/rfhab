package es.caib.rfhab.model.entity;

public interface Historic extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getHistoricID();
	public void setHistoricID(long _historicID_);

	public long getFuncionariID();
	public void setFuncionariID(long _funcionariID_);

	public java.lang.String getNumeroCai();
	public void setNumeroCai(java.lang.String _numeroCai_);

	public java.lang.String getObservacions();
	public void setObservacions(java.lang.String _observacions_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.Long getUsuariID();
	public void setUsuariID(java.lang.Long _usuariID_);



  // ======================================

}
