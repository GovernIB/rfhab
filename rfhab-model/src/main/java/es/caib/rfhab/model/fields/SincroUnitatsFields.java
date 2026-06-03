
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface SincroUnitatsFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_sincrounitats";


  public static final String _TABLE_MODEL = "sincroUnitats";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField SINCROUNITATSID = new LongField(_TABLE_MODEL, "sincrounitatsId", "sincrounitatsid");  // PK
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final TimestampField DATADARRERASINCRO = new TimestampField(_TABLE_MODEL, "dataDarreraSincro", "datadarrerasincro");
	 public static final TimestampField DATAPRIMERASINCRO = new TimestampField(_TABLE_MODEL, "dataPrimeraSincro", "dataprimerasincro");
	 public static final StringField CODIENTITAT = new StringField(_TABLE_MODEL, "codiEntitat", "codientitat");
	 public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions");
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariId", "usuariid");


  public static final Field<?>[] ALL_SINCROUNITATS_FIELDS = {
    SINCROUNITATSID,
    DATACREACIO,
    DATADARRERASINCRO,
    DATAPRIMERASINCRO,
    CODIENTITAT,
    OBSERVACIONS,
    USUARIID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SINCROUNITATSID
  };
}
