package es.caib.rfhab.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

  public static final String RFHAB_PROPERTY_BASE="es.caib.rfhab.";

    public static final String MAIL_SERVICE = "java:/es.caib.rfhab.mail";

    // TRUE ROLES
    public static final String RFH_ADMIN="RFH_ADMIN";
    public static final String RFH_SUPER="RFH_SUPER";
    public static final String RFH_USER="RFH_USER";
    public static final String RFH_WS="RFH_WS";

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
    
}
