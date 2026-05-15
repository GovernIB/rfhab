package es.caib.rfhab.back.security;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.logic.AuthenticationLogicaService;
import es.caib.rfhab.logic.IdiomaLogicaService;
import es.caib.rfhab.logic.UnitatLogicaService;
import es.caib.rfhab.logic.utils.EjbManager;
import es.caib.rfhab.persistence.EntitatJPA;
import es.caib.rfhab.persistence.IdiomaJPA;
import es.caib.rfhab.persistence.UnitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.persistence.UsuariJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author jpou
 * 
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    protected final Logger log = Logger.getLogger(getClass());

    public static final String LOGIN_PLUGIN_KEY = Constants.RFHAB_PROPERTY_BASE + "userinformationplugin";

    public static IUserInformationPlugin loginPlugin = null;

    protected AuthenticationLogicaService authenticationLogicaEjb;

    protected IdiomaLogicaService idiomaLogicaEjb;

    protected UnitatLogicaService unitatEjb;

    @Override
    public synchronized void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

        try {
            authenticationLogicaEjb = EjbManager.getAuthenticationLogicaEJB();
            idiomaLogicaEjb = EjbManager.getIdiomaLogicaEJB();
            unitatEjb = EjbManager.getUnitatEJB();
        } catch (I18NException e) {
            log.error(e.getMessage());
        }

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication au = sc.getAuthentication();
        String entitatCodi;

        if (au == null) {
            // TODO traduccio
            throw new LoginException("NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO");
        }

        User user = (User) au.getPrincipal();

        String username = user.getUsername();
        log.info(" =================================================================");
        log.info(" ============ Login Usuari: " + username);

        try {

            LoginInfo loginInfo = LoginInfo.getInstance();

            if (!username.equals(loginInfo.getUsuariPersona().getUsername())) {
                entitatCodi = null; // (loginInfo.getEntitatID() != null) ? loginInfo.getEntitatID() : null;
            }

        } catch (Throwable e) {
            // OK
            // Si és llança un error significa que ja hi ha una sessió oberta
            log.info("Sessió ja oberta");
        }

        // Cercam si té el ROLE_USER o ROLE_ADMIN
        Collection<GrantedAuthority> realAuthorities = user.getAuthorities();
        boolean containsRoleUser = false;
        boolean containsRoleAdmin = false;
        boolean containsRoleSuper = false;

        if (realAuthorities.size() == 0) {
            log.info("No hi ha ROLES per a l'usuari: " + username);

        } else {
            for (GrantedAuthority grantedAuthority : realAuthorities) {
                String rol = grantedAuthority.getAuthority();

                log.info("Rol REAL : " + rol);

                if (Constants.ROLE_USER.equals(rol)) {
                    containsRoleUser = true;
                }
                if (Constants.ROLE_ADMIN.equals(rol)) {
                    containsRoleAdmin = true;
                }
                if (Constants.ROLE_SUPER.equals(rol)) {
                    containsRoleSuper = true;
                }
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("containsRoleUser: " + containsRoleUser);
            log.debug("containsRoleAdmin: " + containsRoleAdmin);
            log.debug("containsRoleSuper: " + containsRoleSuper);
        }

        // Rol actiu
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        if (containsRoleSuper) {
            WebUtils.setSessionAttribute(sra.getRequest(), "rolBack", Constants.ROLE_SUPER);
            log.info("Setejat rolBack a SUPER");
        } else if (containsRoleAdmin) {
            WebUtils.setSessionAttribute(sra.getRequest(), "rolBack", Constants.ROLE_ADMIN);
            log.info("Setejat rolBack a ADMIN");
        } else if (containsRoleUser) {
            WebUtils.setSessionAttribute(sra.getRequest(), "rolBack", Constants.ROLE_USER);
            log.info("Setejat rolBack a USER");
        } else {
            WebUtils.setSessionAttribute(sra.getRequest(), "rolBack", null);
            log.info("Setejat rolBack a NULL");
        }

        UsuariJPA usuariPersona;
        Long entitatIDActual = null;
        Map<Long, EntitatJPA> entitats = new HashMap<Long, EntitatJPA>();

        try {
            usuariPersona = authenticationLogicaEjb.findByUsername(username);
            if (usuariPersona != null) {
                Set<UsuariEntitatJPA> usuarisEntitat = usuariPersona.getUsuariEntitats();
                for (UsuariEntitatJPA usuariEntitatJPA : usuarisEntitat) {
                    if (usuariEntitatJPA != null) {
                        EntitatJPA entitat = usuariEntitatJPA.getEntitat();
                        log.info("entitat => " + entitat.getEntitatID() + " ## actiu: " + entitat.isActiu());
                        log.info("usuariEntitat => " + usuariEntitatJPA.getUsuariEntitatID() + " ## actiu: "
                                + usuariEntitatJPA.isActiu());
                        if (entitat.isActiu() && usuariEntitatJPA.isActiu()) {
                            entitats.put(usuariEntitatJPA.getEntitatID(), entitat);
                        }
                    }
                }

                List<EntitatJPA> usuarisEntitatList = new java.util.ArrayList<>(entitats.values());
                // List<EntitatJPA> usuarisEntitatList =
                // authenticationLogicaEjb.findAllByUsuariIdWithEntitat(usuariPersona.getUsuariID());

                UnitatJPA unitat = null;
                for (EntitatJPA ue : usuarisEntitatList) {
                    log.info("UsuariEntitatJPAList => " + ue.getEntitatID() + " - codi: " + ue.getUnitatID());

                    unitat = unitatEjb.findByPrimaryKey(ue.getUnitatID());
                    // log.info("unidad dir3 => " + unitat.getCodi());
                    log.info("unidad dir3 => " + (unitat != null ? unitat.getCodi() : null));

                    if (unitat != null) {
                        entitatCodi = unitat.getCodi();
                        log.info("Usuari pertany a l'entitat amb codi: " + entitatCodi);
                        if (ue.isActiu()) {
                            entitatIDActual = ue.getEntitatID();
                            log.info(">>>>>> Entitat predeterminada " + entitatIDActual);
                            break;
                        } else {
                            log.info("Entitat " + entitatCodi + " no està activa");
                        }
                    }
                    // TODO:llançar excepció si no l'hem trobada??
                }
            }
        } catch (I18NException e) {
            log.error("Error cercant usuari a la base de dades", e);
            throw new LoginException("Error cercant usuari a la base de dades", e);
        }

        UserInfo info = null;
        boolean necesitaConfigurar = false;
        UsuariJPA persona = null;
        String codiDir3Actual = null;

        if (usuariPersona == null) {

            log.info(" La persona amb username  '" + username + "' no esta registrada !!!!");

            try {
                IUserInformationPlugin plugin = getUserInformationPluginInstance();
                info = plugin.getUserInfoByUserName(username);

                necesitaConfigurar = true;

            } catch (Throwable e) {
                String msg;
                if (e instanceof I18NException) {
                    msg = I18NUtils.getMessage((I18NException) e);
                } else if (e instanceof I18NValidationException) {
                    msg = I18NUtils.getMessage((I18NValidationException) e);
                } else {
                    msg = e.getMessage();
                }
                msg = "Error llegint informació del plugin de UserInformation: " + msg;
                log.error(msg, e);
            }

            {
                // convertim la informació del plugin userinformation a usuariJPA
                String nom, llinatge1, llinatge2;
                {
                    nom = info.getName();

                    llinatge1 = (info.getSurname1() != null) ? info.getSurname1() : null;
                    llinatge2 = (info.getSurname2() != null) ? info.getSurname2() : null;

                    if (nom == null || llinatge1 == null) {
                        String full = info.getFullName();

                        if (full == null) {
                            nom = "Unknown";
                            llinatge1 = "Unknown";
                        } else {

                            if (nom == null) {
                                int pos = full.indexOf(' ');
                                if (pos == -1) {
                                    nom = full;
                                } else {
                                    nom = full.substring(0, pos);
                                }
                            }

                            if (llinatge1 == null) {
                                int pos = full.indexOf(' ') + 1;
                                if (pos == -1) {
                                    llinatge1 = full;
                                } else {
                                    llinatge1 = full.substring(pos + 1);
                                }
                            }
                        }
                    }
                }

                persona = new UsuariJPA();
                persona.setCorreu(info.getEmail() == null || info.getEmail().trim().isEmpty() ? "noemail@" : info.getEmail());

                persona.setNom(nom);
                persona.setLlinatge1(llinatge1);

                if (llinatge2 != null && llinatge2.trim().length() != 0)
                    persona.setLlinatge2(llinatge2);

                persona.setUsername(info.getUsername());

                String nif = info.getAdministrationID();
                if (nif != null && nif.trim().length() != 0) {
                    persona.setNif(nif.trim().replaceAll("\\s+", "").toUpperCase());
                }

                try {
                    String defaultLanguage = (Configuracio.getDefaultLanguage() != null)
                            ? Configuracio.getDefaultLanguage()
                            : "ca";

                    if (idiomaLogicaEjb == null)
                        idiomaLogicaEjb = EjbManager.getIdiomaLogicaEJB();

                    IdiomaJPA idiomaUsuari = idiomaLogicaEjb.findByCodi(defaultLanguage);

                    if (idiomaUsuari != null) {
                        log.info("IdiomabyCodi => " + idiomaUsuari.getIdiomaID());
                        persona.setIdioma(idiomaUsuari);
                        persona.setIdiomaID(idiomaUsuari.getIdiomaID());
                    } else {
                        persona.setIdiomaID(defaultLanguage);
                        log.info("IdiomabyCodi => null");
                    }

                } catch (Exception e) {
                    log.error("Error cercant idioma per defecte", e);
                }

                persona.setDataCreacio(Timestamp.valueOf(LocalDateTime.now()));

                log.info("============  UsuariJPA persona ======================");
                log.info("Nom: " + persona.getNom());
                log.info("Llinatge1: " + persona.getLlinatge1());
                log.info("Llinatge2: " + persona.getLlinatge2());
                log.info("Username: " + persona.getUsername());
                log.info("Correu: " + persona.getCorreu());
                log.info("NIF: " + persona.getNif());
                log.info("Idioma: " + persona.getIdiomaID());
                log.info("DataCreacio: " + persona.getDataCreacio());
                log.info("=======================================================");

            }

            UsuariEntitatJPA usuariEntitat = null;

            {
                if (containsRoleAdmin) {
                    String defaultEntityCode = Configuracio.getDefaultEntitat();
                    log.info("Entitat per defecte: " + defaultEntityCode);

                    codiDir3Actual = defaultEntityCode;

                    if (defaultEntityCode != null && defaultEntityCode.trim().length() != 0) {

                        try {
                            // Buscamos la ID de la entidad por el código DIR3
                            Long defaultEntity = authenticationLogicaEjb.findEntitatByDir3(defaultEntityCode);
                            log.info("Default Entitat ID => " + defaultEntity);

                            if (defaultEntity != null && defaultEntity > 0) {
                                usuariEntitat = new UsuariEntitatJPA();
                                usuariEntitat.setEntitatID(defaultEntity);
                                usuariEntitat.setActiu(true);
                            } else {
                                log.error("No s'ha trobat l'entitat per defecte");
                            }
                        } catch (Exception e) {
                            log.error("Error cercant la entitat per defecte", e);
                        }
                    }

                }
            }

            necesitaConfigurar = true;

            try {
                persona = authenticationLogicaEjb.crearUsuari(persona);

                if (usuariEntitat == null) {
                    usuariPersona = persona;
                } else {
                    usuariEntitat.setUsuariID(persona.getUsuariID());
                    usuariEntitat = authenticationLogicaEjb.create(usuariEntitat);
                    usuariPersona = persona;

                    authenticationLogicaEjb.updateUsuariActiu(usuariPersona, true);
                }

            } catch (I18NException e) {
                log.error("Error creant usuari", e);
                throw new LoginException("Error creant usuari", e);
            }

            log.info("necesitaConfigurarUsuari = " + necesitaConfigurar);
        }

        if(!necesitaConfigurar){
            if (usuariPersona.getUsuariEntitats().size() < 1) {
                I18NTranslation translation = new I18NTranslation("error.senseentitat", username);
                log.error("error senseentitat => " + I18NUtils.tradueix(translation));
                I18NTranslation translation2 = new I18NTranslation("error.nomesaccessuper", username);
                log.error("error senseentitat => " + I18NUtils.tradueix(translation2));
                // Si l'usuari no té el rol SUPER i no te associada cap entitat
                if (!containsRoleSuper) {
                    throw new LoginException(I18NUtils.tradueix(translation));
                }
            }
    
            if (entitatIDActual == null) {
                I18NTranslation translation = new I18NTranslation("error.senseentitatactiva", username);
                log.error("error senseentitatactiva => " + I18NUtils.tradueix(translation));
                I18NTranslation translation2 = new I18NTranslation("error.nomesaccessuper", username);
                log.error("error senseentitatactiva => " + I18NUtils.tradueix(translation2));
                // Si l'usuari no té el rol SUPER i no te associada cap entitat activa
                if (!containsRoleSuper) {
                    throw new LoginException(I18NUtils.tradueix(translation));
                }
            }
        }

        log.info("user => " + user);
        log.info("entitatIDActual => " + entitatIDActual);
        log.info("necesitaConfigurar => " + necesitaConfigurar);

        LoginInfo loginInfo = new LoginInfo(user, new HashSet<GrantedAuthority>(realAuthorities), username,
                usuariPersona, entitatIDActual, codiDir3Actual, necesitaConfigurar);

        loginInfo.setEntitats(entitats);

        log.info("--------------------- LOGININFO AUTHENTICATIONSUCCESSLISTENER----------------------");
        log.info("LoginInfo => " + loginInfo);
        log.info("-------------------------------------------------------");

        // and set the authentication of the current Session context
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

        log.info(" =================================================================");
    }

    public static IUserInformationPlugin getUserInformationPluginInstance() throws I18NException {
        if (loginPlugin == null) {
            final String propertyPlugin = LOGIN_PLUGIN_KEY;

            Properties propTmp = Configuracio.getSystemAndFileProperties();

            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
                    Constants.RFHAB_PROPERTY_BASE, propTmp);

            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiateplugin.userinfo");
            }
            loginPlugin = (IUserInformationPlugin) pluginInstance;
        }
        return loginPlugin;
    }

    public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR = new Comparator<GrantedAuthority>() {
        @Override
        public int compare(GrantedAuthority o1, GrantedAuthority o2) {
            return -o1.getAuthority().compareTo(o2.getAuthority());
        }
    };

}
