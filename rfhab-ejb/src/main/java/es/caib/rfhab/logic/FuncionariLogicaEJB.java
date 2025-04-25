package es.caib.rfhab.logic;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.utils.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.FuncionariEJB;
import es.caib.rfhab.ejb.HistoricService;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Historic;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.HistoricJPA;

/**
 * 
 * @autor jagarcia
 *
 */

@Stateless
public class FuncionariLogicaEJB extends FuncionariEJB implements FuncionariLogicaService {

	@EJB(mappedName = HistoricService.JNDI_NAME)
	protected HistoricService historicEjb;

	@EJB(mappedName = RolService.JNDI_NAME)
	protected RolService rolEjb;

	@Override
	@PermitAll
	public long getFuncionariID(String numero, String usuari, Long entitatId) throws I18NException {

		StringBuilder queryString = new StringBuilder("SELECT f FROM FuncionariJPA f WHERE ");

		if (!Utils.isEmpty(numero)) {
			queryString.append(" f." + FuncionariFields.NUMERO.javaName + " = :numero ");
		} else if (!Utils.isEmpty(usuari)) {
			queryString.append(" f." + FuncionariFields.USUARI.javaName + " = :usuari ");
		}

		// TODO FILTRE ENTITAT

		TypedQuery<FuncionariJPA> query = getEntityManager().createQuery(queryString.toString(), FuncionariJPA.class);

		List<FuncionariJPA> resultats = query.getResultList();

		return (!resultats.isEmpty()) ? (resultats.get(0)).getFuncionariID() : null;

	}

	@Override
	@PermitAll
	public boolean isFuncionariAutoritzat(Long funcionariId, String codiSia, Long entitatId) throws I18NException {

		try {

			StringBuilder query = new StringBuilder();

			// TODO ENTITAT => funcionari pot estar al sistema sense plaça? Si es així,
			// perqué no quedi orfe, l'associam a la darrera entitat?

			return true;

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return false;
	}

	@Override
	@PermitAll
	public boolean isFuncionariHabilitat(Long funcionariId, String codiRol, Long entitatId) throws I18NException {

		// TODO ENTITATID

		// TODO REVISAR

		/*
		 * List<RolJPA> rolsFuncionari = getRolsByFuncionariID(funcionariId);
		 * for (RolJPA rol : rolsFuncionari) {
		 * if (rol.getCodi().toUpperCase().equals(codiRol.toUpperCase())) {
		 * return true;
		 * }
		 * }
		 */

		return false;
	}

	@Override
	@PermitAll
	public Funcionari createAndHistory(Funcionari funcionari, String cai, Long usuariId) throws I18NException {

		HistoricJPA historic = new HistoricJPA();

		try {

			log.info("Creant funcionari: " + funcionari.getNom() + " " + funcionari.getLlinatge1() + " "
					+ funcionari.getLlinatge2());

			historic.setNumeroCai(cai);
			historic.setFuncionariID(funcionari.getFuncionariID());
			historic.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			historic.setUsuariID(usuariId);

			HistoricFuncionariDAO newFuncionari = new HistoricFuncionariDAO(funcionari);
			ObjectMapper mapper = new ObjectMapper();
			String canvis = mapper.writeValueAsString(newFuncionari);
			historic.setObservacions(canvis);

			Historic nou = historicEjb.create(historic);

			log.info("Historic de funcionari creat: " + nou.getHistoricID());

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.creation",
					String.valueOf(funcionari.getFuncionariID()) + " (CAI: " + cai + ")");
		}

		return funcionari;

	}

	@Override
	@PermitAll
	public Funcionari updateAndHistory(Funcionari funcionari, String cai, Long usuariId) throws I18NException {

		log.info("CAI: " + cai);
		log.info("UsuariID: " + usuariId);
		if (funcionari != null)
			log.info("FuncionariID: " + funcionari.getFuncionariID());
		else
			log.error("Funcionari null");

		HistoricJPA historic = new HistoricJPA();

		historic.setNumeroCai(cai);
		historic.setFuncionariID(funcionari.getFuncionariID());
		historic.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historic.setUsuariID(usuariId);

		HistoricFuncionariDAO newFuncionari = new HistoricFuncionariDAO(funcionari);

		/*
		 * TODO REVISAR
		 * 
		 * try {
		 * List<Rol> oldRols = getRolsByFuncionariIDv2(funcionari.getFuncionariID());
		 * if (oldRols != null && !oldRols.isEmpty())
		 * newFuncionari.setRols(oldRols);
		 * else
		 * log.error("No s'han pogut recuperar els rols del funcionari");
		 * 
		 * } catch (Exception e) {
		 * log.error("Error al recuperar els rols del funcionari");
		 * log.error(e.getMessage());
		 * }
		 */

		try {
			ObjectMapper mapper = new ObjectMapper();
			String canvis = mapper.writeValueAsString(newFuncionari);
			historic.setObservacions(canvis);
		} catch (JsonProcessingException e) {
			log.info("error al convertir el funcionari a JSON");
			log.error(e.getMessage());
		}

		try {
			Historic nou = historicEjb.create(historic);

			if (nou != null)
				log.info("Historic de funcionari creat: " + nou.getHistoricID());
		} catch (Exception e) {
			log.info("Error al crear el historic de funcionari");
			log.error(e.getMessage());
		}

		return funcionari;
	}

	@Override
	@PermitAll
	public HistoricFuncionariDAO fromJson(String json) throws I18NException {
		ObjectMapper mapper = new ObjectMapper();
		try {

			if (json == null || json.isEmpty())
				return null;

			return mapper.readValue(json, HistoricFuncionariDAO.class);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			throw new I18NException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new I18NException(e.getMessage());
		}
	}

	@Override
	@PermitAll
	public String getMaxFuncionariNumero() throws SecurityException, NoSuchFieldException {

		StringBuilder queryString = new StringBuilder(
				"select max(rf." + FuncionariFields.NUMERO.javaName + ") from " + FuncionariJPA.class.getName()
						+ " rf where rf." + FuncionariFields.NUMERO.javaName + " like '" + Constants.SQL_NUMERO_PATTERN
						+ "' escape '" + Constants.SQL_LIKE_ESCAPE_PATTERN + "'");
		Class<?> numeroClass = FuncionariFields.NUMERO.getClass().getField("javaName").getType();// TODO:comentar a anadal que getJavaClass no funciona perquè javaClass és javaName
		TypedQuery<?> query = getEntityManager().createQuery(queryString.toString(),
				numeroClass);
		List<?> resultats = query.getResultList();

		return (!resultats.isEmpty()) ? (resultats.get(0).toString()) : null;
	}
}
