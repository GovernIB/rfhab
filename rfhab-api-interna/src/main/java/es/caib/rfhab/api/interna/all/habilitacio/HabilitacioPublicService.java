package es.caib.rfhab.api.interna.all.habilitacio;

import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.HabilitacioLogicaService;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.HabilitacioJPA;

import org.fundaciobit.pluginsib.utils.rest.RestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

/**
 * Servei JSON d'accés Públic de consulta d'habilitacions de Funcionaris/àries
 * habilitats/ades
 */
@Path("/public/habilitacio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(tags = @Tag(name = "HabilitacioPublicService", description = "Servei JSON d'accés Públic de consulta d'habilitacions de Funcionaris/àries habilitats/ades"), info = @Info(title = "API REST INTERNA PÚBLICA de RFHab", description = "Conjunt de Serveis REST de RFHab per ser accedits públicament", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "governdigital@ibdigital.caib.es", url = "https://governdigital.fundaciobit.org")

), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplepublic-v1"))
public class HabilitacioPublicService {

        protected final Logger log = Logger.getLogger(HabilitacioPublicService.class);

        public static final String TAG_NAME = "Habilitacio";

        @EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
        protected FuncionariLogicaService funcionariLogicaEjb;

        @EJB(mappedName = HabilitacioLogicaService.JNDI_NAME)
        protected HabilitacioLogicaService habilitacioLogicaEjb;

        @Path("/byfuncionari")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        @Operation(tags = TAG_NAME, operationId = "byfuncionari", summary = "Habilitacions d'un funcionari/ària", method = "get")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                        @ApiResponse(responseCode = "200", description = "Habilitacions assignades al lloc de feina assignat d'un/una funcionari/ària", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LlistaHabilitacionsPojo.class))) })
        public LlistaHabilitacionsPojo getHabilitacions(

                        @Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
                                        @ExampleObject(name = "Català", value = "ca"),
                                        @ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
                        @Parameter(description = "Identificador del/la funcionari/ària (NIF/NIE)", required = false, example = "12345678Z", schema = @Schema(implementation = String.class)) @Pattern(regexp = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$") @QueryParam("identificador") String identificador,
                        @Parameter(description = "Usuari del/la funcionari/ària (p.ex. u12345)", required = false, example = "u12345", schema = @Schema(implementation = String.class)) @QueryParam("usuari") String usuari)
                        // @Parameter(description = "Usuari del funcionari (p.ex. u12345)", required =
                        // false, example = "u12345", schema = @Schema(implementation = String.class))
                        // @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$") @QueryParam("usuari") String
                        // usuari)
                        throws RestException {

                try {
                        if ((identificador == null || identificador.isBlank())
                                        && (usuari == null || usuari.isBlank())) {
                                throw new RestException("Cal indicar o bé 'identificador' o bé 'usuari'");
                        }

                        StringBuilder sb = new StringBuilder();
                        sb.append("Llengua: " + language + "\n");
                        sb.append("Identificador: " + identificador + "\n");
                        sb.append("Usuari: " + usuari + "\n");
                        log.info(sb.toString());

                        LlistaHabilitacionsPojo habilitacions = new LlistaHabilitacionsPojo(
                                        new java.util.ArrayList<HabilitacioJPA>(), language);

                        try {
                                FuncionariJPA funcionari;
                                if (identificador != null && !identificador.isBlank()) {
                                        funcionari = funcionariLogicaEjb.comprovarFuncionariActiuByNif(language,
                                                        identificador, true);
                                } else {
                                        funcionari = funcionariLogicaEjb.comprovarFuncionariActiuByUsuari(language,
                                                        usuari, true);
                                }
                                List<HabilitacioJPA> habilitacionsTrobades = habilitacioLogicaEjb
                                                .findByFuncionari(funcionari);
                                if (habilitacionsTrobades != null) {
                                        habilitacions.setHabilitacionsFromJpa(habilitacionsTrobades);
                                }

                        } catch (I18NException re) {
                                log.error(re.getMessage(), re);
                                habilitacions.setMissatgeError(re.getMessage());
                        }

                        return habilitacions;

                } catch (Throwable th) {

                        String msg;
                        if (th instanceof I18NException) {
                                I18NException ie = (I18NException) th;
                                msg = I18NCommonUtils.getMessage(ie, new Locale(language));
                        } else {
                                msg = th.getMessage();
                        }

                        log.error("Error cridada api rest 'habilitacio': " + msg, th);

                        throw new RestException(msg);

                }
        }

}
