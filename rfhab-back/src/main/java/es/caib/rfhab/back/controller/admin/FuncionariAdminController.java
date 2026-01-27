package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.fundaciobit.genapp.common.query.selectcolumn.Select7Values;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.FuncionariController;
import es.caib.rfhab.back.form.webdb.FuncionariFilterForm;
import es.caib.rfhab.back.form.webdb.FuncionariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.ActivitatEstat;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.PersonalOamrTipus;
import es.caib.rfhab.commons.utils.RegistreActivitatTipus;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.logic.ActivitatLogicaService;
import es.caib.rfhab.logic.AutoritzacioLogicaService;
import es.caib.rfhab.logic.FuncionariLlocLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.HistoricLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.logic.utils.DbDaoDictionaries;
import es.caib.rfhab.logic.utils.FuncionariLlocLlocDAO;
import es.caib.rfhab.logic.utils.HistoricCanvisFuncionariDAO;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.persistence.FuncionariJPA;

/**
 * @author jagarcia
 * @author jpou
 */
@Controller
@RequestMapping(value = FuncionariAdminController.CONTEXTWEB)
@SessionAttributes(types = { FuncionariForm.class, FuncionariFilterForm.class })
public class FuncionariAdminController extends FuncionariController {

	public static final String CONTEXTWEB = "/admin/funcionari";

	private static final String TIPUS_IDENTIFICACIO_SELECCIONA = "0";

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariLogicaEjb;

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

		String jsOpenModalGuardar = "javascript:createDivModal('"
				+ I18NUtils.tradueix("funcionari.modificar.guardar.titol") + "', '"
				+ I18NUtils.tradueix("funcionari.modificar.guardar.missatge")
				+ "', '', 'funcionariForm', 'funcionari-save-modal-id', 'fa-save');\r\n" +
				"        $('#funcionari-save-modal-id').modal('show');\r\n";
		AdditionalButton guardarButton = new AdditionalButton("",
				"genapp.save",
				jsOpenModalGuardar,
				AdditionalButtonStyle.PRIMARY);
		funcionariForm.setSaveButtonVisible(false);

