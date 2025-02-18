
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PluginFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_plugin";


  public static final String _TABLE_MODEL = "plugin";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField CLASSE = new StringField(_TABLE_MODEL, "classe", "classe");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final StringField PROPERTIES = new StringField(_TABLE_MODEL, "properties", "properties");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final StringField TIPUS = new StringField(_TABLE_MODEL, "tipus", "tipus");


  public static final Field<?>[] ALL_PLUGIN_FIELDS = {
    PLUGINID,
    NOM,
    DESCRIPCIO,
    CLASSE,
    ENTITATID,
    PROPERTIES,
    ACTIU,
    DATACREACIO,
    TIPUS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PLUGINID
  };
}
