package es.caib.rfhab.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.LlocRolService;

/**
 * 
 * @autor jpou
 *
 */
@Local
public interface LlocRolLogicaService extends LlocRolService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/LlocRolLogicaEJB!es.caib.rfhab.logic.LlocRolLogicaService";

	public Long getLlocRolIDByLlocAndRol(Long llocId, Long rolId)
			throws I18NException;
}
