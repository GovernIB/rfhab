package es.caib.rfhab.logic;

import java.sql.Timestamp;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UsuariService;
import es.caib.rfhab.logic.utils.TicketAccesDto.RpersonaInfo;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Usuari;
import es.caib.rfhab.persistence.UsuariJPA;

/**
 * 
 * @author jagarcia
 * @author jpou
 *
 */
@Local
public interface UsuariLogicaService extends UsuariService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/UsuariLogicaEJB!es.caib.rfhab.logic.UsuariLogicaService";

	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException, javax.ejb.EJBException;

	public UsuariJPA findByUsername(String username) throws I18NException;

	public UsuariJPA findByNif(String nif) throws I18NException;

	public UsuariJPA update(UsuariJPA usuari) throws I18NException, javax.ejb.EJBException;

	public String checkIsActiuIteNif(Integer usuariId, String language) throws I18NException;

	public String checkIsActiuIteNif(Usuari usuari, String language) throws I18NException;

	public String registraActivitatIobteTicketAccessFh(Funcionari funcionari, String codiDir3, RpersonaInfo interessat,
			RpersonaInfo representant, String idTramiteCatalogo, String ticketLanguage, String ticketParametros,
			boolean servicioCatalogo, String tramite, String tramitVersio, String unitatAdministrativa,
			Timestamp dataActivitat, String procediment, String arxiuExpedientId, String arxiuDocumentId)
			throws I18NException;

}
