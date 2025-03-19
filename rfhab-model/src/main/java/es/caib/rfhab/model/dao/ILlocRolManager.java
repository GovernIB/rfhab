package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ILlocRolManager extends org.fundaciobit.genapp.common.query.ITableManager<LlocRol, Long> {


	public LlocRol create( java.sql.Timestamp _dataCreacio_, long _llocID_, long _rolID_) throws I18NException;

	public LlocRol findByPrimaryKey(long _llocRolID_);

	public void delete(long _llocRolID_);

}
