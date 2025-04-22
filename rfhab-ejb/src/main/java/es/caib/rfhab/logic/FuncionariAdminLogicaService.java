package es.caib.rfhab.logic;

import javax.ejb.Local;

import es.caib.rfhab.ejb.FuncionariService;

/**
 * 
 * @autor jpou
 *
 */

@Local
public interface FuncionariAdminLogicaService extends FuncionariService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/FuncionariAdminLogicaEJB!es.caib.rfhab.logic.FuncionariAdminLogicaService";

	public String getMaxFuncionariNumero() throws SecurityException, NoSuchFieldException;
}
