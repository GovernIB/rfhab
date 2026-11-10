package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_historiclloc */
public interface HistoricLlocFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_historiclloc";

    public static final String _TABLE_MODEL = "historicLloc";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField HISTORICLLOCID = new LongField(_TABLE_MODEL, "historicllocID", "historicllocid", 19);  // PK
    public static final LongField LLOCID = new LongField(_TABLE_MODEL, "llocID", "llocid", 19);
    public static final StringField NUMEROCAI = new StringField(_TABLE_MODEL, "numeroCai", "numerocai", 50);
    public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions", 2147483647);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid", 19);

    public static final Field<?>[] ALL_HISTORICLLOC_FIELDS = {
        HISTORICLLOCID,
        LLOCID,
        NUMEROCAI,
        OBSERVACIONS,
        DATACREACIO,
        USUARIID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        HISTORICLLOCID
    };
}
