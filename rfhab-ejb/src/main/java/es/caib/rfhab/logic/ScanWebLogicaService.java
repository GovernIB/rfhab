package es.caib.rfhab.logic;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpServletResponse;

import es.caib.rfhab.ejb.ScanWebService;

/**
 * 
 * @author jpou
 *
 */
@Local
public interface ScanWebLogicaService extends ScanWebService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/ScanWebLogicaEJB!es.caib.rfhab.logic.ScanWebLogicaService";

	public List<String> checkFinalScanweb(String transactionID);

	public List<String> checkResultatEscaneig(String transactionID, HttpServletResponse response) throws Exception;

	public HashMap<String, String> escaneig(String firstPartReturnUrl, String username, String languageUI,
			String funcionariNom,
			String funcionariAdministracioID, String funcionariDir3, List<String> interessatsList, List<String> organs,
			String ciutadaNif, String ciutadaNom) throws Exception;

}
