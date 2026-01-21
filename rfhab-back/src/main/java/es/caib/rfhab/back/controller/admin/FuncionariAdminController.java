package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.FuncionariController;
import es.caib.rfhab.back.form.webdb.FuncionariFilterForm;
import es.caib.rfhab.back.form.webdb.FuncionariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.logic.ActivitatLogicaService;
import es.caib.rfhab.logic.AutoritzacioLogicaService;
import es.caib.rfhab.logic.FuncionariLlocLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.HistoricLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.logic.utils.FuncionariLlocLlocDAO;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.FuncionariLlocQueryPath;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.LlocJPA;

/*
 * 
 * @author jagarcia
 * @author jpou
 * 
 */
@Controller
@RequestMapping(value = "/admin/funcionari")
@SessionAttributes(types = { FuncionariForm.class, FuncionariFilterForm.class })
public class FuncionariAdminController extends FuncionariController {

	private static final String TIPUS_IDENTIFICACIO_SELECCIONA = "0";

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariEJB;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocEJB;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	protected FuncionariLlocLogicaService funcionariLlocLogicaEJB;

	@EJB(mappedName = UnitatLogicaService.JNDI_NAME)
	protected UnitatLogicaService unitatEJB;

	@EJB(mappedName = HistoricLogicaService.JNDI_NAME)
	protected HistoricLogicaService historicEjb;

	@EJB(mappedName = ActivitatLogicaService.JNDI_NAME)
	protected ActivitatLogicaService activitatEJB;

	@EJB(mappedName = AutoritzacioLogicaService.JNDI_NAME)
	protected AutoritzacioLogicaService autoritzacioEJB;

	@Override
	public String getTileForm() {
		return "funcionariFormAdmin";
	}

	@Override
	public String getTileList() {
		return "funcionariListAdmin";
	}

