package es.caib.rfhab.logic;

import javax.ejb.Local;

import es.caib.rfhab.ejb.UnitatService;
import es.caib.rfhab.persistence.UnitatJPA;

/**
 * 
 * @author jpou
 *
 */
@Local
public interface UnitatLogicaUserService extends UnitatService {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/UnitatLogicaUserEJB!es.caib.rfhab.logic.UnitatLogicaUserService";

    public UnitatJPA findByPrimaryKey(Long _ID_);
}
