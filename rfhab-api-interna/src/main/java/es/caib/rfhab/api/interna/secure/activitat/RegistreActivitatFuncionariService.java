package es.caib.rfhab.api.interna.secure.activitat;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.commons.utils.IdentificacioTipus;
import es.caib.rfhab.commons.utils.IdentificacioTipusValues;
import es.caib.rfhab.commons.utils.RegistreActivitatTipus;
import es.caib.rfhab.commons.utils.RegistreActivitatTipusValues;
import es.caib.rfhab.logic.ActivitatLogicaService;
import es.caib.rfhab.logic.AutoritzacioLogicaService;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.validator.ActivitatValidator;

import java.sql.Timestamp;
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
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
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
@Path("/secure/activitat")
@OpenAPIDefinition(tags = @Tag(name = RegistreActivitatFuncionariService.TAG_NAME, description = "Registrar l'activitat d'un/a funcionari/ària"), info = @Info(title = "API REST INTERNA de RFHab", description = "Serveis REST de RFHab per ser accedits emprant autenticació", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "https://governdigital.fundaciobit.org")), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplesecure-v1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = RegistreActivitatFuncionariService.SECURITY_NAME, scheme = "basic")
public class RegistreActivitatFuncionariService extends RestUtils {

	protected Logger log = Logger.getLogger(RegistreActivitatFuncionariService.class);

	@EJB(mappedName = ActivitatLogicaService.JNDI_NAME)
	protected ActivitatLogicaService activitatEjb;

	@EJB(mappedName = AutoritzacioLogicaService.JNDI_NAME)
	protected AutoritzacioLogicaService autoritzacioLogicaEjb;

	@EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
	protected FuncionariLogicaService funcionariLogicaEjb;

	protected ActivitatValidator<Activitat> validator = new RegistreActivitatValidator();

	protected static final String TAG_NAME = "RegistreActivitatFuncionariService";

	protected static final String SECURITY_NAME = "BasicAuth";

	/**
	 * Registra una activitat d'un funcionari
	 * 
	 * @param language                       Idioma en que s'han de retornar els
	 *                                       missatges. Obligatori
	 * @param funcionariNif                  NIF del/de la funcionari/ària que
	 *                                       realitza la activitat. Obligatori
	 * @param tipus                          Tipus de l'activitat. Obligatori
	 * @param dataActivitatStr               Data de registre de l'activitat.
	 *                                       Obligatori
	 * @param csvCopiaAutentica              Identificador de còpia autèntica.
	 *                                       Obligatori pel tipus d'activitat 1
	 * @param registre                       Número de registre associat a
	 *                                       l'activitat. Obligatori pel tipus
	 *                                       d'activitat 2
	 * @param idActuacioTramitFh             Identificador associat a l'activitat de
	 *                                       tràmit iniciada que es preten marcar
	 *                                       com a finalitzada. Obligatori pel tipus
	 *                                       d'activitat 2
	 * @param tramit                         Codi del tràmit associat a l'activitat.
	 *                                       Obligatori pel tipus d'activitat 3
	 * @param tramitVersio                   Versió del tràmit associat a
	 *                                       l'activitat. Obligatori pel tipus
	 *                                       d'activitat 3
	 * @param procediment                    Codi del procediment associat a
	 *                                       l'activitat. Obligatori pel tipus
	 *                                       d'activitat 3
	 * @param nomInteressat                  Nom de l'interessat/da del tràmit.
	 *                                       Obligatori pel tipus d'activitat 3
	 * @param llinatge1Interessat            Primer Llinatge de l'interessat/da del
	 *                                       tràmit. Obligatori pel tipus
	 *                                       d'activitat 3
	 * @param llinatge2Interessat            Segon Llinatge de l'interessat/da del
	 *                                       tràmit. Obligatori pel tipus
	 *                                       d'activitat 3
	 * @param tipusIdentificacioInteressat   Tipus d'identificació de
	 *                                       l'interessat/da del tràmit. Obligatori
	 *                                       pel tipus d'activitat 3
	 * @param identificacioInteressat        Identificació de l'interessat/da del
	 *                                       tràmit. Obligatori pel tipus
	 *                                       d'activitat 3
	 * @param nomRepresentant                Nom del/de la representant de
	 *                                       l'interessat/da del tràmit. Obligatori
	 *                                       pel tipus d'activitat 3
	 * @param llinatge1Representant          Primer Llinatge del/de la representant
	 *                                       de l'interessat/da del tràmit.
	 *                                       Obligatori pel tipus d'activitat 3
	 * @param llinatge2Representant          Segon Llinatge del/de la representant
	 *                                       de l'interessat/da del tràmit.
	 *                                       Obligatori pel tipus d'activitat 3
	 * @param tipusIdentificacioRepresentant Tipus d'identificació del/de la
	 *                                       representant de l'interessat/da del
	 *                                       tràmit. Obligatori pel tipus
	 *                                       d'activitat 3
	 * @param identificacioRepresentant      Identificació del/de la representant de
	 *                                       l'interessat/da del tràmit. Obligatori
	 *                                       pel tipus d'activitat 3
	 * @param arxiuExpedientId               Identificador de l'expedient a Arxiu.
	 *                                       Obligatori pel tipus d'activitat 3
	 * @param arxiuDocumentId                Identificador del document a Arxiu.
	 *                                       Obligatori pel tipus d'activitat 3
	 * @param codiSia                        Codi SIA del funcionari. Opcional
	 * @param autorització                   Codi de lautorització del funcionari.
	 *                                       Opcional
	 * 
	 * @return
	 */

