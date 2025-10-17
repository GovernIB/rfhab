package es.caib.rfhab.logic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UnitatService;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.persistence.UnitatJPA;

/**
 * 
 * @author jpou
 *
 */
@Local
public interface UnitatLogicaService extends UnitatService {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/UnitatLogicaEJB!es.caib.rfhab.logic.UnitatLogicaService";

    public UnitatJPA findByPrimaryKey(Long _ID_);

    public Unitat findByCodiDir3(String codi, Integer versio) throws I18NException;

    public Unitat findUnitatMare(Long unitatId) throws I18NException;

    public Unitat findUnitatMare(Long unitatId, Long unitatIdTope) throws I18NException;

    public List<Unitat> findAllReferencingUnitats(List<Unitat> unitats, String codiInicial);

}
