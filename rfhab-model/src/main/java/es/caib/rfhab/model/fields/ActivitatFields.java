
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ActivitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "rfh_activitat";


  public static final String _TABLE_MODEL = "activitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ACTIVITATID = new LongField(_TABLE_MODEL, "activitatID", "activitatid");  // PK
	 public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField REGISTRE = new StringField(_TABLE_MODEL, "registre", "registre");
	 public static final StringField TRAMIT = new StringField(_TABLE_MODEL, "tramit", "tramit");
	 public static final StringField CODISIA = new StringField(_TABLE_MODEL, "codiSia", "codisia");
	 public static final LongField AUTORITZACIOID = new LongField(_TABLE_MODEL, "autoritzacioID", "autoritzacioid");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final StringField INTERESSATNOM = new StringField(_TABLE_MODEL, "interessatNom", "interessatnom");
	 public static final StringField INTERESSATLLINATGE1 = new StringField(_TABLE_MODEL, "interessatLlinatge1", "interessatllinatge1");
	 public static final StringField INTERESSATLLINATGE2 = new StringField(_TABLE_MODEL, "interessatLlinatge2", "interessatllinatge2");
	 public static final IntegerField INTERESSATTIPUS = new IntegerField(_TABLE_MODEL, "interessatTipus", "interessattipus");
	 public static final StringField INTERESSATIDENTIFICACIO = new StringField(_TABLE_MODEL, "interessatIdentificacio", "interessatidentificacio");
	 public static final StringField REPRESENTANTNOM = new StringField(_TABLE_MODEL, "representantNom", "representantnom");
	 public static final StringField REPRESENTANTLLINATGE1 = new StringField(_TABLE_MODEL, "representantLlinatge1", "representantllinatge1");
	 public static final StringField REPRESENTANTLLINATGE2 = new StringField(_TABLE_MODEL, "representantLlinatge2", "representantllinatge2");
	 public static final IntegerField REPRESENTANTTIPUS = new IntegerField(_TABLE_MODEL, "representantTipus", "representanttipus");
	 public static final StringField REPRESENTANTIDENTIFICACIO = new StringField(_TABLE_MODEL, "representantIdentificacio", "representantidentificacio");
	 public static final IntegerField TRAMITVERSIO = new IntegerField(_TABLE_MODEL, "tramitVersio", "tramitversio");
	 public static final StringField ARXIUDOCUMENTID = new StringField(_TABLE_MODEL, "arxiuDocumentID", "arxiudocumentid");
	 public static final StringField ARXIUEXPEDIENTID = new StringField(_TABLE_MODEL, "arxiuExpedientID", "arxiuexpedientid");
	 public static final IntegerField ESTAT = new IntegerField(_TABLE_MODEL, "estat", "estat");
	 public static final StringField URL = new StringField(_TABLE_MODEL, "url", "url");


  public static final Field<?>[] ALL_ACTIVITAT_FIELDS = {
    ACTIVITATID,
    FUNCIONARIID,
    TIPUS,
    REGISTRE,
    TRAMIT,
    CODISIA,
    AUTORITZACIOID,
    DATACREACIO,
    INTERESSATNOM,
    INTERESSATLLINATGE1,
    INTERESSATLLINATGE2,
    INTERESSATTIPUS,
    INTERESSATIDENTIFICACIO,
    REPRESENTANTNOM,
    REPRESENTANTLLINATGE1,
    REPRESENTANTLLINATGE2,
    REPRESENTANTTIPUS,
    REPRESENTANTIDENTIFICACIO,
    TRAMITVERSIO,
    ARXIUDOCUMENTID,
    ARXIUEXPEDIENTID,
    ESTAT,
    URL
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ACTIVITATID
  };
}
