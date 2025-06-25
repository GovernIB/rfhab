package es.caib.rfhab.pluginsib.arxiu;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.utils.AbstractPluginProperties;
import org.jboss.logging.Logger;

import es.caib.pluginsib.arxiu.caib.ArxiuPluginCaib;
import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.pluginsib.arxiu.model.DocumentInfo;
import es.caib.pluginsib.arxiu.api.ContingutArxiu;
import es.caib.pluginsib.arxiu.api.ContingutOrigen;
import es.caib.pluginsib.arxiu.api.Document;
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
import es.caib.pluginsib.arxiu.api.IArxiuPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.inject.Inject;

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

	/* Propietats del plugin */
	private static final String PROPERTY_SERIE_DOCUMENTAL = ARXIU_PLUGIN_PROPERTY + "serieDocumental";
	private static final String PROPERTY_CLASIFICACIO = ARXIU_PLUGIN_PROPERTY + "classificacio";
	private static final String PROPERTY_CODI_APLICACIO = ARXIU_PLUGIN_PROPERTY + "aplicacio";
	private static final String PROPERTY_TANCAR_EXPEDIENT = ARXIU_PLUGIN_PROPERTY + "tancarExpedient";
	private static final String PROPERTY_SCHEDULER_EXPRESSION = ARXIU_PLUGIN_PROPERTY + "scheduler";

	private Properties propietats;

	@Inject
	private IArxiuPlugin plugin = null;

	public Properties getPropietats() {
		return propietats;
	}

	public void setPropietats(Properties propietats) {
		this.propietats = propietats;
	}

	public IArxiuPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(IArxiuPlugin plugin) {
		this.plugin = plugin;
	}

	public ArxiuPlugin() {
		super();
	}

	@Override
	public String tancarExpedientPerId(String identificador) {
		LOG.info("tancarExpedient::" + identificador);
		if (StringUtils.isNotEmpty(identificador)) {
			if (plugin == null) {
				plugin = getArxiuPlugin();
			}
			return plugin.expedientTancar(identificador);
		}
		return null;
	}

	private IArxiuPlugin getArxiuPlugin() {

		LOG.info("Instanciant plugin d'arxiu");

		Properties appSystemProps = Configuracio.getAppSystemProperties();

		Properties properties = new Properties();
		properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.base.url",
				appSystemProps.getProperty(ARXIU_PLUGIN_URL));
		properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.usuari",
				appSystemProps.getProperty(ARXIU_PLUGIN_USERNAME));
		properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.contrasenya",
				appSystemProps.getProperty(ARXIU_PLUGIN_PASSWORD));
		properties.setProperty(ArxiuPluginCaib.ARXIU_BASE_PROPERTY + "caib.aplicacio.codi",
				appSystemProps.getProperty(ARXIU_PLUGIN_CODI_APLICACIO));

		return new ArxiuPluginCaib(Constants.RFHAB_PROPERTY_BASE, properties);

	}

	@Override
	public String crearExpedient(DocumentInfo documentInfo) throws I18NException {

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

			if (plugin == null) {
				plugin = getArxiuPlugin();
			}

			ContingutArxiu expedientCreat = plugin.expedientCrear(expedient);

			if (Configuracio.isDesenvolupament()) {
				LOG.info(" ==============  EXPEDIENT CREAT ================== ");
				LOG.info("expedientCreat.identificador => " + expedientCreat.getIdentificador());
				LOG.info("expedientCreat.nom => " + expedientCreat.getNom());
				LOG.info("expedientCreat.versio => " + expedientCreat.getVersio());
			}

			return expedientCreat.getIdentificador();

		} catch (Exception e) {
			LOG.error("Error alhora de carregar la informació per crear l'expedient");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String crearDocument(DocumentInfo documentInfo, String expedientId) throws I18NException {

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
		document.setContingut(null);
		document.setFirmes(new ArrayList<Firma>());

		DocumentMetadades metadadesDoc = new DocumentMetadades();
		metadadesDoc.setIdentificador(null);
		metadadesDoc.setSerieDocumental(propietats.getProperty(PROPERTY_SERIE_DOCUMENTAL));

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
		//firma.setTipus(FirmaTipus.toEnum(documentInfo.getFirma().getFormatFirma()));
		//firma.setPerfil(getPerfilFirma(documentInfo.getFirma().getPerfilFirma()));
		firma.setFitxerNom(fitxerFirmat.getNom());
		
		try {
			InputStream in = fitxerFirmat.getData().getInputStream();
			byte[] byteArray=org.apache.commons.io.IOUtils.toByteArray(in);
			firma.setContingut(byteArray);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		
		firma.setTamany(fitxerFirmat.getTamany());
		firma.setTipusMime(fitxerFirmat.getMime());
		document.getFirmes().add(firma);

		metadadesDoc.setExtensio(DocumentExtensio.PDF);
		metadadesDoc.setFormat(DocumentFormat.PDF);
		
		document.setMetadades(metadadesDoc);
		
		if (plugin == null) {
			plugin = getArxiuPlugin();
		}

		ContingutArxiu documentoCreado = plugin.documentCrear(document, expedientId);

		LOG.info("Id Documento: " + documentoCreado.getIdentificador());

		return documentoCreado.getIdentificador();
	}

	@Override
	public boolean tancarExpedient(String identificador) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	private Firma getFirma(DocumentInfo documentInfo) throws Exception {

		Firma firma = null;
		FirmaTipus firmaTipus = null;

		if (documentInfo.getFirma() != null && documentInfo.getFirma().getFormatFirma() != null) {

			LOG.info("documentInfo.getFirma() != null && documentInfo.getFirma().getFormatFirma().length() > 0");

			// ens envien el document ja firmat, pot ser PADES o detached
			firma = new Firma();
			firma.setTipus(FirmaTipus.toEnum(documentInfo.getFirma().getFormatFirma()));
			firma.setPerfil(getPerfilFirma(documentInfo.getFirma().getPerfilFirma()));

			if (documentInfo.getFirma().getFirma() == null) {
				// firma atached
				firma.setFitxerNom(documentInfo.getNom());
				firma.setContingut(documentInfo.getFitxer().getContingut());
				firma.setTamany(documentInfo.getFitxer().getTamany());
				firma.setTipusMime(documentInfo.getFitxer().getTipusMime());

			} 

		} else if (documentInfo.getSignatura() != null) {
			// es tracta d'un document que hem firmat amb portafib: PADES (TF06) O
			// CADES(TF04)

			LOG.info("documentInfo.getSignatura() != null");

			firma = new Firma();
			firma.setFitxerNom(documentInfo.getSignatura().getFileName());
			firma.setContingut(documentInfo.getSignatura().getFileData());
			firma.setTamany((documentInfo.getSignatura().getFileData()).length);
			firma.setTipusMime(documentInfo.getSignatura().getFileMime());

			if (documentInfo.getSignatura().getEniPerfilFirma() != null)
				firma.setPerfil(getPerfilFirma(documentInfo.getSignatura().getEniPerfilFirma()));

			if (documentInfo.getSignatura().getEniTipoFirma() != null) {
				switch (documentInfo.getSignatura().getEniTipoFirma().trim()) {
				case "TF01":
					firmaTipus = FirmaTipus.CSV;
					break;
				case "TF02":
					firmaTipus = FirmaTipus.XADES_DET;
					break;
				case "TF03":
					firmaTipus = FirmaTipus.XADES_ENV;
					break;
				case "TF04":
					firmaTipus = FirmaTipus.CADES_DET;
					break;
				case "TF05":
					firmaTipus = FirmaTipus.CADES_ATT;
					break;
				case "TF06":
					firmaTipus = FirmaTipus.PADES;
					break;
				case "TF07":
					firmaTipus = FirmaTipus.SMIME;
					break;
				case "TF08":
					firmaTipus = FirmaTipus.ODT;
					break;
				case "TF09":
					firmaTipus = FirmaTipus.OOXML;
					break;
				}
				firma.setTipus(firmaTipus);
			}
		} else {
			LOG.info("Firma NULL => No es pot obtenir la firma");
		}

		if (Configuracio.isDesenvolupament() && firma != null) {
			LOG.info("========== INFO FIRMA DOCUMENT ARXIU =======================");
			LOG.info("firma.getFitxerNom => " + firma.getFitxerNom());
			LOG.info("firma.getContingut().length => " + (firma.getContingut()).length);
			LOG.info("firma.getTamany() => " + firma.getTamany());
			LOG.info("firma.getPerfil() => " + ((firma.getPerfil() != null) ? firma.getPerfil().name() : "null")
					+ " - infoSignatura.eniPerfilFirma: " + documentInfo.getSignatura().getEniPerfilFirma());
			LOG.info("firma.getTipusMime => " + firma.getTipusMime());
			LOG.info("firma.getTipus => " + ((firma.getTipus() != null) ? firma.getTipus().name() : "null"));
			LOG.info("_______________________________________________");
		}

		return firma;
	}
	*/

}