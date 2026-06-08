package es.caib.rfhab.commons.utils;

public final class IdentificacioTipusValues {
    static final String NIF_NAME = "NIF";
    static final String NIF_VALUE_STR = "1";
    static final String NIF_DESCRIPTION = "NIF";

    static final String NIE_NAME = "NIE";
    static final String NIE_VALUE_STR = "2";
    static final String NIE_DESCRIPTION = "NIE";

    // static final String PASSAPORT_NAME = "PASSAPORT";
    // static final String PASSAPORT_VALUE_STR = "3";
    // static final String PASSAPORT_DESCRIPTION = "Passaport";

    static final String ALTRES_NAME = "ALTRES";
    static final String ALTRES_VALUE_STR = "4";
    static final String ALTRES_DESCRIPTION = "Altres";


    private static final String DESCRIPTION_ALL_NAME_VALUE_SEPARATOR = " - ";
    private static final String DESCRIPTION_ALL_VALUES_SEPARATOR = "<br />&emsp;";

    public static final String DESCRIPTION_ALL_VALUES = 
        NIF_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + NIF_DESCRIPTION + DESCRIPTION_ALL_VALUES_SEPARATOR
        + NIE_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + NIE_DESCRIPTION + DESCRIPTION_ALL_VALUES_SEPARATOR
        /*+ PASSAPORT_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + PASSAPORT_DESCRIPTION + DESCRIPTION_ALL_VALUES_SEPARATOR*/
        + ALTRES_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + ALTRES_DESCRIPTION;
}
