package es.caib.rfhab.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.ActivitatController;
import es.caib.rfhab.back.form.webdb.ActivitatFilterForm;
import es.caib.rfhab.back.form.webdb.ActivitatForm;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.logic.ActivitatLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.model.fields.ActivitatFields;
import es.caib.rfhab.persistence.ActivitatJPA;

/*
 * 
 * @author jpou
 * 
 */
@Controller
@RequestMapping(value = "/admin/activitat")
@SessionAttributes(types = { ActivitatForm.class, ActivitatFilterForm.class })
public class ActivitatAdminController extends ActivitatController {

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariEJB;

	@EJB(mappedName = UnitatLogicaService.JNDI_NAME)
	protected UnitatLogicaService unitatEJB;

	@EJB(mappedName = ActivitatLogicaService.JNDI_NAME)
	protected ActivitatLogicaService activitatEJB;

	@Override
	public String getTileForm() {
		return "activitatFormAdmin";
	}

	@Override
	public String getTileList() {
		return "activitatListAdmin";
	}

	@Override
	public boolean isActiveFormNew() {
		return false;
	}

	@Override
	public boolean isActiveFormEdit() {
		return false;
	}

	@Override
	public boolean isActiveDelete() {
		return false;
	}

	@Override
	public boolean isActiveFormView() {
		return true;
	}

	@Override
	public ActivitatForm getActivitatForm(ActivitatJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		ActivitatForm activitatForm = super.getActivitatForm(_jpa, __isView, request, mav);
		ActivitatJPA activitat = activitatForm.getActivitat();

		activitatForm.setDeleteButtonVisible(false);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return activitatForm;
	}

	@Override
	public ActivitatFilterForm getActivitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		ActivitatFilterForm activitatFilterForm = super.getActivitatFilterForm(pagina, mav, request);

		if (activitatFilterForm.isNou()) {

			activitatFilterForm.addHiddenField(ActivitatFields.ACTIVITATID);
			activitatFilterForm.addHiddenField(ActivitatFields.REGISTRE);
			activitatFilterForm.addHiddenField(ActivitatFields.CODISIA);
			activitatFilterForm.addHiddenField(ActivitatFields.AUTORITZACIOID);
			activitatFilterForm.addHiddenField(ActivitatFields.INTERESSATNOM);
			activitatFilterForm.addHiddenField(ActivitatFields.INTERESSATLLINATGE1);
			activitatFilterForm.addHiddenField(ActivitatFields.INTERESSATLLINATGE2);
			activitatFilterForm.addHiddenField(ActivitatFields.INTERESSATTIPUS);
			// activitatFilterForm.addHiddenField(ActivitatFields.INTERESSATIDENTIFICACIO);
			activitatFilterForm.addHiddenField(ActivitatFields.REPRESENTANTNOM);
			activitatFilterForm.addHiddenField(ActivitatFields.REPRESENTANTLLINATGE1);
			activitatFilterForm.addHiddenField(ActivitatFields.REPRESENTANTLLINATGE2);
			activitatFilterForm.addHiddenField(ActivitatFields.REPRESENTANTTIPUS);
			activitatFilterForm.addHiddenField(ActivitatFields.REPRESENTANTIDENTIFICACIO);
			activitatFilterForm.addHiddenField(ActivitatFields.ARXIUDOCUMENTID);
			activitatFilterForm.addHiddenField(ActivitatFields.ARXIUEXPEDIENTID);
			activitatFilterForm.addHiddenField(ActivitatFields.URL);
			activitatFilterForm.addHiddenField(ActivitatFields.IDACTUACIOTRAMIT);

			activitatFilterForm.addHiddenField(ActivitatFields.TRAMIT);
			activitatFilterForm.addHiddenField(ActivitatFields.TRAMITVERSIO);
			activitatFilterForm.addHiddenField(ActivitatFields.DATACREACIO);

			activitatFilterForm.setOrderBy(ActivitatFields.DATAACTIVITAT.sqlName);
			activitatFilterForm.setOrderAsc(false);
		}

		activitatFilterForm.setDeleteButtonVisible(false);
		activitatFilterForm.setVisibleMultipleSelection(false);
		activitatFilterForm.setDeleteSelectedButtonVisible(false);
		activitatFilterForm.setEditButtonVisible(false);
		activitatFilterForm.setViewButtonVisible(true);
		activitatFilterForm.setAddButtonVisible(false);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return activitatFilterForm;
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long activitatID) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCancel(request, activitatID));
	}

}
