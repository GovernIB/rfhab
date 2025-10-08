package es.caib.rfhab.api.interna.secure.funcionari;

import es.caib.rfhab.api.interna.utils.I18NLogicUtilsApiInterna;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.IdentificacioTipus;
import es.caib.rfhab.commons.utils.IdentificacioTipusValues;
import es.caib.rfhab.commons.utils.StringUtils;
import es.caib.rfhab.ejb.ActivitatService;
import es.caib.rfhab.ejb.EntitatService;
import es.caib.rfhab.ejb.FuncionariLlocService;
import es.caib.rfhab.ejb.LlocRolService;
import es.caib.rfhab.ejb.LlocService;
import es.caib.rfhab.ejb.RolService;
import es.caib.rfhab.ejb.UnitatService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.UsuariLogicaService;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.Rol;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.FuncionariLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.LlocRolFields;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.model.fields.UnitatFields;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.validator.FuncionariValidator;
import es.caib.rfhab.pluginsib.rolsac.RolsacPlugin;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import io.swagger.v3.oas.annotations.media.ExampleObject;

/**
 *
 * @author jagarcia
 * @author jpou
 *
 */
@Path("/secure/funcionari")
@OpenAPIDefinition(tags = @Tag(name = FuncionariRestService.TAG_NAME, description = "Registrar l'activitat d'un funcionari"), info = @Info(title = "API REST INTERNA de RFHab", description = "Serveis REST de RFHab per ser accedits emprant autenticació", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "https://governdigital.fundaciobit.org")), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplesecure-v1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = FuncionariRestService.SECURITY_NAME, scheme = "basic")
public class FuncionariRestService extends RestUtils {

	private static final String CORREU_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

	protected Logger log = Logger.getLogger(FuncionariRestService.class);

	@EJB(mappedName = ActivitatService.JNDI_NAME)
	protected ActivitatService activitatEjb;

	@EJB(mappedName = UsuariLogicaService.JNDI_NAME)
	protected UsuariLogicaService usuariLogicaEjb;

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

	protected FuncionariValidator<Funcionari> validator = new FuncionariValidator<Funcionari>();

	protected RolsacPlugin rolsacPlugin = null;

	protected static final String TAG_NAME = "FuncionariRestService";

	protected static final String SECURITY_NAME = "BasicAuth";

