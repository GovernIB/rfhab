package es.caib.rfhab.model.entity;

public interface FuncionariLloc extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getFuncionarillocID();
	public void setFuncionarillocID(long _funcionarillocID_);

	public long getLlocID();
	public void setLlocID(long _llocID_);

	public long getFuncionariID();
	public void setFuncionariID(long _funcionariID_);

	public java.sql.Date getDataInici();
	public void setDataInici(java.sql.Date _dataInici_);

	public java.sql.Date getDataFi();
	public void setDataFi(java.sql.Date _dataFi_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.Long getUsuariID();
	public void setUsuariID(java.lang.Long _usuariID_);

	public java.lang.String getNumeroCai();
	public void setNumeroCai(java.lang.String _numeroCai_);



  // ======================================

}