		if (funcionariForm.isNou()) {
			// botó de guardar
			funcionariForm.addAdditionalButton(guardarButton);
			// botó donar d'alta funcionari
			AdditionalButton donarDeAltaButton = getDonarDeAltaButton(request, true);
			funcionariForm.addAdditionalButton(donarDeAltaButton);

			funcionari.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			funcionari.setDataBaixa(new Timestamp(System.currentTimeMillis()));

			LoginInfo loginInfo = LoginInfo.getInstance();
			Long entitatIDActual = loginInfo.getEntitatIDActual();
			log.info("ENTITAT ID ACTUAL: => " + entitatIDActual);
			if (entitatIDActual != null && entitatIDActual > 0) {
				funcionari.setEntitatID(entitatIDActual);
			}

			String nouFuncionariNumero = funcionariLogicaEjb.getNouFuncionariNumero();
			funcionari.setNumero(nouFuncionariNumero);
		} else {
			long funcionariId = funcionari.getFuncionariID();

			// Pipella Activitat - Obtenir les activitats que té assignades el funcionari
			List<Activitat> activitatsFuncionari = activitatEJB.getActivitatsByFuncionariID(funcionariId);
			mav.addObject("activitatItems", activitatsFuncionari);
			List<StringKeyValue> tipusActivitats = getTipusActivitats();
			mav.addObject("listOfValuesForTipus", tipusActivitats);
			List<StringKeyValue> estatActivitats = getEstatsActivitats();
			mav.addObject("listOfValuesForEstat", estatActivitats);

			// Pipella Lloc assignat - Obtenir tots els llocs relacionats amb el funcionari
			// (actuals, sense data fi)
			// Pipella Funcionari històrics - Obtenir tots els llocs relacionats amb el
			// funcionari
			List<FuncionariLlocLlocDAO> llocsFuncionari = llocEJB.getLlocHistoricByFuncionariID(funcionariId, true);
			List<FuncionariLlocLlocDAO> llocsFuncionariHistoric = llocEJB.getLlocHistoricByFuncionariID(funcionariId,
					false);
			mav.addObject("llocItems", llocsFuncionari);
			mav.addObject("llocsHistoric", llocsFuncionariHistoric);// aquí CAI NOOOO!!!!

			// Pipella Històric - Obtenir tots els canvis realitzats al funcionari
			List<Select7Values<Long, String, String, String, String, Timestamp, String>> historicItems = historicEjb
					.getHistoricByFuncionariId(funcionariId);
			log.info("HistoricFuncionari.size: " + historicItems.size());

			List<HistoricCanvisFuncionariDAO> historicCanvis = new ArrayList<>();
			historicItems.forEach(
					x -> {
						log.info("HistoricFuncionari: " + x.getValue1() + " " + x.getValue2() + " " + x.getValue3()
								+ " " + x.getValue4() + " " + x.getValue5() + " " + x.getValue6() + " "
								+ x.getValue7());
						try {
							historicCanvis.add(
									new HistoricCanvisFuncionariDAO(x.getValue1(),
											(x.getValue3() == null ? "" : x.getValue3()) + " "
													+ (x.getValue4() == null ? "" : x.getValue4()) + " "
													+ (x.getValue5() == null ? "" : x.getValue5()),
											x.getValue7(), x.getValue2(), x.getValue6()));
						} catch (I18NException e) {
							log.error("Error al crear HistoricCanvisFuncionariDAO amb id " + x.getValue1(), e);
							HtmlUtils.saveMessageError(request,
									"Error al crear HistoricCanvisFuncionariDAO amb data " + x.getValue6());
							try {
								historicCanvis.add(
										new HistoricCanvisFuncionariDAO(x.getValue1(),
												(x.getValue3() == null ? "" : x.getValue3()) + " "
														+ (x.getValue4() == null ? "" : x.getValue4()) + " "
														+ (x.getValue5() == null ? "" : x.getValue5()),
												"", x.getValue2(), x.getValue6()));
							} catch (I18NException e1) {
								log.error(
										"Error desconegut al crear HistoricCanvisFuncionariDAO amb id " + x.getValue1(),
										e);
							}
						}
					});

			mav.addObject("diferenciesDictionary", DbDaoDictionaries.HistoricFuncionari);
			mav.addObject("historicItems", historicCanvis);

			// no el volem veure al mode de consulta
			if (!__isView) {
				// botons donar de baixa/alta
				if (funcionari.getDataBaixa() == null) {
					// botó donar de baixa funcionari
					String urlGoTo = request.getContextPath() + getContextWeb() + "/" + funcionariId + "/delete/";
					String actionButtonOnClickCallback = "goTo(encodeURI(\\'" + urlGoTo
							+ "\\' + \\'?numerocai=\\' + document.getElementById(\\'numerocai\\').value))";
					String jsOpenModalDonarBaixa = "javascript:createDivModal('"
							+ I18NUtils.tradueix("funcionari.donarbaixa") + "', '"
							+ I18NUtils.tradueix("funcionari.donarbaixa.missatgecontinuar") + "', '"
							+ urlGoTo + "', '', 'func-donarbaixa-id', 'fa-user-times', '', '"
							+ actionButtonOnClickCallback
							+ "', '" + I18NUtils.tradueix("acceptar") + "');\r\n" + //
							"        $('#func-donarbaixa-id').modal('show');\r\n";
					AdditionalButton donarDeBaixaButton = new AdditionalButton("fas fa-user-times",
							"funcionari.donarbaixa",
							jsOpenModalDonarBaixa,
							AdditionalButtonStyle.DANGER);
					funcionariForm.addAdditionalButton(donarDeBaixaButton);
				} else {
					// No es poden modificar les dades d’una persona que està Inactiva #110 (2a
					// revisió)
					funcionariForm.setAllFieldsReadOnly(FuncionariFields.ALL_FUNCIONARI_FIELDS);
					// botó donar d'alta funcionari
					AdditionalButton donarDeAltaButton = getDonarDeAltaButton(request, false);
					funcionariForm.addAdditionalButton(donarDeAltaButton);
				}

				// botó de guardar
				funcionariForm.addAdditionalButton(guardarButton);
			}
		}
		mav.addObject("FUNCIONARI_NUMERO_PLACEHOLDER", Constants.FUNCIONARI_NUMERO_PLACEHOLDER);

		funcionariForm.addReadOnlyField(FuncionariFields.DATACREACIO);
		funcionariForm.addHiddenField(FuncionariFields.DATACREACIO);

		funcionariForm.addReadOnlyField(FuncionariFields.DATABAIXA);
		funcionariForm.addHiddenField(FuncionariFields.DATABAIXA);

