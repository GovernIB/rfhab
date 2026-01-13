package es.caib.rfhab.commons.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonalOamrTipus {
    NO(PersonalOamrTipusValues.NO_NAME, PersonalOamrTipusValues.NO_VALUE_STR,
            PersonalOamrTipusValues.NO_DESCRIPTION),
    SI(PersonalOamrTipusValues.SI_NAME, PersonalOamrTipusValues.SI_VALUE_STR,
            PersonalOamrTipusValues.SI_DESCRIPTION);

    private final Integer valor;
    private final String nom;
    private final String descripcio;

    private PersonalOamrTipus(String name, String value, String description) {
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
    public static PersonalOamrTipus fromName(String name) {
        for (PersonalOamrTipus val : PersonalOamrTipus.values()) {
            if (val.nom.equals(name)) {
                return val;
            }
        }
        throw new IllegalArgumentException(name);
    }

    @JsonCreator
    public static PersonalOamrTipus fromString(String key) {
        for (PersonalOamrTipus val : PersonalOamrTipus.values()) {
            if (val.valor.toString().equals(key)) {
                return val;
            }
        }
        throw new IllegalArgumentException(key);
    }

    @JsonCreator
    public static PersonalOamrTipus fromValue(Integer value) {
        for (PersonalOamrTipus val : PersonalOamrTipus.values()) {
            if (val.valor.equals(value)) {
                return val;
            }
        }
        throw new IllegalArgumentException(value.toString());
    }
}
