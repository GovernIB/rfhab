package es.caib.rfhab.commons.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.Hidden;

public enum RegistreActivitatTipus {
    // Quan fa una còpia autèntica des de DIGITALIB, en aquest cas, DIGITALIB hauria d'informar al RFHAB de que realitza una còpia autèntica.
    COPIA(RegistreActivitatTipusValues.COPIA_NAME, RegistreActivitatTipusValues.COPIA_VALUE_STR,
            RegistreActivitatTipusValues.COPIA_DESCRIPTION),
    // Quan inicia un tràmit com a funcionari habilitat. Event propi registre de funcionaris habilitats, que genera el registre.
    @Hidden COMPAREIX(RegistreActivitatTipusValues.COMPAREIX_NAME, RegistreActivitatTipusValues.COMPAREIX_VALUE_STR,
            RegistreActivitatTipusValues.COMPAREIX_DESCRIPTION),
    // Finalització de tràmit com a funcionari habilitat. L'event el registraria SISTRA2.
    TRAMIT(RegistreActivitatTipusValues.TRAMIT_NAME, RegistreActivitatTipusValues.TRAMIT_VALUE_STR,
            RegistreActivitatTipusValues.TRAMIT_DESCRIPTION);

    private final Integer valor;
    private final String nom;
    private final String descripcio;

    private RegistreActivitatTipus(String name, String value, String description) {
        this.nom = name;
        this.valor = Integer.valueOf(value);
        this.descripcio = description;
    }

    public String getDescripcio() {
        return this.descripcio;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    @JsonValue
    public Integer getValue() {
        return this.valor;
    }

    @JsonCreator
    public static RegistreActivitatTipus fromString(String key) {
        for (RegistreActivitatTipus val : RegistreActivitatTipus.values()) {
            if (val.valor.toString().equals(key)) {
                return val;
            }
        }
        throw new IllegalArgumentException(key);
    }

    @JsonCreator
    public static RegistreActivitatTipus fromValue(Integer value) {
        for (RegistreActivitatTipus val : RegistreActivitatTipus.values()) {
            if (val.valor.equals(value)) {
                return val;
            }
        }
        throw new IllegalArgumentException(value.toString());
    }
}
