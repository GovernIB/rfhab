
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface RolFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_rol";


  public static final String _TABLE_MODEL = "rol";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ROLID = new LongField(_TABLE_MODEL, "rolID", "rolid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_ROL_FIELDS = {
    ROLID,
    NOMID,
    CODI,
    DATACREACIO,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ROLID
  };
}
