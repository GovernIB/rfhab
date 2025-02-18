package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.AutoritzacioService;
import es.caib.rfhab.model.entity.Autoritzacio;

/**
 * 
 * @autor jagarcia
 *
 */

@Local
public interface AutoritzacioLogicaService extends AutoritzacioService {
	
	public static final String JNDI_NAME = "java:app/rfhab-ejb/AutoritzacioLogicaEJB!es.caib.rfhab.logic.AutoritzacioLogicaService";
	
	public List<Autoritzacio> getAutoritzacionsByFuncionariID(Long funcionariId) throws I18NException;
	
	public List<Autoritzacio> getAutoritzacionsByLlocID(Long llocId) throws I18NException;
	
}
