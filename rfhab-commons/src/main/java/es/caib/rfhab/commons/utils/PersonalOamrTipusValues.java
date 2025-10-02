package es.caib.rfhab.commons.utils;

public final class PersonalOamrTipusValues {
    static final String NO_NAME = "NO";
    static final String NO_VALUE_STR = "1";
    static final String NO_DESCRIPTION = "No";

    static final String SI_NAME = "SI";
    static final String SI_VALUE_STR = "2";
    static final String SI_DESCRIPTION = "Sí";

    private static final String DESCRIPTION_ALL_NAME_VALUE_SEPARATOR = " - ";
    private static final String DESCRIPTION_ALL_VALUES_SEPARATOR = "<br />&emsp;";

    public static final String DESCRIPTION_ALL_VALUES = NO_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR
            + NO_DESCRIPTION + DESCRIPTION_ALL_VALUES_SEPARATOR
            + SI_VALUE_STR + DESCRIPTION_ALL_NAME_VALUE_SEPARATOR + SI_DESCRIPTION;
}
