
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.LlocRolJPA;
import es.caib.rfhab.persistence.LlocRolIJPAManager;
import es.caib.rfhab.model.dao.ILlocRolManager;

import es.caib.rfhab.model.entity.LlocRol;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface LlocRolService extends LlocRolIJPAManager,ILlocRolManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/LlocRolEJB!es.caib.rfhab.ejb.LlocRolService";

    public LlocRolJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(LlocRol instance, FitxerService fitxerEjb) throws I18NException;
}
