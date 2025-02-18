
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_usuari";


  public static final String _TABLE_MODEL = "usuari";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1");
	 public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField USERNAME = new StringField(_TABLE_MODEL, "username", "username");
	 public static final StringField CORREU = new StringField(_TABLE_MODEL, "correu", "correu");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField DARRERAENTITAT = new LongField(_TABLE_MODEL, "darreraEntitat", "darreraentitat");
	 public static final TimestampField DATABAIXA = new TimestampField(_TABLE_MODEL, "dataBaixa", "databaixa");


  public static final Field<?>[] ALL_USUARI_FIELDS = {
    USUARIID,
    NOM,
    LLINATGE1,
    LLINATGE2,
    NIF,
    USERNAME,
    CORREU,
    IDIOMAID,
    ACTIU,
    DATACREACIO,
    DARRERAENTITAT,
    DATABAIXA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIID
  };
}
