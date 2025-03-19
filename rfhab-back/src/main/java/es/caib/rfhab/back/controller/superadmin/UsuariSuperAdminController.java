package es.caib.rfhab.back.controller.superadmin;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.webdb.UsuariFilterForm;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.logic.UsuariEntitatLogicaService;
import es.caib.rfhab.model.entity.Usuari;
import es.caib.rfhab.model.fields.EntitatFields;
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
		usuariForm.addReadOnlyField(ACTIU);
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

		
		Map<Long, String> mapEntitat = (Map<Long,String>) filterForm.getAdditionalField(1).getValueMap();
		
		mapEntitat.clear();
		
		for (Usuari usuari : list) {
			
			final Long usuariID = usuari.getUsuariID();
			
			List<EntitatJPA> entitats = usuariEntitatEJB.findAllByUsuariIdWithEntitat(usuariID);
			
			String entitatHtml = "";
			for (EntitatJPA entitat : entitats) {
				entitatHtml +="<span class='badge badge-secondary'>" + entitat.getNom() + "</span>";
			}
			
			mapEntitat.put(usuariID, entitatHtml);
			
		}
	
	}

}
