package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAutoritzacioManager extends org.fundaciobit.genapp.common.query.ITableManager<Autoritzacio, Long> {


	public Autoritzacio create( long _llocID_, java.lang.String _codiSia_, java.lang.String _procediment_, java.lang.String _cai_, java.sql.Date _dataInici_, java.sql.Date _dataFi_, java.sql.Timestamp _dataCreacio_, java.lang.String _observacions_, java.lang.Long _usuariID_, java.lang.Long _funcionariID_) throws I18NException;

	public Autoritzacio findByPrimaryKey(long _autoritzacioID_);

	public void delete(long _autoritzacioID_);

}
