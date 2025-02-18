package es.caib.rfhab.back.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.persistence.UsuariJPA;

@Controller
@RequestMapping(value = "/admin/usuari")
@SessionAttributes(types = { UsuariForm.class })
public class UsuariAdminController extends UsuariController {

	@Override
	public String getTileForm() {
		return "usuariFormAdmin";
	}

	@Override
	public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {

		LoginInfo loginInfo = LoginInfo.getInstance();

		if (_jpa.getUsuariID() == loginInfo.getUsuariPersona().getUsuariID()) {

			UsuariForm usuariForm = super.getUsuariForm(_jpa, __isView, request, mav);

			usuariForm.addHiddenField(DARRERAENTITAT);
			usuariForm.addReadOnlyField(DATACREACIO);
			usuariForm.addReadOnlyField(ACTIU);
			
			if (_jpa.getDataBaixa() != null)
				usuariForm.addReadOnlyField(DATABAIXA);
			else
				usuariForm.addHiddenField(DATABAIXA);
			
			usuariForm.setDeleteButtonVisible(false);

			usuariForm.setAttachedAdditionalJspCode(false);

			return usuariForm;
		}

		throw new I18NException("No es pot veure ni editar un usuari que no sigui el propi");

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
			return super.update(request, usuari);
		}

		throw new I18NException("Només es pot modificar el propi usuari");
	}

}
