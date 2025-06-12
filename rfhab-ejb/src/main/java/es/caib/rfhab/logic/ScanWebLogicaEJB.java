package es.caib.rfhab.logic;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.ScanWebEJB;
import es.caib.rfhab.model.bean.ScanWebBean;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.persistence.FitxerJPA;
import es.caib.rfhab.pluginsib.digitalib.ScanWebSimplePlugin;

/**
 * 
 * @author jpou
 *
 */
@Stateless
public class ScanWebLogicaEJB extends ScanWebEJB implements ScanWebLogicaService {
	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerLogicaEjb;

	// TODO: El plugin ScanWebSimplePlugin s'hauria de carregar a través del EJB o
	// un plugin manager
	private ScanWebSimplePlugin scanwebPlugin = new ScanWebSimplePlugin();

	private static Map<String, Map<String, String>> transactionsStarted = new HashMap<>();

	@Override
	public Map<String, String> checkFinalScanweb(String transactionID) {
		Map<String, String> urlFitxersFirmatsOerrors = transactionsStarted.get(transactionID);

		// procés acabat, esborrem la transacció
		if (urlFitxersFirmatsOerrors != null) {
			log.info("XYZ YYY checkFinalScanweb FINALITZADA transactionID = " + transactionID
					+ ", urlFitxersFirmatsOerrors = "
					+ urlFitxersFirmatsOerrors);
			transactionsStarted.remove(transactionID);
		}
		return urlFitxersFirmatsOerrors;
	}

	@Override
	public HashMap<String, String> escaneig(String firstPartReturnUrl, String username, String languageUI,
			String funcionariNom,
			String funcionariAdministracioID, String funcionariDir3, List<String> interessatsList, List<String> organs,
			String ciutadaNif, String ciutadaNom) throws Exception {
		HashMap<String, String> transactionPreparedOrErrors = scanwebPlugin.escaneig(firstPartReturnUrl,
				username, languageUI, funcionariNom, funcionariAdministracioID, funcionariDir3, interessatsList, organs,
				ciutadaNif, ciutadaNom);
		log.info("XYZ YYY transactionPreparedOrErrors = " + transactionPreparedOrErrors);

		for (Entry<String, String> entry : transactionPreparedOrErrors.entrySet()) {
			log.info("XYZ YYY transactionPreparedOrErrors entry: " + entry.getKey() + " = " + entry.getValue());
			transactionsStarted.put(entry.getKey(), null);
		}

		return transactionPreparedOrErrors;
	}

	@Override
	public Map<String, String> checkResultatEscaneig(String transactionID, HttpServletResponse response)
			throws Exception {
		Map<String, String> urlFitxersFirmatsOerrors = scanwebPlugin.checkResultatEscaneig(transactionID,
				FileSystemManager.getFilesPath());

		log.info("XYZ ZZZ urlFitxersFirmatsOerrors = " + urlFitxersFirmatsOerrors);
		if (transactionsStarted.containsKey(transactionID)) {
			transactionsStarted.put(transactionID, urlFitxersFirmatsOerrors);
			return urlFitxersFirmatsOerrors;
		} else {
			log.error("XYZ ZZZ transactionID not found: " + transactionID);
			throw new Exception("Transaction ID not found: " + transactionID);
		}
	}

	@Override
	public Fitxer guardaEscaneig(String transactionID, File file, long entitatId, long usuariId) throws I18NException {
		Fitxer fitxer = new FitxerJPA();
		fitxer.setNom(file.getName());
		fitxer.setDescripcio(null);
		fitxer.setMime("application/pdf");
		fitxer.setTamany(file.length());
		fitxer = fitxerLogicaEjb.create(fitxer);
		FileSystemManager.crearFitxer(file, fitxer.getFitxerID());

		ScanWebBean scanWebBean = new ScanWebBean();
		// TODO: ficar plugin a ejb
		// TODO:falta informació de fitxer firmat...
		scanWebBean.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		scanWebBean.setEntitatID(entitatId);
		// TODO:falta
		scanWebBean.setFileInfo(null);
		scanWebBean.setFitxerID(fitxer.getFitxerID());
		// TODO:falta
		scanWebBean.setMetadades(null);
		// TODO:falta
		scanWebBean.setMissatge(null);
		// TODO:falta
		scanWebBean.setStatus(0);
		// TODO:falta
		scanWebBean.setSignedFileInfo(null);
		scanWebBean.setTransactionID(transactionID);
		// TODO:falta (subtransactionID)
		scanWebBean.setTransactionWebID(transactionID);
		scanWebBean.setUsuariID(usuariId);

		return fitxer;
	}
}
