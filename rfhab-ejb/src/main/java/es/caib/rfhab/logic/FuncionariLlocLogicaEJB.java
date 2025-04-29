package es.caib.rfhab.logic;

import java.sql.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.FuncionariLlocEJB;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.fields.FuncionariLlocFields;

/**
 * 
 * @autor jpou
 *
 */

@Stateless
public class FuncionariLlocLogicaEJB extends FuncionariLlocEJB implements FuncionariLlocLogicaService {

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
		List<FuncionariLloc> llocs = select(
				getWhereFuncionariIsCurrent(FuncionariLlocFields.FUNCIONARIID.equal(funcionariId)));
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
}
