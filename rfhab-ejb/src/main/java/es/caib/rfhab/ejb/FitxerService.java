
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.FitxerJPA;
import es.caib.rfhab.persistence.FitxerIJPAManager;
import es.caib.rfhab.model.dao.IFitxerManager;

import es.caib.rfhab.model.entity.Fitxer;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/FitxerEJB!es.caib.rfhab.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Fitxer instance, FitxerService fitxerEjb) throws I18NException;
}
