
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.IdiomaJPA;
import es.caib.rfhab.persistence.IdiomaIJPAManager;
import es.caib.rfhab.model.dao.IIdiomaManager;

import es.caib.rfhab.model.entity.Idioma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/IdiomaEJB!es.caib.rfhab.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Idioma instance, FitxerService fitxerEjb) throws I18NException;
}
