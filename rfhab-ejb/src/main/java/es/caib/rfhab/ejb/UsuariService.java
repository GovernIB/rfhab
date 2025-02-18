
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.UsuariJPA;
import es.caib.rfhab.persistence.UsuariIJPAManager;
import es.caib.rfhab.model.dao.IUsuariManager;

import es.caib.rfhab.model.entity.Usuari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariService extends UsuariIJPAManager,IUsuariManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/UsuariEJB!es.caib.rfhab.ejb.UsuariService";

    public UsuariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Usuari instance, FitxerService fitxerEjb) throws I18NException;
}
