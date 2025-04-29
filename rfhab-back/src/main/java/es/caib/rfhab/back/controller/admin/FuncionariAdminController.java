package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.FuncionariController;
import es.caib.rfhab.back.form.webdb.FuncionariFilterForm;
import es.caib.rfhab.back.form.webdb.FuncionariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.UnitatService;
import es.caib.rfhab.logic.ActivitatLogicaService;
import es.caib.rfhab.logic.AutoritzacioLogicaService;
import es.caib.rfhab.logic.FuncionariLlocLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.HistoricLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.HistoricJPA;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;

@Controller
@RequestMapping(value = "/admin/funcionari")
@SessionAttributes(types = { FuncionariForm.class, FuncionariFilterForm.class })
public class FuncionariAdminController extends FuncionariController {

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariEJB;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocEJB;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	protected FuncionariLlocLogicaService funcionariLlocLogicaEJB;

	@EJB(mappedName = UnitatService.JNDI_NAME)
	protected UnitatService unitatEJB;

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

			int nouNumber = 1;
			String maxFuncionariNumero;
			try {
				maxFuncionariNumero = funcionariEJB.getMaxFuncionariNumero();
			} catch (SecurityException e) {
				throw new I18NException(e.getMessage());
			} catch (NoSuchFieldException e) {
				throw new I18NException(e.getMessage());
			}
			if (maxFuncionariNumero != null) {
				// Extreu la part numèrica de la cadena
				String numericPart = maxFuncionariNumero
						.substring(Constants.FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX.length());
				// Converteix la part numèrica a un enter, suma 1 i torna a formar la cadena
				nouNumber = Integer.parseInt(numericPart);
				nouNumber += 1;
			}
			// Format numèric amb el mateix nombre de dígits que l'original
			String updatedNumericPart = String
					.format("%0" + Constants.FUNCIONARI_NUMERO_PLACEHOLDER_NUMERICPART.length() + "d", nouNumber);
			// Reconstrueix la cadena amb el prefix i el nou valor numèric
			String nouFuncionariNumero = Constants.FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX + updatedNumericPart;
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
			List<Lloc> llocsFuncionariHistoric = llocEJB.getLlocByFuncionariID(funcionariId, false);
			mav.addObject("llocItems", llocsFuncionari);
			mav.addObject("llocsHistoric", llocsFuncionariHistoric);

			// Pipella Històric - Obtenir tots els canvis realitzats al funcionari
			List<Select6Values<Long, String, String, String, String, Timestamp>> historicItems = historicEjb
					.getHistoricByFuncionariId(funcionariId);
			mav.addObject("historicItems", historicItems);

			if (funcionari.getDataBaixa() == null) {
				// botó donar de baixa funcionari
				String jsOpenModalDonarBaixa = "javascript:createDivModal(traduccions.type['titol.funcionari.donarbaixa.continuar'], traduccions.type['missatge.funcionari.donarbaixa.continuar'], '"
						+ request.getContextPath() + getContextWeb() + "/" + funcionariId + "/delete/"
						+ "', '', 'func-donarbaixa-id', 'fa-user-times');\r\n" + //
						"        $('#func-donarbaixa-id').modal('show');\r\n";
				AdditionalButton donarDeBaixaButton = new AdditionalButton("fas fa-user-times",
						"funcionari.donarbaixa",
						jsOpenModalDonarBaixa,
						AdditionalButtonStyle.DANGER);
				funcionariForm.addAdditionalButton(donarDeBaixaButton);
			} else {
				// TODO: afegir botó per donar d'alta el funcionari
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
			funcionariFilterForm.addHiddenField(IDENTIFICADOR);
			funcionariFilterForm.addHiddenField(CORREU);
			funcionariFilterForm.addHiddenField(ENTITATID);
			funcionariFilterForm.addHiddenField(OBSERVACIONS);
			funcionariFilterForm.addHiddenField(DATABAIXA);

			{
				// Afegir filtres adicionals
				List<Field<?>> newFilterBy = new ArrayList<Field<?>>(funcionariFilterForm.getDefaultFilterByFields());
				newFilterBy.remove(LlocFields.PERSONALOAMR);
				newFilterBy.remove(FuncionariFields.IDENTIFICADOR);
				funcionariFilterForm.setFilterByFields(newFilterBy);

			}

			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(LlocFields.CODILLOC.codeLabel);
				adfield.setPosition(1);
				adfield.setOrderBy(LlocFields.CODILLOC);
				adfield.setEscapeXml(false);
				adfield.setValueMap(new HashMap<Long, String>());
				funcionariFilterForm.addAdditionalField(adfield);
			}

			{
				AdditionalField<Long, String> adfield2 = new AdditionalField<Long, String>();
				adfield2.setCodeName(LlocFields.UNITATID.codeLabel);
				adfield2.setPosition(2);
				adfield2.setOrderBy(LlocFields.UNITATID);
				adfield2.setEscapeXml(false);
				adfield2.setValueMap(new HashMap<Long, String>());
				funcionariFilterForm.addAdditionalField(adfield2);
			}

			{
				AdditionalField<Long, String> adfield3 = new AdditionalField<Long, String>();
				adfield3.setCodeName(LlocFields.PERSONALOAMR.codeLabel);
				adfield3.setPosition(3);
				adfield3.setOrderBy(LlocFields.PERSONALOAMR);
				adfield3.setEscapeXml(false);
				adfield3.setSearchBy(LlocFields.PERSONALOAMR);
				adfield3.setGroupBy(LlocFields.PERSONALOAMR);
				adfield3.setValueMap(new HashMap<Long, String>());
				funcionariFilterForm.addAdditionalField(adfield3);

				Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
						Arrays.asList(FuncionariFields.ALL_FUNCIONARI_FIELDS));
				hiddenFields.add(LlocFields.PERSONALOAMR);
				hiddenFields.forEach(x -> log.info("hiddenFields item: " + x.javaName));

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

