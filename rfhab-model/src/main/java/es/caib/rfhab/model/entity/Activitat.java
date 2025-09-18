package es.caib.rfhab.model.entity;

public interface Activitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getActivitatID();
	public void setActivitatID(long _activitatID_);

	public long getFuncionariID();
	public void setFuncionariID(long _funcionariID_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getRegistre();
	public void setRegistre(java.lang.String _registre_);

	public java.lang.String getTramit();
	public void setTramit(java.lang.String _tramit_);

	public java.lang.String getCodiSia();
	public void setCodiSia(java.lang.String _codiSia_);

	public java.lang.Long getAutoritzacioID();
	public void setAutoritzacioID(java.lang.Long _autoritzacioID_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.String getInteressatNom();
	public void setInteressatNom(java.lang.String _interessatNom_);

	public java.lang.String getInteressatLlinatge1();
	public void setInteressatLlinatge1(java.lang.String _interessatLlinatge1_);

	public java.lang.String getInteressatLlinatge2();
	public void setInteressatLlinatge2(java.lang.String _interessatLlinatge2_);

	public java.lang.Integer getInteressatTipus();
	public void setInteressatTipus(java.lang.Integer _interessatTipus_);

	public java.lang.String getInteressatIdentificacio();
	public void setInteressatIdentificacio(java.lang.String _interessatIdentificacio_);

	public java.lang.String getRepresentantNom();
	public void setRepresentantNom(java.lang.String _representantNom_);

	public java.lang.String getRepresentantLlinatge1();
	public void setRepresentantLlinatge1(java.lang.String _representantLlinatge1_);

	public java.lang.String getRepresentantLlinatge2();
	public void setRepresentantLlinatge2(java.lang.String _representantLlinatge2_);

	public java.lang.Integer getRepresentantTipus();
	public void setRepresentantTipus(java.lang.Integer _representantTipus_);

	public java.lang.String getRepresentantIdentificacio();
	public void setRepresentantIdentificacio(java.lang.String _representantIdentificacio_);

	public java.lang.Integer getTramitVersio();
	public void setTramitVersio(java.lang.Integer _tramitVersio_);

	public java.lang.String getArxiuDocumentID();
	public void setArxiuDocumentID(java.lang.String _arxiuDocumentID_);

	public java.lang.String getArxiuExpedientID();
	public void setArxiuExpedientID(java.lang.String _arxiuExpedientID_);

	public int getEstat();
	public void setEstat(int _estat_);

	public java.lang.String getUrl();
	public void setUrl(java.lang.String _url_);

	public java.sql.Timestamp getDataActivitat();
	public void setDataActivitat(java.sql.Timestamp _dataActivitat_);

	public java.lang.String getIdActuacioTramit();
	public void setIdActuacioTramit(java.lang.String _idActuacioTramit_);

	public java.lang.String getProcediment();
	public void setProcediment(java.lang.String _procediment_);



  // ======================================

}
