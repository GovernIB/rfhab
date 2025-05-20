package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.RolController;
import es.caib.rfhab.back.form.webdb.RolFilterForm;
import es.caib.rfhab.back.form.webdb.RolForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.persistence.RolJPA;

/**
 * 
 * @author jagarcia
 * @author jpou
 * 
 */
@Controller
@RequestMapping(value = RolAdminController.CONTEXTWEB)
@SessionAttributes(types = { RolForm.class, RolFilterForm.class })
public class RolAdminController extends RolController {

	public static final String CONTEXTWEB = "/admin/habilitacio";

	@Override
	public String getTileForm() {
		return "rolFormAdmin";
	}

	@Override
	public String getTileList() {
		return "rolListAdmin";
	}

	@Override
	public RolFilterForm getRolFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		RolFilterForm rolFilterForm = super.getRolFilterForm(pagina, mav, request);
		
		if (rolFilterForm.isNou()) {
			rolFilterForm.addHiddenField(ROLID);
			rolFilterForm.addHiddenField(DATACREACIO);
			rolFilterForm.addHiddenField(ENTITATID);
		}
		
		rolFilterForm.setVisibleMultipleSelection(false);
		
		return rolFilterForm;
	}

	@Override
	public RolForm getRolForm(RolJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
	
		RolForm rolForm = super.getRolForm(_jpa, __isView, request, mav);
		
		if (rolForm.isNou()) {
			rolForm.addHiddenField(ROLID);
			rolForm.addHiddenField(ENTITATID);
			rolForm.getRol().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			rolForm.getRol().setEntitatID(LoginInfo.getInstance().getEntitatIDActual());
		}

		rolForm.setAttachedAdditionalJspCode(true);
		rolForm.addReadOnlyField(DATACREACIO);
		
		return rolForm;
	}
	
	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
		
		final Where defaultCondition = super.getAdditionalCondition(request);
		
		// filtrar per entitat
		LoginInfo loginInfo = LoginInfo.getInstance();
		
		System.out.println("================================================");
		System.out.println("ENTITAT ACTUAL: => " + loginInfo.getEntitatIDActual());
		System.out.println("ENTITAT ID ACTUAL: => " + loginInfo.getEntitatID());
		System.out.println("================================================");
		
		Where w1 = null; 
		if (loginInfo.getEntitatIDActual() != null && loginInfo.getEntitatIDActual() > 0) {
			w1 = RolFields.ENTITATID.equal(loginInfo.getEntitatIDActual());
		}
		
		return (w1 != null) ? Where.AND(defaultCondition, w1) : defaultCondition;
	}

}