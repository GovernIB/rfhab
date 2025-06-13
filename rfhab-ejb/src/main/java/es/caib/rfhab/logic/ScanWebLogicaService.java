package es.caib.rfhab.logic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSubtransactionResult;
import es.caib.rfhab.ejb.ScanWebService;
import es.caib.rfhab.model.entity.Fitxer;

/**
 * 
 * @author jpou
 *
 */
@Local
public interface ScanWebLogicaService extends ScanWebService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/ScanWebLogicaEJB!es.caib.rfhab.logic.ScanWebLogicaService";

	public Map<String, MassiveScanWebSimpleSubtransactionResult> checkFinalScanweb(String transactionID);

	public Map<String, MassiveScanWebSimpleSubtransactionResult> finalitzaEscaneig(String transactionID,
			HttpServletResponse response)
			throws Exception;

	public HashMap<String, String> escaneig(String firstPartReturnUrl, String username, String languageUI,
			String funcionariNom,
			String funcionariAdministracioID, String funcionariDir3, List<String> interessatsList, List<String> organs,
			String ciutadaNif, String ciutadaNom) throws Exception;

	public Object gestionaResultatScaneig(String subTransactionID,
			MassiveScanWebSimpleSubtransactionResult transactionResult, long entitatId, long usuariId)
			throws I18NException, IOException;

	public Fitxer guardaEscaneig(long transactionId, String transactionWebId, File file, String fileMime,
			String escaneigFileInfo, String escaneigMetadades, String escaneigMissatge, long escaneigStatus,
			String escaneigSignedFileInfo, long entitatId, long usuariId) throws I18NException;
}
