package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
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
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.UnitatLogicaEJB;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.model.entity.Unitat;

@Stateless
public class CarregadorMassiuFhIllocsLogicaEJB implements CarregadorMassiuFhIllocsLogicaService {
    /**
     * Helper per fer una cridada POST amb paràmetres com a queryparams i
     * autenticació bàsica.
     * El paràmetre json ha de ser un JSON objecte (no array), que es transforma a
     * queryparams.
     */
    private String doPostWithQueryParams(String endpoint, String json) throws Exception {
        // Converteix el JSON a map
        java.util.Map<String, Object> params = objectMapper.readValue(json, java.util.HashMap.class);
        StringBuilder urlBuilder = new StringBuilder(endpoint);
        if (!params.isEmpty()) {
            urlBuilder.append("?");
            boolean first = true;
            for (var entry : params.entrySet()) {
                if (!first)
                    urlBuilder.append("&");
                urlBuilder.append(java.net.URLEncoder.encode(entry.getKey(), java.nio.charset.StandardCharsets.UTF_8));
                urlBuilder.append("=");
                urlBuilder.append(java.net.URLEncoder.encode(String.valueOf(entry.getValue()),
                        java.nio.charset.StandardCharsets.UTF_8));
                first = false;
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

    protected final Logger log = Logger.getLogger(getClass());

    @EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
    protected FuncionariLogicaService funcionariLogicaEjb;

    @EJB(mappedName = UnitatLogicaService.JNDI_NAME)
    protected UnitatLogicaService unitatLogicaEjb;

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

        FuncionariLogicaService funcionariLogicaEjb;
        UnitatLogicaService unitatLogicaEjb;
        CarregadorMassiuFhIllocsLogicaEJB carregador = null;

        funcionariLogicaEjb = new FuncionariLogicaEJB();
        unitatLogicaEjb = new UnitatLogicaEJB();
        carregador = new CarregadorMassiuFhIllocsLogicaEJB(odsFilePath, mappingFilePath, apiExternaProperties);

        carregador.funcionariLogicaEjb = funcionariLogicaEjb;
        carregador.unitatLogicaEjb = unitatLogicaEjb;

        return carregador;
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
    private String buildJson(Object... kvPairs) throws Exception {
        var params = new java.util.HashMap<String, Object>();
        for (int i = 0; i + 1 < kvPairs.length; i += 2) {
            params.put(String.valueOf(kvPairs[i]), kvPairs[i + 1]);
        }
        return objectMapper.writeValueAsString(params);
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
        String endpoint = apiUrl + "/secure/lloc/donaralta";
        String json = buildJson("language", lang, "usuariid", usuariId, "codilloc", codiLloc, "expansio", expansio,
                "numerocai", numCai);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Dona de baixa un lloc via API REST.
     */
    @Override
    public String donarBaixaLloc(String lang, String usuariId, String codiLloc, String expansio, String numCai)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/donarbaixa";
        String json = buildJson("language", lang, "usuariid", usuariId, "codilloc", codiLloc, "expansio", expansio,
                "numerocai", numCai);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Assigna un funcionari a un lloc via API REST.
     */
    @Override
    public String assignarFuncionari(String lang, String usuariId, String identificadorFh, String codiLloc,
            String expansio, String numeroCai,
            String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/assignarfuncionari";
        String json = buildJson("identificadorfh", identificadorFh, "codilloc", codiLloc, "expansio", expansio,
                "numerocai", numeroCai, "observacions", observacions);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Treu un funcionari d'un lloc via API REST.
     */
    @Override
    public String treureFuncionari(String identificadorFh, String codiLloc, String expansio, String numeroCai,
            String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/treurefuncionari";
        String json = buildJson("identificadorfh", identificadorFh, "codilloc", codiLloc, "expansio", expansio,
                "numerocai", numeroCai, "observacions", observacions);
        return doPostWithQueryParams(endpoint, json);
    }

    /**
     * Treu tots els funcionaris d'un lloc via API REST.
     */
    @Override
    public String treureTotsFuncionari(String codiLloc, String expansio, String numeroCai, String observacions)
            throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/treuretotsfuncionari";
        String json = buildJson("codilloc", codiLloc, "expansio", expansio, "numerocai", numeroCai, "observacions",
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
    public void carregaFh() throws Exception {
        if (odsFilePath == null || mapper == null) {
            throw new IllegalStateException(
                    "odsFilePath o mapper no inicialitzats. Cal cridar configureOdsPaths abans d'utilitzar carregaFh().");
        }
        List<FuncionariOdsDTO> dtos = mapper.readOdsToDto(new File(odsFilePath), FuncionariOdsDTO.class);
        for (FuncionariOdsDTO dto : dtos) {
            // Aquí s'aplicarà la lògica de processament i crida a l'API REST externa
            log.info("Processant DTO: " + dto.toString());
            processaFuncionarisLlocsIassignacions(dto);
        }
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
        String observacionsAlta = "Observacions alta: " + dto.observacionsAlta + "\n";
        String observacionsBaixa = "Observacions baixa: " + dto.observacionsBaixa + "\n";
        String observacions = ((dataAltaFh != null && !dataAltaFh.isEmpty()) ? observacionsAlta : "")
                + ((dataBaixaFh != null && !dataBaixaFh.isEmpty()) ? observacionsBaixa : "");
        String lang = "ca";

        // creació de FH
        // TODO: no se li pot posar una data alta passada
        NouFuncionariHabilitatDTO nouFh = new NouFuncionariHabilitatDTO(
                lang,
                Integer.parseInt(this.usuariId),
                dto.nom,
                dto.primerLlinatge,
                dto.segonLlinatge,
                funcionariLogicaEjb.getNumeroFhFromNumeric(Integer.parseInt(dto.numRfh)),
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

        // creació de llocs
        PersonalOamrTipus personalOamr = PersonalOamrTipus.SI;
        try {
            personalOamr = PersonalOamrTipus.fromString(dto.oamr);
        } catch (IllegalArgumentException iaex) {
        }
        String[] unitatLlocCodiDir3 = dto.dir3UnitatOrganica.split("v");
        String codiUnitatDir3 = unitatLlocCodiDir3[0].trim();
        Integer versioUnitatDir3 = unitatLlocCodiDir3.length > 1 ? (Integer.parseInt(codiUnitatDir3)) : null;
        log.info("Cercant unitat amb codiDir3: " + codiUnitatDir3 + " i versio: " + versioUnitatDir3);
        Unitat unitatLloc = unitatLogicaEjb.findByCodiDir3(codiUnitatDir3, versioUnitatDir3);
        log.info("Unitat trobada: " + (unitatLloc != null ? unitatLloc.getUnitatID() : null));
        if (unitatLloc == null) {
            String errorMsg = "No s'ha trobat la unitat amb codiDir " + dto.dir3UnitatOrganica;
            throw new I18NException(errorMsg);
        }
        NouLlocDTO nouLloc = new NouLlocDTO(lang, Integer.parseInt(usuariId), dto.codiLlocFeina, dto.expansio,
                dto.nomLlocFeina, personalOamr, Long.parseLong(entitatId), unitatLloc.getUnitatID(), dto.numCaiAlta,
                dto.habilitacio.split(","), dto.observacionsAlta, null, null);
        log.info("Creant lloc: " + nouLloc.toString());
        String respostaNouLloc = nouLloc(nouLloc);
        log.info("Resposta creació lloc: " + respostaNouLloc);

        log.info("Donant alta lloc: " + dto.codiLlocFeina + " - " + dto.expansio);
        String respostaDonarAltaLloc = donarAltaLloc(lang, usuariId, dto.codiLlocFeina, dto.expansio, dto.numCaiAlta);
        log.info("Resposta donar alta lloc: " + respostaDonarAltaLloc);

        // assignació de FH a lloc
        log.info("Assignant funcionari " + dto.nif + " a lloc " + dto.codiLlocFeina + " - " + dto.expansio);
        String respostaAssignarFuncionari = assignarFuncionari(lang, usuariId, dto.nif, dto.codiLlocFeina, dto.expansio,
                dto.numCaiAlta, observacions);
        log.info("Resposta assignació funcionari a lloc: " + respostaAssignarFuncionari);

        // TODO: no puc assignar una data baixa específica...
        if (dataBaixaFh != null && !dataBaixaFh.isEmpty()) {
            log.info("Donant baixa lloc: " + dto.codiLlocFeina + " - " + dto.expansio);
            String respostaDonarBaixaFh = donarBaixaFh(lang, usuariId, dto.nif, dto.numCaiAlta);
            log.info("Resposta donar baixa FH: " + respostaDonarBaixaFh);
            log.info("Donant baixa lloc: " + dto.codiLlocFeina + " - " + dto.expansio);
            String respostaDonarBaixaLloc = donarBaixaLloc(lang, usuariId, dto.codiLlocFeina, dto.expansio,
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
