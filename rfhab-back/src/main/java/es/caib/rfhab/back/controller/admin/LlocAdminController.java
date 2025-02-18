package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.LlocController;
import es.caib.rfhab.back.form.webdb.LlocFilterForm;
import es.caib.rfhab.back.form.webdb.LlocForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.ejb.FuncionariLlocService;
import es.caib.rfhab.logic.HistoricLlocLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;

/**
 * @author jagarcia
 */

@Controller
@RequestMapping(value = "/admin/lloc")
@SessionAttributes(types = { LlocForm.class, LlocFilterForm.class })
public class LlocAdminController extends LlocController {

	protected final Logger log = Logger.getLogger(getClass());

	@EJB(mappedName = HistoricLlocLogicaService.JNDI_NAME)
	protected HistoricLlocLogicaService historicLlocEjb;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocLogicaEjb;

	@EJB(mappedName = FuncionariLlocService.JNDI_NAME)
	protected FuncionariLlocService funcionariLlocEjb;

	@Override
	public String getTileForm() {
		return "llocFormAdmin";
	}

	@Override
	public String getTileList() {
		return "llocListAdmin";
	}

	@Override
	public LlocFilterForm getLlocFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		LlocFilterForm llocFilterForm = super.getLlocFilterForm(pagina, mav, request);

		if (llocFilterForm.isNou()) {
			llocFilterForm.addHiddenField(LLOCID);
			llocFilterForm.addHiddenField(DATACREACIO);
			llocFilterForm.addHiddenField(ENTITATID);
			llocFilterForm.addHiddenField(OBSERVACIONS);
			llocFilterForm.addHiddenField(DATABAIXA);
			
			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(FuncionariFields.NOM.codeLabel);
				adfield.setPosition(1);
				adfield.setEscapeXml(false);
				adfield.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield);
			}

			llocFilterForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
					getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));
		}
		
		llocFilterForm.setDeleteSelectedButtonVisible(false);
		llocFilterForm.setVisibleMultipleSelection(false);
				
		llocFilterForm.setAttachedAdditionalJspCode(true);

		return llocFilterForm;
	}

	@Override
	public LlocForm getLlocForm(LlocJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		
		mav.addObject("isView", __isView);

		LlocForm llocForm = super.getLlocForm(_jpa, __isView, request, mav);

		if (llocForm.isNou()) {
			
			mav.addObject("isNew", llocForm.isNou());
			
			llocForm.getLloc().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			llocForm.getLloc().setEntitatID(LoginInfo.getInstance().getEntitatIDActual());
			
			Long entitatId = LoginInfo.getInstance().getUsuariPersona().getDarreraEntitat();
			llocForm.getLloc().setEntitatID(entitatId);
			
			mav.addObject("historic", new ArrayList<HistoricLloc>());
			
		}else {
			
			List<Select6Values<Long, String, String, String, String, Timestamp>> historic = historicLlocEjb.getHistoricByLlocId(_jpa.getLlocID());
			log.info("HistoricLloc.size: " + historic.size());

			historic.forEach( x -> 
	            log.info("HistoricLloc: " + x.getValue1() + " " + x.getValue2() + " " + x.getValue3() + " " + x.getValue4() + " " + x.getValue5() + " " + x.getValue6()));
	        
			mav.addObject("historic", historic);
		}
		
		llocForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
				getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));

		llocForm.addHiddenField(ENTITATID);
		llocForm.addReadOnlyField(DATACREACIO);

		llocForm.setCancelButtonVisible(false);
		llocForm.setAttachedAdditionalJspCode(true);

		return llocForm;
	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, LlocFilterForm filterForm, List<Lloc> list)
			throws I18NException {

		Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

		Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.isNull());

		Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());
		
		Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

		Where w = Where.OR(w1, w2, w3, w4);

		List<Long> llocsOcupats = funcionariLlocEjb.executeQuery(FuncionariLlocFields.LLOCID, w);

		filterForm.getAdditionalButtonsByPK().clear();
		
		
		Map<Long, String> mapFuncionari = (Map<Long,String>) filterForm.getAdditionalField(1).getValueMap();
		mapFuncionari.clear();
		
		HashMap<Long, Funcionari> llistaFuncionarisActius = llocLogicaEjb.getCurrentFuncionarisByLloc(0L);

		for (Lloc lloc : list) {
			if (!llocsOcupats.contains(lloc.getLlocID())) {
				filterForm.addAdditionalButtonByPK(lloc.getLlocID(),
						new AdditionalButton("fa fa-user-plus", "lloc.assignarfuncionari",
								"/admin/funcionarilloc/assignar/{0}", AdditionalButtonStyle.SECONDARY));
			}else {
				
				Funcionari f = llistaFuncionarisActius.get(lloc.getLlocID());
				String nom = f.getNom() + " " + f.getLlinatge1() + " " + f.getLlinatge2() + " (" + f.getUsuari() + ")";
				mapFuncionari.put(lloc.getLlocID(), nom);
			}
		}
		
	}

	@Override
	public LlocJPA create(HttpServletRequest request, LlocJPA lloc) throws I18NException, I18NValidationException {

		LlocJPA newLloc = super.create(request, lloc);

		/*
		 * Enumeration<String> parameterNames = request.getParameterNames(); while
		 * (parameterNames.hasMoreElements()) { String paramName =
		 * parameterNames.nextElement(); log.info("crearLlocPost => " + paramName + ": "
		 * + request.getParameter(paramName)); }
		 */

		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();

		llocLogicaEjb.createAndHistory((Lloc) newLloc, request.getParameter("numerocai"), usuariId);

		return newLloc;

	}

	@Override
	public LlocJPA update(HttpServletRequest request, LlocJPA lloc) throws I18NException, I18NValidationException {

		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();

		return (LlocJPA) llocLogicaEjb.updateAndHistory((Lloc) lloc, request.getParameter("numerocai"), usuariId);
	}

	@Override
	public List<StringKeyValue> getReferenceListForPersonalOamr(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue("0", "No"));
		__tmp.add(new StringKeyValue("1", "Sí"));
		return __tmp;
	}
	
	@Override
	 public void delete(HttpServletRequest request, Lloc lloc) throws I18NException {
		
		Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

		Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.isNull());

		Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());
		
		Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

		Where w = Where.OR(w1, w2, w3, w4);
		
		List<Long> llocsOcupats = funcionariLlocEjb.executeQuery(FuncionariLlocFields.LLOCID, w);
		
		if (llocsOcupats.size() > 0) {
			throw new I18NException(createMessageError(request, "error.funcionariAssignat", lloc.getLlocID()));
			
		} else {
			
			// No es poden eliminar. Es donen de baixa insertant la data de baixa. 
			// llocEjb.delete(lloc);

			HistoricLlocJPA historicLloc = new HistoricLlocJPA();
			historicLloc.setLlocID(lloc.getLlocID());
			historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			historicLloc.setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
			
			historicLloc.setNumeroCai("CAI");
			historicLloc.setObservacions("Lloc Eliminat");
			historicLlocEjb.create(historicLloc);
			
			lloc.setDataBaixa(new Timestamp(System.currentTimeMillis()));
			llocEjb.update(lloc);
		}
		
	  }

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:/admin/funcionari/list/1";
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
			w1 = LlocFields.ENTITATID.equal(loginInfo.getEntitatIDActual());
		}
		
		return (w1 != null) ? Where.AND(defaultCondition, w1) : defaultCondition;
	}

}