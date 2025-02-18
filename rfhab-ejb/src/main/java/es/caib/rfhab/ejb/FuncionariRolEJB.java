
package es.caib.rfhab.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.rfhab.model.entity.FuncionariRol;
import es.caib.rfhab.persistence.FuncionariRolJPA;
import es.caib.rfhab.persistence.FuncionariRolJPAManager;

import es.caib.rfhab.commons.utils.Constants;

@Stateless
public class FuncionariRolEJB extends FuncionariRolJPAManager implements FuncionariRolService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(FuncionariRol instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public FuncionariRol create(FuncionariRol instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public FuncionariRol update(FuncionariRol instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(FuncionariRol instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public FuncionariRolJPA findByPrimaryKey(Long _ID_) {
        return (FuncionariRolJPA)super.findByPrimaryKey(_ID_);
    }

}