	protected ObjectMapper mapper = new ObjectMapper();

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
			@Parameter(description = "Codi de la entitat", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @QueryParam("entitat") String entitat) {

		try {

			if (StringUtils.isEmpty(usuari)) {
				return "Error: cal indicar el nom del usuari del funcionari o funcionària";
			}

			if (StringUtils.isEmpty(entitat)) {
				return "Error: cal indicar el codi de l'entitat a la que pertany el funcionari o funcionària";
			}

			log.info("FuncionariHabilitat amb usuari: " + usuari + " i entitat " + entitat);

			// Obtenim el funcionari a partir del nom d'usuari del funcionari i la entitat
			Where condicioFuncionari = Where.AND(FuncionariFields.USUARI.equal(usuari),
					FuncionariFields.ENTITATID.equal(Long.parseLong(entitat)));
			List<Long> funcionari = funcionariEjb.executeQuery(FuncionariFields.FUNCIONARIID, condicioFuncionari);

			funcionari.forEach(f -> {
				log.info("Funcionari identificats: " + String.valueOf(f));
			});

			// Amb el funcionariID, cercam a la taula de FuncionariLloc, el llocID assignat
			// i actiu
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
			for (Rol rolItem : rols) {
				if (rol.equalsIgnoreCase(rolItem.getCodi())) {
					habilitat = true;
					break;
				}
			}

			if (habilitat) {
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

			if (StringUtils.isEmpty(usuari)) {
				return "Error: cal indicar el nom del usuari del funcionari o funcionària";
			}

			if (codiSia == null) {
				return "Error: cal indicar el codi SIA del tràmit";
			}

			if (entitat == null) {
				return "Error: cal indicar el codi de l'entitat a la que pertany el funcionari o funcionària";
			}

			// Cercam el funcionari per veure si existeix a la entitat indicada
			Where condicioFuncionari = Where.AND(FuncionariFields.USUARI.equal(usuari),
					FuncionariFields.ENTITATID.equal(Long.parseLong(entitat)));
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
			for (Lloc llocItem : llocsItems) {
				log.info("Lloc ocupat: " + llocItem.getCodiLloc() + " - Personal OAMR: " + llocItem.getPersonalOamr()
						+ " - Unitat: " + llocItem.getUnitatID());
				isOamr = (llocItem.getPersonalOamr() > 1);
			}

			// TODO si retorna més d'un lloc, pensar si retornar ERROR

			if (isOamr) {
				return mapper.writeValueAsString("SI");
			} else {

				// Si no es personalOamr, ens conectam a ROLSAC i revisam que existeix el
				// codiSia Indicat

				List<String> codiDir3 = unitatEjb.executeQuery(UnitatFields.CODI,
						UnitatFields.UNITATID.equal(llocsItems.get(0).getUnitatID()));

				if (codiDir3.size() > 0) {

					if (rolsacPlugin == null) {
						rolsacPlugin = new RolsacPlugin();
					}

					Boolean autoritzat = false;
					HashMap<String, String> procediments = rolsacPlugin.obtenirProcedimentsByDir3(codiDir3.get(0));
					for (String item : procediments.keySet()) {
						log.info("Procediment " + item);
						if (codiSia.equalsIgnoreCase(item)) {
							autoritzat = true;
							break;
						}
					}

					if (autoritzat) {
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

			if (StringUtils.isEmpty(usuari)) {
				return "Error: cal indicar el número o el nom del usuari del funcionari o funcionària";
			}

			if (StringUtils.isEmpty(entitat)) {
				return "Error: cal indicar el codi de l'entitat";
			}

			log.info("FuncionariHabilitat amb usuari: " + usuari + " i entitat " + entitat);

			// Obtenim el funcionari a partir del nom d'usuari del funcionari i la entitat
			Where condicioFuncionari = Where.AND(FuncionariFields.USUARI.equal(usuari),
					FuncionariFields.ENTITATID.equal(Long.parseLong(entitat)));
			List<Long> funcionari = funcionariEjb.executeQuery(FuncionariFields.FUNCIONARIID, condicioFuncionari);

			funcionari.forEach(f -> {
				log.info("Funcionari identificats: " + String.valueOf(f));
			});

			// Amb el funcionariID, cercam a la taula de FuncionariLloc, el llocID assignat
			// i actiu
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

	/**
	 * Registra un/a funcionari/ària habilitat nou a RFHab
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant el registre
	 *                 d'un nou FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/nou")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariRestService.TAG_NAME }, operationId = "nouFuncionariHabilitat", summary = "Registra un/a funcionari/ària habilitat nou a RFHab")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operació realitzada correctament.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
			@ApiResponse(responseCode = "401", description = "No Autenticat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "403", description = "No Autoritzat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }), })
	public String nouFuncionariHabilitat(
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "Identificador de l'usuari que està realitzant el registre d'un nou FH", required = true, example = "9999", schema = @Schema(type = "int")) @NotNull @QueryParam("usuariid") Integer usuariId,
			@Parameter(description = "Nom del funcionari", required = true) @QueryParam("nom") @NotNull String nom,
			@Parameter(description = "Primer llinatge", required = true) @QueryParam("llinatge1") @NotNull String llinatge1,
			@Parameter(description = "Segon llinatge", required = false) @QueryParam("llinatge2") String llinatge2,
			@Parameter(description = "Número del funcionari. Si és buit, el programa ho rellenarà automàticamente amb la seqüència més alta + 1", required = false, example = Constants.FUNCIONARI_NUMERO_PLACEHOLDER, schema = @Schema(implementation = String.class, pattern = Constants.REGEX_FUNCIONARI_NUMERO_PATTERN)) @QueryParam("numero") String numero,
			@Parameter(description = "Tipus d'identificació del funcionari/ària:<br />&emsp;<i>"
					+ IdentificacioTipusValues.DESCRIPTION_ALL_VALUES
					+ "</i>", required = true, example = "", schema = @Schema(type = "IdentificacioTipus", description = IdentificacioTipusValues.DESCRIPTION_ALL_VALUES)) @NotNull @QueryParam("tipusIdentificador") IdentificacioTipus tipusIdentificador,
			@Parameter(description = "Identificador", required = true) @NotNull @QueryParam("identificador") String identificador,
			@Parameter(description = "Usuari", required = true, schema = @Schema(implementation = String.class)) @QueryParam("username") @NotNull String username,
			@Parameter(description = "Correu electrònic", required = true, schema = @Schema(implementation = String.class, pattern = CORREU_PATTERN)) @QueryParam("correu") @NotNull String correu,
			@Parameter(description = "EntitatID", required = true, example = "1000") @QueryParam("entitatId") @NotNull Long entitatId,
			@Parameter(description = "Número CAI", required = false) @QueryParam("numerocai") String numeroCai,
			@Parameter(description = "Observacions", required = false) @QueryParam("observacions") String observacions,
			@Parameter(description = "Data de baixa", required = false, example = "2025-08-31T06:15:00+00:00", schema = @Schema(implementation = String.class, pattern = DATE_PATTERN_ISO8601_DATE_AND_TIME)) @QueryParam("databaixa") String dataBaixaStr) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari actuant: " + usuariId + "\n");
			sb.append("Nom: " + nom + "\n");
			sb.append("Llinatge1: " + llinatge1 + "\n");
			sb.append("Llinatge2: " + llinatge2 + "\n");
			sb.append("Número FH: " + numero + "\n");
			sb.append("TipusIdentificador: " + tipusIdentificador.getValue() + "\n");
			sb.append("Identificador: " + identificador + "\n");
			sb.append("Usuari: " + username + "\n");
			sb.append("Correu: " + correu + "\n");
			sb.append("EntitatId: " + entitatId + "\n");
			sb.append("NumeroCai: " + numeroCai + "\n");
			sb.append("Observacions: " + observacions + "\n");
			sb.append("DataBaixa: " + dataBaixaStr + "\n");

			log.info(sb.toString());

			Timestamp dataBaixa = null;
			if (dataBaixaStr != null && !dataBaixaStr.isEmpty()) {
				dataBaixa = new Timestamp(
						parseDateTimeISO8601ToDate(dataBaixaStr, "data", language).getTime());
			}

			Timestamp dataCreacio = new Timestamp(System.currentTimeMillis());

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariEjb.comprovarFuncionariActiuByNif(language, usuariNif, true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			if (numero == null || numero.isEmpty()) {
				numero = funcionariEjb.getNouFuncionariNumero();
				log.info("XYZ YYY numero = " + numero);
			}

			Funcionari funcionariNou = new FuncionariJPA();
			funcionariNou.setNom(nom);
			funcionariNou.setLlinatge1(llinatge1);
			funcionariNou.setLlinatge2(llinatge2);
			funcionariNou.setTipusIdentificador(tipusIdentificador.getValue());
			funcionariNou.setIdentificador(identificador);
			funcionariNou.setCorreu(correu);
			funcionariNou.setNumero(numero);
			funcionariNou.setUsuari(username);
			funcionariNou.setEntitatID(entitatId);
			funcionariNou.setDataCreacio(dataCreacio);
			funcionariNou.setDataBaixa(dataBaixa);
			funcionariNou.setObservacions(observacions);

			// validam funcionari
			Funcionari funcionariCreat;
			BeanValidatorResult<Funcionari> __vr = new BeanValidatorResult<Funcionari>();
			validator.validate(__vr, funcionariNou, true, funcionariEjb);

			if (__vr.hasErrors()) {
				List<I18NFieldError> vrErrors = __vr.getErrors();
				List<String> errorsMsg = new ArrayList<String>();
				// errorsMsg.add(I18NUtils.tradueix("error.form"));
				for (I18NFieldError i18nFieldError : vrErrors) {
					// errorsMsg.add(I18NUtils.tradueix("error.creation",
					// i18nFieldError.getTranslation().getCode(),
					// I18NUtils.tradueixArguments(i18nFieldError.getTranslation().getArgs())));

					// String[] argumentsTraduits =
					// I18NUtils.tradueixArguments(i18nFieldError.getTranslation().getArgs());
					// errorsMsg.add(I18NUtils.tradueix(i18nFieldError.getTranslation().getCode(),
					// argumentsTraduits));
					errorsMsg.add(
							I18NLogicUtilsApiInterna.tradueix(new Locale(language),
									i18nFieldError.getTranslation().getCode(),
									Arrays.stream(i18nFieldError.getTranslation().getArgs()).map(arg -> arg.getValue())
											.toArray(size -> new String[size])));
				}
				String msg = errorsMsg.toString();
				log.error(msg);
				throw new I18NException(msg);
			} else {
				// Cream funcionari i auditoria
				funcionariCreat = funcionariEjb.createAndHistory(funcionariNou, numeroCai, usuariId.longValue());
			}

			String successMsg = String.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
					"success.creation",
					new String[] { I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionari.funcionari"),
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionari.funcionariID"),
							String.valueOf(funcionariCreat.getFuncionariID()),
							"" }));
			log.info(successMsg);

			return I18NCommonUtils.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NCommonUtils.tradueix(new Locale(language), "funcionari.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Donar d'alta un/a funcionari/ària habilitat que previament ha estat donat de
	 * baixa
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant l'acció
	 *                 de donar d'alta el FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/donaralta")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariRestService.TAG_NAME }, operationId = "donarAltaFuncionariHabilitat", summary = "Dona d'alta un/a funcionari/ària habilitat a RFHab")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operació realitzada correctament.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
			@ApiResponse(responseCode = "401", description = "No Autenticat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "403", description = "No Autoritzat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }), })
	public String donarAlta(
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "Identificador de l'usuari que està realitzant el registre d'un nou FH", required = true, example = "9999", schema = @Schema(type = "int")) @NotNull @QueryParam("usuariid") Integer usuariId,
			@Parameter(description = "Identificador (NIF, NIE o passaport)", required = true) @NotNull @QueryParam("identificador") String identificador,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = "", implementation = String.class)) @QueryParam("numerocai") String numeroCai) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("Funcionari identificador: " + identificador + "\n");
			sb.append("Número CAI: " + numeroCai + "\n");
			log.info(sb.toString());
			if (numeroCai == null) {
				numeroCai = "";
				log.info("XYZ YYY numeroCai = " + numeroCai);
			}

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariEjb.comprovarFuncionariActiuByNif(language, usuariNif, true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			Funcionari funcionariAdonarDeAlta = funcionariEjb.findByNif(identificador);
			if (funcionariAdonarDeAlta == null) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"funcionari.error.noexisteixnif",
						new String[] { identificador }));
			}
			long funcionariId = funcionariAdonarDeAlta.getFuncionariID();
			funcionariEjb.donarDeAltaAndHistory(funcionariId, numeroCai, usuariId);

			String successMsg = String.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
					"success.modification",
					new String[] { I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionari.funcionari"),
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionari.funcionariID"),
							String.valueOf(funcionariId),
							"" }));
			log.info(successMsg);

			return I18NCommonUtils.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NCommonUtils.tradueix(new Locale(language), "funcionari.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Donar de baixa un/a funcionari/ària habilitat
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant l'acció
	 *                 de donar d'alta el FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/donarbaixa")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariRestService.TAG_NAME }, operationId = "donarBaixaFuncionariHabilitat", summary = "Dóna de baixa un/a funcionari/ària habilitat a RFHab")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operació realitzada correctament.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
			@ApiResponse(responseCode = "401", description = "No Autenticat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "403", description = "No Autoritzat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "Error no controlat", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }), })
	public String donarBaixa(
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "Identificador de l'usuari que està realitzant el registre d'un nou FH", required = true, example = "9999", schema = @Schema(type = "int")) @NotNull @QueryParam("usuariid") Integer usuariId,
			@Parameter(description = "Identificador (NIF, NIE o passaport)", required = true) @NotNull @QueryParam("identificador") String identificador,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = "", implementation = String.class)) @QueryParam("numerocai") String numeroCai) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("Funcionari identificador: " + identificador + "\n");
			sb.append("Número CAI: " + numeroCai + "\n");
			log.info(sb.toString());

			if (numeroCai == null) {
				numeroCai = "";
				log.info("XYZ YYY numeroCai = " + numeroCai);
			}

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariEjb.comprovarFuncionariActiuByNif(language, usuariNif, true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			Funcionari funcionariAdonarDeBaixa = funcionariEjb.findByNif(identificador);
			if (funcionariAdonarDeBaixa == null) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"funcionari.error.noexisteixnif",
						new String[] { identificador }));
			}

			funcionariEjb.donarDeBaixaFuncionariAndHistory(funcionariAdonarDeBaixa, numeroCai, usuariId);

			String successMsg = String.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
					"success.modification",
					new String[] {
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "success.modification"),
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionari.funcionari",
									"funcionari.identificador"),
							identificador }));

			log.info(successMsg);

			return I18NCommonUtils.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NCommonUtils.tradueix(new Locale(language), "funcionari.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}
}
