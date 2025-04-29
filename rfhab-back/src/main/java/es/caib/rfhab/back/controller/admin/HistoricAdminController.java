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

import com.fasterxml.jackson.core.JsonParseException;

import es.caib.rfhab.back.controller.webdb.HistoricController;
import es.caib.rfhab.back.form.webdb.HistoricFilterForm;
import es.caib.rfhab.back.form.webdb.HistoricForm;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.utils.DbDaoDictionaries;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.logic.utils.JsonUtils;
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

		HistoricJPA historicJPA = historicForm.getHistoric();
		if (historicJPA.getObservacions() != null) {
			try {
				List<HistoricFuncionariDAO> historicLloc = JsonUtils
						.listFromJson(historicJPA.getObservacions(), HistoricFuncionariDAO[].class);
				HistoricFuncionariDAO oldHistoric = historicLloc.get(0);
				log.info("oldHistoric: " + oldHistoric.toString());
				mav.addObject("vell", oldHistoric);
				HistoricFuncionariDAO nouHistoric = historicLloc.get(1);
				log.info("nouHistoric: " + nouHistoric.toString());
				mav.addObject("nou", nouHistoric);
				mav.addObject("diferenciesDictionary", DbDaoDictionaries.HistoricFuncionari);
			} catch (JsonParseException | IndexOutOfBoundsException e) {
				mav.addObject("observacions", historicJPA.getObservacions());
			} catch (Exception e) {
				log.error("Error al parsejar el JSON de les observacions", e);
				throw new I18NException(e, "historiclloc.error.parsejason");
			}
		}

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
				historicFilterForm.addAdditionalButton(new AdditionalButton("fa fa-chevron-left", "historic.tornar",
						"/admin/funcionari/view/" + codiFuncionari, AdditionalButtonStyle.INFO));
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
