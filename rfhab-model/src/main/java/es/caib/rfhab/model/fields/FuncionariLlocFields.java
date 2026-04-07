
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FuncionariLlocFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_funcionarilloc";


  public static final String _TABLE_MODEL = "funcionariLloc";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FUNCIONARILLOCID = new LongField(_TABLE_MODEL, "funcionarillocID", "funcionarillocid");  // PK
	 public static final LongField LLOCID = new LongField(_TABLE_MODEL, "llocID", "llocid");
	 public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid");
	 public static final DateField DATAINICI = new DateField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final DateField DATAFI = new DateField(_TABLE_MODEL, "dataFi", "datafi");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");
	 public static final StringField NUMEROCAI = new StringField(_TABLE_MODEL, "numeroCai", "numerocai");


  public static final Field<?>[] ALL_FUNCIONARILLOC_FIELDS = {
    FUNCIONARILLOCID,
    LLOCID,
    FUNCIONARIID,
    DATAINICI,
    DATAFI,
    DATACREACIO,
    USUARIID,
    NUMEROCAI
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FUNCIONARILLOCID
  };
}
