package es.caib.rfhab.model.entity;

public interface SincroUnitats extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getSincrounitatsId();
	public void setSincrounitatsId(long _sincrounitatsId_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.sql.Timestamp getDataDarreraSincro();
	public void setDataDarreraSincro(java.sql.Timestamp _dataDarreraSincro_);

	public java.sql.Timestamp getDataPrimeraSincro();
	public void setDataPrimeraSincro(java.sql.Timestamp _dataPrimeraSincro_);

	public java.lang.String getCodiEntitat();
	public void setCodiEntitat(java.lang.String _codiEntitat_);

	public java.lang.String getObservacions();
	public void setObservacions(java.lang.String _observacions_);

	public java.lang.Long getUsuariId();
	public void setUsuariId(java.lang.Long _usuariId_);



  // ======================================

}
