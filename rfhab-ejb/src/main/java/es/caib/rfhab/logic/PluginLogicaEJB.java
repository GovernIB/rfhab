package es.caib.rfhab.logic;

import java.io.StringReader;
import java.util.List;
import java.util.Properties;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.PluginEJB;
import es.caib.rfhab.ejb.PluginService;
import es.caib.rfhab.model.entity.Plugin;
import es.caib.rfhab.model.fields.PluginFields;

/*
 * author jagarcia
 */

@Stateless
public class PluginLogicaEJB extends PluginEJB implements PluginLogicaService {

	
	@EJB(mappedName = PluginService.JNDI_NAME)
	private PluginService pluginEjb;
	
	
	@Override
	@PermitAll
	public List<Plugin> findByEntitatTipus(Long idEntitat, String tipusPlugin) throws I18NException {
		
		Where w = Where.AND( PluginFields.ENTITATID.equal(idEntitat)  , PluginFields.TIPUS.equal(tipusPlugin));
		return pluginEjb.select(w);
		
	}
	
	@Override
	@PermitAll
	public Object getPlugin(Long idEntitat, String tipusPlugin) throws I18NException {

		try {

			List<Plugin> plugins = findByEntitatTipus(idEntitat, tipusPlugin);

			if (plugins.size() > 0) {
				return carregarPlugin(plugins.get(0));
			}

		} catch (Exception e) {
			throw new I18NException(e, "error.desconegut", e.getMessage());
		}

		return null;
	}

	@Override
	@PermitAll
	public Properties getPropertiesPlugin(Long idEntitat, String tipusPlugin) throws I18NException {

		try {

			List<Plugin> plugins = findByEntitatTipus(idEntitat, tipusPlugin);

			if (plugins.size() > 0) {
				return carregarPropietats(plugins.get(0));
			}
		} catch (Exception e) {
			throw new I18NException(e, "error.desconegut", e.getMessage());
		}

		return null;
	}

	private Object carregarPlugin(Plugin plugin) throws Exception {

		String BASE_PACKAGE = Constants.RFHAB_PROPERTY_BASE;

		if (plugin == null) {
			return null;
		}

		String className = plugin.getClasse().trim();

		Properties prop = new Properties();

		if (plugin.getProperties() != null && plugin.getProperties().trim().length() > 0) {
			prop.load(new StringReader(plugin.getProperties()));
		}

		return org.fundaciobit.pluginsib.core.v3.utils.PluginsManager.instancePluginByClassName(className, BASE_PACKAGE,
				prop);
	}

	private Properties carregarPropietats(Plugin plugin) throws Exception {

		if (plugin == null) {
			return null;
		}

		Properties prop = new Properties();

		if (plugin.getProperties() != null && plugin.getProperties().trim().length() > 0) {
			prop.load(new StringReader(plugin.getProperties()));
		}

		return prop;
	}

}
