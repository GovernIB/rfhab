package es.caib.rfhab.model.entity;

public interface Entitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public java.lang.Long getUnitatID();
	public void setUnitatID(java.lang.Long _unitatID_);

	public java.sql.Timestamp getDataBaixa();
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_);



  // ======================================

}
