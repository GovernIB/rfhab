
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_entitat";


  public static final String _TABLE_MODEL = "entitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final LongField UNITATID = new LongField(_TABLE_MODEL, "unitatID", "unitatid");
	 public static final TimestampField DATABAIXA = new TimestampField(_TABLE_MODEL, "dataBaixa", "databaixa");


  public static final Field<?>[] ALL_ENTITAT_FIELDS = {
    ENTITATID,
    NOM,
    ACTIU,
    UNITATID,
    DATABAIXA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTITATID
  };
}
