package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFuncionariRolManager extends org.fundaciobit.genapp.common.query.ITableManager<FuncionariRol, Long> {


	public FuncionariRol create( long _funcionariID_, long _rolID_, java.sql.Timestamp _dataCreacio_) throws I18NException;

	public FuncionariRol findByPrimaryKey(long _funcionariRolID_);

	public void delete(long _funcionariRolID_);

}
