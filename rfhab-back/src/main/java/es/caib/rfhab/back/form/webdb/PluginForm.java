package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.PluginJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginForm extends RFHabBaseForm {
  
  private PluginJPA plugin;
  
  public PluginForm() {
  }
  
  public PluginForm(PluginForm __toClone) {
    super(__toClone);
      this.plugin = __toClone.plugin;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public PluginForm(PluginJPA plugin, boolean nou) {
    super(nou);
    this.plugin = plugin;
  }
  
  public PluginJPA getPlugin() {
    return plugin;
  }
  public void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
