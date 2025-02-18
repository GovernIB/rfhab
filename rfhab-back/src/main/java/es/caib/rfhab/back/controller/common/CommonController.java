package es.caib.rfhab.back.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UsuariController;
import es.caib.rfhab.back.form.webdb.UsuariForm;
import es.caib.rfhab.back.security.LoginInfo;

/**
 * 
 * @author jagarcia
 *
 */
@Controller
@RequestMapping(value = "/common/")
public class CommonController extends UsuariController{

	protected final Logger log = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/canviarEntitat/{entitatId}")
	public ModelAndView canviarEntitat(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "entitatId") String entitatId) throws Exception {

		if (entitatId != null && !entitatId.isEmpty()) {
			log.info("canviarEntitat: " + entitatId);
			LoginInfo loginInfo = LoginInfo.getInstance();
			loginInfo.setEntitatIDActual(Long.parseLong(entitatId));
		}
		return new ModelAndView("principal");
	}

}
