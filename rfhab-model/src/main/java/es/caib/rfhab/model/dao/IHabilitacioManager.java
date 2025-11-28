package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IHabilitacioManager extends org.fundaciobit.genapp.common.query.ITableManager<Habilitacio, Long> {


	public Habilitacio create( java.lang.Long _nomID_, java.lang.String _codi_, java.sql.Timestamp _dataCreacio_, java.lang.Long _entitatID_) throws I18NException;

	public Habilitacio findByPrimaryKey(long _habilitacioID_);

	public void delete(long _habilitacioID_);

}
