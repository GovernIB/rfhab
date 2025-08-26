package es.caib.rfhab.back.controller.user;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.digitalib.api.interna.client.apimassivescanwebsimple.v1.model.MassiveScanWebSimpleSubtransactionResult;
import es.caib.rfhab.back.controller.FileDownloadController;
import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.dto.ScanWebResult;
import es.caib.rfhab.back.form.webdb.UsuariFilterForm;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.commons.utils.OdtToPdfService;
import es.caib.rfhab.logic.ActivitatLogicaService;
import es.caib.rfhab.logic.EntitatLogicaService;
import es.caib.rfhab.logic.FitxerPublicLogicaService;
import es.caib.rfhab.logic.ScanWebLogicaService;
import es.caib.rfhab.logic.SistramitLogicaService;
import es.caib.rfhab.logic.UnitatLogicaUserService;
import es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits.PlantillaOdtModelConsentiment;
import es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits.TramitConsentimentDAO;
import es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits.TramitConsentimentDTO;
import es.caib.rfhab.logic.utils.TicketAccesDto.RpersonaInfo;
import es.caib.rfhab.model.entity.Entitat;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.model.entity.Usuari;
import es.caib.rfhab.model.fields.IdiomaFields;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.UsuariJPA;
import es.caib.rfhab.pluginsib.rolsac.RolsacPlugin;

/**
 * 
 * @author jagarcia
 * @author jpou
 *
 */
