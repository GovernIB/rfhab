package es.caib.rfhab.back.controller.admin;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.persistence.FuncionariLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;

/**
 * @author jagarcia
 * @author jpou
 */
@Controller
@RequestMapping(value = FuncionariLlocAdminController.CONTEXTWEB)
@SessionAttributes(types = { FuncionariLlocForm.class, FuncionariLlocFilterForm.class })
public class FuncionariLlocAdminController extends FuncionariLlocController {

	private static final String LLOC_ID_SESSION_ATTRIBUTE_NAME = "LlocId";

	private static final String FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME = "FuncionariId";

	private static final String FUNCIONARI_VALOR_BUIT = "0";

	public static final String CONTEXTWEB = "/admin/funcionarilloc";

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

	private void setLlocRefListSelects() {
		Select<?>[] selects;
		selects = new Select<?>[] { LlocFields.CODILLOC.select, LlocFields.NOM.select };
		llocRefList.setSelects(selects);
	}

	@Override
	public FuncionariLlocFilterForm getFuncionariLlocFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		setLlocRefListSelects();
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

		setLlocRefListSelects();

		FuncionariLlocForm funcionariLlocForm = super.getFuncionariLlocForm(_jpa, __isView, request, mav);

		HttpSession currentSession = request.getSession();

		String jsOpenModalGuardar = "javascript:createDivModal('"
				+ I18NUtils.tradueix("funcionarilloc.modificar.guardar.titol") + "', '"
				+ I18NUtils.tradueix("funcionarilloc.modificar.guardar.missatge",
						new String[] { "##funcionari-numero##", "##lloc-codillocpropi##" })
				+ "', '', 'funcionarillocForm', 'funcionarilloc-save-modal-id', 'fa-save', '', null, '"
				+ I18NUtils.tradueix("acceptar") + "');\r\n" +
				"        const funcionariName = document.getElementById('funcionariLloc_funcionariID').options[document.getElementById('funcionariLloc_funcionariID').selectedIndex].text;\r\n"
				+
				"        const llocFeinaName = document.getElementById('funcionariLloc_llocID').options[document.getElementById('funcionariLloc_llocID').selectedIndex].text;\r\n"
				+
				"        document.querySelector('#funcionarilloc-save-modal-id .modal-body p').innerText = document.querySelector('#funcionarilloc-save-modal-id .modal-body p').innerText.replace('##funcionari-numero##', funcionariName);\r\n"
				+
				"        document.querySelector('#funcionarilloc-save-modal-id .modal-body p').innerText = document.querySelector('#funcionarilloc-save-modal-id .modal-body p').innerText.replace('##lloc-codillocpropi##', llocFeinaName);\r\n"
				+
				"        $('#funcionarilloc-save-modal-id').modal('show');\r\n";
		AdditionalButton guardarButton = new AdditionalButton("",
				"genapp.save",
				jsOpenModalGuardar,
				AdditionalButtonStyle.PRIMARY);
		funcionariLlocForm.setSaveButtonVisible(false);

