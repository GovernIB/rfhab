package es.caib.rfhab.back.utils;

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import es.caib.rfhab.commons.utils.Constants;

/**
 * 
 * @author jpou
 * 
 */
public class UrlUtils {

    protected static final Logger log = Logger.getLogger(Utils.class);

    public static String getRefererRedirect(HttpServletRequest request, String defaultRedirect) {
        HttpSession session = request.getSession();
        Object refererUrl = session.getAttribute(Constants.REFERER_SESSION_ATTRIBUTE);
        session.removeAttribute(Constants.REFERER_SESSION_ATTRIBUTE);
        String redirectUrl = "";
        try {
            if (refererUrl == null || refererUrl.toString().isEmpty() || refererUrl.toString().equals("/")
                    || new URL(refererUrl.toString()).getPath().isEmpty()) {
                redirectUrl = defaultRedirect;
            }
        } catch (MalformedURLException e) {
        }

        if (redirectUrl.isEmpty()) {
            redirectUrl = "redirect:" + refererUrl;
        }
        
        log.info("Redirigint cap a " + redirectUrl);
        return redirectUrl;
    }
}
