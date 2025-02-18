package es.caib.rfhab.pluginsib.arxiu;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.pluginsib.arxiu.model.DocumentInfo;

/**
 * Interfície que defineix els mètodes que ha d'implementar un plugin d'arxiu.
 * 
 * @author Fundació BIT
 */

public interface IArxiuPlugin extends IPluginIB {
	
	public static final String ARXIU_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES + "arxiu.";
	
	public String crearExpedient(DocumentInfo documentInfo) throws I18NException;
	
	public String crearDocument(DocumentInfo documentInfo, String expedientId) throws I18NException;
	
	public String tancarExpedientPerId(String identificador) throws I18NException;
	
	public boolean tancarExpedient(String identificador) throws Exception;
	
}