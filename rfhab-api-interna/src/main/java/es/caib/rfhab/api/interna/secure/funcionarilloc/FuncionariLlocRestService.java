package es.caib.rfhab.api.interna.secure.funcionarilloc;

import es.caib.rfhab.api.interna.utils.I18NLogicUtilsApiInterna;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.logic.FuncionariLlocLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.LlocLogicaService;
import es.caib.rfhab.logic.UsuariLogicaService;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.FuncionariLloc;
import es.caib.rfhab.model.entity.Lloc;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.FuncionariLlocJPA;
import es.caib.rfhab.persistence.validator.FuncionariLlocValidator;

import java.sql.Date;
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
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

/**
 *
 * @author jpou
 *
 */
@Path("/secure/funcionarilloc")
@OpenAPIDefinition(tags = @Tag(name = FuncionariLlocRestService.TAG_NAME, description = "Controla les assignacions desl/de les funcionaris/es habilitats/des a llocs de feina"), info = @Info(title = "API REST INTERNA de RFHab", description = "Serveis REST de RFHab per ser accedits emprant autenticació", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "https://governdigital.fundaciobit.org")), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplesecure-v1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = FuncionariLlocRestService.SECURITY_NAME, scheme = "basic")
public class FuncionariLlocRestService extends RestUtils {

	protected Logger log = Logger.getLogger(FuncionariLlocRestService.class);

	@EJB(mappedName = UsuariLogicaService.JNDI_NAME)
	protected UsuariLogicaService usuariLogicaEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariLogicaEjb;

	@EJB(mappedName = LlocLogicaService.JNDI_NAME)
	protected LlocLogicaService llocLogicaEjb;

	@EJB(mappedName = FuncionariLlocLogicaService.JNDI_NAME)
	protected FuncionariLlocLogicaService funcionariLlocLogicaEjb;

	protected FuncionariLlocValidator<FuncionariLloc> validator = new FuncionariLlocValidator<FuncionariLloc>();

	protected static final String TAG_NAME = "FuncionariLlocRestService";

	protected static final String SECURITY_NAME = "BasicAuth";

