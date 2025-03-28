package es.caib.rfhab.back.controller.superadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.EntitatController;
import es.caib.rfhab.back.form.webdb.EntitatFilterForm;
import es.caib.rfhab.back.form.webdb.EntitatForm;

@Controller
@RequestMapping(value = "/superadmin/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatSuperAdminController extends EntitatController {

	@Override
	public String getTileForm() {
		return "entitatFormSuperAdmin";
	}

	@Override
	public String getTileList() {
		return "entitatListSuperAdmin";
	}

	@Override
	public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		EntitatFilterForm entitatFilterForm = super.getEntitatFilterForm(pagina, mav, request);

		if (entitatFilterForm.isNou()) {

			entitatFilterForm
					.addAdditionalButtonForEachItem(new AdditionalButton("fa fa-user-plus", "usuari.assignarusuari",
							"/superadmin/usuariEntitat/assignar/{0}", AdditionalButtonStyle.SECONDARY));
		}

		entitatFilterForm.setAttachedAdditionalJspCode(true);

		return entitatFilterForm;

	}

}
