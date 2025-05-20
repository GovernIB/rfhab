package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.FuncionariLlocService;
import es.caib.rfhab.model.entity.FuncionariLloc;

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
}
