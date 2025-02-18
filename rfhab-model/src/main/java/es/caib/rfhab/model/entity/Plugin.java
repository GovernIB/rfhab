package es.caib.rfhab.model.entity;

public interface Plugin extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginID();
	public void setPluginID(long _pluginID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.String getClasse();
	public void setClasse(java.lang.String _classe_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.lang.String getProperties();
	public void setProperties(java.lang.String _properties_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.String getTipus();
	public void setTipus(java.lang.String _tipus_);



  // ======================================

}
