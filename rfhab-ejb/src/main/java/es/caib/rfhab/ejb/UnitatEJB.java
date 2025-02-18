
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.persistence.UnitatJPA;
import es.caib.rfhab.persistence.UnitatJPAManager;

import es.caib.rfhab.commons.utils.Constants;

@Stateless
public class UnitatEJB extends UnitatJPAManager implements UnitatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(Unitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public Unitat create(Unitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public Unitat update(Unitat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(Unitat instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public UnitatJPA findByPrimaryKey(Long _ID_) {
        return (UnitatJPA)super.findByPrimaryKey(_ID_);
    }

}
