
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.rfhab.model.entity.UsuariEntitat;
import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatJPAManager;

import es.caib.rfhab.commons.utils.Constants;

@Stateless
public class UsuariEntitatEJB extends UsuariEntitatJPAManager implements UsuariEntitatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(UsuariEntitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public UsuariEntitat create(UsuariEntitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public UsuariEntitat update(UsuariEntitat instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(UsuariEntitat instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public UsuariEntitatJPA findByPrimaryKey(Long _ID_) {
        return (UsuariEntitatJPA)super.findByPrimaryKey(_ID_);
    }

}
