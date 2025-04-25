package es.caib.rfhab.logic;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select6Values;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.ejb.HistoricLlocEJB;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.model.fields.HistoricLlocFields;
import es.caib.rfhab.model.fields.UsuariQueryPath;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/**
 * 
 * @autor jpou
 *
 */
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

	@PermitAll
	public void create(HistoricLlocJPA historicLloc, HistoricLlocDAO historicNew,
			HistoricLlocDAO historicOld)
			throws JsonProcessingException, I18NException {
		ObjectMapper mapper = new ObjectMapper();
		String cambio = mapper.writeValueAsString(new HistoricLlocDAO[] { historicNew, historicOld });
		historicLloc.setObservacions(cambio);

		super.create(historicLloc);
	}

	@PermitAll
	public void create(HistoricLlocJPA historicLloc, String observacions)
			throws I18NException {
		historicLloc.setObservacions(observacions);

		super.create(historicLloc);
	}

	@Override
	@PermitAll
	public List<HistoricLlocDAO> listFromJson(String json)
			throws JsonParseException, JsonMappingException, IOException {
		if (json == null || json.isEmpty()) {
			return null; // Retorna null si el JSON és buit o nul
		}

		ObjectMapper mapper = new ObjectMapper();
		// mapper.setDefaultLeniency(true); // Permet JSON incomplet o amb camps
		// desconeguts

		// Converteix el JSON a un array d'objectes HistoricLlocDAO
		HistoricLlocDAO[] historicArray = mapper.readValue(json, HistoricLlocDAO[].class);

		// Converteix l'array a una llista
		return new ArrayList<>(List.of(historicArray));
	}

	@Override
	@PermitAll
	public HistoricLlocDAO fromJson(String json) throws I18NException {

		if (json == null || json.isEmpty())
			return null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			HistoricLlocDAO historic = mapper.readValue(json, HistoricLlocDAO.class);
			return historic;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException(e.getMessage());
		}
	}
}
