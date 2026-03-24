package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.commons.utils.IdentificacioTipus;
import es.caib.rfhab.commons.utils.PersonalOamrTipus;
import es.caib.rfhab.logic.FuncionariLogicaEJB;

@Stateless
public class CarregadorMassiuFhIllocsLogicaEJB implements CarregadorMassiuFhIllocsLogicaService {
    private static class ConsultaFuncionariResponse {
        public boolean existeix;
        public boolean donatDeBaixa;
    }

    private static class ConsultaLlocResponse {
        public boolean existeix;
        public boolean donatDeBaixa;
    }

    private static class ConsultaUnitatResponse {
        public boolean existeix;
        public Long unitatId;
    }

    private static class ConsultaHabilitacioResponse {
        public boolean existeix;
        public Long habilitacioId;
    }

    /**
     * Helper per fer una cridada POST amb paràmetres com a queryparams i
     * autenticació bàsica.
     * El paràmetre json ha de ser un JSON objecte (no array), que es transforma a
     * queryparams.
     */
    private String doPostWithQueryParams(String endpoint, String json)
            throws Exception {

        return doPostWithQueryParams(endpoint, json, true);
    }

    /**
     * Helper per fer una cridada POST amb paràmetres com a queryparams i
     * autenticació bàsica.
     * El paràmetre json ha de ser un JSON objecte (no array), que es transforma a
     * queryparams.
     */
    private String doPostWithQueryParams(String endpoint, String json, boolean listParamsAsRepeatedParams)
            throws Exception {
        // Converteix el JSON a map
        java.util.Map<String, Object> params = objectMapper.readValue(json, java.util.HashMap.class);
        StringBuilder urlBuilder = new StringBuilder(endpoint);
        if (!params.isEmpty()) {
            urlBuilder.append("?");
            boolean first = true;
            for (var entry : params.entrySet()) {
                // Ex.: '.../habilitacions?usuari=string&entitat=string&entitat=string'
                if (listParamsAsRepeatedParams && entry.getValue() instanceof List<?>) {
                    for (Object entryValue : (List<Object>) entry.getValue()) {
                        first = appendToUrlBuilder(urlBuilder, first, entry.getKey(), entryValue);
                    }
                } else {
                    first = appendToUrlBuilder(urlBuilder, first, entry.getKey(), entry.getValue());
                }
            }
        }
        String urlWithParams = urlBuilder.toString();
        log.info("[REST] Endpoint (queryparams): " + urlWithParams);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlWithParams))
                .header("Authorization", getBasicAuthHeader())
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode < 200 || statusCode >= 300) {
            String errorMsg = "HTTP error: " + statusCode + " - " + response.body();
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return response.body();
    }

    private boolean appendToUrlBuilder(StringBuilder urlBuilder, boolean first, String entryKey, Object entryValue) {
        if (!first)
            urlBuilder.append("&");
        urlBuilder.append(java.net.URLEncoder.encode(entryKey, java.nio.charset.StandardCharsets.UTF_8));
        urlBuilder.append("=");
        urlBuilder.append(java.net.URLEncoder.encode(String.valueOf(entryValue),
                java.nio.charset.StandardCharsets.UTF_8));
        first = false;
        return first;
    }

    protected final Logger log = Logger.getLogger(getClass());

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String odsFilePath;
    private final String apiUrl;
    private final String user;
    private final String pass;
    private final String usuariId;
    private final String entitatId;
    private final OdsToDtoMapper mapper;

    /**
     * Constructor sense paràmetres. Inicialitza odsFilePath i mapper a null.
     * 
     * @throws I18NException
     */
    public CarregadorMassiuFhIllocsLogicaEJB() throws I18NException {
        super();

        this.odsFilePath = null;
        this.apiUrl = Configuracio.getCarregadorMassiuEndpoint();
        this.user = Configuracio.getCarregadorMassiuUser();
        this.pass = Configuracio.getCarregadorMassiuPassword();
        this.usuariId = Configuracio.getCarregadorMassiuUsuariId();
        this.entitatId = Configuracio.getCarregadorMassiuEntitatId();
        if (this.apiUrl == null || this.user == null || this.pass == null || this.usuariId == null
                || this.entitatId == null) {
            throw new I18NException(
                    "CarregadorMassiuFhIllocs configuration is incomplete. Please check the properties file.");
        }
        this.mapper = null;
    }

    /**
     * Constructor amb la ruta del fitxer ODS i la URL de l'API externa.
     * 
     * @param odsFilePath     Ruta del fitxer ODS
     * @param mappingFilePath Ruta del fitxer de mapping .properties
     * @throws Exception Si hi ha problemes de lectura
     */
    public CarregadorMassiuFhIllocsLogicaEJB(String odsFilePath, String mappingFilePath)
            throws Exception {
        super();

        this.odsFilePath = odsFilePath;
        this.apiUrl = Configuracio.getCarregadorMassiuEndpoint();
        this.user = Configuracio.getCarregadorMassiuUser();
        this.pass = Configuracio.getCarregadorMassiuPassword();
        this.usuariId = Configuracio.getCarregadorMassiuUsuariId();
        this.entitatId = Configuracio.getCarregadorMassiuEntitatId();
        if (this.apiUrl == null || this.user == null || this.pass == null || this.usuariId == null
                || this.entitatId == null) {
            throw new I18NException(
                    "CarregadorMassiuFhIllocs configuration is incomplete. Please check the properties file.");
        }

        this.mapper = new OdsToDtoMapper(new File(mappingFilePath));
    }

    /**
     * Constructor amb la ruta del fitxer ODS i la URL de l'API externa.
     * 
     * @param odsFilePath          Ruta del fitxer ODS
     * @param mappingFilePath      Ruta del fitxer de mapping .properties
     * @param apiExternaProperties Fitxer de propietats que conté la URL de l'API
     *                             REST externa i les credencials
     * @throws Exception Si hi ha problemes de lectura
     */
    public CarregadorMassiuFhIllocsLogicaEJB(String odsFilePath, String mappingFilePath,
            Properties apiExternaProperties)
            throws Exception {
        super();

        this.odsFilePath = odsFilePath;
        this.apiUrl = Configuracio.getCarregadorMassiuEndpoint(apiExternaProperties);
        this.user = Configuracio.getCarregadorMassiuUser(apiExternaProperties);
        this.pass = Configuracio.getCarregadorMassiuPassword(apiExternaProperties);
        this.usuariId = Configuracio.getCarregadorMassiuUsuariId(apiExternaProperties);
        this.entitatId = Configuracio.getCarregadorMassiuEntitatId(apiExternaProperties);

        log.info("CarregadorMassiuFhIllocsLogicaEJB constructor - apiUrl: " + this.apiUrl);
        log.info("CarregadorMassiuFhIllocsLogicaEJB constructor - user: " + this.user);
        log.info("CarregadorMassiuFhIllocsLogicaEJB constructor - usuariId: " + this.usuariId);
        log.info("CarregadorMassiuFhIllocsLogicaEJB constructor - entitatId: " + this.entitatId);

        if (this.apiUrl == null || this.user == null || this.pass == null || this.usuariId == null
                || this.entitatId == null) {
            throw new I18NException(
                    "CarregadorMassiuFhIllocs configuration is incomplete. Please check the properties file.");
        }

        this.mapper = new OdsToDtoMapper(new File(mappingFilePath));
    }

    /**
     * Constructor amb la ruta del fitxer ODS i la URL de l'API externa.
     * 
     * @param odsFilePath          Ruta del fitxer ODS
     * @param mappingFilePath      Ruta del fitxer de mapping .properties
     * @param apiExternaProperties Fitxer de propietats que conté la URL de l'API
     *                             REST externa i les credencials
     * @throws Exception Si hi ha problemes de lectura
     */
    public static CarregadorMassiuFhIllocsLogicaService CrearCarregadorMassiuFhIllocsLogicaEJBambEjbsPerTests(
            String odsFilePath, String mappingFilePath,
            Properties apiExternaProperties)
            throws Exception {

        CarregadorMassiuFhIllocsLogicaEJB carregador = null;

        carregador = new CarregadorMassiuFhIllocsLogicaEJB(odsFilePath, mappingFilePath, apiExternaProperties);

        return carregador;
    }

    /**
     * Cerca un camp en la jerarquia de classes
     */
    private static java.lang.reflect.Field findFieldInHierarchy(Class<?> clazz, String fieldName) {
        Class<?> current = clazz;
        while (current != null) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                current = current.getSuperclass();
            }
        }
        return null;
    }

    /**
     * Assigna valors a odsFilePath i mapper.
     * 
     * @param odsFilePath     Ruta del fitxer ODS
     * @param mappingFilePath Ruta del fitxer de mapping .properties
     * @throws Exception Si hi ha problemes de lectura
     */
    public void configureOdsPaths(String odsFilePath, String mappingFilePath) throws Exception {
        if (odsFilePath == null || mappingFilePath == null) {
            throw new IllegalArgumentException("odsFilePath i mappingFilePath no poden ser null");
        }
        // Reflection hack: odsFilePath and mapper are final, but for this patch, assume
        // we can set them (or remove final if needed)
        java.lang.reflect.Field odsField = CarregadorMassiuFhIllocsLogicaEJB.class.getDeclaredField("odsFilePath");
        odsField.setAccessible(true);
        odsField.set(this, odsFilePath);
        java.lang.reflect.Field mapperField = CarregadorMassiuFhIllocsLogicaEJB.class.getDeclaredField("mapper");
        mapperField.setAccessible(true);
        mapperField.set(this, new OdsToDtoMapper(new File(mappingFilePath)));
    }

    /**
     * Helper per crear un JSON a partir de parells clau-valor.
     */
    private String buildJson(Boolean ignoreNulls, Object... kvPairs) throws Exception {
        var params = new java.util.HashMap<String, Object>();
        for (int i = 0; i + 1 < kvPairs.length; i += 2) {
            Object value = kvPairs[i + 1];
            if (value != null || (ignoreNulls != null && !ignoreNulls)) {
                params.put(String.valueOf(kvPairs[i]), value);
            }
        }
        return objectMapper.writeValueAsString(params);
    }

    /**
     * Helper per crear un JSON a partir de parells clau-valor.
     */
    private String buildJson(Object... kvPairs) throws Exception {
        return buildJson(true, kvPairs);
    }

    /**
     * Helper per fer una crida POST amb JSON i autenticació bàsica.
     */
    private String doPostJson(String endpoint, String json) throws Exception {
        log.info("[REST] Endpoint: " + endpoint);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", getBasicAuthHeader())
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode < 200 || statusCode >= 300) {
            String errorMsg = "HTTP error: " + statusCode + " - " + response.body();
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        return response.body();
    }

    private ConsultaFuncionariResponse consultaFuncionariPerNif(String lang, String nif) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/consulta";
        String json = buildJson("language", lang, "identificador", nif);
        String response = doPostWithQueryParams(endpoint, json);
        return objectMapper.readValue(response, ConsultaFuncionariResponse.class);
    }

    private ConsultaLlocResponse consultaLlocPerCodiIExpansio(String lang, String codiLloc, String expansio)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/consulta";
        String json = buildJson("language", lang, "codilloc", codiLloc, "expansio", expansio);
        String response = doPostWithQueryParams(endpoint, json);
        return objectMapper.readValue(response, ConsultaLlocResponse.class);
    }

    private ConsultaUnitatResponse consultaUnitatPerCodiDir3(String lang, String codiDir3) throws Exception {
        String endpoint = apiUrl + "/secure/unitat/consulta";
        String json = buildJson("language", lang, "codidir3", codiDir3);
        String response = doPostWithQueryParams(endpoint, json);
        return objectMapper.readValue(response, ConsultaUnitatResponse.class);
    }

    private ConsultaHabilitacioResponse consultaHabilitacioPerCodi(String lang, String codiHabilitacio)
            throws Exception {
        String endpoint = apiUrl + "/secure/habilitacio/consulta";
        String json = buildJson("language", lang, "codi", codiHabilitacio);
        String response = doPostWithQueryParams(endpoint, json);
        return objectMapper.readValue(response, ConsultaHabilitacioResponse.class);
    }

    private String getBasicAuthHeader() {
        String auth = user + ":" + pass;
        return "Basic "
                + java.util.Base64.getEncoder().encodeToString(auth.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    /**
     * Dona d'alta un nou funcionari habilitat via API REST.
     */
    @Override
    public String nouFuncionariHabilitat(NouFuncionariHabilitatDTO dto) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/nou";
        String json = objectMapper.writeValueAsString(dto);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Dona d'alta un funcionari via API REST.
     */
    @Override
    public String donarAltaFh(String lang, String usuariId, String identificadorFh, String numCai) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/donaralta";
        String json = buildJson("language", lang, "usuariid", usuariId, "identificador", identificadorFh, "numerocai",
                numCai);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Dona de baixa un funcionari via API REST.
     */
    @Override
    public String donarBaixaFh(String lang, String usuariId, String identificadorFh, String numCai) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/donarbaixa";
        String json = buildJson("language", lang, "usuariid", usuariId, "identificador", identificadorFh, "numerocai",
                numCai);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Dona d'alta un nou lloc via API REST.
     */
    @Override
    public String nouLloc(NouLlocDTO dto)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/nou";
        String json = objectMapper.writeValueAsString(dto);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Dona d'alta un lloc via API REST.
     */
    @Override
    public String donarAltaLloc(String lang, String usuariId, String codiLloc, String expansio, String numCai)
            throws Exception {
        return donarAltaLloc(lang, usuariId, codiLloc, expansio, null, numCai);
    }

    /**
     * Dona d'alta un lloc via API REST, permetent identificació per codi propi.
     */
    public String donarAltaLloc(String lang, String usuariId, String codiLloc, String expansio, String codiLlocPropi,
            String numCai)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/donaralta";
        String json = buildJson("language", lang, "usuariid", usuariId, "codilloc", codiLloc, "expansio", expansio,
                "codillocpropi", codiLlocPropi, "numerocai", numCai);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Dona de baixa un lloc via API REST.
     */
    @Override
    public String donarBaixaLloc(String lang, String usuariId, String codiLloc, String expansio, String numCai)
            throws Exception {
        return donarBaixaLloc(lang, usuariId, codiLloc, expansio, null, numCai);
    }

    /**
     * Dona de baixa un lloc via API REST, permetent identificació per codi propi.
     */
    public String donarBaixaLloc(String lang, String usuariId, String codiLloc, String expansio, String codiLlocPropi,
            String numCai)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/donarbaixa";
        String json = buildJson("language", lang, "usuariid", usuariId, "codilloc", codiLloc, "expansio", expansio,
                "codillocpropi", codiLlocPropi, "numerocai", numCai);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Assigna un funcionari a un lloc via API REST.
     */
    @Override
    public String assignarFuncionari(String lang, String usuariId, String identificadorFh, String codiLloc,
            String expansio, String dataIniciStr, String dataFiStr, String numeroCai, String observacions)
            throws Exception {
        return assignarFuncionari(lang, usuariId, identificadorFh, codiLloc, expansio, null, dataIniciStr, dataFiStr,
                numeroCai, observacions);
    }

    /**
     * Assigna un funcionari a un lloc via API REST, permetent identificació per
     * codi propi.
     */
    public String assignarFuncionari(String lang, String usuariId, String identificadorFh, String codiLloc,
            String expansio, String codiLlocPropi, String dataIniciStr, String dataFiStr, String numeroCai,
            String observacions)
            throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/assignarfuncionari";
        String json = buildJson("language", lang, "usuariid", usuariId, "identificadorfh", identificadorFh, "codilloc",
                codiLloc, "expansio", expansio, "codillocpropi", codiLlocPropi, "datainici", dataIniciStr, "datafi",
                dataFiStr, "numerocai", numeroCai,
                "observacions", observacions);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Treu un funcionari d'un lloc via API REST.
     */
    @Override
    public String treureFuncionari(String lang, String usuariId, String identificadorFh, String codiLloc,
            String expansio, String numeroCai,
            String observacions) throws Exception {
        return treureFuncionari(lang, usuariId, identificadorFh, codiLloc, expansio, null, numeroCai, observacions);
    }

    public String treureFuncionari(String lang, String usuariId, String identificadorFh, String codiLloc,
            String expansio, String codiLlocPropi, String numeroCai,
            String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/treurefuncionari";
        String json = buildJson("language", lang, "usuariid", usuariId, "identificadorfh", identificadorFh, "codilloc",
                codiLloc, "expansio", expansio,
                "codillocpropi", codiLlocPropi,
                "numerocai", numeroCai, "observacions", observacions);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Treu tots els funcionaris d'un lloc via API REST.
     */
    @Override
    public String treureTotsFuncionari(String lang, String usuariId, String codiLloc, String expansio, String numeroCai,
            String observacions)
            throws Exception {
        return treureTotsFuncionari(lang, usuariId, codiLloc, expansio, null, numeroCai, observacions);
    }

    public String treureTotsFuncionari(String lang, String usuariId, String codiLloc, String expansio,
            String codiLlocPropi, String numeroCai,
            String observacions)
            throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/treuretotsfuncionari";
        String json = buildJson("language", lang, "usuariid", usuariId, "codilloc", codiLloc, "expansio", expansio,
                "codillocpropi", codiLlocPropi,
                "numerocai", numeroCai, "observacions",
                observacions);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Llegeix el fitxer ODS i processa cada línia com a FuncionariOdsDTO, aplicant
     * la lògica de càrrega.
     * La lògica de crida a l'API REST s'ha d'implementar més endavant.
     * 
     * @throws Exception Si hi ha problemes de lectura o processament
     */
    @Override
    public List<String> carregaFh(Consumer<FuncionariOdsDTO> dtoMapper) throws Exception {
        List<String> errors = new ArrayList<>();
        if (odsFilePath == null || mapper == null) {
            throw new IllegalStateException(
                    "odsFilePath o mapper no inicialitzats. Cal cridar configureOdsPaths abans d'utilitzar carregaFh().");
        }
        List<FuncionariOdsDTO> dtos = mapper.readOdsToDto(new File(odsFilePath), FuncionariOdsDTO.class, true, true);
        for (FuncionariOdsDTO dto : dtos) {
            // Aquí s'aplicarà la lògica de processament i crida a l'API REST externa
            log.info("Processant DTO: " + dto.toString());
            try {
                dtoMapper.accept(dto);
                processaFuncionarisLlocsIassignacions(dto);
            } catch (I18NException | RuntimeException ex) {
                ex.printStackTrace();
                errors.add(ex.getMessage());
            }
        }

        return errors;
    }

    /**
     * Processa un DTO i fa la crida a l'API REST externa.
     * 
     * @param dto El DTO a processar
     * @throws Exception
     */
    private void processaFuncionarisLlocsIassignacions(FuncionariOdsDTO dto) throws Exception {
        String dataAltaFh = dto.dataAlta;
        String dataBaixaFh = dto.dataBaixa;
        String dataDesassignacio = dto.dataDesassignacio;
        String observacionsAlta = "Observacions alta: " + dto.observacionsAlta + "\n";
        String observacionsBaixa = "Observacions baixa: " + dto.observacionsBaixa + "\n";
        String observacions = ((dataAltaFh != null && !dataAltaFh.isEmpty()) ? observacionsAlta : "")
                + ((dataBaixaFh != null && !dataBaixaFh.isEmpty()) ? observacionsBaixa : "");
        String lang = "ca";

        // creació de FH
        // (no se li pot posar una data alta passada, TODO???)
        ConsultaFuncionariResponse consultaFuncionari = consultaFuncionariPerNif(lang, dto.nif);
        // Si ja existeix el funcionari, no l'intentam crear
        if (!consultaFuncionari.existeix) {
            NouFuncionariHabilitatDTO nouFh = new NouFuncionariHabilitatDTO(
                    lang,
                    Integer.parseInt(this.usuariId),
                    dto.nom,
                    dto.primerLlinatge,
                    dto.segonLlinatge,
                    FuncionariLogicaEJB.getNumeroFhFromNumeric(Integer.parseInt(dto.numRfh)),
                    IdentificacioTipus.DNI,
                    dto.nif,
                    dto.usuari,
                    dto.adrecaElectronica,
                    Long.parseLong(this.entitatId),
                    dto.numCaiAlta,
                    observacions,
                    null);
            log.info("Creant FH: " + nouFh.toString());
            String respostaCreacióFh = nouFuncionariHabilitat(nouFh);
            log.info("Resposta creació FH: " + respostaCreacióFh);

            log.info("Donant alta FH: " + dto.nif);
            String respostaDonarAltaFh = donarAltaFh(lang, usuariId, dto.nif, dto.numCaiAlta);
            log.info("Resposta donar alta FH: " + respostaDonarAltaFh);
        } else if (consultaFuncionari.donatDeBaixa) {
            // Si ja existeix el funcionari, només l'intentarem donar d'alta en cas de que
            // estigui donat de baixa
            log.info("Donant alta FH: " + dto.nif);
            String respostaDonarAltaFh = donarAltaFh(lang, usuariId, dto.nif, dto.numCaiAlta);
            log.info("Resposta donar alta FH: " + respostaDonarAltaFh);
        }

        // creació de llocs
        ConsultaLlocResponse consultaLloc = null;
        // IBSalut pot tenir codi lloc null
        if (dto.codiLlocFeina != null && !dto.codiLlocFeina.isEmpty()) {
            consultaLloc = consultaLlocPerCodiIExpansio(lang, dto.codiLlocFeina, dto.expansio);
        }

        String codiLlocPropi = "";
        // SI JA EXISTEIX EL LLOC, NO L'HEM D'INTENTAR CREAR
        if (consultaLloc == null || !consultaLloc.existeix) {
            PersonalOamrTipus personalOamr = PersonalOamrTipus.SI;
            try {
                personalOamr = PersonalOamrTipus.fromName(dto.oamr);
            } catch (IllegalArgumentException iaex) {
                log.warn(
                        "No s'ha pogut traduïr el camp OAMR de numRFH \"" + dto.numRfh + "\". --> OAMR rebut: "
                                + dto.oamr);
            }
            if (dto.dir3UnitatOrganica == null) {
                String errorMsg = "Assignant el funcionari amb nif " + dto.nif + " al lloc de feina "
                        + dto.codiLlocFeina + " s'ha trobat buit el camp dir3UnitatOrganica";
                throw new I18NException(errorMsg);
            }

            String[] unitatLlocCodiDir3 = dto.dir3UnitatOrganica.split("[vV](?=\\d+$)");
            String codiUnitatDir3 = unitatLlocCodiDir3[0].trim().toUpperCase();
            Integer versioUnitatDir3 = unitatLlocCodiDir3.length > 1 ? (Integer.parseInt(unitatLlocCodiDir3[1].trim()))
                    : null;
            if (versioUnitatDir3 == null) {
                versioUnitatDir3 = 1;
            }
            log.info("=== DEBUG Cerca Unitat ===");
            log.info("Codi original: [" + dto.dir3UnitatOrganica + "]");
            log.info("Codi cercat: [" + codiUnitatDir3 + "] (length=" + codiUnitatDir3.length() + ")");
            log.info("Versio: " + versioUnitatDir3);
            ConsultaUnitatResponse consultaUnitat = consultaUnitatPerCodiDir3(lang, codiUnitatDir3);
            log.info("Unitat trobada: " + (consultaUnitat != null ? consultaUnitat.unitatId : null));
            if (consultaUnitat == null || !consultaUnitat.existeix || consultaUnitat.unitatId == null) {
                String errorMsg = "No s'ha trobat la unitat amb codiDir3:\n" +
                        "  Original: [" + dto.dir3UnitatOrganica + "]\n" +
                        "  Cercat: [" + codiUnitatDir3 + "] (length=" + codiUnitatDir3.length() + ")\n" +
                        "  Versió: " + versioUnitatDir3 + "\n" +
                        "  COMPROVAR: Espais, majúscules/minúscules, i que el camp 'codiDir3' a la BD contingui aquest valor EXACTAMENT";

                throw new I18NException(errorMsg);
            }
            List<Long> habilitacions = new ArrayList<>();
            String[] habilitacionsFromInput = dto.habilitacio.split(",");
            for (String codiHabilitacio : habilitacionsFromInput) {
                log.info("Cercant habilitació amb codi: " + codiHabilitacio);
                ConsultaHabilitacioResponse consultaHabilitacio = consultaHabilitacioPerCodi(lang,
                        codiHabilitacio.trim());
                log.info("Habilitació trobada: "
                        + (consultaHabilitacio != null ? consultaHabilitacio.habilitacioId : null));
                if (consultaHabilitacio == null || !consultaHabilitacio.existeix
                        || consultaHabilitacio.habilitacioId == null) {
                    String errorMsg = "No s'ha trobat l'habilitació amb CODI " + codiHabilitacio;
                    throw new I18NException(errorMsg);
                }
                Long habilitacioID = consultaHabilitacio.habilitacioId;
                if (!habilitacions.contains(habilitacioID)) {
                    habilitacions.add(habilitacioID);
                }
            }
            NouLlocDTO nouLloc = new NouLlocDTO(lang, Integer.parseInt(usuariId), dto.codiLlocFeina, dto.expansio,
                    dto.nomLlocFeina, personalOamr, Long.parseLong(entitatId), consultaUnitat.unitatId,
                    dto.numCaiAlta,
                    habilitacions.stream().map(Object::toString)
                            .collect(Collectors.toUnmodifiableList()).toArray(new String[0]),
                    dto.observacionsAlta, null, null);
            log.info("Creant lloc: " + nouLloc.toString());
            String respostaNouLloc = nouLloc(nouLloc);
            log.info("Resposta creació lloc: " + respostaNouLloc);
            codiLlocPropi = respostaNouLloc;

            log.info("Donant alta lloc: " + dto.codiLlocFeina + " - " + dto.expansio);
            String respostaDonarAltaLloc = donarAltaLloc(lang, usuariId, dto.codiLlocFeina, dto.expansio,
                    codiLlocPropi,
                    dto.numCaiAlta);
            log.info("Resposta donar alta lloc: " + respostaDonarAltaLloc);
        } else {
            // SI EL LLOC JA EXISTIA, NOMÉS EL DONAREM D'ALTA EN CAS D'ESTAR DONAT DE BAIXA
            if (consultaLloc.donatDeBaixa) {
                log.info("Donant alta lloc: " + dto.codiLlocFeina + " - " + dto.expansio);
                String respostaDonarAltaLloc = donarAltaLloc(lang, usuariId, dto.codiLlocFeina, dto.expansio,
                        dto.numCaiAlta);
                log.info("Resposta donar alta lloc: " + respostaDonarAltaLloc);
                codiLlocPropi = respostaDonarAltaLloc;
            }
        }

        // assignació de FH a lloc
        log.info("Assignant funcionari " + dto.nif + " a lloc " + dto.codiLlocFeina + " - " + dto.expansio);
        String codiLlocPerAssignacio = (dto.codiLlocFeina != null && !dto.codiLlocFeina.trim().isEmpty())
                ? dto.codiLlocFeina
                : null;
        String codiLlocPropiPerAssignacio = (codiLlocPropi != null && !codiLlocPropi.trim().isEmpty())
                ? codiLlocPropi
                : null;
        String respostaAssignarFuncionari = assignarFuncionari(lang, usuariId, dto.nif, codiLlocPerAssignacio,
                dto.expansio, codiLlocPropiPerAssignacio,
                dataAltaFh, dataDesassignacio, dto.numCaiAlta, observacions);
        log.info("Resposta assignació funcionari a lloc: " + respostaAssignarFuncionari);

        if (dataBaixaFh != null && !dataBaixaFh.isEmpty()) {
            log.info("Donant baixa FH: " + dto.nif + " - CAI: " + dto.numCaiAlta);
            String respostaDonarBaixaFh = donarBaixaFh(lang, usuariId, dto.nif, dto.numCaiAlta);
            log.info("Resposta donar baixa FH: " + respostaDonarBaixaFh);
            log.info("Donant baixa lloc: " + dto.codiLlocFeina + " - " + dto.expansio);
            String codiLlocPerBaixa = (dto.codiLlocFeina != null && !dto.codiLlocFeina.trim().isEmpty())
                    ? dto.codiLlocFeina
                    : null;
            String codiLlocPropiPerBaixa = (codiLlocPropi != null && !codiLlocPropi.trim().isEmpty())
                    ? codiLlocPropi
                    : null;
            String respostaDonarBaixaLloc = donarBaixaLloc(lang, usuariId, codiLlocPerBaixa, dto.expansio,
                    codiLlocPropiPerBaixa,
                    dto.numCaiAlta);
            log.info("Resposta donar baixa lloc: " + respostaDonarBaixaLloc);
        }
    }

    @Override
    public String getOdsFilePath() {
        if (odsFilePath == null) {
            throw new IllegalStateException(
                    "odsFilePath no inicialitzat. Cal cridar configureOdsPaths abans d'utilitzar getOdsFilePath().");
        }
        return odsFilePath;
    }

    @Override
    public String getApiUrl() {
        return apiUrl;
    }
}
