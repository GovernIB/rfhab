package es.caib.rfhab.logic;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.ejb.HistoricEJB;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Historic;
import es.caib.rfhab.model.fields.HistoricFields;
import es.caib.rfhab.model.fields.UsuariQueryPath;
import es.caib.rfhab.persistence.HistoricJPA;

@Stateless
public class HistoricLogicaEJB extends HistoricEJB implements HistoricLogicaService {

	@Override
	@PermitAll
	public List<Select6Values<Long, String, String, String, String, Timestamp>> getHistoricByFuncionariId(
			Long funcionariId)
			throws I18NException {

		Select6Columns<Long, String, String, String, String, Timestamp> s6c = new Select6Columns<Long, String, String, String, String, Timestamp>(
				HistoricFields.HISTORICID.select, HistoricFields.NUMEROCAI.select, new UsuariQueryPath().NOM().select,
				new UsuariQueryPath().LLINATGE1().select, new UsuariQueryPath().LLINATGE2().select,
				HistoricFields.DATACREACIO.select);

		Where w = HistoricFields.FUNCIONARIID.equal(funcionariId);

		OrderBy orderBy = new OrderBy(HistoricFields.DATACREACIO, OrderType.DESC);

		return this.executeQuery(s6c, w, orderBy);
	}

	@PermitAll
	public Historic create(HistoricJPA historicFuncionari, HistoricFuncionariDAO historicNew,
			HistoricFuncionariDAO historicOld)
			throws JsonProcessingException, I18NException {
		ObjectMapper mapper = new ObjectMapper();
		String cambio = mapper.writeValueAsString(new HistoricFuncionariDAO[] { historicOld, historicNew });
		historicFuncionari.setObservacions(cambio);

		return super.create(historicFuncionari);
	}

	@PermitAll
	public Historic create(HistoricJPA historicFuncionari, String observacions)
			throws I18NException {
		historicFuncionari.setObservacions(observacions);

		return super.create(historicFuncionari);
	}

	@Override
	@PermitAll
	public HistoricJPA create(Funcionari funcionari, String cai, Long usuariId) throws I18NException {
		try {
			HistoricJPA historic = null;

			if (funcionari != null) {
				historic = new HistoricJPA();
				historic.setFuncionariID(funcionari.getFuncionariID());
				historic.setNumeroCai(cai);
				historic.setDataCreacio(new Timestamp(System.currentTimeMillis()));
				historic.setUsuariID(usuariId);

				HistoricFuncionariDAO historicNew = new HistoricFuncionariDAO(funcionari);
				HistoricFuncionariDAO historicOld = new HistoricFuncionariDAO();
				this.create(historic, historicNew, historicOld);
			} else
				throw new I18NException("error.creation", "<lloc null>");

			return historic;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.creation", String.valueOf(funcionari.getFuncionariID()));
		}
	}

}
