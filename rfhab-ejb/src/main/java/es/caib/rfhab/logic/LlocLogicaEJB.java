package es.caib.rfhab.logic;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.LlocEJB;
import es.caib.rfhab.logic.utils.FuncionariLlocDAO;
import es.caib.rfhab.logic.utils.FuncionariLlocLlocDAO;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.LlocHabilitacio;
import es.caib.rfhab.model.entity.Habilitacio;
import es.caib.rfhab.model.entity.Historic;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.HistoricFields;
import es.caib.rfhab.model.fields.HistoricLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocHabilitacioFields;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;
import es.caib.rfhab.persistence.TraduccioJPA;
import es.caib.rfhab.persistence.HabilitacioJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Stateless
public class LlocLogicaEJB extends LlocEJB implements LlocLogicaService {

	@EJB(mappedName = HistoricLogicaService.JNDI_NAME)
	HistoricLogicaService historicLogicaEjb;

	@EJB(mappedName = HistoricLlocLogicaService.JNDI_NAME)
	HistoricLlocLogicaService historicLlocLogicaEjb;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	FuncionariLlocLogicaService funcionariLlocLogicaEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	FuncionariLogicaService funcionariLogicaEjb;

	@EJB(mappedName = LlocHabilitacioLogicaService.JNDI_NAME)
	LlocHabilitacioLogicaService llocHabilitacioLogicaEjb;

	@EJB(mappedName = HabilitacioLogicaService.JNDI_NAME)
	HabilitacioLogicaService habilitacioLogicaEjb;

	@Override
	@PermitAll
	public Lloc updateAndHistory(Lloc lloc, String cai, Long usuariId, String[] habilitacionsSeleccionades)
			throws I18NException {
		try {
			Lloc newLloc = null;
			if (lloc == null) {
				throw new I18NException("error.modification", "<lloc null>");
			}
			long llocID = lloc.getLlocID();
			Lloc oldLloc = findByPrimaryKey(llocID);
			if (oldLloc == null) {
				throw new I18NException("error.modification", new String[] { "lloc", LlocFields.LLOCID.sqlName,
						String.valueOf(llocID), "<oldLloc null>" });
			}

			HistoricLlocDAO historicOld = new HistoricLlocDAO(oldLloc);
			newLloc = update(lloc);
			long newLlocId = newLloc.getLlocID();
			String codiLlocNewLloc = newLloc.getCodiLloc();
			log.info("Lloc actualitzat: " + newLlocId);

			// actualitzam habilitacionsLlocss segons habilitacionsSeleccionades
			List<String> habilitacionsToInsert = new ArrayList<String>();
			if (habilitacionsSeleccionades != null) {
				habilitacionsToInsert = Arrays.stream(habilitacionsSeleccionades).collect(Collectors.toList());
			}
			Where llocHabilitacionsByLlocId = LlocHabilitacioFields.LLOCID.equal(newLlocId);
			List<LlocHabilitacio> llocsHabilitacionsOld = llocHabilitacioLogicaEjb.select(llocHabilitacionsByLlocId);
			// delete i actualitzar llistat d'inserts
			if (llocsHabilitacionsOld != null) {
				for (LlocHabilitacio llocHabilitacioOld : llocsHabilitacionsOld) {
					Long habilitacioId = llocHabilitacioOld.getHabilitacioId();
					String habilitacioIdStr = String.valueOf(habilitacioId);
					if (habilitacionsToInsert.contains(habilitacioIdStr)) {
						// ja existeix, no l'hem d'inserir
						habilitacionsToInsert.remove(habilitacioIdStr);
					} else {
						// no existeix en el llistat de noves, l'hem d'esborrar
						llocHabilitacioLogicaEjb.delete(llocHabilitacioOld);
						TypedQuery<Long> q = llocHabilitacioLogicaEjb.getEntityManager().createQuery(
								"Select count(lr.llocID) from LlocHabilitacioJPA as lr where lr.llocID = :llocId",
								Long.class);
						q.setParameter("llocId", newLlocId);
						Long count = q.getSingleResult();
						log.info("Desassignació feta de l'habilitació " + habilitacioIdStr
								+ " al lloc de feina "
								+ codiLlocNewLloc + ". Ara té " + count + " habilitacions assignades.");

						HabilitacioJPA habilitacio = habilitacioLogicaEjb.findByPrimaryKey(habilitacioId);
						TraduccioJPA nomHabilitacio = habilitacio.getNom();
						log.info("Creant històric de desassignació d'habilitació");
						createHistoricLlocHabilitacio(cai, usuariId, newLlocId, habilitacioId,
								codiLlocNewLloc, nomHabilitacio, false, "ca");
						log.info(
								"Creat històric de desassignació d'habilitació " + nomHabilitacio + " al lloc de feina "
										+ codiLlocNewLloc);
					}
				}
			}
			// insert
			for (String habilitacioIdStr : habilitacionsToInsert) {
				try {
					if (habilitacioIdStr == null || habilitacioIdStr.isEmpty()) {
						continue;
					}
					Long habilitacioId = Long.parseLong(habilitacioIdStr);
					// comprovam que el habilitacio existeix
					HabilitacioJPA habilitacio = habilitacioLogicaEjb.findByPrimaryKey(habilitacioId);
					if (habilitacio != null) {
						LlocHabilitacio llocHabilitacio = new es.caib.rfhab.persistence.LlocHabilitacioJPA();
						llocHabilitacio.setLlocID(newLlocId);
						llocHabilitacio.setHabilitacioId((long) habilitacioId);
						llocHabilitacio.setDataCreacio(new Timestamp(System.currentTimeMillis()));
						llocHabilitacioLogicaEjb.create(llocHabilitacio);
						TraduccioJPA nomHabilitacio = habilitacio.getNom();
						log.info("Assignació creada de l'habilitació " + nomHabilitacio + " al lloc de feina "
								+ codiLlocNewLloc);

						log.info("Creant històric d'assignació d'habilitació");
						createHistoricLlocHabilitacio(cai, usuariId, newLlocId, habilitacioId,
								codiLlocNewLloc, nomHabilitacio, true, "ca");
						log.info(
								"Creat històric d'assignació d'habilitació " + nomHabilitacio + " al lloc de feina "
										+ codiLlocNewLloc);
					} else {
						log.warn("No s'ha trobat l'Habilitacio amb ID " + habilitacioId
								+ ". No s'assigna al Lloc amb ID "
								+ llocID);
					}
				} catch (NumberFormatException nfe) {
					log.error("Error assignant habilitació al lloc de feina. El valor de l'habilitació no és numèric: "
							+ habilitacioIdStr);
				}
			}

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
			log.error(e);
			long llocID3 = lloc.getLlocID();
			throw new I18NException("error.modification", String.valueOf(llocID3));
		}
	}

