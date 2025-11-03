package es.caib.rfhab.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.pluginsib.arxiu.api.ArxiuException;
import es.caib.rfhab.commons.utils.IdentificacioTipus;
import es.caib.rfhab.commons.utils.RegistreActivitatTipus;
import es.caib.rfhab.ejb.ActivitatService;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.persistence.validator.ActivitatValidator;

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

	public Activitat createIupdate(Activitat activitatAcrear, Activitat activitatAactualitzar) throws I18NException;

	public Activitat registraNovaActivitat(String language, ActivitatValidator<Activitat> validator,
			RegistreActivitatTipus tipus, String csvCopiaAutentica, String registre,
			String idActuacioTramitFh, String tramit, String tramitVersio, String procediment, String nomInteressat,
			String llinatge1Interessat, String llinatge2Interessat, IdentificacioTipus tipusIdentificacioInteressat,
			String identificacioInteressat, String nomRepresentant, String llinatge1Representant,
			String llinatge2Representant, IdentificacioTipus tipusIdentificacioRepresentant,
			String identificacioRepresentant, String arxiuExpedientId, String arxiuDocumentId, Timestamp dataActivitat,
			Long funcionariId) throws I18NException;

	public Where getActivitatsByFuncionariNomCompletWhere(String funcionariNom)
			throws I18NException, NoSuchFieldException;

	public Where getActivitatsByFuncionariNifWhere(String funcionariNif)
			throws I18NException, NoSuchFieldException;
}
