package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;


import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.HistoricController;
import es.caib.rfhab.back.form.webdb.HistoricFilterForm;
import es.caib.rfhab.back.form.webdb.HistoricForm;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.HistoricJPA;

@Controller
@RequestMapping("/admin/historic")
@SessionAttributes(types = { HistoricForm.class, HistoricFilterForm.class })
public class HistoricAdminController extends HistoricController {

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	FuncionariLogicaService funcionariEjb;
	
	@Override
	public String getTileForm() {
		return "historicFormAdmin";
	}

	@Override
	public String getTileList() {
		return "historicListAdmin";
	}

	@Override
	public HistoricForm getHistoricForm(HistoricJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		HistoricForm historicForm = super.getHistoricForm(_jpa, __isView, request, mav);

		if (historicForm.isNou()) {
			historicForm.getHistoric().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			
			historicForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));
		}

		historicForm.addReadOnlyField(DATACREACIO);
		historicForm.addHiddenField(OBSERVACIONS);
		
		if (_jpa.getObservacions() != null) {
			HistoricFuncionariDAO oldFuncionari = funcionariEjb.fromJson(_jpa.getObservacions());
			mav.addObject("vell", oldFuncionari);
		}
		
		// Recuperam el actual funcionari per mostrar-lo a la vista i poder comparar-ho amb el historic
		List<Funcionari> actualFuncionari = funcionariEjb.select(FuncionariFields.FUNCIONARIID.equal(_jpa.getFuncionari().getFuncionariID()));
		mav.addObject("actual", actualFuncionari.get(0));
		
		historicForm.setAttachedAdditionalJspCode(true);
		
		return historicForm;

	}

	@Override
	public HistoricFilterForm getHistoricFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		HistoricFilterForm historicFilterForm = super.getHistoricFilterForm(pagina, mav, request);

		if (historicFilterForm.isNou()) {
			historicFilterForm.addHiddenField(HISTORICID);
			historicFilterForm.addHiddenField(OBSERVACIONS);

			historicFilterForm.setViewButtonVisible(true);
			historicFilterForm.setEditButtonVisible(false);
			historicFilterForm.setDeleteButtonVisible(false);
			historicFilterForm.setDeleteSelectedButtonVisible(false);
			historicFilterForm.setAddButtonVisible(false);

			historicFilterForm.setOrderBy(DATACREACIO.javaName);
			historicFilterForm.setOrderAsc(false);
			
			Long codiFuncionari = null;
			if (request.getSession() != null && request.getSession().getAttribute("FuncionariId") != null)
				codiFuncionari = (Long) request.getSession().getAttribute("FuncionariId");
			
			if (codiFuncionari != null && codiFuncionari > 0) {
				FuncionariJPA funcionari = funcionariEjb.findByPrimaryKey(codiFuncionari);
				historicFilterForm.addAdditionalButton( new AdditionalButton("fa fa-chevron-left", "historic.tornar", "/admin/funcionari/view/" + codiFuncionari, AdditionalButtonStyle.INFO));
				
			}

		}
		return historicFilterForm;

	}

	@RequestMapping(value = "/llistar/{funcionariId}", method = RequestMethod.GET)
	public String llistarHistoriPerFuncionariId(HttpServletRequest request,
			@PathVariable("funcionariId") Long funcionariId) {

		request.getSession().setAttribute("FuncionariId", funcionariId);

		return "redirect:/admin/historic/list";
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
