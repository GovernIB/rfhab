package es.caib.rfhab.logic;

import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.FuncionariService;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.persistence.FuncionariJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Local
public interface FuncionariLogicaService extends FuncionariService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/FuncionariLogicaEJB!es.caib.rfhab.logic.FuncionariLogicaService";

	public long getFuncionariID(String numero, String usuari, Long entitatId) throws I18NException;

	public boolean isFuncionariAutoritzat(Long funcionariId, String codiSia, Long entitatId) throws I18NException;

	public boolean isFuncionariHabilitat(Long funcionariId, String codiRol, Long entitatId) throws I18NException;

	public FuncionariJPA createAndHistory(Funcionari funcionari, String cai, Long usuariId) throws I18NException;

	public Funcionari updateAndHistory(Funcionari funcionari, String cai, Long usuariId) throws I18NException;

	public HistoricFuncionariDAO fromJson(String json) throws I18NException;

	public Object getMaxFuncionariNumero() throws SecurityException, NoSuchFieldException;

	public Funcionari dessassignarFuncionariAndHistory(Funcionari funcionari, Long llocId, final String numeroCai,
			long usuariId, boolean donarDeBaixaFuncionari, boolean donarDeBaixaLloc) throws I18NException;

	public Funcionari donarDeBaixaFuncionariAndHistory(Funcionari funcionari, final String numeroCai, long usuariId)
			throws I18NException;

	public Funcionari donarDeAltaAndHistory(java.lang.Long funcionariID, String numeroCai, long usuarId)
			throws I18NException;

	public FuncionariJPA findByNif(String nif) throws I18NException;

	public boolean isFuncionariActiu(FuncionariJPA funcionari) throws I18NException;

	public FuncionariJPA comprovarFuncionariActiuByNif(String language, String funcionariNif, boolean checkLloc)
			throws I18NException;

	public String getNouFuncionariNumero() throws I18NException;

	public String getNumeroFhFromNumeric(int nouNumber);
}
