package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFuncionariLlocManager extends org.fundaciobit.genapp.common.query.ITableManager<FuncionariLloc, Long> {


	public FuncionariLloc create( long _llocID_, long _funcionariID_, java.sql.Date _dataInici_, java.sql.Date _dataFi_, java.sql.Timestamp _dataCreacio_, java.lang.Long _usuariID_) throws I18NException;

	public FuncionariLloc findByPrimaryKey(long _funcionarillocID_);

	public void delete(long _funcionarillocID_);

}
