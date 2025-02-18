package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.FuncionariLlocController;
import es.caib.rfhab.back.form.webdb.FuncionariLlocFilterForm;
import es.caib.rfhab.back.form.webdb.FuncionariLlocForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.ejb.HistoricLlocService;
import es.caib.rfhab.persistence.FuncionariLlocJPA;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/**
 * @author jagarcia
 */

@Controller
@RequestMapping(value = "/admin/funcionarilloc")
@SessionAttributes(types = { FuncionariLlocForm.class, FuncionariLlocFilterForm.class })
public class FuncionariLlocAdminController extends FuncionariLlocController {

	@EJB(mappedName = HistoricLlocService.JNDI_NAME)
	protected HistoricLlocService historicLlocEjb;
	
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
		}

		return funcionariLlocFilterForm;
	}

	@Override
	public FuncionariLlocForm getFuncionariLlocForm(FuncionariLlocJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {

		FuncionariLlocForm funcionariLlocForm = super.getFuncionariLlocForm(_jpa, __isView, request, mav);

		if (funcionariLlocForm.isNou()) {

			if (request.getSession() != null && request.getSession().getAttribute("LlocId") != null) {
				funcionariLlocForm.getFuncionariLloc().setLlocID((long) request.getSession().getAttribute("LlocId"));
				funcionariLlocForm.addReadOnlyField(LLOCID);
			}

			if (request.getSession() != null && request.getSession().getAttribute("FuncionariId") != null) {
				funcionariLlocForm.getFuncionariLloc()
						.setFuncionariID((long) request.getSession().getAttribute("FuncionariId"));
				funcionariLlocForm.addReadOnlyField(FUNCIONARIID);
			}

			funcionariLlocForm.getFuncionariLloc().setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
			funcionariLlocForm.getFuncionariLloc().setDataCreacio(new Timestamp(System.currentTimeMillis()));

		}

		funcionariLlocForm.addHiddenField(USUARIID);
		funcionariLlocForm.addReadOnlyField(DATACREACIO);

		return funcionariLlocForm;
	}

	@Override
	public FuncionariLlocJPA create(HttpServletRequest request, FuncionariLlocJPA funcionariLloc)
			throws I18NException, I18NValidationException {
		
		FuncionariLlocJPA funcionariLlocJPA = super.create(request, funcionariLloc);
		
		// Guardar imatge del canvi a historic de Lloc i historic de funcionari
		
		HistoricLlocJPA historicLloc = new HistoricLlocJPA();
		historicLloc.setLlocID(funcionariLloc.getLlocID());
		historicLloc.setNumeroCai(request.getParameter("numeroCai"));
		historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historicLloc.setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
		historicLloc.setObservacions("Nova assignació de funcionari a lloc: " + funcionariLloc.getFuncionariID() + " - " + funcionariLloc.getLlocID());
		historicLlocEjb.create(historicLloc);
		
		return funcionariLlocJPA;
	}

	@RequestMapping(value = "/assignarfuncionari/{funcionariId}", method = RequestMethod.GET)
	public String assignarFuncionariLloc(HttpServletRequest request, @PathVariable("funcionariId") Long funcionariId) {
		request.getSession().setAttribute("FuncionariId", funcionariId);
		return "redirect:/admin/funcionarilloc/new";
	}

	@RequestMapping(value = "/assignar/{llocId}", method = RequestMethod.GET)
	public String assignarFuncionari(HttpServletRequest request, @PathVariable("llocId") Long llocId) {

		request.getSession().setAttribute("LlocId", llocId);

		return "redirect:/admin/funcionarilloc/new";
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:/admin/funcionari/list/1";
	}
	
	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long historicID) {
        return "redirect:/admin/funcionari/list/1";
    }

}