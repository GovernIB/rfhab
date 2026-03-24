package es.caib.rfhab.api.interna.secure.habilitacio;

import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.api.interna.utils.I18NLogicUtilsApiInterna;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.logic.HabilitacioLogicaService;
import es.caib.rfhab.model.entity.Habilitacio;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Path("/secure/habilitacio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = HabilitacioRestService.SECURITY_NAME, scheme = "basic")
public class HabilitacioRestService extends RestUtils {

    protected Logger log = Logger.getLogger(HabilitacioRestService.class);

    protected static final String SECURITY_NAME = "BasicAuth";

    protected ObjectMapper mapper = new ObjectMapper();

    @EJB(mappedName = HabilitacioLogicaService.JNDI_NAME)
    protected HabilitacioLogicaService habilitacioLogicaEjb;

    public static class ConsultaHabilitacioResponse {
        private boolean existeix;
        private Long habilitacioId;

        public boolean isExisteix() {
            return existeix;
        }

        public void setExisteix(boolean existeix) {
            this.existeix = existeix;
        }

        public Long getHabilitacioId() {
            return habilitacioId;
        }

        public void setHabilitacioId(Long habilitacioId) {
            this.habilitacioId = habilitacioId;
        }
    }

    @Path("/consulta")
    @POST
    @Hidden
    @RolesAllowed({ Constants.RFH_WS })
    @SecurityRequirement(name = HabilitacioRestService.SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String consultaPerCodi(
            @Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
                    @ExampleObject(name = "Català", value = "ca"),
                    @ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
            @Parameter(description = "Codi habilitació", required = true) @QueryParam("codi") @NotNull String codiHabilitacio) {
        try {
            if (language == null || language.isEmpty()) {
                language = "ca";
            }
            ConsultaHabilitacioResponse response = new ConsultaHabilitacioResponse();
            Habilitacio habilitacio = habilitacioLogicaEjb.findByCodi(codiHabilitacio);
            if (habilitacio != null) {
                response.setExisteix(true);
                response.setHabilitacioId(Long.valueOf(habilitacio.getHabilitacioID()));
            } else {
                response.setExisteix(false);
            }
            return mapper.writeValueAsString(response);
        } catch (Throwable th) {
            String msg = I18NLogicUtilsApiInterna.tradueix(new Locale(language), "habilitacio.error.desconegut",
                    new String[] { th.getMessage() });
            log.error(msg, th);
            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }
    }
}
