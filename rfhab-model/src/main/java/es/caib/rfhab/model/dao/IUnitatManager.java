package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUnitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Unitat, Long> {


	public Unitat create( java.lang.String _codi_, int _versio_, java.lang.String _denominacio_, java.lang.String _cooficial_, java.lang.String _arrel_, java.lang.Integer _arrelVersio_, java.lang.String _superior_, java.lang.Integer _superiorVersio_, java.lang.String _estat_) throws I18NException;

	public Unitat findByPrimaryKey(long _unitatID_);

	public void delete(long _unitatID_);

}
