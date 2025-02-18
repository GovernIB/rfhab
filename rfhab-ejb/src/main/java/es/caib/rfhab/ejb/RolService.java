
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.RolJPA;
import es.caib.rfhab.persistence.RolIJPAManager;
import es.caib.rfhab.model.dao.IRolManager;

import es.caib.rfhab.model.entity.Rol;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface RolService extends RolIJPAManager,IRolManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/RolEJB!es.caib.rfhab.ejb.RolService";

    public RolJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Rol instance, FitxerService fitxerEjb) throws I18NException;
}
