package es.caib.rfhab.back.controller.superadmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.rfhab.back.controller.webdb.UnitatController;
import es.caib.rfhab.back.form.webdb.UnitatFilterForm;
import es.caib.rfhab.back.form.webdb.UnitatForm;
import es.caib.rfhab.pluginsib.dir3caib.Dir3CaibPlugin;

@Controller
@RequestMapping(value = "/superadmin/unitat")
@SessionAttributes(types = { UnitatForm.class, UnitatFilterForm.class })
public class UnitatSuperAdminController extends UnitatController {

	@Override
	public String getTileForm() {
		return "unitatFormSuperAdmin";
	}

	@Override
	public String getTileList() {
		return "unitatListSuperAdmin";
	}

	@Override
	public UnitatFilterForm getUnitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		UnitatFilterForm unitatFilterForm = super.getUnitatFilterForm(pagina, mav, request);

		if (unitatFilterForm.isNou()) {
			unitatFilterForm.addHiddenField(ARREL);
			unitatFilterForm.addHiddenField(ARRELVERSIO);
			unitatFilterForm.addHiddenField(SUPERIOR);
			unitatFilterForm.addHiddenField(SUPERIORVERSIO);
			unitatFilterForm.addHiddenField(UNITATID);
			unitatFilterForm.addHiddenField(VERSIO);
			unitatFilterForm.addHiddenField(ESTAT);

			unitatFilterForm.setAddButtonVisible(false);
			unitatFilterForm.setDeleteButtonVisible(false);
			unitatFilterForm.setEditButtonVisible(false);
			unitatFilterForm.setViewButtonVisible(false);
			unitatFilterForm.setVisibleMultipleSelection(false);

			unitatFilterForm.addAdditionalButton(new AdditionalButton("fa fa-repeat", "unitats.sincronitzar",
					"/superadmin/unitat/sincronitzar", AdditionalButtonStyle.PRIMARY));
		}

		unitatFilterForm.setAttachedAdditionalJspCode(true);

		return unitatFilterForm;
	}

	@RequestMapping(value = "/sincronitzar")
	public ModelAndView option1(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Dir3CaibPlugin api = new Dir3CaibPlugin();
		//TODO: obtenir codidir3 d'entitat actual
		//TODO (si és primera descàrrega i volem dur tot l'arbre, passar null com a dates):
		// Descarga ultimaDescarga = descargaEjb.ultimaDescarga(RegwebConstantes.DESCARGA_UNIDAD, entidadId);
		// Timestamp fechaUltimaActualizacion = null;
		// if (ultimaDescarga.getFechaImportacion() != null) {
		// 	fechaUltimaActualizacion = new Timestamp(ultimaDescarga.getFechaImportacion().getTime());
		// }

		// // Establecemos la fecha de la primera sincronizacion
		// Descarga primeraDescarga = descargaEjb.primeraDescarga(RegwebConstantes.DESCARGA_UNIDAD, entidadId);
		// Timestamp fechaSincronizacion = null;
		// if (primeraDescarga.getFechaImportacion() != null) {
		// 	fechaSincronizacion = new Timestamp(primeraDescarga.getFechaImportacion().getTime());
		// }
 
		// api.sincronitzar(entitatCodiDir3, fechaUltimaActualizacion, fechaSincronizacion);

		// ModelAndView mav = new ModelAndView("option1Admin");
		// mav.addObject("optionNumber", "OPCIÓ SINCRONITZAR AMB DIR3CAIB");
		// return mav;
		return llistatPaginat(request, response, 1);
	}

}
