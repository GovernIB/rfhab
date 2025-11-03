package es.caib.rfhab.back.controller.superadmin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UsuariEntitatController;
import es.caib.rfhab.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.rfhab.back.form.webdb.UsuariEntitatForm;

import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.model.fields.UsuariEntitatFields;

@Controller
@RequestMapping(value = "/superadmin/usuariEntitat")
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class })
public class UsuariEntitatSuperAdminController extends UsuariEntitatController {

	private static final String USUARI_ID_NAME_ATTRIBUTE = "usuariId";
	private static final String ENTITAT_ID_NAME_ATTRIBUTE = "entitatId";

	@Override
	public String getTileForm() {
		return "usuariEntitatFormSuperAdmin";
	}

	@Override
	public String getTileList() {
		return "usuariEntitatListSuperAdmin";
	}

	@Override
	public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa, boolean __isView, HttpServletRequest request,
			ModelAndView mav) throws I18NException {
		UsuariEntitatForm usuariEntitatForm = super.getUsuariEntitatForm(_jpa, __isView, request, mav);

		Object entitatIdAttribute = request.getSession().getAttribute(ENTITAT_ID_NAME_ATTRIBUTE);
		log.info("usuariEntitat:new => " + entitatIdAttribute);

		if (usuariEntitatForm.isNou()) {
			UsuariEntitatJPA usuariEntitatItem = new UsuariEntitatJPA();
			
			if (entitatIdAttribute != null) {
				usuariEntitatItem.setEntitatID((long) entitatIdAttribute);
				usuariEntitatForm.addReadOnlyField(UsuariEntitatFields.ENTITATID);
				request.getSession().removeAttribute(ENTITAT_ID_NAME_ATTRIBUTE);
			}
			
			Object usuariIdAttribute = request.getSession().getAttribute(USUARI_ID_NAME_ATTRIBUTE);
			if (usuariIdAttribute != null) {
				usuariEntitatItem.setUsuariID((long) usuariIdAttribute);
				usuariEntitatForm.addReadOnlyField(UsuariEntitatFields.USUARIID);
				request.getSession().removeAttribute(USUARI_ID_NAME_ATTRIBUTE);
			}
			
			usuariEntitatItem.setActiu(true);
			usuariEntitatForm.setUsuariEntitat(usuariEntitatItem);
			
		}

		return usuariEntitatForm;
	}

	@RequestMapping(value = "/assignar/{entitatId}", method = RequestMethod.GET)
	public String assignarUsuari(HttpServletRequest request, @PathVariable(ENTITAT_ID_NAME_ATTRIBUTE) Long entitatId) {

		log.info("Assignar nou usuari a entitatId " + entitatId);
		request.getSession().setAttribute(ENTITAT_ID_NAME_ATTRIBUTE, entitatId);

		return "redirect:/superadmin/usuariEntitat/new";
	}
	
	@RequestMapping(value = "/assignarUsuari/{usuariId}", method = RequestMethod.GET)
	public String assignarEntitat(HttpServletRequest request, @PathVariable(USUARI_ID_NAME_ATTRIBUTE) Long usuariId) {
		
		log.info("Assignar entitat a usuariID " + usuariId);
		request.getSession().setAttribute(USUARI_ID_NAME_ATTRIBUTE, usuariId);
		
		return "redirect:/superadmin/usuariEntitat/new";
		
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuariEntitatID) {
		return "redirect:/superadmin/usuari/list/1";
	}
	
	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, UsuariEntitatForm usuariEntitatForm) {
	    return "redirect:/superadmin/usuari/list/1";
	  }

}
