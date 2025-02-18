package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IHistoricLlocManager extends org.fundaciobit.genapp.common.query.ITableManager<HistoricLloc, Long> {


	public HistoricLloc create( long _llocID_, java.lang.String _numeroCai_, java.lang.String _observacions_, java.sql.Timestamp _dataCreacio_, java.lang.Long _usuariID_) throws I18NException;

	public HistoricLloc findByPrimaryKey(long _historicllocID_);

	public void delete(long _historicllocID_);

}
