
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.FuncionariIJPAManager;
import es.caib.rfhab.model.dao.IFuncionariManager;

import es.caib.rfhab.model.entity.Funcionari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FuncionariService extends FuncionariIJPAManager,IFuncionariManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/FuncionariEJB!es.caib.rfhab.ejb.FuncionariService";

    public FuncionariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Funcionari instance, FitxerService fitxerEjb) throws I18NException;
}
