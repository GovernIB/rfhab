package es.caib.rfhab.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.logic.UsuariLogicaService;
import es.caib.rfhab.model.fields.UsuariFields;
import es.caib.rfhab.persistence.UsuariJPA;

@Controller
@RequestMapping(value = UsuariAdminController.CONTEXTWEB)
@SessionAttributes(types = { UsuariForm.class })
public class UsuariAdminController extends UsuariController {

	public static final String CONTEXTWEB = "/admin/usuari";

	@EJB(mappedName = UsuariLogicaService.JNDI_NAME)
	protected UsuariLogicaService usuariLogicaEjb;

	@Override
	public String getTileForm() {
		return "usuariFormAdmin";
	}

	@Override
	protected ModelAndView editAndViewUsuariGet(@PathVariable("usuariID") java.lang.Long usuariID,
			HttpServletRequest request,
			HttpServletResponse response, boolean __isView) throws I18NException {
		UsuariJPA usuari = findByPrimaryKey(request, usuariID);

		LoginInfo loginInfo = LoginInfo.getInstance();

		long loggedUserId = loginInfo.getUsuariPersona().getUsuariID();
		if (usuari != null && usuari.getUsuariID() != loggedUserId) {
			createMessageError(request, "usuari.all.nopropi.noeditarniveure", usuariID);
			return super.editAndViewUsuariGet(loggedUserId, request, response, __isView);
		}

		return super.editAndViewUsuariGet(usuariID, request, response, __isView);
	}

	@Override
	public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {

		LoginInfo loginInfo = LoginInfo.getInstance();

		if (_jpa.getUsuariID() == loginInfo.getUsuariPersona().getUsuariID()) {

			UsuariForm usuariForm = super.getUsuariForm(_jpa, __isView, request, mav);

			usuariForm.addHiddenField(UsuariFields.DARRERAENTITAT);
			usuariForm.addReadOnlyField(UsuariFields.DATACREACIO);
			usuariForm.addReadOnlyField(UsuariFields.ACTIU);
			usuariForm.addReadOnlyField(UsuariFields.NIF);
			usuariForm.addReadOnlyField(UsuariFields.USERNAME);
			usuariForm.addReadOnlyField(UsuariFields.CORREU);

			if (_jpa.getDataBaixa() != null)
				usuariForm.addReadOnlyField(UsuariFields.DATABAIXA);
			else
				usuariForm.addHiddenField(UsuariFields.DATABAIXA);

			usuariForm.setDeleteButtonVisible(false);

			usuariForm.setAttachedAdditionalJspCode(false);

			return usuariForm;
		}

		throw new I18NException("usuari.all.nopropi.noeditarniveure");
	}

	@Override
	public boolean isActiveList() {
		return false;
	}

	@Override
	public boolean isActiveFormNew() {
		return false;
	}

	@Override
	public boolean isActiveDelete() {
		return false;
	}

	@Override
	public UsuariJPA update(HttpServletRequest request, UsuariJPA usuari)
			throws I18NException, I18NValidationException {

		LoginInfo loginInfo = LoginInfo.getInstance();

		if (usuari.getUsuariID() == loginInfo.getUsuariPersona().getUsuariID()) {
			return usuariLogicaEjb.update(usuari);
		}

		throw new I18NException("usuari.all.nopropi.nomodificar");
	}

	@Override
	public UsuariJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long usuariID) throws I18NException {
		return (UsuariJPA) usuariLogicaEjb.findByPrimaryKey(usuariID);
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, UsuariForm usuariForm, Throwable __e) {
		if (__e == null) {
			return "redirect:/";
		} else {
			return getTileForm();
		}
	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuariID) {
		return "redirect:/";
	}

}
