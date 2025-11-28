
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface HabilitacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_habilitacio";


  public static final String _TABLE_MODEL = "habilitacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField HABILITACIOID = new LongField(_TABLE_MODEL, "habilitacioID", "habilitacioid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_HABILITACIO_FIELDS = {
    HABILITACIOID,
    NOMID,
    CODI,
    DATACREACIO,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
HABILITACIOID
  };
}
