package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.RolEJB;
import es.caib.rfhab.model.dao.IRolManager;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.LlocRol;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.fields.FuncionariLlocQueryPath;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocRolFields;
import es.caib.rfhab.model.fields.LlocRolQueryPath;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.persistence.FuncionariJPA;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class RolLogicaEJB extends RolEJB implements RolLogicaService {

    @EJB(mappedName = LlocRolLogicaService.JNDI_NAME)
    protected LlocRolLogicaService llocRolLogicaEJB;

    @EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
    protected FuncionariLlocLogicaService funcionariLlocLogicaEJB;

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

    @Override
    @PermitAll
    public Rol findByCodiIfuncionari(String codi, FuncionariJPA funcionari) throws I18NException {
        Where habilitacioByLlocDeFuncionari = null;

        FuncionariLlocQueryPath funcionarilLocQueryPath = new FuncionariLlocQueryPath();
        Where whereFuncionari = funcionarilLocQueryPath.FUNCIONARI().FUNCIONARIID().equal(funcionari.getFuncionariID());
        SubQuery<FuncionariLloc, Long> subQueryLlocIdFromFuncionari;
        subQueryLlocIdFromFuncionari = funcionariLlocLogicaEJB.getSubQuery(LlocFields.LLOCID, whereFuncionari);

        LlocRolQueryPath llocRolQueryPath = new LlocRolQueryPath();
        Where whereLloc = llocRolQueryPath.LLOC().LLOCID().in(subQueryLlocIdFromFuncionari);
        SubQuery<LlocRol, Long> subQueryRolIdFromLloc;
        subQueryRolIdFromLloc = llocRolLogicaEJB.getSubQuery(LlocRolFields.ROLID, whereLloc);
        habilitacioByLlocDeFuncionari = RolFields.ROLID.in(subQueryRolIdFromLloc);

        Where codiDir3W = RolFields.CODI.equal(codi);
        List<Rol> resultats = select(Where.AND(habilitacioByLlocDeFuncionari, codiDir3W));
        return (resultats != null && resultats.size() > 0) ? resultats.get(0) : null;
    }

}
