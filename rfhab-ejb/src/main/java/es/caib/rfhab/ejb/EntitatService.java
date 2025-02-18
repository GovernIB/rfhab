
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.EntitatJPA;
import es.caib.rfhab.persistence.EntitatIJPAManager;
import es.caib.rfhab.model.dao.IEntitatManager;

import es.caib.rfhab.model.entity.Entitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntitatService extends EntitatIJPAManager,IEntitatManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/EntitatEJB!es.caib.rfhab.ejb.EntitatService";

    public EntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Entitat instance, FitxerService fitxerEjb) throws I18NException;
}
