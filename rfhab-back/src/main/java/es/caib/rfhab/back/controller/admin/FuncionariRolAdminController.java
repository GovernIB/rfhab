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
import org.springframework.web.servlet.view.RedirectView;

import es.caib.rfhab.back.controller.webdb.FuncionariRolController;
import es.caib.rfhab.back.form.webdb.FuncionariRolFilterForm;
import es.caib.rfhab.back.form.webdb.FuncionariRolForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.ejb.FuncionariRolService;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.model.entity.FuncionariRol;
import es.caib.rfhab.model.fields.FuncionariRolFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.persistence.FuncionariRolJPA;
import es.caib.rfhab.persistence.RolJPA;

/**
 * @author jagarcia
 */

@Controller
@RequestMapping(value = "/admin/funcionarirol")
@SessionAttributes(types = { FuncionariRolForm.class, FuncionariRolFilterForm.class })
public class FuncionariRolAdminController extends FuncionariRolController {

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariEJB;

	@EJB(mappedName = FuncionariRolService.JNDI_NAME)
	protected FuncionariRolService funcionariRolEJB;

	@EJB(mappedName = RolService.JNDI_NAME)
	protected RolService rolEJB;

	@Override
	public String getTileForm() {
		return "funcionariRolFormAdmin";
	}

	@Override
	public String getTileList() {
		return "funcionariRolListAdmin";
	}

	@Override
	public FuncionariRolFilterForm getFuncionariRolFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		FuncionariRolFilterForm funcionariRolFilterForm = super.getFuncionariRolFilterForm(pagina, mav, request);

		if (funcionariRolFilterForm.isNou()) {
			funcionariRolFilterForm.addHiddenField(FUNCIONARIROLID);
			funcionariRolFilterForm.addHiddenField(DATACREACIO);

			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(RolFields.NOMID.codeLabel);
				adfield.setPosition(1);
				adfield.setOrderBy(RolFields.NOMID);
				adfield.setEscapeXml(false);
				adfield.setValueMap(new HashMap<Long, String>());
				funcionariRolFilterForm.addAdditionalField(adfield);
			}

			funcionariRolFilterForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.DARK));
		}

		return funcionariRolFilterForm;
	}

	@Override
	public FuncionariRolForm getFuncionariRolForm(FuncionariRolJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		FuncionariRolForm funcionariRolForm = super.getFuncionariRolForm(_jpa, __isView, request, mav);

		if (funcionariRolForm.isNou()) {
			funcionariRolForm.getFuncionariRol()
					.setFuncionariID((long) request.getSession().getAttribute("FuncionariId"));
			funcionariRolForm.getFuncionariRol().setDataCreacio(new Timestamp(System.currentTimeMillis()));

			funcionariRolForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.DARK));
		}

		funcionariRolForm.addReadOnlyField(FUNCIONARIID);
		funcionariRolForm.addHiddenField(DATACREACIO);
		
		// Obtenir els rols del funcionari i passar-los a la vista per marcar els checkboxs
		List<FuncionariRol> rolsFuncionari = funcionariRolEJB.select(FuncionariRolFields.FUNCIONARIID.equal(funcionariRolForm.getFuncionariRol().getFuncionariID()));
		mav.addObject("rolsFuncionari", rolsFuncionari);

		funcionariRolForm.setAttachedAdditionalJspCode(true);

		return funcionariRolForm;
	}

	@RequestMapping(value = "/assignar/{funcionariId}", method = RequestMethod.GET)
	public String assignarRol(HttpServletRequest request, @PathVariable("funcionariId") Long funcionariId) {

		log.info("Assignar rols a funcionariId => " + funcionariId);
		request.getSession().setAttribute("FuncionariId", funcionariId);
		return "redirect:/admin/funcionarirol/new";
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:/admin/funcionari/list/";
	}

	@RequestMapping(value = "/modificar/{funcionariId}", method = RequestMethod.GET)
	public ModelAndView modificarFuncionari(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long funcionariId) throws I18NException {

		request.getSession().setAttribute("FuncionariId", funcionariId);
		return llistatPaginat(request, response, 1);
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Long funcionariId = (Long) request.getSession().getAttribute("FuncionariId");
		Where w = null;
		if (funcionariId != null) {
			w = FuncionariRolFields.FUNCIONARIID.equal(funcionariId);
		}
		return w;
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, FuncionariRolForm funcionariRolForm) {
		return "redirect:/admin/funcionari/list/";
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, FuncionariRolForm funcionariRolForm,
			Throwable __e) {
		return "redirect:/admin/funcionari/list/";
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionariRolID, Throwable __e) {
		return "redirect:/admin/funcionari/list/";
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long funcionariRolID) {
		return "redirect:/admin/funcionari/list/";
	}

	@Override
	public String crearFuncionariRolPost(@ModelAttribute FuncionariRolForm funcionariRolForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!isActiveFormNew()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		try {
			
			log.info("------ START Crear FuncionariRolPost ----");
			
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
			List<FuncionariRol> anticsRols = funcionariRolEjb.select(FuncionariRolFields.FUNCIONARIID.equal(funcionariRolForm.getFuncionariRol().getFuncionariID()));
			
			// Crear los nuevos roles
			for (Long rolId : rolsMarcats) {
				FuncionariRolJPA funcionariRol = new FuncionariRolJPA();
				funcionariRol.setFuncionariID(funcionariRolForm.getFuncionariRol().getFuncionariID());
				funcionariRol.setRolID(rolId);
				funcionariRol.setDataCreacio(funcionariRolForm.getFuncionariRol().getDataCreacio());
				funcionariRol = create(request, funcionariRol);
				funcionariRolForm.setFuncionariRol(funcionariRol);
			}
			
			// Eliminar los roles anteriores una vez creados los nuevos
			for (FuncionariRol funcionariRol : anticsRols) {
                funcionariRolEjb.delete(funcionariRol.getFuncionariRolID());
            }

			log.info("------ END Crear FuncionariRolPost ----");
			
			createMessageSuccess(request, "success.generic", null);
			
			return getRedirectWhenCreated(request, funcionariRolForm);
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
	public void postList(HttpServletRequest request, ModelAndView mav, FuncionariRolFilterForm filterForm,
			List<FuncionariRol> list) throws I18NException {

		Map<Long, String> mapRol = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();

		mapRol.clear();
		final String idioma = LoginInfo.getInstance().getLanguage();

		for (FuncionariRol funcionariRolItem : list) {

			final Long funcionariRolId = funcionariRolItem.getFuncionariRolID();
			String rolDesc = rolEJB.findByPrimaryKey(funcionariRolId).getNom().getTraduccio(idioma).getValor();
			mapRol.put(funcionariRolId, rolDesc);

		}

	}

}