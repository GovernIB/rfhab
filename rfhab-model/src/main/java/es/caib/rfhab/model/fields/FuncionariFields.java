
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FuncionariFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_funcionari";


  public static final String _TABLE_MODEL = "funcionari";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid");  // PK
	 public static final IntegerField NUMERO = new IntegerField(_TABLE_MODEL, "numero", "numero");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1");
	 public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2");
	 public static final IntegerField TIPUSIDENTIFICADOR = new IntegerField(_TABLE_MODEL, "tipusIdentificador", "tipusidentificador");
	 public static final StringField IDENTIFICADOR = new StringField(_TABLE_MODEL, "identificador", "identificador");
	 public static final StringField USUARI = new StringField(_TABLE_MODEL, "usuari", "usuari");
	 public static final StringField CORREU = new StringField(_TABLE_MODEL, "correu", "correu");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions");
	 public static final TimestampField DATABAIXA = new TimestampField(_TABLE_MODEL, "dataBaixa", "databaixa");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_FUNCIONARI_FIELDS = {
    FUNCIONARIID,
    NUMERO,
    NOM,
    LLINATGE1,
    LLINATGE2,
    TIPUSIDENTIFICADOR,
    IDENTIFICADOR,
    USUARI,
    CORREU,
    DATACREACIO,
    OBSERVACIONS,
    DATABAIXA,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FUNCIONARIID
  };
}
