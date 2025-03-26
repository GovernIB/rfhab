package es.caib.rfhab.logic;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.ejb.FuncionariLlocService;
import es.caib.rfhab.ejb.FuncionariService;
import es.caib.rfhab.ejb.HistoricLlocService;
import es.caib.rfhab.ejb.LlocEJB;
import es.caib.rfhab.ejb.LlocRolService;
import es.caib.rfhab.ejb.LlocService;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.LlocRol;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocRolFields;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;

/**
 * 
 * @autor jagarcia
 *
 */

@Stateless
public class LlocLogicaEJB extends LlocEJB implements LlocLogicaService {

	@EJB(mappedName = HistoricLlocService.JNDI_NAME)
	HistoricLlocService historicLlocEjb;

	@EJB(mappedName = FuncionariLlocService.JNDI_NAME)
	FuncionariLlocService funcionariLlocEjb;

	@EJB(mappedName = FuncionariService.JNDI_NAME)
	FuncionariService funcionariEjb;

	@EJB(mappedName = LlocRolService.JNDI_NAME)
	LlocRolService llocRolEjb;

	@EJB(mappedName = RolService.JNDI_NAME)
	RolService rolEjb;

	@Override
	@PermitAll
	public Lloc updateAndHistory(Lloc lloc, String cai, Long usuariId) throws I18NException {

		try {
			Lloc newLloc = null;

			if (lloc != null) {

				Lloc oldLloc = findByPrimaryKey(lloc.getLlocID());

				if (oldLloc != null) {

					newLloc = update(lloc);

					HistoricLlocJPA historicLloc = new HistoricLlocJPA();
					historicLloc.setLlocID(oldLloc.getLlocID());
					historicLloc.setNumeroCai(cai);
					historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
					historicLloc.setUsuariID(usuariId);

					/*
					 * String cambio = ""; if (oldLloc.getNom() != lloc.getNom()) { cambio +=
					 * "Nom: " + oldLloc.getNom() + " -> " + lloc.getNom() + " | "; }
					 * 
					 * if (oldLloc.getCodiDir3() != lloc.getCodiDir3()) { cambio += "CodiDir3: " +
					 * oldLloc.getCodiDir3() + " -> " + lloc.getCodiDir3() + " | "; }
					 * 
					 * if (oldLloc.getCodiLloc() != lloc.getCodiLloc()) { cambio += "CodiLloc: " +
					 * oldLloc.getCodiLloc() + " -> " + lloc.getCodiLloc() + " | "; }
					 * 
					 * if (oldLloc.getPersonalOamr() != lloc.getPersonalOamr()) { cambio +=
					 * "Personaloamr: " + oldLloc.getPersonalOamr() + " -> " +
					 * lloc.getPersonalOamr() + " | "; }
					 */

					HistoricLlocDAO historic = new HistoricLlocDAO(oldLloc);

					ObjectMapper mapper = new ObjectMapper();

					String cambio = mapper.writeValueAsString(historic);

					historicLloc.setObservacions(cambio);

					historicLlocEjb.create(historicLloc);

				} else
					throw new I18NException("error.modification", String.valueOf(lloc.getLlocID()));
			}

			return newLloc;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	@PermitAll
	public HistoricLlocJPA createAndHistory(Lloc lloc, String cai, Long usuariId) throws I18NException {

		try {

			HistoricLlocJPA historicLloc = null;

			if (lloc != null) {

				log.info("Lloc creat: " + lloc.getLlocID());

				historicLloc = new HistoricLlocJPA();
				historicLloc.setLlocID(lloc.getLlocID());
				historicLloc.setNumeroCai(cai);
				historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
				historicLloc.setUsuariID(usuariId);

				HistoricLlocDAO historic = new HistoricLlocDAO(lloc);
				ObjectMapper mapper = new ObjectMapper();
				String cambio = mapper.writeValueAsString(historic);
				historicLloc.setObservacions(cambio);

				historicLlocEjb.create(historicLloc);

				log.info("HistoricLloc creat: " + historicLloc.getHistoricllocID());

			} else
				throw new I18NException("error.creation", String.valueOf(lloc.getLlocID()));

			return historicLloc;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	@PermitAll
	@SuppressWarnings("unchecked")
	public HashMap<Long, LlocJPA> getAllLlocsOcupats(Long entitatId) throws I18NException {

		HashMap<Long, LlocJPA> llocsOcupats = new HashMap<Long, LlocJPA>();

		try {

			StringBuilder customQuery = new StringBuilder(
					"Select fl.funcionariID, l.llocID, l.nom, l.unitatID, l.codiLloc, "
							+ "l.personalOamr, l.dataCreacio, l.dataBaixa, l.observacions FROM FuncionariLlocJPA as fl "
							+ " inner join LlocJPA as l on l.llocID = fl.llocID "
							+ " where (fl.dataInici < :avui or fl.dataInici is null) "
							+ " and (fl.dataFi > :avui or fl.dataFi is null) " + " and l.entitatID = :entitatId");

			Query q = getEntityManager().createQuery(customQuery.toString());
			q.setParameter("avui", new Date(System.currentTimeMillis()));
			q.setParameter("entitatId", entitatId);

			List<Object[]> result = q.getResultList();

			for (Object[] obj : result) {

				LlocJPA llocJPA = new LlocJPA((long) obj[1], (String) obj[4], (String) obj[2], (int) obj[5],
						(long) entitatId, (Timestamp) obj[6], (Timestamp) obj[7], (String) obj[8], (long) obj[3]);

				llocsOcupats.put((Long) obj[0], llocJPA);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return llocsOcupats;

	}

	@Override
	@PermitAll
	public HashMap<Long, Funcionari> getCurrentFuncionarisByLloc(Long llocId, Long entitatId) throws I18NException {

		HashMap<Long, Funcionari> funcionaris = new HashMap<Long, Funcionari>();

		Where w = null;

		if (llocId != null && llocId > 0) {

			w = FuncionariLlocFields.LLOCID.equal(llocId);

		} else {

			Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
					FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
					FuncionariLlocFields.DATAFI.isNull());

			Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());
			
			Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			w = Where.OR(w1, w2, w3, w4);

		}

		List<FuncionariLloc> funcionarisActius = funcionariLlocEjb.select(w);

		List<Long> funcionarisAssignats = new ArrayList<Long>(funcionarisActius.size());
		for (FuncionariLloc f : funcionarisActius) {
			funcionarisAssignats.add(f.getFuncionariID());
		}

		Where filtro = FuncionariFields.FUNCIONARIID.in(funcionarisAssignats);
		if (entitatId != null && entitatId > 0){
			filtro = Where.AND(filtro, FuncionariFields.ENTITATID.equal(entitatId));
		}
		List<Funcionari> llistaFuncionaris = funcionariEjb
				.select(filtro);

		for (FuncionariLloc fl : funcionarisActius) {
			for (Funcionari f : llistaFuncionaris) {
				if (fl.getFuncionariID() == f.getFuncionariID()) {
					funcionaris.put(fl.getLlocID(), f);
					break;
				}
			}
		}

		return funcionaris;

	}

	@Override
	public List<Lloc> getLlocByFuncionariID(Long funcionariId, boolean current) throws I18NException {
		
		if (funcionariId != null) {
			
			Where w = FuncionariLlocFields.FUNCIONARIID.equal(funcionariId);
			 
			if (current) {
				
				Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
						FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

				Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
						FuncionariLlocFields.DATAFI.isNull());

				Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());
				
				Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

				w = Where.AND(w, Where.OR(w1, w2, w3, w4));
				
			}
			List<FuncionariLloc> funcionarisLlocs = funcionariLlocEjb.select(w);
			if (funcionarisLlocs.size() > 0) {
				List<Lloc> llocsOcupatsPerFuncionari = new ArrayList<Lloc>();
				for (FuncionariLloc fl : funcionarisLlocs) {
					Lloc lloc = findByPrimaryKey(fl.getLlocID());
					if (lloc != null) {
						llocsOcupatsPerFuncionari.add(lloc);
					}
				}
				return llocsOcupatsPerFuncionari;
			}
		}
		return null;
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

	@Override
	@PermitAll
	public List<Funcionari> getFuncionarisByLlocID(Long llocId) throws I18NException {

		if (llocId != null) {

			Where w = FuncionariLlocFields.LLOCID.equal(llocId);

			List<FuncionariLloc> funcionarisLlocs = funcionariLlocEjb.select(w);

			if (funcionarisLlocs.size() > 0) {
				List<Funcionari> llistaFuncionaris = new ArrayList<Funcionari>(funcionarisLlocs.size());
				for (FuncionariLloc fl : funcionarisLlocs) {
					Funcionari funcionari = funcionariEjb.findByPrimaryKey(fl.getFuncionariID());
					if (funcionari != null) {
						llistaFuncionaris.add(funcionari);
					}
				}
				return llistaFuncionaris;
			}

		}
		return new ArrayList<Funcionari>();

	}

	@Override
	@PermitAll
	public List<Rol> getRolsByLlocID(Long llocId) throws I18NException {

		if (llocId != null){


			Where w = LlocRolFields.LLOCID.equal(llocId);

			List<LlocRol> llocsRols = llocRolEjb.select(w);

			if (llocsRols.size() > 0) {
				List<Rol> llistaRols = new ArrayList<Rol>(llocsRols.size());
				for (LlocRol lr : llocsRols) {
					Rol rol = rolEjb.findByPrimaryKey(lr.getRolID());
					if (rol != null) {
						llistaRols.add(rol);
					}
				}
				return llistaRols;
			}

		}
		return new ArrayList<Rol>();

	}

}
