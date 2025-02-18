
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.LlocJPA;
import es.caib.rfhab.persistence.LlocIJPAManager;
import es.caib.rfhab.model.dao.ILlocManager;

import es.caib.rfhab.model.entity.Lloc;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface LlocService extends LlocIJPAManager,ILlocManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/LlocEJB!es.caib.rfhab.ejb.LlocService";

    public LlocJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Lloc instance, FitxerService fitxerEjb) throws I18NException;
}
