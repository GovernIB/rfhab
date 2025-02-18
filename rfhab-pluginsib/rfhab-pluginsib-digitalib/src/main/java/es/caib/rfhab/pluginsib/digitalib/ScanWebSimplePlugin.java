package es.caib.rfhab.pluginsib.digitalib;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.api.ApiMassiveScanWebSimpleApi;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleArxiuOptionalParameters;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleArxiuRequiredParameters;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleAvailableProfile;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleAvailableProfiles;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleConstants;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleFile;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleGetTransactionIdRequest;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSignatureParameters;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleStartTransactionRequest;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleStatus;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSubTransactionsOfTransaction;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSubtransactionResult;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSubtransactionResultRequest;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.services.ApiClient;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.services.Configuration;
import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.services.auth.HttpBasicAuth;
import es.caib.rfhab.commons.utils.Configuracio;

public class ScanWebSimplePlugin implements IScanWebSimplePlugin {

	protected static final String ENDPOINT = SCANWEB_PLUGIN_PROPERTY + "endpoint";
	protected static final String USUARI = SCANWEB_PLUGIN_PROPERTY + "usuari";
	protected static final String CLAU = SCANWEB_PLUGIN_PROPERTY + "contrasenya";

	protected static final String PROFILE = SCANWEB_PLUGIN_PROPERTY + "profile";

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private ApiMassiveScanWebSimpleApi api = null;

	public static final MassiveScanWebSimpleConstants CONSTANTS = new MassiveScanWebSimpleConstants();

	public void setApi(ApiMassiveScanWebSimpleApi api) {
		this.api = api;
	}

	public ApiMassiveScanWebSimpleApi getApi() {
		return api;
	}

