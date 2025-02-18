package es.caib.rfhab.back.controller.superadmin;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.PluginController;
import es.caib.rfhab.back.form.webdb.PluginFilterForm;
import es.caib.rfhab.back.form.webdb.PluginForm;
import es.caib.rfhab.back.security.LoginInfo;
import es.caib.rfhab.persistence.PluginJPA;

/**
 * @author jagarcia
 */

@Controller
@RequestMapping(value = "/superadmin/plugin")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class PluginSuperAdminController extends PluginController {
	
	protected final Logger log = Logger.getLogger(getClass());

	@Override
	public String getTileForm() {
		return "pluginFormAdmin";
	}

	@Override
	public String getTileList() {
		return "pluginListAdmin";
	}

	@Override
	public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		PluginFilterForm pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);

		if (pluginFilterForm.isNou()) {
			pluginFilterForm.addHiddenField(PLUGINID);
			pluginFilterForm.addHiddenField(DATACREACIO);
			pluginFilterForm.addHiddenField(PROPERTIES);
		}

		return pluginFilterForm;
	}

	@Override
	public PluginForm getPluginForm(PluginJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		
		PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);

		if (pluginForm.isNou()) {
			pluginForm.getPlugin().setDataCreacio(new Timestamp(System.currentTimeMillis()));
			pluginForm.getPlugin().setEntitatID(LoginInfo.getInstance().getUsuariPersona().getDarreraEntitat());
		}
		
		pluginForm.addReadOnlyField(DATACREACIO);
		
		return pluginForm;
	}

}