package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.i18n.I18NException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.commons.utils.IdentificacioTipus;
import es.caib.rfhab.commons.utils.PersonalOamrTipus;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.model.entity.Unitat;

public class CarregadorMassiuFhIllocs {

    @EJB(mappedName = FuncionariLogicaService.JNDI_NAME)
    protected FuncionariLogicaService funcionariLogicaEjb;

    @EJB(mappedName = UnitatLogicaService.JNDI_NAME)
    protected UnitatLogicaService unitatLogicaEjb;

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
    public String nouFuncionariHabilitat(NouFuncionariHabilitatDTO dto) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/noufuncionarihabilitat";
        String json = objectMapper.writeValueAsString(dto);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona d'alta un funcionari via API REST.
     */
    public String donarAltaFh(String lang, String usuariId, String identificadorFh, String numCai) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/donaralta";
        String json = buildJson("language", lang, "usuariid", usuariId, "identificador", identificadorFh, "numcai",
                numCai);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona de baixa un funcionari via API REST.
     */
    public String donarBaixaFh(String lang, String usuariId, String identificadorFh, String numCai) throws Exception {
        String endpoint = apiUrl + "/secure/funcionari/donarbaixa";
        String json = buildJson("language", lang, "usuariid", usuariId, "identificador", identificadorFh, "numcai",
                numCai);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona d'alta un nou lloc via API REST.
     */
    public String nouLloc(NouLlocDTO dto)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/nou";
        String json = objectMapper.writeValueAsString(dto);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona d'alta un lloc via API REST.
     */
    public String donarAltaLloc(String lang, String usuariId, String codiLloc, String expansio, String numCai)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/donaralta";
        String json = buildJson("language", lang, "usuariid", usuariId, "codiLloc", codiLloc, "expansio", expansio,
                "numcai", numCai);
        return doPostJson(endpoint, json);
    }

    /**
     * Dona de baixa un lloc via API REST.
     */
    public String donarBaixaLloc(String lang, String usuariId, String codiLloc, String expansio, String numCai)
            throws Exception {
        String endpoint = apiUrl + "/secure/lloc/donarbaixa";
        String json = buildJson("language", lang, "usuariid", usuariId, "codiLloc", codiLloc, "expansio", expansio,
                "numcai", numCai);
        return doPostJson(endpoint, json);
    }

    /**
     * Assigna un funcionari a un lloc via API REST.
     */
    public String assignarFuncionari(String lang, String usuariId, String identificadorFh, String codiLloc,
            String expansio, String numeroCai,
            String observacions) throws Exception {
        String endpoint = apiUrl + "/secure/funcionarilloc/assignarfuncionari";
        String json = buildJson("identificadorfh", identificadorFh, "codilloc", codiLloc, "expansio", expansio,
                "numerocai", numeroCai, "observacions", observacions);
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
    private final String usuariId;
    private final String entitatId;
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
    public CarregadorMassiuFhIllocs(String odsFilePath, String mappingFilePath, Properties apiExternaProperties)
            throws Exception {
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
        // TODO:aquest hauria de fallar per ses majuscules a nes noms des parametres.
        nouFuncionariHabilitat(nouFh);
        donarAltaFh(lang, usuariId, dto.nif, dto.numCaiAlta);

        // creació de llocs
        PersonalOamrTipus personalOamr = PersonalOamrTipus.SI;
        try {
            personalOamr = PersonalOamrTipus.fromString(dto.oamr);
        } catch (IllegalArgumentException iaex) {
        }
        String[] unitatLlocCodiDir3 = dto.dir3UnitatOrganica.split("v");
        Unitat unitatLloc = unitatLogicaEjb.findByCodiDir3(unitatLlocCodiDir3[0].trim(),
                unitatLlocCodiDir3.length > 1 ? (Integer.parseInt(unitatLlocCodiDir3[0].trim())) : null);
        if (unitatLloc == null) {
            String errorMsg = "No s'ha trobat la unitat amb codiDir " + dto.dir3UnitatOrganica;
            throw new I18NException(errorMsg);
        }
        NouLlocDTO nouLloc = new NouLlocDTO(lang, Integer.parseInt(usuariId), dto.codiLlocFeina, dto.expansio,
                dto.nomLlocFeina, personalOamr, Long.parseLong(entitatId), unitatLloc.getUnitatID(), dto.numCaiAlta,
                dto.habilitacio.split(","), dto.observacionsAlta, null, null);
        nouLloc(nouLloc);
        donarAltaLloc(lang, usuariId, dto.codiLlocFeina, dto.expansio, dto.numCaiAlta);

        // assignació de FH a lloc
        assignarFuncionari(lang, usuariId, dto.nif, dto.codiLlocFeina, dto.expansio, dto.numCaiAlta, observacions);

        // TODO: no puc assignar una data baixa específica...
        if (dataBaixaFh != null && !dataBaixaFh.isEmpty()) {
            donarBaixaFh(lang, usuariId, dto.nif, dto.numCaiAlta);
            donarBaixaLloc(lang, usuariId, dto.codiLlocFeina, dto.expansio, dto.numCaiAlta);
        }
    }

    public String getOdsFilePath() {
        return odsFilePath;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
