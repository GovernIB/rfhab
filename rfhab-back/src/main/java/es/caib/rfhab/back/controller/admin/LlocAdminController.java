package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectGroupBy;
import org.fundaciobit.genapp.common.query.SelectMax;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;
import org.fundaciobit.genapp.common.query.selectcolumn.Select7Values;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.CustomDateI18NEditor;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.LlocController;
import es.caib.rfhab.back.form.webdb.LlocFilterForm;
import es.caib.rfhab.back.form.webdb.LlocForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.PersonalOamrTipus;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.ejb.HabilitacioService;
import es.caib.rfhab.logic.EntitatLogicaService;
import es.caib.rfhab.logic.FuncionariLlocLogicaService;
import es.caib.rfhab.logic.HistoricLlocLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.logic.LlocHabilitacioLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.logic.utils.DbDaoDictionaries;
import es.caib.rfhab.logic.utils.FuncionariLlocDAO;
import es.caib.rfhab.logic.utils.HistoricCanvisLlocDAO;
import es.caib.rfhab.model.entity.Entitat;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.Habilitacio;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.HistoricLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocHabilitacioFields;
import es.caib.rfhab.model.fields.LlocQueryPath;
import es.caib.rfhab.model.fields.UnitatFields;
import es.caib.rfhab.persistence.LlocJPA;
import es.caib.rfhab.persistence.UnitatJPA;

/**
 * @author jagarcia
 * @author jpou
 */
@Controller
@RequestMapping(value = LlocAdminController.CONTEXTWEB)
@SessionAttributes(types = { LlocForm.class, LlocFilterForm.class })
public class LlocAdminController extends LlocController {

	public static final String CONTEXTWEB = "/admin/lloc";

	protected final Logger log = Logger.getLogger(getClass());

	private static final String TIPUS_PERSONAL_OAMR = "0";

	@EJB(mappedName = HistoricLlocLogicaService.JNDI_NAME)
	protected HistoricLlocLogicaService historicLlocEjb;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocLogicaEjb;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	protected FuncionariLlocLogicaService funcionariLlocLogicaEjb;

	@EJB(mappedName = LlocHabilitacioLogicaService.JNDI_NAME)
	protected LlocHabilitacioLogicaService llocHabilitacioLogicaEjb;

	@EJB(mappedName = UnitatLogicaService.JNDI_NAME)
	protected UnitatLogicaService unitatEjb;

	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicaEjb;

	@EJB(mappedName = HabilitacioService.JNDI_NAME)
	protected HabilitacioService habilitacionsEjb;

	private void setUnitatRefListSelects(String language) {
		Select<?>[] selects;
		if (language == "es") {
			selects = new Select<?>[] { UnitatFields.CODI.select, UnitatFields.DENOMINACIO.select };
		} else {
			selects = new Select<?>[] { UnitatFields.CODI.select, UnitatFields.COOFICIAL.select };
		}
		log.info("setUnitatRefListSelects: " + selects.length + " language: " + language);
		unitatRefList.setSelects(selects);
	}

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
		LoginInfo loginInfo = LoginInfo.getInstance();
		String lang = LocaleContextHolder.getLocale().getLanguage();

		setUnitatRefListSelects(lang);

		LlocFilterForm llocFilterForm = super.getLlocFilterForm(pagina, mav, request);

		String oamrSelectvalue = (StringUtils.isNotEmpty(request.getParameter("personalOamrSelect")))
				? request.getParameter("personalOamrSelect")
				: "";
		request.getSession().removeAttribute(Constants.ATTR_FILTRE_OAMR_VALOR_PER_DEFECTE);
		String actiusSelectvalue = (StringUtils.isNotEmpty(request.getParameter("actiusSegonsDatabaixaName")))
				? request.getParameter("actiusSegonsDatabaixaName")
				: "";
		request.getSession().removeAttribute(Constants.ATTR_FILTRE_ACTIUS_VALOR_PER_DEFECTE);
		String unitatsoSelectvalue = (StringUtils.isNotEmpty(request.getParameter("unitatIDDesde")))
				? request.getParameter("unitatIDDesde")
				: "";
		request.getSession().removeAttribute(Constants.ATTR_FILTRE_UNITATSO_VALOR_PER_DEFECTE);
		String unitatSuperiorSelectvalue = (StringUtils.isNotEmpty(request.getParameter("unitatSuperiorName")))
				? request.getParameter("unitatSuperiorName")
				: "";
		request.getSession().removeAttribute(Constants.ATTR_FILTRE_UNITATSUPERIOR_VALOR_PER_DEFECTE);

		if (llocFilterForm.isNou()) {
			llocFilterForm.addHiddenField(LlocFields.LLOCID);
			llocFilterForm.addHiddenField(LlocFields.DATACREACIO);
			llocFilterForm.addHiddenField(LlocFields.ENTITATID);
			llocFilterForm.addHiddenField(LlocFields.OBSERVACIONS);
			llocFilterForm.addHiddenField(LlocFields.DATABAIXA);
			llocFilterForm.addHiddenField(LlocFields.CODILLOC);
			llocFilterForm.addHiddenField(LlocFields.EXPANSIO);

			{
				AdditionalField<Long, String> adfield0 = new AdditionalField<Long, String>();
				adfield0.setCodeName(UnitatFields.SUPERIOR.codeLabel);
				adfield0.setPosition(1);
				adfield0.setEscapeXml(false);
				adfield0.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield0);
			}

