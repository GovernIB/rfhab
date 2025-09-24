package es.caib.rfhab.back.controller.user;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.ActivitatController;
import es.caib.rfhab.back.form.webdb.ActivitatFilterForm;
import es.caib.rfhab.back.form.webdb.ActivitatForm;
import es.caib.rfhab.persistence.ActivitatJPA;

@Controller
@RequestMapping(ActivitatUserController.CONTEXTWEB)
@SessionAttributes(types = { ActivitatForm.class, ActivitatFilterForm.class })
public class ActivitatUserController extends ActivitatController {

	public static final String CONTEXTWEB = "/user/activitat/";

	@Override
	public String getTileForm() {
		return "activitatFormUser";
	}

	@Override
	public String getTileList() {
		return "activitatListUser";
	}

	@Override
	public ActivitatFilterForm getActivitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		ActivitatFilterForm activitatFilterForm = super.getActivitatFilterForm(pagina, mav, request);

		if (activitatFilterForm.isNou()) {
			activitatFilterForm.addHiddenField(ACTIVITATID);
			activitatFilterForm.addHiddenField(FUNCIONARIID);

			activitatFilterForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));
		}

		activitatFilterForm.setViewButtonVisible(true);
		activitatFilterForm.setDeleteButtonVisible(false);
		activitatFilterForm.setEditButtonVisible(false);
		activitatFilterForm.setDeleteSelectedButtonVisible(false);

		return activitatFilterForm;

	}

	@Override
	public ActivitatForm getActivitatForm(ActivitatJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		ActivitatForm activitatForm = super.getActivitatForm(_jpa, __isView, request, mav);

		if (activitatForm.isNou()) {
			activitatForm.getActivitat().setDataCreacio(new Timestamp(System.currentTimeMillis()));

			activitatForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));
		}

		activitatForm.addReadOnlyField(DATACREACIO);

		return activitatForm;

	}

	@Override
	public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue("0", I18NUtils.tradueix("tipusregistre.0")));
		__tmp.add(new StringKeyValue("1", I18NUtils.tradueix("tipusregistre.1")));
		__tmp.add(new StringKeyValue("2", I18NUtils.tradueix("tipusregistre.2")));
		return __tmp;
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:/admin/funcionari/list/1";
	}
}
