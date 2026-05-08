package es.caib.rfhab.api.interna.all.habilitacio;

import java.util.Objects;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import es.caib.rfhab.persistence.HabilitacioJPA;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Habilitació de RFHab.
 *
 * @author jpou
 */
@Schema(name = "HabilitacioPojo")
public class HabilitacioPojo {

    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String codi;

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    @JsonbCreator
    public HabilitacioPojo(@JsonbProperty("nom") String nom, @JsonbProperty("codi") String codi) {
        Objects.requireNonNull(nom, "nom no pot ser null");
        Objects.requireNonNull(codi, "codi no pot ser null");
        this.nom = nom;
        this.codi = codi;
    }

    @Override
    public String toString() {
        return nom + "(" + codi + ")";
    }

    public static HabilitacioPojo fromHabilitacioJPA(HabilitacioJPA habilitacio, String llengua) {
        return new HabilitacioPojo(habilitacio.getNom().getTraduccio(llengua).getValor(), habilitacio.getCodi());
    }
}
