package es.caib.rfhab.logic;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.UnitatEJB;
import es.caib.rfhab.persistence.UnitatJPA;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class UnitatLogicaUserEJB extends UnitatEJB implements UnitatLogicaUserService {

    @Override
    @RolesAllowed({ Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS,
            Constants.RFH_USER })
    public UnitatJPA findByPrimaryKey(Long _ID_) {
        return (UnitatJPA) super.findByPrimaryKey(_ID_);
    }
}
