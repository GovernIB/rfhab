package es.caib.rfhab.commons.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IdentificacioTipus {
    NIF(IdentificacioTipusValues.NIF_NAME, IdentificacioTipusValues.NIF_VALUE_STR,
            IdentificacioTipusValues.NIF_DESCRIPTION),
    NIE(IdentificacioTipusValues.NIE_NAME, IdentificacioTipusValues.NIE_VALUE_STR,
            IdentificacioTipusValues.NIE_DESCRIPTION),
    PASSAPORT(IdentificacioTipusValues.PASSAPORT_NAME, IdentificacioTipusValues.PASSAPORT_VALUE_STR,
            IdentificacioTipusValues.PASSAPORT_DESCRIPTION),
    ALTRES(IdentificacioTipusValues.ALTRES_NAME, IdentificacioTipusValues.ALTRES_VALUE_STR,
            IdentificacioTipusValues.ALTRES_DESCRIPTION);

    private final Integer valor;
    private final String nom;
    private final String descripcio;

    private IdentificacioTipus(String name, String value, String description) {
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
    public static IdentificacioTipus fromString(String key) {
        for (IdentificacioTipus val : IdentificacioTipus.values()) {
            if (val.valor.toString().equals(key)) {
                return val;
            }
        }
        throw new IllegalArgumentException(key);
    }

    @JsonCreator
    public static IdentificacioTipus fromValue(Integer value) {
        for (IdentificacioTipus val : IdentificacioTipus.values()) {
            if (val.valor.equals(value)) {
                return val;
            }
        }
        throw new IllegalArgumentException(value.toString());
    }
}