			{
				AdditionalField<Long, String> adfield2 = new AdditionalField<Long, String>();
				adfield2.setCodeName(LlocFields.CODILLOC.codeLabel);
				adfield2.setPosition(2);
				adfield2.setEscapeXml(false);
				adfield2.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield2);
			}

			{
				AdditionalField<Long, String> adfield3 = new AdditionalField<Long, String>();
				adfield3.setCodeName(FuncionariLlocFields.FUNCIONARIID.codeLabel);
				adfield3.setPosition(3);
				adfield3.setEscapeXml(false);
				adfield3.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield3);
			}

			{
				AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
				adfield4.setCodeName("habilitacio.habilitacio.plural");
				adfield4.setPosition(4);
				// adfield4.setOrderBy(HabilitacioFields.CODI);
				adfield4.setEscapeXml(false);
				adfield4.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield4);
			}
			{
				AdditionalField<Long, String> adfieldDarreraModificacio = new AdditionalField<Long, String>();
				adfieldDarreraModificacio.setCodeName("darreramodificacio");
				adfieldDarreraModificacio.setPosition(5);
				adfieldDarreraModificacio.setEscapeXml(false);
				adfieldDarreraModificacio.setValueMap(new HashMap<Long, String>());
				// adfieldDarreraModificacio.setValueField(CODILLOC);
				adfieldDarreraModificacio.setOrderBy(HistoricLlocFields.DATACREACIO);
				llocFilterForm.addAdditionalField(adfieldDarreraModificacio);
				// llocFilterForm.addhiddenField(adfieldDarreraModificacio);
			}

			actiusSelectvalue = "1";

			llocFilterForm.setOrderBy(HistoricLlocFields.DATACREACIO.javaName);
			llocFilterForm.setOrderAsc(false);
		}
		request.getSession().setAttribute(Constants.ATTR_FILTRE_OAMR_VALOR_PER_DEFECTE, oamrSelectvalue);
		request.getSession().setAttribute(Constants.ATTR_FILTRE_ACTIUS_VALOR_PER_DEFECTE, actiusSelectvalue);
		request.getSession().setAttribute(Constants.ATTR_FILTRE_UNITATSO_VALOR_PER_DEFECTE, unitatsoSelectvalue);
		request.getSession().setAttribute(Constants.ATTR_FILTRE_UNITATSUPERIOR_VALOR_PER_DEFECTE,
				unitatSuperiorSelectvalue);

		Long entitatIDActual = loginInfo.getEntitatIDActual();
		List<StringKeyValue> _unitatsTemp = getUnitatsByEntitatArrel(mav, entitatIDActual,
				false);
		Map<String, String> unitatsFiltreCerca = Utils.listToMap(_unitatsTemp);
		unitatsFiltreCerca.put("", I18NUtils.tradueix("tots"));
		mav.addObject(Constants.NOM_ATTR_FILTRE_UNITATS, unitatsFiltreCerca);
		log.info(Constants.NOM_ATTR_FILTRE_UNITATS + ": " + unitatsFiltreCerca.size());
		log.info(unitatsFiltreCerca);

		List<StringKeyValue> _unitatsMareTemp = getUnitatsSuperiorsByEntitatArrel(mav, entitatIDActual);
		Map<String, String> unitatsMareFiltreCerca = Utils.listToMap(_unitatsMareTemp);
		unitatsMareFiltreCerca.put("", I18NUtils.tradueix("tots"));
		mav.addObject(Constants.NOM_ATTR_FILTRE_UNITATS_SUPERIORS, unitatsMareFiltreCerca);
		log.info(Constants.NOM_ATTR_FILTRE_UNITATS_SUPERIORS + ": " + unitatsMareFiltreCerca.size());
		log.info(unitatsMareFiltreCerca);

		llocFilterForm.setVisibleExportList(true);

		llocFilterForm.setViewButtonVisible(true);
		llocFilterForm.setDeleteButtonVisible(false);
		llocFilterForm.setDeleteSelectedButtonVisible(false);
		llocFilterForm.setVisibleMultipleSelection(false);

		llocFilterForm.setAttachedAdditionalJspCode(true);

		// final String filtreActiusValorPerDefecte = (String) request.getSession()
		// .getAttribute(Constants.ATTR_FILTRE_ACTIUS_VALOR_PER_DEFECTE);
		return llocFilterForm;
	}

	@Override
	public LlocForm getLlocForm(LlocJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		LoginInfo loginInfo = LoginInfo.getInstance();
		String lang = LocaleContextHolder.getLocale().getLanguage();
		cleanSessionObjectsForMav(request);

		//TODO: demanar a tnadal si això encara és útil
		mav.addObject("isView", __isView);

		setUnitatRefListSelects(lang);

		LlocForm llocForm = super.getLlocForm(_jpa, __isView, request, mav);
		LlocJPA lloc = llocForm.getLloc();

		llocForm.setDeleteButtonVisible(false);

		String jsOpenModalGuardar = "javascript:createDivModal('"
				+ I18NUtils.tradueix("lloc.modificar.guardar.titol") + "', '"
				+ I18NUtils.tradueix("lloc.modificar.guardar.missatge")
				+ "', '', 'llocForm', 'lloc-save-modal-id', 'fa-save');\r\n" +
				"        $('#lloc-save-modal-id').modal('show');\r\n";
		AdditionalButton guardarButton = new AdditionalButton("",
				"genapp.save",
				jsOpenModalGuardar,
				AdditionalButtonStyle.PRIMARY);
		llocForm.setSaveButtonVisible(false);

		final boolean donatdeBaixa = lloc.getDataBaixa() != null || lloc.getDataalta() == null;
		if (llocForm.isNou()) {
			llocForm.addAdditionalButton(guardarButton);

			//TODO: demanar a tnadal si això encara és útil
			mav.addObject("isNew", llocForm.isNou());

			mav.addObject("LLOC_CODILLOC_PLACEHOLDER", Constants.LLOC_CODILLOC_PLACEHOLDER);
			mav.addObject("LLOC_CODILLOCPROPI_PLACEHOLDER", Constants.LLOC_CODILLOCPROPI_PLACEHOLDER);

			lloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			lloc.setEntitatID(loginInfo.getEntitatIDActual());

			mav.addObject("historic", new ArrayList<HistoricLloc>());
			List<StringKeyValue> unitatsEntitat = getReferenceListForUnitatID(request, mav, llocForm, null);
			llocForm.setListOfUnitatForUnitatID(unitatsEntitat);
			if (unitatsEntitat.size() > 0) {
				lloc.setUnitatID(Long.parseLong(unitatsEntitat.get(0).key));
			}
			log.info("Unitat ID seleccionada: " + lloc.getUnitatID());

			lloc.setPersonalOamr(PersonalOamrTipus.NO.getValue());
			log.info("Personal OAMR seleccionat per defecte: " + PersonalOamrTipus.NO.getDescripcio());

			// botó donar d'alta lloc
			AdditionalButton donarDeAltaButton = getDonarDeAltaButton(request, true);
			llocForm.addAdditionalButton(donarDeAltaButton);
			llocForm.addReadOnlyField(LlocFields.DATAALTA);

			String nouLlocCodiPropi = llocLogicaEjb.getNouLlocCodiPropi(null, null);
			lloc.setCodiLlocPropi(nouLlocCodiPropi);
		} else {
			// Pipella Funcionari assignat- Obtenir tots els funcionaris relacionats amb el
			// lloc (actuals, sense data fi)
			// Pipella Funcionari històrics - Obtenir tots els funcionaris relacionats amb
			// el lloc
			long llocID = lloc.getLlocID();
			List<FuncionariLlocDAO> funcionaris = llocLogicaEjb.getFuncionarisLlocByLlocID(llocID, true);
			List<FuncionariLlocDAO> funcionarisHistoric = llocLogicaEjb.getFuncionarisLlocByLlocID(llocID);

			log.info("funcionaris per lloc: " + funcionaris.size());
			funcionaris.forEach(funcionari -> {
				log.info(funcionari.getNom() + " " + funcionari.getLlinatge1() + " " + funcionari.getLlinatge2());
			});
			log.info("fi funcionaris per lloc");

			mav.addObject("funcionaris", funcionaris);
			mav.addObject("funcionarisHistoric", funcionarisHistoric);

			// Pipella Habilitacions - Obtenir tots els habilitacions relacionats amb el
			// lloc
			List<Habilitacio> llistaHabilitacions = llocLogicaEjb.getHabilitacionsByLlocID(llocID);
			llistaHabilitacions.forEach(habilitacio -> {
				log.info("Habilitació: " + habilitacio.getCodi());
			});
			mav.addObject("habilitacions", llistaHabilitacions);

			// Pipella Històric - Obtenir tots els canvis realitzats al lloc de feina (ja
			// només ho mostram al mode consulta)
			mav.addObject("historic", new ArrayList<HistoricLloc>());

			// no el volem veure al mode de consulta
			if (!__isView) {
				llocForm.addAdditionalButton(guardarButton);
			}

			// botons donar de baixa/alta (no els volem veure al mode consulta)
			if (!__isView) {
				if (!donatdeBaixa) {
					// Lloc donat d'alta
					// botó donar de baixa lloc
					String urlGoTo = request.getContextPath() + getContextWeb() + "/" + llocID + "/delete/";
					String actionButtonOnClickCallback = "goTo(encodeURI(\\'" + urlGoTo
							+ "\\' + \\'?numerocai=\\' + document.getElementById(\\'numerocai\\').value))";
					String jsOpenModalDonarBaixa = "javascript:createDivModal(traduccions.type['titol.lloc.donarbaixa.continuar'], '"
							+ I18NUtils.tradueix("lloc.donarbaixa.missatgecontinuar", lloc.getCodiLlocPropi()) + "', '"
							+ urlGoTo + "', '', 'lloc-donarbaixa-id', 'fa-laptop-code', '', '"
							+ actionButtonOnClickCallback + "', '" + I18NUtils.tradueix("acceptar") + "');\r\n" + //
							"        $('#lloc-donarbaixa-id').modal('show');\r\n";
					AdditionalButton donarDeBaixaButton = new AdditionalButton("fas fa-laptop-code",
							"lloc.donarbaixa",
							jsOpenModalDonarBaixa,
							AdditionalButtonStyle.DANGER);
					llocForm.addAdditionalButton(donarDeBaixaButton);
				} else {
					// Lloc donat de baixa
					llocForm.addReadOnlyField(LlocFields.DATAALTA);

					// botó donar d'alta lloc
					AdditionalButton donarDeAltaButton = getDonarDeAltaButton(request, false);
					llocForm.addAdditionalButton(donarDeAltaButton);
				}
			} else {
				// Pipella Històric - Obtenir tots els canvis realitzats al lloc de feina (ja
				// només ho mostram al mode consulta)
				List<Select7Values<Long, String, String, String, String, Timestamp, String>> historic = historicLlocEjb
						.getHistoricByLlocId(lloc.getLlocID());
				log.info("HistoricLloc.size: " + historic.size());

				List<HistoricCanvisLlocDAO> historicCanvis = new ArrayList<>();
				historic.forEach(
						x -> {
							log.info("HistoricLloc: " + x.getValue1() + " " + x.getValue2() + " " + x.getValue3()
									+ " " + x.getValue4() + " " + x.getValue5() + " " + x.getValue6());
							try {
								historicCanvis.add(
										new HistoricCanvisLlocDAO(x.getValue1(),
												(x.getValue3() == null ? "" : x.getValue3()) + " "
														+ (x.getValue4() == null ? "" : x.getValue4()) + " "
														+ (x.getValue5() == null ? "" : x.getValue5()),
												x.getValue7(), x.getValue2(), x.getValue6()));
							} catch (I18NException e) {
								log.error("Error al crear HistoricCanvisLlocDAO amb id " + x.getValue1(), e);
								HtmlUtils.saveMessageError(request,
										"Error al crear HistoricCanvisLlocDAO amb data " + x.getValue6());
								try {
									historicCanvis.add(
											new HistoricCanvisLlocDAO(x.getValue1(),
													(x.getValue3() == null ? "" : x.getValue3()) + " "
															+ (x.getValue4() == null ? "" : x.getValue4()) + " "
															+ (x.getValue5() == null ? "" : x.getValue5()),
													"", x.getValue2(), x.getValue6()));
								} catch (I18NException e1) {
									log.error("Error desconegut al crear HistoricCanvisLlocDAO amb id " + x.getValue1(),
											e);
								}
							}
						});

				mav.addObject("diferenciesDictionary", DbDaoDictionaries.HistoricLloc);
				mav.addObject("historic", historicCanvis);
			}

			llocForm.addReadOnlyField(LlocFields.CODILLOC);
			llocForm.addReadOnlyField(LlocFields.EXPANSIO);
		}

		llocForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
				getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));

		llocForm.addReadOnlyField(LlocFields.ENTITATID);
		llocForm.addReadOnlyField(LlocFields.CODILLOCPROPI);
		llocForm.addHiddenField(LlocFields.DATACREACIO);
		// // només el volem veure al mode consulta
		// if (!__isView) {
		// llocForm.addHiddenField(LlocFields.DATABAIXA);
		// }
		llocForm.addReadOnlyField(LlocFields.DATABAIXA);

		llocForm.setCancelButtonVisible(false);
		llocForm.setAttachedAdditionalJspCode(true);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return llocForm;
	}

	private AdditionalButton getDonarDeAltaButton(HttpServletRequest request, boolean isNewIalta) {
		String jsOpenModalDonarAlta = "javascript:createDivModal(traduccions.type['titol.lloc.donaralta.continuar'], '"
				+ I18NUtils.tradueix("lloc.donaralta.missatgecontinuar") + "', '"
				+ "', 'llocForm', 'lloc-donaralta-id', 'fa-laptop-medical', '"
				+ request.getContextPath() + getContextWeb() + (isNewIalta ? "/newialta/" : "/donaralta/")
				+ "', null, '" + I18NUtils.tradueix("acceptar") + "');\r\n" + //
				"        $('#lloc-donaralta-id').modal('show');\r\n";
		AdditionalButton donarDeAltaButton = new AdditionalButton("fas fa-laptop-medical",
				"lloc.donaralta",
				jsOpenModalDonarAlta,
				AdditionalButtonStyle.SUCCESS);
		return donarDeAltaButton;
	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, LlocFilterForm filterForm, List<Lloc> list)
			throws I18NException {

		List<Long> llocsOcupats = funcionariLlocLogicaEjb.executeQuery(FuncionariLlocFields.LLOCID,
				funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent());

		filterForm.getAdditionalButtonsByPK().clear();

		Map<Long, String> mapUnitatSuperior = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();
		Map<Long, String> mapCodiFp = (Map<Long, String>) filterForm.getAdditionalField(2).getValueMap();
		Map<Long, String> mapFuncionari = (Map<Long, String>) filterForm.getAdditionalField(3).getValueMap();
		Map<Long, String> mapHabilitacions = (Map<Long, String>) filterForm.getAdditionalField(4).getValueMap();
		Map<Long, String> mapDarreraModificacio = (Map<Long, String>) filterForm.getAdditionalField(5).getValueMap();

		mapUnitatSuperior.clear();
		mapCodiFp.clear();
		mapFuncionari.clear();
		mapHabilitacions.clear();
		mapDarreraModificacio.clear();

		LoginInfo loginInfo = LoginInfo.getInstance();
		String lang = LocaleContextHolder.getLocale().getLanguage();
		HashMap<Long, Funcionari> llistaFuncionarisActius = llocLogicaEjb.getCurrentFuncionarisByLloc(null,
				loginInfo.getEntitatIDActual());

		Map<Long, Timestamp> mapDarreraModificacioTemp = (Map<Long, Timestamp>) request.getSession()
				.getAttribute("mapDarreraModificacio");
		request.getSession().removeAttribute("mapDarreraModificacio");
		for (Lloc lloc : list) {

			final Long llocID = lloc.getLlocID();
			final boolean donatdeBaixa = lloc.getDataBaixa() != null || lloc.getDataalta() == null;

			if (!llocsOcupats.contains(llocID)) {
				if (!donatdeBaixa) {
					// Botó per assignar funcionari
					filterForm.addAdditionalButtonByPK(llocID,
							new AdditionalButton("fa fa-user-plus", "lloc.assignarfuncionari",
									"/admin/funcionarilloc/assignar/{0}", AdditionalButtonStyle.SECONDARY));
				}
			} else {
				Funcionari f = llistaFuncionarisActius.get(llocID);
				if (f != null && f.getNom() != null) {
					String llinatge2 = f.getLlinatge2();
					String llinatge1 = f.getLlinatge1();
					String nom = f.getNom();
					String nomComplet = (nom != null ? nom : "") + " "
							+ (llinatge1 != null ? llinatge1 : "") + " "
							+ (llinatge2 != null ? llinatge2 : "")
							+ " (" + f.getUsuari() + ")";
					mapFuncionari.put(llocID, nomComplet);
				} else {
					mapFuncionari.put(llocID, "");
				}

				// Botó per a desassignar funcionari
				String jsOpenModalTreureFuncionari = "javascript:createDivModal('"
						+ I18NUtils.tradueix("lloc.treurefuncionari.titol") + "', '"
						+ I18NUtils.tradueix("lloc.treurefuncionari.missatgecontinuar",
								new String[] { (f != null ? f.getNumero() : "NULL"), lloc.getCodiLlocPropi() })
						+ "', '"
						+ request.getContextPath() + FuncionariLlocAdminController.CONTEXTWEB + "/treure/" + llocID
						+ "', null, 'lloc-treurefuncionari-id', 'fa-user-times', '"
						+ "', null, '" + I18NUtils.tradueix("acceptar") + "');\r\n" + //
						"        $('#lloc-treurefuncionari-id').modal('show');\r\n";
				AdditionalButton treureFuncionariButton = new AdditionalButton("fa fa-user-times",
						"lloc.treurefuncionari",
						jsOpenModalTreureFuncionari,
						AdditionalButtonStyle.INFO);
				filterForm.addAdditionalButtonByPK(llocID, treureFuncionariButton);
			}

			// Comprobam els habilitacions assignats a un lloc de feina
			Boolean llocHasHabilitacio = (llocHabilitacioLogicaEjb
					.count(LlocHabilitacioFields.LLOCID.equal(llocID)) > 0);

			if (llocHasHabilitacio) {
				List<Habilitacio> habilitacionsLloc = llocLogicaEjb.getHabilitacionsByLlocID(llocID);
				String habilitacionsLlocStr = "";
				for (Habilitacio habilitacio : habilitacionsLloc) {
					// Long llocHabilitacioID =
					// llocHabilitacioLogicaEjb.getLlocHabilitacioIDByLlocAndHabilitacio(llocID,
					// habilitacio.getHabilitacioID());
					// String urlEsborrar = request.getContextPath() +
					// LlocHabilitacioAdminController.CONTEXTWEB + "/" + llocHabilitacioID
					// + "/delete";
					// String botoEsborrarTitle =
					// I18NUtils.tradueix("lloc.habilitacio.botoEsborrar");
					// String botoEsborrar = "<a style='margin-left:5px;' href='" + urlEsborrar + "'
					// title='"
					// + botoEsborrarTitle
					// + "' alt='" + botoEsborrarTitle
					// + "'><i class='fas fa-times' style='color:white;'></i></a>";
					// habilitacionsLlocStr += "<span class='badge badge-secondary'>" +
					// habilitacio.getCodi() +
					// botoEsborrar + "</span>";
					habilitacionsLlocStr += "<span class='badge badge-secondary'>" + habilitacio.getCodi() + "</span>";
				}
				mapHabilitacions.put(llocID, habilitacionsLlocStr);
			}

			// Unitat Superior
			Unitat unitatAct = unitatEjb.findByPrimaryKey(lloc.getUnitatID());
			if (unitatAct != null) {
				List<Unitat> unitatSuperior = unitatEjb.select(UnitatFields.CODI.equal(unitatAct.getSuperior()));
				if (unitatSuperior != null && unitatSuperior.size() > 0) {
					mapUnitatSuperior.put(llocID,
							unitatSuperior.get(0).getCodi() + " "
									+ (lang == "es" ? unitatSuperior.get(0).getDenominacio()
											: unitatSuperior.get(0).getCooficial()));

				}

			}

			// Darrera modificació
			if (mapDarreraModificacioTemp != null) {
				Timestamp darreraModificacioLloc = mapDarreraModificacioTemp.get(llocID);
				mapDarreraModificacio.put(llocID,
						darreraModificacioLloc != null ? darreraModificacioLloc.toString() : null);
			}

			// Codi LF (codi del lloc + expansió)
			final String codiLloc = lloc.getCodiLloc();
			final String expansio = lloc.getExpansio();
			mapCodiFp.put(llocID, (codiLloc != null ? codiLloc : "")
					+ (expansio != null && !expansio.isEmpty() ? ("-" + expansio) : ""));

			// Afegir el botó d'assignar habilitacions
			// if (!donatdeBaixa) {
			// filterForm.addAdditionalButtonByPK(llocID,
			// new AdditionalButton("far fa-check-square",
			// "habilitacio.assignarhabilitacio",
			// LlocHabilitacioAdminController.CONTEXTWEB + "/assignar/" + llocID,
			// AdditionalButtonStyle.INFO));
			// }
		}
	}

	@Override
	public void preValidate(HttpServletRequest request, LlocForm llocForm, BindingResult result) throws I18NException {
		LlocJPA lloc = llocForm.getLloc();
		lloc.setEntitatID(Long.parseLong(request.getParameter("lloc.entitatID")));

		if (llocForm.isNou()) {
			// CODI LLOC PROPI DE RFHAB
			String codiLloc = lloc.getCodiLloc();
			String expansioLloc = lloc.getExpansio();

			// per passar la validació de genapp necessitam això (encara que la base de
			// dades ho permeti)
			if ((codiLloc == null || codiLloc.isEmpty()) && (expansioLloc == null || expansioLloc.isEmpty())) {
				lloc.setCodiLloc(Constants.CODILLOC_FAKE_BUIT);
			}
		}

		if (String.valueOf(lloc.getPersonalOamr()).equals(TIPUS_PERSONAL_OAMR)) {
			result.rejectValue(LlocFields.PERSONALOAMR.codeLabel, "error.required",
					new Object[] { "Número" },
					"El camp " + I18NUtils.tradueix(LlocFields.PERSONALOAMR.codeLabel) + " és obligatori");
		}
	}

	@Override
	public LlocJPA create(HttpServletRequest request, LlocJPA lloc) throws I18NException, I18NValidationException {
		lloc.setEntitatID(Long.parseLong(request.getParameter("lloc.entitatID")));

		String habilitacionsSeleccionadesId = request.getParameter("habilitacionsSeleccionadesId");
		log.info("Creant Lloc amb habilitacions seleccionades: " + habilitacionsSeleccionadesId);
		String[] llocHabilitacionsSeleccionades = habilitacionsSeleccionadesId.split(",");
		log.info("Creant Lloc amb habilitacions seleccionades List: " + llocHabilitacionsSeleccionades.length);

		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();

		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;
		log.info("Creant HistoricLloc per a CAI: " + numeroCai + " i usuari: " + usuariId);
		LlocJPA newLloc = llocLogicaEjb.createAndHistory(lloc, numeroCai, usuariId, llocHabilitacionsSeleccionades);
		log.info("Lloc creat amb auditoria: " + newLloc.getLlocID());

		return newLloc;
	}

	@Override
	public LlocJPA update(HttpServletRequest request, LlocJPA lloc) throws I18NException, I18NValidationException {
		String habilitacionsSeleccionadesId = request.getParameter("habilitacionsSeleccionadesId");
		log.info("Actualitzant Lloc amb habilitacions seleccionades: " + habilitacionsSeleccionadesId);
		String[] llocHabilitacionsSeleccionades = habilitacionsSeleccionadesId.split(",");
		log.info("Actualitzant Lloc amb habilitacions seleccionades List: " + llocHabilitacionsSeleccionades.length);

		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;
		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();

		log.info("Actualitzant HistoricLloc per a CAI: " + numeroCai + " i usuari: " + usuariId);
		LlocJPA llocActualitzat = (LlocJPA) llocLogicaEjb.updateAndHistory((Lloc) lloc, numeroCai, usuariId,
				llocHabilitacionsSeleccionades);
		log.info("Lloc actualitzat amb auditoria: " + llocActualitzat.getLlocID());
		return llocActualitzat;
	}

	@Override
	public List<StringKeyValue> getReferenceListForPersonalOamr(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		__tmp.add(new StringKeyValue(TIPUS_PERSONAL_OAMR, I18NUtils.tradueix("trieuopcio")));
		__tmp.add(new StringKeyValue(PersonalOamrTipus.NO.getValue().toString(), PersonalOamrTipus.NO.getDescripcio()));
		__tmp.add(new StringKeyValue(PersonalOamrTipus.SI.getValue().toString(), PersonalOamrTipus.SI.getDescripcio()));
		return __tmp;
	}

	@Override
	public void delete(HttpServletRequest request, Lloc lloc) throws I18NException {
		long llocId = lloc.getLlocID();
		log.info("'Esborrant' (donant de baixa) lloc amb ID " + llocId);

		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;

		llocLogicaEjb.donarDeBaixaLlocAndHistory(llocId, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		// createMessageSuccess(request, "success.modification", llocId);
	}

	@RequestMapping(value = "/donaralta", method = RequestMethod.POST)
	public String donarDeAlta(@ModelAttribute LlocForm llocForm, HttpServletRequest request,
			HttpServletResponse response, boolean mostrarMissatgeCreacio) throws I18NException {
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;

		Lloc llocActualitzat = llocLogicaEjb.donarDeAltaAndHistory(llocForm.getLloc().getLlocID(), numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		// createMessageSuccess(request, "success.modification", llocID);
		return getRedirectWhenModified(request, new LlocForm(new LlocJPA(llocActualitzat), false), null,
				mostrarMissatgeCreacio);
	}

	/**
	 * Guarda un nou Lloc i seguidament el dona d'alta.
	 * redirigint-lo a la pantalla de modificació.
	 */
	@RequestMapping(value = "/newialta", method = RequestMethod.POST)
	public String crearLlocIdonarDalta(@ModelAttribute LlocForm llocForm,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("crearLlocIdonarDalta() llocForm: " + llocForm);
		if (!isActiveFormNew()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		LlocJPA lloc = llocForm.getLloc();

		try {
			preValidate(request, llocForm, result);
			getWebValidator().validate(llocForm, result);
			postValidate(request, llocForm, result);

			if (result.hasErrors()) {
				log.info("Errors al validar el formulari: " + result.getAllErrors());
				result.reject("error.form");
				return getTileForm();
			} else {
				lloc = create(request, lloc);
				createMessageSuccess(request, "success.creation", lloc.getLlocID());
				llocForm.setLloc(lloc);

				String redirectStr = donarDeAlta(llocForm, request, response, true);
				getMsgCreated(request, lloc);

				return redirectStr;
			}
		} catch (Throwable __e) {
			if (__e instanceof I18NValidationException) {
				ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException) __e);
				return getTileForm();
			}
			String msg = createMessageError(request, "error.creation", null, __e);
			log.error(msg, __e);
			return getTileForm();
		}
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return "redirect:" + getContextWeb() + "/list/1";
		// return UrlUtils.getRefererRedirect(request, "redirect:" + getContextWeb() +
		// "/list/1");
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		final String filtreOamrValorPerDefecte = (String) request.getSession()
				.getAttribute(Constants.ATTR_FILTRE_OAMR_VALOR_PER_DEFECTE);
		if (filtreOamrValorPerDefecte != null && !filtreOamrValorPerDefecte.isEmpty()) {
			request.getSession().setAttribute("personalOamrSelect", filtreOamrValorPerDefecte);
			log.info("personalOamrSelect ==> " + filtreOamrValorPerDefecte);
		}

		final String filtreUnitatsoValorPerDefecte = (String) request.getSession()
				.getAttribute(Constants.ATTR_FILTRE_UNITATSO_VALOR_PER_DEFECTE);
		if (filtreUnitatsoValorPerDefecte != null && !filtreUnitatsoValorPerDefecte.isEmpty()) {
			request.getSession().setAttribute("unitatIDDesde", filtreUnitatsoValorPerDefecte);
			log.info("unitatIDDesde ==> " + filtreUnitatsoValorPerDefecte);
		}

		final Where defaultCondition = super.getAdditionalCondition(request);

		Where entitatActualWhere = getEntitatActualWhere();
		Where donatsDeBaixa = getAdditionalConditionDonatsDeBaixa(request);
		Where unitatSuperior = getAdditionalConditionUnitatSuperior(request);

		return Where.AND(defaultCondition, donatsDeBaixa, entitatActualWhere, unitatSuperior);
	}

	private Where getAdditionalConditionUnitatSuperior(HttpServletRequest request) {
		// filtrar per unitat superior
		String unitatSuperiorSelectvalue = (StringUtils.isNotEmpty(request.getParameter("unitatSuperiorName")))
				? request.getParameter("unitatSuperiorName")
				: "";
		log.info("unitatSuperiorSelectvalue ==> " + unitatSuperiorSelectvalue);

		Where unitatSuperiorWhere = null;
		if (StringUtils.isNotEmpty(unitatSuperiorSelectvalue)) {
			Long unitatSuperiorId = Long.parseLong(unitatSuperiorSelectvalue);
			UnitatJPA unitatPare = unitatEjb.findByPrimaryKey(unitatSuperiorId);
			log.info("cercam unitat superior amb codi " + unitatPare.getCodi());
			unitatSuperiorWhere = new LlocQueryPath().UNITAT().SUPERIOR().equal(unitatPare.getCodi());
		}
		return unitatSuperiorWhere;
	}

	private Where getEntitatActualWhere() {
		// filtrar per entitat
		LoginInfo loginInfo = LoginInfo.getInstance();

		System.out.println("================================================");
		Long entitatIDActual = loginInfo.getEntitatIDActual();
		System.out.println("ENTITAT ACTUAL: => " + entitatIDActual);
		System.out.println("ENTITAT ID ACTUAL: => " + loginInfo.getEntitatID());
		System.out.println("================================================");

		Where entitatActualWhere = null;
		if (entitatIDActual != null && entitatIDActual > 0) {
			entitatActualWhere = LlocFields.ENTITATID.equal(entitatIDActual);
		}
		return entitatActualWhere;
	}

	public Where getAdditionalConditionDonatsDeBaixa(HttpServletRequest request) throws I18NException {
		// filtrar per donats de baixa (actius)
		String actiusSelectvalue = (StringUtils.isNotEmpty(request.getParameter("actiusSegonsDatabaixaName")))
				? request.getParameter("actiusSegonsDatabaixaName")
				: "";
		log.info("actiusSelectvalue ==> " + actiusSelectvalue);
		final String filtreActiusValorPerDefecte = (String) request.getSession()
				.getAttribute(Constants.ATTR_FILTRE_ACTIUS_VALOR_PER_DEFECTE);

		if (filtreActiusValorPerDefecte != null && !filtreActiusValorPerDefecte.isEmpty()) {
			actiusSelectvalue = filtreActiusValorPerDefecte;
			log.info("actiusSelectvalue ==> " + actiusSelectvalue);
		}

		if ("0".equals(actiusSelectvalue)) {
			return Where.OR(LlocFields.DATABAIXA.isNotNull(), LlocFields.DATAALTA.isNull());
		} else if ("1".equals(actiusSelectvalue)) {
			return Where.AND(LlocFields.DATABAIXA.isNull(), LlocFields.DATAALTA.isNotNull());
		} else {
			log.warn("Mostrant tots DataBaixa");
		}

		return null;
	}

	@Override
	public void postValidate(HttpServletRequest request, LlocForm llocForm, BindingResult result) throws I18NException {
		// per passar la validació de genapp necessitam això (encara que la base de
		// dades ho permeti)
		LlocJPA lloc = llocForm.getLloc();
		if (lloc != null) {
			String codiLloc = lloc.getCodiLloc();
			if (codiLloc != null && codiLloc.equals(Constants.CODILLOC_FAKE_BUIT)) {
				lloc.setCodiLloc(null);
			}
		}

		getNecessaryObjectsForMav(llocForm, request, null);
	}

	@Override
	public void fillReferencesForForm(LlocForm llocForm,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		super.fillReferencesForForm(llocForm, request, mav);

		getNecessaryObjectsForMav(llocForm, request, mav);
	}

	private void cleanSessionObjectsForMav(HttpServletRequest request) {
		request.getSession().removeAttribute("habilitacionsTotes");
		request.getSession().removeAttribute("habilitacions");
		request.getSession().removeAttribute("unitatsPenjantDeLentitat");
		request.getSession().removeAttribute("entitatsPenjantDeLentitat");
	}

	private void getNecessaryObjectsForMav(LlocForm llocForm, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		List<Habilitacio> habilitacionsTotes = habilitacionsEjb.select();
		if (mav == null) {
			request.getSession().setAttribute("habilitacionsTotes", habilitacionsTotes);
		} else {
			mav.addObject("habilitacionsTotes", habilitacionsTotes);
		}

		if (!llocForm.isNou()) {
			List<Habilitacio> llistaHabilitacions = llocLogicaEjb
					.getHabilitacionsByLlocID(llocForm.getLloc().getLlocID());
			if (mav == null) {
				request.getSession().setAttribute("habilitacions", llistaHabilitacions);
			} else {
				mav.addObject("habilitacions", llistaHabilitacions);
			}
		}

		if (llocForm.getListOfUnitatForUnitatID() != null) {
			List<StringKeyValue> unitatsPenjantDeLentitat = llocForm.getListOfUnitatForUnitatID();
			List<Unitat> referenciades = new ArrayList<Unitat>();
			for (StringKeyValue u : unitatsPenjantDeLentitat) {
				referenciades.add(unitatEjb.findByPrimaryKey(Long.parseLong(u.key)));
			}
			if (mav == null) {
				request.getSession().setAttribute("unitatsPenjantDeLentitat", referenciades);
			} else {
				mav.addObject("unitatsPenjantDeLentitat", referenciades);
			}

			List<Entitat> entitats = entitatLogicaEjb.select(EntitatFields.UNITATID
					.in(unitatsPenjantDeLentitat.stream().map(u -> Long.parseLong(u.key)).toArray(Long[]::new)));
			if (mav == null) {
				request.getSession().setAttribute("entitatsPenjantDeLentitat", entitats);
			} else {
				mav.addObject("entitatsPenjantDeLentitat", entitats);
			}
		}
	}

	@Override
	public List<StringKeyValue> getReferenceListForUnitatID(HttpServletRequest request,
			ModelAndView mav, LlocForm llocForm, Where where) throws I18NException {
		if (llocForm.isHiddenField(UNITATID)) {
			return EMPTY_STRINGKEYVALUE_LIST;
		}
		return getUnitatsByEntitatArrel(mav, llocForm.getLloc().getEntitatID());
	}

	public List<StringKeyValue> getUnitatsByEntitatArrel(
			ModelAndView mav, long entitatId) throws I18NException {
		return getUnitatsByEntitatArrel(mav, entitatId, true);
	}

	public List<StringKeyValue> getUnitatsByEntitatArrel(
			ModelAndView mav, long entitatId, boolean setEntitatsToTheModel) throws I18NException {
		List<StringKeyValue> unitatsResult = new ArrayList<>();

		String lang = LocaleContextHolder.getLocale().getLanguage();

		Entitat entitat = entitatLogicaEjb.findByPrimaryKey(entitatId);
		if (entitat == null) {
			log.info("No hi ha entitat associada al lloc de feina");
			return EMPTY_STRINGKEYVALUE_LIST;
		}
		Unitat unitatArrel = unitatEjb.findByPrimaryKey(entitat.getUnitatID());
		if (unitatArrel == null) {
			log.info("No hi ha unitat associada a l'entitat seleccionada");
			return EMPTY_STRINGKEYVALUE_LIST;
		}

		List<Unitat> referenciades = unitatEjb.findAllReferencingUnitats(unitatEjb.select(), unitatArrel.getCodi());

		for (Unitat u : referenciades) {
			// System.out.println("Unitat referenciada: " + u.getCodi());
			unitatsResult.add(new StringKeyValue(String.valueOf(u.getUnitatID()),
					u.getCodi() + " " + (lang == "es" ? u.getDenominacio() : u.getCooficial())));
		}
		mav.addObject("unitatsPenjantDeLentitat", referenciades);
		if (setEntitatsToTheModel) {
			List<Entitat> entitats = entitatLogicaEjb.select(EntitatFields.UNITATID
					.in(unitatsResult.stream().map(u -> Long.parseLong(u.key)).toArray(Long[]::new)));
			mav.addObject("entitatsPenjantDeLentitat", entitats);
		}

		return unitatsResult;
	}

	public List<StringKeyValue> getUnitatsSuperiorsByEntitatArrel(
			ModelAndView mav, long entitatId) throws I18NException {
		List<StringKeyValue> unitatsResult = new ArrayList<>();

		String lang = LocaleContextHolder.getLocale().getLanguage();

		Entitat entitat = entitatLogicaEjb.findByPrimaryKey(entitatId);
		if (entitat == null) {
			log.info("No hi ha entitat associada al lloc de feina");
			return EMPTY_STRINGKEYVALUE_LIST;
		}
		Unitat unitatArrel = unitatEjb.findByPrimaryKey(entitat.getUnitatID());
		if (unitatArrel == null) {
			log.info("No hi ha unitat associada a l'entitat seleccionada");
			return EMPTY_STRINGKEYVALUE_LIST;
		}

		// TODO: FALTARIA AFEGIR VERSIÓ. PERÒ AIXÒ PER QUAN ESTIGUI LA SINCRONITZACIÓ
		// AMB DIR3
		List<Unitat> referenciades = unitatEjb.findAllReferencingUnitats(unitatEjb.select(), unitatArrel.getCodi());

		for (Unitat u : referenciades) {
			if (u.getSuperior() != null) {
				Integer superiorVersio = u.getSuperiorVersio();
				Unitat unitatMare = (superiorVersio != null && superiorVersio > 0)
						? unitatEjb.findByCodiDir3(u.getSuperior(), superiorVersio)
						: unitatEjb.findByCodiDir3(u.getSuperior());
				if (unitatMare == null) {
					log.warn("La unitat " + u.getCooficial() + " amb ID " + u.getUnitatID() + " no te unitat mare.");
					continue;
				}
				unitatsResult.add(new StringKeyValue(String.valueOf(unitatMare.getUnitatID()),
						unitatMare.getCodi() + " "
								+ (lang == "es" ? unitatMare.getDenominacio() : unitatMare.getCooficial())));
			}
		}

		return unitatsResult;
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, LlocForm llocForm) {
		cleanSessionObjectsForMav(request);

		if (llocForm == null || llocForm.getLloc() == null) {
			log.info("LlocForm o LlocJPA no disponibles per redirigir després de la creació.");
			return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCreated(request, llocForm));
		}
		LlocJPA lloc = llocForm.getLloc();

		getMsgCreated(request, lloc);
		return "redirect:" + getContextWeb() + "/" + lloc.getLlocID() + "/edit/";
	}

	private void getMsgCreated(HttpServletRequest request, LlocJPA lloc) {
		String msg = I18NUtils.tradueix("lloc.crear.success",
				new String[] { I18NUtils.tradueix(getEntityNameCode()), lloc.getCodiLlocPropi() });
		HtmlUtils.deleteMessages(request);
		HtmlUtils.saveMessageSuccess(request, msg);
	}

	public String getRedirectWhenModified(HttpServletRequest request, LlocForm llocForm, Throwable __e,
			boolean noModificarMissatges) {
		String redirectStr = getRedirectWhenModified(request, llocForm, __e);
		if (!noModificarMissatges) {
			LlocJPA lloc = llocForm.getLloc();
			String msg = I18NUtils.tradueix("lloc.donaralta.success",
					new String[] { I18NUtils.tradueix(getEntityNameCode()), lloc.getCodiLlocPropi() });
			HtmlUtils.deleteMessages(request);
			HtmlUtils.saveMessageSuccess(request, msg);
		}
		return redirectStr;
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, LlocForm llocForm, Throwable __e) {
		cleanSessionObjectsForMav(request);

		if (llocForm == null || llocForm.getLloc() == null) {
			log.info("LlocForm o LlocJPA no disponibles per redirigir després de la modificació.");
			return UrlUtils.getRefererRedirect(request, super.getRedirectWhenModified(request, llocForm, __e));
		}
		LlocJPA lloc = llocForm.getLloc();
		String msg = I18NUtils.tradueix("model.modificar.success",
				new String[] { I18NUtils.tradueix(getEntityNameCode()),
						I18NUtils.tradueix("lloc.codiLlocPropi").toLowerCase(), lloc.getCodiLlocPropi() });
		HtmlUtils.deleteMessages(request);
		HtmlUtils.saveMessageSuccess(request, msg);
		return "redirect:" + getContextWeb() + "/" + lloc.getLlocID() + "/edit/";
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long llocID, Throwable __e) {
		cleanSessionObjectsForMav(request);
		if (llocID != null) {
			LlocJPA lloc = llocLogicaEjb.findByPrimaryKey(llocID);
			if (lloc == null) {
				String __msg = createMessageError(request, "error.notfound", llocID);
				log.error(__msg);
			} else {
				String msg = I18NUtils.tradueix("lloc.donarbaixa.exit",
						new String[] { I18NUtils.tradueix(getEntityNameCode()), lloc.getCodiLlocPropi() });
				HtmlUtils.deleteMessages(request);
				HtmlUtils.saveMessageSuccess(request, msg);
			}
		}
		return "redirect:" + getContextWeb() + "/" + llocID + "/edit/";
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long llocID) {
		cleanSessionObjectsForMav(request);
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCancel(request, llocID));
	}

	@Override
	public List<Lloc> executeSelect(ITableManager<Lloc, Long> ejb, Where where, OrderBy[] orderBy, Integer itemsPerPage,
			int inici) throws I18NException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		LlocFilterForm llocFilterForm;
		llocFilterForm = (LlocFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
		String orderByCurrent = llocFilterForm.getOrderBy();
		log.info(orderByCurrent);
		OrderType ordenaPerData = null;
		if (orderBy != null) {
			for (OrderBy oBy : orderBy) {
				log.info("##OrderBy## --> " + oBy.javaName);
				if (oBy.javaName.equals("dataCreacio")) {
					ordenaPerData = oBy.orderType;
					break;
				}
			}
		}

		OrderBy orderBy2 = null;
		log.info("##Where## --> " + where.toSQL());

		Where whereAux = null;
		if (ordenaPerData != null) {
			orderBy2 = new OrderBy("max(datacreacio)", ordenaPerData);
		}

		Select2Columns<Long, Timestamp> s;
		s = new Select2Columns<>(new SelectGroupBy<>(HistoricLlocFields.LLOCID),
				new SelectMax<>(HistoricLlocFields.DATACREACIO));

		List<Select2Values<Long, Timestamp>> resultat = historicLlocEjb.executeQuery(s, whereAux, orderBy2);
		// TODO?:aquest resultat hauria de dur també els nulls, però això només per
		// legacy
		if (resultat == null) {
			resultat = new ArrayList<Select2Values<Long, Timestamp>>();
		}

		if (ordenaPerData != null) {
			log.info("sí ordenam per data");

			Integer noApliquenPelsFiltres = 0;
			List<Lloc> result = new ArrayList();
			Map<Long, Timestamp> mapDarreraModificacio = new HashMap<>();
			List<Long> idsAfegits = new ArrayList();
			// TODO: ineficient total, això se carrega tota sa paginació i es recorr sempre
			// tots els resultats...
			for (int i = 0; i < (inici + itemsPerPage + noApliquenPelsFiltres)
					&& i < resultat.size(); i++) {
				Select2Values<Long, Timestamp> select2Values = resultat.get(i);
				log.info(select2Values.getValue1() + " " + select2Values.getValue2());
				Long llocId = select2Values.getValue1();
				Timestamp dataDarreraModificacio = select2Values.getValue2();
				List<Lloc> llocsTrobats = ejb.select(Where.AND(LlocFields.LLOCID.equal(llocId), where));
				if (llocsTrobats != null && llocsTrobats.size() > 0) {
					if (i >= (inici + noApliquenPelsFiltres)) {
						result.add(llocsTrobats.get(0));
						idsAfegits.add(llocId);
						mapDarreraModificacio.put(llocId, dataDarreraModificacio);
					}
				} else {
					noApliquenPelsFiltres++;
				}
			}

			int elementsRestants = itemsPerPage - result.size();
			if (elementsRestants > 0) {
				List<Lloc> llocsTrobats = super.executeSelect(ejb, Where.AND(where, LLOCID.notIn(idsAfegits)), null,
						elementsRestants, inici + result.size());
				log.info("ja tenim el resultat de la select sense ordenació per data");
				// Comprovam si llocsTrobats és null, i si ho és, l'inicialitzam com a llista
				// buida
				if (llocsTrobats == null) {
					llocsTrobats = new ArrayList<>();
				}

				for (Lloc lloc : llocsTrobats) {
					Long llocId = lloc.getLlocID();
					if (!mapDarreraModificacio.containsKey(llocId)) {
						Stream<Select2Values<Long, Timestamp>> resultStream = resultat.stream();
						Select2Values<Long, Timestamp> select2Values = resultStream
								.filter(item -> item.getValue1().equals(llocId)).findFirst().orElse(null);
						Timestamp dataDarreraModificacio = null;
						if (select2Values != null) {
							log.info(select2Values.getValue1() + " " + select2Values.getValue2());
							dataDarreraModificacio = select2Values.getValue2();
						}
						mapDarreraModificacio.put(llocId, dataDarreraModificacio);

						result.add(lloc);
					}
				}
			}

			log.info("acabam iteració de tots els elements");

			request.getSession().setAttribute("mapDarreraModificacio", mapDarreraModificacio);

			return result;
		} else {
			log.info("no ordenam per data");
			List<Lloc> result = super.executeSelect(ejb, where, orderBy, itemsPerPage, inici);
			log.info("ja tenim el resultat de la select sense ordenació per data");

			Map<Long, Timestamp> mapDarreraModificacio = new HashMap<>();
			for (Lloc lloc : result) {
				Long llocId = lloc.getLlocID();
				Stream<Select2Values<Long, Timestamp>> resultStream = resultat.stream();
				Select2Values<Long, Timestamp> select2Values = resultStream
						.filter(item -> item.getValue1().equals(llocId)).findFirst().orElse(null);
				Timestamp dataDarreraModificacio = null;
				if (select2Values != null) {
					log.info(select2Values.getValue1() + " " + select2Values.getValue2());
					dataDarreraModificacio = select2Values.getValue2();
				}
				mapDarreraModificacio.put(llocId, dataDarreraModificacio);
			}

			request.getSession().setAttribute("mapDarreraModificacio", mapDarreraModificacio);

			return result;
		}
	}

	public void initBinderDateTiemComAdate(WebDataBinder binder) {
		binder.registerCustomEditor(java.sql.Date.class, new CustomDateI18NEditor(I18NUtils.i18NDateFormat, true));
		binder.registerCustomEditor(java.sql.Time.class, new CustomDateI18NEditor(I18NUtils.i18NTimeFormat, true));
		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateI18NEditor(I18NUtils.i18NDateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@Override
	@InitBinder("llocFilterForm")
	public void initBinderFilterForm(WebDataBinder binder) {
		this.initBinderDateTiemComAdate(binder);
	}
}