	/**
	 * Assigna un/a funcionari/ària a un lloc de feina
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant el registre
	 *                 d'un nou FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/assignarfuncionari")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariLlocRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariLlocRestService.TAG_NAME }, operationId = "nouLloc", summary = "Registra un lloc de feina nou a RFHab")
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
	public String assignarFuncionari(
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "Identificador de l'usuari que està realitzant el registre d'un nou FH", required = true, example = "9999", schema = @Schema(type = "int")) @NotNull @QueryParam("usuariid") Integer usuariId,
			@Parameter(description = "Codi del lloc", required = true) @QueryParam("codilloc") @NotNull String codiLloc,
			@Parameter(description = "Expansió del lloc", required = false) @QueryParam("expansio") String expansio,
			@Parameter(description = "Identificador del funcionari a assignar", required = true) @QueryParam("identificadorfh") @NotNull String identificadorFh,
			@Parameter(description = "Data d'inici'", required = false, example = "2025-08-31T06:15:00+00:00", schema = @Schema(implementation = String.class, pattern = DATE_PATTERN_ISO8601_DATE_AND_TIME)) @QueryParam("datainici") String dataIniciStr,
			@Parameter(description = "Data de fi", required = false, example = "2025-08-31T06:15:00+00:00", schema = @Schema(implementation = String.class, pattern = DATE_PATTERN_ISO8601_DATE_AND_TIME)) @QueryParam("datafi") String dataFiStr,
			@Parameter(description = "Observacions", required = false) @QueryParam("observacions") String observacions,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = Constants.NUMEROCAI_BUIT, implementation = String.class)) @QueryParam("numerocai") String numeroCai) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("CodiLloc: " + codiLloc + "\n");
			sb.append("Expansió: " + expansio + "\n");
			sb.append("Identificador FH: " + identificadorFh + "\n");
			sb.append("DataInici: " + dataIniciStr + "\n");
			sb.append("DataFi: " + dataFiStr + "\n");
			sb.append("Observacions: " + observacions + "\n");
			sb.append("NumeroCai: " + numeroCai + "\n");
			log.info(sb.toString());

			if (numeroCai == null) {
				numeroCai = Constants.NUMEROCAI_BUIT;
				log.info("XYZ YYY numeroCai = " + numeroCai);
			}

			Date dataInici = null;
			if (dataIniciStr != null && !dataIniciStr.isEmpty()) {
				dataInici = new Date(parseDateTimeISO8601ToDate(dataIniciStr, "data", language).getTime());
			}
			Date dataFi = null;
			if (dataFiStr != null && !dataFiStr.isEmpty()) {
				dataFi = new Date(parseDateTimeISO8601ToDate(dataFiStr, "data", language).getTime());
			}
			Timestamp dataCreacio = new Timestamp(System.currentTimeMillis());

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariLogicaEjb.comprovarFuncionariActiuByNif(language, usuariNif,
					true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			Funcionari funcionariAassignar = funcionariLogicaEjb.findByNif(identificadorFh);
			if (funcionariAassignar == null) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"funcionari.error.noexisteixnif",
						new String[] { identificadorFh }));
			}
			log.info("XYZ YYY funcionariAassignar = " + funcionariAassignar.getNumero());

			// cercam el lloc a donar d'alta
			List<Lloc> llocsAassignar = llocLogicaEjb.getLlocsByCodiIexpansio(codiLloc, expansio);
			if (llocsAassignar == null || llocsAassignar.size() == 0) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"error.lloc.noexisteixcodiiexpansio",
						new String[] { codiLloc, expansio }));
			}
			Lloc llocAassignar = llocsAassignar.get(0);

			FuncionariLloc funcionariLloc = new FuncionariLlocJPA();
			funcionariLloc.setDataCreacio(dataCreacio);
			funcionariLloc.setFuncionariID(funcionariAassignar.getFuncionariID());
			funcionariLloc.setLlocID(llocAassignar.getLlocID());
			funcionariLloc.setUsuariID(usuariId.longValue());
			funcionariLloc.setDataInici(dataInici);
			funcionariLloc.setDataFi(dataFi);

			// validam entitat
			FuncionariLloc fLlocCreat;
			BeanValidatorResult<FuncionariLloc> __vr = new BeanValidatorResult<FuncionariLloc>();
			validator.validate(__vr, funcionariLloc, true, funcionariLogicaEjb, funcionariLlocLogicaEjb, llocLogicaEjb,
					usuariLogicaEjb);

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
				// Cream funcionariLloc i auditoria
				fLlocCreat = funcionariLlocLogicaEjb.assignarFuncionari(funcionariLloc, numeroCai, observacions,
						usuariId.longValue());
			}

			String successMsg = String.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
					"success.creation",
					new String[] {
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionariLloc.funcionariLloc"),
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionariLloc.funcionarillocID"),
							String.valueOf(fLlocCreat.getFuncionarillocID()),
							"" }));
			log.info(successMsg);

			return I18NLogicUtilsApiInterna.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException | I18NValidationException re) {
			String msg = re.getLocalizedMessage();
			if (re instanceof I18NValidationException) {
				List<I18NFieldError> vrErrors = ((I18NValidationException) re).getFieldErrorList();
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
				msg = errorsMsg.toString();
			}
			log.error(msg);
			throw new RestException(msg, Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionarilloc.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Dessassigna un/a funcionari/ària a un lloc de feina
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant el registre
	 *                 d'un nou FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/treurefuncionari")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariLlocRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariLlocRestService.TAG_NAME }, operationId = "nouLloc", summary = "Registra un lloc de feina nou a RFHab")
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
	public String treureFuncionari(
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "Identificador de l'usuari que està realitzant el registre d'un nou FH", required = true, example = "9999", schema = @Schema(type = "int")) @NotNull @QueryParam("usuariid") Integer usuariId,
			@Parameter(description = "Codi del lloc (si és buit, es desassignarà de tots els llocs de feina)", required = false) @QueryParam("codilloc") String codiLloc,
			@Parameter(description = "Expansió del lloc", required = false) @QueryParam("expansio") String expansio,
			@Parameter(description = "Identificador del funcionari a desassignar", required = true) @QueryParam("identificadorfh") @NotNull String identificadorFh,
			@Parameter(description = "Observacions", required = false) @QueryParam("observacions") String observacions,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = "", implementation = String.class)) @QueryParam("numerocai") String numeroCai) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("CodiLloc: " + codiLloc + "\n");
			sb.append("Expansió: " + expansio + "\n");
			sb.append("Identificador FH: " + identificadorFh + "\n");
			sb.append("Observacions: " + observacions + "\n");
			sb.append("NumeroCai: " + numeroCai + "\n");
			log.info(sb.toString());

			if (numeroCai == null) {
				numeroCai = Constants.NUMEROCAI_BUIT;
				log.info("XYZ YYY numeroCai = " + numeroCai);
			}

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariLogicaEjb.comprovarFuncionariActiuByNif(language, usuariNif,
					true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			Funcionari funcionariAdesassignar = funcionariLogicaEjb.findByNif(identificadorFh);
			if (funcionariAdesassignar == null) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"funcionari.error.noexisteixnif",
						new String[] { identificadorFh }));
			}
			log.info("XYZ YYY funcionariAdesassignar = " + funcionariAdesassignar.getNumero());

			// cercam el lloc a desassignar
			Lloc llocAdesassignar = null;
			if (codiLloc != null && !codiLloc.isEmpty()) {
				List<Lloc> llocsAdesassignar = llocLogicaEjb.getLlocsByCodiIexpansio(codiLloc, expansio);
				if (llocsAdesassignar == null || llocsAdesassignar.size() == 0) {
					throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
							"error.lloc.noexisteixcodiiexpansio",
							new String[] { codiLloc, expansio }));
				}
				llocAdesassignar = llocsAdesassignar.get(0);
			}

			// Cream funcionariLloc i auditoria
			funcionariLogicaEjb.dessassignarFuncionariAndHistory(funcionariAdesassignar,
					llocAdesassignar != null ? llocAdesassignar.getLlocID() : null, numeroCai, usuariId.longValue(),
					false, false);

			String successMsg = String.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
					"success.modification",
					new String[] {
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionariLloc.funcionariLloc"),
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionari.funcionariID"),
							String.valueOf(funcionariAdesassignar.getFuncionariID()), "" }));
			log.info(successMsg);

			return I18NLogicUtilsApiInterna.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionarilloc.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Dessassigna tots/totes els/les funcionaris/es d'un lloc de feina
	 * 
	 * @param language Idioma en que s'han de retornar els missatges. Obligatori
	 * @param usuariId Identificador de l'usuari que està realitzant el registre
	 *                 d'un nou FH. Obligatori
	 * 
	 * @return
	 */

