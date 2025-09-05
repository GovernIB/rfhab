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
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.LlocEJB;
import es.caib.rfhab.ejb.LlocRolService;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.logic.utils.FuncionariLlocDAO;
import es.caib.rfhab.logic.utils.FuncionariLlocLlocDAO;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.LlocRol;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocRolFields;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Stateless
public class LlocLogicaEJB extends LlocEJB implements LlocLogicaService {

	@EJB(mappedName = HistoricLlocLogicaService.JNDI_NAME)
	HistoricLlocLogicaService historicLlocLogicaEjb;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	FuncionariLlocLogicaService funcionariLlocLogicaEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	FuncionariLogicaService funcionariLogicaEjb;

	@EJB(mappedName = LlocRolService.JNDI_NAME)
	LlocRolService llocRolEjb;

	@EJB(mappedName = RolService.JNDI_NAME)
	RolService rolEjb;

	@Override
	@PermitAll
	public Lloc updateAndHistory(Lloc lloc, String cai, Long usuariId) throws I18NException {
		try {
			Lloc newLloc = null;
			if (lloc == null) {
				throw new I18NException("error.modification", "<lloc null>");
			}
			Lloc oldLloc = findByPrimaryKey(lloc.getLlocID());
			if (oldLloc == null) {
				throw new I18NException("error.modification", new String[] { "lloc", LlocFields.LLOCID.sqlName,
						String.valueOf(lloc.getLlocID()), "<oldLloc null>" });
			}

			HistoricLlocDAO historicOld = new HistoricLlocDAO(oldLloc);
			newLloc = update(lloc);
			log.info("Lloc actualitzat: " + newLloc.getLlocID());

			HistoricLlocJPA historicLloc = new HistoricLlocJPA();
			historicLloc.setLlocID(oldLloc.getLlocID());
			historicLloc.setNumeroCai(cai);
			historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			historicLloc.setUsuariID(usuariId);

			HistoricLlocDAO historicNew = new HistoricLlocDAO(newLloc);
			HistoricLloc historicCreat = historicLlocLogicaEjb.create(historicLloc, historicNew, historicOld);
			log.info("HistoricLloc creat: " + historicCreat.getHistoricllocID());

			return newLloc;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.modification", String.valueOf(lloc.getLlocID()));
		}
	}

	@Override
	@PermitAll
	public HistoricLlocJPA createAndHistory(Lloc lloc, String cai, Long usuariId) throws I18NException {
		try {
			HistoricLlocJPA historicLloc = null;

			if (lloc != null) {
				historicLloc = new HistoricLlocJPA();
				historicLloc.setLlocID(lloc.getLlocID());
				historicLloc.setNumeroCai(cai);
				historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
				historicLloc.setUsuariID(usuariId);

				HistoricLlocDAO historicNew = new HistoricLlocDAO(lloc);
				HistoricLlocDAO historicOld = new HistoricLlocDAO();
				historicLlocLogicaEjb.create(historicLloc, historicNew, historicOld);
			} else
				throw new I18NException("error.creation", "<lloc null>");

			return historicLloc;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.creation", String.valueOf(lloc.getLlocID()));
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
							+ "l.personalOamr, l.dataAlta, l.dataCreacio, l.dataBaixa, l.observacions, l.codillocpropi, l.expansio FROM FuncionariLlocJPA as fl "
							+ " inner join LlocJPA as l on l.llocID = fl.llocID "
							+ " where (fl.dataInici < :avui or fl.dataInici is null) "
							+ " and (fl.dataFi > :avui or fl.dataFi is null) " + " and l.entitatID = :entitatId");

			Query q = getEntityManager().createQuery(customQuery.toString());
			q.setParameter("avui", new Date(System.currentTimeMillis()));
			q.setParameter("entitatId", entitatId);

			List<Object[]> result = q.getResultList();

			for (Object[] obj : result) {

				LlocJPA llocJPA = new LlocJPA((long) obj[1], (String) obj[4], (String) obj[10], (String) obj[11],
						(String) obj[2],
						(long) entitatId,
						(long) obj[3], (int) obj[5], (Timestamp) obj[6], (Timestamp) obj[7], (Timestamp) obj[8],
						(String) obj[9]);

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
			w = funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent();
		}

		List<FuncionariLloc> funcionarisActius = funcionariLlocLogicaEjb.select(w);

		List<Long> funcionarisAssignats = new ArrayList<Long>(funcionarisActius.size());
		for (FuncionariLloc f : funcionarisActius) {
			funcionarisAssignats.add(f.getFuncionariID());
		}

		Where filtro = FuncionariFields.FUNCIONARIID.in(funcionarisAssignats);
		if (entitatId != null && entitatId > 0) {
			filtro = Where.AND(filtro, FuncionariFields.ENTITATID.equal(entitatId));
		}
		List<Funcionari> llistaFuncionaris = funcionariLogicaEjb
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
				w = funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent(w);
			}
			List<FuncionariLloc> funcionarisLlocs = funcionariLlocLogicaEjb.select(w);
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

