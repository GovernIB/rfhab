package es.caib.rfhab.pluginsib.dir3caib;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.rfhab.commons.utils.Constants;

/**
 * 
 * @autor jagarcia
 */

public interface IDir3CaibPlugin extends IPluginIB {
	
	public static final String DIR3CAIB_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES + "dir3caib.";
	
	public void sincronitzar() throws I18NException;
	
}
