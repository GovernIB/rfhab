package es.caib.rfhab.commons.utils;

/**
 *
 * @author jpou
 *
 */
public interface Constants {

        public static final String RFHAB_PROPERTY_BASE = "es.caib.rfhab.";
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
        public static final String FUNCIONARI_NUMERO_REGEX_NUMERICPART = "\\d\\d\\d\\d\\d\\d\\d";// SI ES CANVIA AQUEST,
                                                                                                 // CANVIAR EL D'BAIX
                                                                                                 // TAMBÉ
        public static final String FUNCIONARI_NUMERO_PLACEHOLDER_NUMERICPART = "XXXXXXX";// SI ES CANVIA AQUEST, CANVIAR
                                                                                         // EL D'ADALT TAMBE
        public static final String REGEX_FUNCIONARI_NUMERO_PATTERN = FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX
                        + FUNCIONARI_NUMERO_REGEX_NUMERICPART;
        public static final String FUNCIONARI_NUMERO_PLACEHOLDER = FUNCIONARI_NUMERO_PLACEHOLDER_PREFIX
                        + FUNCIONARI_NUMERO_PLACEHOLDER_NUMERICPART;
        public static final String CORREU_PLACEHOLDER = "xxx@yyy.zz";
        public static final String SQL_LIKE_ESCAPE_PATTERN = "^";
        public static final String SQL_FUNCIONARI_NUMERO_PATTERN = REGEX_FUNCIONARI_NUMERO_PATTERN
                        .replaceAll("_", SQL_LIKE_ESCAPE_PATTERN + "_")
                        .replaceAll("\\\\d", "_");

        public static final String LLOC_CODILLOC_PLACEHOLDER = "PFH_XXXXXXX";

        public static final String LLOC_CODILLOCPROPI_PLACEHOLDER_PREFIX = "LF";
        public static final String LLOC_CODILLOCPROPI_PLACEHOLDER_NUMERICPART = "XXXXXX";
        public static final String LLOC_CODILLOCPROPI_PLACEHOLDER = LLOC_CODILLOCPROPI_PLACEHOLDER_PREFIX
                        + LLOC_CODILLOCPROPI_PLACEHOLDER_NUMERICPART;
        public static final String SQL_LLOC_CODILLOCPROPI_PATTERN = LLOC_CODILLOCPROPI_PLACEHOLDER
                        .replaceAll("_", SQL_LIKE_ESCAPE_PATTERN + "_")
                        .replaceAll("X", "_");
        public static final String REGEX_LLOC_CODILLOCPROPI_PATTERN = LLOC_CODILLOCPROPI_PLACEHOLDER
                        .replaceAll("X", "\\d");

        public static final String CODILLOC_FAKE_BUIT = "#####55555#####";

        public static final String REFERER_SESSION_ATTRIBUTE = "referer";

        public static final String ATTR_FILTRE_OAMR_VALOR_PER_DEFECTE = "llocs_filtreOamrValorPerDefecte";
        public static final String ATTR_FILTRE_ACTIUS_VALOR_PER_DEFECTE = "llocs_filtreActiusValorPerDefecte";
        public static final String ATTR_FILTRE_UNITATSUPERIOR_VALOR_PER_DEFECTE = "llocs_filtreUnitatSuperiorValorPerDefecte";
        public static final String ATTR_FILTRE_UNITATSO_VALOR_PER_DEFECTE = "llocs_filtreUnitatsoValorPerDefecte";
        public static final String ATTR_FILTRE_FOAMR_VALOR_PER_DEFECTE = "funcionaris_filtreOamrValorPerDefecte";
        public static final String ATTR_FILTRE_FACTIUS_VALOR_PER_DEFECTE = "funcionaris_filtreActiusValorPerDefecte";
        public static final String ATTR_FILTRE_FASSIGNATS_VALOR_PER_DEFECTE = "funcionaris_filtreAssignatsValorPerDefecte";

        public static final String NOM_ATTR_FILTRE_UNITATS = "unitatsFiltreCerca";
        public static final String NOM_ATTR_FILTRE_UNITATS_SUPERIORS = "unitatsSuperiorsCerca";

        // PLUGIN ARXIU
        public static final int ARXIU_PLUGIN_REINTENTS_CREAR_DOCUMENT = 5;
        public static final int ARXIU_PLUGIN_REINTENTS_TANCAR_EXPEDIENT = 5;

        public static final String NUMEROCAI_BUIT = " ";
}
