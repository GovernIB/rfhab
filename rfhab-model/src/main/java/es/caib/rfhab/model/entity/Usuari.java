package es.caib.rfhab.model.entity;

public interface Usuari extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getUsuariID();
	public void setUsuariID(long _usuariID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getLlinatge1();
	public void setLlinatge1(java.lang.String _llinatge1_);

	public java.lang.String getLlinatge2();
	public void setLlinatge2(java.lang.String _llinatge2_);

	public java.lang.String getNif();
	public void setNif(java.lang.String _nif_);

	public java.lang.String getUsername();
	public void setUsername(java.lang.String _username_);

	public java.lang.String getCorreu();
	public void setCorreu(java.lang.String _correu_);

	public java.lang.String getIdiomaID();
	public void setIdiomaID(java.lang.String _idiomaID_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.Long getDarreraEntitat();
	public void setDarreraEntitat(java.lang.Long _darreraEntitat_);

	public java.sql.Timestamp getDataBaixa();
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_);



  // ======================================

}
