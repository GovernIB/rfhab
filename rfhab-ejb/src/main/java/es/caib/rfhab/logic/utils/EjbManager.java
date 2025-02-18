package es.caib.rfhab.logic.utils;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UnitatService;
import es.caib.rfhab.logic.AuthenticationLogicaService;
import es.caib.rfhab.logic.IdiomaLogicaService;

/**
 * @author jagarcia
 */


public final class EjbManager {
	
	protected static final Logger log = Logger.getLogger(EjbManager.class);
	
	protected static AuthenticationLogicaService authenticationLogicaEjb;
	protected static IdiomaLogicaService idiomaLogicaEjb;
	protected static UnitatService unitatEjb;
	
	public static AuthenticationLogicaService getAuthenticationLogicaEJB() throws I18NException {

		if (authenticationLogicaEjb == null) {
			try {
				authenticationLogicaEjb = (AuthenticationLogicaService) new InitialContext()
						.lookup(AuthenticationLogicaService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "AuthenticationLogicaService");
			}
		}
		return authenticationLogicaEjb;
	}
	
	public static IdiomaLogicaService getIdiomaLogicaEJB() throws I18NException {

		if (idiomaLogicaEjb == null) {
			try {
				idiomaLogicaEjb = (IdiomaLogicaService) new InitialContext().lookup(IdiomaLogicaService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "IdiomaLogicaService");
			}
		}
		return idiomaLogicaEjb;
	}
	
	public static UnitatService getUnitatEJB() throws I18NException {

		if (unitatEjb == null) {
			try {
				unitatEjb = (UnitatService) new InitialContext().lookup(UnitatService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UnitatService");
			}
		}
		return unitatEjb;
	}
	
	
	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

}
