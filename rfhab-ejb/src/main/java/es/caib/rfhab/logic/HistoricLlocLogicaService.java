package es.caib.rfhab.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.selectcolumn.Select7Values;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.caib.rfhab.ejb.HistoricLlocService;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/*
 *
 * @author jagarcia
 * @author jpou
 * 
 */
@Local
public interface HistoricLlocLogicaService extends HistoricLlocService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/HistoricLlocLogicaEJB!es.caib.rfhab.logic.HistoricLlocLogicaService";

	public List<Select7Values<Long, String, String, String, String, Timestamp, String>> getHistoricByLlocId(Long llocId)
			throws I18NException;

	public HistoricLloc create(HistoricLlocJPA historicLloc, HistoricLlocDAO historicNew,
			HistoricLlocDAO historicOld) throws JsonProcessingException, I18NException;

	public HistoricLloc create(HistoricLlocJPA historicLloc, String observacions)
			throws I18NException;
}
