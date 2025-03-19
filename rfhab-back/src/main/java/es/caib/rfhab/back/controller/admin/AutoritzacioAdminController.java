package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.back.controller.webdb.AutoritzacioController;
import es.caib.rfhab.back.form.webdb.AutoritzacioFilterForm;
import es.caib.rfhab.back.form.webdb.AutoritzacioForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.persistence.AutoritzacioJPA;
import es.caib.rfhab.pluginsib.rolsac.RolsacPlugin;

@Controller
@RequestMapping(value = "/admin/autoritzacio")
@SessionAttributes(types = { AutoritzacioForm.class, AutoritzacioFilterForm.class })
public class AutoritzacioAdminController extends AutoritzacioController {

	private RolsacPlugin rolsacPlugin = null;

	@Override
	public String getTileForm() {
		return "autoritzacioFormAdmin";
	}

	@Override
	public String getTileList() {
		return "autoritzacioListAdmin";
	}

	@Override
	public AutoritzacioForm getAutoritzacioForm(AutoritzacioJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		AutoritzacioForm autoritzacioForm = super.getAutoritzacioForm(_jpa, __isView, request, mav);

		if (autoritzacioForm.isNou()) {

			if (request.getSession() != null && request.getSession().getAttribute("funcionariId") != null) {
				autoritzacioForm.getAutoritzacio()
						.setFuncionariID((long) request.getSession().getAttribute("funcionariId"));
				autoritzacioForm.addReadOnlyField(FUNCIONARIID);
			}

			autoritzacioForm.getAutoritzacio().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			autoritzacioForm.getAutoritzacio().setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
			autoritzacioForm.addHiddenField(USUARIID);

			autoritzacioForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));
		}

		autoritzacioForm.addReadOnlyField(DATACREACIO);
		
		autoritzacioForm.setAttachedAdditionalJspCode(true);

		return autoritzacioForm;

	}

	@Override
	public AutoritzacioFilterForm getAutoritzacioFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		AutoritzacioFilterForm autoritzacioFilterForm = super.getAutoritzacioFilterForm(pagina, mav, request);

		if (autoritzacioFilterForm.isNou()) {

			autoritzacioFilterForm.addHiddenField(AUTORITZACIOID);
			autoritzacioFilterForm.addHiddenField(DATACREACIO);
			autoritzacioFilterForm.addHiddenField(OBSERVACIONS);

			autoritzacioFilterForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));

		}

		autoritzacioFilterForm.setViewButtonVisible(true);

		return autoritzacioFilterForm;

	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:/admin/lloc/list/1";
	}

	@RequestMapping(value = "/getProcediments", method = RequestMethod.GET)
	public ModelAndView getProcediments(HttpServletRequest request) {

		try {
			if (rolsacPlugin == null)
				rolsacPlugin = new RolsacPlugin();

			HashMap<String, String> llistaProcediments = rolsacPlugin.obtenirProcediments();

			if (llistaProcediments != null) {
				llistaProcediments.forEach((x, y) -> log.info("Procediment: " + x + " " + y));
			}

			ModelAndView mav = new ModelAndView("jsonView");

			if (llistaProcediments == null)
				llistaProcediments = new HashMap<String, String>();

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(llistaProcediments);		
			mav.addObject("jsonData", json);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping(value = "/getTramits/{procedimentID}", method = RequestMethod.GET)
	public ModelAndView getTramits(HttpServletRequest request, @PathVariable("procedimentID") String procedimentID) {

		try {
			if (rolsacPlugin == null)
				rolsacPlugin = new RolsacPlugin();

			HashMap<String, String> llistaTramits = rolsacPlugin.obtenirTramits(procedimentID);

			if (llistaTramits != null) {
				llistaTramits.forEach((x, y) -> log.info("Tramit: " + x + " " + y));
			}

			ModelAndView mav = new ModelAndView("jsonView");

			if (llistaTramits == null)
				llistaTramits = new HashMap<String, String>();

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(llistaTramits);
			mav.addObject("jsonData", json);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping(value = "/assignar/{funcionariId}", method = RequestMethod.GET)
	public String novaAutoritzacioFuncionari(HttpServletRequest request,
			@PathVariable("funcionariId") Long funcionariId) {
		request.getSession().setAttribute("funcionariId", funcionariId);
		return "redirect:/admin/autoritzacio/new";
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long historicID) {
		return "redirect:/admin/funcionari/list/1";
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, AutoritzacioForm autoritzacioForm) {

		if (request.getSession() != null && request.getSession().getAttribute("funcionariId") != null) {
			String funcionariId = (String) request.getSession().getAttribute("funcionariId");
			return "redirect:/admin/funcionari/" + funcionariId + "/view";
		}

		if (request.getSession() != null && request.getSession().getAttribute("llocId") != null) {
			String llocId = (String) request.getSession().getAttribute("llocId");
			return "redirect:/admin/lloc/" + llocId + "/view";
		}

		return "redirect:/admin/funcionari/list/1";
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, AutoritzacioForm autoritzacioForm,
			Throwable __e) {
		return getRedirectWhenCreated(request, autoritzacioForm);
	}

}
