package es.caib.rfhab.logic;

import java.util.Properties;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.logic.utils.JsonUtils;
import es.caib.rfhab.logic.utils.TicketAccesDto.InfoTicketDto;
import es.caib.rfhab.logic.utils.TicketAccesDto.RfuncionarioHabilitadoInfo;
import es.caib.rfhab.logic.utils.TicketAccesDto.RpersonaInfo;
import es.caib.rfhab.logic.utils.TicketAccesDto.RtramiteFH;
import es.caib.rfhab.model.entity.Funcionari;

@Stateless
public class SistramitLogicaEJB implements SistramitLogicaService {

    public final Logger log = Logger.getLogger(this.getClass());

    final String endpoint;
    final String usuari;
    final String pass;

    public SistramitLogicaEJB() throws I18NException {
        super();

        endpoint = Configuracio.getSistramitEndpoint();
        usuari = Configuracio.getSistramitUser();
        pass = Configuracio.getSistramitPassword();
        if (endpoint == null || usuari == null || pass == null) {
            throw new I18NException("Sistramit configuration is incomplete. Please check the properties file.");
        }
    }

    public SistramitLogicaEJB(Properties properties) throws I18NException {
        super();

        endpoint = Configuracio.getSistramitEndpoint(properties);
        usuari = Configuracio.getSistramitUser(properties);
        pass = Configuracio.getSistramitPassword(properties);
        if (endpoint == null || usuari == null || pass == null) {
            throw new I18NException("Sistramit configuration is incomplete. Please check the properties file.");
        }
    }

    /**
     * Retorna el ticket d'accés per a la Fundació Hospital de la Santa Creu i Sant
     * Pau.
     * 
     * @return String amb el ticket d'accés.
     * @throws I18NException
     */
    @Override
    public String getTicketAccesoFh(Funcionari funcionari, String codiDir3, RpersonaInfo interessat, RpersonaInfo representant, String idTramiteCatalogo, String ticketLanguage, String ticketParametros, boolean servicioCatalogo, String tramite, Integer tramiteVersion) throws I18NException {
        final String entitat = "ticketAccesoFH";

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        RfuncionarioHabilitadoInfo funcionarioHabilitat = RfuncionarioHabilitadoInfo.fromFuncionari(funcionari, codiDir3);
        RtramiteFH tramit = new RtramiteFH(idTramiteCatalogo, ticketLanguage, ticketParametros, servicioCatalogo, tramite, tramiteVersion);
        InfoTicketDto requestBodyDto = new InfoTicketDto(funcionarioHabilitat, interessat, representant, tramit);

        log.info("Sistramit: " + entitat + " request: " + JsonUtils.toJson(requestBodyDto));
        final ResponseEntity<String> responseTicketAccesFh = restTemplate
                .postForEntity(endpoint + entitat, requestBodyDto, String.class);

        if (responseTicketAccesFh != null && responseTicketAccesFh.getStatusCode().is2xxSuccessful()) {
            log.debug("Sistramit: " + entitat + " response: " + responseTicketAccesFh.getBody());
            return responseTicketAccesFh.getBody();
        } else {
            log.error("Sistramit: " + entitat + " failed with status code: " + responseTicketAccesFh.getStatusCode());
            log.error("Sistramit: " + entitat + " failed with body: " + responseTicketAccesFh.getBody());
            throw new RuntimeException("Failed to get ticket access for FH. Status code: "
                    + responseTicketAccesFh.getStatusCode());
        }
    }
}
