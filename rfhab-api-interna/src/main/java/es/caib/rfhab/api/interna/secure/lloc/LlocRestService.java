package es.caib.rfhab.api.interna.secure.lloc;

import es.caib.rfhab.api.interna.utils.I18NLogicUtilsApiInterna;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.PersonalOamrTipus;
import es.caib.rfhab.commons.utils.PersonalOamrTipusValues;
import es.caib.rfhab.ejb.ActivitatService;
import es.caib.rfhab.ejb.LlocRolService;
import es.caib.rfhab.logic.EntitatLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.logic.UsuariEntitatLogicaService;
import es.caib.rfhab.logic.UsuariLogicaService;
import es.caib.rfhab.model.entity.Entitat;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.model.entity.Unitat;
import es.caib.rfhab.model.entity.UsuariEntitat;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.LlocJPA;
import es.caib.rfhab.persistence.validator.LlocValidator;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

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
 * @author jpou
 *
 */
@Path("/secure/lloc")
@OpenAPIDefinition(tags = @Tag(name = LlocRestService.TAG_NAME, description = "Registrar i edita un lloc nou"), info = @Info(title = "API REST INTERNA de RFHab", description = "Serveis REST de RFHab per ser accedits emprant autenticació", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "https://governdigital.fundaciobit.org")), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplesecure-v1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = LlocRestService.SECURITY_NAME, scheme = "basic")
public class LlocRestService extends RestUtils {

	protected Logger log = Logger.getLogger(LlocRestService.class);

	@EJB(mappedName = ActivitatService.JNDI_NAME)
	protected ActivitatService activitatEjb;

	@EJB(mappedName = UsuariLogicaService.JNDI_NAME)
	protected UsuariLogicaService usuariLogicaEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariEjb;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocLogicaEjb;

	@EJB(mappedName = LlocRolService.JNDI_NAME)
	protected LlocRolService llocRolEjb;

	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicaEjb;

	@EJB(mappedName = UnitatLogicaService.JNDI_NAME)
	protected UnitatLogicaService unitatLogicaEjb;

	@EJB(mappedName = UsuariEntitatLogicaService.JNDI_NAME)
	protected UsuariEntitatLogicaService usuariEntitatLogicaEjb;

	protected LlocValidator<Lloc> validator = new LlocValidator<Lloc>();

	protected static final String TAG_NAME = "LlocRestService";

	protected static final String SECURITY_NAME = "BasicAuth";

	/**
	 * Registra un lloc nou a RFHab
	 * 
	 * @param language    Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId    Identificador de l'usuari que està realitzant el registre
	 *                    d'un nou FH. Obligatori
	 * @param donarDeAlta Si es true, el FH es dona d'alta automàticament a Rfhab.
	 *                    Opcional (per defecte false)
	 * 
	 * @return
	 */

