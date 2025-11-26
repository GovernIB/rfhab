package es.caib.rfhab.pluginsib.rolsac;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.pluginsib.rolsac.model.Tramits;

public interface IRolsacPlugin extends IPluginIB {

	public static final String ROLSAC_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES
			+ "rolsac.";

	public HashMap<String, String[]> obtenirProcedimentsByDir3(String codiDir3, String llengua) throws Exception;

	public HashMap<String, String[]> reobtenirProcedimentsAll(String llengua) throws Exception;

	public HashMap<String, String[]> obtenirTramits(String procedimentId, String llengua) throws Exception;

	public HashMap<String, String[]> obtenirTramitsAll(String llengua) throws Exception;

	public String getCodiDir3UnitatAdministrativa(String codi) throws InterruptedException, ExecutionException;

	public Tramits obtenirTramitPerId(String tramitId, String llengua) throws Exception;
}