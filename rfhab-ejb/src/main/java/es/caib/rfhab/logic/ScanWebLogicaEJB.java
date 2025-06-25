package es.caib.rfhab.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleFile;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleStatus;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSubtransactionResult;
import es.caib.rfhab.commons.utils.FileNameCleaner;
import es.caib.rfhab.ejb.ScanWebEJB;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.model.entity.ScanWeb;
import es.caib.rfhab.persistence.FitxerJPA;
import es.caib.rfhab.persistence.ScanWebJPA;
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

	private static Map<String, Map<String, MassiveScanWebSimpleSubtransactionResult>> transactionsStarted = new HashMap<>();

	@Override
	public Map<String, MassiveScanWebSimpleSubtransactionResult> checkFinalScanweb(String transactionID) {
		Map<String, MassiveScanWebSimpleSubtransactionResult> urlFitxersFirmatsOerrors = transactionsStarted
				.get(transactionID);

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
	public Map<String, MassiveScanWebSimpleSubtransactionResult> finalitzaEscaneig(String transactionID,
			HttpServletResponse response)
			throws Exception {
		Map<String, MassiveScanWebSimpleSubtransactionResult> fitxersFirmatsOerrors = scanwebPlugin.finalitzaEscaneig(
				transactionID);

		log.info("XYZ ZZZ urlFitxersFirmatsOerrors = " + fitxersFirmatsOerrors);
		if (transactionsStarted.containsKey(transactionID)) {
			transactionsStarted.put(transactionID, fitxersFirmatsOerrors);
			return fitxersFirmatsOerrors;
		} else {
			log.error("XYZ ZZZ transactionID not found: " + transactionID);
			throw new Exception("Transaction ID not found: " + transactionID);
		}
	}

	@Override
	public Fitxer guardaEscaneig(long transactionId, String transactionWebId, File file, String fileMime,
			String escaneigFileInfo, String escaneigMetadades, String escaneigMissatge, long escaneigStatus,
			String escaneigSignedFileInfo, long entitatId, long usuariId) throws I18NException {
		Fitxer fitxer = new FitxerJPA();
		fitxer.setNom(file.getName());
		fitxer.setDescripcio(null);
		fitxer.setMime(fileMime);// això sempre hauria de ser un mime tipus "application/pdf"
		fitxer.setTamany(file.length());
		fitxer = fitxerLogicaEjb.create(fitxer);
		FileSystemManager.crearFitxer(file, fitxer.getFitxerID());

		ScanWeb escaneig = new ScanWebJPA();
		escaneig.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		escaneig.setEntitatID(entitatId);
		escaneig.setFileInfo(escaneigFileInfo);
		escaneig.setFitxerID(fitxer.getFitxerID());
		escaneig.setMetadades(escaneigMetadades);
		escaneig.setMissatge(escaneigMissatge);
		escaneig.setStatus(escaneigStatus);
		escaneig.setSignedFileInfo(escaneigSignedFileInfo);
		escaneig.setTransactionID(transactionId);
		escaneig.setTransactionWebID(transactionWebId);
		escaneig.setUsuariID(usuariId);
		create(escaneig);

		return fitxer;
	}

	@Override
	public Object gestionaResultatScaneig(String subTransactionID,
			MassiveScanWebSimpleSubtransactionResult transactionResult, long entitatId, long usuariId)
			throws I18NException, IOException {
		Fitxer fitxer = null;

		MassiveScanWebSimpleStatus transactionStatus = transactionResult.getStatus();

		int status = transactionStatus.getStatus();

		if (ScanWebSimplePlugin.CONSTANTS.getMassiveScanWebSimpleStatusSTATUSREQUESTEDID().equals(status)) { // = 0;
			String msg = "Subtransacció: " + subTransactionID
					+ " -- S'ha rebut un estat inconsistent del procés"
					+ " (requestedid). Pot ser el PLugin no està ben desenvolupat."
					+ " Consulti amb el seu administrador.";
			log.info(msg);
			return msg;
		}
		if (ScanWebSimplePlugin.CONSTANTS.getMassiveScanWebSimpleStatusSTATUSINPROGRESS().equals(status)) { // =
																											// 1;
			String msg = "Subtransacció: " + subTransactionID
					+ " -- S'ha rebut un estat inconsistent del procés"
					+ " (En Progrés). Pot ser el PLugin no està ben desenvolupat."
					+ " Consulti amb el seu administrador.";
			log.info(msg);
			return msg;
		}
		if (ScanWebSimplePlugin.CONSTANTS.getMassiveScanWebSimpleStatusSTATUSFINALERROR().equals(status)) { // =
																											// -1;
			String msg = "Subtransacció: " + subTransactionID
					+ " -- Error durant la realització de l'escaneig/còpia autèntica: "
					+ transactionStatus.getErrorMessage();
			log.info(msg);
			String desc = transactionStatus.getErrorStackTrace();
			if (desc != null) {
				log.info(desc);
			}
			return msg;
		}
		if (ScanWebSimplePlugin.CONSTANTS.getMassiveScanWebSimpleStatusSTATUSCANCELLED().equals(status)) { // =
																											// -2;
			String msg = "Subtransacció: " + subTransactionID
					+ " -- Durant el procés, l'usuari ha cancelat la transacció.";
			log.info(msg);
			return msg;
		}

		if (ScanWebSimplePlugin.CONSTANTS.getMassiveScanWebSimpleStatusSTATUSFINALOK().equals(status)) { // = 2;
			File filesPath = new File(FileSystemManager.getFilesPath(), "scanWeb");
			filesPath.mkdirs();

			// Enregistrament de la transaccio amb digitalIB
			MassiveScanWebSimpleFile signedFile = transactionResult.getSignedFile();
			if (signedFile != null) {
				File signed = new File(filesPath,
						FileNameCleaner
								.cleanFileName(subTransactionID + "_signed." + signedFile.getNom()));
				// .cleanFileName(transactionID + "_" + (count - 1) + "_signed." +
				// signedFile.getNom()));

				FileOutputStream fos = new FileOutputStream(signed);
				fos.write(signedFile.getData());
				fos.flush();
				fos.close();

				String signedFileAbsPath = signed.getAbsolutePath();
				log.info("Firma del Fitxer Escanejat guardat a " + signedFileAbsPath);
				// TODO: fileinfo??
				// TODO: metadades??
				fitxer = guardaEscaneig(transactionResult.getTransactionID(), transactionResult.getTransactionWebID(),
						signed, signedFile.getMime(), null,
						transactionResult.getAdditionalMetadatas().stream().map(Object::toString)
								.collect(Collectors.joining(", ")),
						transactionStatus.getErrorMessage(), status, transactionResult.getSignedFileInfo().toString(),
						entitatId, usuariId);
			} else {
				MassiveScanWebSimpleFile scannedFile = transactionResult.getScannedFile();
				if (scannedFile != null) {

					String format = transactionResult.getScannedFileInfo().getFormatFile();
					if (format == null) {
						format = "unknown";
					} else {
						format = format.replace("/", ".");
					}

					File scanFile = new File(filesPath,
							FileNameCleaner.cleanFileName(subTransactionID + "_scanfile." + format));
					// FileNameCleaner.cleanFileName(transactionID + "_" + (count - 1) +
					// "_scanfile." + format));

					FileOutputStream fos = new FileOutputStream(scanFile);
					fos.write(scannedFile.getData());
					fos.flush();
					fos.close();

					String scannedFileAbsPath = scanFile.getAbsolutePath();
					log.info("Fitxer Escanejat guardat a " + scannedFileAbsPath);
					// TODO:fileinfo??
					// TODO: metadades??
					fitxer = guardaEscaneig(transactionResult.getTransactionID(),
							transactionResult.getTransactionWebID(),
							scanFile, scannedFile.getMime(), null,
							transactionResult.getAdditionalMetadatas().stream().map(Object::toString)
									.collect(Collectors.joining(", ")),
							transactionStatus.getErrorMessage(), status,
							transactionResult.getScannedFileInfo().toString(),
							entitatId, usuariId);
				} else {
					String msg = "Subtransacció: " + subTransactionID
							+ " -- No s'ha trobat el fitxer escanejat ni la firma.";
					log.info(msg);
					return msg;
				}
			}

			// MassiveScanWebSimpleFile detachedSignedFile =
			// result.getDetachedSignatureFile();
			// if (detachedSignedFile != null) {
			// File detached = new File(filesPath,
			// (count - 1) + "_detached_sign." + detachedSignedFile.getNom());

			// FileOutputStream fos = new FileOutputStream(detached);
			// fos.write(detachedSignedFile.getData());
			// fos.flush();
			// fos.close();

			// String detachedFileAbsPath = detached.getAbsolutePath();
			// log.info("Document Detached de la Firma (Document Escanejat) guardat a "
			// + detachedFileAbsPath);
			// filesOrErrorsResult.put(subTransactionID, detachedFileAbsPath);
			// }
		} // Final Case Firma OK

		return fitxer;
	}
}
