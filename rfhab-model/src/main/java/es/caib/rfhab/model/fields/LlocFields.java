
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface LlocFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_lloc";


  public static final String _TABLE_MODEL = "lloc";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField LLOCID = new LongField(_TABLE_MODEL, "llocID", "llocid");  // PK
	 public static final StringField CODILLOC = new StringField(_TABLE_MODEL, "codiLloc", "codilloc");
	 public static final StringField CODILLOCPROPI = new StringField(_TABLE_MODEL, "codiLlocPropi", "codillocpropi");
	 public static final StringField EXPANSIO = new StringField(_TABLE_MODEL, "expansio", "expansio");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final LongField UNITATID = new LongField(_TABLE_MODEL, "unitatID", "unitatid");
	 public static final IntegerField PERSONALOAMR = new IntegerField(_TABLE_MODEL, "personalOamr", "personaloamr");
	 public static final TimestampField DATAALTA = new TimestampField(_TABLE_MODEL, "dataalta", "dataalta");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final TimestampField DATABAIXA = new TimestampField(_TABLE_MODEL, "dataBaixa", "databaixa");
	 public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions");


  public static final Field<?>[] ALL_LLOC_FIELDS = {
    LLOCID,
    CODILLOC,
    CODILLOCPROPI,
    EXPANSIO,
    NOM,
    ENTITATID,
    UNITATID,
    PERSONALOAMR,
    DATAALTA,
    DATACREACIO,
    DATABAIXA,
    OBSERVACIONS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
LLOCID
  };
}
