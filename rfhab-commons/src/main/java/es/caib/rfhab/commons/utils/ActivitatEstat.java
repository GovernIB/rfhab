package es.caib.rfhab.commons.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivitatEstat {
    INDEFINIT(0),
    INICIAT(1),
    PAUSAT(2),
    ACABAT(3);

    private final Integer valor;

    private ActivitatEstat(Integer value) {
        this.valor = value;
    }

    @Override
    public String toString() {
        return this.name();
    }

    @JsonValue
    public Integer getValue() {
        return this.valor;
    }

    @JsonCreator
    public static ActivitatEstat fromString(String key) {
        for (ActivitatEstat val : ActivitatEstat.values()) {
            if (val.valor.toString().equals(key)) {
                return val;
            }
        }
        throw new IllegalArgumentException(key);
    }

    @JsonCreator
    public static ActivitatEstat fromValue(Integer value) {
        for (ActivitatEstat val : ActivitatEstat.values()) {
            if (val.valor.equals(value)) {
                return val;
            }
        }
        throw new IllegalArgumentException(value.toString());
    }
}
