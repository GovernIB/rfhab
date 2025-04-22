package es.caib.rfhab.model.entity;

public interface Funcionari extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getFuncionariID();
	public void setFuncionariID(long _funcionariID_);

	public java.lang.String getNumero();
	public void setNumero(java.lang.String _numero_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getLlinatge1();
	public void setLlinatge1(java.lang.String _llinatge1_);

	public java.lang.String getLlinatge2();
	public void setLlinatge2(java.lang.String _llinatge2_);

	public int getTipusIdentificador();
	public void setTipusIdentificador(int _tipusIdentificador_);

	public java.lang.String getIdentificador();
	public void setIdentificador(java.lang.String _identificador_);

	public java.lang.String getUsuari();
	public void setUsuari(java.lang.String _usuari_);

	public java.lang.String getCorreu();
	public void setCorreu(java.lang.String _correu_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.String getObservacions();
	public void setObservacions(java.lang.String _observacions_);

	public java.sql.Timestamp getDataBaixa();
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);



  // ======================================

}