		funcionariForm.addReadOnlyField(FuncionariFields.NUMERO);

		funcionariForm.addReadOnlyField(FuncionariFields.ENTITATID);
		funcionariForm.addHiddenField(FuncionariFields.ENTITATID);

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

	private AdditionalButton getDonarDeAltaButton(HttpServletRequest request, boolean isNewIalta) {
		String jsOpenModalDonarAlta = "javascript:createDivModal('"
				+ I18NUtils.tradueix("funcionari.donaralta")
				+ "','" + I18NUtils.tradueix("funcionari.donaralta.missatgecontinuar") + "', '"
				+ "', 'funcionariForm', 'func-donaralta-id', 'fa-user-plus', '"
				+ request.getContextPath() + getContextWeb() + (isNewIalta ? "/newialta/" : "/donaralta/")
				+ "', null, '" + I18NUtils.tradueix("acceptar") + "');\r\n" + //
				"        $('#func-donaralta-id').modal('show');\r\n";
		AdditionalButton donarDeAltaButton = new AdditionalButton("fas fa-user-plus",
				"funcionari.donaralta",
				jsOpenModalDonarAlta,
				AdditionalButtonStyle.SUCCESS);
		return donarDeAltaButton;
	}

	@Override
	public FuncionariFilterForm getFuncionariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		FuncionariFilterForm funcionariFilterForm = super.getFuncionariFilterForm(pagina, mav, request);

		String oamrSelectvalue = (StringUtils.isNotEmpty(request.getParameter("lloc.personalOamr")))
				? request.getParameter("lloc.personalOamr")
				: "";
		request.getSession().removeAttribute(Constants.ATTR_FILTRE_FOAMR_VALOR_PER_DEFECTE);
		String actiusSelectvalue = (StringUtils.isNotEmpty(request.getParameter("actiusSegonsDatabaixaName")))
				? request.getParameter("actiusSegonsDatabaixaName")
				: "";
		request.getSession().removeAttribute(Constants.ATTR_FILTRE_FACTIUS_VALOR_PER_DEFECTE);
		String assignatsSelectvalue = (StringUtils.isNotEmpty(request.getParameter("assignatsAllocName")))
				? request.getParameter("assignatsAllocName")
				: "";
		request.getSession().removeAttribute(Constants.ATTR_FILTRE_FASSIGNATS_VALOR_PER_DEFECTE);

		if (funcionariFilterForm.isNou()) {

			funcionariFilterForm.addHiddenField(FUNCIONARIID);
			funcionariFilterForm.addHiddenField(DATACREACIO);
			funcionariFilterForm.addHiddenField(TIPUSIDENTIFICADOR);
			// funcionariFilterForm.addHiddenField(IDENTIFICADOR);
			funcionariFilterForm.addHiddenField(CORREU);
			funcionariFilterForm.addHiddenField(ENTITATID);
			funcionariFilterForm.addHiddenField(OBSERVACIONS);
			// funcionariFilterForm.addHiddenField(DATABAIXA);//la necessitam per marcar la
			// fila en gris. L'ocultarem al funcionariListModificable. NO BORRAR AQUEST
			// COMENTARI!!!

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

			// TODO: no canvia el nom del filtre, només el de la columna #111
			// funcionariFilterForm.addLabel(FuncionariFields.USUARI, "nomlabel");
			// funcionariFilterForm.setLabels();
		}
		request.getSession().setAttribute(Constants.ATTR_FILTRE_FOAMR_VALOR_PER_DEFECTE, oamrSelectvalue);
		request.getSession().setAttribute(Constants.ATTR_FILTRE_FACTIUS_VALOR_PER_DEFECTE, actiusSelectvalue);
		request.getSession().setAttribute(Constants.ATTR_FILTRE_FASSIGNATS_VALOR_PER_DEFECTE, assignatsSelectvalue);

		funcionariFilterForm.setVisibleExportList(true);

		funcionariFilterForm.setDeleteButtonVisible(false);
		funcionariFilterForm.setVisibleMultipleSelection(false);
		funcionariFilterForm.setDeleteSelectedButtonVisible(false);
		funcionariFilterForm.setViewButtonVisible(true);
		funcionariFilterForm.setAttachedAdditionalJspCode(true);

