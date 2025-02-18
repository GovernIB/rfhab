package es.caib.rfhab.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import es.caib.pluginsib.arxiu.api.IArxiuPlugin;

/**
 * @author jagarcia
 */

@Stateless(name = "PluginArxiuLogicaService")
public class PluginArxiuLogicaEJB extends AbstractPluginLogicaEJB<IArxiuPlugin> implements PluginArxiuLogicaService {


	@Override
	protected String getName() {
		return "Arxiu";
	}


	@PermitAll
	@Override
	public boolean guardarDocument ( String infoArxiu ) {
		return false;
	}
	
	
	@PermitAll
	@Override
	public boolean tancarExpedient(String expedientId) {
		return false;
	}

	
	
}
