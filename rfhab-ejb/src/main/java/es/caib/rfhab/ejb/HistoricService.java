
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.HistoricJPA;
import es.caib.rfhab.persistence.HistoricIJPAManager;
import es.caib.rfhab.model.dao.IHistoricManager;

import es.caib.rfhab.model.entity.Historic;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface HistoricService extends HistoricIJPAManager,IHistoricManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/HistoricEJB!es.caib.rfhab.ejb.HistoricService";

    public HistoricJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Historic instance, FitxerService fitxerEjb) throws I18NException;
}
