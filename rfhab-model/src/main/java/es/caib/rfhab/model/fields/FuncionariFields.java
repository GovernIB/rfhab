package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_funcionari */
public interface FuncionariFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_funcionari";

    public static final String _TABLE_MODEL = "funcionari";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid", 19);  // PK
    public static final StringField NUMERO = new StringField(_TABLE_MODEL, "numero", "numero", 10);
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 255);
    public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1", 255);
    public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2", 255);
    public static final IntegerField TIPUSIDENTIFICADOR = new IntegerField(_TABLE_MODEL, "tipusIdentificador", "tipusidentificador", 10);
    public static final StringField IDENTIFICADOR = new StringField(_TABLE_MODEL, "identificador", "identificador", 50);
    public static final StringField USUARI = new StringField(_TABLE_MODEL, "usuari", "usuari", 50);
    public static final StringField CORREU = new StringField(_TABLE_MODEL, "correu", "correu", 255);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions", 2147483647);
    public static final TimestampField DATABAIXA = new TimestampField(_TABLE_MODEL, "dataBaixa", "databaixa", 29);
    public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid", 19);

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
