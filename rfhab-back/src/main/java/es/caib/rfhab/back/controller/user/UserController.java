package es.caib.rfhab.back.controller.user;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.webdb.UsuariFilterForm;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.model.entity.Usuari;
import es.caib.rfhab.model.fields.IdiomaFields;
import es.caib.rfhab.persistence.UsuariJPA;
import es.caib.rfhab.pluginsib.digitalib.ScanWebSimplePlugin;
import es.caib.rfhab.pluginsib.rolsac.RolsacPlugin;

/**
 * 
 * @author jagarcia
 *
 */
@Controller
@RequestMapping(value = "/usuari/")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UserController extends UsuariController {

	private RolsacPlugin rolsacPlugin;

	private ScanWebSimplePlugin scanwebPlugin;

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

	@RequestMapping(value = "/scanweb", method = RequestMethod.GET)
	public ModelAndView scanweb(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info("XYZ ZZZ ENTRANT A SCANWEB");

		scanwebPlugin = new ScanWebSimplePlugin();
		scanwebPlugin.escaneig("u00666", "ca", "Funcionari DeProfessio", "12345678X", "1254123412",
				Arrays.asList("43153858Q"), Arrays.asList("A04013511"), "11223344C", "Pep Gonella");

		ModelAndView mav = new ModelAndView("scanPage");

		return mav;
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

}