	@Path("/nou")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = LlocRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			LlocRestService.TAG_NAME }, operationId = "nouLloc", summary = "Registra un lloc de feina nou a RFHab")
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
	public String nouLloc(
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "Identificador de l'usuari que està realitzant el registre d'un nou FH", required = true, example = "9999", schema = @Schema(type = "int")) @NotNull @QueryParam("usuariid") Integer usuariId,
			@Parameter(description = "Codi del lloc", required = true) @QueryParam("codilloc") @NotNull String codiLloc,
			@Parameter(description = "Expansió del lloc", required = false) @QueryParam("expansio") String expansio,
			@Parameter(description = "Nom del lloc", required = true) @QueryParam("nom") @NotNull String nom,
			@Parameter(description = "Personal OAMR:<br />&emsp;<i>"
					+ PersonalOamrTipusValues.DESCRIPTION_ALL_VALUES
					+ "</i>", required = true, example = "", schema = @Schema(type = "PersonalOamrTipus", description = PersonalOamrTipusValues.DESCRIPTION_ALL_VALUES)) @NotNull @QueryParam("personaloamr") PersonalOamrTipus personalOamr,
			@Parameter(description = "observacions", required = false) @QueryParam("observacions") String observacions,
			@Parameter(description = "Entitat a la qual pertany el lloc. Ha de ser una de les entitats associades a l'usuari", required = true, example = "1000") @QueryParam("entitatid") @NotNull Long entitatId,
			@Parameter(description = "Unitat orgànica a la qual pertany el lloc. Ha de pertànyer a l'entitat sel·leccionada", required = true, example = "2") @QueryParam("unitatid") @NotNull Long unitatId,
			@Parameter(description = "Habilitacions associades al lloc (IDs)", required = false, example = "", array = @ArraySchema(schema = @Schema(type = "int"))) @QueryParam("habilitacions") String[] habilitacions,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = Constants.NUMEROCAI_BUIT, implementation = String.class)) @QueryParam("numerocai") String numeroCai,
			@Parameter(description = "Data de alta", required = false, example = "2025-08-31T06:15:00+00:00", schema = @Schema(implementation = String.class, pattern = DATE_PATTERN_ISO8601_DATE_AND_TIME)) @QueryParam("dataalta") String dataAltaStr,
			@Parameter(description = "Data de baixa", required = false, example = "2025-08-31T06:15:00+00:00", schema = @Schema(implementation = String.class, pattern = DATE_PATTERN_ISO8601_DATE_AND_TIME)) @QueryParam("databaixa") String dataBaixaStr) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("CodiLloc: " + codiLloc + "\n");
			sb.append("Expansió: " + expansio + "\n");
			sb.append("Nom: " + nom + "\n");
			sb.append("PersonalOAMR: " + personalOamr.getValue() + "\n");
			sb.append("Observacions: " + observacions + "\n");
			sb.append("EntitatId: " + entitatId + "\n");
			sb.append("UnitatId: " + unitatId + "\n");
			sb.append("habilitacions: " + habilitacions + "\n");
			sb.append("NumeroCai: " + numeroCai + "\n");
			sb.append("DataAlta: " + dataAltaStr + "\n");
			sb.append("DataBaixa: " + dataBaixaStr + "\n");
			log.info(sb.toString());

			if (numeroCai == null) {
				numeroCai = Constants.NUMEROCAI_BUIT;
				log.info("nou valor numeroCai = " + numeroCai);
			}

			Timestamp dataBaixa = null;
			if (dataBaixaStr != null && !dataBaixaStr.isEmpty()) {
				dataBaixa = new Timestamp(
						parseDateTimeISO8601ToDate(dataBaixaStr, "data", language).getTime());
			}
			Timestamp dataAlta = null;
			if (dataBaixaStr != null && !dataAltaStr.isEmpty()) {
				dataAlta = new Timestamp(
						parseDateTimeISO8601ToDate(dataAltaStr, "data", language).getTime());
			}

			Timestamp dataCreacio = new Timestamp(System.currentTimeMillis());

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariEjb.comprovarFuncionariActiuByNif(language, usuariNif, true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			Lloc llocNou = new LlocJPA();
			llocNou.setCodiLloc(codiLloc);
			String nouLlocCodiPropi = llocLogicaEjb.getNouLlocCodiPropi(codiLloc, expansio);
			llocNou.setCodiLlocPropi(nouLlocCodiPropi);
			llocNou.setDataBaixa(dataBaixa);
			llocNou.setDataCreacio(dataCreacio);
			llocNou.setDataalta(dataAlta);

			// validam que l'usuari està associat a l'entitat
			List<UsuariEntitat> ueTrobats = usuariEntitatLogicaEjb.findAllByUsuariIdWithEntitatId(usuariId, entitatId);
			if (ueTrobats == null || ueTrobats.size() == 0) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"error.usuarientitat.noexisteix",
						new String[] { String.valueOf(usuariId), String.valueOf(entitatId) }));
			}
			// validam que te unitat
			Entitat entitat = entitatLogicaEjb.findByPrimaryKey(entitatId);
			if (entitat == null) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"error.entitat.noexisteixdinsmare",
						new String[] { String.valueOf(entitatId), String.valueOf(unitatId) }));
			}
			// validam que unitat penja de l'entitat
			Unitat unitatEntitat = unitatLogicaEjb.findUnitatMare(unitatId, entitat.getUnitatID());
			if (unitatEntitat == null) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"error.unitat.noexisteixdinsmare",
						new String[] { String.valueOf(unitatId), String.valueOf(entitatId) }));
			}
			llocNou.setEntitatID(entitatId);
			llocNou.setExpansio(expansio);
			llocNou.setNom(nom);
			llocNou.setObservacions(observacions);
			llocNou.setPersonalOamr(personalOamr.getValue());
			llocNou.setUnitatID(unitatId);

			// validam lloc
			Lloc llocCreat;
			BeanValidatorResult<Lloc> __vr = new BeanValidatorResult<Lloc>();
			validator.validate(__vr, llocNou, true, entitatLogicaEjb, llocLogicaEjb, unitatLogicaEjb);

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
				llocCreat = llocLogicaEjb.createAndHistory(llocNou, numeroCai, usuariId.longValue(), habilitacions);
				// if (dataAlta != null) {
				// if (dataBaixa == null) {
				// llocLogicaEjb.donarDeAltaAndHistory(llocCreat.getLlocID(), numeroCai,
				// usuariId);
				// } else {
				// if (dataBaixa.compareTo(dataAlta) > 0) {
				// llocLogicaEjb.donarDeBaixaLlocAndHistory(llocCreat.getLlocID(), numeroCai,
				// usuariId);
				// llocLogicaEjb.donarDeAltaAndHistory(llocCreat.getLlocID(), numeroCai,
				// usuariId);
				// } else {
				// llocLogicaEjb.donarDeAltaAndHistory(llocCreat.getLlocID(), numeroCai,
				// usuariId);
				// llocLogicaEjb.donarDeBaixaLlocAndHistory(llocCreat.getLlocID(), numeroCai,
				// usuariId);
				// }
				// }
				// } else {
				// if (dataBaixa != null) {
				// llocLogicaEjb.donarDeBaixaLlocAndHistory(llocCreat.getLlocID(), numeroCai,
				// usuariId);
				// }
				// }
			}

			String successMsg = String
					.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language), "success.creation",
							new String[] { I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.lloc"),
									I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.llocID"),
									String.valueOf(llocCreat.getLlocID()),
									"" }));
			log.info(successMsg);

			return I18NLogicUtilsApiInterna.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Dona d'alta un lloc
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant el registre
	 *                 d'un nou FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/donaralta")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = LlocRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			LlocRestService.TAG_NAME }, operationId = "nouLloc", summary = "Registra un lloc de feina nou a RFHab")
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
			@Parameter(description = "Codi del lloc", required = true) @QueryParam("codilloc") @NotNull String codiLloc,
			@Parameter(description = "Expansió del lloc", required = false) @QueryParam("expansio") String expansio,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = Constants.NUMEROCAI_BUIT, implementation = String.class)) @QueryParam("numerocai") String numeroCai) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("CodiLloc: " + codiLloc + "\n");
			sb.append("Expansió: " + expansio + "\n");
			sb.append("NumeroCai: " + numeroCai + "\n");
			log.info(sb.toString());

			if (numeroCai == null) {
				numeroCai = Constants.NUMEROCAI_BUIT;
				log.info("nou valor numeroCai = " + numeroCai);
			}

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariEjb.comprovarFuncionariActiuByNif(language, usuariNif, true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			// cercam el lloc a donar d'alta
			List<Lloc> llocsAdonarDalta = llocLogicaEjb.getLlocsByCodiIexpansio(codiLloc, expansio);
			if (llocsAdonarDalta == null || llocsAdonarDalta.size() == 0) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"error.lloc.noexisteixcodiiexpansio",
						new String[] { codiLloc, expansio }));
			}
			Lloc llocAdonarDalta = llocsAdonarDalta.get(0);
			// Cream funcionari i auditoria
			Lloc llocActualitzat = llocLogicaEjb.donarDeAltaAndHistory(llocAdonarDalta.getLlocID(), numeroCai,
					usuariId);

			String successMsg = String
					.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language), "success.modification",
							new String[] { I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.lloc"),
									I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.codiLloc"),
									llocActualitzat.getCodiLloc() }));
			log.info(successMsg);

			return I18NLogicUtilsApiInterna.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Dona de baixa un lloc
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant el registre
	 *                 d'un nou FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/donarbaixa")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = LlocRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			LlocRestService.TAG_NAME }, operationId = "nouLloc", summary = "Registra un lloc de feina nou a RFHab")
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
			@Parameter(description = "Codi del lloc", required = true) @QueryParam("codilloc") @NotNull String codiLloc,
			@Parameter(description = "Expansió del lloc", required = false) @QueryParam("expansio") String expansio,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = Constants.NUMEROCAI_BUIT, implementation = String.class)) @QueryParam("numerocai") String numeroCai) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("CodiLloc: " + codiLloc + "\n");
			sb.append("Expansió: " + expansio + "\n");
			sb.append("NumeroCai: " + numeroCai + "\n");
			log.info(sb.toString());

			if (numeroCai == null) {
				numeroCai = Constants.NUMEROCAI_BUIT;
				log.info("nou valor numeroCai = " + numeroCai);
			}

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariEjb.comprovarFuncionariActiuByNif(language, usuariNif, true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			// cercam el lloc a donar d'alta
			List<Lloc> llocsAdonarDeBaixa = llocLogicaEjb.getLlocsByCodiIexpansio(codiLloc, expansio);
			if (llocsAdonarDeBaixa == null || llocsAdonarDeBaixa.size() == 0) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"error.lloc.noexisteixcodiiexpansio",
						new String[] { codiLloc, expansio }));
			}
			Lloc llocAdonarDeBaixa = llocsAdonarDeBaixa.get(0);
			// Cream funcionari i auditoria
			llocLogicaEjb.donarDeBaixaLlocAndHistory(llocAdonarDeBaixa.getLlocID(), numeroCai, usuariId);

			String successMsg = String
					.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language), "success.modification",
							new String[] { I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.lloc"),
									I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.codiLloc"),
									codiLloc }));
			log.info(successMsg);

			return I18NLogicUtilsApiInterna.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}
}
