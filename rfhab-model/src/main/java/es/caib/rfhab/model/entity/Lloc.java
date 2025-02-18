package es.caib.rfhab.model.entity;

public interface Lloc extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getLlocID();
	public void setLlocID(long _llocID_);

	public java.lang.String getCodiLloc();
	public void setCodiLloc(java.lang.String _codiLloc_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public int getPersonalOamr();
	public void setPersonalOamr(int _personalOamr_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.sql.Timestamp getDataBaixa();
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_);

	public java.lang.String getObservacions();
	public void setObservacions(java.lang.String _observacions_);

	public long getUnitatID();
	public void setUnitatID(long _unitatID_);



  // ======================================

}
