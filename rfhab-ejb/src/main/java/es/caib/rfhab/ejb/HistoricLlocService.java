
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.HistoricLlocIJPAManager;
import es.caib.rfhab.model.dao.IHistoricLlocManager;

import es.caib.rfhab.model.entity.HistoricLloc;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface HistoricLlocService extends HistoricLlocIJPAManager,IHistoricLlocManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/HistoricLlocEJB!es.caib.rfhab.ejb.HistoricLlocService";

    public HistoricLlocJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(HistoricLloc instance, FitxerService fitxerEjb) throws I18NException;
}
