
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.rfhab.persistence.AutoritzacioJPA;
import es.caib.rfhab.persistence.AutoritzacioIJPAManager;
import es.caib.rfhab.model.dao.IAutoritzacioManager;

import es.caib.rfhab.model.entity.Autoritzacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AutoritzacioService extends AutoritzacioIJPAManager,IAutoritzacioManager {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/AutoritzacioEJB!es.caib.rfhab.ejb.AutoritzacioService";

    public AutoritzacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Autoritzacio instance, FitxerService fitxerEjb) throws I18NException;
}
