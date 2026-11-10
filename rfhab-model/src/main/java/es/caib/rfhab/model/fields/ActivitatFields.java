package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_activitat */
public interface ActivitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_activitat";

    public static final String _TABLE_MODEL = "activitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField ACTIVITATID = new LongField(_TABLE_MODEL, "activitatID", "activitatid", 19);  // PK
    public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid", 19);
    public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus", 10);
    public static final StringField REGISTRE = new StringField(_TABLE_MODEL, "registre", "registre", 50);
    public static final StringField TRAMIT = new StringField(_TABLE_MODEL, "tramit", "tramit", 150);
    public static final StringField CODISIA = new StringField(_TABLE_MODEL, "codiSia", "codisia", 150);
    public static final LongField AUTORITZACIOID = new LongField(_TABLE_MODEL, "autoritzacioID", "autoritzacioid", 19);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final StringField INTERESSATNOM = new StringField(_TABLE_MODEL, "interessatNom", "interessatnom", 255);
    public static final StringField INTERESSATLLINATGE1 = new StringField(_TABLE_MODEL, "interessatLlinatge1", "interessatllinatge1", 255);
    public static final StringField INTERESSATLLINATGE2 = new StringField(_TABLE_MODEL, "interessatLlinatge2", "interessatllinatge2", 255);
    public static final IntegerField INTERESSATTIPUS = new IntegerField(_TABLE_MODEL, "interessatTipus", "interessattipus", 10);
    public static final StringField INTERESSATIDENTIFICACIO = new StringField(_TABLE_MODEL, "interessatIdentificacio", "interessatidentificacio", 50);
    public static final StringField REPRESENTANTNOM = new StringField(_TABLE_MODEL, "representantNom", "representantnom", 255);
    public static final StringField REPRESENTANTLLINATGE1 = new StringField(_TABLE_MODEL, "representantLlinatge1", "representantllinatge1", 255);
    public static final StringField REPRESENTANTLLINATGE2 = new StringField(_TABLE_MODEL, "representantLlinatge2", "representantllinatge2", 255);
    public static final IntegerField REPRESENTANTTIPUS = new IntegerField(_TABLE_MODEL, "representantTipus", "representanttipus", 10);
    public static final StringField REPRESENTANTIDENTIFICACIO = new StringField(_TABLE_MODEL, "representantIdentificacio", "representantidentificacio", 50);
    public static final IntegerField TRAMITVERSIO = new IntegerField(_TABLE_MODEL, "tramitVersio", "tramitversio", 10);
    public static final StringField ARXIUDOCUMENTID = new StringField(_TABLE_MODEL, "arxiuDocumentID", "arxiudocumentid", 255);
    public static final StringField ARXIUEXPEDIENTID = new StringField(_TABLE_MODEL, "arxiuExpedientID", "arxiuexpedientid", 255);
    public static final IntegerField ESTAT = new IntegerField(_TABLE_MODEL, "estat", "estat", 10);
    public static final StringField URL = new StringField(_TABLE_MODEL, "url", "url", 255);
    public static final TimestampField DATAACTIVITAT = new TimestampField(_TABLE_MODEL, "dataActivitat", "dataactivitat", 29);
    public static final StringField IDACTUACIOTRAMIT = new StringField(_TABLE_MODEL, "idActuacioTramit", "idactuaciotramit", 255);
    public static final StringField PROCEDIMENT = new StringField(_TABLE_MODEL, "procediment", "procediment", 150);
    public static final IntegerField ARXIUREINTENTS = new IntegerField(_TABLE_MODEL, "arxiuReintents", "arxiureintents", 10);
    public static final IntegerField ARXIUESTAT = new IntegerField(_TABLE_MODEL, "arxiuEstat", "arxiuestat", 10);
    public static final TimestampField ARXIUDARRERINTENT = new TimestampField(_TABLE_MODEL, "arxiuDarrerIntent", "arxiudarrerintent", 29);

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
        URL,
        DATAACTIVITAT,
        IDACTUACIOTRAMIT,
        PROCEDIMENT,
        ARXIUREINTENTS,
        ARXIUESTAT,
        ARXIUDARRERINTENT
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        ACTIVITATID
    };
}