	public List<FuncionariLlocLlocDAO> getLlocHistoricByFuncionariID(Long funcionariId, boolean current)
			throws I18NException {
		if (funcionariId != null) {
			Where w = FuncionariLlocFields.FUNCIONARIID.equal(funcionariId);

			if (current) {
				w = funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent(w);
			}
			List<FuncionariLloc> funcionarisLlocs = funcionariLlocLogicaEjb.select(w);
			if (funcionarisLlocs.size() > 0) {
				List<FuncionariLlocLlocDAO> llocsOcupatsPerFuncionari = new ArrayList<FuncionariLlocLlocDAO>(
						funcionarisLlocs.size());
				for (FuncionariLloc fl : funcionarisLlocs) {
					Lloc lloc = findByPrimaryKey(fl.getLlocID());
					if (lloc != null) {
						llocsOcupatsPerFuncionari.add(new FuncionariLlocLlocDAO(lloc, fl));
					}
				}
				return llocsOcupatsPerFuncionari;
			}
		}
		return null;
	}

	@Override
	@PermitAll
	public List<Funcionari> getFuncionarisByLlocID(Long llocId) throws I18NException {
		return getFuncionarisByLlocID(llocId, false);
	}

	@Override
	@PermitAll
	public List<Funcionari> getFuncionarisByLlocID(Long llocId, boolean current) throws I18NException {

		if (llocId != null) {

			Where w = FuncionariLlocFields.LLOCID.equal(llocId);

			if (current) {
				w = funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent(w);
			}

			List<FuncionariLloc> funcionarisLlocs = funcionariLlocLogicaEjb.select(w);

			if (funcionarisLlocs.size() > 0) {
				List<Funcionari> llistaFuncionaris = new ArrayList<Funcionari>(funcionarisLlocs.size());
				for (FuncionariLloc fl : funcionarisLlocs) {
					Funcionari funcionari = funcionariLogicaEjb.findByPrimaryKey(fl.getFuncionariID());
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
	public List<FuncionariLlocDAO> getFuncionarisLlocByLlocID(Long llocId) throws I18NException {
		return getFuncionarisLlocByLlocID(llocId, false);
	}

	@Override
	@PermitAll
	public List<FuncionariLlocDAO> getFuncionarisLlocByLlocID(Long llocId, boolean current) throws I18NException {

		if (llocId != null) {

			Where w = FuncionariLlocFields.LLOCID.equal(llocId);

			if (current) {
				w = funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent(w);
			}

			List<FuncionariLloc> funcionarisLlocs = funcionariLlocLogicaEjb.select(w);

			if (funcionarisLlocs.size() > 0) {
				List<FuncionariLlocDAO> llistaFuncionaris = new ArrayList<FuncionariLlocDAO>(funcionarisLlocs.size());
				for (FuncionariLloc fl : funcionarisLlocs) {
					Funcionari funcionari = funcionariLogicaEjb.findByPrimaryKey(fl.getFuncionariID());
					if (funcionari != null) {
						llistaFuncionaris.add(new FuncionariLlocDAO(funcionari, fl));
					}
				}
				return llistaFuncionaris;
			}
		}
		return new ArrayList<FuncionariLlocDAO>();
	}

	@Override
	@PermitAll
	public List<Rol> getRolsByLlocID(Long llocId) throws I18NException {

		if (llocId != null) {

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

	public Funcionari donarDeBaixaLlocAndHistory(long llocId, final String numeroCai, long usuariId)
			throws I18NException {
		return funcionariLogicaEjb.dessassignarFuncionariAndHistory(null, llocId, numeroCai, usuariId, false, true);
	}

	public Lloc donarDeAltaAndHistory(java.lang.Long llocID, String numeroCai, long usuariId) throws I18NException {
		log.info("Donant d'alta Lloc amb ID " + llocID);

		LlocJPA lloc = findByPrimaryKey(llocID);
		if (lloc == null) {
			log.error("No s'ha trobat el Lloc amb ID " + llocID);
			throw new I18NException("lloc.error.noexisteix", llocID.toString());
		}

		// afegim històric
		HistoricLlocJPA historic = new HistoricLlocJPA();
		historic.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historic.setLlocID(llocID);
		historic.setNumeroCai(numeroCai);
		historic.setUsuariID(usuariId);
		historicLlocLogicaEjb.create(historic, "Lloc de feina " + lloc.getCodiLloc() + " donat d'alta"
				+ (lloc.getDataalta() != null ? " de nou" : ""));

		// el donam de baixa
		lloc.setDataBaixa(null);
		lloc.setDataalta(new Timestamp(System.currentTimeMillis()));
		return update(lloc);
	}

	@Override
	@PermitAll
	public Object getMaxLlocCodiPropi() throws SecurityException, NoSuchFieldException {

		StringBuilder queryString = new StringBuilder(
				"select max(rf." + LlocFields.CODILLOCPROPI.javaName + ") from " + LlocJPA.class.getName()
						+ " rf where rf." + LlocFields.CODILLOCPROPI.javaName + " like '"
						+ Constants.SQL_LLOC_CODILLOCPROPI_PATTERN
						+ "' escape '" + Constants.SQL_LIKE_ESCAPE_PATTERN + "'");
		Class<?> codiPropiClass = LlocFields.CODILLOCPROPI.getClass().getField("javaName").getType();// TODO:comentar a
																										// anadal que
																										// getJavaClass
																										// no
																										// funciona
																										// perquè
																										// javaClass és
																										// javaName
		TypedQuery<?> query = getEntityManager().createQuery(queryString.toString(),
				codiPropiClass);
		List<?> resultats = query.getResultList();

		return (resultats != null && !resultats.isEmpty()) ? (resultats.get(0)) : null;
	}
}
