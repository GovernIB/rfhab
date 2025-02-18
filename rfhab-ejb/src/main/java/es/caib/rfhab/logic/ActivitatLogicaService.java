package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.ActivitatService;
import es.caib.rfhab.model.entity.Activitat;

/**
 * 
 * @autor jagarcia
 *
 */

@Local
public interface ActivitatLogicaService extends ActivitatService{
	
	public static final String JNDI_NAME = "java:app/rfhab-ejb/ActivitatLogicaEJB!es.caib.rfhab.logic.ActivitatLogicaService";
	
	public List<Activitat> getActivitatsByFuncionariID(Long funcionariId) throws I18NException;

}
