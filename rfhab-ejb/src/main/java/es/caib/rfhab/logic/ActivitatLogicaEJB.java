package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.ActivitatEJB;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.fields.ActivitatFields;

/**
 * 
 * @autor jagarcia
 *
 */

@Stateless
public class ActivitatLogicaEJB extends ActivitatEJB implements ActivitatLogicaService {

	@Override
	public List<Activitat> getActivitatsByFuncionariID(Long funcionariId) throws I18NException {
		return this.select(ActivitatFields.FUNCIONARIID.equal(funcionariId));
	}
	
}
