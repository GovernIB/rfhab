package es.caib.rfhab.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.pluginsib.arxiu.api.ArxiuException;
import es.caib.pluginsib.arxiu.api.ConsultaFiltre;
import es.caib.pluginsib.arxiu.api.ConsultaOperacio;
import es.caib.pluginsib.arxiu.api.ConsultaResultat;
import es.caib.pluginsib.arxiu.api.ContingutArxiu;
import es.caib.pluginsib.arxiu.api.ContingutOrigen;
import es.caib.pluginsib.arxiu.api.DocumentContingut;
import es.caib.pluginsib.arxiu.api.DocumentEstatElaboracio;
import es.caib.pluginsib.arxiu.api.DocumentTipus;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.FileNameCleaner;
import es.caib.rfhab.ejb.ActivitatEJB;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.model.fields.ActivitatFields;
import es.caib.rfhab.persistence.FitxerJPA;
import es.caib.rfhab.pluginsib.arxiu.ArxiuPlugin;
import es.caib.rfhab.pluginsib.arxiu.model.DocumentInfo;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Stateless
public class ActivitatLogicaEJB extends ActivitatEJB implements ActivitatLogicaService {

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerLogicaEjb;

	ArxiuPlugin pluginArxiu = new ArxiuPlugin();

	@Override
	public List<Activitat> getActivitatsByFuncionariID(Long funcionariId) throws I18NException {
		return this.select(ActivitatFields.FUNCIONARIID.equal(funcionariId));
	}

	@Override
	public HashMap<String, String> guardarArxiu(Fitxer fitxer, String nom, String perfilfirma, String tipusFirma,
			List<String> interessats, List<String> organs)
			throws I18NException {
		log.info("ActivitatLogicaEJB::guardarArxiu");
		HashMap<String, String> identificadors = new HashMap<String, String>();

		// TODO: afegir guardat a taula d'arxiu???

		log.info("ActivitatLogicaEJB::guardarArxiu::creant documentInfo");
		DocumentInfo expedientPerCrear = new DocumentInfo();
		expedientPerCrear.setEstatElaboracio(DocumentEstatElaboracio.COPIA_DP.toString());
		expedientPerCrear.setFitxer(fitxer);
		expedientPerCrear.setInteressats(interessats);
		expedientPerCrear.setMetadades(null);// TODO: no emprat??
		expedientPerCrear.setNom(nom);
		expedientPerCrear.setNumeroRegistre(null);// TODO: no emprat??
		expedientPerCrear.setOrgans(organs);
		expedientPerCrear.setOrigen(ContingutOrigen.ADMINISTRACIO.toString());// TODO: no emprat??
		expedientPerCrear.setTipusDocumental(DocumentTipus.ALTRES.toString());
		String serieDocumental = pluginArxiu.getPropietats().getProperty(ArxiuPlugin.PROPERTY_SERIE_DOCUMENTAL);

		log.info("ActivitatLogicaEJB::guardarArxiu::creant expedient");
		String identificadorExpedient = null;
		try {
			identificadorExpedient = pluginArxiu.crearExpedient(expedientPerCrear, serieDocumental);
		} catch (ArxiuException e) {
			log.error(
					" Error Creant Expedient: " + e.getMessage() + ". Consultam si l'expedient ja està creat...");

			// Comprovar si l'expedient ja existeix
			ConsultaResultat resultat;
			resultat = pluginArxiu.expedientConsulta(getLlistaFiltresExpedienteMetadatos(nom, serieDocumental),
					0, 111);

			if (resultat.getResultats() != null && resultat.getResultats().size() != 0) {

				for (ContingutArxiu ca : resultat.getResultats()) {

					if (nom.equals(ca.getNom())) {
						identificadorExpedient = ca.getIdentificador();
						log.info("XYZ ZZZ TMP Expedient ja existia (ID = " + identificadorExpedient + ")");
					}
				}
			}

			// Si expedientID val null, vol dir que ni l'ha pogut crear, i que tampoc
			// existia
			// ja a arxiu (no està duplicat)
			if (identificadorExpedient == null) {
				log.error("No hem trobat expedient amb nom " + nom + ". Llançan excepció original.");
				throw e;
			}
		}
		String identificadorDocument = null;

		if (identificadorExpedient == null) {
			log.error("No s'ha pogut crear l'expedient.");
			throw new I18NException("error.creacio.expedient");
		}
		log.info("ActivitatLogicaEJB::guardarArxiu::expedient creat amb id: " + identificadorExpedient);
		// TODO:ficar aquests reintents només a la cridada que es fa dins el plugin
		// TODO: revisar que si no s'ha creat el document, no es tanqui l'expedient
		int intents = 0;
		while (intents++ < Constants.ARXIU_PLUGIN_REINTENTS_CREAR_DOCUMENT && identificadorDocument == null) {
			log.info("ActivitatLogicaEJB::guardarArxiu::creant document intent: " + intents + " de "
					+ Constants.ARXIU_PLUGIN_REINTENTS_CREAR_DOCUMENT);
			try {
				identificadorDocument = pluginArxiu.crearDocument(expedientPerCrear, identificadorExpedient,
						serieDocumental, perfilfirma, tipusFirma);
			} catch (ArxiuException aex) {
				log.error("Error ArxiuPlugin alhora de crear document: "
						+ aex.getLocalizedMessage());
				aex.printStackTrace();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				tancarExpedient(identificadorExpedient);
				throw e;
			}
			// arxiuexception
			if (identificadorDocument != null) {
				log.info("ActivitatLogicaEJB::guardarArxiu::document creat amb id: " + identificadorDocument);
			} else {
				if (intents <= Constants.ARXIU_PLUGIN_REINTENTS_CREAR_DOCUMENT) {
					log.warn("ActivitatLogicaEJB::guardarArxiu::no s'ha pogut crear el document, reintentant");
				} else {
					log.error(
							"ActivitatLogicaEJB::guardarArxiu::no s'ha pogut crear el document, no es reintentarà més");
					tancarExpedient(identificadorExpedient);
				}
			}
		}

		identificadors.put(identificadorExpedient, identificadorDocument);
		return identificadors;
	}

