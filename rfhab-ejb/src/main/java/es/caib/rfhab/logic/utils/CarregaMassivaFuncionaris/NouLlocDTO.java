package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.rfhab.commons.utils.PersonalOamrTipus;

@JsonInclude(JsonInclude.Include.NON_NULL) // Inclou només camps no nuls al JSON
public class NouLlocDTO implements java.io.Serializable {
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
     * Codi del lloc. Obligatori.
     */
    @JsonProperty("codilloc")
    public String codiLloc;

    /**
     * Expansió del lloc. Opcional.
     */
    public String expansio;

    /**
     * Nom del lloc. Obligatori.
     */
    public String nom;

    /**
     * Personal OAMR. Obligatori.
     * Valors possibles: NO o SI.
     */
    @JsonProperty("personaloamr")
    public PersonalOamrTipus personalOamr;

    /**
     * Entitat a la qual pertany el lloc. Ha de ser una de les entitats associades a
     * l'usuari. Obligatori.
     * Exemple: 1000
     */
    @JsonProperty("entitatid")
    public Long entitatId;

    /**
     * Unitat orgànica a la qual pertany el lloc. Ha de pertànyer a l'entitat
     * sel·leccionada. Obligatori.
     * Exemple: 1000
     */
    @JsonProperty("unitatid")
    public Long unitatId;

    /**
     * Número CAI. Opcional.
     */
    @JsonProperty("numerocai")
    public String numeroCai;

    /**
     * Habilitacions associades al lloc (IDs). Opcional
     */
    public String[] habilitacions;

    /**
     * Observacions. Opcional
     */
    public String observacions;

    /**
     * Data d'alta. Opcional. Format ISO8601: "2025-08-31T06:15:00+00:00"
     */
    @JsonProperty("dataalta")
    public String dataAltaStr;

    /**
     * Data de baixa. Opcional. Format ISO8601: "2025-08-31T06:15:00+00:00"
     */
    @JsonProperty("databaixa")
    public String dataBaixaStr;

    public NouLlocDTO() {
        super();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    public NouLlocDTO(
            String language,
            Integer usuariId,
            String codiLloc,
            String expansio,
            String nom,
            PersonalOamrTipus personalOamr,
            Long entitatId,
            Long unitatId,
            String numeroCai,
            String[] habilitacions,
            String observacions,
            String dataAltaStr,
            String dataBaixaStr) {
        this();

        this.language = language;
        this.usuariId = usuariId;
        this.codiLloc = codiLloc;
        this.expansio = expansio;
        this.nom = nom;
        this.personalOamr = personalOamr;
        this.entitatId = entitatId;
        this.unitatId = unitatId;
        this.numeroCai = numeroCai;
        this.habilitacions = habilitacions;
        this.observacions = observacions;
        this.dataAltaStr = dataAltaStr;
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
