package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IRolManager extends org.fundaciobit.genapp.common.query.ITableManager<Rol, Long> {


	public Rol create( java.lang.Long _nomID_, java.lang.String _codi_, java.sql.Timestamp _dataCreacio_, java.lang.Long _entitatID_) throws I18NException;

	public Rol findByPrimaryKey(long _rolID_);

	public void delete(long _rolID_);

}
