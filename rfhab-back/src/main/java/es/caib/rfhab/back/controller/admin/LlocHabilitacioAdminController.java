package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.LlocHabilitacioController;
import es.caib.rfhab.back.form.webdb.LlocHabilitacioFilterForm;
import es.caib.rfhab.back.form.webdb.LlocHabilitacioForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.ejb.LlocHabilitacioService;
import es.caib.rfhab.logic.HabilitacioLogicaService;
import es.caib.rfhab.model.entity.LlocHabilitacio;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.LlocHabilitacioFields;
import es.caib.rfhab.model.fields.HabilitacioFields;
import es.caib.rfhab.persistence.LlocJPA;
import es.caib.rfhab.persistence.LlocHabilitacioJPA;

@Controller
@RequestMapping(value = LlocHabilitacioAdminController.CONTEXTWEB)
@SessionAttributes(types = { LlocHabilitacioForm.class, LlocHabilitacioFilterForm.class })
public class LlocHabilitacioAdminController extends LlocHabilitacioController {

	public static final String CONTEXTWEB = "/admin/llochabilitacio";

	protected final Logger log = Logger.getLogger(getClass());

	@EJB(mappedName = LlocHabilitacioService.JNDI_NAME)
	protected LlocHabilitacioService llocHabilitacioLogicaEJB;

	@EJB(mappedName = HabilitacioLogicaService.JNDI_NAME)
	protected HabilitacioLogicaService habilitacioLogicaEJB;

	@Override
	public String getTileForm() {
		return "llocHabilitacioFormAdmin";
	}

	@Override
	public String getTileList() {
		return "llocHabilitacioListAdmin";
	}

	@Override
	public LlocHabilitacioFilterForm getLlocHabilitacioFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		LlocHabilitacioFilterForm llocHabilitacioFilterForm = super.getLlocHabilitacioFilterForm(pagina, mav, request);

		if (llocHabilitacioFilterForm.isNou()) {
			llocHabilitacioFilterForm.addHiddenField(LLOCHABILITACIOID);
			llocHabilitacioFilterForm.addHiddenField(DATACREACIO);

			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(HabilitacioFields.NOMID.codeLabel);
				adfield.setPosition(1);
				adfield.setOrderBy(HabilitacioFields.NOMID);
				adfield.setEscapeXml(false);
				adfield.setValueMap(new HashMap<Long, String>());
				llocHabilitacioFilterForm.addAdditionalField(adfield);
			}

			llocHabilitacioFilterForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.DARK));
		}

		return llocHabilitacioFilterForm;
	}

	@Override
	public LlocHabilitacioForm getLlocHabilitacioForm(LlocHabilitacioJPA _jpa, boolean __isView,
			HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		LlocHabilitacioForm llocHabilitacioForm = super.getLlocHabilitacioForm(_jpa, __isView, request, mav);

		if (llocHabilitacioForm.isNou()) {
			llocHabilitacioForm.getLlocHabilitacio()
					.setLlocID((long) request.getSession().getAttribute("llocId"));
			llocHabilitacioForm.getLlocHabilitacio().setDataCreacio(new Timestamp(System.currentTimeMillis()));

			llocHabilitacioForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.DARK));
		}

		llocHabilitacioForm.addReadOnlyField(LLOCID);
		llocHabilitacioForm.addHiddenField(DATACREACIO);

		// Obtenir les habilitacions del funcionari i passar-los a la vista per marcar
		// els
		// checkboxs
		List<LlocHabilitacio> habilitacionsLloc = llocHabilitacioLogicaEJB
				.select(LlocHabilitacioFields.LLOCID.equal(llocHabilitacioForm.getLlocHabilitacio().getLlocID()));
		mav.addObject("habilitacionsLloc", habilitacionsLloc);

		llocHabilitacioForm.setAttachedAdditionalJspCode(true);

		return llocHabilitacioForm;
	}

	@RequestMapping(value = "/assignar/{llocId}", method = RequestMethod.GET)
	public String assignarHabilitacio(HttpServletRequest request, @PathVariable("llocId") Long llocId) {

		log.info("Assignar habilitacions a llocId => " + llocId);
		request.getSession().setAttribute("llocId", llocId);
		return "redirect:" + getContextWeb() + "/new";
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:/admin/lloc/list/";
	}

	@RequestMapping(value = "/modificar/{llocId}", method = RequestMethod.GET)
	public ModelAndView modificarLloc(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long llocId) throws I18NException {

		request.getSession().setAttribute("llocId", llocId);
		return llistatPaginat(request, response, 1);
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Long llocId = (Long) request.getSession().getAttribute("llocId");
		Where w = null;
		if (llocId != null) {
			w = LlocHabilitacioFields.LLOCID.equal(llocId);
		}
		return w;
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, LlocHabilitacioForm llocHabilitacioForm) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, LlocHabilitacioForm llocHabilitacioForm,
			Throwable __e) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long llocHabilitacioID, Throwable __e) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long llocHabilitacioID) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String crearLlocHabilitacioPost(@ModelAttribute LlocHabilitacioForm llocHabilitacioForm,
			BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!isActiveFormNew()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		LlocHabilitacioJPA llocHabilitacioActual = llocHabilitacioForm.getLlocHabilitacio();
		LlocJPA lloc = llocHabilitacioActual.getLloc();
		String llocCodi = lloc.getCodiLloc();
		if (lloc.getDataBaixa() != null || lloc.getDataalta() == null) {
			throw new I18NValidationException(new I18NFieldError(FuncionariFields.DATABAIXA,
					new I18NTranslation("llochabilitacio.error.lloc.baixa", llocCodi)));
		}

		try {

			log.info("------ START Crear LlocHabilitacioPost ----");

			ArrayList<Long> habilitacionsMarcats = new ArrayList<Long>();

			Enumeration<String> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String parameterName = enumeration.nextElement();
				if (parameterName.contains("habilitacioID_")) {
					log.info("Parameter Name - " + parameterName + ", Value - " + request.getParameter(parameterName));
					habilitacionsMarcats.add(Long.parseLong(request.getParameter(parameterName)));
				}
			}

			// Obtenir les habilitacions d'un funcionari
			List<LlocHabilitacio> anticsHabilitacions = llocHabilitacioEjb
					.select(LlocHabilitacioFields.LLOCID.equal(llocHabilitacioActual.getLlocID()));

			// Crea les noves habilitacions
			for (Long habilitacioId : habilitacionsMarcats) {
				if (habilitacioId != null) {
					LlocHabilitacioJPA llocHabilitacio = new LlocHabilitacioJPA();
					llocHabilitacio.setLlocID(llocHabilitacioActual.getLlocID());
					llocHabilitacio.setHabilitacioId((long) habilitacioId);
					llocHabilitacio.setDataCreacio(llocHabilitacioActual.getDataCreacio());
					llocHabilitacio = create(request, llocHabilitacio);
					llocHabilitacioForm.setLlocHabilitacio(llocHabilitacio);
				}
			}

			// Eliminar los roles anteriores una vez creados los nuevos
			for (LlocHabilitacio llocHabilitacio : anticsHabilitacions) {
				llocHabilitacioEjb.delete(llocHabilitacio.getLlocHabilitacioID());
			}

			log.info("------ END Crear LlocHabilitacioPost ----");

			createMessageSuccess(request, "success.generic", null);

			return getRedirectWhenCreated(request, llocHabilitacioForm);
		} catch (Throwable __e) {
			if (__e instanceof I18NValidationException) {
				ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException) __e);
				return getTileForm();
			}
			String msg = createMessageError(request, "error.creation", null, __e);
			log.error(msg, __e);
			return getTileForm();
		}
	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, LlocHabilitacioFilterForm filterForm,
			List<LlocHabilitacio> list) throws I18NException {

		Map<Long, String> mapHabilitacio = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();

		mapHabilitacio.clear();
		final String idioma = LoginInfo.getInstance().getLanguage();

		for (LlocHabilitacio llocHabilitacioItem : list) {

			final Long llocHabilitacioId = llocHabilitacioItem.getLlocHabilitacioID();
			String habilitacioDesc = habilitacioLogicaEJB.findByPrimaryKey(llocHabilitacioId).getNom()
					.getTraduccio(idioma)
					.getValor();
			mapHabilitacio.put(llocHabilitacioId, habilitacioDesc);

		}

	}
}
