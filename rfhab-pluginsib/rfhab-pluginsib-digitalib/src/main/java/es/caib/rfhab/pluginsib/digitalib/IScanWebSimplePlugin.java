package es.caib.rfhab.pluginsib.digitalib;

import java.io.File;
import java.util.List;

import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.rfhab.commons.utils.Constants;

public interface IScanWebSimplePlugin extends IPluginIB {

	public static final String SCANWEB_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES
			+ "scanweb.";

	public List<String> escaneig(String usuari, String languageUI, String funcionariNom,
			String funcionariAdministracioID,
			String funcionariDir3, List<String> interessats, List<String> organs, String ciutadaNif, String ciutadaNom,
			File filesPath)
			throws Exception;

}
