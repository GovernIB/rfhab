package es.caib.rfhab.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;

import es.caib.rfhab.ejb.HistoricLlocService;

/*
 * author jagarcia
 */

@Local
public interface HistoricLlocLogicaService extends HistoricLlocService{

	public static final String JNDI_NAME = "java:app/rfhab-ejb/HistoricLlocLogicaEJB!es.caib.rfhab.logic.HistoricLlocLogicaService";
	
	public List<Select6Values<Long, String, String, String, String, Timestamp>> getHistoricByLlocId(Long llocId) throws I18NException;
	
}
