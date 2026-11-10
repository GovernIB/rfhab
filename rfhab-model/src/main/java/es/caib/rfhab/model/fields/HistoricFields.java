package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_historic */
public interface HistoricFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_historic";

    public static final String _TABLE_MODEL = "historic";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField HISTORICID = new LongField(_TABLE_MODEL, "historicID", "historicid", 19);  // PK
    public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid", 19);
    public static final StringField NUMEROCAI = new StringField(_TABLE_MODEL, "numeroCai", "numerocai", 50);
    public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions", 2147483647);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid", 19);

    public static final Field<?>[] ALL_HISTORIC_FIELDS = {
        HISTORICID,
        FUNCIONARIID,
        NUMEROCAI,
        OBSERVACIONS,
        DATACREACIO,
        USUARIID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        HISTORICID
    };
}
