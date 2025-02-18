package es.caib.rfhab.pluginsib.rolsac;

import java.util.HashMap;
import java.util.List;

import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.Procedimientos;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.Tramites;

public interface IRolsacPlugin extends IPluginIB{
	
	public static final String ROLSAC_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES + "rolsac.";

	public HashMap<String,String> obtenirProcediments() throws Exception;

	public HashMap<String,String> obtenirTramits(String procedimentId) throws Exception;

}