package es.caib.rfhab.logic;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import es.caib.rfhab.ejb.HistoricLlocService;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/*
 * author jagarcia
 */

@Local
public interface HistoricLlocLogicaService extends HistoricLlocService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/HistoricLlocLogicaEJB!es.caib.rfhab.logic.HistoricLlocLogicaService";

	public List<Select6Values<Long, String, String, String, String, Timestamp>> getHistoricByLlocId(Long llocId)
			throws I18NException;

	public void create(HistoricLlocJPA historicLloc, HistoricLlocDAO historicNew,
			HistoricLlocDAO historicOld) throws JsonProcessingException, I18NException;

	public void create(HistoricLlocJPA historicLloc, String observacions)
			throws I18NException;

	public HistoricLlocDAO fromJson(String json) throws I18NException;

	public List<HistoricLlocDAO> listFromJson(String json) throws JsonParseException, JsonMappingException, IOException;
}
