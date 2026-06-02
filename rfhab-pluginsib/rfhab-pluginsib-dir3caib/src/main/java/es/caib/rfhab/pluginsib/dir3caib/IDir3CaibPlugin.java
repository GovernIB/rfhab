package es.caib.rfhab.pluginsib.dir3caib;

import java.sql.Timestamp;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.rfhab.commons.utils.Constants;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 */

public interface IDir3CaibPlugin extends IPluginIB {

	public static final String DIR3CAIB_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES
			+ "dir3caib.";

	public void sincronitzar(String codiArrel, Timestamp fechaActualizacion, Timestamp fechaSincronizacion)
			throws I18NException;

}