	@Path("/registre")
	@POST
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = RegistreActivitatFuncionariService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			RegistreActivitatFuncionariService.TAG_NAME }, operationId = "registreActivitat", summary = "Registra una activiat d\'un funcionari")
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
	public String registreActivitat(

			// COMUNS PER TOTS (obligatoris)
			@Parameter(name = "language", description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')", in = ParameterIn.QUERY, required = false, examples = {
					@ExampleObject(name = "Català", value = "ca"),
					@ExampleObject(name = "Castellano", value = "es") }, schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language") String language,
			@Parameter(description = "NIF del/de la funcionari/ària que realitza la activitat", required = true, example = "44444444A", schema = @Schema(type = "string")) @NotNull @QueryParam("funcionari") String funcionariNif,
			@Parameter(description = "Tipus de l\'activitat:<br />&emsp;<i>"
					+ RegistreActivitatTipusValues.DESCRIPTION_ALL_VALUES
					+ "</i>", required = true, example = "", schema = @Schema(type = "RegistreActivitatTipus", description = RegistreActivitatTipusValues.DESCRIPTION_ALL_VALUES)) @NotNull @QueryParam("tipus") RegistreActivitatTipus tipus,
			@Parameter(description = "Data de registre de l\'activitat", required = true, example = "2025-08-31T06:15:00+00:00", schema = @Schema(implementation = String.class, pattern = DATE_PATTERN_ISO8601_DATE_AND_TIME)) @NotNull @QueryParam("data") String dataActivitatStr,

			// COPIA
			@Parameter(description = "Identificador de còpia autèntica, podria ser un identificador intern de digitalib o bé el csv de la còpia autèntica. Obligatori pel tipus d'activitat 1", required = false, example = "klñjjskadfjklsdkl/asdlfjsd", schema = @Schema(type = "string")) @QueryParam("identificadorCopiaAutentica") String csvCopiaAutentica,

			// TRAMIT
			@Parameter(description = "Número de registre associat a l\'activitat. Obligatori pel tipus d'activitat 2", required = false, example = "", schema = @Schema(type = "string")) @QueryParam("registre") String registre,
			@Parameter(description = "Identificador associat a l\'activitat de tràmit iniciada que es preten marcar com a finalitzada. Obligatori pel tipus d'activitat 2", required = false, example = "", schema = @Schema(type = "string")) @QueryParam("idActuacioTramitFh ") String idActuacioTramitFh,

			// COMPAREIX (ocults)
			@Parameter(description = "Codi del tràmit associat a l\'activitat. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("tramit") String tramit,
			@Parameter(description = "Versió del tràmit associat a l\'activitat. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("tramitversio") String tramitVersio,
			@Parameter(description = "Codi del procediment associat a l\'activitat. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("procediment") String procediment,
			@Parameter(description = "Nom de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("nominteressat") String nomInteressat,
			@Parameter(description = "Primer Llinatge de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("llinatge1interessat") String llinatge1Interessat,
			@Parameter(description = "Segon Llinatge de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("llinatge2interessat") String llinatge2Interessat,
			@Parameter(description = "Tipus d'identificació de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3:<br />&emsp;<i>"
					+ IdentificacioTipusValues.DESCRIPTION_ALL_VALUES
					+ "</i>", required = false, example = "", schema = @Schema(type = "IdentificacioTipus", description = IdentificacioTipusValues.DESCRIPTION_ALL_VALUES), hidden = true) @QueryParam("tipusidentificaciointeressat") IdentificacioTipus tipusIdentificacioInteressat,
			@Parameter(description = "Identificació de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("identificaciointeressat") String identificacioInteressat,
			@Parameter(description = "Nom del/de la representant de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("nomrepresentant") String nomRepresentant,
			@Parameter(description = "Primer Llinatge del/de la representant de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("llinatge1representant") String llinatge1Representant,
			@Parameter(description = "Segon Llinatge del/de la representant de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("llinatge2representant") String llinatge2Representant,
			@Parameter(description = "Tipus d'identificació del/de la representant de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3:<br />&emsp;<i>"
					+ IdentificacioTipusValues.DESCRIPTION_ALL_VALUES
					+ "</i>", required = false, example = "", schema = @Schema(type = "IdentificacioTipus", description = IdentificacioTipusValues.DESCRIPTION_ALL_VALUES), hidden = true) @QueryParam("tipusidentificaciorepresentant") IdentificacioTipus tipusIdentificacioRepresentant,
			@Parameter(description = "Identificació del/de la representant de l\'interessat/da del tràmit. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("identificaciorepresentant") String identificacioRepresentant,
			@Parameter(description = "Identificació de l'expedient d'Arxiu que conté el Model de consentiment firmat. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("arxiuexpedientid") String arxiuExpedientId,
			@Parameter(description = "Identificació del document d'Arxiu que conté el Model de consentiment firmat. Obligatori pel tipus d'activitat 3", required = false, example = "", schema = @Schema(type = "string"), hidden = true) @QueryParam("arxiudocumentid") String arxiuDocumentId,

			// No emprats... (ocults)
			@Parameter(description = "Codi SIA associat a l\'activitat", required = false, example = "", array = @ArraySchema(schema = @Schema(type = "string")), hidden = true) @QueryParam("codisia") String codiSia,
			@Parameter(description = "Codi de l\'autorització associat a l\'activitat", required = false, example = "", array = @ArraySchema(schema = @Schema(type = "string")), hidden = true) @QueryParam("autoritzacio") String autoritzacio) {

		try {
			/*
			 * http://localhost:8280/rfhabapi/interna/secure/activitat/registre?funcionari=
			 * Juan&tipus=1&registre=R0001&tramit=T001&codisia=XXX&autoritzacio=5
			 */

			StringBuilder sb = new StringBuilder();
			sb.append("Llengua: " + language + "\n");
			sb.append("Funcionari: " + funcionariNif + "\n");
			sb.append("Tipus activitat: " + tipus + "\n");
			sb.append("Data activitat: " + dataActivitatStr + "\n");
			sb.append("Identificador Copia Autèntica: " + csvCopiaAutentica + "\n");
			sb.append("Registre activitat: " + registre + "\n");
			sb.append("Identificació activitat tràmit: " + idActuacioTramitFh + "\n");
			sb.append("Tràmit: " + tramit + "\n");
			sb.append("Versió Tràmit: " + tramitVersio + "\n");
			sb.append("Procediment: " + procediment + "\n");
			sb.append("Nom Interessat: " + nomInteressat + "\n");
			sb.append("Llinatge1 Interessat: " + llinatge1Interessat + "\n");
			sb.append("Llinatge2 Interessat: " + llinatge2Interessat + "\n");
			sb.append("Tipus Identificació Interessat: " + tipusIdentificacioInteressat + "\n");
			sb.append("Identificació Interessat: " + identificacioInteressat + "\n");
			sb.append("Nom Representant: " + nomRepresentant + "\n");
			sb.append("Llinatge1 Representant: " + llinatge1Representant + "\n");
			sb.append("Llinatge2 Representant: " + llinatge2Representant + "\n");
			sb.append("Tipus Identificació Representant: " + tipusIdentificacioRepresentant + "\n");
			sb.append("Identificació Representant: " + identificacioRepresentant + "\n");
			sb.append("Identificació expedient Arxiu: " + arxiuExpedientId + "\n");
			sb.append("Identificació document Arxiu: " + arxiuDocumentId + "\n");
			sb.append("Codi SIA: " + codiSia + "\n");
			sb.append("Autoritzacio: " + autoritzacio + "\n");

			log.info(sb.toString());

			Timestamp dataActivitat = new Timestamp(
					parseDateTimeISO8601ToDate(dataActivitatStr, "data", language).getTime());

			// validar codi de funcionari
			FuncionariJPA funcionari = funcionariLogicaEjb.comprovarFuncionariActiuByNif(language, funcionariNif, true);
			Long funcionariId = funcionari.getFuncionariID();

			String funcionariNom = (funcionari.getNom() != null ? funcionari.getNom() : "") + " "
					+ (funcionari.getLlinatge1() != null ? funcionari.getLlinatge1() : "") + " "
					+ (funcionari.getLlinatge2() != null ? funcionari.getLlinatge2() : "");

			log.info("XYZ YYY funcionariNom = " + funcionariNom);

			Activitat newAct = activitatEjb.registraNovaActivitat(language, validator, tipus, csvCopiaAutentica,
					registre,
					idActuacioTramitFh, tramit, tramitVersio, procediment,
					nomInteressat, llinatge1Interessat, llinatge2Interessat, tipusIdentificacioInteressat,
					identificacioInteressat, nomRepresentant, llinatge1Representant, llinatge2Representant,
					tipusIdentificacioRepresentant, identificacioRepresentant, arxiuExpedientId, arxiuDocumentId,
					dataActivitat, funcionariId);
			return "Operació realitzada correctament";// TODO: #73 traduïr
		} catch (I18NException re) {
			log.error(re.getMessage(), re);
			throw new RestException(re.getMessage(), Status.BAD_REQUEST);
		} catch (Throwable th) {
			String msg = "Error desconegut enregistrant activitat: " + th.getMessage();
			log.error(msg, th);
			throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
		}
	}
}