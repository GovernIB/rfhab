
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ScanWebFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_digitalib";


  public static final String _TABLE_MODEL = "scanWeb";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DIGITALID = new LongField(_TABLE_MODEL, "digitalID", "digitalid");  // PK
	 public static final LongField TRANSACTIONID = new LongField(_TABLE_MODEL, "transactionID", "transactionid");
	 public static final StringField TRANSACTIONWEBID = new StringField(_TABLE_MODEL, "transactionWebID", "transactionwebid");
	 public static final LongField STATUS = new LongField(_TABLE_MODEL, "status", "status");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final StringField FILEINFO = new StringField(_TABLE_MODEL, "fileInfo", "fileinfo");
	 public static final StringField SIGNEDFILEINFO = new StringField(_TABLE_MODEL, "signedFileInfo", "signedfileinfo");
	 public static final StringField METADADES = new StringField(_TABLE_MODEL, "metadades", "metadades");
	 public static final StringField MISSATGE = new StringField(_TABLE_MODEL, "missatge", "missatge");
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_SCANWEB_FIELDS = {
    DIGITALID,
    TRANSACTIONID,
    TRANSACTIONWEBID,
    STATUS,
    FITXERID,
    FILEINFO,
    SIGNEDFILEINFO,
    METADADES,
    MISSATGE,
    USUARIID,
    DATACREACIO,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DIGITALID
  };
}
