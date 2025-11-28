package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ILlocHabilitacioManager extends org.fundaciobit.genapp.common.query.ITableManager<LlocHabilitacio, Long> {


	public LlocHabilitacio create( java.sql.Timestamp _dataCreacio_, long _llocID_, long _habilitacioId_) throws I18NException;

	public LlocHabilitacio findByPrimaryKey(long _llocHabilitacioID_);

	public void delete(long _llocHabilitacioID_);

}
