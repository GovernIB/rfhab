package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.FuncionariLlocService;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.persistence.FuncionariLlocJPA;

/**
 * 
 * @autor jpou
 *
 */
@Local
public interface FuncionariLlocLogicaService extends FuncionariLlocService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/FuncionariLlocLogicaEJB!es.caib.rfhab.logic.FuncionariLlocLogicaService";

	public Where getWhereFuncionariIsCurrent();

	public Where getWhereFuncionariIsCurrent(Where w);

	public List<FuncionariLloc> donarDeBaixaFuncionariDeLloc(long funcionariId) throws I18NException;

	public List<FuncionariLloc> donarDeBaixaFuncionariDeLloc(long funcionariId, Long llocId) throws I18NException;

	public List<FuncionariLloc> donarDeBaixaFuncionariDeLlocByLloc(long llocId) throws I18NException;

	public boolean isFuncionariAssignat(long funcionariID) throws I18NException;

	public boolean isLlocAssignat(long llocID) throws I18NException;

	public FuncionariLlocJPA assignarFuncionari(FuncionariLloc funcionariLloc, String numeroCai, String observacions,
			long usuarId)
			throws I18NException, I18NValidationException;

	public List<FuncionariLloc> getFuncionariLlocsActualmentNoAssignats(long funcionariID, long llocID)
			throws I18NException;
}
