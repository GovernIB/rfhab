package es.caib.rfhab.back.controller.user;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import es.caib.rfhab.back.controller.FileDownloadController;
import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.webdb.UsuariFilterForm;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.logic.EntitatLogicaService;
import es.caib.rfhab.logic.FitxerPublicLogicaService;
import es.caib.rfhab.logic.ScanWebLogicaService;
import es.caib.rfhab.logic.UnitatLogicaUserService;
import es.caib.rfhab.model.bean.ScanWebBean;
import es.caib.rfhab.model.entity.Entitat;
import es.caib.rfhab.model.entity.Fitxer;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.model.entity.Usuari;
import es.caib.rfhab.model.fields.IdiomaFields;
import es.caib.rfhab.persistence.FitxerJPA;
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

		rolsacPlugin = new RolsacPlugin();
		HashMap<String, String> llistaProcediments = rolsacPlugin.obtenirProcediments();

		// HashMap<String, String> llistaTramits =
		// rolsacPlugin.obtenirTramits("1533169");

		ModelAndView mav = new ModelAndView("homeUsuari");

		if (llistaProcediments != null) {
			mav.addObject("llistaProcediments", llistaProcediments);
		} else {
			mav.addObject("llistaProcediments", new HashMap<String, String>());
		}

		return mav;
	}

	@RequestMapping(value = "/preparescanweb", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, String> prepareScanweb(
			@RequestParam(value = "languageUI", required = false) String languageUI,
			@RequestParam(value = "interessats", required = false) String interessats,
			@RequestParam(value = "ciutadaTipusIdentificacio", required = false) String ciutadaTipusIdentificacio,
			@RequestParam(value = "ciutadaNif", required = false) String ciutadaNif,
			@RequestParam(value = "ciutadaNom", required = false) String ciutadaNom,
			@RequestParam(value = "ciutadaLlinatges", required = false) String ciutadaLlinatges,
			@RequestParam(value = "representant", required = false) Boolean representant,
			@RequestParam(value = "representantNom", required = false) String representantNom,
			@RequestParam(value = "representantLlinatges", required = false) String representantLlinatges,
			@RequestParam(value = "representantTipusIdentificacio", required = false) String representantTipusIdentificacio,
			@RequestParam(value = "representantIdentificacio", required = false) String representantIdentificacio,
			@RequestParam(value = "procediment", required = false) String procediment,
			@RequestParam(value = "tramit", required = false) String tramit,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("XYZ ZZZ ENTRANT A PREPARESCANWEB");

		log.info("XYZ YYY languageUI = " + languageUI);
		log.info("XYZ YYY interessats = " + interessats);
		List<String> interessatsList = Arrays.asList(interessats.split("--"));
		log.info("XYZ YYY interessatsList = " + interessatsList);
		log.info("XYZ YYY ciutadaTipusIdentificacio = " + ciutadaTipusIdentificacio);
		log.info("XYZ YYY ciutadaNif = " + ciutadaNif);
		log.info("XYZ YYY ciutadaNom = " + ciutadaNom);
		log.info("XYZ YYY ciutadaLlinatges = " + ciutadaLlinatges);
		log.info("XYZ YYY representant = " + representant);
		log.info("XYZ YYY representantNom = " + representantNom);
		log.info("XYZ YYY representantLlinatges = " + representantLlinatges);
		log.info("XYZ YYY representantTipusIdentificacio = " + representantTipusIdentificacio);
		log.info("XYZ YYY representantIdentificacio = " + representantIdentificacio);
		log.info("XYZ YYY procediment = " + procediment);
		log.info("XYZ YYY tramit = " + tramit);
		// TODO:aquí sobra info que s'ha d'emprar per generar el pdf plantilla que
		// descarregarà l'usuari

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

	@RequestMapping(value = "/checkfinalscanweb/", method = RequestMethod.GET)
	@ResponseBody
	public List<String> checkFinalScanweb(
			@RequestParam(value = "transactionID", required = true) String transactionID,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("checkFinalScanweb: " + transactionID);

		Map<String, String> urlFitxersFirmatsOerrors = scanWebLogicaEjb.checkFinalScanweb(transactionID);// a urlFitxersFirmatsOerrors tenc subtransaccions

		if (urlFitxersFirmatsOerrors == null) {
			// transacció en curs
			log.info("checkFinalScanweb: transacció en curs");
			return null;
		}

		// HtmlUtils.saveMessageError només és útil si retornam un ModelAndView
		List<String> urlErrorsOFitxers = new java.util.ArrayList<String>();
		for (String urlFitxer : urlFitxersFirmatsOerrors.values()) {
			File file = new File(urlFitxer);
			if (!file.exists()) {
				// Si hi ha un error, el retornem com a missatge d'error
				// HtmlUtils.saveMessageError(request, urlFitxer);// només és útil si retornam un ModelAndView
				urlErrorsOFitxers.add(urlFitxer);
			} else {
				LoginInfo loginInfo = LoginInfo.getInstance();
				Fitxer fitxer = scanWebLogicaEjb.guardaEscaneig(transactionID, file, loginInfo.getEntitatID(),
						loginInfo.getUsuariPersona().getUsuariID());
				urlErrorsOFitxers
						.add(FileDownloadController.fileUrl(fitxer));
						// .add(getAbsoluteControllerBase(request, FileDownloadController.CONTEXTWEB) + fitxerId);
			}
		}

		return urlErrorsOFitxers;
		// return new ModelAndView("homeUsuari", "fitxersFirmats", fitxersFirmatsIds);
	}

	@RequestMapping(value = "/scanweb/{transactionID}", method = RequestMethod.GET)
	@ResponseBody
	public void scanweb(
			@PathVariable(value = "transactionID", required = true) String transactionID,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("XYZ ZZZ ENTRANT A SCANWEB");

		log.info("XYZ ZZZ transactionID = " + transactionID);

		scanWebLogicaEjb.checkResultatEscaneig(transactionID, response);

		PrintWriter out = response.getWriter();
		out.println("HTTP/1.0 200 OK");
		out.println("Content-Type: text/html");
		out.println("\r\n");
		out.println(
				"<html><body>OK. Revisi consola per saber l'estat final del proc&eacute;s</body></html>");

		out.flush();
		out.close();
		System.err.println("Connexio amb el client finalitzada.");

		// ModelAndView mav = new ModelAndView("homeUsuari", "fitxersFirmats",
		// urlFitxersFirmats);
		// ModelAndView mav = new ModelAndView("scanPage", "fitxersFirmats",
		// urlFitxersFirmats);

		// return mav;
		// return new ModelAndView("homeUsuari");
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
}
