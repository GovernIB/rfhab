package es.caib.rfhab.back.controller.superadmin;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.webdb.UsuariFilterForm;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.logic.UsuariEntitatLogicaService;
import es.caib.rfhab.model.entity.Usuari;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.IdiomaFields;
import es.caib.rfhab.model.fields.UsuariEntitatFields;
import es.caib.rfhab.persistence.EntitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.persistence.UsuariJPA;

@Controller
@RequestMapping(value = "/superadmin/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuariSuperAdminController extends UsuariController {

	@EJB(mappedName = UsuariEntitatLogicaService.JNDI_NAME)
	protected UsuariEntitatLogicaService usuariEntitatEJB;

	@Override
	public String getTileForm() {
		return "usuariFormSuperAdmin";
	}

	@Override
	public String getTileList() {
		return "usuariListSuperAdmin";
	}

	@Override
	public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {

		List<EntitatJPA> entitats = null;
		List<UsuariEntitatJPA> usuarisEntitats = null;
		if (__isView) {
			entitats = usuariEntitatEJB.findAllByUsuariIdWithEntitat(_jpa.getUsuariID());
			usuarisEntitats = usuariEntitatEJB.findAllByUsuariId(_jpa.getUsuariID());
		}
		mav.addObject("entitats", entitats);
		mav.addObject("usuarisEntitats", usuarisEntitats);

		UsuariForm usuariForm = super.getUsuariForm(_jpa, __isView, request, mav);

		if (usuariForm.isNou()) {
			usuariForm.getUsuari().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			usuariForm.getUsuari().setActiu(true);
		}

		usuariForm.addHiddenField(DARRERAENTITAT);
		usuariForm.addReadOnlyField(DATACREACIO);
		// usuariForm.addReadOnlyField(ACTIU);
		usuariForm.addReadOnlyField(DATABAIXA);

		usuariForm.setAttachedAdditionalJspCode(true);

		return usuariForm;

	}

	@Override
	public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);

		if (usuariFilterForm.isNou()) {
			usuariFilterForm.addHiddenField(USUARIID);
			usuariFilterForm.addHiddenField(DATACREACIO);
			usuariFilterForm.addHiddenField(IDIOMAID);
			usuariFilterForm.addHiddenField(DARRERAENTITAT);

			usuariFilterForm.addAdditionalButtonForEachItem(
					new AdditionalButton("fa fa-building", "usuari.assignarentitat",
							"/superadmin/usuariEntitat/assignarUsuari/{0}", AdditionalButtonStyle.SECONDARY));
		}

		{
			AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
			adfield.setCodeName(UsuariEntitatFields.ENTITATID.codeLabel);
			adfield.setPosition(1);
			adfield.setOrderBy(EntitatFields.NOM);
			adfield.setEscapeXml(false);
			adfield.setValueMap(new HashMap<Long, String>());
			usuariFilterForm.addAdditionalField(adfield);
		}

		usuariFilterForm.setViewButtonVisible(true);
		usuariFilterForm.setAttachedAdditionalJspCode(true);

		return usuariFilterForm;

	}

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm, List<Usuari> list)
			throws I18NException {

		Map<Long, String> mapEntitat = (Map<Long, String>) filterForm.getAdditionalField(1).getValueMap();

		mapEntitat.clear();

		for (Usuari usuari : list) {

			final Long usuariID = usuari.getUsuariID();

			List<EntitatJPA> entitats = usuariEntitatEJB.findAllByUsuariIdWithEntitat(usuariID);

			String entitatHtml = "";
			for (EntitatJPA entitat : entitats) {
				String urlborrar = request.getContextPath() + getContextWeb() + "/entitat/delete" + "?usuariID="
						+ usuariID + "&entitatID="
						+ entitat.getEntitatID();
				String botoEsborrarTitle = I18NUtils.tradueix("usuari.entitat.botoEsborrar");
				String botoEsborrar = "<a style='margin-left:5px;' href='" + urlborrar + "' title='" + botoEsborrarTitle
						+ "' alt='" + botoEsborrarTitle
						+ "'><i class='fas fa-times' style='color:white;'></i></a>";
				// mostram el badge vermell si l'entitat està deshabilitada i groc si
				// l'assignació ho està
				boolean assignacioAentitatActiva = usuariEntitatEJB.isActiuUsuariEntitat(usuariID,
						entitat.getEntitatID());
				String badgeTipus = entitat.isActiu() ? "badge-secondary"
						: (assignacioAentitatActiva ? "badge-danger"
								: "badge-warning");
				String badgeTitle = entitat.isActiu() ? "Activa"
						: (assignacioAentitatActiva ? "Entitat inactiva"
								: "Assignació a entitat inactiva");
				entitatHtml += "<span class='badge " + badgeTipus + "' title='" + badgeTitle + "''>" + entitat.getNom()
						+ botoEsborrar + "</span>";
			}

			mapEntitat.put(usuariID, entitatHtml);
		}
	}

	@RequestMapping(value = "/entitat/delete", method = RequestMethod.GET)
	public String esborrarEntitatAssociada(HttpServletRequest request, @RequestParam("usuariID") Long usuariID,
			@RequestParam("entitatID") Long entitatID) throws I18NException {
		log.info("Esborrant l'entitat associada a l'usuari " + usuariID + " i entitat " + entitatID);

		if (usuariID == null || entitatID == null) {
			log.error("UsuariID o EntitatID no vàlid");
			throw new I18NException("error.usuariEntitat.noValid");
		}
		usuariEntitatEJB.delete(Where.AND(UsuariEntitatFields.USUARIID.equal(usuariID),
				UsuariEntitatFields.ENTITATID.equal(entitatID)));

		String missatgeBorrat = I18NUtils.tradueix("usuari.entitat.borrat",
				new String[] { entitatID.toString(), usuariID.toString() });
		HtmlUtils.saveMessageInfo(request, missatgeBorrat);
		log.info(missatgeBorrat);

		LoginInfo loginInfo = LoginInfo.getInstance();
		UsuariJPA usuariActiu = loginInfo.getUsuariPersona();
		if (usuariActiu.getUsuariID() == usuariID) {
			log.info("S'han modificat les dades de l'usuari actiu. Anem a actualitzar les entitats...");
			Map<Long, EntitatJPA> entitatsActualitzades = new HashMap<>();
			for (EntitatJPA entitat : loginInfo.getEntitats().values()) {
				if (entitat.getEntitatID() != entitatID.longValue()) {
					entitatsActualitzades.put(entitat.getEntitatID(), entitat);
					log.info("Entitat " + entitat.getNom() + " amb ID " + entitat.getEntitatID() + " continua.");
				} else {
					log.info("Entitat " + entitat.getNom() + " amb ID " + entitat.getEntitatID()
							+ " esborrada de la llista.");
				}
			}
			loginInfo.setEntitats(entitatsActualitzades);
		}

		String redireccio = "redirect:" + getContextWeb() + "/list/1";
		log.info("Redirigint cap a " + redireccio);
		return redireccio;
	}

	@Override
	public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
			ModelAndView mav, Where where) throws I18NException {
		return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID, where,
				new OrderBy(IdiomaFields.ORDRE, OrderType.ASC));
	}
}
