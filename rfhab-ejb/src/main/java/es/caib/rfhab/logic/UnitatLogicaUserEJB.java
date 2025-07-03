package es.caib.rfhab.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

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
    @PermitAll
    public UnitatJPA findByPrimaryKey(Long _ID_) {
        return (UnitatJPA) super.findByPrimaryKey(_ID_);
    }
}
