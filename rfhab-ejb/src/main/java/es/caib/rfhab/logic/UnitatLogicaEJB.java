package es.caib.rfhab.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UnitatEJB;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.model.fields.UnitatFields;
import es.caib.rfhab.persistence.UnitatJPA;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class UnitatLogicaEJB extends UnitatEJB implements UnitatLogicaService {

    @Override
    @PermitAll
    public UnitatJPA findByPrimaryKey(Long _ID_) {
        return (UnitatJPA) super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public Unitat findUnitatMare(Long unitatId) throws I18NException {
        return findUnitatMare(unitatId, null);
    }

    @Override
    @PermitAll
    public Unitat findUnitatMare(Long unitatId, Long unitatIdTope) throws I18NException {
        // Cerca la unitat inicial amb el unitatId especificat
        Unitat unitatMare = findByPrimaryKey(unitatId);
        Unitat unitatActual = unitatMare;

        String unitatActualSuperior = unitatActual.getSuperior();

        // Itera fins que no hi hagi més "mares" (és a dir, fins que el camp "superior"
        // sigui null o no es trobi cap coincidència)
        while (unitatActual != null && unitatActualSuperior != null && !unitatActualSuperior.isEmpty()
                && unitatActual.getUnitatID() != unitatIdTope) {
            unitatActualSuperior = unitatActual.getSuperior();
            unitatActual = null;
            List<Unitat> unitatsTrobades = select(UnitatFields.CODI.equal(unitatActualSuperior));
            if (unitatsTrobades != null && unitatsTrobades.size() > 0) {
                unitatActual = unitatsTrobades.get(0);
                unitatMare = unitatActual; // Actualitza la unitat mare si es troba una nova unitat
            }
        }

        // Retorna l'última unitat trobada (la "mare")
        return unitatMare;
    }

    @Override
    @PermitAll
    public List<Unitat> findAllReferencingUnitats(List<Unitat> unitats, String codiInicial) {
        List<Unitat> result = new ArrayList<>();
        List<String> codisPendents = new ArrayList<>();
        codisPendents.add(codiInicial);
        boolean entitatArrelAfegida = false;

        while (!codisPendents.isEmpty()) {
            String codiActual = codisPendents.remove(0);

            for (Unitat unitat : unitats) {
                // aquí només hauria d'entrar un pic per afegir la unitat "arrel". LLevar si no
                // es vol aquesta unitat. Però revisar el jsp perquè no està preparat per això,
                // ja que cerca l'entitat "arrel" dins el llistat d'unitats.
                if (!entitatArrelAfegida && codiInicial.equals(unitat.getCodi())) {
                    result.add(0, unitat);
                    entitatArrelAfegida = true;
                }
                if (codiActual.equals(unitat.getSuperior())) {
                    result.add(unitat);
                    codisPendents.add(unitat.getCodi());
                }
            }
        }

        return result;
    }
}
