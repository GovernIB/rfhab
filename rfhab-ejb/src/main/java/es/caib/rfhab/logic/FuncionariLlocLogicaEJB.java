package es.caib.rfhab.logic;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.FuncionariLlocEJB;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.persistence.FuncionariLlocJPA;
import es.caib.rfhab.persistence.HistoricJPA;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/**
 * 
 * @autor jpou
 *
 */
@Stateless
public class FuncionariLlocLogicaEJB extends FuncionariLlocEJB implements FuncionariLlocLogicaService {

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocLogicaEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariLogicaEjb;

	@EJB(mappedName = HistoricLogicaService.JNDI_NAME)
	protected HistoricLogicaService historicLogicaEjb;

	@EJB(mappedName = HistoricLlocLogicaService.JNDI_NAME)
	protected HistoricLlocLogicaService historicLlocLogicaEjb;

	public Where getWhereFuncionariIsCurrent() {
		Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThanOrEqual(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

		Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThanOrEqual(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.isNull());

		Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());

		Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

		return Where.OR(w1, w2, w3, w4);
	}

	public Where getWhereFuncionariIsCurrent(Where w) {
		return Where.AND(w, getWhereFuncionariIsCurrent());
	}

	@PermitAll
	public List<FuncionariLloc> donarDeBaixaFuncionariDeLloc(long funcionariId) throws I18NException {
		return donarDeBaixaFuncionariDeLloc(funcionariId, null);
	}

	@PermitAll
	public List<FuncionariLloc> donarDeBaixaFuncionariDeLloc(long funcionariId, Long llocId) throws I18NException {
		Where w = FuncionariLlocFields.FUNCIONARIID.equal(funcionariId);
		if (llocId != null) {
			w = Where.AND(w, FuncionariLlocFields.LLOCID.equal(llocId));
		}
		List<FuncionariLloc> llocs = select(
				getWhereFuncionariIsCurrent(w));
		// TODO:aquí només hauria d'haver-ni un
		if (llocs != null && llocs.size() > 0) {
			log.info("Trobats " + llocs.size() + " llocs de feina per al funcionari " + funcionariId);
			for (FuncionariLloc lloc : llocs) {
				lloc.setDataFi(new Date(System.currentTimeMillis()));
				update(lloc);
			}
		} else {
			log.info("No s'ha trobat cap lloc de feina per al funcionari " + funcionariId);
		}

		return llocs;
	}

	@PermitAll
	public List<FuncionariLloc> donarDeBaixaFuncionariDeLlocByLloc(long llocId) throws I18NException {
		List<FuncionariLloc> llocs = select(
				getWhereFuncionariIsCurrent(FuncionariLlocFields.LLOCID.equal(llocId)));
		// TODO:aquí només hauria d'haver-ni un
		if (llocs != null && llocs.size() > 0) {
			log.info("Trobats " + llocs.size() + " funcionaris assignats per al lloc de feina " + llocId);
			for (FuncionariLloc lloc : llocs) {
				lloc.setDataFi(new Date(System.currentTimeMillis()));
				update(lloc);
			}
		} else {
			log.info("No s'ha trobat cap funcionari assignat per al lloc de feina " + llocId);
		}

		return llocs;
	}

	public boolean isFuncionariAssignat(long funcionariID) throws I18NException {
		return count(Where.AND(FuncionariLlocFields.DATAFI.isNull(),
				FuncionariLlocFields.FUNCIONARIID.equal(funcionariID))) > 0;
	}

	public boolean isLlocAssignat(long llocID) throws I18NException {
		return count(Where.AND(FuncionariLlocFields.DATAFI.isNull(),
				FuncionariLlocFields.LLOCID.equal(llocID))) > 0;
	}

