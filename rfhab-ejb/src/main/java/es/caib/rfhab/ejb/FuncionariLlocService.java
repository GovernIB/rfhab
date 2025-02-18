
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.FuncionariLlocJPA;
import es.caib.rfhab.persistence.FuncionariLlocIJPAManager;
import es.caib.rfhab.model.dao.IFuncionariLlocManager;

import es.caib.rfhab.model.entity.FuncionariLloc;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FuncionariLlocService extends FuncionariLlocIJPAManager,IFuncionariLlocManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/FuncionariLlocEJB!es.caib.rfhab.ejb.FuncionariLlocService";

    public FuncionariLlocJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(FuncionariLloc instance, FitxerService fitxerEjb) throws I18NException;
}
