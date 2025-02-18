
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatIJPAManager;
import es.caib.rfhab.model.dao.IUsuariEntitatManager;

import es.caib.rfhab.model.entity.UsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariEntitatService extends UsuariEntitatIJPAManager,IUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/UsuariEntitatEJB!es.caib.rfhab.ejb.UsuariEntitatService";

    public UsuariEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(UsuariEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
