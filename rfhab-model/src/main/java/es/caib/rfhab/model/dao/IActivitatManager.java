package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IActivitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Activitat, Long> {


	public Activitat create( long _funcionariID_, int _tipus_, java.lang.String _registre_, java.lang.String _tramit_, java.lang.String _codiSia_, java.lang.Long _autoritzacioID_, java.sql.Timestamp _dataCreacio_, java.lang.String _interessatNom_, java.lang.String _interessatLlinatge1_, java.lang.String _interessatLlinatge2_, java.lang.Integer _interessatTipus_, java.lang.String _interessatIdentificacio_, java.lang.String _representantNom_, java.lang.String _representantLlinatge1_, java.lang.String _representantLlinatge2_, java.lang.Integer _representantTipus_, java.lang.String _representantIdentificacio_, java.lang.Integer _tramitVersio_, java.lang.String _arxiuDocumentID_, java.lang.String _arxiuExpedientID_, int _estat_, java.lang.String _url_, java.sql.Timestamp _dataActivitat_, java.lang.String _idActuacioTramit_, java.lang.String _procediment_, java.lang.Integer _arxiuReintents_, java.lang.Integer _arxiuEstat_) throws I18NException;

	public Activitat findByPrimaryKey(long _activitatID_);

	public void delete(long _activitatID_);

}
