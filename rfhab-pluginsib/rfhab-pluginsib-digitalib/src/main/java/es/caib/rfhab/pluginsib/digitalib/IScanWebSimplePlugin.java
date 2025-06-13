package es.caib.rfhab.pluginsib.digitalib;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSubtransactionResult;
import es.caib.rfhab.commons.utils.Constants;

/*
 *
 * @author jpou
 * 
 */
public interface IScanWebSimplePlugin extends IPluginIB {

	public static final String SCANWEB_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES
			+ "scanweb.";

	public HashMap<String, String> escaneig(String firstPartReturnUrl, String usuari, String languageUI,
			String funcionariNom,
			String funcionariAdministracioID, String funcionariDir3, List<String> interessats, List<String> organs,
			String ciutadaNif, String ciutadaNom)
			throws Exception;

	public Map<String, MassiveScanWebSimpleSubtransactionResult> finalitzaEscaneig(String transactionID)
			throws Exception;

}
