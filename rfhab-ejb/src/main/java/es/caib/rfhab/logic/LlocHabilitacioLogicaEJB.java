package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.LlocHabilitacioEJB;
import es.caib.rfhab.model.entity.LlocHabilitacio;
import es.caib.rfhab.model.fields.LlocHabilitacioFields;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class LlocHabilitacioLogicaEJB extends LlocHabilitacioEJB implements LlocHabilitacioLogicaService {

	public Long getLlocHabilitacioIDByLlocAndHabilitacio(Long llocId, Long habilitacioId) throws I18NException {
		// Retrieve LlocHabilitacio ID by Lloc and Habilitacio
		List<LlocHabilitacio> result = super.select(
				Where.AND(LlocHabilitacioFields.LLOCID.equal(llocId), LlocHabilitacioFields.HABILITACIOID.equal(habilitacioId)));
		if (!result.isEmpty()) {
			return result.get(0).getLlocHabilitacioID();
		}
		return null;
	}
}