	@PermitAll
	public FuncionariLlocJPA assignarFuncionari(FuncionariLlocJPA funcionariLloc, String numeroCai, String observacions,
			long usuarId)
			throws I18NException, I18NValidationException {
		long funcionariID = funcionariLloc.getFuncionariID();
		long llocID = funcionariLloc.getLlocID();
		log.info(numeroCai + " --> assignant funcionari " + funcionariID + " a lloc "
				+ llocID + " amb observacions " + observacions);

		// Validam
		Funcionari funcionari = funcionariLogicaEjb.findByPrimaryKey(funcionariID);
		if (funcionari == null) {
			throw new I18NException("error.notfound",
					new String[] { "funcionari.funcionari", "funcionari.funcionariID", String.valueOf(funcionariID) });
		}

		Lloc lloc = llocLogicaEjb.findByPrimaryKey(llocID);
		if (lloc == null) {
			throw new I18NException("error.notfound",
					new String[] { "lloc.lloc", "lloc.llocID", String.valueOf(llocID) });
		}

		String funcionariIdentificador = funcionari.getIdentificador();
		if (funcionari.getDataBaixa() != null) {
			throw new I18NValidationException(
					java.util.Collections.singletonList(
							new I18NFieldError(FuncionariLlocFields.FUNCIONARIID,
									new I18NTranslation("funcionarilloc.error.funcionari.baixa",
											funcionariIdentificador))));
		}

		String llocCodi = lloc.getCodiLloc();
		if (lloc.getDataBaixa() != null || lloc.getDataalta() == null) {
			throw new I18NValidationException(new I18NFieldError(FuncionariLlocFields.LLOCID,
					new I18NTranslation("funcionarilloc.error.lloc.baixa", llocCodi)));
		}

		// no permetre que un funcionari estigui assignat a més d'un lloc de feina
		if (isFuncionariAssignat(funcionariID)) {
			throw new I18NValidationException(
					java.util.Collections.singletonList(new I18NFieldError(FuncionariLlocFields.FUNCIONARIID,
							new I18NTranslation("funcionarilloc.error.funcionari.assignat", funcionariIdentificador))));
		}

		// no permetre que un lloc de feina tengui assignat més d'un funcionari
		if (isLlocAssignat(llocID)) {
			throw new I18NValidationException(
					java.util.Collections.singletonList(new I18NFieldError(FuncionariLlocFields.LLOCID,
							new I18NTranslation("funcionarilloc.error.lloc.assignat", llocCodi))));
		}

		// Cream assignació
		log.info("Validacions correctes, creant assignació de funcionari " + funcionariID
				+ " a lloc " + llocID);
		FuncionariLlocJPA funcionariLlocJPA = (FuncionariLlocJPA) create(funcionariLloc);

		// Cream històrics
		log.info("Creant històrics de funcionari i lloc");
		HistoricLlocJPA historicLloc = new HistoricLlocJPA();
		historicLloc.setLlocID(llocID);
		historicLloc.setNumeroCai(numeroCai);
		historicLloc.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historicLloc.setUsuariID(usuarId);
		HistoricJPA historicFuncionari = new HistoricJPA();
		historicFuncionari.setFuncionariID(funcionariID);
		historicFuncionari.setNumeroCai(numeroCai);
		historicFuncionari.setDataCreacio(new Timestamp(System.currentTimeMillis()));
		historicFuncionari.setUsuariID(usuarId);

		String funcionariIdString = Long.toString(funcionariID);
		String llocIdString = Long.toString(llocID);

		String historicLlocObservacions = "Nova assignació de funcionari " + funcionariIdentificador + " (id "
				+ funcionariIdString + ") a lloc " + llocCodi + " (id " + llocIdString + ")";
		if (numeroCai != null && !numeroCai.isEmpty()) {
			historicLlocObservacions += " --> CAI: " + numeroCai;
		}
		if (observacions != null && !observacions.isEmpty()) {
			historicLlocObservacions += " --> Observacions: " + observacions;
		}
		historicLlocLogicaEjb.create(historicLloc, historicLlocObservacions);
		historicLogicaEjb.create(historicFuncionari, historicLlocObservacions);

		return funcionariLlocJPA;
	}

	public List<FuncionariLloc> getFuncionariLlocsActualmentNoAssignats(long funcionariID, long llocID)
			throws I18NException {
		return select(Where
				.AND(FuncionariLlocFields.FUNCIONARIID.equal(funcionariID), FuncionariLlocFields.LLOCID.equal(llocID),
						FuncionariLlocFields.DATAFI.isNotNull()));
	}
}
