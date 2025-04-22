package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFuncionariManager extends org.fundaciobit.genapp.common.query.ITableManager<Funcionari, Long> {


	public Funcionari create( java.lang.String _numero_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, int _tipusIdentificador_, java.lang.String _identificador_, java.lang.String _usuari_, java.lang.String _correu_, java.sql.Timestamp _dataCreacio_, java.lang.String _observacions_, java.sql.Timestamp _dataBaixa_, long _entitatID_) throws I18NException;

	public Funcionari findByPrimaryKey(long _funcionariID_);

	public void delete(long _funcionariID_);

}
