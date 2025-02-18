package es.caib.rfhab.back.controller.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.rfhab.back.controller.webdb.IdiomaController;
import es.caib.rfhab.back.form.webdb.IdiomaFilterForm;
import es.caib.rfhab.back.form.webdb.IdiomaForm;



@Controller
@RequestMapping(value = "/superadmin/idioma")
@SessionAttributes(types = { IdiomaForm.class, IdiomaFilterForm.class })
public class IdiomaSuperAdminController extends IdiomaController {
	
	public String getTileForm() {
		return "idiomaFormSuperAdmin";
	}

	public String getTileList() {
		return "idiomaListSuperAdmin";
	}
	
}
