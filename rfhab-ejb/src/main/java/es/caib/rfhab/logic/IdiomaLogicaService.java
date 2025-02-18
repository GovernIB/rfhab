package es.caib.rfhab.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.IdiomaService;
import es.caib.rfhab.persistence.IdiomaJPA;

/**
 * 
 * @author jagarcia
 *
 */

@Local
public interface IdiomaLogicaService  extends IdiomaService {
	
	public static final String JNDI_NAME = "java:app/rfhab-ejb/IdiomaLogicaEJB!es.caib.rfhab.logic.IdiomaLogicaService";
	
	public IdiomaJPA findByCodi(String codi) throws I18NException;
}
