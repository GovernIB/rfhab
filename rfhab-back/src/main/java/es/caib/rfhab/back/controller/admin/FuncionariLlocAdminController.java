package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
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
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.Utils;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.HistoricLlocLogicaService;
import es.caib.rfhab.logic.HistoricLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.persistence.FuncionariLlocJPA;
import es.caib.rfhab.persistence.HistoricJPA;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/**
 * @author jagarcia
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

		FuncionariLlocJPA funcionariLlocJPA = super.create(request, funcionariLloc);

		// Guardar imatge del canvi a historic de Lloc i historic de funcionari
		final String numeroCai = (Utils.isNotEmpty(request.getParameter("numeroCai")))
				? request.getParameter("numeroCai")
				: "";

		HistoricLlocJPA historicLloc = new HistoricLlocJPA();
		long llocID = funcionariLloc.getLlocID();
		historicLloc.setLlocID(llocID);
		historicLloc.setNumeroCai(numeroCai);
		historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historicLloc.setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
		long funcionariID = funcionariLloc.getFuncionariID();
		HistoricJPA historicFuncionari = new HistoricJPA();
		historicFuncionari.setFuncionariID(funcionariID);
		historicFuncionari.setNumeroCai(numeroCai);
		historicFuncionari.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historicFuncionari.setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		String funcionariIdString = Long.toString(funcionariID);
		Funcionari funcionari = funcionariEjb.findByPrimaryKey(funcionariID);
		String funcionariIdentificador = "<null>";
		if (funcionari != null) {
			funcionariIdentificador = funcionari.getIdentificador();
		}
		String llocIdString = Long.toString(llocID);
		Lloc lloc = llocEjb.findByPrimaryKey(llocID);
		String llocCodi = "<null>";
		if (lloc != null) {
			llocCodi = lloc.getCodiLloc();
		}
		String historicLlocObservacions = "Nova assignació de funcionari " + funcionariIdentificador + " (id "
				+ funcionariIdString + ") a lloc " + llocCodi + " (id " + llocIdString + ")";
		historicLlocLogicaEjb.create(historicLloc, historicLlocObservacions);
		historicLogicaEjb.create(historicFuncionari, historicLlocObservacions);

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

	@Override
	public List<StringKeyValue> getReferenceListForLlocID(HttpServletRequest request,
			ModelAndView mav, FuncionariLlocFilterForm funcionariLlocFilterForm,
			List<FuncionariLloc> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where) throws I18NException {

		Where w1 = FuncionariFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatIDActual());
		return super.getReferenceListForFuncionariID(request, mav, Where.AND(where, w1));
	}

	@Override
	public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
			ModelAndView mav, FuncionariLlocForm funcionariLlocForm, Where where) throws I18NException {

		Where w1 = FuncionariFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatIDActual());
		return super.getReferenceListForFuncionariID(request, mav, Where.AND(where, w1));
	}
}