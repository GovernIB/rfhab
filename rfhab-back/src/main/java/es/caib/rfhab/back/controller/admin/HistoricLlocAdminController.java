package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.HistoricLlocController;
import es.caib.rfhab.back.form.webdb.HistoricLlocFilterForm;
import es.caib.rfhab.back.form.webdb.HistoricLlocForm;
import es.caib.rfhab.back.form.webdb.LlocRefList;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;

/**
 * @author jagarcia
 */

@Controller
@RequestMapping(value = "/admin/historiclloc")
@SessionAttributes(types = { HistoricLlocForm.class, HistoricLlocFilterForm.class })
public class HistoricLlocAdminController extends HistoricLlocController {

	protected final Logger log = Logger.getLogger(getClass());
	
	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	LlocLogicaService llocEjb; 
	
	@Override
	public String getTileForm() {
		return "historicLlocFormAdmin";
	}

	@Override
	public String getTileList() {
		return "historicLlocListAdmin";
	}

	@PostConstruct
	public void init() {

		this.llocRefList = new LlocRefList(llocRefList);

		this.llocRefList.setSelects(new Select<?>[] { LlocFields.NOM.select, new SelectConstant(" ("),
				LlocFields.CODILLOC.select, new SelectConstant(")") });

		this.llocRefList.setSeparator("");

	}

	@Override
	public HistoricLlocFilterForm getHistoricLlocFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		HistoricLlocFilterForm historicLlocFilterForm = super.getHistoricLlocFilterForm(pagina, mav, request);

		if (historicLlocFilterForm.isNou()) {
			historicLlocFilterForm.addHiddenField(HISTORICLLOCID);
			historicLlocFilterForm.addHiddenField(OBSERVACIONS);
			
			historicLlocFilterForm.setViewButtonVisible(true);
			historicLlocFilterForm.setEditButtonVisible(false);
			historicLlocFilterForm.setDeleteButtonVisible(false);
			historicLlocFilterForm.setDeleteSelectedButtonVisible(false);
			historicLlocFilterForm.setAddButtonVisible(false);

			historicLlocFilterForm.setOrderBy(DATACREACIO.javaName);
			historicLlocFilterForm.setOrderAsc(false);
			
			Long codigoLugar = (Long) request.getSession().getAttribute("LlocId");
			if(codigoLugar != null && codigoLugar > 0) {
				LlocJPA plaza = llocEjb.findByPrimaryKey(codigoLugar);
				historicLlocFilterForm.setTitleCode("historiclloc.customTitol");
				historicLlocFilterForm.setTitleParam(plaza.getNom() + " (" + plaza.getCodiLloc() + ")");
				
				historicLlocFilterForm.addAdditionalButton(new AdditionalButton("fa fa-chevron-left", "historiclloc.tornar", "/admin/lloc/view/" + codigoLugar, AdditionalButtonStyle.INFO)); 
			}
			
		}

		return historicLlocFilterForm;
	}

	@Override
	public HistoricLlocForm getHistoricLlocForm(HistoricLlocJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		HistoricLlocForm historicLlocForm = super.getHistoricLlocForm(_jpa, __isView, request, mav);

		if (historicLlocForm.isNou()) {
			historicLlocForm.getHistoricLloc().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			
			historicLlocForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));
		}
		
		historicLlocForm.addReadOnlyField(DATACREACIO);
		historicLlocForm.addHiddenField(OBSERVACIONS);
		
		if (_jpa.getObservacions() != null) {
			HistoricLlocDAO oldLloc = llocEjb.fromJson(_jpa.getObservacions());
			mav.addObject("vell", oldLloc);
		}
		
		List<Lloc> actualLloc = llocEjb.select(LlocFields.LLOCID.equal(_jpa.getLloc().getLlocID()));
		mav.addObject("actual", actualLloc.get(0));
		
		historicLlocForm.setAttachedAdditionalJspCode(true);
		
		return historicLlocForm;
	}
	
	
	@RequestMapping(value= "/llistar/{llocId}", method = RequestMethod.GET)
	public String llistarHistoricPerLlocId(HttpServletRequest request, @PathVariable("llocId") Long llocId) {

		request.getSession().setAttribute("LlocId", llocId);
		
		return "redirect:/admin/historiclloc/list";
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