@Controller
@RequestMapping(value = UserController.CONTEXTWEB)
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UserController extends UsuariController {

	public static final String CONTEXTWEB = "/usuari/";

	private static final String CARPETA_PLANTILLES = "/rfhab_plantilles/";
	private static final String PLANTILLA_PROVES_CAT_ODT = UserController.CARPETA_PLANTILLES
			+ "model_consentiment_def_i_protec_dades.odt";
	private static final String PLANTILLA_PROVES_CAST_ODT = UserController.CARPETA_PLANTILLES
			+ "model_consentiment_def_i_protec_dades_cast.odt";

	@EJB(mappedName = SistramitLogicaService.JNDI_NAME)
	protected SistramitLogicaService sistramitLogicaEjb;

	@EJB(mappedName = ActivitatLogicaService.JNDI_NAME)
	protected ActivitatLogicaService activitatLogicaEjb;

	@EJB(mappedName = ScanWebLogicaService.JNDI_NAME)
	protected ScanWebLogicaService scanWebLogicaEjb;

	@EJB(mappedName = UnitatLogicaUserService.JNDI_NAME)
	protected UnitatLogicaUserService unitatLogicaEjb;

	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicaEjb;

	private RolsacPlugin rolsacPlugin;

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerLogicaEjb;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info("XYZ ZZZ ENTRANT A HOME");

		LoginInfo loginInfo = LoginInfo.getInstance();
		String language = loginInfo.getLanguage();
		rolsacPlugin = new RolsacPlugin();
		HashMap<String, String[]> llistaProcediments = rolsacPlugin.obtenirProcedimentsAll(language);

		// HashMap<String, String> llistaTramits =
		// rolsacPlugin.obtenirTramits("1533169");

		ModelAndView mav = new ModelAndView("homeUsuari");

		if (llistaProcediments == null) {
			llistaProcediments = new HashMap<String, String[]>();
		}
		mav.addObject("llistaProcediments", llistaProcediments);

		return mav;
	}

	@RequestMapping(value = "/guardararxiu", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, String> guardarArxiu(
			@RequestParam(value = "encryptedIdFitxer", required = true) String encryptedIdFitxer,
			@RequestParam(value = "perfilfirma", required = false) String perfilfirma,
			@RequestParam(value = "tipusFirma", required = false) String tipusFirma,
			@RequestParam(value = "interessats", required = false) String interessats,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("ENTRANT A guardararxiu");

		log.info("guardararxiu -- encryptedIdFitxer = " + encryptedIdFitxer);
		log.info("guardararxiu -- perfilfirma = " + perfilfirma);
		log.info("guardararxiu -- tipusFirma = " + tipusFirma);
		log.info("guardararxiu -- interessats = " + interessats);
		List<String> interessatsList = Arrays.asList(interessats.split("--"));
		log.info("guardararxiu -- interessatsList = " + interessatsList);

		LoginInfo loginInfo = LoginInfo.getInstance();
		Entitat entitat = entitatLogicaEjb.findByPrimaryKey(loginInfo.getEntitatID());
		Unitat unitat = unitatLogicaEjb.findByPrimaryKey(entitat.getUnitatID());
		List<String> organs = Arrays.asList(unitat.getCodi());
		log.info("guardararxiu -- organs = " + organs);

		final long fitxerId = FileDownloadController.recuperaFitxerId(encryptedIdFitxer);
		log.info("guardararxiu -- fitxerId = " + fitxerId);
		Fitxer fitxer = fitxerLogicaEjb.findByPrimaryKey(fitxerId);
		if (fitxer == null) {
			log.error("guardararxiu -- Fitxer amb id " + fitxerId + " no trobat");
			throw new I18NException("error.arxiu.fitxernotfound", Long.toString(fitxerId));
		}
		HashMap<String, String> identificadors = activitatLogicaEjb.guardarArxiu(fitxer, fitxer.getNom(),
				perfilfirma, tipusFirma, interessatsList, organs);
		return identificadors;
	}

	@RequestMapping(value = "/documentimprimible", method = RequestMethod.GET)
	@ResponseBody
	public String documentImprimible(
			@RequestParam(value = "identificadorDocument", required = true) String identificadorDocument,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("ENTRANT A documentimprimible");
		log.info("documentimprimible -- identificadorDocument = " + identificadorDocument);
		Fitxer documentFitxer = activitatLogicaEjb.documentImprimible(identificadorDocument);
		return FileDownloadController.fileUrl(documentFitxer);
	}

	@RequestMapping(value = "/obtenirtramits/{identificadorProcediment}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, String[]> obtenirTramits(
			@PathVariable(value = "identificadorProcediment", required = true) String identificadorProcediment,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("ENTRANT A obtenirtramit");
		log.info("obtenirtramit -- identificadorProcediment = " + identificadorProcediment);
		LoginInfo loginInfo = LoginInfo.getInstance();
		String language = loginInfo.getLanguage();
		HashMap<String, String[]> llistaTramits = rolsacPlugin.obtenirTramits(identificadorProcediment, language);
		return llistaTramits;
	}

	// TODO:convertir això a asincron i fer tasca nocturna, amb gestió de
	// reintents...
	@RequestMapping(value = "/tancarexpedient", method = RequestMethod.GET)
	@ResponseBody
	public void tancarExpedient(
			@RequestParam(value = "identificadorExpedient", required = true) String identificadorExpedient,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("ENTRANT A tancarexpedient");
		log.info("tancarexpedient -- identificadorExpedient = " + identificadorExpedient);
		activitatLogicaEjb.tancarExpedient(identificadorExpedient);
	}

	@RequestMapping(value = "/ticketAccesFh", method = RequestMethod.GET)
	@ResponseBody
	public String ticketAccesFh(
			@RequestParam(value = "languageUI", required = false) String languageUI,
			@RequestParam(value = "interessats", required = false) String interessats,
			@RequestParam(value = "ciutadaTipusIdentificacio", required = false) String ciutadaTipusIdentificacio,
			@RequestParam(value = "ciutadaNif", required = false) String ciutadaNif,
			@RequestParam(value = "ciutadaNom", required = false) String ciutadaNom,
			@RequestParam(value = "ciutadaLlinatge1", required = false) String ciutadaLlinatge1,
			@RequestParam(value = "ciutadaLlinatge2", required = false) String ciutadaLlinatge2,
			@RequestParam(value = "representant", required = false) Boolean representant,
			@RequestParam(value = "representantNom", required = false) String representantNom,
			@RequestParam(value = "representantLlinatge1", required = false) String representantLlinatge1,
			@RequestParam(value = "representantLlinatge2", required = false) String representantLlinatge2,
			@RequestParam(value = "representantTipusIdentificacio", required = false) String representantTipusIdentificacio,
			@RequestParam(value = "representantIdentificacio", required = false) String representantIdentificacio,
			@RequestParam(value = "procediment", required = false) String procediment,
			@RequestParam(value = "tramitCodi", required = true) String tramitCodi,
			@RequestParam(value = "tramitVersio", required = true) String tramitVersio,
			@RequestParam(value = "tramitParametres", required = false) String tramitParametres,
			@RequestParam(value = "idTraTel", required = false) String idTraTel,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("XYZ ZZZ ENTRANT A ticketAccesFh");

		log.info("XYZ YYY languageUI = " + languageUI);
		log.info("XYZ YYY interessats = " + interessats);
		List<String> interessatsList = Arrays.asList(interessats.split("--"));
		log.info("XYZ YYY interessatsList = " + interessatsList);
		log.info("XYZ YYY ciutadaTipusIdentificacio = " + ciutadaTipusIdentificacio);
		log.info("XYZ YYY ciutadaNif = " + ciutadaNif);
		log.info("XYZ YYY ciutadaNom = " + ciutadaNom);
		log.info("XYZ YYY ciutadaLlinatge1 = " + ciutadaLlinatge1);
		log.info("XYZ YYY ciutadaLlinatge2 = " + ciutadaLlinatge2);
		log.info("XYZ YYY representant = " + representant);
		log.info("XYZ YYY representantNom = " + representantNom);
		log.info("XYZ YYY representantLlinatge1 = " + representantLlinatge1);
		log.info("XYZ YYY representantLlinatge2 = " + representantLlinatge2);
		log.info("XYZ YYY representantTipusIdentificacio = " + representantTipusIdentificacio);
		log.info("XYZ YYY representantIdentificacio = " + representantIdentificacio);
		log.info("XYZ YYY procediment = " + procediment);
		log.info("XYZ YYY tramitCodi = " + tramitCodi);
		log.info("XYZ YYY tramitVersio = " + tramitVersio);
		log.info("XYZ YYY tramitParametres = " + tramitParametres);
		log.info("XYZ YYY idTraTel = " + idTraTel);

		LoginInfo loginInfo = LoginInfo.getInstance();
		Usuari usuari = loginInfo.getUsuariPersona();
		String username = usuari.getUsername();
		String funcionariAdministracioID = usuari.getNif();
		String funcionariNom = (usuari.getNom() != null ? usuari.getNom() : "") + " "
				+ (usuari.getLlinatge1() != null ? usuari.getLlinatge1() : "") + " "
				+ (usuari.getLlinatge2() != null ? usuari.getLlinatge2() : "");
		String funcionariDir3 = getCodiDIR3(request, username);// codiDIR3 del lloc de feina del funcionari
		Entitat entitat = entitatLogicaEjb.findByPrimaryKey(loginInfo.getEntitatID());
		Unitat unitat = unitatLogicaEjb.findByPrimaryKey(entitat.getUnitatID());

		log.info("XYZ YYY username = " + username);
		log.info("XYZ YYY funcionariAdministracioID = " + funcionariAdministracioID);
		log.info("XYZ YYY funcionariNom = " + funcionariNom);
		log.info("XYZ YYY funcionariDir3 = " + funcionariDir3);

		String urlTramit = null;
		try {
			Funcionari funcionari = new FuncionariJPA();
			funcionari.setCorreu(usuari.getCorreu());
			funcionari.setEntitatID(entitat.getEntitatID());
			funcionari.setIdentificador(funcionariAdministracioID);
			funcionari.setLlinatge1(usuari.getLlinatge1());
			funcionari.setLlinatge2(usuari.getLlinatge2());
			funcionari.setNom(funcionariNom);
			funcionari.setTipusIdentificador(1);
			funcionari.setUsuari(username);

			RpersonaInfo interessatTramit = new RpersonaInfo(ciutadaLlinatge1, ciutadaLlinatge2, ciutadaNif,
					ciutadaNom);
			RpersonaInfo representantTramit = null;
			if (representant != null && representant) {
				representantTramit = new RpersonaInfo(representantLlinatge1, representantLlinatge2, representantNom,
						representantIdentificacio);
			}

			// procediment i tramit
			List<String> campsBuits = new java.util.ArrayList<>();
			if (tramitCodi == null || "".equals(tramitCodi)) {
				campsBuits.add("tramitCodi");
			}
			if (tramitVersio == null || "".equals(tramitVersio)) {
				campsBuits.add("tramitVersio");
			}
			if (idTraTel == null || "".equals(idTraTel)) {
				campsBuits.add("idTraTel");
			}
			if (campsBuits.size() > 0) {
				String missatge = "Error al recuperar el ticket d'accés, els següents camps estan buits i són necessaris: ";
				for (String camp : campsBuits) {
					missatge += camp + ", ";
				}
				missatge = "ERROR: " + missatge.substring(0, missatge.length() - 2);
				log.error(missatge);
				return missatge;
			}
			urlTramit = sistramitLogicaEjb.getTicketAccesoFh(funcionari, funcionariDir3, interessatTramit,
					representantTramit,
					tramitCodi, languageUI, tramitParametres, false, idTraTel, Integer.valueOf(tramitVersio));
		} catch (Exception e) {
			log.error("Error retrieving ticket access. Message: " + e.getMessage());
			log.error("Error retrieving ticket access. LocalizedMessage: " + e.getLocalizedMessage());
			throw e;
		}
		log.info("Ticket access URL: " + urlTramit);
		return urlTramit;
	}

	@RequestMapping(value = "/preparescanweb", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, String> prepareScanweb(
			@RequestParam(value = "languageUI", required = false) String languageUI,
			@RequestParam(value = "interessats", required = false) String interessats,
			@RequestParam(value = "ciutadaTipusIdentificacio", required = false) String ciutadaTipusIdentificacio,
			@RequestParam(value = "ciutadaNif", required = false) String ciutadaNif,
			@RequestParam(value = "ciutadaNom", required = false) String ciutadaNom,
			@RequestParam(value = "ciutadaLlinatge1", required = false) String ciutadaLlinatge1,
			@RequestParam(value = "ciutadaLlinatge2", required = false) String ciutadaLlinatge2,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("XYZ ZZZ ENTRANT A PREPARESCANWEB");

		log.info("XYZ YYY languageUI = " + languageUI);
		log.info("XYZ YYY interessats = " + interessats);
		List<String> interessatsList = Arrays.asList(interessats.split("--"));
		log.info("XYZ YYY interessatsList = " + interessatsList);
		log.info("XYZ YYY ciutadaTipusIdentificacio = " + ciutadaTipusIdentificacio);
		log.info("XYZ YYY ciutadaNif = " + ciutadaNif);
		log.info("XYZ YYY ciutadaNom = " + ciutadaNom);

		LoginInfo loginInfo = LoginInfo.getInstance();
		Usuari usuari = loginInfo.getUsuariPersona();
		String username = usuari.getUsername();
		String funcionariAdministracioID = usuari.getNif();
		String funcionariNom = (usuari.getNom() != null ? usuari.getNom() : "") + " "
				+ (usuari.getLlinatge1() != null ? usuari.getLlinatge1() : "") + " "
				+ (usuari.getLlinatge2() != null ? usuari.getLlinatge2() : "");
		String funcionariDir3 = getCodiDIR3(request, username);// codiDIR3 del lloc de feina del funcionari
		Entitat entitat = entitatLogicaEjb.findByPrimaryKey(loginInfo.getEntitatID());
		Unitat unitat = unitatLogicaEjb.findByPrimaryKey(entitat.getUnitatID());
		List<String> organs = Arrays.asList(unitat.getCodi());

		log.info("XYZ YYY username = " + username);
		log.info("XYZ YYY funcionariAdministracioID = " + funcionariAdministracioID);
		log.info("XYZ YYY funcionariNom = " + funcionariNom);
		log.info("XYZ YYY funcionariDir3 = " + funcionariDir3);
		log.info("XYZ YYY organs = " + organs);
		// scanwebPlugin.escaneig("u00666", "ca", "Funcionari DeProfessio", "12345678X",
		// "1254123412",
		// Arrays.asList("43153858Q"), Arrays.asList("A04013511"), "11223344C", "Pep
		// Gonella");
		final String absoluteControllerBase = getAbsoluteControllerBase(request, CONTEXTWEB);
		final String firstPartReturnUrl = absoluteControllerBase + "scanweb/";

		log.info("XYZ YYY firstPartReturnUrl = " + firstPartReturnUrl);
		HashMap<String, String> transactionPreparedOrErrors = scanWebLogicaEjb.escaneig(firstPartReturnUrl,
				username, languageUI, funcionariNom, funcionariAdministracioID, funcionariDir3, interessatsList, organs,
				ciutadaNif, ciutadaNom);

		return transactionPreparedOrErrors;
	}

	@RequestMapping(value = "/modelConsentiment", method = RequestMethod.POST)
	@ResponseBody
	public void modelConsentiment(@RequestBody TramitConsentimentDTO tramitConsentimentDto,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("XYZ ZZZ ENTRANT A modelConsentiment");

		log.info("XYZ YYY languageUI = " + tramitConsentimentDto.languageUI);
		log.info("XYZ YYY interessats = " + tramitConsentimentDto.interessats);
		List<String> interessatsList = Arrays.asList(tramitConsentimentDto.interessats.split("--"));
		log.info("XYZ YYY interessatsList = " + interessatsList);
		log.info("XYZ YYY ciutadaTipusIdentificacio = " + tramitConsentimentDto.ciutadaTipusIdentificacio);
		log.info("XYZ YYY ciutadaNif = " + tramitConsentimentDto.ciutadaNif);
		log.info("XYZ YYY ciutadaNom = " + tramitConsentimentDto.ciutadaNom);
		log.info("XYZ YYY ciutadaLlinatge1 = " + tramitConsentimentDto.ciutadaLlinatge1);
		log.info("XYZ YYY ciutadaLlinatge2 = " + tramitConsentimentDto.ciutadaLlinatge2);
		log.info("XYZ YYY representant = " + tramitConsentimentDto.representant);
		log.info("XYZ YYY representantNom = " + tramitConsentimentDto.representantNom);
		log.info("XYZ YYY representantLlinatge1 = " + tramitConsentimentDto.representantLlinatge1);
		log.info("XYZ YYY representantLlinatge2 = " + tramitConsentimentDto.representantLlinatge2);
		log.info("XYZ YYY representantTipusIdentificacio = " + tramitConsentimentDto.representantTipusIdentificacio);
		log.info("XYZ YYY representantIdentificacio = " + tramitConsentimentDto.representantIdentificacio);
		log.info("XYZ YYY procediment = " + tramitConsentimentDto.procediment);
		log.info("XYZ YYY procedimentNom = " + tramitConsentimentDto.procedimentNom);
		log.info("XYZ YYY procedimentCodiSia = " + tramitConsentimentDto.procedimentCodiSia);
		log.info("XYZ YYY tramitNom = " + tramitConsentimentDto.tramitNom);
		log.info("XYZ YYY tramitCodi = " + tramitConsentimentDto.tramitCodi);
		log.info("XYZ YYY tramitVersio = " + tramitConsentimentDto.tramitVersio);
		log.info("XYZ YYY tramitParametres = " + tramitConsentimentDto.tramitParametres);
		log.info("XYZ YYY idTraTel = " + tramitConsentimentDto.idTraTel);

		LoginInfo loginInfo = LoginInfo.getInstance();
		Usuari usuari = loginInfo.getUsuariPersona();
		String username = usuari.getUsername();
		String funcionariAdministracioID = usuari.getNif();
		String funcionariNom = (usuari.getNom() != null ? usuari.getNom() : "") + " "
				+ (usuari.getLlinatge1() != null ? usuari.getLlinatge1() : "") + " "
				+ (usuari.getLlinatge2() != null ? usuari.getLlinatge2() : "");
		String funcionariDir3 = getCodiDIR3(request, username);// codiDIR3 del lloc de feina del funcionari

		log.info("XYZ YYY username = " + username);
		log.info("XYZ YYY funcionariAdministracioID = " + funcionariAdministracioID);
		log.info("XYZ YYY funcionariNom = " + funcionariNom);
		log.info("XYZ YYY funcionariDir3 = " + funcionariDir3);

		TramitConsentimentDAO tramitConsentimentDAO = new TramitConsentimentDAO(tramitConsentimentDto.ciutadaNom,
				tramitConsentimentDto.ciutadaLlinatge1,
				tramitConsentimentDto.ciutadaLlinatge2, tramitConsentimentDto.ciutadaNif,
				tramitConsentimentDto.representant ? tramitConsentimentDto.representantNom : null,
				tramitConsentimentDto.representant ? tramitConsentimentDto.representantLlinatge1 : null,
				tramitConsentimentDto.representant ? tramitConsentimentDto.representantLlinatge2 : null,
				tramitConsentimentDto.representant ? tramitConsentimentDto.representantIdentificacio : null,
				usuari.getNom(), usuari.getLlinatge1(),
				usuari.getLlinatge2(), funcionariDir3,
				tramitConsentimentDto.procedimentNom, tramitConsentimentDto.procedimentCodiSia,
				tramitConsentimentDto.tramitNom, tramitConsentimentDto.tramitCodi, new java.util.Date());
		Map<String, Object> freemarkerDadesMap = PlantillaOdtModelConsentiment
				.buildFreemarkerContext(tramitConsentimentDAO);
		String plantillaModelConsentimentPath = tramitConsentimentDto.languageUI.startsWith("es")
				? UserController.PLANTILLA_PROVES_CAST_ODT
				: UserController.PLANTILLA_PROVES_CAT_ODT;
		log.info("Plantilla model consentiment: " + plantillaModelConsentimentPath);
		try (InputStream templateFile = getClass().getResourceAsStream(plantillaModelConsentimentPath)) {
			getResource(plantillaModelConsentimentPath);
			byte[] pdf = OdtToPdfService.generatePdf(freemarkerDadesMap, templateFile);
			log.info("PDF generat correctament!");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=\"document.pdf\"");
			response.getOutputStream().write(pdf);
		}
	}

	@RequestMapping(value = "/checkfinalscanweb/", method = RequestMethod.GET)
	@ResponseBody
	public List<ScanWebResult> checkFinalScanweb(
			@RequestParam(value = "transactionID", required = true) String transactionID,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("checkFinalScanweb: " + transactionID);

		Map<String, MassiveScanWebSimpleSubtransactionResult> fitxersFirmatsOerrors = scanWebLogicaEjb
				.checkFinalScanweb(transactionID);// a
		// urlFitxersFirmatsOerrors
		// tenc
		// subtransaccions

		if (fitxersFirmatsOerrors == null) {
			// transacció en curs
			log.info("checkFinalScanweb: transacció en curs");
			return null;
		}

		// HtmlUtils.saveMessageError només és útil si retornam un ModelAndView
		List<ScanWebResult> urlErrorsOFitxers = new java.util.ArrayList<ScanWebResult>();
		for (Entry<String, MassiveScanWebSimpleSubtransactionResult> entry : fitxersFirmatsOerrors.entrySet()) {
			String transactionId = entry.getKey();
			MassiveScanWebSimpleSubtransactionResult result = entry.getValue();
			Object escaneigResultat = scanWebLogicaEjb.gestionaResultatScaneig(transactionId,
					result, LoginInfo.getInstance().getEntitatID(), LoginInfo.getInstance().getUsuariPersona()
							.getUsuariID());
			if (escaneigResultat instanceof Fitxer) {
				Fitxer fitxer = (Fitxer) escaneigResultat;
				String urlFitxer = FileDownloadController.fileUrl(fitxer);
				log.info("Fitxer escanejat: " + urlFitxer);
				urlErrorsOFitxers.add(new ScanWebResult("", urlFitxer, result.getSignedFileInfo().getSignType(),
						result.getSignedFileInfo().getEniPerfilFirma()));
			} else if (escaneigResultat instanceof String) {
				String errorMessage = (String) escaneigResultat;
				log.error("Error en l'escaneig: " + errorMessage);
				// HtmlUtils.saveMessageError(request, errorMessage);// només és útil si
				// retornam un ModelAndView
				urlErrorsOFitxers.add(new ScanWebResult(errorMessage, "", null, null));
			} else {
				String errorMessage = "Tipus de resultat inesperat: " + escaneigResultat.getClass().getName();
				log.error(errorMessage);
				urlErrorsOFitxers.add(new ScanWebResult(errorMessage, "", null, null));
			}
		}

		return urlErrorsOFitxers;
		// return new ModelAndView("homeUsuari", "fitxersFirmats", fitxersFirmatsIds);
	}

	@RequestMapping(value = "/scanweb/{transactionID}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String scanweb(
			@PathVariable(value = "transactionID", required = true) String transactionID,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("XYZ ZZZ ENTRANT A SCANWEB");
		log.info("XYZ ZZZ transactionID = " + transactionID);

		scanWebLogicaEjb.finalitzaEscaneig(transactionID, response);

		System.out.println("Connexio amb el client finalitzada.");
		return "<html>"
				+ "<body>OK. Revisi consola per saber l'estat final del proc&eacute;s</body>"
				+ "<script>window.close();</script>"
				+ "</html>";
	}

	@RequestMapping(value = "/nou/{usuariID}/check", method = RequestMethod.GET)
	public String checkUsuari(@PathVariable("usuariID") java.lang.Long usuariID, HttpServletRequest request,
			HttpServletResponse response) throws I18NException {
		log.info("XYZ ZZZ ENTRANT A USER CHECK");
		Usuari usuari = LoginInfo.getInstance().getUsuariPersona();

		String redirectString;
		log.info("XYZ ZZZ usuariID = " + usuari.getUsuariID());
		log.info("XYZ ZZZ usuariNIF = " + usuari.getNif());
		if (usuari.getUsuariID() == 0 || usuari.getNif() == null) {
			redirectString = "redirect:" + getContextWeb() + "/new";
		} else {
			redirectString = "redirect:" + getContextWeb() + "/" + usuariID + "/edit";
		}
		return redirectString;
	}

	@Override
	public boolean isActiveFormNew() {
		return true;
	}

	@Override
	public boolean isActiveList() {
		return false;
	}

	@Override
	public boolean isActiveFormEdit() {
		return true;
	}

	@Override
	public boolean isActiveDelete() {
		return false;
	}

	@Override
	public boolean isActiveFormView() {
		return false;
	}

	@Override
	public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		UsuariForm userForm = super.getUsuariForm(_jpa, __isView, request, mav);

		userForm.setCancelButtonVisible(false);
		userForm.setDeleteButtonVisible(false);
		if (userForm.getUsuari().getUsername() != null) {
			userForm.addReadOnlyField(USERNAME);
		}

		String nif = userForm.getUsuari().getNif();

		if (nif != null && nif.trim().length() > 0) {
			userForm.addReadOnlyField(NIF);
		}

		Usuari usuari = LoginInfo.getInstance().getUsuariPersona();
		UsuariJPA usuariJPA = new UsuariJPA(usuari);
		userForm.setUsuari(usuariJPA);

		return userForm;
	}

	public String getCodiDIR3(HttpServletRequest request, String username) {

		log.info("Aquest mètode es per cercar el dir3");

		try {
			// TODO: llevar aquesta primera part quan funcioni getCodiDir3Actual
			LoginInfo loginInfo = LoginInfo.getInstance();
			Long entitatId = loginInfo.getEntitatID();
			String codi = entitatLogicaEjb.findCodiDir3ByEntitatId(entitatId);
			if (codi != null) {
				return codi;
			}

			// TODO IEstructuraOrganitzativaPlugin instance =
			// pluginEstructuraOrganitzativaEjb.getInstance();
			// codiDIR3 = instance.getDir3DepartamentDireccioGeneral(username);

			String codiDIR3 = LoginInfo.getInstance().getCodiDir3Actual();
			log.info("El codiDIR3 de " + username + " es: " + codiDIR3);
			return codiDIR3;

		} catch (Throwable e) {
			HtmlUtils.saveMessageError(request, "No hem trobat el DIR3 d'aques usuari");
			return null;
			// throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfount",
			// e.getMessage());
		}

	}

	@Override
	public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {
		Where w = Where.AND(where, IdiomaFields.SUPORTAT.equal(true));
		return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID, w);
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, UsuariForm usuariForm, Throwable __e) {
		if (__e == null) {
			return "redirect:/";
		} else {
			return getTileForm();
		}
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, UsuariForm usuariForm) {
		UsuariJPA usuari = usuariForm.getUsuari();
		LoginInfo.getInstance().setUsuariPersona(usuari);

		Long userid = LoginInfo.getInstance().getUsuariPersona().getUsuariID();

		log.info("userid: " + userid);
		return "redirect:/";
	}

	@Override
	public String getTileForm() {
		return "userFormCommon";
	}

	protected static String getAbsoluteControllerBase(HttpServletRequest request,
			String webContext) {
		return getAbsoluteURLBase(request) + webContext;
	}

	protected static String getAbsoluteURLBase(HttpServletRequest request) {
		// return
		// request.getSession().getAttribute(MenuUserController.URL_BASE_NAVEGADOR)
		// + getContextWeb();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ +request.getServerPort() + request.getContextPath();
	}

	protected byte[] getResource(String path) throws Exception {
		return FileUtils.toByteArray(getClass().getResourceAsStream(path));
	}
}