		if (funcionariLlocForm.isNou()) {
			funcionariLlocForm.addAdditionalButton(guardarButton);

			FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();
			if (currentSession != null) {
				Object llocIdAttribute = currentSession.getAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME);
				if (llocIdAttribute != null && !llocIdAttribute.toString().isEmpty() && ((long) llocIdAttribute) != 0) {
					log.info("getFuncionariLlocForm llocId detectat, assignant: " + llocIdAttribute);
					funcionariLloc.setLlocID((long) llocIdAttribute);
					funcionariLlocForm.addReadOnlyField(LLOCID);
				}

				Object funcionariIdAttribute = currentSession.getAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME);
				if (funcionariIdAttribute != null && !funcionariIdAttribute.toString().isEmpty()
						&& ((long) funcionariIdAttribute) != 0) {
					log.info("getFuncionariLlocForm funcionariId detectat, assignant: " + funcionariIdAttribute);
					funcionariLloc.setFuncionariID(
							(long) funcionariIdAttribute);
					funcionariLlocForm.addReadOnlyField(FUNCIONARIID);
				}
			}

			funcionariLloc.setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
			funcionariLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			funcionariLloc.setDataInici(new Date(System.currentTimeMillis()));
			funcionariLlocForm.addHiddenField(FuncionariLlocFields.DATACREACIO);
			funcionariLlocForm.addHiddenField(FuncionariLlocFields.DATAFI);
		} else if (!__isView) {
			// no el volem veure al mode de consulta
			funcionariLlocForm.addAdditionalButton(guardarButton);
		}

		funcionariLlocForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
				getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));

		funcionariLlocForm.addHiddenField(USUARIID);
		funcionariLlocForm.addReadOnlyField(DATACREACIO);

		funcionariLlocForm.setTitleCode("funcionarilloc.titol");
		funcionariLlocForm.setCancelButtonVisible(false);
		funcionariLlocForm.setAttachedAdditionalJspCode(true);

		currentSession.setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return funcionariLlocForm;
	}

	@Override
	public void preValidate(HttpServletRequest request, FuncionariLlocForm funcionariLlocForm, BindingResult result)
			throws I18NException {
		FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();
		if (String.valueOf(funcionariLloc.getFuncionariID()).equals(FUNCIONARI_VALOR_BUIT)) {
			result.rejectValue(FuncionariLlocFields.FUNCIONARIID.codeLabel, "error.required",
					new Object[] { "Funcionari/ària" },
					"El camp " + I18NUtils.tradueix(FuncionariLlocFields.FUNCIONARIID.codeLabel) + " és obligatori");
		}
	}

	@Override
	public FuncionariLlocJPA create(HttpServletRequest request, FuncionariLlocJPA funcionariLloc)
			throws I18NException, I18NValidationException {

		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: "";
		final String observacions = (StringUtils.isNotEmpty(request.getParameter("observacions")))
				? request.getParameter("observacions")
				: "";

		FuncionariLlocJPA funcionariLlocJPA = null;
		funcionariLlocJPA = funcionariLlocEjb.assignarFuncionari(funcionariLloc, numeroCai, observacions,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		return funcionariLlocJPA;
	}

	@RequestMapping(value = "/assignarfuncionari/{funcionariId}", method = RequestMethod.GET)
	public String assignarFuncionariLloc(HttpServletRequest request, @PathVariable("funcionariId") Long funcionariId) {
		request.getSession().setAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME, funcionariId);
		request.getSession().removeAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME);
		return "redirect:" + FuncionariLlocAdminController.CONTEXTWEB + "/new";
	}

	@RequestMapping(value = "/assignar/{llocId}", method = RequestMethod.GET)
	public String assignarFuncionari(HttpServletRequest request, @PathVariable("llocId") Long llocId) {
		request.getSession().setAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME, llocId);
		request.getSession().removeAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME);
		return "redirect:" + FuncionariLlocAdminController.CONTEXTWEB + "/new";
	}

	@RequestMapping(value = "/treure/{llocId}", method = RequestMethod.GET)
	public String treureFuncionari(HttpServletRequest request, @PathVariable("llocId") Long llocId)
			throws LoginException, I18NException {

		log.info("Desassignant funcionari(s) del lloc " + llocId);

		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: "";

		LlocJPA llocDelQualDesassignar = llocEjb.findByPrimaryKey(llocId);
		if (llocDelQualDesassignar == null) {
			String __msg = createMessageError(request, "error.notfound", llocId);
			log.error(__msg);
		} else {
			Funcionari funcionariDesassignat = funcionariEjb.dessassignarFuncionariAndHistory(null, llocId, numeroCai,
					LoginInfo.getInstance().getUsuariPersona().getUsuariID(), false, false);
			if (funcionariDesassignat == null) {
				String msg = I18NUtils.tradueix("funcionarilloc.treure.error",
						new String[] { llocDelQualDesassignar.getCodiLlocPropi() });
				HtmlUtils.saveMessageWarning(request, msg);
			} else {
				String msg = I18NUtils.tradueix("funcionarilloc.treure.success",
						new String[] { funcionariDesassignat.getNumero(), llocDelQualDesassignar.getCodiLlocPropi() });
				HtmlUtils.saveMessageSuccess(request, msg);
			}
		}

		return "redirect:" + LlocAdminController.CONTEXTWEB + "/list/";
	}

	@Override
	public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
			ModelAndView mav, FuncionariLlocForm funcionariLlocForm, Where where) throws I18NException {
		if (funcionariLlocForm.isHiddenField(FUNCIONARIID)) {
			return EMPTY_STRINGKEYVALUE_LIST;
		}
		Where _where = null;
		if (funcionariLlocForm.isReadOnlyField(FUNCIONARIID)) {
			_where = FuncionariFields.FUNCIONARIID.equal(funcionariLlocForm.getFuncionariLloc().getFuncionariID());
			return funcionariRefList.getReferenceList(FuncionariFields.FUNCIONARIID, _where);
		}
		return getReferenceListForFuncionariID(request, mav, Where.AND(where, _where));
	}

	@Override
	public List<StringKeyValue> getReferenceListForFuncionariID(HttpServletRequest request,
			ModelAndView mav, Where where) throws I18NException {
		LoginInfo loginInfo = LoginInfo.getInstance();
		Long entitatActual = loginInfo.getEntitatIDActual();
		Where funcionarisFromEntitatActualWhere = FuncionariFields.ENTITATID.equal(entitatActual);

		List<StringKeyValue> funcionarisList = funcionariRefList.getReferenceList(FuncionariFields.FUNCIONARIID,
				Where.AND(where, funcionarisFromEntitatActualWhere));

		funcionarisList.removeIf(funcionari -> {
			try {
				return funcionariLlocEjb.isFuncionariAssignat(Long.parseLong(funcionari.getKey()));
			} catch (I18NException e) {
				log.error("Error checking if funcionari is assigned", e);
				return false;
			}
		});

		funcionarisList.add(new StringKeyValue(FUNCIONARI_VALOR_BUIT, I18NUtils.tradueix("trieuopcio")));
		return funcionarisList;
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:" + LlocAdminController.CONTEXTWEB + "/list/1";
		// return UrlUtils.getRefererRedirect(request,
		// "redirect:/admin/funcionari/list/1");
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long historicID) {
		return UrlUtils.getRefererRedirect(request, "redirect:" + LlocAdminController.CONTEXTWEB + "/list/1");
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionarillocID, Throwable __e) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenDelete(request, funcionarillocID, __e));
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, FuncionariLlocForm funcionariLlocForm) {
		if (funcionariLlocForm != null) {
			FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();
			if (funcionariLloc != null) {
				Long funcionariId = funcionariLloc.getFuncionariID();
				Long llocId = funcionariLloc.getLlocID();
				if (funcionariId != null && funcionariId != 0) {
					log.info("redirigint amb funcionariId=" + funcionariId);
					request.getSession().setAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME, funcionariId);
				} else {
					request.getSession().removeAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME);
				}
				if (llocId != null && llocId != 0) {
					log.info("redirigint amb llocId=" + llocId);
					request.getSession().setAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME, llocId);
				} else {
					request.getSession().removeAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME);
				}

				return "redirect:" + FuncionariLlocAdminController.CONTEXTWEB + "/new";
			}
		}

		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCreated(request, funcionariLlocForm), false);
	}
}