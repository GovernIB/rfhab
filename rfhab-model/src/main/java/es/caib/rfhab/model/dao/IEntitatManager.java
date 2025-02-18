package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Entitat, Long> {


	public Entitat create( java.lang.String _nom_, boolean _actiu_, java.lang.Long _unitatID_, java.sql.Timestamp _dataBaixa_) throws I18NException;

	public Entitat findByPrimaryKey(long _entitatID_);

	public void delete(long _entitatID_);

}
