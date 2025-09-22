package es.caib.rfhab.back.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import es.caib.rfhab.logic.FuncionariLogicaService;
import es.caib.rfhab.logic.UsuariLogicaService;
import es.caib.rfhab.persistence.FuncionariJPA;
import es.caib.rfhab.persistence.UsuariJPA;

public class UsuariIsFuncionariActiuInterceptor implements HandlerInterceptor {

    private FuncionariLogicaService funcionariLogicaEjb;
    private UsuariLogicaService usuariLogicaEjb;

    public UsuariIsFuncionariActiuInterceptor(FuncionariLogicaService funcionariEjb, UsuariLogicaService usuariEjb) {
        this.funcionariLogicaEjb = funcionariEjb;
        this.usuariLogicaEjb = usuariEjb;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LoginInfo loginInfo = LoginInfo.getInstance();
        UsuariJPA usuari = loginInfo.getUsuariPersona();
        String language = loginInfo.getLanguage();
        FuncionariJPA funcionari;
        try {
            String usuariNif = usuariLogicaEjb.checkIsActiuIteNif(usuari, language);
            funcionari = funcionariLogicaEjb.comprovarFuncionariActiuByNif(language, usuariNif, true);
        } catch (I18NException ex) {
            // HtmlUtils.saveMessageError(request, ex.getLocalizedMessage());
            HtmlUtils.saveMessageError(request, ex.getMessage());
            response.sendRedirect(request.getContextPath());
            // response.sendRedirect(request.getContextPath() + "/common/principal.html");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // No implementation needed for this interceptor
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // No implementation needed for this interceptor
    }
}
