
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.ScanWebJPA;
import es.caib.rfhab.persistence.ScanWebIJPAManager;
import es.caib.rfhab.model.dao.IScanWebManager;

import es.caib.rfhab.model.entity.ScanWeb;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ScanWebService extends ScanWebIJPAManager,IScanWebManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/ScanWebEJB!es.caib.rfhab.ejb.ScanWebService";

    public ScanWebJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(ScanWeb instance, FitxerService fitxerEjb) throws I18NException;
}
