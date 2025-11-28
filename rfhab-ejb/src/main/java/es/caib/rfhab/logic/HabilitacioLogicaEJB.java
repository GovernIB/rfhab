package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.HabilitacioEJB;
import es.caib.rfhab.model.dao.IHabilitacioManager;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.LlocHabilitacio;
import es.caib.rfhab.model.entity.Habilitacio;
import es.caib.rfhab.model.fields.FuncionariLlocQueryPath;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocHabilitacioFields;
import es.caib.rfhab.model.fields.LlocHabilitacioQueryPath;
import es.caib.rfhab.model.fields.HabilitacioFields;
import es.caib.rfhab.persistence.FuncionariJPA;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class HabilitacioLogicaEJB extends HabilitacioEJB implements HabilitacioLogicaService {

    @EJB(mappedName = LlocHabilitacioLogicaService.JNDI_NAME)
    protected LlocHabilitacioLogicaService llocHabilitacioLogicaEJB;

    @EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
    protected FuncionariLlocLogicaService funcionariLlocLogicaEJB;

    public static Habilitacio findByCodi(IHabilitacioManager um, String codi) throws I18NException {
        Where codiDir3W = HabilitacioFields.CODI.equal(codi);
        List<Habilitacio> resultats = um.select(codiDir3W);
        return (resultats != null && resultats.size() > 0) ? resultats.get(0) : null;
    }

    @Override
    @PermitAll
    public Habilitacio findByCodi(String codi) throws I18NException {
        return HabilitacioLogicaEJB.findByCodi(this, codi);
    }

    @Override
    @PermitAll
    public Habilitacio findByCodiIfuncionari(String codi, FuncionariJPA funcionari) throws I18NException {
        Where habilitacioByLlocDeFuncionari = null;

        FuncionariLlocQueryPath funcionarilLocQueryPath = new FuncionariLlocQueryPath();
        Where whereFuncionari = funcionarilLocQueryPath.FUNCIONARI().FUNCIONARIID().equal(funcionari.getFuncionariID());
        SubQuery<FuncionariLloc, Long> subQueryLlocIdFromFuncionari;
        subQueryLlocIdFromFuncionari = funcionariLlocLogicaEJB.getSubQuery(LlocFields.LLOCID, whereFuncionari);

        LlocHabilitacioQueryPath llocHabilitacioQueryPath = new LlocHabilitacioQueryPath();
        Where whereLloc = llocHabilitacioQueryPath.LLOC().LLOCID().in(subQueryLlocIdFromFuncionari);
        SubQuery<LlocHabilitacio, Long> subQueryHabilitacioIdFromLloc;
        subQueryHabilitacioIdFromLloc = llocHabilitacioLogicaEJB.getSubQuery(LlocHabilitacioFields.HABILITACIOID, whereLloc);
        habilitacioByLlocDeFuncionari = HabilitacioFields.HABILITACIOID.in(subQueryHabilitacioIdFromLloc);

        Where codiDir3W = HabilitacioFields.CODI.equal(codi);
        List<Habilitacio> resultats = select(Where.AND(habilitacioByLlocDeFuncionari, codiDir3W));
        return (resultats != null && resultats.size() > 0) ? resultats.get(0) : null;
    }

}