	@Override
	public String tancarExpedient(String identificador) throws I18NException {
		log.info("ActivitatLogicaEJB::tancarExpedient");
		if (identificador == null || identificador.isEmpty()) {
			log.error("Identificador d'expedient no pot ser null o buit.");
			throw new I18NException("error.identificador.expedient",
					"El identificador d'expedient no pot ser null o buit.");
		}

		// TODO: afegir guardat a taula d'arxiu???

		String identificadorExpedient = null;
		int intents = 0;
		while (intents++ < Constants.ARXIU_PLUGIN_REINTENTS_TANCAR_EXPEDIENT && identificadorExpedient == null) {
			log.info("ActivitatLogicaEJB::tancarExpedient::tancant expedient intent: " + intents + " de "
					+ Constants.ARXIU_PLUGIN_REINTENTS_TANCAR_EXPEDIENT);
			identificadorExpedient = pluginArxiu.tancarExpedientPerId(identificador);
			if (identificadorExpedient != null) {
				log.info("ActivitatLogicaEJB::tancarExpedient::expedient tancat amb id: " + identificadorExpedient);
			} else {
				if (intents <= Constants.ARXIU_PLUGIN_REINTENTS_TANCAR_EXPEDIENT) {
					log.warn("ActivitatLogicaEJB::tancarExpedient::no s'ha pogut tancar l'expedient, reintentant");
				} else {
					log.error(
							"ActivitatLogicaEJB::tancarExpedient::no s'ha pogut tancar l'expedient, no es reintentarà més");
					// TODO:crear missatge
					throw new I18NException("error.tancament.expedient", "No s'ha pogut tancar l'expedient.");
				}
			}
		}

		return identificadorExpedient;
	}

