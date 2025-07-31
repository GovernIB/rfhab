package es.caib.rfhab.back.controller.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.LlocController;
import es.caib.rfhab.back.form.webdb.LlocFilterForm;
import es.caib.rfhab.back.form.webdb.LlocForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.back.utils.UrlUtils;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.ejb.EntitatService;
import es.caib.rfhab.logic.FuncionariLlocLogicaService;
import es.caib.rfhab.logic.HistoricLlocLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.logic.LlocRolLogicaService;
import es.caib.rfhab.logic.UnitatLogicaUserService;
import es.caib.rfhab.logic.utils.FuncionariLlocDAO;
import es.caib.rfhab.model.entity.Entitat;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocRolFields;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.model.fields.UnitatFields;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;
import es.caib.rfhab.pluginsib.rolsac.RolsacPlugin;

/**
 * @author jagarcia
 * @author jpou
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

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	protected FuncionariLlocLogicaService funcionariLlocLogicaEjb;

	@EJB(mappedName = LlocRolLogicaService.JNDI_NAME)
	protected LlocRolLogicaService llocRolLogicaEjb;

	@EJB(mappedName = UnitatLogicaUserService.JNDI_NAME)
	protected UnitatLogicaUserService unitatEjb;

	@EJB(mappedName = EntitatService.JNDI_NAME)
	protected EntitatService entitatEjb;

	protected RolsacPlugin rolsacPlugin = null;

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
				AdditionalField<Long, String> adfield0 = new AdditionalField<Long, String>();
				adfield0.setCodeName(UnitatFields.SUPERIOR.codeLabel);
				adfield0.setPosition(1);
				adfield0.setEscapeXml(false);
				adfield0.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield0);
			}

			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(FuncionariLlocFields.FUNCIONARIID.codeLabel);
				adfield.setPosition(2);
				adfield.setEscapeXml(false);
				adfield.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield);
			}

			{
				AdditionalField<Long, String> adfield2 = new AdditionalField<Long, String>();
				adfield2.setCodeName(RolFields._TABLE_TRANSLATION);
				adfield2.setPosition(3);
				// adfield2.setOrderBy(RolFields.CODI);
				adfield2.setEscapeXml(false);
				adfield2.setValueMap(new HashMap<Long, String>());
				llocFilterForm.addAdditionalField(adfield2);
			}
		}
		List<StringKeyValue> _unitatsTemp = getUnitatsByEntitatArrel(mav, LoginInfo.getInstance().getEntitatIDActual(),
				false);
		Map<String, String> unitatsFiltreCerca = Utils.listToMap(_unitatsTemp);
		unitatsFiltreCerca.put("", I18NUtils.tradueix("tots"));
		mav.addObject("unitatsFiltreCerca", unitatsFiltreCerca);
		log.info("unitatsFiltreCerca: " + unitatsFiltreCerca.size());
		log.info(unitatsFiltreCerca);

		llocFilterForm.setDeleteButtonVisible(false);
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
		LlocJPA lloc = llocForm.getLloc();

		llocForm.setDeleteButtonVisible(false);

		if (llocForm.isNou()) {
			mav.addObject("isNew", llocForm.isNou());

			lloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			lloc.setEntitatID(LoginInfo.getInstance().getEntitatIDActual());

			mav.addObject("historic", new ArrayList<HistoricLloc>());
			List<StringKeyValue> unitatsEntitat = getReferenceListForUnitatID(request, mav, llocForm, null);
			llocForm.setListOfUnitatForUnitatID(unitatsEntitat);
			if (unitatsEntitat.size() > 0) {
				lloc.setUnitatID(Long.parseLong(unitatsEntitat.get(0).key));
			}
			log.info("Unitat ID seleccionada: " + lloc.getUnitatID());
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

			// Pipella Rols - Obtenir tots els rols relacionats amb el lloc
			List<Rol> llistaRols = llocLogicaEjb.getRolsByLlocID(llocID);
			llistaRols.forEach(rol -> {
				log.info("Rol: " + rol.getCodi());
			});
			mav.addObject("rols", llistaRols);

			// Pipella Autoritzacions - procediments de Rolsac autoritzats en funció del
			// codi DIR3 de la unitat
			HashMap<String, String> procediments = new HashMap<String, String>();
			if (lloc.getUnitatID() > 0) {
				try {
					List<Unitat> unitatsDir3 = unitatEjb.select(UnitatFields.UNITATID.equal(lloc.getUnitatID()));
					if (unitatsDir3.size() > 0) {
						procediments = getProcedimentsByDir3(unitatsDir3.get(0).getCodi());
						procediments.forEach((clave, valor) -> {
							log.info("Procediment: " + clave + " - " + valor);
						});
					}
				} catch (Exception e) {
					log.error(e);
				}
			}
			mav.addObject("procediments", procediments);

			// Pipella Històric - Obtenir tots els canvis realitzats al lloc de feina
			List<Select6Values<Long, String, String, String, String, Timestamp>> historic = historicLlocEjb
					.getHistoricByLlocId(lloc.getLlocID());
			log.info("HistoricLloc.size: " + historic.size());

			historic.forEach(x -> log.info("HistoricLloc: " + x.getValue1() + " " + x.getValue2() + " " + x.getValue3()
					+ " " + x.getValue4() + " " + x.getValue5() + " " + x.getValue6()));

			mav.addObject("historic", historic);

			// botons donar de baixa/alta
			if (lloc.getDataBaixa() == null) {
				// botó donar de baixa lloc
				String jsOpenModalDonarBaixa = "javascript:createDivModal(traduccions.type['titol.lloc.donarbaixa.continuar'], traduccions.type['missatge.lloc.donarbaixa.continuar'], '"
						+ request.getContextPath() + getContextWeb() + "/" + llocID + "/delete/"
						+ "', '', 'lloc-donarbaixa-id', 'fa-laptop-code');\r\n" + //
						"        $('#lloc-donarbaixa-id').modal('show');\r\n";
				AdditionalButton donarDeBaixaButton = new AdditionalButton("fas fa-laptop-code",
						"lloc.donarbaixa",
						jsOpenModalDonarBaixa,
						AdditionalButtonStyle.DANGER);
				llocForm.addAdditionalButton(donarDeBaixaButton);
			} else {
				// botó donar d'alta lloc
				String jsOpenModalDonarAlta = "javascript:createDivModal(traduccions.type['titol.lloc.donaralta.continuar'], traduccions.type['missatge.lloc.donaralta.continuar'], '"
						+ request.getContextPath() + getContextWeb() + "/" + llocID + "/donaralta/"
						+ "', '', 'lloc-donaralta-id', 'fa-laptop-medical');\r\n" + //
						"        $('#lloc-donaralta-id').modal('show');\r\n";
				AdditionalButton donarDeAltaButton = new AdditionalButton("fas fa-laptop-medical",
						"lloc.donaralta",
						jsOpenModalDonarAlta,
						AdditionalButtonStyle.DANGER);
				llocForm.addAdditionalButton(donarDeAltaButton);
			}
		}

		mav.addObject("lloc", lloc);
		if (lloc != null && lloc.getPersonalOamr() > 0) {
			mav.addObject("isOamr", 1);
		} else {
			mav.addObject("isOamr", 0);
		}

		llocForm.addAdditionalButton(new AdditionalButton(" fas fa-long-arrow-alt-left", "tornar",
				getContextWeb() + "/tornar", AdditionalButtonStyle.SECONDARY));

		llocForm.addReadOnlyField(ENTITATID);
		llocForm.addReadOnlyField(DATACREACIO);

		llocForm.setCancelButtonVisible(false);
		llocForm.setAttachedAdditionalJspCode(true);

		request.getSession().setAttribute(Constants.REFERER_SESSION_ATTRIBUTE, request.getHeader("referer"));

		return llocForm;
	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, LlocFilterForm filterForm, List<Lloc> list)
			throws I18NException {

		List<Long> llocsOcupats = funcionariLlocLogicaEjb.executeQuery(FuncionariLlocFields.LLOCID,
				funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent());

		filterForm.getAdditionalButtonsByPK().clear();

		Map<Long, String> mapUnitatSuperior = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();
		Map<Long, String> mapFuncionari = (Map<Long, String>) filterForm.getAdditionalField(2).getValueMap();
		Map<Long, String> mapRols = (Map<Long, String>) filterForm.getAdditionalField(3).getValueMap();

		mapUnitatSuperior.clear();
		mapFuncionari.clear();
		mapRols.clear();

		HashMap<Long, Funcionari> llistaFuncionarisActius = llocLogicaEjb.getCurrentFuncionarisByLloc(null,
				LoginInfo.getInstance().getEntitatIDActual());

		for (Lloc lloc : list) {

			final Long llocID = lloc.getLlocID();

			if (!llocsOcupats.contains(llocID)) {

				// Botó per assignar funcionari
				filterForm.addAdditionalButtonByPK(llocID,
						new AdditionalButton("fa fa-user-plus", "lloc.assignarfuncionari",
								"/admin/funcionarilloc/assignar/{0}", AdditionalButtonStyle.SECONDARY));

			} else {
				Funcionari f = llistaFuncionarisActius.get(llocID);
				if (f != null && f.getNom() != null) {
					String nom = f.getNom() + " " + f.getLlinatge1() + " " + f.getLlinatge2() + " (" + f.getUsuari()
							+ ")";
					mapFuncionari.put(llocID, nom);
				} else {
					mapFuncionari.put(llocID, "");
				}

				// Botó per desassignar funcionari
				filterForm.addAdditionalButtonByPK(llocID, new AdditionalButton("fa fa-user-times",
						"lloc.treurefuncionari", "/admin/funcionarilloc/treure/{0}", AdditionalButtonStyle.INFO));

			}

			// Botó per donar de baixa un lloc de feina
			filterForm.addAdditionalButtonByPK(llocID, new AdditionalButton("fa fa-pause", "lloc.baixa",
					"/admin/lloc/{0}/delete", AdditionalButtonStyle.DANGER));

			// Comprobam els rols assignats a un lloc de feina
			Boolean llocHasRol = (llocRolLogicaEjb.count(LlocRolFields.LLOCID.equal(llocID)) > 0);

			if (llocHasRol) {
				List<Rol> rolsLloc = llocLogicaEjb.getRolsByLlocID(llocID);
				String rolsLlocStr = "";
				for (Rol rol : rolsLloc) {
					Long llocRolID = llocRolLogicaEjb.getLlocRolIDByLlocAndRol(llocID, rol.getRolID());
					String urlEsborrar = request.getContextPath() + LlocRolAdminController.CONTEXTWEB + "/" + llocRolID
							+ "/delete";
					String botoEsborrarTitle = I18NUtils.tradueix("lloc.habilitacio.botoEsborrar");
					String botoEsborrar = "<a style='margin-left:5px;' href='" + urlEsborrar + "' title='"
							+ botoEsborrarTitle
							+ "' alt='" + botoEsborrarTitle
							+ "'><i class='fas fa-times' style='color:white;'></i></a>";
					rolsLlocStr += "<span class='badge badge-secondary'>" + rol.getCodi() + botoEsborrar + "</span>";
				}
				mapRols.put(llocID, rolsLlocStr);
			}

			// Unitat Superior
			Unitat unitatAct = unitatEjb.findByPrimaryKey(lloc.getUnitatID());
			if (unitatAct != null) {
				List<Unitat> unitatSuperior = unitatEjb.select(UnitatFields.CODI.equal(unitatAct.getSuperior()));
				if (unitatSuperior != null && unitatSuperior.size() > 0)
					mapUnitatSuperior.put(llocID,
							unitatSuperior.get(0).getCodi() + " " + unitatSuperior.get(0).getDenominacio());

			}

			// Afegir el botó d'assignar rols
			filterForm.addAdditionalButtonByPK(llocID, new AdditionalButton("far fa-check-square", "rol.assignarrol",
					LlocRolAdminController.CONTEXTWEB + "/assignar/" + llocID, AdditionalButtonStyle.INFO));
		}
	}

	public void preValidate(HttpServletRequest request, LlocForm llocForm, BindingResult result) throws I18NException {
		LlocJPA lloc = llocForm.getLloc();
		lloc.setEntitatID(Long.parseLong(request.getParameter("lloc.entitatID")));
	}

	@Override
	public LlocJPA create(HttpServletRequest request, LlocJPA lloc) throws I18NException, I18NValidationException {
		lloc.setEntitatID(Long.parseLong(request.getParameter("lloc.entitatID")));
		LlocJPA newLloc = super.create(request, lloc);
		log.info("Lloc creat: " + lloc.getLlocID());

		Long usuariId = LoginInfo.getInstance().getUsuariPersona().getUsuariID();

		String numeroCai = request.getParameter("numerocai");
		log.info("Creant HistoricLloc per a CAI: " + numeroCai + " i usuari: " + usuariId);
		HistoricLlocJPA historicLloc = llocLogicaEjb.createAndHistory((Lloc) newLloc, numeroCai, usuariId);
		log.info("HistoricLloc creat: " + historicLloc.getHistoricllocID());

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
		long llocId = lloc.getLlocID();
		log.info("'Esborrant' (donant de baixa) lloc amb ID " + llocId);

		// TODO:revisar això #38
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numeroCai")))
				? request.getParameter("numeroCai")
				: "";

		llocLogicaEjb.donarDeBaixaLlocAndHistory(llocId, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		createMessageSuccess(request, "success.modification", llocId);// funcionari.donaralta.exit
	}

	@RequestMapping(value = "/{llocID}/donaralta")
	public String donarDeAlta(@PathVariable("llocID") java.lang.Long llocID, HttpServletRequest request,
			HttpServletResponse response) throws I18NException {
		// TODO:revisar això #38
		final String numeroCai = (StringUtils.isNotEmpty(request.getParameter("numeroCai")))
				? request.getParameter("numeroCai")
				: "";

		llocLogicaEjb.donarDeAltaAndHistory(llocID, numeroCai,
				LoginInfo.getInstance().getUsuariPersona().getUsuariID());

		createMessageSuccess(request, "success.modification", llocID);// funcionari.donaralta.exit
		return getRedirectWhenModified(request, null, null);
	}

	@RequestMapping(value = "/tornar", method = RequestMethod.GET)
	public String tornar(HttpServletRequest request) {
		return UrlUtils.getRefererRedirect(request, "redirect:" + getContextWeb() + "/list/1");
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		final Where defaultCondition = super.getAdditionalCondition(request);

		Where entitatActualWhere = getEntitatActualWhere();
		Where donatsDeBaixa = getAdditionalConditionDonatsDeBaixa(request);

		return Where.AND(donatsDeBaixa,
				(entitatActualWhere != null) ? Where.AND(defaultCondition, entitatActualWhere) : defaultCondition);
	}

	private Where getEntitatActualWhere() {
		// filtrar per entitat
		LoginInfo loginInfo = LoginInfo.getInstance();

		System.out.println("================================================");
		System.out.println("ENTITAT ACTUAL: => " + loginInfo.getEntitatIDActual());
		System.out.println("ENTITAT ID ACTUAL: => " + loginInfo.getEntitatID());
		System.out.println("================================================");

		Where entitatActualWhere = null;
		if (loginInfo.getEntitatIDActual() != null && loginInfo.getEntitatIDActual() > 0) {
			entitatActualWhere = LlocFields.ENTITATID.equal(loginInfo.getEntitatIDActual());
		}
		return entitatActualWhere;
	}

	public Where getAdditionalConditionDonatsDeBaixa(HttpServletRequest request) throws I18NException {
		// filtrar per donats de baixa (actius)
		final String actiusSelectvalue = (StringUtils.isNotEmpty(request.getParameter("actiusSegonsDatabaixaName")))
				? request.getParameter("actiusSegonsDatabaixaName")
				: "";
		log.info("actiusSelectvalue ==> " + actiusSelectvalue);

		if ("0".equals(actiusSelectvalue)) {
			return LlocFields.DATABAIXA.isNotNull();
		} else if ("1".equals(actiusSelectvalue)) {
			return LlocFields.DATABAIXA.isNull();
		} else {
			log.warn("Mostrant tots DataBaixa");
		}

		return null;
	}

	private HashMap<String, String> getProcedimentsByDir3(String codiDir3) throws Exception {

		if (rolsacPlugin == null)
			rolsacPlugin = new RolsacPlugin();

		return rolsacPlugin.obtenirProcedimentsByDir3(codiDir3);

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

		Entitat entitat = entitatEjb.findByPrimaryKey(entitatId);
		if (entitat == null) {
			log.info("No hi ha entitat associada al lloc de feina");
			return EMPTY_STRINGKEYVALUE_LIST;
		}
		Unitat unitatArrel = unitatEjb.findByPrimaryKey(entitat.getUnitatID());
		if (unitatArrel == null) {
			log.info("No hi ha unitat associada a l'entitat seleccionada");
			return EMPTY_STRINGKEYVALUE_LIST;
		}

		List<Unitat> referenciades = findAllReferencingUnitats(unitatEjb.select(), unitatArrel.getCodi());

		for (Unitat u : referenciades) {
			// System.out.println("Unitat referenciada: " + u.getCodi());
			unitatsResult.add(new StringKeyValue(String.valueOf(u.getUnitatID()),
					u.getCodi() + " " + u.getCooficial() + " " + u.getDenominacio()));
		}
		mav.addObject("unitats", referenciades);
		if (setEntitatsToTheModel) {
			List<Entitat> entitats = entitatEjb.select(EntitatFields.UNITATID
					.in(unitatsResult.stream().map(u -> Long.parseLong(u.key)).toArray(Long[]::new)));
			mav.addObject("entitats", entitats);
		}

		return unitatsResult;
	}

	public static List<Unitat> findAllReferencingUnitats(List<Unitat> unitats, String codiInicial) {
		List<Unitat> result = new ArrayList<>();
		List<String> codisPendents = new ArrayList<>();
		codisPendents.add(codiInicial);
		boolean entitatArrelAfegida = false;

		while (!codisPendents.isEmpty()) {
			String codiActual = codisPendents.remove(0);

			for (Unitat unitat : unitats) {
				// aquí només hauria d'entrar un pic per afegir la unitat "arrel". LLevar si no
				// es vol aquesta unitat. Però revisar el jsp perquè no està preparat per això,
				// ja que cerca l'entitat "arrel" dins el llistat d'unitats.
				if (!entitatArrelAfegida && codiInicial.equals(unitat.getCodi())) {
					result.add(0, unitat);
					entitatArrelAfegida = true;
				}
				if (codiActual.equals(unitat.getSuperior())) {
					result.add(unitat);
					codisPendents.add(unitat.getCodi());
				}
			}
		}

		return result;
	}

	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, LlocForm llocForm) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCreated(request, llocForm));
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, LlocForm llocForm, Throwable __e) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenModified(request, llocForm, __e));
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long llocID, Throwable __e) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenDelete(request, llocID, __e));
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long llocID) {
		return UrlUtils.getRefererRedirect(request, super.getRedirectWhenCancel(request, llocID));
	}
}