package es.caib.rfhab.model.dao;

import es.caib.rfhab.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariEntitat, Long> {


	public UsuariEntitat create( long _entitatID_, long _usuariID_, boolean _actiu_) throws I18NException;

	public UsuariEntitat findByPrimaryKey(long _usuariEntitatID_);

	public void delete(long _usuariEntitatID_);

}
