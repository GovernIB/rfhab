package es.caib.rfhab.commons.utils;

/**
 *
 * @author jpou
 *
 */
public interface Constants {

    public static final String RFHAB_PROPERTY_BASE = "es.caib.rfhab.";
    public static final String SISTRAMIT_PROPERTY_BASE = "sistramit.";
    public static final String CARREGADORMASSIU_PROPERTY_BASE = "carregadormassiu.";

    public static final String MAIL_SERVICE = "java:/es.caib.rfhab.mail";

    // TRUE ROLES
    public static final String RFH_ADMIN = "RFH_ADMIN";
    public static final String RFH_SUPER = "RFH_SUPER";
    public static final String RFH_USER = "RFH_USER";
    public static final String RFH_WS = "RFH_WS";

    // VIRTUAL SECURITY ROLES
    public static final String ROLE_SUPER = "ROLE_SUPER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS = RFH_SUPER;
    public static final String ROLE_EJB_BASIC_ACCESS = RFH_ADMIN;
    public static final String ROLE_EJB_WS_ACCESS = RFH_WS;

    // TIPUS DE PLUGINS
    public static final String PLUGIN_ARXIU = "ARXIU";
    public static final String PLUGIN_SCAN = "SCANWEB";
    public static final String PLUGIN_DIR3 = "DIR3";
    public static final String PLUGIN_ROLSAC = "ROLSAC";

    // MENU ADMIN
    public static final String FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX = "FH_";
    public static final String FUNCIONARI_NUMERO_REGEX_NUMERICPART = "\\d\\d\\d\\d\\d\\d\\d";//SI SE CANVIA AQUEST, CANVIAR ES D'BAIX TAMBÉ
    public static final String FUNCIONARI_NUMERO_PLACEHOLDER_NUMERICPART = "XXXXXXX";//SI SE CANVIA AQUEST, CANVIAR ES D'ADALT TAMBE
    public static final String REGEX_FUNCIONARI_NUMERO_PATTERN = FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX
            + FUNCIONARI_NUMERO_REGEX_NUMERICPART;
    public static final String FUNCIONARI_NUMERO_PLACEHOLDER = FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX
            + FUNCIONARI_NUMERO_PLACEHOLDER_NUMERICPART;
    public static final String SQL_LIKE_ESCAPE_PATTERN = "^";
    public static final String SQL_FUNCIONARI_NUMERO_PATTERN = REGEX_FUNCIONARI_NUMERO_PATTERN
            .replaceAll("_", SQL_LIKE_ESCAPE_PATTERN + "_")
            .replaceAll("\\\\d", "_");

    public static final String LLOC_CODILLOCPROPI_PLACEHOLDER_PREFIX = "LF";
    public static final String LLOC_CODILLOCPROPI_PLACEHOLDER_NUMERICPART = "XXXXXX";
    public static final String LLOC_CODILLOCPROPI_PLACEHOLDER = LLOC_CODILLOCPROPI_PLACEHOLDER_PREFIX
            + LLOC_CODILLOCPROPI_PLACEHOLDER_NUMERICPART;
    public static final String SQL_LLOC_CODILLOCPROPI_PATTERN = LLOC_CODILLOCPROPI_PLACEHOLDER
            .replaceAll("_", SQL_LIKE_ESCAPE_PATTERN + "_")
            .replaceAll("X", "_");
    public static final String REGEX_LLOC_CODILLOCPROPI_PATTERN = LLOC_CODILLOCPROPI_PLACEHOLDER
            .replaceAll("X", "\\d");

    public static final String REFERER_SESSION_ATTRIBUTE = "referer";

    // PLUGIN ARXIU
    public static final int ARXIU_PLUGIN_REINTENTS_CREAR_DOCUMENT = 5;
    public static final int ARXIU_PLUGIN_REINTENTS_TANCAR_EXPEDIENT = 5;
}
