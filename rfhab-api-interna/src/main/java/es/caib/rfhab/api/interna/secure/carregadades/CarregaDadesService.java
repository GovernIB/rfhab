package es.caib.rfhab.api.interna.secure.carregadades;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris.CarregadorMassiuFhIllocsLogicaEJB;
import es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris.CarregadorMassiuFhIllocsLogicaService;
import es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris.FuncionariOdsDTO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import io.swagger.v3.oas.annotations.media.Content;

/**
 *
 * @author fbosch
 * @author jpou
 *
 */
@Path("/secure/carregadades")
@OpenAPIDefinition(tags = @Tag(name = CarregaDadesService.TAG_NAME, description = "Carrega inicial de dades"), info = @Info(title = "API REST INTERNA de RFHab", description = "Serveis REST de RFHab per ser accedits emprant autenticació", version = "1.0-SNAPSHOT", license = @License(name = "European Union Public Licence (EUPL v1.2)", url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"), contact = @Contact(name = "Departament de Govern Digital a la Fundació Bit", email = "otae@fundaciobit.org", url = "https://governdigital.fundaciobit.org")), externalDocs = @ExternalDocumentation(description = "Java Client (GovernIB Github)", url = "https://github.com/GovernIB/rfhab/tree/rfhab-1.0/rfhab-api-interna-client-exemplesecure-v1"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = CarregaDadesService.SECURITY_NAME, scheme = "basic")
public class CarregaDadesService extends RestUtils {

	protected Logger log = Logger.getLogger(CarregaDadesService.class);

	protected static final String TAG_NAME = "CarregaDadesService";

	protected static final String SECURITY_NAME = "BasicAuth";

	private static Properties configCarregadorMassiu = null;
	private static CarregadorMassiuFhIllocsLogicaService carregador = null;
	private static List<SimpleDateFormat> datesFormatsFromOds = null;

	private String mappingResourcePath = "ods-mapping.properties";

	private List<String> datesFormats = Arrays.asList("dd/MM/yyyy", "dd/MM/yy");

	@Path("/carregainicial")
	@POST
	@RolesAllowed({ Constants.RFH_WS })
	@SecurityRequirement(name = CarregaDadesService.SECURITY_NAME)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(tags = {
			CarregaDadesService.TAG_NAME }, operationId = "CarregaInicialDades", summary = "Carrega inicial de dades")
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
	public String carregaInicialDades(

			@Parameter(description = "Fitxers", required = true, schema = @Schema(implementation = FitxersCarrega.class)) FitxersCarrega fitxers) {

		File tempMappingFile = null;
		File tempOdsFile = null;
		try {
			// Carregar properties des dels bytes enviats
			configCarregadorMassiu = new Properties();
			configCarregadorMassiu.load(new ByteArrayInputStream(fitxers.getProperties()));
			log.info("Properties carregades: " + configCarregadorMassiu);

			// Carregar mapping properties del classpath i crear fitxer temporal
			tempMappingFile = File.createTempFile("rfhab_mapping_", ".properties");
			try (InputStream is = getClass().getClassLoader().getResourceAsStream(mappingResourcePath);
					FileOutputStream fos = new FileOutputStream(tempMappingFile)) {
				if (is == null) {
					throw new IOException("No s'ha trobat el recurs: " + mappingResourcePath);
				}
				byte[] buffer = new byte[8192];
				int bytesRead;
				while ((bytesRead = is.read(buffer)) != -1) {
					fos.write(buffer, 0, bytesRead);
				}
			}
			log.info("Fitxer mapping temporal creat: " + tempMappingFile.getAbsolutePath());

			// Crear fitxer temporal per al fitxer ODS des dels bytes enviats
			tempOdsFile = File.createTempFile("rfhab_ods_", ".ods");
			try (FileOutputStream fos = new FileOutputStream(tempOdsFile)) {
				fos.write(fitxers.getExcel());
			}
			log.info("Fitxer ODS temporal creat: " + tempOdsFile.getAbsolutePath());

			// Crear carregador amb el constructor amb EJBs injectats
			carregador = new CarregadorMassiuFhIllocsLogicaEJB(
					tempOdsFile.getAbsolutePath(), tempMappingFile.getAbsolutePath(), configCarregadorMassiu);
			datesFormatsFromOds = new ArrayList<>();
			datesFormats.forEach(df -> datesFormatsFromOds.add(new SimpleDateFormat(df)));

			/*
			 * assertNotNull(carregador);
			 * assertTrue(carregador.getOdsFilePath().endsWith(".ods"));
			 * assertTrue(carregador.getApiUrl().startsWith("http"));
			 */

			// Només comprovem que no llença excepcions i processa DTOs
			List<String> errorsCarregant = carregador.carregaFh(fod -> mapDtoDatesIhabilitacions(fod));
			// mostra el llistat d'errors
			log.info("S'han trobat " + errorsCarregant.size() + " Errors en carregar FH"
					+ (errorsCarregant.size() > 0 ? ": " + errorsCarregant : "."));

			List<String> errorsAmbPrefix = errorsCarregant.stream()
					.map(e -> "##ERROR##" + e)
					.collect(java.util.stream.Collectors.toList());

			java.nio.file.Files.write(
					java.nio.file.Paths.get("errorsCarregant.txt"),
					errorsAmbPrefix,
					java.nio.charset.StandardCharsets.UTF_8,
					java.nio.file.StandardOpenOption.CREATE,
					java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
			// assertTrue(errorsCarregant.isEmpty());
			// TODO: afegir asserts sobre l'estat intern o mocks
			log.info("carregaFh executat correctament per: " + tempOdsFile.getAbsolutePath());

			return "Test: " + fitxers.getExcel().length + " bytes excel, " + fitxers.getProperties().length
					+ " bytes properties";
		} catch (Exception e) {
			log.error("Error consultant les habilitacions d'un funcionari: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Eliminar fitxers temporals
			if (tempMappingFile != null && tempMappingFile.exists()) {
				try {
					tempMappingFile.delete();
					log.info("Fitxer mapping temporal eliminat: " + tempMappingFile.getAbsolutePath());
				} catch (Exception e) {
					log.warn("No s'ha pogut eliminar el fitxer mapping temporal: " + tempMappingFile.getAbsolutePath());
				}
			}
			if (tempOdsFile != null && tempOdsFile.exists()) {
				try {
					tempOdsFile.delete();
					log.info("Fitxer ODS temporal eliminat: " + tempOdsFile.getAbsolutePath());
				} catch (Exception e) {
					log.warn("No s'ha pogut eliminar el fitxer ODS temporal: " + tempOdsFile.getAbsolutePath());
				}
			}
		}
		return "hola";

	}

	private void mapDtoDatesIhabilitacions(FuncionariOdsDTO fod) {
		mapDtoDates(fod);
		mapDtoHabilitacions(fod);
	}

	private void mapDtoDates(FuncionariOdsDTO fod) {
		fod.dataAlta = RestUtils.convertDateToDateTimeISO8601(tryParseDate(fod.dataAlta));
		fod.dataBaixa = RestUtils.convertDateToDateTimeISO8601(tryParseDate(fod.dataBaixa));
		fod.dataDesassignacio = RestUtils.convertDateToDateTimeISO8601(tryParseDate(fod.dataDesassignacio));
	}

	private void mapDtoHabilitacions(FuncionariOdsDTO fod) {
		if (fod.habilitacio != null) {
			fod.habilitacio = fod.habilitacio.toUpperCase();
		}
	}

	java.util.Date tryParseDate(String dateString) {
		if (dateString == null) {
			return null;
		}

		for (SimpleDateFormat formatString : datesFormatsFromOds) {
			try {
				return formatString.parse(dateString);
			} catch (ParseException e) {
			}
		}

		return null;
	}
}
