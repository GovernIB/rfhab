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

import es.caib.rfhab.back.controller.webdb.LlocRolController;
import es.caib.rfhab.back.form.webdb.LlocRolFilterForm;
import es.caib.rfhab.back.form.webdb.LlocRolForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.ejb.LlocRolService;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.model.entity.LlocRol;
import es.caib.rfhab.model.fields.LlocRolFields;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.persistence.LlocRolJPA;

@Controller
@RequestMapping(value = LlocRolAdminController.CONTEXTWEB)
@SessionAttributes(types = { LlocRolForm.class, LlocRolFilterForm.class })
public class LlocRolAdminController extends LlocRolController {

	public static final String CONTEXTWEB = "/admin/llochabilitacio";

	protected final Logger log = Logger.getLogger(getClass());

	@EJB(mappedName = LlocRolService.JNDI_NAME)
	protected LlocRolService llocRolEJB;

	@EJB(mappedName = RolService.JNDI_NAME)
	protected RolService rolEJB;

	@Override
	public String getTileForm() {
		return "llocRolFormAdmin";
	}

	@Override
	public String getTileList() {
		return "llocRolListAdmin";
	}

	@Override
	public LlocRolFilterForm getLlocRolFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		LlocRolFilterForm llocRolFilterForm = super.getLlocRolFilterForm(pagina, mav, request);

		if (llocRolFilterForm.isNou()) {
			llocRolFilterForm.addHiddenField(LLOCROLID);
			llocRolFilterForm.addHiddenField(DATACREACIO);

			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(RolFields.NOMID.codeLabel);
				adfield.setPosition(1);
				adfield.setOrderBy(RolFields.NOMID);
				adfield.setEscapeXml(false);
				adfield.setValueMap(new HashMap<Long, String>());
				llocRolFilterForm.addAdditionalField(adfield);
			}

			llocRolFilterForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.DARK));
		}

		return llocRolFilterForm;
	}

	@Override
	public LlocRolForm getLlocRolForm(LlocRolJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		LlocRolForm llocRolForm = super.getLlocRolForm(_jpa, __isView, request, mav);

		if (llocRolForm.isNou()) {
			llocRolForm.getLlocRol()
					.setLlocID((long) request.getSession().getAttribute("llocId"));
			llocRolForm.getLlocRol().setDataCreacio(new Timestamp(System.currentTimeMillis()));

			llocRolForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.DARK));
		}

		llocRolForm.addReadOnlyField(LLOCID);
		llocRolForm.addHiddenField(DATACREACIO);

		// Obtenir els rols del funcionari i passar-los a la vista per marcar els
		// checkboxs
		List<LlocRol> rolsLloc = llocRolEJB.select(LlocRolFields.LLOCID.equal(llocRolForm.getLlocRol().getLlocID()));
		mav.addObject("rolsLloc", rolsLloc);

		llocRolForm.setAttachedAdditionalJspCode(true);

		return llocRolForm;
	}

	@RequestMapping(value = "/assignar/{llocId}", method = RequestMethod.GET)
	public String assignarRol(HttpServletRequest request, @PathVariable("llocId") Long llocId) {

		log.info("Assignar rols a llocId => " + llocId);
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
			w = LlocRolFields.LLOCID.equal(llocId);
		}
		return w;
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, LlocRolForm llocRolForm) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, LlocRolForm llocRolForm,
			Throwable __e) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long llocRolID, Throwable __e) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long llocRolID) {
		return "redirect:/admin/lloc/list/";
	}

	@Override
	public String crearLlocRolPost(@ModelAttribute LlocRolForm llocRolForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!isActiveFormNew()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		try {

			log.info("------ START Crear LlocRolPost ----");

			ArrayList<Long> rolsMarcats = new ArrayList<Long>();

			Enumeration<String> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String parameterName = enumeration.nextElement();
				if (parameterName.contains("rolID_")) {
					log.info("Parameter Name - " + parameterName + ", Value - " + request.getParameter(parameterName));
					rolsMarcats.add(Long.parseLong(request.getParameter(parameterName)));
				}
			}

			// Obtener los roles de un funcionario
			List<LlocRol> anticsRols = llocRolEjb
					.select(LlocRolFields.LLOCID.equal(llocRolForm.getLlocRol().getLlocID()));

			// Crear los nuevos roles
			for (Long rolId : rolsMarcats) {
				LlocRolJPA llocRol = new LlocRolJPA();
				llocRol.setLlocID(llocRolForm.getLlocRol().getLlocID());
				llocRol.setRolID(rolId);
				llocRol.setDataCreacio(llocRolForm.getLlocRol().getDataCreacio());
				llocRol = create(request, llocRol);
				llocRolForm.setLlocRol(llocRol);
			}

			// Eliminar los roles anteriores una vez creados los nuevos
			for (LlocRol llocRol : anticsRols) {
				llocRolEjb.delete(llocRol.getLlocRolID());
			}

			log.info("------ END Crear LlocRolPost ----");

			createMessageSuccess(request, "success.generic", null);

			return getRedirectWhenCreated(request, llocRolForm);
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
	public void postList(HttpServletRequest request, ModelAndView mav, LlocRolFilterForm filterForm,
			List<LlocRol> list) throws I18NException {

		Map<Long, String> mapRol = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();

		mapRol.clear();
		final String idioma = LoginInfo.getInstance().getLanguage();

		for (LlocRol llocRolItem : list) {

			final Long llocRolId = llocRolItem.getLlocRolID();
			String rolDesc = rolEJB.findByPrimaryKey(llocRolId).getNom().getTraduccio(idioma).getValor();
			mapRol.put(llocRolId, rolDesc);

		}

	}
}