	@Override
	@PermitAll
	public LlocJPA createAndHistory(Lloc lloc, String cai, Long usuariId, String[] habilitacionsSeleccionades)
			throws I18NException {
		LlocJPA llocJpa = null;

		if (lloc == null) {
			throw new I18NException("error.creation", "<lloc null>");
		}

		try {
			log.info("Creant lloc: " + lloc.getCodiLlocPropi() + " --> " + lloc.getCodiLloc() + " - "
					+ lloc.getExpansio());
			llocJpa = (LlocJPA) create(lloc);
			long llocCreatID = llocJpa.getLlocID();
			log.info("Lloc creat: " + llocCreatID);
			// cream habilitacionsLlocss segons habilitacionsSeleccionades
			if (habilitacionsSeleccionades != null) {
				for (String habilitacioIdStr : habilitacionsSeleccionades) {
					try {
						if (habilitacioIdStr == null || habilitacioIdStr.isEmpty()) {
							continue;
						}
						Long habilitacioId = Long.parseLong(habilitacioIdStr);
						// comprovam que el habilitacio existeix
						HabilitacioJPA habilitacio = habilitacioLogicaEjb.findByPrimaryKey(habilitacioId);
						if (habilitacio != null) {
							LlocHabilitacio llocHabilitacio = new es.caib.rfhab.persistence.LlocHabilitacioJPA();
							llocHabilitacio.setLlocID(llocCreatID);
							llocHabilitacio.setHabilitacioId((long) habilitacioId);
							llocHabilitacio.setDataCreacio(new Timestamp(System.currentTimeMillis()));
							llocHabilitacioLogicaEjb.create(llocHabilitacio);
							String codiLlocLlocCreat = llocJpa.getCodiLloc();
							TraduccioJPA nomHabilitacio = habilitacio.getNom();
							log.info("Assignació creada de l'habilitació " + nomHabilitacio + " al lloc de feina "
									+ codiLlocLlocCreat);

							log.info("Creant històric d'assignació d'habilitació");
							createHistoricLlocHabilitacio(cai, usuariId, llocCreatID, habilitacioId,
									codiLlocLlocCreat, nomHabilitacio, true, "ca");
							log.info(
									"Creat històric d'assignació d'habilitació " + nomHabilitacio + " al lloc de feina "
											+ codiLlocLlocCreat);
						} else {
							log.warn("No s'ha trobat l'Habilitacio amb ID " + habilitacioId
									+ ". No s'assigna al Lloc amb ID " + llocCreatID);
						}
					} catch (NumberFormatException nfe) {
						log.error(
								"Error assignant habilitació al lloc de feina. El valor de l'habilitació no és numèric: "
										+ habilitacioIdStr);
					}
				}
			}

			HistoricLlocJPA historicLloc = null;
			historicLloc = new HistoricLlocJPA();
			historicLloc.setLlocID(llocCreatID);
			historicLloc.setNumeroCai(cai);
			historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			historicLloc.setUsuariID(usuariId);

			HistoricLlocDAO historicNew = new HistoricLlocDAO(lloc);
			HistoricLlocDAO historicOld = new HistoricLlocDAO();
			historicLlocLogicaEjb.create(historicLloc, historicNew, historicOld);
			log.info("HistoricLloc creat: " + historicLloc.getHistoricllocID());

			return llocJpa;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.creation", String.valueOf(lloc.getLlocID()));
		}
	}

