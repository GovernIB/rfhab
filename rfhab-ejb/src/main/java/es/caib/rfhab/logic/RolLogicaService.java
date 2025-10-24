package es.caib.rfhab.logic;

import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.model.entity.Rol;

/**
 * 
 * @author jpou
 *
 */
@Local
public interface RolLogicaService extends RolService {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/RolLogicaEJB!es.caib.rfhab.logic.RolLogicaService";

    public Rol findByCodi(String codi) throws I18NException;

}