	/**
	 * Genera la versió imprimible del document.
	 * 
	 * @param identificador
	 *                      Identificador del document.
	 * @return La informació de l'arxiu imprimible en format PDF.
	 * @throws FileNotFoundException Si es produeix qualque error al moment de crear
	 *                               el fitxer
	 * @throws IOException           Si es produeix qualque error a l'escriure el
	 *                               contingut fitxer al
	 *                               disc dur.
	 * @throws ArxiuException
	 *                               Si es produeix algun problema al realitzar
	 *                               l’operació
	 *                               amb l’arxiu.
	 */
	@Override
	public Fitxer documentImprimible(String identificador) throws IOException, FileNotFoundException, ArxiuException {
		// TODO: SA MERDA SEMPRE CAP AMUNT
		log.info("ActivitatLogicaEJB::documentImprimible::" + identificador);
		File filesPath = new File(FileSystemManager.getFilesPath(), "arxiu");
		filesPath.mkdirs();

		DocumentContingut document = pluginArxiu.documentImprimible(identificador);
		if (document == null) {
			log.error("ActivitatLogicaEJB::documentImprimible::no s'ha pogut obtenir el document imprimible");
			return null;
		}

		log.info("ActivitatLogicaEJB::documentImprimible::obtenció del document imprimible correcta");

		File imprimibleFile = new File(filesPath,
				FileNameCleaner.cleanFileName(identificador + "_arxiu." + document.getArxiuNom()));

		FileOutputStream fos = new FileOutputStream(imprimibleFile);
		fos.write(document.getContingut());
		fos.flush();
		fos.close();

		String fileMime = document.getTipusMime();

		try {
			return guardaDocumentImprimible(imprimibleFile, fileMime);
		} catch (I18NException e) {
			log.error("Error guardant el document imprimible: " + e.getMessage(), e);
			return null;
		}
	}

	private Fitxer guardaDocumentImprimible(File file, String fileMime) throws I18NException {
		Fitxer fitxer = new FitxerJPA();
		fitxer.setNom(file.getName());
		fitxer.setDescripcio(null);
		fitxer.setMime(fileMime);// això sempre hauria de ser un mime tipus "application/pdf"
		fitxer.setTamany(file.length());
		fitxer = fitxerLogicaEjb.create(fitxer);
		FileSystemManager.crearFitxer(file, fitxer.getFitxerID());

		// ScanWeb escaneig = new ScanWebJPA();
		// escaneig.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		// escaneig.setEntitatID(entitatId);
		// escaneig.setFileInfo(escaneigFileInfo);
		// escaneig.setFitxerID(fitxer.getFitxerID());
		// escaneig.setMetadades(escaneigMetadades);
		// escaneig.setMissatge(escaneigMissatge);
		// escaneig.setStatus(escaneigStatus);
		// escaneig.setSignedFileInfo(escaneigSignedFileInfo);
		// escaneig.setTransactionID(transactionId);
		// escaneig.setTransactionWebID(transactionWebId);
		// escaneig.setUsuariID(usuariId);
		// create(escaneig);

		return fitxer;
	}

	private static List<ConsultaFiltre> getLlistaFiltresExpedienteMetadatos(String expedientNom,
			String serieDocumental) {
		List<ConsultaFiltre> listaFiltros = new ArrayList<>();
		ConsultaFiltre filtro = null;

		/*
		 * filtro = new ConsultaFiltre(); filtro.setMetadada("eni:organo");
		 * filtro.setOperacio(ConsultaOperacio.IGUAL);
		 * filtro.setValorOperacio1("A04019281"); listaFiltros.add(filtro);
		 */

		filtro = new ConsultaFiltre();
		filtro.setMetadada("name");
		filtro.setOperacio(ConsultaOperacio.IGUAL);
		filtro.setValorOperacio1(expedientNom);
		listaFiltros.add(filtro);

		filtro = new ConsultaFiltre();
		filtro.setMetadada("eni:cod_clasificacion");
		filtro.setOperacio(ConsultaOperacio.IGUAL);
		filtro.setValorOperacio1(serieDocumental);
		listaFiltros.add(filtro);
		/*
		 * filtro = new ConsultaFiltre(); filtro.setMetadada("eni:fecha_inicio");
		 * filtro.setOperacio(ConsultaOperacio.ENTRE);
		 * filtro.setValorOperacio1(getStringDatetoStringISO8601("01/10/2021"));
		 * filtro.setValorOperacio2(getStringDatetoStringISO8601("30/11/2021"));
		 * listaFiltros.add(filtro);
		 */
		return listaFiltros;
	}
}
