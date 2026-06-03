package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ISincroUnitatsManager extends org.fundaciobit.genapp.common.query.ITableManager<SincroUnitats, Long> {


	public SincroUnitats create( java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataDarreraSincro_, java.sql.Timestamp _dataPrimeraSincro_, java.lang.String _codiEntitat_, java.lang.String _observacions_, java.lang.Long _usuariId_) throws I18NException;

	public SincroUnitats findByPrimaryKey(long _sincrounitatsId_);

	public void delete(long _sincrounitatsId_);

}
