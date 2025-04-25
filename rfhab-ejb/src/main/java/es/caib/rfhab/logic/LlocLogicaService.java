package es.caib.rfhab.logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import es.caib.rfhab.ejb.LlocService;
import es.caib.rfhab.logic.utils.FuncionariLlocDAO;
import es.caib.rfhab.logic.utils.HistoricLlocDAO;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.persistence.HistoricLlocJPA;
import es.caib.rfhab.persistence.LlocJPA;

/**
 * 
 * @autor jagarcia
 *
 */

@Local
public interface LlocLogicaService extends LlocService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/LlocLogicaEJB!es.caib.rfhab.logic.LlocLogicaService";

	public HistoricLlocJPA createAndHistory(Lloc lloc, String cai, Long usuariId) throws I18NException;

	public Lloc updateAndHistory(Lloc lloc, String cai, Long usuariId) throws I18NException;

	public HashMap<Long, LlocJPA> getAllLlocsOcupats(Long entitatId) throws I18NException;

	public HashMap<Long, Funcionari> getCurrentFuncionarisByLloc(Long llocId, Long entitatId) throws I18NException;

	public List<Lloc> getLlocByFuncionariID(Long funcionariId, boolean current) throws I18NException;

	public List<Funcionari> getFuncionarisByLlocID(Long llocId) throws I18NException;

	public List<Funcionari> getFuncionarisByLlocID(Long llocId, boolean current) throws I18NException;

	public List<FuncionariLlocDAO> getFuncionarisLlocByLlocID(Long llocId) throws I18NException;

	public List<FuncionariLlocDAO> getFuncionarisLlocByLlocID(Long llocId, boolean current) throws I18NException;

	public List<Rol> getRolsByLlocID(Long llocId) throws I18NException;
}
