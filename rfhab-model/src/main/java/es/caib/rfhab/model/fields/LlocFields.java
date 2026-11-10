package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_lloc */
public interface LlocFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_lloc";

    public static final String _TABLE_MODEL = "lloc";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField LLOCID = new LongField(_TABLE_MODEL, "llocID", "llocid", 19);  // PK
    public static final StringField CODILLOCPROPI = new StringField(_TABLE_MODEL, "codiLlocPropi", "codillocpropi", 50);
    public static final StringField CODILLOC = new StringField(_TABLE_MODEL, "codiLloc", "codilloc", 50);
    public static final StringField EXPANSIO = new StringField(_TABLE_MODEL, "expansio", "expansio", 50);
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 255);
    public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid", 19);
    public static final LongField UNITATID = new LongField(_TABLE_MODEL, "unitatID", "unitatid", 19);
    public static final TimestampField DATAALTA = new TimestampField(_TABLE_MODEL, "dataalta", "dataalta", 29);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final TimestampField DATABAIXA = new TimestampField(_TABLE_MODEL, "dataBaixa", "databaixa", 29);
    public static final IntegerField PERSONALOAMR = new IntegerField(_TABLE_MODEL, "personalOamr", "personaloamr", 10);
    public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions", 2147483647);

    public static final Field<?>[] ALL_LLOC_FIELDS = {
        LLOCID,
        CODILLOCPROPI,
        CODILLOC,
        EXPANSIO,
        NOM,
        ENTITATID,
        UNITATID,
        DATAALTA,
        DATACREACIO,
        DATABAIXA,
        PERSONALOAMR,
        OBSERVACIONS
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        LLOCID
    };
}