	private void createHistoricLlocHabilitacio(String cai, Long usuariId, long llocCreatID,
			Long habilitacioId, String codiLlocLlocCreat, TraduccioJPA nomHabilitacio, boolean isInsert, String lang)
			throws I18NException {
		HistoricLlocJPA historicLlocHabilitacioNova = new HistoricLlocJPA();
		historicLlocHabilitacioNova.setLlocID(llocCreatID);
		historicLlocHabilitacioNova.setNumeroCai(cai);
		historicLlocHabilitacioNova.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historicLlocHabilitacioNova.setUsuariID(usuariId);
		String historicLlocHAbilitacioNovaObservacions = null;
		if (isInsert) {
			historicLlocHAbilitacioNovaObservacions = "Nova assignació d'habilitació "
					+ nomHabilitacio.getTraduccio(lang) + " (id="
					+ habilitacioId + ") a lloc " + codiLlocLlocCreat + " (id " + llocCreatID + ")";
		} else {
			historicLlocHAbilitacioNovaObservacions = "Nova desassignació d'habilitació "
					+ nomHabilitacio.getTraduccio(lang) + " (id="
					+ habilitacioId + ") a lloc " + codiLlocLlocCreat + " (id " + llocCreatID + ")";
		}
		if (cai != null && !cai.isEmpty()) {
			historicLlocHAbilitacioNovaObservacions += " --> CAI: " + cai;
		}
		historicLlocLogicaEjb.create(historicLlocHabilitacioNova,
				historicLlocHAbilitacioNovaObservacions);
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

				LlocJPA llocJPA = new LlocJPA((long) obj[1], (String) obj[11], (String) obj[4], (String) obj[10],
						(String) obj[2],
						(long) entitatId,
						(long) obj[3], (Timestamp) obj[6], (int) obj[5], (Timestamp) obj[7], (Timestamp) obj[8],
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
	public List<Lloc> getLlocsByCodiIexpansio(String codiLloc, String expansio) throws I18NException {
		Where wCodiLloc = LlocFields.CODILLOC.equal(codiLloc);
		Where wExpansio = LlocFields.EXPANSIO.equal(expansio);
		return select(Where.AND(wCodiLloc, wExpansio));
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
						// volem el numero CAI
						// treim els historics tant de funcionaris com de llocs, i els que coincidexin
						// les observacions seran assignacions/desassignacions --> idò mirarem el que
						// tengui data més alta (serà una assignació perque estam recorrent els
						// funcionaris assignats)
						List<Historic> historicsFuncionari = historicLogicaEjb
								.select(HistoricFields.FUNCIONARIID.equal(funcionari.getFuncionariID()));
						List<HistoricLloc> historicsLloc = historicLlocLogicaEjb
								.select(HistoricLlocFields.LLOCID.equal(fl.getLlocID()));

						String numeroCai = null;
						Timestamp dataMaxima = null;

						// Cercar coincidències entre històrics de funcionari i lloc pel número CAI
						for (Historic hf : historicsFuncionari) {
							if (hf.getNumeroCai() != null) {
								for (HistoricLloc hl : historicsLloc) {
									if (hf.getNumeroCai().equals(hl.getNumeroCai())
											&& hf.getObservacions().equals(hl.getObservacions())) {
										// Coincideix el CAI, agafam la data més alta
										Timestamp dataHistoric = hl.getDataCreacio();
										if (dataHistoric != null
												&& (dataMaxima == null || dataHistoric.after(dataMaxima))) {
											dataMaxima = dataHistoric;
											numeroCai = hf.getNumeroCai();
										}
									}
								}
							}
						}

						llistaFuncionaris.add(new FuncionariLlocDAO(funcionari, fl, numeroCai));
					}
				}
				return llistaFuncionaris;
			}
		}
		return new ArrayList<FuncionariLlocDAO>();
	}

	@Override
	@PermitAll
	public List<Habilitacio> getHabilitacionsByLlocID(Long llocId) throws I18NException {

		if (llocId != null) {

			Where w = LlocHabilitacioFields.LLOCID.equal(llocId);

			List<LlocHabilitacio> llocsHabilitacions = llocHabilitacioLogicaEjb.select(w);

			if (llocsHabilitacions.size() > 0) {
				List<Habilitacio> llistaHabilitacions = new ArrayList<Habilitacio>(llocsHabilitacions.size());
				for (LlocHabilitacio lr : llocsHabilitacions) {
					Habilitacio habilitacio = habilitacioLogicaEjb.findByPrimaryKey(lr.getHabilitacioId());
					if (habilitacio != null) {
						llistaHabilitacions.add(habilitacio);
					}
				}
				return llistaHabilitacions;
			}

		}
		return new ArrayList<Habilitacio>();

	}

	public Funcionari donarDeBaixaLlocAndHistory(long llocId, final String numeroCai, long usuariId)
			throws I18NException {
		log.info("Donant de baixa Lloc amb ID " + llocId + " per a l'usuari " + usuariId + " amb CAI " + numeroCai);
		return funcionariLogicaEjb.dessassignarFuncionariAndHistory(null, llocId, numeroCai, usuariId, false, true);
	}

	public Lloc donarDeAltaAndHistory(java.lang.Long llocID, String numeroCai, long usuariId) throws I18NException {
		log.info("Donant d'alta Lloc amb ID " + llocID + " per a l'usuari " + usuariId + " amb CAI " + numeroCai);

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

	@Override
	@PermitAll
	public String getNouLlocCodiPropi(String codiLloc, String expansio) throws I18NException {
		// DESACTIVAM AQUESTA FUNCIONALITAT. ARA EL CODILLOCPROPI ÉS UNIQUE I NO ES POT
		// REPETIR MAI, PERQUÈ HI POT HAVER LLOCS DE FEINA AMB CODILLOC I EXPANSIO
		// NULLS, I AQUEST CAMP SERIA L'ÚNICA FORMA DE DIFERENCIAR-LOS
		// List<Lloc> llocsAmbMateixCodi = select(LlocFields.CODILLOC.equal(codiLloc));
		// if (llocsAmbMateixCodi != null && llocsAmbMateixCodi.size() > 0) {
		// return llocsAmbMateixCodi.get(0).getCodiLlocPropi();
		// }
		return generaNouLlocCodiPropi();
	}

	private String generaNouLlocCodiPropi() throws I18NException {
		int nouNumber = 1;
		Object maxLlocCodiPropi = null;
		try {
			maxLlocCodiPropi = getMaxLlocCodiPropi();
		} catch (SecurityException e) {
			throw new I18NException(e.getMessage());
		} catch (NoSuchFieldException e) {
			throw new I18NException(e.getMessage());
		}
		if (maxLlocCodiPropi != null) {
			// Extreu la part numèrica de la cadena
			String numericPart = maxLlocCodiPropi.toString()
					.substring(Constants.LLOC_CODILLOCPROPI_PLACEHOLDER_PREFIX.length());
			// Converteix la part numèrica a un enter, suma 1 i torna a formar la cadena
			nouNumber = Integer.parseInt(numericPart);
			nouNumber += 1;
		}
		// Format numèric amb el mateix nombre de dígits que l'original
		String updatedNumericPart = String
				.format("%0" + Constants.LLOC_CODILLOCPROPI_PLACEHOLDER_NUMERICPART.length() + "d", nouNumber);
		// Reconstrueix la cadena amb el prefix i el nou valor numèric
		String nouLlocCodiPropi = Constants.LLOC_CODILLOCPROPI_PLACEHOLDER_PREFIX + updatedNumericPart;
		return nouLlocCodiPropi;
	}
}
