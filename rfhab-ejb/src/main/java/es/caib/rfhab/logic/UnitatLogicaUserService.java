package es.caib.rfhab.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

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
