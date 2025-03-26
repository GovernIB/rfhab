package es.caib.rfhab.api.interna.secure.funcionari;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.Utils;
import es.caib.rfhab.ejb.ActivitatService;
import es.caib.rfhab.ejb.EntitatService;
import es.caib.rfhab.ejb.FuncionariLlocService;
import es.caib.rfhab.ejb.LlocRolService;
import es.caib.rfhab.ejb.LlocService;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.ejb.UnitatService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocRolFields;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.model.fields.UnitatFields;
import es.caib.rfhab.pluginsib.rolsac.RolsacPlugin;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

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
@Path("/secure/funcionari")
@OpenAPIDefinition(tags = @Tag(name = FuncionariRestService.TAG_NAME, description = "Registrar l'activitat d'un funcionari"), info = @Info(title = "API REST INTERNA de RFHab", description = "Serveis REST de RFHab per ser accedits emprant autenticació", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "https://governdigital.fundaciobit.org")), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplesecure-v1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = FuncionariRestService.SECURITY_NAME, scheme = "basic")
public class FuncionariRestService extends RestUtils {

	protected Logger log = Logger.getLogger(FuncionariRestService.class);

	@EJB(mappedName = ActivitatService.JNDI_NAME)
	protected ActivitatService activitatEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariEjb;

	@EJB(mappedName = FuncionariLlocService.JNDI_NAME)
	protected FuncionariLlocService funcionariLlocEjb;

	@EJB(mappedName = LlocService.JNDI_NAME)
	protected LlocService llocEjb;

	@EJB(mappedName = LlocRolService.JNDI_NAME)
	protected LlocRolService llocRolEjb;

	@EJB(mappedName = RolService.JNDI_NAME)
	protected RolService rolEjb;

	@EJB(mappedName = EntitatService.JNDI_NAME)
	protected EntitatService entitatEjb;

	@EJB(mappedName = UnitatService.JNDI_NAME)
	protected UnitatService unitatEjb;

	protected RolsacPlugin rolsacPlugin = null;

	protected static final String TAG_NAME = "FuncionariRestService";

	protected static final String SECURITY_NAME = "BasicAuth";

	protected ObjectMapper mapper = new ObjectMapper();

	public static final int TIPUS_COPIA = 1;
	public static final int TIPUS_TRAMIT = 2;
	public static final int TIPUS_COMPAREIX = 3;