	@Override
	public FuncionariForm getFuncionariForm(FuncionariJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {

		FuncionariForm funcionariForm = super.getFuncionariForm(_jpa, __isView, request, mav);
		FuncionariJPA funcionari = funcionariForm.getFuncionari();

		funcionariForm.setDeleteButtonVisible(false);

		if (funcionariForm.isNou()) {
			funcionari.setDataCreacio(new Timestamp(System.currentTimeMillis()));

			LoginInfo loginInfo = LoginInfo.getInstance();
			Long entitatIDActual = loginInfo.getEntitatIDActual();
			log.info("ENTITAT ID ACTUAL: => " + entitatIDActual);
			if (entitatIDActual != null && entitatIDActual > 0) {
				funcionari.setEntitatID(entitatIDActual);
			}

			String nouFuncionariNumero = funcionariEJB.getNouFuncionariNumero();
			funcionari.setNumero(nouFuncionariNumero);
		} else {
			long funcionariId = funcionari.getFuncionariID();

			// Pipella Activitat - Obtenir les activitats que té assignades el funcionari
			List<Activitat> activitatsFuncionari = activitatEJB.getActivitatsByFuncionariID(funcionariId);
			mav.addObject("activitatItems", activitatsFuncionari);

			// Pipella Lloc assignat - Obtenir tots els llocs relacionats amb el funcionari
			// (actuals, sense data fi)
			// Pipella Funcionari històrics - Obtenir tots els llocs relacionats amb el
			// funcionari
			List<Lloc> llocsFuncionari = llocEJB.getLlocByFuncionariID(funcionariId, true);
			List<FuncionariLlocLlocDAO> llocsFuncionariHistoric = llocEJB.getLlocHistoricByFuncionariID(funcionariId,
					false);
			mav.addObject("llocItems", llocsFuncionari);
			mav.addObject("llocsHistoric", llocsFuncionariHistoric);

			// Pipella Històric - Obtenir tots els canvis realitzats al funcionari
			List<Select6Values<Long, String, String, String, String, Timestamp>> historicItems = historicEjb
					.getHistoricByFuncionariId(funcionariId);
			mav.addObject("historicItems", historicItems);

			// botons donar de baixa/alta
			if (funcionari.getDataBaixa() == null) {
				// botó donar de baixa funcionari
				String urlGoTo = request.getContextPath() + getContextWeb() + "/" + funcionariId + "/delete/";
				String actionButtonOnClickCallback = "goTo(encodeURI(\\'" + urlGoTo
						+ "\\' + \\'?numerocai=\\' + document.getElementById(\\'numerocai\\').value))";
				String jsOpenModalDonarBaixa = "javascript:createDivModal(traduccions.type['titol.funcionari.donarbaixa.continuar'], traduccions.type['missatge.funcionari.donarbaixa.continuar'], '"
						+ urlGoTo + "', '', 'func-donarbaixa-id', 'fa-user-times', '', '" + actionButtonOnClickCallback
						+ "');\r\n" + //
						"        $('#func-donarbaixa-id').modal('show');\r\n";
				AdditionalButton donarDeBaixaButton = new AdditionalButton("fas fa-user-times",
						"funcionari.donarbaixa",
						jsOpenModalDonarBaixa,
						AdditionalButtonStyle.DANGER);
				funcionariForm.addAdditionalButton(donarDeBaixaButton);
			} else {
				// botó donar d'alta funcionari
				String jsOpenModalDonarAlta = "javascript:createDivModal(traduccions.type['titol.funcionari.donaralta.continuar'], traduccions.type['missatge.funcionari.donaralta.continuar'], '"
						+ "', 'funcionariForm', 'func-donaralta-id', 'fa-user-plus', '"
						+ request.getContextPath() + getContextWeb() + "/donaralta/"
						+ "');\r\n" + //
						"        $('#func-donaralta-id').modal('show');\r\n";
				AdditionalButton donarDeAltaButton = new AdditionalButton("fas fa-user-plus",
						"funcionari.donaralta",
						jsOpenModalDonarAlta,
						AdditionalButtonStyle.SUCCESS);
				funcionariForm.addAdditionalButton(donarDeAltaButton);
			}
		}
		mav.addObject("FUNCIONARI_NUMERO_PLACEHOLDER", Constants.FUNCIONARI_NUMERO_PLACEHOLDER);

		funcionariForm.addReadOnlyField(DATACREACIO);
		funcionariForm.addHiddenField(DATABAIXA);
		funcionariForm.addReadOnlyField(FuncionariFields.NUMERO);
		// funcionariForm.addHiddenField(ENTITATID);
		funcionariForm.addReadOnlyField(FuncionariFields.ENTITATID);

		mav.addObject("funcionari", funcionari);

		funcionariForm.setAttachedAdditionalJspCode(true);

		/*
		 * // Afegim la columna ENTITAT AdditionalField<String,String> adfield = new
		 * AdditionalField<String,String>();
		 * adfield.setCodeName(EntitatFields._TABLE_TRANSLATION);
		 * adfield.setPosition(1); final Field<String> ENTITAT_NOM = new
		 * RoleUsuariEntitatQueryPath().USUARIENTITAT().ENTITAT().NOM();
		 * adfield.setValueField(ENTITAT_NOM); adfield.setValueMap(null);
		 * adfield.setOrderBy(ENTITAT_NOM);
		 * 
		 * roleUsuariEntitatFilterForm.addAdditionalField(adfield);
		 */

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return funcionariForm;
	}

	@Override
	public FuncionariFilterForm getFuncionariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		FuncionariFilterForm funcionariFilterForm = super.getFuncionariFilterForm(pagina, mav, request);

		if (funcionariFilterForm.isNou()) {

			funcionariFilterForm.addHiddenField(FUNCIONARIID);
			funcionariFilterForm.addHiddenField(DATACREACIO);
			funcionariFilterForm.addHiddenField(TIPUSIDENTIFICADOR);
			// funcionariFilterForm.addHiddenField(IDENTIFICADOR);
			funcionariFilterForm.addHiddenField(CORREU);
			funcionariFilterForm.addHiddenField(ENTITATID);
			funcionariFilterForm.addHiddenField(OBSERVACIONS);
			// funcionariFilterForm.addHiddenField(DATABAIXA);

			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(LlocFields.CODILLOC.codeLabel);
				adfield.setPosition(1);
				// adfield.setOrderBy(LlocFields.CODILLOC);
				adfield.setEscapeXml(false);
				adfield.setValueMap(new HashMap<Long, String>());
				funcionariFilterForm.addAdditionalField(adfield);
			}

			{
				AdditionalField<Long, String> adfield2 = new AdditionalField<Long, String>();
				adfield2.setCodeName(LlocFields.UNITATID.codeLabel);
				adfield2.setPosition(2);
				// adfield2.setOrderBy(LlocFields.UNITATID);
				adfield2.setEscapeXml(false);
				adfield2.setValueMap(new HashMap<Long, String>());
				funcionariFilterForm.addAdditionalField(adfield2);
			}

			{
				AdditionalField<Long, String> adfield3 = new AdditionalField<Long, String>();
				adfield3.setCodeName(LlocFields.PERSONALOAMR.codeLabel);
				adfield3.setPosition(3);
				// adfield3.setOrderBy(LlocFields.PERSONALOAMR);
				adfield3.setEscapeXml(false);
				// no podem cercar a un altre taula perquè donarà error. Afegirem aquest filtre
				// a getAdditionalCondition
				// adfield3.setSearchBy(LlocFields.PERSONALOAMR);
				adfield3.setGroupBy(LlocFields.PERSONALOAMR);
				adfield3.setValueMap(new HashMap<Long, String>());
				funcionariFilterForm.addAdditionalField(adfield3);
			}

			funcionariFilterForm.setOrderBy(FuncionariFields.LLINATGE1.sqlName);
			funcionariFilterForm.setOrderAsc(true);
		}

