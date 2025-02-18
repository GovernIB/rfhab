package es.caib.rfhab.logic;

import javax.ejb.Local;
import es.caib.pluginsib.arxiu.api.IArxiuPlugin;

/**
 * author jagarcia
 */


@Local
public interface PluginArxiuLogicaService extends AbstractPluginLogicaService<IArxiuPlugin> {

	
	public static final String JNDI_NAME = "java:app/rfhab-ejb/PluginArxiuLogicaServiceImpl!es.caib.rfhab.logic.PluginArxiuLogicaService";
	
	public boolean guardarDocument ( String infoArxiu );
	
	public boolean tancarExpedient ( String expedientId );
	
}
