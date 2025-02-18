
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.FuncionariRolJPA;
import es.caib.rfhab.persistence.FuncionariRolIJPAManager;
import es.caib.rfhab.model.dao.IFuncionariRolManager;

import es.caib.rfhab.model.entity.FuncionariRol;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FuncionariRolService extends FuncionariRolIJPAManager,IFuncionariRolManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/FuncionariRolEJB!es.caib.rfhab.ejb.FuncionariRolService";

    public FuncionariRolJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(FuncionariRol instance, FitxerService fitxerEjb) throws I18NException;
}
