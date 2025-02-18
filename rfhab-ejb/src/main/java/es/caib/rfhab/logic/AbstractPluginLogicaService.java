package es.caib.rfhab.logic;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.PluginService;
import es.caib.rfhab.model.entity.Plugin;

/**
 * author jagarcia
 */
public interface AbstractPluginLogicaService<I> extends PluginService {

	  public List<Plugin> getAllPlugins() throws I18NException;

	  public I getInstanceByPluginID(long pluginID) throws I18NException;

	  public Where getWhere();

	  public I getInstance() throws I18NException;
	  
	  public Long getCurrentPluginID() throws I18NException;
	}