	@Path("/habilitat")
	@GET
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariRestService.TAG_NAME }, operationId = "ObtenirRolsFuncionari", summary = "Obtenir els rols associats a un funcionari")
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
	public String obtenirRolsFuncionari(

			@Parameter(description = "Usuari del funcionari", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("usuari") String usuari,
			@Parameter(description = "Habilitació", required = true, example = "COPIA", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("rol") String rol,
			@Parameter(description = "Codi de la entitat", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("entitat") String entitat
	) {

		try {

			if (Utils.isEmpty(usuari)) {
				return "Error: cal indicar el nom del usuari del funcionari o funcionària";
			}

			if (Utils.isEmpty(entitat)) {
				return "Error: cal indicar el codi de l'entitat a la que pertany el funcionari o funcionària";
			}

			log.info("FuncionariHabilitat amb usuari: " + usuari + " i entitat " + entitat);

			// Obtenim el funcionari a partir del nom d'usuari del funcionari i la entitat
			Where condicioFuncionari = Where.AND(FuncionariFields.USUARI.equal(usuari), FuncionariFields.ENTITATID.equal(Long.parseLong(entitat)));
			List<Long> funcionari = funcionariEjb.executeQuery(FuncionariFields.FUNCIONARIID, condicioFuncionari);

			funcionari.forEach(f -> {
				log.info("Funcionari identificats: " + String.valueOf(f));
			});

			// Amb el funcionariID, cercam a la taula de FuncionariLloc, el llocID assignat i actiu
			Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.isNull());

			Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());

			Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			Where wV = Where.OR(w1, w2, w3, w4);

			Where w = Where.AND(wV, FuncionariLlocFields.FUNCIONARIID.in(funcionari));

			List<Long> llocsOcupats = funcionariLlocEjb.executeQuery(FuncionariLlocFields.LLOCID, w);

			llocsOcupats.forEach(lloc -> {
				log.info("Lloc ocupat: " + String.valueOf(lloc));
			});
			
			// Retornam la llista de Rols assignats al Lloc actiu assignat al funcionari
			List<Long> rolsLloc = llocRolEjb.executeQuery(LlocRolFields.ROLID, LlocRolFields.LLOCID.in(llocsOcupats));	

			rolsLloc.forEach(rolLlocItem -> {
				log.info("RolLlocItem: " + rolLlocItem);
			});

			List<Rol> rols = rolEjb.select(RolFields.ROLID.in(rolsLloc));

			Boolean habilitat = false;
			for(Rol rolItem : rols){
				if (rol.equalsIgnoreCase(rolItem.getCodi())){
					habilitat = true;
					break;
				}
			}

			if (habilitat){
				return mapper.writeValueAsString("SI");
			}

			return mapper.writeValueAsString("NO");

		} catch (Exception e) {
			log.error("Error consultant els rols d'un funcionari: " + e.getMessage());
			return "Error";
		}

	}

	@Path("/autoritzat")
	@GET
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariRestService.TAG_NAME }, operationId = "Comprobar Autorització", summary = "Comprobar si un funcionari té permís per realitzar un trámit")
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
	public String isFuncionariAutoritzat(

			@Parameter(description = "Usuari del funcionari habilitat", required = true, example = "u12345", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("usuari") String usuari,
			@Parameter(description = "Codi SIA", required = true, example = "132313", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("codisia") String codiSia,
			@Parameter(description = "Codi de la entitat", required = true, example = "CAIB", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("entitat") String entitat

	) {

		try {

			if (Utils.isEmpty(usuari)) {
				return "Error: cal indicar el nom del usuari del funcionari o funcionària";
			}

			if (codiSia == null) {
				return "Error: cal indicar el codi SIA del tràmit";
			}

			if (entitat == null) {
				return "Error: cal indicar el codi de l'entitat a la que pertany el funcionari o funcionària";
			}

			// Cercam el funcionari per veure si existeix a la entitat indicada
			Where condicioFuncionari = Where.AND(FuncionariFields.USUARI.equal(usuari), FuncionariFields.ENTITATID.equal(Long.parseLong(entitat)));
			List<Long> funcionari = funcionariEjb.executeQuery(FuncionariFields.FUNCIONARIID, condicioFuncionari);

			funcionari.forEach(f -> {
				log.info("Funcionari identificats: " + String.valueOf(f));
			});

			// Obtenim la informació del lloc que ocupa actiu
			Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.isNull());

			Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());

			Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			Where wV = Where.OR(w1, w2, w3, w4);

			Where w = Where.AND(wV, FuncionariLlocFields.FUNCIONARIID.in(funcionari));

			List<Long> llocsOcupats = funcionariLlocEjb.executeQuery(FuncionariLlocFields.LLOCID, w);

			llocsOcupats.forEach(lloc -> {
				log.info("Lloc ocupat: " + String.valueOf(lloc));
			});


			// Si es personalOamr retornam TRUE
			List<Lloc> llocsItems = llocEjb.select(LlocFields.LLOCID.in(llocsOcupats));
			Boolean isOamr = false;
			for (Lloc llocItem : llocsItems){
				log.info("Lloc ocupat: " + llocItem.getCodiLloc() + " - Personal OAMR: "  + llocItem.getPersonalOamr() + " - Unitat: " + llocItem.getUnitatID());
				isOamr = (llocItem.getPersonalOamr() > 0);
			}

			// TODO si retorna més d'un lloc, pensar si retornar ERROR
			
			if (isOamr){
				return mapper.writeValueAsString("SI");
			} else {

				// Si no es personalOamr, ens conectam a ROLSAC i revisam que existeix el codiSia Indicat

				List<String> codiDir3 = unitatEjb.executeQuery(UnitatFields.CODI, UnitatFields.UNITATID.equal(llocsItems.get(0).getUnitatID()));

				if (codiDir3.size() > 0){

					if (rolsacPlugin == null){
						rolsacPlugin = new RolsacPlugin();
					}

					Boolean autoritzat = false;
					HashMap<String, String> procediments = rolsacPlugin.obtenirProcedimentsByDir3(codiDir3.get(0));
					for (String item : procediments.keySet()){
						log.info("Procediment " + item);
						if (codiSia.equalsIgnoreCase(item)){
							autoritzat = true;
							break;
						}
					}
					
					if (autoritzat){
						return mapper.writeValueAsString("SI"); 
					} 
					
				}
			}

			return mapper.writeValueAsString("NO");
			
		} catch (Exception e) {
			log.error("Error comprovant si un funcionari està autoritzat: " + e.getMessage());
			return "Error";
		}
	}

	@Path("/habilitacions")
	@GET
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariRestService.TAG_NAME }, operationId = "Habilitacions", summary = "Consulta les habilitacions d'un funcionari habilitat")
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
	public String funcionariHabilitat(
			@Parameter(description = "Nom d'usuari del funcionari", required = true, example = "u12345", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("usuari") String usuari,
			@Parameter(description = "Codi de la entitat", required = true, example = "CAIB", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("entitat") String entitat

	) {

		try {

			if ( Utils.isEmpty(usuari)) {
				return "Error: cal indicar el número o el nom del usuari del funcionari o funcionària";
			}

			if (Utils.isEmpty(entitat)) {
				return "Error: cal indicar el codi de l'entitat";
			}

			log.info("FuncionariHabilitat amb usuari: " + usuari + " i entitat " + entitat);

			// Obtenim el funcionari a partir del nom d'usuari del funcionari i la entitat
			Where condicioFuncionari = Where.AND(FuncionariFields.USUARI.equal(usuari), FuncionariFields.ENTITATID.equal(Long.parseLong(entitat)));
			List<Long> funcionari = funcionariEjb.executeQuery(FuncionariFields.FUNCIONARIID, condicioFuncionari);

			funcionari.forEach(f -> {
				log.info("Funcionari identificats: " + String.valueOf(f));
			});

			// Amb el funcionariID, cercam a la taula de FuncionariLloc, el llocID assignat i actiu
			Where w1 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			Where w2 = Where.AND(FuncionariLlocFields.DATAINICI.lessThan(new Date(System.currentTimeMillis())),
				FuncionariLlocFields.DATAFI.isNull());

			Where w3 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(), FuncionariLlocFields.DATAFI.isNull());

			Where w4 = Where.AND(FuncionariLlocFields.DATAINICI.isNull(),
				FuncionariLlocFields.DATAFI.greaterThan(new Date(System.currentTimeMillis())));

			Where wV = Where.OR(w1, w2, w3, w4);

			Where w = Where.AND(wV, FuncionariLlocFields.FUNCIONARIID.in(funcionari));

			List<Long> llocsOcupats = funcionariLlocEjb.executeQuery(FuncionariLlocFields.LLOCID, w);

			llocsOcupats.forEach(lloc -> {
				log.info("Lloc ocupat: " + String.valueOf(lloc));
			});
			
			// Retornam la llista de Rols assignats al Lloc actiu assignat al funcionari
			List<Long> rolsLloc = llocRolEjb.executeQuery(LlocRolFields.ROLID, LlocRolFields.LLOCID.in(llocsOcupats));	

			rolsLloc.forEach(rolLlocItem -> {
				log.info("RolLlocItem: " + rolLlocItem);
			});

			List<Rol> rols = rolEjb.select(RolFields.ROLID.in(rolsLloc));
			
			String resposta = "[";
			for (Rol rolItem : rols) {
				resposta += rolItem.getCodi() + ",";
			}
			resposta = "]";

			return mapper.writeValueAsString(resposta);

		} catch (Exception e) {
			log.error("Error comprovant si un funcionari està habilitat: " + e.getMessage());
			return "Error";
		}
	}

}
