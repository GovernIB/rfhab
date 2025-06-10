package es.caib.rfhab.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;

import es.caib.rfhab.ejb.FitxerService;
import es.caib.rfhab.ejb.ScanWebEJB;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.pluginsib.digitalib.ScanWebSimplePlugin;

/**
 * 
 * @author jpou
 *
 */
@Stateless
public class ScanWebLogicaEJB extends ScanWebEJB implements ScanWebLogicaService {
	@EJB(mappedName = FitxerService.JNDI_NAME)
    protected FitxerService fitxerLogicaEjb;
	
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
	public Map<String, String> checkResultatEscaneig(String transactionID, HttpServletResponse response) throws Exception {
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
}
