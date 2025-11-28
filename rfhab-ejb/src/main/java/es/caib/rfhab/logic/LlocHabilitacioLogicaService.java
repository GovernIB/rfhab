package es.caib.rfhab.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.LlocHabilitacioService;

/**
 * 
 * @autor jpou
 *
 */
@Local
public interface LlocHabilitacioLogicaService extends LlocHabilitacioService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/LlocHabilitacioLogicaEJB!es.caib.rfhab.logic.LlocHabilitacioLogicaService";

	public Long getLlocHabilitacioIDByLlocAndHabilitacio(Long llocId, Long habilitacioId)
			throws I18NException;
}
