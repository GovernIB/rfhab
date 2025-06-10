package es.caib.rfhab.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.FitxerEJB;
import es.caib.rfhab.model.entity.Fitxer;

/**
 * 
 * @autor jpou
 *
 */
@Stateless(name = "FitxerPublicLogicaEJB")
public class FitxerPublicLogicaEJB extends FitxerEJB implements FitxerPublicLogicaService {

	@Override
	@PermitAll
	public void delete(Fitxer instance) {
		super.delete(instance);
	}

	@Override
	@PermitAll
	public Fitxer create(Fitxer instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public Fitxer update(Fitxer instance) throws I18NException {
		return super.update(instance);
	}
}