		funcionariFilterForm.setDeleteButtonVisible(false);
		funcionariFilterForm.setVisibleMultipleSelection(false);
		funcionariFilterForm.setDeleteSelectedButtonVisible(false);
		funcionariFilterForm.setViewButtonVisible(false);
		funcionariFilterForm.setAttachedAdditionalJspCode(true);

		// funcionariFilterForm.setActionsRenderer(FuncionariFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return funcionariFilterForm;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		final Where defaultCondition = super.getAdditionalCondition(request);
		final Where entitatIdActualWhere = getEntitatIdActualWhere();
		final Where personalOamrWhere = getPersonalOamrWhere(request);
		final Where donatsDeBaixaWhere = getAdditionalConditionDonatsDeBaixa(request);
		final Where AssignatsAllocDeFeinaWhere = getAdditionalConditionAssignats(request);

		log.debug("defaultCondition ==> " + (defaultCondition != null ? defaultCondition.toSQL() : "null"));
		log.debug("donatsDeBaixaWhere ==> " + (donatsDeBaixaWhere != null ? donatsDeBaixaWhere.toSQL() : "null"));
		log.debug("personalOamrWhere ==> " + (personalOamrWhere != null ? personalOamrWhere.toSQL() : "null"));
		log.debug("entitatIdActualWhere ==> " + (entitatIdActualWhere != null ? entitatIdActualWhere.toSQL() : "null"));
		log.debug("AssignatsAllocDeFeinaWhere ==> "
				+ (AssignatsAllocDeFeinaWhere != null ? AssignatsAllocDeFeinaWhere.toSQL() : "null"));

