package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.FuncionariLlocController;
import es.caib.rfhab.back.form.webdb.FuncionariLlocFilterForm;
import es.caib.rfhab.back.form.webdb.FuncionariLlocForm;
import es.caib.rfhab.back.security.LoginException;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.logic.FuncionariLlocLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.HistoricLlocLogicaService;
import es.caib.rfhab.logic.HistoricLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.persistence.FuncionariLlocJPA;

/**
 * @author jagarcia
 * @author jpou
 */
@Controller
@RequestMapping(value = "/admin/funcionarilloc")
@SessionAttributes(types = { FuncionariLlocForm.class, FuncionariLlocFilterForm.class })
public class FuncionariLlocAdminController extends FuncionariLlocController {

	@EJB(mappedName = HistoricLlocLogicaService.JNDI_NAME)
	protected HistoricLlocLogicaService historicLlocLogicaEjb;

	@EJB(mappedName = HistoricLogicaService.JNDI_NAME)
	protected HistoricLogicaService historicLogicaEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariEjb;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocEjb;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	protected FuncionariLlocLogicaService funcionariLlocEjb;

	@Override
	public String getTileForm() {
		return "funcionariLlocFormAdmin";
	}

	@Override
	public String getTileList() {
		return "funcionariLlocListAdmin";
	}

	@Override
	public FuncionariLlocFilterForm getFuncionariLlocFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		FuncionariLlocFilterForm funcionariLlocFilterForm = super.getFuncionariLlocFilterForm(pagina, mav, request);

		if (funcionariLlocFilterForm.isNou()) {
			funcionariLlocFilterForm.addHiddenField(FUNCIONARILLOCID);
			funcionariLlocFilterForm.addHiddenField(DATACREACIO);

			funcionariLlocFilterForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));
			log.info("getContextPath: " + request.getContextPath());
		}

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return funcionariLlocFilterForm;
	}

	@Override
	public FuncionariLlocForm getFuncionariLlocForm(FuncionariLlocJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {

		FuncionariLlocForm funcionariLlocForm = super.getFuncionariLlocForm(_jpa, __isView, request, mav);

		if (funcionariLlocForm.isNou()) {
			FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();
			if (request.getSession() != null && request.getSession().getAttribute("LlocId") != null) {
				funcionariLloc.setLlocID((long) request.getSession().getAttribute("LlocId"));
				funcionariLlocForm.addReadOnlyField(LLOCID);
			}

			if (request.getSession() != null && request.getSession().getAttribute("FuncionariId") != null) {
				funcionariLloc.setFuncionariID((long) request.getSession().getAttribute("FuncionariId"));
				funcionariLlocForm.addReadOnlyField(FUNCIONARIID);
			}

			funcionariLloc.setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
			funcionariLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		}

		funcionariLlocForm.addHiddenField(USUARIID);
		funcionariLlocForm.addReadOnlyField(DATACREACIO);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return funcionariLlocForm;
	}

	@Override
	public FuncionariLlocJPA create(HttpServletRequest request, FuncionariLlocJPA funcionariLloc)
			throws I18NException, I18NValidationException {

		// TODO:revisar això #38
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numeroCai")))
				? request.getParameter("numeroCai")
				: "";

		FuncionariLlocJPA funcionariLlocJPA = null;
		funcionariLlocJPA = funcionariLlocEjb.assignarFuncionari(funcionariLloc, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		return funcionariLlocJPA;
	}

	@RequestMapping(value = "/assignarfuncionari/{funcionariId}", method = RequestMethod.GET)
	public String assignarFuncionariLloc(HttpServletRequest request, @PathVariable("funcionariId") Long funcionariId) {
		request.getSession().setAttribute("FuncionariId", funcionariId);
		return "redirect:/admin/funcionarilloc/new";
	}

	@RequestMapping(value = "/assignar/{llocId}", method = RequestMethod.GET)
	public String assignarFuncionari(HttpServletRequest request, @PathVariable("llocId") Long llocId) {

		request.getSession().setAttribute("LlocId", llocId);
		return "redirect:/admin/funcionarilloc/new";
	}

	@RequestMapping(value = "/treure/{llocId}", method = RequestMethod.GET)
	public String treureFuncionari(HttpServletRequest request, @PathVariable("llocId") Long llocId)
			throws LoginException, I18NException {

		log.info("Desassignant funcionari(s) del lloc " + llocId);

		// TODO:revisar això #38
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numeroCai")))
				? request.getParameter("numeroCai")
				: "";

		funcionariEjb.dessassignarFuncionariAndHistory(null, llocId, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID(), false, false);

		createMessageSuccess(request, "success.modification", llocId);// funcionari.donaralta.exit
		return getRedirectWhenCreated(request, null);
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return UrlUtils.getRefererRedirect(request, "redirect:/admin/funcionari/list/1");
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long historicID) {
		return UrlUtils.getRefererRedirect(request, "redirect:/admin/funcionari/list/1");
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionarillocID, Throwable __e) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenDelete(request, funcionarillocID, __e));
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, FuncionariLlocForm funcionariLlocForm) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCreated(request, funcionariLlocForm));
	}
}