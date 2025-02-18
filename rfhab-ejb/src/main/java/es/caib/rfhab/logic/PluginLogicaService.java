package es.caib.rfhab.logic;

import java.util.List;
import java.util.Properties;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.PluginService;
import es.caib.rfhab.model.entity.Plugin;

/*
 * author jagarcia
 */

@Local
public interface PluginLogicaService extends PluginService {
	
	public static final String JNDI_NAME = "java:app/rfhab-ejb/PluginLogicaServiceImpl!es.caib.rfhab.logic.PluginLogicaService";
	
	public Object getPlugin(Long idEntitat, String tipusPlugin) throws I18NException;
	
	public Properties getPropertiesPlugin(Long idEntitat, String tipusPlugin) throws I18NException;
	
	public List<Plugin> findByEntitatTipus(Long idEntitat, String tipusPlugin) throws I18NException;
	
}
