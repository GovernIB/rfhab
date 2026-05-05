package es.caib.rfhab.back.utils;

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import es.caib.rfhab.commons.utils.Configuracio;
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
        log.info("refererUrl: " + refererUrl);
        session.removeAttribute(Constants.REFERER_SESSION_ATTRIBUTE);
        String actualReferer = request.getHeader("referer") != null ? request.getHeader("referer") : "";
        String redirectUrl = "";
        try {
            // Comprovar si no tenc referer o si el referer és igual a la pàgina on estic
            if (refererUrl == null || refererUrl.toString().isEmpty() || refererUrl.toString().equals("/")
                    || new URL(refererUrl.toString()).getPath().replaceFirst("/", "").isEmpty()
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

    public static String getUrlOrigin(String urlStr) throws MalformedURLException {
        URL url = new URL(urlStr);
        int port = url.getPort();
        String scheme = url.getProtocol();
        String host = url.getHost();
        if (port == -1) {
            port = "https".equals(scheme) ? 443 : 80;
        }
        boolean defaultPort = ("http".equals(scheme) && port == 80) || ("https".equals(scheme) && port == 443);
        return scheme + "://" + host + (defaultPort ? "" : ":" + port);
    }

    public static String getAbsoluteControllerBase(HttpServletRequest request,
            String webContext) {
        return getAbsoluteURLBase(request) + webContext;
    }

    public static String getAbsoluteURLBase(HttpServletRequest request) {
        return Configuracio.getBackUrl();
        // return getRequestOrigin(request) + request.getContextPath();
    }

    public static String getRequestOrigin(HttpServletRequest request) {
        int port = request.getServerPort();
        String scheme = request.getScheme();
        String host = request.getServerName();
        boolean defaultPort = ("http".equals(scheme) && port == 80) || ("https".equals(scheme) && port == 443);
        return scheme + "://" + host + (defaultPort ? "" : ":" + port);
    }

}
