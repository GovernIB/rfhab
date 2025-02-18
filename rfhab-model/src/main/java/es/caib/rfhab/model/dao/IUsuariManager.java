package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariManager extends org.fundaciobit.genapp.common.query.ITableManager<Usuari, Long> {


	public Usuari create( java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _nif_, java.lang.String _username_, java.lang.String _correu_, java.lang.String _idiomaID_, boolean _actiu_, java.sql.Timestamp _dataCreacio_, java.lang.Long _darreraEntitat_, java.sql.Timestamp _dataBaixa_) throws I18NException;

	public Usuari findByPrimaryKey(long _usuariID_);

	public void delete(long _usuariID_);

}
