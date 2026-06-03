
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.SincroUnitatsJPA;
import es.caib.rfhab.persistence.SincroUnitatsIJPAManager;
import es.caib.rfhab.model.dao.ISincroUnitatsManager;

import es.caib.rfhab.model.entity.SincroUnitats;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface SincroUnitatsService extends SincroUnitatsIJPAManager,ISincroUnitatsManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/SincroUnitatsEJB!es.caib.rfhab.ejb.SincroUnitatsService";

    public SincroUnitatsJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(SincroUnitats instance, FitxerService fitxerEjb) throws I18NException;
}
