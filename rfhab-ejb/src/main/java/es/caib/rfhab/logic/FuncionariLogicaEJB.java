package es.caib.rfhab.logic;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.FuncionariEJB;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Historic;
import es.caib.rfhab.model.entity.HistoricLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.HistoricJPA;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Stateless
public class FuncionariLogicaEJB extends FuncionariEJB implements FuncionariLogicaService {

	@EJB(mappedName = HistoricLogicaService.JNDI_NAME)
	protected HistoricLogicaService historicLogicaEjb;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	protected FuncionariLlocLogicaService funcionariLlocLogicaEjb;

	@EJB(mappedName = HistoricLlocLogicaService.JNDI_NAME)
	protected HistoricLlocLogicaService historicLlocLogicaEjb;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocEjb;

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
	public boolean isFuncionariActiu(FuncionariJPA funcionari) throws I18NException {
		Timestamp ara = new Timestamp(System.currentTimeMillis());
		Timestamp funcionariDataCreacio = funcionari.getDataCreacio();
		Timestamp funcionariDataBaixa = funcionari.getDataBaixa();
		boolean donatDalta = funcionariDataCreacio != null && funcionariDataCreacio.compareTo(ara) <= 0;
		boolean donatDeBaixa = funcionariDataBaixa != null && funcionariDataBaixa.compareTo(ara) <= 0;
		return donatDalta && !donatDeBaixa;
	}

	@Override
	@PermitAll
	public FuncionariJPA createAndHistory(Funcionari funcionari, String cai, Long usuariId) throws I18NException {

		FuncionariJPA funcionariJpa = null;

		if (funcionari == null) {
			throw new I18NException("error.creation", "<funcionari null>");
		}

		try {
			log.info("Creant funcionari: " + funcionari.getNom() + " " + funcionari.getLlinatge1() + " "
					+ funcionari.getLlinatge2());
			funcionariJpa = (FuncionariJPA) create(funcionari);
			log.info("Funcionari creat: " + funcionariJpa.getFuncionariID());
			Historic nou = historicLogicaEjb.create(funcionariJpa, cai, usuariId);
			log.info("Historic de funcionari creat: " + nou.getHistoricID());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.creation",
					String.valueOf(funcionari.getFuncionariID()) + " (CAI: " + cai + ")");
		}

