package es.caib.rfhab.back.controller.admin;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.persistence.FuncionariLlocJPA;

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

		HttpSession currentSession = request.getSession();
		if (funcionariLlocForm.isNou()) {
			FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();
			if (currentSession != null) {
				Object llocIdAttribute = currentSession.getAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME);
				if (llocIdAttribute != null && !llocIdAttribute.toString().isEmpty() && ((long) llocIdAttribute) != 0) {
					funcionariLloc.setLlocID((long) llocIdAttribute);
					funcionariLlocForm.addReadOnlyField(LLOCID);
				}

				Object funcionariIdAttribute = currentSession.getAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME);
				if (funcionariIdAttribute != null && !funcionariIdAttribute.toString().isEmpty()
						&& ((long) funcionariIdAttribute) != 0) {
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
		}

		funcionariLlocForm.addHiddenField(USUARIID);
		funcionariLlocForm.addReadOnlyField(DATACREACIO);

		funcionariLlocForm.setTitleCode("funcionarilloc.titol");
		currentSession.setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

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
		request.getSession().setAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME, funcionariId);
		return "redirect:" + FuncionariLlocAdminController.CONTEXTWEB + "/new";
	}

	@RequestMapping(value = "/assignar/{llocId}", method = RequestMethod.GET)
	public String assignarFuncionari(HttpServletRequest request, @PathVariable("llocId") Long llocId) {

		request.getSession().setAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME, llocId);
		return "redirect:" + FuncionariLlocAdminController.CONTEXTWEB + "/new";
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
		FuncionariLlocJPA funcionariLlocFake = new FuncionariLlocJPA();
		funcionariLlocFake.setLlocID(llocId);
		return getRedirectWhenCreated(request, new FuncionariLlocForm(funcionariLlocFake, false));
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

		return funcionarisList;
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
		if (funcionariLlocForm != null) {
			FuncionariLlocJPA funcionariLloc = funcionariLlocForm.getFuncionariLloc();
			if (funcionariLloc != null) {
				Long funcionariId = funcionariLloc.getFuncionariID();
				Long llocId = funcionariLloc.getLlocID();
				if (funcionariId != null) {
					log.info("redirigint amb funcionariId=" + funcionariId);
					request.getSession().setAttribute(FUNCIONARI_ID_SESSION_ATTRIBUTE_NAME, funcionariId);
				}
				if (llocId != null) {
					log.info("redirigint amb llocId=" + llocId);
					request.getSession().setAttribute(LLOC_ID_SESSION_ATTRIBUTE_NAME, llocId);
				}

				return "redirect:" + FuncionariLlocAdminController.CONTEXTWEB + "/new";
			}
		}

		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCreated(request, funcionariLlocForm), false);
	}

	@Override
	public void postValidate(HttpServletRequest request, FuncionariLlocForm funcionariLlocForm, BindingResult result)
			throws I18NException {

		if (result.hasFieldErrors(get(FuncionariLlocFields.FUNCIONARIID))
				&& result.hasFieldErrors(get(FuncionariLlocFields.LLOCID))) {
			removeFieldErrors(result, get(FuncionariLlocFields.FUNCIONARIID));
			removeFieldErrors(result, get(FuncionariLlocFields.LLOCID));
		}
	}

	public void removeFieldErrors(BindingResult bindingResult, String fieldName) {
		if (!(bindingResult instanceof BeanPropertyBindingResult)) {
			throw new IllegalArgumentException("Només funciona amb BeanPropertyBindingResult");
		}
		BeanPropertyBindingResult br = (BeanPropertyBindingResult) bindingResult;
		try {
			Field errorsField = BeanPropertyBindingResult.class.getSuperclass().getSuperclass()
					.getDeclaredField("errors"); // està definit a AbstractBindingResult
			errorsField.setAccessible(true);

			List<ObjectError> errors = (List<ObjectError>) errorsField.get(br);

			Iterator<ObjectError> it = errors.iterator();
			while (it.hasNext()) {
				ObjectError err = it.next();
				if (err instanceof FieldError) {
					FieldError fe = (FieldError) err;
					if (fe.getField().equals(fieldName)) {
						it.remove(); // elimina el error
					}
				}
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			log.error("No se pudo acceder a los errores internos: " + e.getMessage(), e);
			throw new RuntimeException("No se pudo acceder a los errores internos: " + e.getMessage(), e);
		}
	}
}