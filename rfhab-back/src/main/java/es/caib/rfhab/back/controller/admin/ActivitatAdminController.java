package es.caib.rfhab.back.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.user.UserController;
import es.caib.rfhab.back.controller.webdb.ActivitatController;
import es.caib.rfhab.back.form.webdb.ActivitatFilterForm;
import es.caib.rfhab.back.form.webdb.ActivitatForm;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.ActivitatEstat;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.RegistreActivitatTipus;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.logic.ActivitatLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.ActivitatFields;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.FuncionariLlocQueryPath;
import es.caib.rfhab.model.fields.LlocFields;
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
	protected FuncionariLogicaService funcionariLogicaEjb;

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

		List<StringKeyValue> _tipusActivitatTemp = getTipusActivitats();
		Map<String, String> tipusActivitatFiltreCerca = Utils.listToMap(_tipusActivitatTemp);
		tipusActivitatFiltreCerca.put("", I18NUtils.tradueix("tots"));
		mav.addObject("tipusActivitatFiltreCerca", tipusActivitatFiltreCerca);
		log.info("tipusActivitatFiltreCerca: " + tipusActivitatFiltreCerca.size());
		log.info(tipusActivitatFiltreCerca);

		activitatFilterForm.setDeleteButtonVisible(false);
		activitatFilterForm.setVisibleMultipleSelection(false);
		activitatFilterForm.setDeleteSelectedButtonVisible(false);
		activitatFilterForm.setEditButtonVisible(false);
		activitatFilterForm.setViewButtonVisible(true);
		activitatFilterForm.setAddButtonVisible(false);

		activitatFilterForm.setAttachedAdditionalJspCode(true);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return activitatFilterForm;
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long activitatID) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCancel(request, activitatID));
	}

	@Override
	public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
			ModelAndView mav, ActivitatForm activitatForm, Where where) throws I18NException {
		if (activitatForm.isHiddenField(ActivitatFields.TIPUS)) {
			return EMPTY_STRINGKEYVALUE_LIST;
		}
		return getReferenceListForTipus(request, mav, where);
	}

	@Override
	public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
			ModelAndView mav, Where where) throws I18NException {
		return getTipusActivitats();
	}

	@Override
	public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
			ModelAndView mav, ActivitatForm activitatForm, Where where) throws I18NException {
		if (activitatForm.isHiddenField(ActivitatFields.ESTAT)) {
			return EMPTY_STRINGKEYVALUE_LIST;
		}
		return getReferenceListForEstat(request, mav, where);
	}

	@Override
	public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
			ModelAndView mav, Where where) throws I18NException {
		return getEstatsActivitats();
	}

	public List<StringKeyValue> getTipusActivitats() throws I18NException {
		List<StringKeyValue> tipusActivitatsResult = new ArrayList<StringKeyValue>();

		for (RegistreActivitatTipus rat : RegistreActivitatTipus.values()) {
			tipusActivitatsResult.add(new StringKeyValue(String.valueOf(rat.getValue()),
					rat.getDescripcio()));

		}

		return tipusActivitatsResult;
	}

	public List<StringKeyValue> getEstatsActivitats() throws I18NException {
		List<StringKeyValue> estatsActivitatsResult = new ArrayList<StringKeyValue>();

		for (ActivitatEstat ae : ActivitatEstat.values()) {
			estatsActivitatsResult.add(new StringKeyValue(String.valueOf(ae.getValue()),
					ae.name()));

		}

		return estatsActivitatsResult;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		final Where defaultCondition = super.getAdditionalCondition(request);

		Where funcionariNomCondition = null;
		try {
			funcionariNomCondition = getAdditionalConditionFuncionariNom(request);
		} catch (NoSuchFieldException | I18NException e) {
			e.printStackTrace();
			throw new I18NException(e, "activitat.filtre.funcionarisnom");
		}

		Where funcionariNifCondition = null;
		try {
			funcionariNifCondition = getAdditionalConditionFuncionariNif(request);
		} catch (NoSuchFieldException | I18NException e) {
			e.printStackTrace();
			throw new I18NException(e, "activitat.filtre.funcionarisnif");
		}

		return Where.AND(funcionariNomCondition, funcionariNifCondition, defaultCondition);
	}

	public Where getAdditionalConditionFuncionariNom(HttpServletRequest request)
			throws I18NException, NoSuchFieldException {
		final String funcionarisInput = (StringUtils.isNotEmpty(request.getParameter("activitatfuncionarisnom")))
				? request.getParameter("activitatfuncionarisnom")
				: "";
		log.info("funcionarisInput nom ==> " + funcionarisInput);
		return activitatEJB.getActivitatsByFuncionariNomCompletWhere(funcionarisInput);
	}

	public Where getAdditionalConditionFuncionariNif(HttpServletRequest request)
			throws I18NException, NoSuchFieldException {
		final String funcionarisInput = (StringUtils.isNotEmpty(request.getParameter("activitatfuncionarisnif")))
				? request.getParameter("activitatfuncionarisnif")
				: "";
		log.info("funcionarisInput nif ==> " + funcionarisInput);
		return activitatEJB.getActivitatsByFuncionariNifWhere(funcionarisInput);
	}

}
