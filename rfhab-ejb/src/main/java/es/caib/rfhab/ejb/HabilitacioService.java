
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.HabilitacioJPA;
import es.caib.rfhab.persistence.HabilitacioIJPAManager;
import es.caib.rfhab.model.dao.IHabilitacioManager;

import es.caib.rfhab.model.entity.Habilitacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface HabilitacioService extends HabilitacioIJPAManager,IHabilitacioManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/HabilitacioEJB!es.caib.rfhab.ejb.HabilitacioService";

    public HabilitacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Habilitacio instance, FitxerService fitxerEjb) throws I18NException;
}
