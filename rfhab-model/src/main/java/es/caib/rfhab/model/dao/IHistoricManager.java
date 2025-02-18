package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IHistoricManager extends org.fundaciobit.genapp.common.query.ITableManager<Historic, Long> {


	public Historic create( long _funcionariID_, java.lang.String _numeroCai_, java.lang.String _observacions_, java.sql.Timestamp _dataCreacio_, java.lang.Long _usuariID_) throws I18NException;

	public Historic findByPrimaryKey(long _historicID_);

	public void delete(long _historicID_);

}
