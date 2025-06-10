package es.caib.rfhab.logic;

import javax.ejb.Local;

import es.caib.rfhab.ejb.FitxerService;

/**
 * 
 * @autor jpou
 *
 */
@Local
public interface FitxerPublicLogicaService extends FitxerService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/FitxerPublicLogicaEJB!es.caib.rfhab.logic.FitxerPublicLogicaService";

}
