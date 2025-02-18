
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginQueryPath() {
  }

  protected PluginQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), PluginFields.PLUGINID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), PluginFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), PluginFields.DESCRIPCIO);
  }

  public StringField CLASSE() {
    return new StringField(getQueryPath(), PluginFields.CLASSE);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), PluginFields.ENTITATID);
  }

  public StringField PROPERTIES() {
    return new StringField(getQueryPath(), PluginFields.PROPERTIES);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), PluginFields.ACTIU);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), PluginFields.DATACREACIO);
  }

  public StringField TIPUS() {
    return new StringField(getQueryPath(), PluginFields.TIPUS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