	@Override
	public void escaneig(String usuari, String languageUI, String funcionariNom, String funcionariAdministracioID, String funcionariDir3,
			List<String> interessats, List<String> organs, String ciutadaNif, String ciutadaNom) throws Exception {

		String transactionID = null;

		try {

			ApiMassiveScanWebSimpleApi instancia = getApiConnection();

			MassiveScanWebSimpleAvailableProfiles scanWebProfileSelected = instancia.getAvailableProfiles(languageUI);

			if (scanWebProfileSelected == null) {
				LOG.info("NO HI HA PERFILS PER AQUEST USUARI APLICACIÓ");
				return;
			} else {
				for (MassiveScanWebSimpleAvailableProfile profile : scanWebProfileSelected.getAvailableProfiles()) {
					LOG.info("Profile: " + profile.getName() + " - " + profile.getDescription());
				}
			}

			LOG.info(" -----------------------------");

			// Recuperar un ID de transacció
			{

				MassiveScanWebSimpleAvailableProfile profileSelected = scanWebProfileSelected.getAvailableProfiles()
						.get(0);

				final String profileCode = profileSelected.getCode();

				final int view = CONSTANTS.getMassiveScanWebSimpleGetTransactionIdRequestVIEWIFRAME();
				// CONSTANTS.getMassiveScanWebSimpleGetTransactionIdRequestVIEWFULLSCREEN()

				final String transactionName = "Transaccio " + System.currentTimeMillis();

				MassiveScanWebSimpleGetTransactionIdRequest transacctionIdRequest;

				if (profileSelected.getProfileType()
						.equals(CONSTANTS.getMassiveScanWebSimpleAvailableProfilePROFILETYPEONLYSCAN())) {

					transacctionIdRequest = new MassiveScanWebSimpleGetTransactionIdRequest();
					transacctionIdRequest.setTransactionName(transactionName);
					transacctionIdRequest.setScanWebProfile(profileCode);
					transacctionIdRequest.setView(view);
					transacctionIdRequest.setLanguageUI(languageUI);
					transacctionIdRequest.setFuncionariUsername(usuari);

				} else if (profileSelected.getProfileType()
						.equals(CONSTANTS.getMassiveScanWebSimpleAvailableProfilePROFILETYPESCANANDSIGNATURE())) {

					MassiveScanWebSimpleSignatureParameters signatureParameters = new MassiveScanWebSimpleSignatureParameters();
					signatureParameters.setFunctionaryFullName(funcionariNom);
					signatureParameters.setFunctionaryAdministrationID(funcionariAdministracioID);
					signatureParameters.setFunctionayDIR3Unit(funcionariDir3);

					transacctionIdRequest = new MassiveScanWebSimpleGetTransactionIdRequest();
					transacctionIdRequest.setTransactionName(transactionName);
					transacctionIdRequest.setScanWebProfile(profileCode);
					transacctionIdRequest.setView(view);
					transacctionIdRequest.setLanguageUI(languageUI);
					transacctionIdRequest.setFuncionariUsername(usuari);
					transacctionIdRequest.setSignatureParameters(signatureParameters);

				} else if (profileSelected.getProfileType().equals(
						CONSTANTS.getMassiveScanWebSimpleAvailableProfilePROFILETYPESCANANDSIGNATUREANDCUSTODY())) {

					MassiveScanWebSimpleSignatureParameters signatureParameters = new MassiveScanWebSimpleSignatureParameters();
					signatureParameters.setFunctionaryFullName(funcionariNom);
					signatureParameters.setFunctionaryAdministrationID(funcionariAdministracioID);
					signatureParameters.setFunctionayDIR3Unit(funcionariDir3);

					MassiveScanWebSimpleArxiuRequiredParameters arxiuRequiredParameters = new MassiveScanWebSimpleArxiuRequiredParameters();

					final List<String> personesInteressades = new ArrayList<String>(interessats);

					/**
					 * CONSTANTS.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTORIGENCIUTADA()
					 * CONSTANTS.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTORIGENADMINISTRACIO()
					 */
					final int origen = CONSTANTS
							.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTORIGENADMINISTRACIO();

					/**
					 * @see CONSTANTS.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTELABORATIONSTATEORIGINAL()
					 * @see CONSTANTS.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTELABORATIONSTATECOPIACF()
					 * @see CONSTANTS.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTELABORATIONSTATECOPIADP()
					 * @see CONSTANTS.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTELABORATIONSTATECOPIAPR()
					 * @see CONSTANTS.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTELABORATIONSTATEALTRES()
					 */
					final String documentEstatElaboracio = CONSTANTS
							.getMassiveScanWebSimpleArxiuRequiredParametersDOCUMENTELABORATIONSTATEORIGINAL();

					List<String> organsAfectats = new ArrayList<String>(organs);

					arxiuRequiredParameters.setCitizenAdministrationID(ciutadaNif);
					arxiuRequiredParameters.setCitizenFullName(ciutadaNom);
					arxiuRequiredParameters.setDocumentElaborationState(documentEstatElaboracio);
					arxiuRequiredParameters.setDocumentOrigen(origen);
					arxiuRequiredParameters.setInterestedPersons(personesInteressades);
					arxiuRequiredParameters.setAffectedOrganisms(organsAfectats);

					MassiveScanWebSimpleArxiuOptionalParameters arxiuOptionalParameters = null;

					transacctionIdRequest = new MassiveScanWebSimpleGetTransactionIdRequest();
					transacctionIdRequest.setTransactionName(transactionName);
					transacctionIdRequest.setScanWebProfile(profileCode);
					transacctionIdRequest.setView(view);
					transacctionIdRequest.setLanguageUI(languageUI);
					transacctionIdRequest.setFuncionariUsername(usuari);
					transacctionIdRequest.setSignatureParameters(signatureParameters);
					transacctionIdRequest.setArxiuRequiredParameters(arxiuRequiredParameters);
					transacctionIdRequest.setArxiuOptionalParameters(arxiuOptionalParameters);

				} else {
					throw new Exception("Tipus de perfil desconegut " + profileSelected.getProfileType());

				}

				// Enviam la part comu de la transacció
				transactionID = api.getTransactionID(transacctionIdRequest);
				LOG.info("languageUI = |" + languageUI + "|");
				LOG.info("TransactionID = |" + transactionID + "|");
			}

			// Servidor TEMPORAL
			String host = Inet4Address.getLocalHost().getHostAddress();
			// final int port = 1989;

			Random r = new Random();
			int low = 1900;
			int high = 2000;
			final int port = r.nextInt(high - low) + low;

			final String returnUrl = "http://" + host + ":" + port + "/returnurl/" + transactionID;
			LOG.info("ReturnURL =" + returnUrl);

			MassiveScanWebSimpleStartTransactionRequest startTransactionInfo;
			startTransactionInfo = new MassiveScanWebSimpleStartTransactionRequest();

			startTransactionInfo.setReturnUrl(returnUrl);
			startTransactionInfo.setTransactionID(transactionID);

			String redirectUrl = api.startTransaction(startTransactionInfo);
			LOG.info("RedirectUrl = " + redirectUrl);

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(redirectUrl));
			} else {
				LOG.info("Per favor obri un Navegador i copia-li la URL anterior ...");
			}

