
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface HistoricFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_historic";


  public static final String _TABLE_MODEL = "historic";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField HISTORICID = new LongField(_TABLE_MODEL, "historicID", "historicid");  // PK
	 public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid");
	 public static final StringField NUMEROCAI = new StringField(_TABLE_MODEL, "numeroCai", "numerocai");
	 public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");


  public static final Field<?>[] ALL_HISTORIC_FIELDS = {
    HISTORICID,
    FUNCIONARIID,
    NUMEROCAI,
    OBSERVACIONS,
    DATACREACIO,
    USUARIID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
HISTORICID
  };
}
