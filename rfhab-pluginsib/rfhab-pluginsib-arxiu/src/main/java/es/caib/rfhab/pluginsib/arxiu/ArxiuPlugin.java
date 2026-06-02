package es.caib.rfhab.pluginsib.arxiu;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.utils.AbstractPluginProperties;
import org.jboss.logging.Logger;

import es.caib.pluginsib.arxiu.caib.ArxiuPluginCaib;
import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.pluginsib.arxiu.model.DocumentInfo;
import es.caib.pluginsib.arxiu.api.ArxiuException;
import es.caib.pluginsib.arxiu.api.ConsultaFiltre;
import es.caib.pluginsib.arxiu.api.ConsultaResultat;
import es.caib.pluginsib.arxiu.api.ContingutArxiu;
import es.caib.pluginsib.arxiu.api.ContingutOrigen;
import es.caib.pluginsib.arxiu.api.Document;
import es.caib.pluginsib.arxiu.api.DocumentContingut;
import es.caib.pluginsib.arxiu.api.DocumentEstat;
import es.caib.pluginsib.arxiu.api.DocumentEstatElaboracio;
import es.caib.pluginsib.arxiu.api.DocumentExtensio;
import es.caib.pluginsib.arxiu.api.DocumentFormat;
import es.caib.pluginsib.arxiu.api.DocumentMetadades;
import es.caib.pluginsib.arxiu.api.DocumentTipus;
import es.caib.pluginsib.arxiu.api.Expedient;
import es.caib.pluginsib.arxiu.api.ExpedientEstat;
import es.caib.pluginsib.arxiu.api.ExpedientMetadades;
import es.caib.pluginsib.arxiu.api.Firma;
import es.caib.pluginsib.arxiu.api.FirmaPerfil;
import es.caib.pluginsib.arxiu.api.FirmaTipus;
import es.caib.pluginsib.arxiu.api.IArxiuPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Interfície que defineix els mètodes que ha d'implementar un plugin d'arxiu.
 * 
 * @author Fundació BIT
 */

public class ArxiuPlugin extends AbstractPluginProperties implements es.caib.rfhab.pluginsib.arxiu.IArxiuPlugin {

	protected final Logger LOG = Logger.getLogger(this.getClass());

	private static final String ARXIU_PLUGIN_URL = ARXIU_PLUGIN_PROPERTY + "endpoint";
	private static final String ARXIU_PLUGIN_USERNAME = ARXIU_PLUGIN_PROPERTY + "usuari";
	private static final String ARXIU_PLUGIN_PASSWORD = ARXIU_PLUGIN_PROPERTY + "contrasenya";
	private static final String ARXIU_PLUGIN_CODI_APLICACIO = ARXIU_PLUGIN_PROPERTY + "aplicacio";
	private static final String ARXIU_PLUGIN_CONVERSIO_IMPRIMIBLE_URL = ARXIU_PLUGIN_PROPERTY
			+ "conversio.imprimible.url";
	private static final String ARXIU_PLUGIN_CONVERSIO_IMPRIMIBLE_USUARI = ARXIU_PLUGIN_PROPERTY
			+ "conversio.imprimible.usuari";
	private static final String ARXIU_PLUGIN_CONVERSIO_IMPRIMIBLE_PASS = ARXIU_PLUGIN_PROPERTY
			+ "conversio.imprimible.contrasenya";

	/* Propietats del plugin */
	public static final String PROPERTY_SERIE_DOCUMENTAL = ARXIU_PLUGIN_PROPERTY + "serieDocumental";
	private static final String PROPERTY_CLASIFICACIO = ARXIU_PLUGIN_PROPERTY + "classificacio";

	protected final ArxiuManager arxiuManager = new ArxiuManager();

	private Properties propietats;

	public Properties getPropietats() {
		return propietats;
	}

	public void setPropietats(Properties propietats) {
		this.propietats = propietats;
	}

	public ArxiuPlugin() {
		super();
		setPropietats(Configuracio.getAppSystemProperties());
	}

	public ArxiuPlugin(Properties props) {
		super();
		setPropietats(props);
	}

	@Override
	public String tancarExpedientPerId(String identificador) throws I18NException {
		LOG.info("tancarExpedient::" + identificador);
		if (!StringUtils.isNotEmpty(identificador)) {
			LOG.error("tancarExpedient:: Identificador d'expedient buit o null");
			throw new I18NException("error.arxiu.expedient.buit");
		}
		String resultat = null;
		ExpedientEstat estat = consultarEstatExpedient(identificador, null);
		switch (estat) {
			case OBERT:
				LOG.info("tancarExpedient:: Expedient obert, el tancarem");
				resultat = arxiuManager.getPlugin().expedientTancar(identificador);
				LOG.info("tancarExpedient:: Expedient Tancat");

			case TANCAT:
				LOG.info("tancarExpedient::  Expedient ja estava tancat");
				resultat = "";
				break;

			default:
				LOG.info("tancarExpedient::  Expedient index remissio. No el tancarem");
				break;
		}

		return resultat;
	}

