
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface LlocRolFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_llocrol";


  public static final String _TABLE_MODEL = "llocRol";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField LLOCROLID = new LongField(_TABLE_MODEL, "llocRolID", "llocrolid");  // PK
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField LLOCID = new LongField(_TABLE_MODEL, "llocID", "llocid");
	 public static final LongField ROLID = new LongField(_TABLE_MODEL, "rolID", "rolid");


  public static final Field<?>[] ALL_LLOCROL_FIELDS = {
    LLOCROLID,
    DATACREACIO,
    LLOCID,
    ROLID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
LLOCROLID
  };
}
