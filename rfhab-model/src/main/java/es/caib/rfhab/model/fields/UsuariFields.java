package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_usuari */
public interface UsuariFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_usuari";

    public static final String _TABLE_MODEL = "usuari";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid", 19);  // PK
    public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom", 255);
    public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1", 255);
    public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2", 255);
    public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif", 50);
    public static final StringField USERNAME = new StringField(_TABLE_MODEL, "username", "username", 255);
    public static final StringField CORREU = new StringField(_TABLE_MODEL, "correu", "correu", 255);
    public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid", 5);
    public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu", 1);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final LongField DARRERAENTITAT = new LongField(_TABLE_MODEL, "darreraEntitat", "darreraentitat", 19);
    public static final TimestampField DATABAIXA = new TimestampField(_TABLE_MODEL, "dataBaixa", "databaixa", 29);

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
