package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.FuncionariService;
import es.caib.rfhab.logic.utils.HistoricFuncionariDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.persistence.RolJPA;

/**
 * 
 * @autor jagarcia
 *
 */

@Local
public interface FuncionariLogicaService extends FuncionariService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/FuncionariLogicaEJB!es.caib.rfhab.logic.FuncionariLogicaService";
	
	public List<RolJPA> getRolsByFuncionariID(Long funcionariId) throws I18NException;
	
	public List<Rol> getRolsByFuncionariIDv2(Long funcionariId) throws I18NException;
	
	public long getFuncionariID(String numero, String usuari, Long entitatId) throws I18NException;
	
	public boolean isFuncionariAutoritzat(Long funcionariId, String codiSia, Long entitatId) throws I18NException;
	
	public boolean isFuncionariHabilitat(Long funcionariId, String codiRol, Long entitatId) throws I18NException;
		
	public Funcionari createAndHistory(Funcionari funcionari, String cai, Long usuariId) throws I18NException;
	
	public Funcionari updateAndHistory (Funcionari funcionari, String cai, Long usuariId) throws I18NException;
	
	public HistoricFuncionariDAO fromJson(String json) throws I18NException;
}
