package es.caib.rfhab.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.caib.rfhab.ejb.HistoricService;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Historic;
import es.caib.rfhab.persistence.HistoricJPA;

/*
 * author jagarcia
 */

@Local
public interface HistoricLogicaService extends HistoricService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/HistoricLogicaEJB!es.caib.rfhab.logic.HistoricLogicaService";

	public List<Select6Values<Long, String, String, String, String, Timestamp>> getHistoricByFuncionariId(
			Long funcionariId) throws I18NException;

	public Historic create(HistoricJPA historicFuncionari, HistoricFuncionariDAO historicNew,
			HistoricFuncionariDAO historicOld) throws JsonProcessingException, I18NException;

	public Historic create(HistoricJPA historicFuncionari, String observacions)
			throws I18NException;

	public HistoricJPA create(Funcionari funcionari, String cai, Long usuariId) throws I18NException;

}