		// funcionariFilterForm.setActionsRenderer(FuncionariFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return funcionariFilterForm;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		final String filtreOamrValorPerDefecte = (String) request.getSession()
				.getAttribute(Constants.ATTR_FILTRE_FOAMR_VALOR_PER_DEFECTE);
		if (filtreOamrValorPerDefecte != null && !filtreOamrValorPerDefecte.isEmpty()) {
			request.getSession().setAttribute("lloc.personalOamr", filtreOamrValorPerDefecte);
			log.info("lloc.personalOamr ==> " + filtreOamrValorPerDefecte);
		}

		final String filtreActiusValorPerDefecte = (String) request.getSession()
				.getAttribute(Constants.ATTR_FILTRE_FACTIUS_VALOR_PER_DEFECTE);
		if (filtreActiusValorPerDefecte != null && !filtreActiusValorPerDefecte.isEmpty()) {
			request.getSession().setAttribute("actiusSegonsDatabaixaName", filtreActiusValorPerDefecte);
			log.info("actiusSegonsDatabaixaName ==> " + filtreActiusValorPerDefecte);
		}

		final String filtreAssignatsValorPerDefecte = (String) request.getSession()
				.getAttribute(Constants.ATTR_FILTRE_FASSIGNATS_VALOR_PER_DEFECTE);
		if (filtreAssignatsValorPerDefecte != null && !filtreAssignatsValorPerDefecte.isEmpty()) {
			request.getSession().setAttribute("assignatsAllocName", filtreAssignatsValorPerDefecte);
			log.info("assignatsAllocName ==> " + filtreAssignatsValorPerDefecte);
		}

		final Where defaultCondition = super.getAdditionalCondition(request);
		final Where entitatIdActualWhere = getEntitatIdActualWhere();
		final Where personalOamrWhere = getPersonalOamrWhere(request);
		final Where donatsDeBaixaWhere = getAdditionalConditionDonatsDeBaixa(request);
		final Where assignatsAllocDeFeinaWhere = getAdditionalConditionAssignats(request);
		final Where nomCompletWhere = getAdditionalConditionFuncionariNom(request);

		log.debug("defaultCondition ==> " + (defaultCondition != null ? defaultCondition.toSQL() : "null"));
		log.debug("donatsDeBaixaWhere ==> " + (donatsDeBaixaWhere != null ? donatsDeBaixaWhere.toSQL() : "null"));
		log.debug("personalOamrWhere ==> " + (personalOamrWhere != null ? personalOamrWhere.toSQL() : "null"));
		log.debug("entitatIdActualWhere ==> " + (entitatIdActualWhere != null ? entitatIdActualWhere.toSQL() : "null"));
		log.debug("AssignatsAllocDeFeinaWhere ==> "
				+ (assignatsAllocDeFeinaWhere != null ? assignatsAllocDeFeinaWhere.toSQL() : "null"));
		log.debug("nomCompletWhere ==> " + (nomCompletWhere != null ? nomCompletWhere.toSQL() : "null"));

