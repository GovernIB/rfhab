package es.caib.rfhab.logic;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.LlocService;
import es.caib.rfhab.logic.utils.FuncionariLlocDAO;
import es.caib.rfhab.logic.utils.FuncionariLlocLlocDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.Habilitacio;
import es.caib.rfhab.persistence.LlocJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Local
public interface LlocLogicaService extends LlocService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/LlocLogicaEJB!es.caib.rfhab.logic.LlocLogicaService";

	public LlocJPA createAndHistory(Lloc lloc, String cai, Long usuariId, String[] habilitacionsSeleccionades)
			throws I18NException;

	public Lloc updateAndHistory(Lloc lloc, String cai, Long usuariId, String[] habilitacionsSeleccionades)
			throws I18NException;

	public HashMap<Long, Funcionari> getCurrentFuncionarisByLloc(Long llocId, Long entitatId) throws I18NException;

	public List<FuncionariLlocLlocDAO> getLlocHistoricByFuncionariID(Long funcionariId, boolean current)
			throws I18NException;

	public List<Lloc> getLlocByFuncionariID(Long funcionariId, boolean current) throws I18NException;

	public List<Lloc> getLlocsByCodiIexpansio(String codiLloc, String expansio) throws I18NException;

	public List<Funcionari> getFuncionarisByLlocID(Long llocId) throws I18NException;

	public List<Funcionari> getFuncionarisByLlocID(Long llocId, boolean current) throws I18NException;

	public List<FuncionariLlocDAO> getFuncionarisLlocByLlocID(Long llocId) throws I18NException;

	public List<FuncionariLlocDAO> getFuncionarisLlocByLlocID(Long llocId, boolean current) throws I18NException;

	public List<Habilitacio> getHabilitacionsByLlocID(Long llocId) throws I18NException;

	public Funcionari donarDeBaixaLlocAndHistory(long llocId, final String numeroCai, long usuariId)
			throws I18NException;

	public Lloc donarDeAltaAndHistory(java.lang.Long llocID, String numeroCai, long usuariId) throws I18NException;

	public Object getMaxLlocCodiPropi() throws SecurityException, NoSuchFieldException;

	public String getNouLlocCodiPropi(String codiLloc, String expansio) throws I18NException;

}
