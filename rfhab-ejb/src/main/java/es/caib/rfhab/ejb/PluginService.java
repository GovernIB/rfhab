
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.PluginJPA;
import es.caib.rfhab.persistence.PluginIJPAManager;
import es.caib.rfhab.model.dao.IPluginManager;

import es.caib.rfhab.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PluginService extends PluginIJPAManager,IPluginManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/PluginEJB!es.caib.rfhab.ejb.PluginService";

    public PluginJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Plugin instance, FitxerService fitxerEjb) throws I18NException;
}