		return Where.AND(nomCompletWhere, personalOamrWhere, donatsDeBaixaWhere, assignatsAllocDeFeinaWhere,
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
		String personalOamr = (StringUtils.isNotEmpty(request.getParameter("lloc.personalOamr")))
				? request.getParameter("lloc.personalOamr")
				: "";
		log.info("personalOamr ==> " + personalOamr);

		return funcionariLogicaEjb.isPersonalOamrWhere(
				(personalOamr == null || personalOamr.isEmpty()) ? null : PersonalOamrTipus.fromString(personalOamr));
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

	public Where getAdditionalConditionFuncionariNom(HttpServletRequest request)
			throws I18NException {
		String filtreFuncionarisNom = request.getParameter("funcionarisNom");
		final String funcionarisInput = (StringUtils.isNotEmpty(filtreFuncionarisNom))
				? filtreFuncionarisNom.toUpperCase()
				: "";
		log.info("funcionarisInput nom ==> " + funcionarisInput);
		Where funcionariFuncionarisNomWhere = null;
		try {
			funcionariFuncionarisNomWhere = getFuncionarisByFuncionariNomCompletWhere(funcionarisInput);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			log.warn("Mostrant tots funcionaris nom");
		}
		return funcionariFuncionarisNomWhere;
	}

	public Where getFuncionarisByFuncionariNomCompletWhere(String funcionariNom)
			throws I18NException, NoSuchFieldException {
		Where funcionariFuncionarisNomWhere = null;
		if (!"".equals(funcionariNom)) {
			List<Long> funcionarisId = funcionariLogicaEjb.getFuncionarisIdsByNomComplet(funcionariNom);
			if (funcionarisId != null) {
				funcionariFuncionarisNomWhere = FuncionariFields.FUNCIONARIID.in(funcionarisId);
			}
		} else {
			log.warn("Mostrant tots funcionaris nom");
		}
		return funcionariFuncionarisNomWhere;
	}

	@Override
	public void delete(HttpServletRequest request, Funcionari funcionari) throws I18NException {
		long funcionariId = funcionari.getFuncionariID();
		log.info("'Esborrant' (donant de baixa) funcionari amb ID " + funcionariId);

		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;

		funcionariLogicaEjb.donarDeBaixaFuncionariAndHistory(funcionari, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		// createMessageSuccess(request, "success.modification", funcionariId);
	}

	@RequestMapping(value = "/donaralta")
	public String donarDeAlta(@ModelAttribute FuncionariForm funcionariForm, HttpServletRequest request,
			HttpServletResponse response) throws I18NException {
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;

		long funcionariId = funcionariForm.getFuncionari().getFuncionariID();
		Funcionari funcionari = funcionariLogicaEjb.donarDeAltaAndHistory(funcionariId, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		String redirection = getRedirectWhenModified(request, null, null);
		String msg = I18NUtils.tradueix("funcionari.donaralta.success",
				new String[] { funcionari.getNumero() });
		HtmlUtils.deleteMessages(request);
		HtmlUtils.saveMessageSuccess(request, msg);

		return redirection;
	}

	@Override
	public FuncionariJPA create(HttpServletRequest request, FuncionariJPA funcionari)
			throws I18NException, I18NValidationException {
		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numerocai")))
				? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;
		log.info("Creant Funcionari i Historic per a CAI: " + numeroCai + " i usuari: " + usuariId);
		FuncionariJPA newFuncionari = funcionariLogicaEjb.createAndHistory(funcionari, numeroCai, usuariId);

		return newFuncionari;
	}

	/**
	 * Guardar un nou Funcionari i seguidament el dona d'alta
	 * redirigint-lo a la pantalla de modificació.
	 */
	@RequestMapping(value = "/newialta", method = RequestMethod.POST)
	public String crearFuncionariIdonarDalta(@ModelAttribute FuncionariForm funcionariForm,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("crearFuncionariIdonarDalta() funcionariForm: " + funcionariForm);
		if (!isActiveFormNew()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		FuncionariJPA funcionari = funcionariForm.getFuncionari();

		try {
			preValidate(request, funcionariForm, result);
			getWebValidator().validate(funcionariForm, result);
			postValidate(request, funcionariForm, result);

			if (result.hasErrors()) {
				log.info("Errors al validar el formulari: " + result.getAllErrors());
				result.reject("error.form");
				return getTileForm();
			} else {
				funcionari = create(request, funcionari);
				createMessageSuccess(request, "success.creation", funcionari.getFuncionariID());
				funcionariForm.setFuncionari(funcionari);

				String redirectStr = donarDeAlta(funcionariForm, request, response);
				// getMsgCreated(request, funcionari);

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
		__tmp.add(new StringKeyValue(PersonalOamrTipus.NO.getValue().toString(), PersonalOamrTipus.NO.getDescripcio()));
		__tmp.add(new StringKeyValue(PersonalOamrTipus.SI.getValue().toString(), PersonalOamrTipus.SI.getDescripcio()));
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

	public List<StringKeyValue> getTipusActivitats() throws I18NException {
		List<StringKeyValue> tipusActivitatsResult = new ArrayList<StringKeyValue>();

		for (RegistreActivitatTipus rat : RegistreActivitatTipus.values()) {
			tipusActivitatsResult.add(new StringKeyValue(String.valueOf(rat.getValue()),
					rat.getDescripcio()));
		}

		return tipusActivitatsResult;
	}

	public List<StringKeyValue> getEstatsActivitats() throws I18NException {
		List<StringKeyValue> estatsActivitatsResult = new ArrayList<StringKeyValue>();

		for (ActivitatEstat ae : ActivitatEstat.values()) {
			estatsActivitatsResult.add(new StringKeyValue(String.valueOf(ae.getValue()),
					ae.name()));
		}

		return estatsActivitatsResult;
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
		String numeroCai = (!Utils.isEmpty(request.getParameter("numerocai"))) ? request.getParameter("numerocai")
				: Constants.NUMEROCAI_BUIT;
		return (FuncionariJPA) funcionariLogicaEjb.updateAndHistory((Funcionari) funcionari, numeroCai, usuariId);
	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, FuncionariFilterForm filterForm,
			List<Funcionari> list) throws I18NException {

		Map<Long, String> mapCodiLloc = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();
		Map<Long, String> mapUnitatOrganica = (Map<Long, String>) filterForm.getAdditionalField(2).getValueMap();
		Map<Long, String> mapOamr = (Map<Long, String>) filterForm.getAdditionalField(3).getValueMap();

		mapCodiLloc.clear();
		mapUnitatOrganica.clear();
		mapOamr.clear();

		final String CREU_NO_ICONA = "<i class=\"fa fa-times\"></i>";
		final String TICK_SI_ICONA = "<i class=\"fa fa-check\"></i>";
		final String BLANK_ICONA = "";// "<i class=\"fa fa-minus\"></i>";

		for (Funcionari funcionari : list) {
			final Long funcionariID = funcionari.getFuncionariID();

			// LoginInfo.getInstance().getEntitatIDActual()
			List<Lloc> llocs = llocEJB.getLlocByFuncionariID(funcionariID, true);
			if (llocs != null && llocs.size() > 0) {
				if (llocs.size() > 1) {
					log.error("###postList### Només hi hauria d'haver un lloc actiu pel funcionari amb id "
							+ funcionariID);
				}
				Lloc lloc = llocs.get(0);
				mapCodiLloc.put(funcionariID, lloc.getCodiLloc());
				mapUnitatOrganica.put(funcionariID, unitatEJB.findByPrimaryKey(lloc.getUnitatID()).getCodi());
				mapOamr.put(funcionariID,
						((PersonalOamrTipus.fromValue(lloc.getPersonalOamr()) == PersonalOamrTipus.SI) ? TICK_SI_ICONA
								: CREU_NO_ICONA));
			} else {
				mapCodiLloc.put(funcionariID, "");
				mapUnitatOrganica.put(funcionariID, "");
				mapOamr.put(funcionariID, BLANK_ICONA);
			}
		}
	}

	private void getMsgCreated(HttpServletRequest request, FuncionariJPA funcionari) {
		String msg = I18NUtils.tradueix("funcionari.crear.success",
				new String[] { funcionari.getNumero() });
		HtmlUtils.deleteMessages(request);
		HtmlUtils.saveMessageSuccess(request, msg);
	}

	private void getMsgDeleted(HttpServletRequest request, FuncionariJPA funcionari) {
		String msg = I18NUtils.tradueix("funcionari.donarbaixa.success",
				new String[] { funcionari.getNumero() });
		HtmlUtils.deleteMessages(request);
		HtmlUtils.saveMessageSuccess(request, msg);
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, FuncionariForm funcionariForm) {
		FuncionariJPA funcionari = funcionariForm.getFuncionari();

		getMsgCreated(request, funcionari);
		return "redirect:" + getContextWeb() + "/new/";
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, FuncionariForm funcionariForm, Throwable __e) {
		if (funcionariForm == null || funcionariForm.getFuncionari() == null) {
			log.info("FuncionariForm o FuncionariJPA no disponibles per a redirigir després de la modificació.");
			return UrlUtils.getRefererRedirect(request, super.getRedirectWhenModified(request, funcionariForm, __e));
		}
		FuncionariJPA funcionari = funcionariForm.getFuncionari();
		String msg = I18NUtils.tradueix("funcionari.modificar.guardar.success",
				new String[] { funcionari.getNumero() });
		HtmlUtils.deleteMessages(request);
		HtmlUtils.saveMessageSuccess(request, msg);
		return "redirect:" + getContextWeb() + "/" + funcionari.getFuncionariID() + "/edit/";
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long funcionariID, Throwable __e) {
		if (funcionariID != null) {
			FuncionariJPA funcionari = funcionariLogicaEjb.findByPrimaryKey(funcionariID);
			if (funcionari != null) {
				getMsgDeleted(request, funcionari);
			}
		}
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenDelete(request, funcionariID, __e));
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long funcionariID) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCancel(request, funcionariID));
	}

}
