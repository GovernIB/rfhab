package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.HabilitacioController;
import es.caib.rfhab.back.form.webdb.HabilitacioFilterForm;
import es.caib.rfhab.back.form.webdb.HabilitacioForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.logic.LlocHabilitacioLogicaService;
import es.caib.rfhab.model.entity.Habilitacio;
import es.caib.rfhab.model.entity.LlocHabilitacio;
import es.caib.rfhab.model.fields.HabilitacioFields;
import es.caib.rfhab.model.fields.LlocHabilitacioFields;
import es.caib.rfhab.persistence.HabilitacioJPA;

/**
 * 
 * @author jagarcia
 * @author jpou
 * 
 */
@Controller
@RequestMapping(value = HabilitacioAdminController.CONTEXTWEB)
@SessionAttributes(types = { HabilitacioForm.class, HabilitacioFilterForm.class })
public class HabilitacioAdminController extends HabilitacioController {

	public static final String CONTEXTWEB = "/admin/habilitacio";

	@EJB(mappedName = LlocHabilitacioLogicaService.JNDI_NAME)
	protected LlocHabilitacioLogicaService llocHabilitacioEJB;

	@Override
	public String getTileForm() {
		return "habilitacioFormAdmin";
	}

	@Override
	public String getTileList() {
		return "habilitacioListAdmin";
	}

	@Override
	public HabilitacioFilterForm getHabilitacioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		HabilitacioFilterForm habilitacioFilterForm = super.getHabilitacioFilterForm(pagina, mav, request);

		if (habilitacioFilterForm.isNou()) {
			habilitacioFilterForm.addHiddenField(HABILITACIOID);
			habilitacioFilterForm.addHiddenField(DATACREACIO);
			habilitacioFilterForm.addHiddenField(ENTITATID);
		}

		habilitacioFilterForm.setVisibleMultipleSelection(false);

		return habilitacioFilterForm;
	}

	@Override
	public HabilitacioForm getHabilitacioForm(HabilitacioJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav)
			throws I18NException {

		HabilitacioForm habilitacioForm = super.getHabilitacioForm(_jpa, __isView, request, mav);

		if (habilitacioForm.isNou()) {
			habilitacioForm.addHiddenField(HABILITACIOID);
			habilitacioForm.addHiddenField(ENTITATID);
			habilitacioForm.getHabilitacio().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			habilitacioForm.getHabilitacio().setEntitatID(LoginInfo.getInstance().getEntitatIDActual());
		}

		habilitacioForm.setAttachedAdditionalJspCode(true);
		habilitacioForm.addReadOnlyField(DATACREACIO);

		return habilitacioForm;
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
			w1 = HabilitacioFields.ENTITATID.equal(loginInfo.getEntitatIDActual());
		}

		return (w1 != null) ? Where.AND(defaultCondition, w1) : defaultCondition;
	}

	@Override
	public void delete(HttpServletRequest request, Habilitacio habilitacio) throws I18NException {

		long habilitacioID = habilitacio.getHabilitacioID();
		List<LlocHabilitacio> asignacionsTrobades = llocHabilitacioEJB
				.select(LlocHabilitacioFields.HABILITACIOID.equal(habilitacioID));

		if (asignacionsTrobades != null && asignacionsTrobades.size() > 0) {
			// String __msg = createMessageError(request,
			// "habilitacio.admin.delete.error.assignacions", habilitacioID);
			throw new I18NException("habilitacio.admin.delete.error.assignacions");
		}

		super.delete(request, habilitacio);
	}

}