			readFromSocket(port);

			LOG.info(" Cridant a getSubTransactionsOfTransaction(" + transactionID + ") ...");
			MassiveScanWebSimpleSubTransactionsOfTransaction subs = api.getSubTransactionsOfTransaction(transactionID);

			List<String> subtransacions = subs.getSubtransacions();
			int count = 1;
			for (String subTransactionID : subtransacions) {

				// Segons el tipus de perfil ja ho omplirà automàticament
				Boolean returnScannedFile = null;
				Boolean returnSignedFile = null;

				LOG.info("-------------=====  SUBTRANSACCIO(" + count + ") [" + subTransactionID
						+ "] =====--------------");
				count++;
				LOG.info(" Descarregant Informació dels Resultat:");
				LOG.info("     * TransaccioID = " + transactionID);
				LOG.info("     * SubransaccioID = " + subTransactionID);
				LOG.info("     * returnScannedFile = " + returnScannedFile);
				LOG.info("     * returnSignedFile = " + returnSignedFile);

				MassiveScanWebSimpleSubtransactionResultRequest resultRequest;
				resultRequest = new MassiveScanWebSimpleSubtransactionResultRequest();
				resultRequest.setSubtransactionID(subTransactionID);
				resultRequest.setReturnScannedFile(returnScannedFile);
				resultRequest.setReturnSignedFile(returnSignedFile);

				MassiveScanWebSimpleSubtransactionResult result = api.getSubTransactionResult(resultRequest);

				MassiveScanWebSimpleStatus transactionStatus = result.getStatus();

				int status = transactionStatus.getStatus();

				LOG.info(result.toString());

				if (CONSTANTS.getMassiveScanWebSimpleStatusSTATUSREQUESTEDID().equals(status)) { // = 0;
					LOG.info("S'ha rebut un estat inconsistent del procés"
							+ " (requestedid). Pot ser el PLugin no està ben desenvolupat."
							+ " Consulti amb el seu administrador.");

				} else if (CONSTANTS.getMassiveScanWebSimpleStatusSTATUSINPROGRESS().equals(status)) { // = 1;
					LOG.info("S'ha rebut un estat inconsistent del procés"
							+ " (En Progrés). Pot ser el PLugin no està ben desenvolupat."
							+ " Consulti amb el seu administrador.");

				} else if (CONSTANTS.getMassiveScanWebSimpleStatusSTATUSFINALERROR().equals(status)) { // = -1;

					LOG.info("Error durant la realització de l'escaneig/còpia autèntica: "
							+ transactionStatus.getErrorMessage());
					String desc = transactionStatus.getErrorStackTrace();
					if (desc != null) {
						LOG.info(desc);
					}
					continue;

				} else if (CONSTANTS.getMassiveScanWebSimpleStatusSTATUSCANCELLED().equals(status)) { // = -2;
					LOG.info("Durant el procés, l'usuari ha cancelat la transacció.");
					continue;

				} else

				if (CONSTANTS.getMassiveScanWebSimpleStatusSTATUSFINALOK().equals(status)) { // = 2;
					{

						// Enregistrament de la transaccio amb digitalIB
						
						result.getStatus().getStatus()
						if (result.getScannedFile() != null) {

							String format = result.getScannedFileInfo().getFormatFile();
							if (format == null) {
								format = "unknown";
							} else {
								format = format.replace("/", ".");
							}

							File scanFile = new File((count - 1) + "_scanfile." + format);

							FileOutputStream fos = new FileOutputStream(scanFile);
							fos.write(result.getScannedFile().getData());
							fos.flush();
							fos.close();

							LOG.info("Fitxer Escanejat guardat a " + scanFile.getAbsolutePath());
						}

						MassiveScanWebSimpleFile signedFile = result.getSignedFile();

						if (signedFile != null) {
							File signed = new File((count - 1) + "_signed." + signedFile.getNom());

							FileOutputStream fos = new FileOutputStream(signed);
							fos.write(signedFile.getData());
							fos.flush();
							fos.close();

							LOG.info("Firma del Fitxer Escanejat guardat a " + signed.getAbsolutePath());
						}

						MassiveScanWebSimpleFile detachedSignedFile = result.getDetachedSignatureFile();

						if (detachedSignedFile != null) {
							File detached = new File((count - 1) + "_detached_sign." + detachedSignedFile.getNom());

							FileOutputStream fos = new FileOutputStream(detached);
							fos.write(detachedSignedFile.getData());
							fos.flush();
							fos.close();

							LOG.info("Document Detached de la Firma (Document Escanejat) guardat a "
									+ detached.getAbsolutePath());
						}

					} // Final Case Firma OK
				}
			} // final for

		} catch (Exception e) {
			LOG.error("Error en la connexió amb ScanWeb", e);
			throw e;
		} finally {
			if (api != null && transactionID != null) {
				try {
					api.closeTransaction(transactionID);
				} catch (Throwable th) {
					th.printStackTrace();
				}
			}
		}

	}

	public static void readFromSocket(int port) throws Exception {

		ServerSocket serverSocket = new ServerSocket(port);
		System.err.println("Servidor escoltant al PORT: " + port);
		{
			Socket clientSocket = serverSocket.accept();
			System.err.println("Nou Client Connectat desde " + clientSocket.getRemoteSocketAddress());

			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

			String s;
			System.err.println(" =========================== ");
			while ((s = in.readLine()) != null) {
				System.err.println(s);
				break;
			}
			System.err.println(" =========================== ");

			out.println("HTTP/1.0 200 OK");
			out.println("Content-Type: text/html");
			out.println("\r\n");
			out.println("<html><body>OK (Revisi consola per saber l'estat final del proc&eacute;s)</body></html>");

			System.err.println("Connexio amb el client finalitzada.");
			out.flush();
			out.close();
			in.close();
			clientSocket.close();
		}

		serverSocket.close();

	}

	private ApiMassiveScanWebSimpleApi getApiConnection() throws Exception {

		ApiMassiveScanWebSimpleApi instancia = getApi();

		if (instancia == null) {

			final String host = Configuracio.getAppSystemProperty(ENDPOINT);
			final String usuari = Configuracio.getAppSystemProperty(USUARI);
			final String clau = Configuracio.getAppSystemProperty(CLAU);

			ApiClient client = Configuration.getDefaultApiClient();
			client.setBasePath(host);

			HttpBasicAuth basicAuth = (HttpBasicAuth) client.getAuthentication("BasicAuth");
			basicAuth.setUsername(usuari);
			basicAuth.setPassword(clau);

			ApiMassiveScanWebSimpleApi api = new ApiMassiveScanWebSimpleApi(client);
			setApi(api);
		}

		return getApi();

	}

}
