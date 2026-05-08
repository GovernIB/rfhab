package es.caib.rfhab.api.interna.all.habilitacio;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import es.caib.rfhab.persistence.HabilitacioJPA;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Llista d'habilitacions de RFHab. En cas d'haver-hi la llista buida, revisar
 * el missatge d'error.
 *
 * @author jpou
 */
@Schema(name = "LlistaHabilitacionsPojo")
public class LlistaHabilitacionsPojo {

    private List<HabilitacioPojo> habilitacions;
    private String missatgeError;
    private String lang;

    @JsonbCreator
    public LlistaHabilitacionsPojo(@JsonbProperty("habilitacionsJpa") List<HabilitacioJPA> habilitacionsJpa,
            @JsonbProperty("llengua") String llengua) {
        Objects.requireNonNull(habilitacionsJpa, "habilitacionsJpa no pot ser null");
        Objects.requireNonNull(llengua, "llengua no pot ser null");
        this.lang = llengua;
        this.habilitacions = habilitacionsJpa.stream()
                .map(h -> HabilitacioPojo.fromHabilitacioJPA(h, this.lang))
                .collect(Collectors.toList());
    }

    public void setHabilitacionsFromJpa(List<HabilitacioJPA> habilitacionsJpa) {
        this.habilitacions = habilitacionsJpa.stream()
                .map(h -> HabilitacioPojo.fromHabilitacioJPA(h, this.lang))
                .collect(Collectors.toList());
    }

    public void setHabilitacions(List<HabilitacioPojo> habilitacions) {
        this.habilitacions = habilitacions;
    }

    public List<HabilitacioPojo> getHabilitacions() {
        return habilitacions;
    }

    public String getMissatgeError() {
        return missatgeError;
    }

    public void setMissatgeError(String missatgeError) {
        this.missatgeError = missatgeError;
    }

    @Override
    public String toString() {
        return "Habilitacions: " + habilitacions;
    }

}