		return Where.AND(personalOamrWhere, donatsDeBaixaWhere, AssignatsAllocDeFeinaWhere,
				(entitatIdActualWhere != null) ? Where.AND(defaultCondition, entitatIdActualWhere) : defaultCondition);
	}

	private Where getEntitatIdActualWhere() {
		// filtrar per entitat
		LoginInfo loginInfo = LoginInfo.getInstance();

		System.out.println("================================================");
		Long entitatIDActual = loginInfo.getEntitatIDActual();
		System.out.println("ENTITAT ACTUAL: => " + entitatIDActual);
		System.out.println("ENTITAT ID ACTUAL: => " + loginInfo.getEntitatID());
		System.out.println("================================================");

		Where entitatIdActualWhere = null;
		if (entitatIDActual != null && entitatIDActual > 0) {
			entitatIdActualWhere = FuncionariFields.ENTITATID.equal(entitatIDActual);
		}
		return entitatIdActualWhere;
	}

	private Where getPersonalOamrWhere(HttpServletRequest request) throws I18NException {
		// filtrar per personalOamr
		final String personalOamr = (StringUtils.isNotEmpty(request.getParameter("lloc.personalOamr")))
				? request.getParameter("lloc.personalOamr")
				: "";

		log.info("personalOamr ==> " + personalOamr);
		Where personalOamrWhere = null;
		if (!"".equals(personalOamr)) {
			FuncionariLlocQueryPath funcionarilLocQueryPath = new FuncionariLlocQueryPath();
			Where whereLloc = funcionarilLocQueryPath.LLOC().PERSONALOAMR().equal(Integer.valueOf(personalOamr));
			// executeQuery seria fer un select i el resultat el ficariem dins l'IN. En
			// canvi, SubQuery prepara la sentencia select i és el que fica dins l'in. Seria
			// IN Operator vs IN (SELECT). És a dir, SubQuery seria una SubQuery normal i
			// corrent de SQL, molt més eficient en aquest cas.
			SubQuery<FuncionariLloc, Long> subQuery;
			subQuery = funcionariLlocLogicaEJB.getSubQuery(FuncionariLlocFields.FUNCIONARIID, whereLloc);
			// List<Long> fFuncIds =
			// funcionariLlocLogicaEJB.executeQuery(FuncionariLlocFields.FUNCIONARIID,
			// whereLloc);
			personalOamrWhere = FuncionariFields.FUNCIONARIID.in(subQuery);
		} else {
			log.warn("Mostrant tots personalOamr");
		}
		return personalOamrWhere;
	}

	private Where getAdditionalConditionAssignats(HttpServletRequest request) throws I18NException {
		// filtrar per assignatsAlloc
		final String assignatsAlloc = (StringUtils.isNotEmpty(request.getParameter("assignatsAllocName")))
				? request.getParameter("assignatsAllocName")
				: "";

		log.info("assignatsAlloc ==> " + assignatsAlloc);
		Where assignatsAllocWhere = null;

		Where whereLloc = funcionariLlocLogicaEJB.getWhereFuncionariIsCurrent();
		SubQuery<FuncionariLloc, Long> subQuery;
		subQuery = funcionariLlocLogicaEJB.getSubQuery(FuncionariLlocFields.FUNCIONARIID, whereLloc);
		if ("1".equals(assignatsAlloc)) {
			assignatsAllocWhere = FuncionariFields.FUNCIONARIID.in(subQuery);
		} else if (TIPUS_IDENTIFICACIO_SELECCIONA.equals(assignatsAlloc)) {
			assignatsAllocWhere = FuncionariFields.FUNCIONARIID.notIn(subQuery);
		} else {
			log.warn("Mostrant tots assignatsAlloc");
		}
		return assignatsAllocWhere;
	}

	public Where getAdditionalConditionDonatsDeBaixa(HttpServletRequest request) throws I18NException {
		// filtrar per donats de baixa (actius)
		final String actiusSelectvalue = (StringUtils.isNotEmpty(request.getParameter("actiusSegonsDatabaixaName")))
				? request.getParameter("actiusSegonsDatabaixaName")
				: "";
		log.info("actiusSelectvalue ==> " + actiusSelectvalue);

		if (TIPUS_IDENTIFICACIO_SELECCIONA.equals(actiusSelectvalue)) {
			return FuncionariFields.DATABAIXA.isNotNull();
		} else if ("1".equals(actiusSelectvalue)) {
			return FuncionariFields.DATABAIXA.isNull();
		} else {
			log.warn("Mostrant tots DataBaixa");
		}

		return null;
	}

	@Override
	public void delete(HttpServletRequest request, Funcionari funcionari) throws I18NException {
		long funcionariId = funcionari.getFuncionariID();
		log.info("'Esborrant' (donant de baixa) funcionari amb ID " + funcionariId);

		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: "";

		funcionariEJB.donarDeBaixaFuncionariAndHistory(funcionari, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		// createMessageSuccess(request, "success.modification", funcionariId);//
		// funcionari.donaralta.exit
	}

	@RequestMapping(value = "/donaralta")
	public String donarDeAlta(@ModelAttribute FuncionariForm funcionariForm, HttpServletRequest request,
			HttpServletResponse response) throws I18NException {
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: "";

		long funcionariId = funcionariForm.getFuncionari().getFuncionariID();
		funcionariEJB.donarDeAltaAndHistory(funcionariId, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		createMessageSuccess(request, "success.modification", funcionariId);// funcionari.donaralta.exit
		return getRedirectWhenModified(request, null, null);
	}

	@Override
	public FuncionariJPA create(HttpServletRequest request, FuncionariJPA funcionari)
			throws I18NException, I18NValidationException {
		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: "";
		log.info("Creant Funcionari i Historic per a CAI: " + numeroCai + " i usuari: " + usuariId);
		FuncionariJPA newFuncionari = funcionariEJB.createAndHistory(funcionari, numeroCai, usuariId);

		return newFuncionari;
	}

	@Override
	public Map<Field<?>, GroupByItem> fillReferencesForList(FuncionariFilterForm filterForm, HttpServletRequest request,
			ModelAndView mav, List<Funcionari> list, List<GroupByItem> groupItems) throws I18NException {

		Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();

		groupByItemsMap = super.fillReferencesForList(filterForm, request, mav, list, groupItems);

		Map<String, String> _tmpPersonalOamr;
		List<StringKeyValue> _listPersonalOamr;

		// Field PersonalOamr
		{
			_listPersonalOamr = getReferenceListForPersonalOamr(request, mav, filterForm, list, groupByItemsMap, null);
			_tmpPersonalOamr = Utils.listToMap(_listPersonalOamr);
			filterForm.setMapOfValuesForTipusIdentificador(_tmpPersonalOamr);
			if (filterForm.getGroupByFields().contains(LlocFields.PERSONALOAMR)) {
				fillValuesToGroupByItems(_tmpPersonalOamr, groupByItemsMap, LlocFields.PERSONALOAMR, false);
			}
			;
		}

		return groupByItemsMap;
	}

	public List<StringKeyValue> getReferenceListForPersonalOamr(HttpServletRequest request, ModelAndView mav,
			FuncionariFilterForm funcionariFilterForm, List<Funcionari> list,
			Map<Field<?>, GroupByItem> _groupByItemsMap, Where where) throws I18NException {

		if (funcionariFilterForm.isHiddenField(LlocFields.PERSONALOAMR)
				&& !funcionariFilterForm.isGroupByField(LlocFields.PERSONALOAMR)
				&& !funcionariFilterForm.isFilterByField(LlocFields.PERSONALOAMR)) {

			return EMPTY_STRINGKEYVALUE_LIST;
		}

		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue(TIPUS_IDENTIFICACIO_SELECCIONA, "No"));
		__tmp.add(new StringKeyValue("1", "Sí"));
		return __tmp;
	}

	@Override
	public List<StringKeyValue> getReferenceListForTipusIdentificador(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {

		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue(TIPUS_IDENTIFICACIO_SELECCIONA, I18NUtils.tradueix("tipusidentificacio.0")));
		__tmp.add(new StringKeyValue("1", I18NUtils.tradueix("tipusidentificacio.1")));
		__tmp.add(new StringKeyValue("2", I18NUtils.tradueix("tipusidentificacio.2")));
		__tmp.add(new StringKeyValue("3", I18NUtils.tradueix("tipusidentificacio.3")));
		__tmp.add(new StringKeyValue("4", I18NUtils.tradueix("tipusidentificacio.4")));
		return __tmp;
	}

	@Override
	public void preValidate(HttpServletRequest request, FuncionariForm funcionariForm, BindingResult result)
			throws I18NException {
		FuncionariJPA funcionari = funcionariForm.getFuncionari();
		if (String.valueOf(funcionari.getTipusIdentificador()).equals(TIPUS_IDENTIFICACIO_SELECCIONA)) {
			result.rejectValue(FuncionariFields.TIPUSIDENTIFICADOR.codeLabel, "error.required",
					new Object[] { "Número" },
					"El camp " + I18NUtils.tradueix(FuncionariFields.TIPUSIDENTIFICADOR.codeLabel) + " és obligatori");
		}
	}

	@Override
	public FuncionariJPA update(HttpServletRequest request, FuncionariJPA funcionari)
			throws I18NException, I18NValidationException {

		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();
		String numeroCai = (!Utils.isEmpty(request.getParameter("numerocai"))) ? request.getParameter("numerocai") : "";
		return (FuncionariJPA) funcionariEJB.updateAndHistory((Funcionari) funcionari, numeroCai, usuariId);
	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, FuncionariFilterForm filterForm,
			List<Funcionari> list) throws I18NException {

		Map<Long, String> mapFuncionari = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();
		Map<Long, String> mapFuncionari2 = (Map<Long, String>) filterForm.getAdditionalField(2).getValueMap();
		Map<Long, String> mapFuncionari3 = (Map<Long, String>) filterForm.getAdditionalField(3).getValueMap();

		mapFuncionari.clear();
		mapFuncionari2.clear();
		mapFuncionari3.clear();

		HashMap<Long, LlocJPA> placesOcupades = llocEJB
				.getAllLlocsOcupats(LoginInfo.getInstance().getEntitatIDActual());

		for (Funcionari funcionari : list) {

			final Long funcionariID = funcionari.getFuncionariID();

			LlocJPA lloc = placesOcupades.get(funcionariID);
			if (lloc != null) {
				mapFuncionari.put(funcionariID, lloc.getCodiLloc());
				mapFuncionari2.put(funcionariID, unitatEJB.findByPrimaryKey(lloc.getUnitatID()).getCodi());
				mapFuncionari3.put(funcionariID, ((lloc.getPersonalOamr() > 1) ? "<i class=\"fa fa-check\"></i>"
						: "<i class=\"fa fa-times\"></i>"));
			} else {
				mapFuncionari.put(funcionariID, "");
				mapFuncionari2.put(funcionariID, "");
				mapFuncionari3.put(funcionariID, "<i class=\"fa fa-times\"></i>");
			}
		}
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, FuncionariForm funcionariForm) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCreated(request, funcionariForm));
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, FuncionariForm funcionariForm, Throwable __e) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenModified(request, funcionariForm, __e));
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionariID, Throwable __e) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenDelete(request, funcionariID, __e));
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long funcionariID) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCancel(request, funcionariID));
	}

}
