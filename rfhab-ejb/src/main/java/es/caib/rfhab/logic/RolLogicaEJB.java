package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.RolEJB;
import es.caib.rfhab.model.dao.IRolManager;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.fields.RolFields;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class RolLogicaEJB extends RolEJB implements RolLogicaService {

    public static Rol findByCodi(IRolManager um, String codi) throws I18NException {
        Where codiDir3W = RolFields.CODI.equal(codi);
        List<Rol> resultats = um.select(codiDir3W);
        return (resultats != null && resultats.size() > 0) ? resultats.get(0) : null;
    }

    @Override
    @PermitAll
    public Rol findByCodi(String codi) throws I18NException {
        return RolLogicaEJB.findByCodi(this, codi);
    }

}
