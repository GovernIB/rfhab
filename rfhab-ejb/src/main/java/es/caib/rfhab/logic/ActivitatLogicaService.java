package es.caib.rfhab.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.pluginsib.arxiu.api.ArxiuException;
import es.caib.pluginsib.arxiu.api.DocumentContingut;
import es.caib.rfhab.ejb.ActivitatService;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Fitxer;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */

@Local
public interface ActivitatLogicaService extends ActivitatService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/ActivitatLogicaEJB!es.caib.rfhab.logic.ActivitatLogicaService";

	public List<Activitat> getActivitatsByFuncionariID(Long funcionariId) throws I18NException;

	public String tancarExpedient(String identificador) throws I18NException;

	public HashMap<String, String> guardarArxiu(Fitxer fitxer, String nom, String perfilfirma, String tipusFirma,
			List<String> interessats, List<String> organs)
			throws I18NException;

	public Fitxer documentImprimible(String identificador) throws IOException, FileNotFoundException, ArxiuException;
}