	@Path("/treuretotsfuncionari")
	@POST
	@Hidden
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = FuncionariLlocRestService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			FuncionariLlocRestService.TAG_NAME }, operationId = "nouLloc", summary = "Registra un lloc de feina nou a RFHab")
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
	public String treureTotsFuncionari(
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "Identificador de l'usuari que està realitzant el registre d'un nou FH", required = true, example = "9999", schema = @Schema(type = "int")) @NotNull @QueryParam("usuariid") Integer usuariId,
			@Parameter(description = "Codi del lloc", required = true) @QueryParam("codilloc") @NotNull String codiLloc,
			@Parameter(description = "Expansió del lloc", required = false) @QueryParam("expansio") String expansio,
			@Parameter(description = "Observacions", required = false) @QueryParam("observacions") String observacions,
			@Parameter(description = "Número CAI", required = false, schema = @Schema(defaultValue = Constants.NUMEROCAI_BUIT, implementation = String.class)) @QueryParam("numerocai") String numeroCai) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Usuari: " + usuariId + "\n");
			sb.append("CodiLloc: " + codiLloc + "\n");
			sb.append("Expansió: " + expansio + "\n");
			sb.append("Observacions: " + observacions + "\n");
			sb.append("NumeroCai: " + numeroCai + "\n");
			log.info(sb.toString());

			if (numeroCai == null) {
				numeroCai = Constants.NUMEROCAI_BUIT;
				log.info("XYZ YYY numeroCai = " + numeroCai);
			}

			// validar codi de funcionari
			String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuariId, language);
			FuncionariJPA funcionariActuant = funcionariLogicaEjb.comprovarFuncionariActiuByNif(language, usuariNif,
					true);

			String funcionariActuantNom = (funcionariActuant.getNom() != null ? funcionariActuant.getNom() : "") + " "
					+ (funcionariActuant.getLlinatge1() != null ? funcionariActuant.getLlinatge1() : "") + " "
					+ (funcionariActuant.getLlinatge2() != null ? funcionariActuant.getLlinatge2() : "");

			log.info("XYZ YYY funcionariActuantNom = " + funcionariActuantNom);

			// cercam el lloc a desassignar
			List<Lloc> llocsAdesassignar = llocLogicaEjb.getLlocsByCodiIexpansio(codiLloc, expansio);
			if (llocsAdesassignar == null || llocsAdesassignar.size() == 0) {
				throw new I18NException(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
						"error.lloc.noexisteixcodiiexpansio",
						new String[] { codiLloc, expansio }));
			}
			Lloc llocAdesassignar = llocsAdesassignar.get(0);

			// Cream funcionariLloc i auditoria
			funcionariLogicaEjb.dessassignarFuncionariAndHistory(null,
					llocAdesassignar.getLlocID(), numeroCai, usuariId.longValue(),
					false, false);

			String successMsg = String.valueOf(I18NLogicUtilsApiInterna.tradueix(new Locale(language),
					"success.modification",
					new String[] {
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionariLloc.funcionariLloc"),
							I18NLogicUtilsApiInterna.tradueix(new Locale(language), "lloc.llocID"),
							String.valueOf(llocAdesassignar.getLlocID()), "" }));
			log.info(successMsg);

			return I18NLogicUtilsApiInterna.tradueix(new Locale(language), "operacio.success");
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = I18NLogicUtilsApiInterna.tradueix(new Locale(language), "funcionarilloc.error.desconegut",
					new String[] { th.getMessage() });
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}
}
