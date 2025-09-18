package es.caib.rfhab.api.interna.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

/**
 *
 * @author anadal
 *
 */
@OpenAPIDefinition(servers = {
        @Server(url = "/rfhabapi/interna"),
        @Server(url = "http://localhost:8080/rfhabapi/interna"),
        @Server(url = "https://dev.caib.es/rfhabapi/interna"),
        @Server(url = "https://proves.caib.es/rfhabapi/interna"),
        @Server(url = "https://se.caib.es/rfhabapi/interna"),
        @Server(url = "https://www.caib.es/rfhabapi/interna")
})
@ApplicationPath("/")
public class JAXRSConfiguration extends Application {

    protected Logger log = org.apache.log4j.Logger.getLogger(this.getClass());

    /**
     * Les aplicacions JAX-RS necessiten un constructor buid.
     */
    public JAXRSConfiguration() {
    }

    /**
     * Podem introduir tasques a realitzar per la inicialització de l'API REST.
     */
    @PostConstruct
    private void init() {
        log.info("Iniciant API REST INTERNA de RFHab");
        // initTraduccions();
    }

    // private void initTraduccions() {

    //     // Sistema de Traduccions WEB
    //     try {
    //         ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
    //         String[] basenames = { "missatges", // /WEB-INF/classes/
    //                 "logicmissatges", "genapp", "portafib_genapp" };
    //         ms.setDefaultEncoding("UTF-8");
    //         ms.setBasenames(basenames);
    //         I18NUtils.setMessageSource(ms);
    //     } catch (Throwable th) {
    //         log.error("Error inicialitzant el sistema de traduccions web: " + th.getMessage(), th);
    //     }

    //     // Sistema de Traduccions LOGIC
    //     // TODO Moure a logic
    //     try {
    //         Class.forName(I18NLogicUtils.class.getName());
    //     } catch (Throwable th) {
    //         log.error("Error inicialitzant el sistema de traduccions logic: " + th.getMessage(), th);
    //     }

    // }
}
