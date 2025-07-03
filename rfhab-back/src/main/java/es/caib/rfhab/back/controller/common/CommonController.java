package es.caib.rfhab.back.controller.common;

import javax.ejb.EJB;
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
import es.caib.rfhab.logic.SistramitLogicaService;
import es.caib.rfhab.logic.UnitatLogicaUserService;
import es.caib.rfhab.persistence.UnitatJPA;

/**
 * 
 * @author jagarcia
 * @author jpou
 *
 */
@Controller
@RequestMapping(value = "/common/")
public class CommonController extends UsuariController {

	protected final Logger log = Logger.getLogger(getClass());

	@EJB(mappedName = UnitatLogicaUserService.JNDI_NAME)
	protected UnitatLogicaUserService unitatLogicaEjb;

	@RequestMapping(value = "/canviarEntitat/{entitatId}")
	public ModelAndView canviarEntitat(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "entitatId") String entitatId) throws Exception {

		if (entitatId != null && !entitatId.isEmpty()) {
			log.info("canviarEntitat: " + entitatId);
			LoginInfo loginInfo = LoginInfo.getInstance();
			loginInfo.setEntitatIDActual(Long.parseLong(entitatId));

			//TODOOO????? revisar codidir3actual
			// UnitatJPA unitat = unitatLogicaEjb.findByEntitatId(Long.parseLong(entitatId));
			// log.info("unitat associada a entitat actual: " + unitat);
			// if (unitat != null) {
			// 	loginInfo.setCodiDir3Actual(unitat.getCodi());
			// }
		}
		return new ModelAndView("principal");
	}

}
