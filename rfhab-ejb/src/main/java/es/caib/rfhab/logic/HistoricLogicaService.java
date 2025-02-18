package es.caib.rfhab.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;

import es.caib.rfhab.ejb.HistoricService;

/*
 * author jagarcia
 */

@Local
public interface HistoricLogicaService extends HistoricService {
	
	public static final String JNDI_NAME = "java:app/rfhab-ejb/HistoricLogicaEJB!es.caib.rfhab.logic.HistoricLogicaService";
	
	public List<Select6Values<Long, String, String, String, String, Timestamp>> getHistoricByFuncionariId(Long funcionariId) throws I18NException;
	
}
