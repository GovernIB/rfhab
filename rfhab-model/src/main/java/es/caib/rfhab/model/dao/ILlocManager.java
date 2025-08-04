package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ILlocManager extends org.fundaciobit.genapp.common.query.ITableManager<Lloc, Long> {


	public Lloc create( java.lang.String _codiLloc_, java.lang.String _nom_, long _entitatID_, long _unitatID_, int _personalOamr_, java.sql.Timestamp _dataalta_, java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataBaixa_, java.lang.String _observacions_) throws I18NException;

	public Lloc findByPrimaryKey(long _llocID_);

	public void delete(long _llocID_);

}
