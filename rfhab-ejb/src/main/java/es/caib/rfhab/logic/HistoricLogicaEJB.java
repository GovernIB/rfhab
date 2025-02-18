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

import es.caib.rfhab.ejb.HistoricEJB;
import es.caib.rfhab.model.fields.HistoricFields;
import es.caib.rfhab.model.fields.UsuariQueryPath;

@Stateless
public class HistoricLogicaEJB extends HistoricEJB implements HistoricLogicaService {

	@Override
	@PermitAll
	public List<Select6Values<Long, String, String, String, String, Timestamp>> getHistoricByFuncionariId(Long funcionariId)
			throws I18NException {

		Select6Columns<Long, String, String, String, String, Timestamp> s6c = new Select6Columns<Long, String, String, String, String, Timestamp>(
				HistoricFields.HISTORICID.select, HistoricFields.NUMEROCAI.select, new UsuariQueryPath().NOM().select,
				new UsuariQueryPath().LLINATGE1().select, new UsuariQueryPath().LLINATGE2().select, HistoricFields.DATACREACIO.select);

		Where w = HistoricFields.FUNCIONARIID.equal(funcionariId);
		
		OrderBy orderBy = new OrderBy(HistoricFields.DATACREACIO, OrderType.DESC);
		
		return this.executeQuery(s6c, w, orderBy);
		
	}
}
