package es.caib.rfhab.pluginsib.rolsac;

import java.util.HashMap;

import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.rfhab.commons.utils.Constants;

public interface IRolsacPlugin extends IPluginIB {

	public static final String ROLSAC_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES
			+ "rolsac.";

	public HashMap<String, String> obtenirProcedimentsByDir3(String codiDir3) throws Exception;

	public HashMap<String, String[]> obtenirProcedimentsAll(String llengua) throws Exception;

	public HashMap<String, String[]> obtenirTramits(String procedimentId, String llengua) throws Exception;

	public HashMap<String, String[]> obtenirTramitsAll(String llengua) throws Exception;
}