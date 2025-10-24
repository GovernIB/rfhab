package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.commons.utils.IdentificacioTipus;

@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
public class NouFuncionariHabilitatDTO implements java.io.Serializable {

    @JsonIgnore
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Idioma en que s'han de retornar les dades (Només suportat 'ca' o 'es').
     * Exemple: "ca", "es". Opcional, per defecte "ca".
     */
    public String language;

    /**
     * Identificador de l'usuari que està realitzant el registre d'un nou FH.
     * Obligatori.
     * Exemple: 9999
     */
    @JsonProperty("usuariid")
    public Integer usuariId;

    /**
     * Nom del funcionari. Obligatori.
     */
    public String nom;

    /**
     * Primer llinatge. Obligatori.
     */
    public String llinatge1;

    /**
     * Segon llinatge. Opcional.
     */
    public String llinatge2;

    /**
     * Número del funcionari. Si és buit, el programa ho rellenarà automàticament
     * amb la seqüència més alta + 1. Opcional.
     * Exemple: "12345"
     */
    public String numero;

    /**
     * Tipus d'identificació del funcionari/ària. Obligatori.
     * Valors possibles: NIF, NIE, passaport, etc.
     */
    public IdentificacioTipus tipusIdentificador;

    /**
     * Identificador (NIF, NIE o passaport). Obligatori.
     */
    public String identificador;

    /**
     * Usuari. Obligatori.
     */
    public String username;

    /**
     * Correu electrònic. Obligatori. Ha de complir el patró de correu electrònic.
     */
    public String correu;

    /**
     * EntitatID. Obligatori.
     * Exemple: 1000
     */
    public Long entitatId;

    /**
     * Número CAI. Opcional.
     */
    @JsonProperty("numerocai")
    public String numeroCai;

    /**
     * Observacions
     */
    public String observacions;

    /**
     * Data de baixa. Opcional. Format ISO8601: "2025-08-31T06:15:00+00:00"
     */
    @JsonProperty("databaixa")
    public String dataBaixaStr;

    public NouFuncionariHabilitatDTO() {
        super();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    public NouFuncionariHabilitatDTO(String language, Integer usuariId, String nom, String llinatge1, String llinatge2,
            String numero, IdentificacioTipus tipusIdentificador, String identificador, String username, String correu,
            Long entitatId, String numeroCai, String observacions, String dataBaixaStr) {
        this();

        this.language = language;
        this.usuariId = usuariId;
        this.nom = nom;
        this.llinatge1 = llinatge1;
        this.llinatge2 = llinatge2;
        this.numero = numero;
        this.tipusIdentificador = tipusIdentificador;
        this.identificador = identificador;
        this.username = username;
        this.correu = correu;
        this.entitatId = entitatId;
        this.numeroCai = numeroCai;
        this.dataBaixaStr = dataBaixaStr;
    }

    @Override
    public String toString() {

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
