
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.ActivitatJPA;
import es.caib.rfhab.persistence.ActivitatIJPAManager;
import es.caib.rfhab.model.dao.IActivitatManager;

import es.caib.rfhab.model.entity.Activitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ActivitatService extends ActivitatIJPAManager,IActivitatManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/ActivitatEJB!es.caib.rfhab.ejb.ActivitatService";

    public ActivitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Activitat instance, FitxerService fitxerEjb) throws I18NException;
}
