package es.caib.rfhab.pluginsib.arxiu;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.logic.PluginLogicaService;

/**
 * Interfície que defineix els mètodes que ha d'implementar un plugin d'arxiu.
 * 
 * @author jagarcia
 */

public class ArxiuController {

	private static final Logger LOG = LoggerFactory.getLogger(ArxiuController.class);

	@Inject
	private ArxiuPlugin plugin;

	@EJB(mappedName = PluginLogicaService.JNDI_NAME)
	private PluginLogicaService pluginEjb;

	public void setPlugin(ArxiuPlugin plugin) {
		this.plugin = plugin;
	}

	public ArxiuPlugin getPlugin() {
		return plugin;
	}

	public ArxiuController() {
		LOG.info("ArxiuController::ArxiuController");
	}

	public ArxiuController(Long entitatId) {

		LOG.info("ArxiuController::ArxiuController::" + entitatId);

		try {
			if (plugin == null) {
				LOG.info("ArxiuController::ArxiuController::plugin is null");
				setPlugin((ArxiuPlugin) pluginEjb.getPlugin(entitatId, Constants.PLUGIN_ARXIU));
			} 

		} catch (Exception e) {

			LOG.error("error al cargar el plugin d'arxiu");
			e.printStackTrace();
		}

	}
}
