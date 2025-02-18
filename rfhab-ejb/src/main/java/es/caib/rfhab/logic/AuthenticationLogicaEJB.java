package es.caib.rfhab.logic;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.persistence.EntitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.persistence.UsuariJPA;

/**
 * 
 * @author jagarcia
 *
 */
@Stateless(name = "AuthenticationLogicaEJB")
public class AuthenticationLogicaEJB implements AuthenticationLogicaService {

	protected Logger log = Logger.getLogger(AuthenticationLogicaEJB.class);

	@EJB(mappedName = UsuariLogicaService.JNDI_NAME)
	protected UsuariLogicaService usuariLogicaEjb;

	@EJB(mappedName = UsuariEntitatLogicaService.JNDI_NAME)
	protected UsuariEntitatLogicaService usuariEntitatLogicaEjb;

	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicaEjb;

	public static final String USERINFORMATION_PLUGIN_KEY = Constants.RFHAB_PROPERTY_BASE + "userinformationplugin";

	public static IUserInformationPlugin userInformationPlugin = null;

	@Override
	public UsuariJPA findByUsername(String username) throws I18NException {
		return usuariLogicaEjb.findByUsername(username);
	}

	@Override
	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException {
		return usuariLogicaEjb.crearUsuari(usuario);
	}

	@Override
	public UsuariEntitatJPA create(UsuariEntitatJPA usuariEntitat) throws I18NException {
		return (UsuariEntitatJPA) usuariEntitatLogicaEjb.create(usuariEntitat);
	}

	@Override
	public List<EntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException {
		return usuariEntitatLogicaEjb.findAllByUsuariIdWithEntitat(usuarioID);
	}

	@Override
	public Long findEntitatByDir3(String dir3) throws I18NException {
		return entitatLogicaEjb.findEntitatByDir3(dir3);
	}

	public static String getPropertiesAsString(Properties prop) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		prop.store(baos, "");
		return new String(baos.toByteArray());
	}

	@Override
	public IUserInformationPlugin getUserInformationPluginInstance() throws EJBException {

		if (userInformationPlugin == null) {

			Properties props = new Properties();

			try {
				String plantilla = getPropertiesAsString(Configuracio.getSystemAndFileProperties());

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("SP", Configuracio.getAppSystemProperties());

				String generat = TemplateEngine.processExpressionLanguageSquareBrackets(plantilla, map,
						new Locale(Configuracio.getDefaultLanguage()));

				props.load(new StringReader(generat));
			} catch (Exception e) {
				throw new EJBException(e);
			}

			String className = props.getProperty(USERINFORMATION_PLUGIN_KEY);
			Object pluginInstance;
			try {
				pluginInstance = PluginsManager.instancePluginByClassName(className, Constants.RFHAB_PROPERTY_BASE,
						props);

				if (pluginInstance == null) {
					log.error("PluginsManager.instancePluginByClassName(" + className + ") HA RETORNAT null ");
				}

			} catch (Exception th) {
				String msg = "Error no controlat instanciant Plugin de userInformation(" + className + "): "
						+ th.getMessage();
				log.error(msg, th);
				pluginInstance = null;
				throw new EJBException("plugin.donotinstantiateplugin", th);
			}

			if (pluginInstance == null) {
				throw new EJBException("plugin.donotinstantiateplugin");
			}
			userInformationPlugin = (IUserInformationPlugin) pluginInstance;
		}
		return userInformationPlugin;

	}

}