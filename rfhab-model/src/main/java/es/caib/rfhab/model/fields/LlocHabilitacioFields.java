
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface LlocHabilitacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_llochabilitacio";


  public static final String _TABLE_MODEL = "llocHabilitacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField LLOCHABILITACIOID = new LongField(_TABLE_MODEL, "llocHabilitacioID", "llochabilitacioid");  // PK
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField LLOCID = new LongField(_TABLE_MODEL, "llocID", "llocid");
	 public static final LongField HABILITACIOID = new LongField(_TABLE_MODEL, "habilitacioId", "habilitacioid");


  public static final Field<?>[] ALL_LLOCHABILITACIO_FIELDS = {
    LLOCHABILITACIOID,
    DATACREACIO,
    LLOCID,
    HABILITACIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
LLOCHABILITACIOID
  };
}
