
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.LlocHabilitacioJPA;
import es.caib.rfhab.persistence.LlocHabilitacioIJPAManager;
import es.caib.rfhab.model.dao.ILlocHabilitacioManager;

import es.caib.rfhab.model.entity.LlocHabilitacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface LlocHabilitacioService extends LlocHabilitacioIJPAManager,ILlocHabilitacioManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/LlocHabilitacioEJB!es.caib.rfhab.ejb.LlocHabilitacioService";

    public LlocHabilitacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(LlocHabilitacio instance, FitxerService fitxerEjb) throws I18NException;
}
