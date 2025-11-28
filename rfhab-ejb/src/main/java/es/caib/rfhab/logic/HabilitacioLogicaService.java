package es.caib.rfhab.logic;

import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.HabilitacioService;
import es.caib.rfhab.model.entity.Habilitacio;
import es.caib.rfhab.persistence.FuncionariJPA;

/**
 * 
 * @author jpou
 *
 */
@Local
public interface HabilitacioLogicaService extends HabilitacioService {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/HabilitacioLogicaEJB!es.caib.rfhab.logic.HabilitacioLogicaService";

    public Habilitacio findByCodi(String codi) throws I18NException;

    public Habilitacio findByCodiIfuncionari(String codi, FuncionariJPA funcionari) throws I18NException;

}
