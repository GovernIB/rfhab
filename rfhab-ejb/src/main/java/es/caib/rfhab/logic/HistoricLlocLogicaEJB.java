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

import es.caib.rfhab.ejb.HistoricLlocEJB;
import es.caib.rfhab.model.fields.HistoricLlocFields;
import es.caib.rfhab.model.fields.UsuariQueryPath;

@Stateless
public class HistoricLlocLogicaEJB extends HistoricLlocEJB implements HistoricLlocLogicaService {

	@Override
	@PermitAll
	public List<Select6Values<Long, String, String, String, String, Timestamp>> getHistoricByLlocId(Long llocId)
			throws I18NException {

		Select6Columns<Long, String, String, String, String, Timestamp> s6c = new Select6Columns<Long, String, String, String, String, Timestamp>(
				HistoricLlocFields.HISTORICLLOCID.select, HistoricLlocFields.NUMEROCAI.select,
				new UsuariQueryPath().NOM().select, new UsuariQueryPath().LLINATGE1().select,
				new UsuariQueryPath().LLINATGE2().select, HistoricLlocFields.DATACREACIO.select);

		Where w = HistoricLlocFields.LLOCID.equal(llocId);

		OrderBy orderBy = new OrderBy(HistoricLlocFields.DATACREACIO, OrderType.DESC);

		return this.executeQuery(s6c, w, orderBy);

	}
	
}
