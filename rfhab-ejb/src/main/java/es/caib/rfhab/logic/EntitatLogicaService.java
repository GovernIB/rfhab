package es.caib.rfhab.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.EntitatService;
import es.caib.rfhab.persistence.EntitatJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Local
public interface EntitatLogicaService extends EntitatService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/EntitatLogicaEJB!es.caib.rfhab.logic.EntitatLogicaService";

	public long findEntitatByDir3(String dir3) throws I18NException;

	public EntitatJPA findByPrimaryKey(Long _ID_);

}
