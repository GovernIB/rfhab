package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.genapp.common.i18n.I18NException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.commons.utils.Configuracio;

public class CarregadorMassiuFhIllocs {
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
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", getBasicAuthHeader())
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String getBasicAuthHeader() {
        String auth = user + ":" + pass;
        return "Basic "
                + java.util.Base64.getEncoder().encodeToString(auth.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Dona d'alta un nou funcionari habilitat via API REST.
     */
    public String nouFuncionariHabilitat(FuncionariOdsDTO dto) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/noufuncionarihabilitat";
        String json = objectMapper.writeValueAsString(dto);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona d'alta un funcionari via API REST.
     */
    public String donarAlta(String identificadorFh, String dataAlta, String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/donaralta";
        String json = buildJson("identificadorFh", identificadorFh, "dataAlta", dataAlta, "observacions", observacions);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona de baixa un funcionari via API REST.
     */
    public String donarBaixa(String identificadorFh, String dataBaixa, String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/donarbaixa";
        String json = buildJson("identificadorFh", identificadorFh, "dataBaixa", dataBaixa, "observacions",
                observacions);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona d'alta un nou lloc via API REST.
     */
    public String nouLloc(String codiLloc, String nom, String expansio, String entitatId, String unitatId)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/nou";
        String json = buildJson("codiLloc", codiLloc, "nom", nom, "expansio", expansio, "entitatId", entitatId,
                "unitatId", unitatId);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona d'alta un lloc via API REST.
     */
    public String donarAltaLloc(String codiLloc, String dataAlta, String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/lloc/donaralta";
        String json = buildJson("codiLloc", codiLloc, "dataAlta", dataAlta, "observacions", observacions);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona de baixa un lloc via API REST.
     */
    public String donarBaixaLloc(String codiLloc, String dataBaixa, String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/lloc/donarbaixa";
        String json = buildJson("codiLloc", codiLloc, "dataBaixa", dataBaixa, "observacions", observacions);
        return doPostJson(endpoint, json);
    }

    /**
     * Assigna un funcionari a un lloc via API REST.
     */
    public String assignarFuncionari(String identificadorFh, String codiLloc, String expansio, String numeroCai,
            String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/assignarfuncionari";
        String json = buildJson("identificadorFh", identificadorFh, "codiLloc", codiLloc, "expansio", expansio,
                "numeroCai", numeroCai, "observacions", observacions);
        return doPostJson(endpoint, json);
    }

    /**
     * Treu un funcionari d'un lloc via API REST.
     */
    public String treureFuncionari(String identificadorFh, String codiLloc, String expansio, String numeroCai,
            String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/treurefuncionari";
        String json = buildJson("identificadorFh", identificadorFh, "codiLloc", codiLloc, "expansio", expansio,
                "numeroCai", numeroCai, "observacions", observacions);
        return doPostJson(endpoint, json);
    }

    /**
     * Treu tots els funcionaris d'un lloc via API REST.
     */
    public String treureTotsFuncionari(String codiLloc, String expansio, String numeroCai, String observacions)
            throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/treuretotsfuncionari";
        String json = buildJson("codiLloc", codiLloc, "expansio", expansio, "numeroCai", numeroCai, "observacions",
                observacions);
        return doPostJson(endpoint, json);
    }

    private final String odsFilePath;
    private final String apiUrl;
    private final String user;
    private final String pass;
    private final OdsToDtoMapper mapper;

    /**
     * Constructor amb la ruta del fitxer ODS i la URL de l'API externa.
     * 
     * @param odsFilePath     Ruta del fitxer ODS
     * @param mappingFilePath Ruta del fitxer de mapping .properties
     * @throws Exception Si hi ha problemes de lectura
     */
    public CarregadorMassiuFhIllocs(String odsFilePath, String mappingFilePath)
            throws Exception {
        this.odsFilePath = odsFilePath;
        this.apiUrl = Configuracio.getCarregadorMassiuEndpoint();
        this.user = Configuracio.getCarregadorMassiuUser();
        this.pass = Configuracio.getCarregadorMassiuPassword();
        if (this.apiUrl == null || this.user == null || this.pass == null) {
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
    public CarregadorMassiuFhIllocs(String odsFilePath, String mappingFilePath, Properties apiExternaProperties)
            throws Exception {
        this.odsFilePath = odsFilePath;
        this.apiUrl = Configuracio.getCarregadorMassiuEndpoint(apiExternaProperties);
        this.user = Configuracio.getCarregadorMassiuUser(apiExternaProperties);
        this.pass = Configuracio.getCarregadorMassiuPassword(apiExternaProperties);
        if (this.apiUrl == null || this.user == null || this.pass == null) {
            throw new I18NException(
                    "CarregadorMassiuFhIllocs configuration is incomplete. Please check the properties file.");
        }

        this.mapper = new OdsToDtoMapper(new File(mappingFilePath));
    }

    /**
     * Llegeix el fitxer ODS i processa cada línia com a FuncionariOdsDTO, aplicant
     * la lògica de càrrega.
     * La lògica de crida a l'API REST s'ha d'implementar més endavant.
     * 
     * @throws Exception Si hi ha problemes de lectura o processament
     */
    public void carregaFh() throws Exception {
        List<FuncionariOdsDTO> dtos = mapper.readOdsToDto(new File(odsFilePath), FuncionariOdsDTO.class);
        for (FuncionariOdsDTO dto : dtos) {
            // Aquí s'aplicarà la lògica de processament i crida a l'API REST externa
            processaDto(dto);
        }
    }

    /**
     * Processa un DTO i fa la crida a l'API REST externa (implementació pendent).
     * 
     * @param dto El DTO a processar
     */
    private void processaDto(FuncionariOdsDTO dto) {
        // TODO: Implementar la orquestació de crides a l'API REST externa

    }

    public String getOdsFilePath() {
        return odsFilePath;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
