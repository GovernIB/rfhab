package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.LlocRolEJB;
import es.caib.rfhab.model.entity.LlocRol;
import es.caib.rfhab.model.fields.LlocRolFields;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class LlocRolLogicaEJB extends LlocRolEJB implements LlocRolLogicaService {

	public Long getLlocRolIDByLlocAndRol(Long llocId, Long rolId) throws I18NException {
		// Retrieve LlocRol ID by Lloc and Rol
		List<LlocRol> result = super.select(
				Where.AND(LlocRolFields.LLOCID.equal(llocId), LlocRolFields.ROLID.equal(rolId)));
		if (!result.isEmpty()) {
			return result.get(0).getLlocRolID();
		}
		return null;
	}
}
