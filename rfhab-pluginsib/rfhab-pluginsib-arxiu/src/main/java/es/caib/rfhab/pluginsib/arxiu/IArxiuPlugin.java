package es.caib.rfhab.pluginsib.arxiu;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;

import es.caib.pluginsib.arxiu.api.ArxiuException;
import es.caib.pluginsib.arxiu.api.ConsultaFiltre;
import es.caib.pluginsib.arxiu.api.ConsultaResultat;
import es.caib.pluginsib.arxiu.api.DocumentContingut;
import es.caib.pluginsib.arxiu.api.ExpedientEstat;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.pluginsib.arxiu.model.DocumentInfo;

/**
 * Interfície que defineix els mètodes que ha d'implementar un plugin d'arxiu.
 * 
 * @author Fundació BIT
 */

public interface IArxiuPlugin extends IPluginIB {

	public static final String ARXIU_PLUGIN_PROPERTY = Constants.RFHAB_PROPERTY_BASE + IPLUGINSIB_BASE_PROPERTIES
			+ "arxiu.";

	public String crearExpedient(DocumentInfo documentInfo, String serieDocumental) throws I18NException;

	public String crearDocument(DocumentInfo documentInfo, String expedientId, String serieDocumental,
			String perfilfirma, String tipusFirma) throws I18NException;

	public String tancarExpedientPerId(String identificador) throws I18NException;

	/**
	 * Realitza una consulta d'expedients.
	 * 
	 * @param filtres
	 *                       Llista de filtres per aplicar a la consulta.
	 * @param pagina
	 *                       Número de la pàgina de resultats que s'ha de retornar.
	 *                       Si te el valor null es
	 *                       fa la consulta sense paginació.
	 * @param itemsPerPagina
	 *                       Nombre d'elements per pàgina. Si te el valor null es fa
	 *                       la consulta sense
	 *                       paginació.
	 * @return El resultat de la consulta.
	 * @throws ArxiuException
	 *                        Si es produeix algun problema al realitzar l’operació
	 *                        amb l’arxiu.
	 */
	public ConsultaResultat expedientConsulta(List<ConsultaFiltre> filtres, Integer pagina,
			Integer itemsPerPagina) throws ArxiuException;

	/**
	 * Genera la versió imprimible del document.
	 * 
	 * @param identificador
	 *                      Identificador del document.
	 * @return La informació de l'arxiu imprimible en format PDF.
	 * @throws ArxiuException
	 *                        Si es produeix algun problema al realitzar l’operació
	 *                        amb l’arxiu.
	 */
	public DocumentContingut documentImprimible(String identificador);

	public ExpedientEstat consultarEstatExpedient(String identificador, String versio) throws I18NException;
}