	@Override
	public String crearExpedient(DocumentInfo documentInfo, String serieDocumental) throws I18NException {

		Expedient expedient = null;

		try {
			expedient = new Expedient();
			expedient.setIdentificador(null);
			expedient.setNom(documentInfo.getNom());

			ExpedientMetadades metadades = new ExpedientMetadades();
			metadades.setIdentificador(null);
			metadades.setDataObertura(new Date());
			metadades.setClassificacio(propietats.getProperty(PROPERTY_CLASIFICACIO));
			metadades.setEstat(ExpedientEstat.OBERT);
			if (documentInfo.getOrgans() != null && documentInfo.getOrgans().size() > 0)
				metadades.setOrgans(documentInfo.getOrgans());
			if (documentInfo.getInteressats() != null && documentInfo.getInteressats().size() > 0)
				metadades.setInteressats(documentInfo.getInteressats());
			metadades.setSerieDocumental(propietats.getProperty(PROPERTY_SERIE_DOCUMENTAL));
			expedient.setMetadades(metadades);

			IArxiuPlugin arxiuPlugin = arxiuManager.getPlugin();
			LOG.info("Plugin Arxiu instanciat");
			ContingutArxiu expedientCreat = arxiuPlugin.expedientCrear(expedient);

			if (Configuracio.isDesenvolupament()) {
				LOG.info(" ==============  EXPEDIENT CREAT ================== ");
				LOG.info("expedientCreat.identificador => " + expedientCreat.getIdentificador());
				LOG.info("expedientCreat.nom => " + expedientCreat.getNom());
				LOG.info("expedientCreat.versio => " + expedientCreat.getVersio());
			}

			return expedientCreat != null ? expedientCreat.getIdentificador() : null;

		} catch (ArxiuException e) {
			LOG.error("Error ArxiuPlugin alhora de carregar la informació per crear l'expedient: "
					+ e.getLocalizedMessage());
			throw e;
		} catch (Exception e) {
			LOG.error("Error alhora de carregar la informació per crear l'expedient");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String crearDocument(DocumentInfo documentInfo, String expedientId, String serieDocumental,
			String perfilfirma, String tipusFirma) throws I18NException {

		if (documentInfo.getFitxer() == null || documentInfo.getFitxer().getTamany() < 1L) {
			LOG.error("No existeix document adjunt o el tamany es inferior a 1 (" + documentInfo.getFitxer().getTamany()
					+ ")");
			throw new I18NException("Document no vàlid");
		}

		if (StringUtils.isEmpty(expedientId)) {
			LOG.error("No existeix expedientId");
		}

		Fitxer fitxerOriginal = documentInfo.getFitxer();

		Document document = new Document();
		document.setIdentificador(null);
		document.setNom(fitxerOriginal.getNom());
		document.setEstat(DocumentEstat.DEFINITIU);
		// DocumentContingut documentContingut = new DocumentContingut();
		// documentContingut.setContingut(IOUtils.toByteArray(getDocumentAltre("annex.zip")));
		// documentContingut.setTipusMime("application/zip");
		// document.setContingut(documentContingut);
		document.setContingut(null);
		document.setFirmes(new ArrayList<Firma>());

		DocumentMetadades metadadesDoc = new DocumentMetadades();
		metadadesDoc.setIdentificador(null);
		metadadesDoc.setSerieDocumental(serieDocumental);

		if (documentInfo.getOrgans() != null)
			metadadesDoc.setOrgans(documentInfo.getOrgans());

		metadadesDoc.setDataCaptura(new Date());

		metadadesDoc.setOrigen(ContingutOrigen.toEnum(documentInfo.getOrigen()));

		if (documentInfo.getEstatElaboracio() != null)
			metadadesDoc.setEstatElaboracio(DocumentEstatElaboracio.toEnum(documentInfo.getEstatElaboracio()));

		metadadesDoc.setTipusDocumental(DocumentTipus.toEnum(documentInfo.getTipusDocumental()));

		Fitxer fitxerFirmat = documentInfo.getFitxer();
		Firma firma = new Firma();
		firma.setCsvRegulacio("");
		FirmaTipus firmaTipusEnum = FirmaTipus.PADES;// PADeS --> FirmaTipus.toEnum(tipusFirma.toUpperCase());
		if (firmaTipusEnum == null) {
			LOG.error("El tipus de firma no és vàlid: " + tipusFirma);
			throw new I18NException("error.firma.tipus.no.valid", tipusFirma);
		}
		firma.setTipus(firmaTipusEnum);// Hauria de ser FirmaTipus.PADES
		FirmaPerfil perfilFirmaEnum = FirmaPerfil.BES;// AdES-BES --> FirmaPerfil.toEnum(perfilfirma);
		if (perfilFirmaEnum == null) {
			LOG.error("El perfil de firma no és vàlid: " + perfilfirma);
			throw new I18NException("error.firma.perfil.no.valid", perfilfirma);
		}
		firma.setPerfil(perfilFirmaEnum);
		firma.setFitxerNom(fitxerFirmat.getNom());

		try {
			File file = FileSystemManager.getFile(fitxerFirmat.getFitxerID());
			if (!file.exists() || file.length() == 0) {
				LOG.error("El fitxer no existeix o està buit: " + fitxerFirmat.getFitxerID());
				throw new I18NException("error.arxiu.fitxerbuit");
			}
			// DataHandler fitxerDataHandler = fitxerFirmat.getData();
			// InputStream in = fitxerDataHandler.getInputStream();
			// byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(in);
			byte[] byteArray = Files.readAllBytes(file.toPath());
			firma.setContingut(byteArray);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			throw new I18NException("error.arxiu.fitxerbuit");
		}

		firma.setTamany(fitxerFirmat.getTamany());
		firma.setTipusMime(fitxerFirmat.getMime());
		document.getFirmes().add(firma);

		metadadesDoc.setExtensio(DocumentExtensio.PDF);
		metadadesDoc.setFormat(DocumentFormat.PDF);

		document.setMetadades(metadadesDoc);

		ContingutArxiu documentoCreat = arxiuManager.getPlugin().documentCrear(document, expedientId);

		LOG.info("Id Document: " + documentoCreat.getIdentificador());

		return documentoCreat.getIdentificador();
	}

	@Override
	public ExpedientEstat consultarEstatExpedient(String identificador, String versio) throws I18NException {
		LOG.info("Consultant detalls d'expedient amb identificador: " + identificador + " i versió: " + versio);
		Expedient detalls = arxiuManager.getPlugin().expedientDetalls(identificador, versio);
		if (detalls == null || detalls.getExpedientMetadades() == null) {
			LOG.error("No s'han pogut obtenir els detalls de l'expedient amb identificador: " + identificador);
			throw new I18NException(
					"No s'han pogut obtenir els detalls de l'expedient amb identificador: " + identificador);
		}
		ExpedientEstat estat = detalls.getExpedientMetadades().getEstat();
		return estat;
	}

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
	@Override
	public ConsultaResultat expedientConsulta(List<ConsultaFiltre> filtres, Integer pagina,
			Integer itemsPerPagina) throws ArxiuException {
		LOG.info("expedientConsulta::" + filtres.toString());
		return arxiuManager.getPlugin().expedientConsulta(filtres, pagina, itemsPerPagina);
	}

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
	@Override
	public DocumentContingut documentImprimible(String identificador) {
		LOG.info("ArxiuPlugin::documentImprimible::Generant versió imprimible (" + "id=" + identificador + ")... ");
		return arxiuManager.getPlugin().documentImprimible(identificador);
	}

	/*
	 * private Firma getFirma(DocumentInfo documentInfo) throws Exception {
	 * 
	 * Firma firma = null;
	 * FirmaTipus firmaTipus = null;
	 * 
	 * if (documentInfo.getFirma() != null &&
	 * documentInfo.getFirma().getFormatFirma() != null) {
	 * 
	 * LOG.
	 * info("documentInfo.getFirma() != null && documentInfo.getFirma().getFormatFirma().length() > 0"
	 * );
	 * 
	 * // ens envien el document ja firmat, pot ser PADES o detached
	 * firma = new Firma();
	 * firma.setTipus(FirmaTipus.toEnum(documentInfo.getFirma().getFormatFirma()));
	 * firma.setPerfil(getPerfilFirma(documentInfo.getFirma().getPerfilFirma()));
	 * 
	 * if (documentInfo.getFirma().getFirma() == null) {
	 * // firma atached
	 * firma.setFitxerNom(documentInfo.getNom());
	 * firma.setContingut(documentInfo.getFitxer().getContingut());
	 * firma.setTamany(documentInfo.getFitxer().getTamany());
	 * firma.setTipusMime(documentInfo.getFitxer().getTipusMime());
	 * 
	 * }
	 * 
	 * } else if (documentInfo.getSignatura() != null) {
	 * // es tracta d'un document que hem firmat amb portafib: PADES (TF06) O
	 * // CADES(TF04)
	 * 
	 * LOG.info("documentInfo.getSignatura() != null");
	 * 
	 * firma = new Firma();
	 * firma.setFitxerNom(documentInfo.getSignatura().getFileName());
	 * firma.setContingut(documentInfo.getSignatura().getFileData());
	 * firma.setTamany((documentInfo.getSignatura().getFileData()).length);
	 * firma.setTipusMime(documentInfo.getSignatura().getFileMime());
	 * 
	 * if (documentInfo.getSignatura().getEniPerfilFirma() != null)
	 * firma.setPerfil(getPerfilFirma(documentInfo.getSignatura().getEniPerfilFirma(
	 * )));
	 * 
	 * if (documentInfo.getSignatura().getEniTipoFirma() != null) {
	 * switch (documentInfo.getSignatura().getEniTipoFirma().trim()) {
	 * case "TF01":
	 * firmaTipus = FirmaTipus.CSV;
	 * break;
	 * case "TF02":
	 * firmaTipus = FirmaTipus.XADES_DET;
	 * break;
	 * case "TF03":
	 * firmaTipus = FirmaTipus.XADES_ENV;
	 * break;
	 * case "TF04":
	 * firmaTipus = FirmaTipus.CADES_DET;
	 * break;
	 * case "TF05":
	 * firmaTipus = FirmaTipus.CADES_ATT;
	 * break;
	 * case "TF06":
	 * firmaTipus = FirmaTipus.PADES;
	 * break;
	 * case "TF07":
	 * firmaTipus = FirmaTipus.SMIME;
	 * break;
	 * case "TF08":
	 * firmaTipus = FirmaTipus.ODT;
	 * break;
	 * case "TF09":
	 * firmaTipus = FirmaTipus.OOXML;
	 * break;
	 * }
	 * firma.setTipus(firmaTipus);
	 * }
	 * } else {
	 * LOG.info("Firma NULL => No es pot obtenir la firma");
	 * }
	 * 
	 * if (Configuracio.isDesenvolupament() && firma != null) {
	 * LOG.info("========== INFO FIRMA DOCUMENT ARXIU =======================");
	 * LOG.info("firma.getFitxerNom => " + firma.getFitxerNom());
	 * LOG.info("firma.getContingut().length => " + (firma.getContingut()).length);
	 * LOG.info("firma.getTamany() => " + firma.getTamany());
	 * LOG.info("firma.getPerfil() => " + ((firma.getPerfil() != null) ?
	 * firma.getPerfil().name() : "null")
	 * + " - infoSignatura.eniPerfilFirma: " +
	 * documentInfo.getSignatura().getEniPerfilFirma());
	 * LOG.info("firma.getTipusMime => " + firma.getTipusMime());
	 * LOG.info("firma.getTipus => " + ((firma.getTipus() != null) ?
	 * firma.getTipus().name() : "null"));
	 * LOG.info("_______________________________________________");
	 * }
	 * 
	 * return firma;
	 * }
	 */

	public class ArxiuManager {
		private IArxiuPlugin plugin = null;

		public IArxiuPlugin getPlugin() {
			if (plugin == null) {
				plugin = getArxiuPlugin();
			}
			return plugin;
		}

		public void setPlugin(IArxiuPlugin plugin) {
			this.plugin = plugin;
		}

		private IArxiuPlugin getArxiuPlugin() {

			LOG.info("Instanciant plugin d'arxiu");

			Properties appSystemProps = propietats;
			// Properties appSystemProps = Configuracio.getAppSystemProperties();

			Properties properties = new Properties();
			properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.base.url",
					appSystemProps.getProperty(ARXIU_PLUGIN_URL));
			properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.usuari",
					appSystemProps.getProperty(ARXIU_PLUGIN_USERNAME));
			properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.contrasenya",
					appSystemProps.getProperty(ARXIU_PLUGIN_PASSWORD));
			properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.aplicacio.codi",
					appSystemProps.getProperty(ARXIU_PLUGIN_CODI_APLICACIO));
			properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.conversio.imprimible.url",
					appSystemProps.getProperty(ARXIU_PLUGIN_CONVERSIO_IMPRIMIBLE_URL));
			properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.conversio.imprimible.usuari",
					appSystemProps.getProperty(ARXIU_PLUGIN_CONVERSIO_IMPRIMIBLE_USUARI));
			properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.conversio.imprimible.contrasenya",
					appSystemProps.getProperty(ARXIU_PLUGIN_CONVERSIO_IMPRIMIBLE_PASS));

			// return new ArxiuPluginCaib();
			return new ArxiuPluginCaib("", properties);
		}
	}
}