		return funcionariJpa;
	}

	@Override
	@PermitAll
	public Funcionari updateAndHistory(Funcionari funcionari, String cai, Long usuariId) throws I18NException {
		try {
			Funcionari newFuncionari = null;
			if (funcionari == null) {
				throw new I18NException("error.modification", "<funcionari null>");
			}
			Funcionari oldFuncionari = findByPrimaryKey(funcionari.getFuncionariID());
			if (oldFuncionari == null) {
				throw new I18NException("error.modification",
						new String[] { "funcionari", FuncionariFields.FUNCIONARIID.sqlName,
								String.valueOf(funcionari.getFuncionariID()), "<oldFuncionari null>" });
			}

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

			HistoricFuncionariDAO historicOld = new HistoricFuncionariDAO(oldFuncionari);
			newFuncionari = update(funcionari);
			log.info("Funcionari actualitzat: " + newFuncionari.getFuncionariID());

			HistoricJPA historicFuncionari = new HistoricJPA();
			historicFuncionari.setFuncionariID(oldFuncionari.getFuncionariID());
			historicFuncionari.setNumeroCai(cai);
			historicFuncionari.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			historicFuncionari.setUsuariID(usuariId);

			HistoricFuncionariDAO historicNew = new HistoricFuncionariDAO(newFuncionari);
			Historic historicCreat = historicLogicaEjb.create(historicFuncionari, historicNew, historicOld);
			log.info("Historic de funcionari creat: " + historicCreat.getHistoricID());

			return newFuncionari;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.modification", String.valueOf(funcionari.getFuncionariID()));
		}
	}

	// si llocId és null, es desassigna de tots els llocs de feina
	public Funcionari dessassignarFuncionariAndHistory(Funcionari funcionari, Long llocId, final String numeroCai,
			long usuariId, boolean donarDeBaixaFuncionari, boolean donarDeBaixaLloc)
			throws I18NException {
		Funcionari newFuncionari = null;

		List<FuncionariLloc> funcionarisLlocsDonatsDeBaixa;
		if (funcionari == null) {
			// el desasignam del lloc de feina
			funcionarisLlocsDonatsDeBaixa = funcionariLlocLogicaEjb
					.donarDeBaixaFuncionariDeLlocByLloc(llocId);
			log.info("Assignacions de Funcionaris al Lloc de Feina " + llocId + " actualitzades");

			// Lloc de feina: el donam de baixa
			if (donarDeBaixaLloc) {
				Lloc lloc = llocEjb.findByPrimaryKey(llocId);
				if (lloc == null) {
					throw new I18NException("error.modification", "<lloc null>");
				}
				String codiLloc = lloc.getCodiLloc();
				lloc.setDataBaixa(new Timestamp(System.currentTimeMillis()));
				llocEjb.update(lloc);
				log.info("Lloc de feina actualitzat: " + llocId);

				// afegim històric del Lloc de feina
				HistoricLlocJPA historicLloc = new HistoricLlocJPA();
				historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
				historicLloc.setLlocID(llocId);
				historicLloc.setNumeroCai(numeroCai);
				historicLloc.setUsuariID(usuariId);
				String observacions = "Lloc de feina " + codiLloc + " donat de baixa";
				HistoricLloc historicCreat = historicLlocLogicaEjb.create(historicLloc, observacions);
				log.info("Històric de Lloc creat: " + historicCreat.getHistoricllocID());
			}
		} else {
			long funcionariId = funcionari.getFuncionariID();
			String funcionariIdentificador = funcionari.getIdentificador();

			// el desasignam del lloc de feina
			funcionarisLlocsDonatsDeBaixa = funcionariLlocLogicaEjb
					.donarDeBaixaFuncionariDeLloc(funcionariId, llocId);
			log.info("Assignacions de Llocs de Feina de Funcionari " + funcionariId + " actualitzades");

			// Funcionari: el donam de baixa
			if (donarDeBaixaFuncionari) {
				funcionari.setDataBaixa(new Timestamp(System.currentTimeMillis()));
				newFuncionari = update(funcionari);
				log.info("Funcionari actualitzat: " + newFuncionari.getFuncionariID());

				// afegim històric de funcionari
				HistoricJPA historic = new HistoricJPA();
				historic.setDataCreacio(new Timestamp(System.currentTimeMillis()));
				historic.setFuncionariID(funcionariId);
				historic.setNumeroCai(numeroCai);
				historic.setUsuariID(usuariId);
				String observacions = "Funcionari " + funcionariIdentificador + " donat de baixa";
				Historic historicCreat = historicLogicaEjb.create(historic, observacions);
				log.info("Històric de funcionari creat: " + historicCreat.getHistoricID());
			}
		}

		// afegim històric de llocs de feina
		for (FuncionariLloc funcionariLloc : funcionarisLlocsDonatsDeBaixa) {
			long funcionariId = funcionariLloc.getFuncionariID();
			Funcionari funcionariDelLloc = findByPrimaryKey(funcionariId);
			if (funcionariDelLloc == null) {
				log.error("No s'ha trobat el funcionari " + funcionariId
						+ " per afegir al seu històric de llocs de feina");
				continue;
			}
			String funcionariIdentificador = funcionariDelLloc.getIdentificador();

			HistoricLlocJPA historicLloc = new HistoricLlocJPA();
			long llocID = funcionariLloc.getLlocID();
			historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			historicLloc.setLlocID(llocID);
			historicLloc.setNumeroCai(numeroCai);
			historicLloc.setUsuariID(usuariId);

			Lloc lloc = llocEjb.findByPrimaryKey(llocID);
			String llocCodi = "<null>";
			if (lloc != null) {
				llocCodi = lloc.getCodiLloc();
			}
			String historicLlocObservacions = "Nova desassignació del funcionari " + funcionariIdentificador
					+ " (id "
					+ funcionariId + ") del lloc " + llocCodi + " (id " + llocID + ")";
			HistoricLloc historicLlocCreat = historicLlocLogicaEjb.create(historicLloc, historicLlocObservacions);
			log.info("Històric de lloc de feina de funcionari creat: " + historicLlocCreat.getHistoricllocID());

			HistoricJPA historicFuncionari = new HistoricJPA();
			historicFuncionari.setFuncionariID(funcionariId);
			historicFuncionari.setNumeroCai(numeroCai);
			historicFuncionari.setDataCreacio(new Timestamp(System.currentTimeMillis()));
			historicFuncionari.setUsuariID(usuariId);

			Historic historicCreat = historicLogicaEjb.create(historicFuncionari, historicLlocObservacions);
			log.info("Històric de funcionari creat: " + historicCreat.getHistoricID());
		}

		return newFuncionari;
	}

	public Funcionari donarDeBaixaFuncionariAndHistory(Funcionari funcionari, final String numeroCai, long usuariId)
			throws I18NException {
		log.info("Donant de baixa funcionari amb ID " + funcionari.getFuncionariID() + " per a l'usuari " + usuariId
				+ " i CAI " + numeroCai);
		return dessassignarFuncionariAndHistory(funcionari, null, numeroCai, usuariId, true, false);
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
	public Object getMaxFuncionariNumero() throws SecurityException, NoSuchFieldException {

		StringBuilder queryString = new StringBuilder(
				"select max(rf." + FuncionariFields.NUMERO.javaName + ") from " + FuncionariJPA.class.getName()
						+ " rf where rf." + FuncionariFields.NUMERO.javaName + " like '"
						+ Constants.SQL_FUNCIONARI_NUMERO_PATTERN
						+ "' escape '" + Constants.SQL_LIKE_ESCAPE_PATTERN + "'");
		Class<?> numeroClass = FuncionariFields.NUMERO.getClass().getField("javaName").getType();// TODO:comentar a
																									// anadal que
																									// getJavaClass no
																									// funciona perquè
																									// javaClass és
																									// javaName
		TypedQuery<?> query = getEntityManager().createQuery(queryString.toString(),
				numeroClass);
		List<?> resultats = query.getResultList();

		return (resultats != null && !resultats.isEmpty()) ? (resultats.get(0)) : null;
	}

	@Override
	@PermitAll
	public Funcionari donarDeAltaAndHistory(java.lang.Long funcionariID, String numeroCai, long usuarId)
			throws I18NException {
		log.info("Donant d'alta funcionari amb ID " + funcionariID);

		FuncionariJPA funcionari = findByPrimaryKey(funcionariID);
		if (funcionari == null) {
			log.error("No s'ha trobat el funcionari amb ID " + funcionariID);
			throw new I18NException("funcionari.error.noexisteix", funcionariID.toString());
		}

		// afegim històric
		HistoricJPA historic = new HistoricJPA();
		historic.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historic.setFuncionariID(funcionariID);
		historic.setNumeroCai(numeroCai);
		historic.setUsuariID(usuarId);
		historicLogicaEjb.create(historic, "Funcionari " + funcionari.getIdentificador() + " donat d'alta de nou");

		// el donam d'alta
		funcionari.setDataBaixa(null);
		return update(funcionari);
	}

	@Override
	@PermitAll
	public FuncionariJPA findByNif(String nif) throws I18NException {
		List<Funcionari> funcionaris = super.select(Where.OR(FuncionariFields.IDENTIFICADOR.equal(nif),
				FuncionariFields.IDENTIFICADOR.equal(nif.toUpperCase()),
				FuncionariFields.IDENTIFICADOR.equal(nif.toLowerCase())));
		if (funcionaris == null || funcionaris.size() == 0) {
			return null;
		}
		if (funcionaris.size() > 1) {
			throw new I18NException("funcionari.error.mesdun", nif);
		}
		return (FuncionariJPA) funcionaris.get(0);
	}

	@Override
	@PermitAll
	public FuncionariJPA comprovarFuncionariActiuByNif(String language, String funcionariNif, boolean checkLloc)
			throws I18NException {
		FuncionariJPA funcionari = findByNif(funcionariNif);

		if (funcionari == null) {
			String errorNoExisteixNif = I18NCommonUtils.tradueix(new Locale(language),
					"funcionari.error.noexisteixnif",
					new String[] { funcionariNif });
			log.error(errorNoExisteixNif);
			throw new I18NException(errorNoExisteixNif);
		}
		if (!isFuncionariActiu(funcionari)) {
			String errorNoActiu = I18NCommonUtils.tradueix(new Locale(language),
					"funcionari.error.noactiu",
					new String[] { funcionariNif });
			log.error(errorNoActiu);
			throw new I18NException(errorNoActiu);
		}

		if (!checkLloc) {
			return funcionari;
		}

		Where funcionariAssignatW = FuncionariLlocFields.FUNCIONARIID.equal(funcionari.getFuncionariID());
		Where funcionariActualmentAssignat = funcionariLlocLogicaEjb.getWhereFuncionariIsCurrent(funcionariAssignatW);
		List<FuncionariLloc> funcionarisLlocs = funcionariLlocLogicaEjb.select(funcionariActualmentAssignat);
		if (funcionarisLlocs == null || funcionarisLlocs.size() == 0) {
			String errorNoAssignat = I18NCommonUtils.tradueix(new Locale(language),
					"funcionari.error.noassignatlloc",
					new String[] { funcionariNif });
			log.error(errorNoAssignat);
			throw new I18NException(errorNoAssignat);
		}

		boolean llocActiu = false;
		Timestamp ara = new Timestamp(System.currentTimeMillis());
		List<Lloc> llocsOcupatsPerFuncionari = new ArrayList<Lloc>();
		List<Lloc> llocsNoActiusOcupatsPerFuncionari = new ArrayList<Lloc>();
		for (FuncionariLloc fl : funcionarisLlocs) {
			Lloc lloc = llocEjb.findByPrimaryKey(fl.getLlocID());
			if (lloc != null) {
				llocsOcupatsPerFuncionari.add(lloc);
				Timestamp llocDataAlta = lloc.getDataalta();
				if (lloc.getDataBaixa() == null && llocDataAlta != null && llocDataAlta.compareTo(ara) <= 0) {
					llocActiu = true;
					break;
				} else {
					llocsNoActiusOcupatsPerFuncionari.add(lloc);
				}
			}
		}

		if (!llocActiu) {
			String errorLlocNoActiu = I18NCommonUtils.tradueix(new Locale(language),
					"funcionari.error.llocnoactiu",
					new String[] { funcionariNif, llocsNoActiusOcupatsPerFuncionari.get(0).getCodiLlocPropi() });
			log.error(errorLlocNoActiu);
			throw new I18NException(errorLlocNoActiu);
		}

		return funcionari;
	}

	@Override
	@PermitAll
	public String getNouFuncionariNumero() throws I18NException {
		int nouNumber = 1;
		Object maxFuncionariNumero = null;
		try {
			maxFuncionariNumero = getMaxFuncionariNumero();
		} catch (SecurityException e) {
			throw new I18NException(e.getMessage());
		} catch (NoSuchFieldException e) {
			throw new I18NException(e.getMessage());
		}
		if (maxFuncionariNumero != null) {
			// Extreu la part numèrica de la cadena
			String numericPart = maxFuncionariNumero.toString()
					.substring(Constants.FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX.length());
			// Converteix la part numèrica a un enter, suma 1 i torna a formar la cadena
			nouNumber = Integer.parseInt(numericPart);
			nouNumber += 1;
		}
		String nouFuncionariNumero = getNumeroFhFromNumeric(nouNumber);
		return nouFuncionariNumero;
	}

	@Override
	@PermitAll
	public String getNumeroFhFromNumeric(int nouNumber) {
		// Format numèric amb el mateix nombre de dígits que l'original
		String updatedNumericPart = String
				.format("%0" + Constants.FUNCIONARI_NUMERO_PLACEHOLDER_NUMERICPART.length() + "d", nouNumber);
		// Reconstrueix la cadena amb el prefix i el nou valor numèric
		String nouFuncionariNumero = Constants.FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX + updatedNumericPart;
		return nouFuncionariNumero;
	}

}
