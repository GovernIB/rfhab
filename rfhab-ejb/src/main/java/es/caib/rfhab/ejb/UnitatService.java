
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.UnitatJPA;
import es.caib.rfhab.persistence.UnitatIJPAManager;
import es.caib.rfhab.model.dao.IUnitatManager;

import es.caib.rfhab.model.entity.Unitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UnitatService extends UnitatIJPAManager,IUnitatManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/UnitatEJB!es.caib.rfhab.ejb.UnitatService";

    public UnitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Unitat instance, FitxerService fitxerEjb) throws I18NException;
}
