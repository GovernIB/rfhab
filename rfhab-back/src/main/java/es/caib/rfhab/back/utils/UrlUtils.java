package es.caib.rfhab.back.utils;

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import es.caib.rfhab.commons.utils.Constants;

/**
 * 
 * @author jpou
 * 
 */
public class UrlUtils {

    protected static final Logger log = Logger.getLogger(UrlUtils.class);

    public static String getRefererRedirect(HttpServletRequest request, String defaultRedirect) {
        return getRefererRedirect(request, defaultRedirect, true);
    }

    public static String getRefererRedirect(HttpServletRequest request, String defaultRedirect,
            boolean checkSameReferer) {
        HttpSession session = request.getSession();
        Object refererUrl = session.getAttribute(Constants.REFERER_SESSION_ATTRIBUTE);
        session.removeAttribute(Constants.REFERER_SESSION_ATTRIBUTE);
        String actualReferer = request.getHeader("referer") != null ? request.getHeader("referer") : "";
        String redirectUrl = "";
        try {
            // Comprovar si no tenc referer o si el referer és igual a la pàgina on estic
            if (refererUrl == null || refererUrl.toString().isEmpty() || refererUrl.toString().equals("/")
                    || new URL(refererUrl.toString()).getPath().isEmpty()
                    || (checkSameReferer && StringUtils.strip(refererUrl.toString(), "/")
                            .equals(StringUtils.strip(actualReferer, "/")))) {
                redirectUrl = defaultRedirect;
            }
        } catch (MalformedURLException e) {
            log.error("Malformed URL: " + refererUrl, e);
        }

        if (redirectUrl.isEmpty()) {
            redirectUrl = "redirect:" + refererUrl;
        }

        log.info("Redirigint cap a " + redirectUrl);
        return redirectUrl;
    }
}
