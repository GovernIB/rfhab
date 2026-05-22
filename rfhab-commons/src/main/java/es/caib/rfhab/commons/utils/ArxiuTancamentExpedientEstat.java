package es.caib.rfhab.commons.utils;

public enum ArxiuTancamentExpedientEstat {

    PENDENT(0),
    TANCAT(1),
    EXHAURIT_REINTENTS(2);

    private final int value;

    private ArxiuTancamentExpedientEstat(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
