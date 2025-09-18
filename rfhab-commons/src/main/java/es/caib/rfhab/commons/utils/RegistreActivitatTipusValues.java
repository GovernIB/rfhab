package es.caib.rfhab.commons.utils;

public final class RegistreActivitatTipusValues {
    static final String COPIA_NAME = "COPIA";
    static final String COPIA_VALUE_STR = "1";
    static final String COPIA_DESCRIPTION = "Còpia autèntica";

    static final String COMPAREIX_NAME = "COMPAREIX";
    static final String COMPAREIX_VALUE_STR = "2";
    static final String COMPAREIX_DESCRIPTION = "Inici tràmit";

    static final String TRAMIT_NAME = "TRAMIT";
    static final String TRAMIT_VALUE_STR = "3";
    static final String TRAMIT_DESCRIPTION = "Final tràmit";


    private static final String DESCRIPTION_ALL_NAME_VALUE_SEPARATOR = " - ";
    private static final String DESCRIPTION_ALL_VALUES_SEPARATOR = "<br />&emsp;";

    public static final String DESCRIPTION_ALL_VALUES = 
        COPIA_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + COPIA_DESCRIPTION + DESCRIPTION_ALL_VALUES_SEPARATOR
        + COMPAREIX_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + COMPAREIX_DESCRIPTION + DESCRIPTION_ALL_VALUES_SEPARATOR
        + TRAMIT_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + TRAMIT_DESCRIPTION;
}
