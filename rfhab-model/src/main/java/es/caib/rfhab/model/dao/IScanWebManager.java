package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IScanWebManager extends org.fundaciobit.genapp.common.query.ITableManager<ScanWeb, Long> {


	public ScanWeb create( java.lang.String _transactionID_, java.lang.String _transactionWebID_, long _status_, long _fitxerID_, java.lang.String _fileInfo_, java.lang.String _signedFileInfo_, java.lang.String _metadades_, java.lang.String _missatge_, java.lang.Long _usuariID_, java.sql.Timestamp _dataCreacio_, long _entitatID_) throws I18NException;

	public ScanWeb findByPrimaryKey(long _digitalID_);

	public void delete(long _digitalID_);

}
