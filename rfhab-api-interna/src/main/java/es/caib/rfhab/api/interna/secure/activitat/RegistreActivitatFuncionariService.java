package es.caib.rfhab.api.interna.secure.activitat;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.ActivitatService;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.persistence.ActivitatJPA;

import java.sql.Timestamp;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;

/**
 *
 * @author anadal
 *
 */
@Path("/secure/activitat")
@OpenAPIDefinition(tags = @Tag(name = RegistreActivitatFuncionariService.TAG_NAME, description = "Registrar l'activitat d'una funcionari"), info = @Info(title = "API REST INTERNA de RFHab", description = "Serveis REST de RFHab per ser accedits emprant autenticació", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "https://governdigital.fundaciobit.org")), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplesecure-v1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = RegistreActivitatFuncionariService.SECURITY_NAME, scheme = "basic")
public class RegistreActivitatFuncionariService extends RestUtils {

	protected Logger log = Logger.getLogger(RegistreActivitatFuncionariService.class);

	@EJB(mappedName = ActivitatService.JNDI_NAME)
	protected ActivitatService activitatEjb;

	protected static final String TAG_NAME = "RegistreActivitatFuncionariService";

	protected static final String SECURITY_NAME = "BasicAuth";
	
	public static final int TIPUS_COPIA = 1;
	public static final int TIPUS_TRAMIT = 2;
	public static final int TIPUS_COMPAREIX = 3;
	
	
	/**
	 * Registra una activitat d'un funcionari
	 * 
	 * @param funcionari
	 * @param tipus
	 * @param registre
	 * @param tramit
	 * @param codiSia
	 * @param autoritzacio
	 * @return
	 */

	@Path("/registre")
	@GET
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = RegistreActivitatFuncionariService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			RegistreActivitatFuncionariService.TAG_NAME }, operationId = "registreActivitat", summary = "Registra una activiat d\'un funcionari")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operació realitzada correctament", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
			@ApiResponse(responseCode = "401", description = "No Autenticat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "403", description = "No Autoritzat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }), })
	public String registreActivitat(

			@Parameter(description = "Nom usuari del funcionari que realitza la activitat", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @NotNull @QueryParam("funcionari") String funcionari,

			@Parameter(description = "Tipus de l\'activitat", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @NotNull @QueryParam("tipus") String tipus,

			@Parameter(description = "Número de registre associat a l\'activitat", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @NotNull @QueryParam("registre") String registre,

			@Parameter(description = "Codi del tràmit associat a l\'activitat", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @NotNull @QueryParam("tramit") String tramit,

			@Parameter(description = "Codi SIA associat a l\'activitat", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @NotNull @QueryParam("codisia") String codiSia,

			@Parameter(description = "Codi de l\'autorització associat a l\'activitat", required = false, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @NotNull @QueryParam("autoritzacio") String autoritzacio) {

		try {
			/*
			 * http://localhost:8280/rfhabapi/interna/secure/activitat/registre?funcionari=
			 * Juan&tipus=1&registre=R0001&tramit=T001&codisia=XXX&autoritzacio=5
			 */

			StringBuilder sb = new StringBuilder();
			sb.append("Funcionari: " + funcionari + "\n");
			sb.append("Registre activitat: " + registre + "\n");
			sb.append("Tipus activitat: " + tipus + "\n");
			sb.append("Tramit: " + tramit + "\n");
			sb.append("Codi SIA: " + codiSia + "\n");
			sb.append("Autoritzacio: " + autoritzacio + "\n");

			log.info(sb.toString());

			// validar codi de funcionari
			Long funcionariId = 1L;

			// Validar tipus d'activitat
			int tipusId = 0;
			switch (tipus.toUpperCase()) {
			case "COPIA":
				tipusId = TIPUS_COPIA;
				break;
			case "TRAMIT":
				tipusId = TIPUS_TRAMIT;
				break;
			case "COMPAREIX":
				tipusId = TIPUS_COMPAREIX;
				break;
			}

			// Validar Autoritzacio
			Long autoritzacioId = 1L;

			ActivitatJPA act = new ActivitatJPA();
			act.setTipus(tipusId);
			act.setAutoritzacioID(autoritzacioId);
			act.setCodiSia(codiSia);
			act.setRegistre(registre);
			act.setFuncionariID(funcionariId);
			act.setDataCreacio(new Timestamp(System.currentTimeMillis()));

			// Enregistrar activitat
			Activitat newAct = activitatEjb.create(act);

			return String.valueOf(newAct.getActivitatID());
		} catch (Exception e) {
			log.error("Error enregistrament activitat: " + e.getMessage());
			return "Error";
		}

	}
}