		return funcionariFilterForm;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		final Where defaultCondition = super.getAdditionalCondition(request);

		// filtrar per entitat
		LoginInfo loginInfo = LoginInfo.getInstance();

		System.out.println("================================================");
		Long entitatIDActual = loginInfo.getEntitatIDActual();
		System.out.println("ENTITAT ACTUAL: => " + entitatIDActual);
		System.out.println("ENTITAT ID ACTUAL: => " + loginInfo.getEntitatID());
		System.out.println("================================================");

		Where w1 = null;
		if (entitatIDActual != null && entitatIDActual > 0) {
			w1 = FuncionariFields.ENTITATID.equal(entitatIDActual);
		}

		// filtrar per personalOamr
		Map<String, String[]> parametros = request.getParameterMap();
		boolean personalOamr = false;
		for (Map.Entry<String, String[]> entry : parametros.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			if ("lloc.personalOamr".equals(entry.getKey())) {
				personalOamr = (entry.getValue()[0].equals("1") ? true : false);
				break;
			}
		}

		if (personalOamr) {
			System.out.println("Where: IS PERSONAL OAMR");
			// Where w2 = new
			// FuncionariLlocQueryPath().LLOC().PERSONALOAMR().greaterThan(0);
			// if (w1 != null)
			// w1 = Where.AND(w1, w2);
			// else
			// w1 = w2;
		}

		if (w1 != null)
			return Where.AND(defaultCondition, w1);
		else
			return defaultCondition;
	}

	@Override
	public void delete(HttpServletRequest request, Funcionari funcionari) throws I18NException {
		long funcionariId = funcionari.getFuncionariID();
		log.info("'Esborrant' (donant de baixa) funcionari amb ID " + funcionariId);

		// afegim històric
		HistoricJPA historic = new HistoricJPA();
		historic.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historic.setFuncionariID(funcionariId);
		historic.setNumeroCai("CAI");// TODO:revisar això
		historic.setUsuariID(LoginInfo.getInstance().getUsuariPersona().getUsuariID());
		historic.setObservacions("Funcionari donat de baixa");
		historicEjb.create(historic);

		// el desasignam del lloc de feina
		funcionariLlocLogicaEJB.donarDeBaixaFuncionariDeLloc(funcionariId);

		// el donam de baixa
		funcionari.setDataBaixa(new Timestamp(System.currentTimeMillis()));
		funcionariEjb.update(funcionari);
	}

	@Override
	public FuncionariJPA create(HttpServletRequest request, FuncionariJPA funcionari)
			throws I18NException, I18NValidationException {
		FuncionariJPA newFuncionari = super.create(request, funcionari);
		log.info("Funcionari creat: " + newFuncionari.getFuncionariID());

		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();

		String numeroCai = request.getParameter("numerocai");
		log.info("Creant Historic per a CAI: " + numeroCai + " i usuari: " + usuariId);
		HistoricJPA historic = historicEjb.create(newFuncionari, numeroCai, usuariId);
		log.info("Historic creat: " + historic.getHistoricID());

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
		__tmp.add(new StringKeyValue("0", "No"));
		__tmp.add(new StringKeyValue("1", "Sí"));
		return __tmp;
	}

	@Override
	public List<StringKeyValue> getReferenceListForTipusIdentificador(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {

		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue("0", I18NUtils.tradueix("tipusidentificacio.0")));
		__tmp.add(new StringKeyValue("1", I18NUtils.tradueix("tipusidentificacio.1")));
		__tmp.add(new StringKeyValue("2", I18NUtils.tradueix("tipusidentificacio.2")));
		__tmp.add(new StringKeyValue("3", I18NUtils.tradueix("tipusidentificacio.3")));
		__tmp.add(new StringKeyValue("4", I18NUtils.tradueix("tipusidentificacio.4")));
		return __tmp;
	}

	// @Override
	// public String editarFuncionariPost(@ModelAttribute FuncionariForm funcionariForm,
	// 		BindingResult result, SessionStatus status, HttpServletRequest request,
	// 		HttpServletResponse response) throws I18NException {

	// 	String numeroCai = (!Utils.isEmpty(request.getParameter("numerocai"))) ? request.getParameter("numerocai") : "";

	// 	funcionariEJB.updateAndHistory(funcionariForm.getFuncionari(), numeroCai,
	// 			LoginInfo.getInstance().getUsuariPersona().getUsuariID());

	// 	return super.editarFuncionariPost(funcionariForm, result, status, request, response);
	// }
	@Override
	public FuncionariJPA update(HttpServletRequest request, FuncionariJPA funcionari) throws I18NException, I18NValidationException {

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
				mapFuncionari3.put(funcionariID, ((lloc.getPersonalOamr() > 0) ? "<i class=\"fa fa-check\"></i>"
						: "<i class=\"fa fa-times\"></i>"));
			} else {
				mapFuncionari.put(funcionariID, "");
				mapFuncionari2.put(funcionariID, "");
				mapFuncionari3.put(funcionariID, "<i class=\"fa fa-times\"></i>");
			}
		}
	}
}
