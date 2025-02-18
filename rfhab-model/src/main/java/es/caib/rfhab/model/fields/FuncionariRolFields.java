
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FuncionariRolFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_funcionarirol";


  public static final String _TABLE_MODEL = "funcionariRol";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FUNCIONARIROLID = new LongField(_TABLE_MODEL, "funcionariRolID", "funcionarirolid");  // PK
	 public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid");
	 public static final LongField ROLID = new LongField(_TABLE_MODEL, "rolID", "rolid");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");


  public static final Field<?>[] ALL_FUNCIONARIROL_FIELDS = {
    FUNCIONARIROLID,
    FUNCIONARIID,
    ROLID,
    DATACREACIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FUNCIONARIROLID
  };
}
