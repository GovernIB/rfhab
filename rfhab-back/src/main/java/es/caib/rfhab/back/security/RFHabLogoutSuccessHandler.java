package es.caib.rfhab.back.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class RFHabLogoutSuccessHandler implements LogoutSuccessHandler {

	protected final Logger log = Logger.getLogger(getClass());

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		log.info("LogoutSuccessHandler.onLogoutSuccess");

		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			request.logout();
			removeCookies(request, response);
			log.info("cookies esborrades a LogoutSuccessHandler.onLogoutSuccess");

		} catch (Exception e) {
			log.error("Error alhora de fer logout:" + e.getMessage());
		}

		// response.sendRedirect("/rfhab/");
		// aplicam “hard logout BASIC”: després del logout retornam 401 amb
		// WWW-Authenticate per forçar que el navegador no reautentiqui automàticament.
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("WWW-Authenticate", "Basic realm=\"Govern de les Illes Balears\", charset=\"UTF-8\"");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write("Sessio tancada. Torna a autenticar-te per continuar.");
	}

	public void removeCookies(HttpServletRequest request, HttpServletResponse response) {
		// Només per Jboss
		// Es itera sobre totes les cookies
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return;
		}

		for (Cookie c : cookies) {
			// Es sobre escriu el valor de cada cookie a NULL
			Cookie ck = new Cookie(c.getName(), null);
			ck.setPath(request.getContextPath());
			ck.setMaxAge(0);
			response.addCookie(ck);

			if (!"/".equals(request.getContextPath())) {
				Cookie rootPathCookie = new Cookie(c.getName(), null);
				rootPathCookie.setPath("/");
				rootPathCookie.setMaxAge(0);
				response.addCookie(rootPathCookie);
			}
		}
	}

}
