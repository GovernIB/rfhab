package es.caib.rfhab.logic;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.PluginEJB;
import es.caib.rfhab.model.entity.Plugin;
import es.caib.rfhab.model.fields.PluginFields;
import es.caib.rfhab.persistence.PluginJPA;

/**
 * 
 * author jagarcia
 */

public abstract class AbstractPluginLogicaEJB<I extends IPluginIB> extends PluginEJB
		implements AbstractPluginLogicaService<I> {

	protected abstract String getName();

	@Override
	public List<Plugin> getAllPlugins() throws I18NException {

		Where where = getWhere();

		return select(where);

	}

	@Override
	public Where getWhere() {
		return Where.AND(PluginFields.ACTIU.equal(true));
	}

	@Override
	public I getInstance() throws I18NException {

		Long pluginID = getCurrentPluginID();

		I instance = this.getInstanceByPluginID(pluginID);
		return instance;
	}

	@Override
	public Long getCurrentPluginID() throws I18NException {
		List<Long> pluginList = this.executeQuery(PluginFields.PLUGINID, getWhere(),
				new OrderBy(PluginFields.PLUGINID));

		if (pluginList == null || pluginList.size() == 0) {
			throw new I18NException("error.plugin.noactiu",
					new I18NArgumentCode(PluginFields.ACTIU.codeLabel));
		}

		Long pluginID = pluginList.get(0);
		return pluginID;
	}

	@Override
	public I getInstanceByPluginID(long pluginID) throws I18NException {

		IPluginIB pluginInstance = null;

		PluginJPA plugin = (PluginJPA) findByPrimaryKey(pluginID);

		if (plugin == null) {
			return null;
		}

		Properties prop = new Properties();
		if (plugin.getProperties() != null && plugin.getProperties().trim().length() != 0) {
			try {

				// Exemple:
				// [=SP["es.caib.digitalib.plugins.signatureserver.afirmaserver.authorization.password"]]

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("SP", Configuracio.getSystemAndFileProperties());

				String plantilla = plugin.getProperties();
				String generat = TemplateEngine.processExpressionLanguageSquareBrackets(plantilla, map,
						new Locale("ca"));

				// final String generat = plantilla;
				// log.error("PROPIETATS DESPRES DE generat:\n" + generat + "\n");

				prop.load(new StringReader(generat));

			} catch (Exception e) {
				throw new I18NException(e, "genapp.comodi", new I18NArgumentString(
						"Error desconegut processant propietats del plugin " + pluginID + ": " + e.getMessage()));
			}
		}

		pluginInstance = (IPluginIB) PluginsManager.instancePluginByClassName(plugin.getClasse(),
				Constants.RFHAB_PROPERTY_BASE, prop);

		if (pluginInstance == null) {
			throw new I18NException("error.plugin.donotinstantiate", getName() + " (" + plugin.getClasse() + ")");
		}
		return (I) pluginInstance;
	}
}
