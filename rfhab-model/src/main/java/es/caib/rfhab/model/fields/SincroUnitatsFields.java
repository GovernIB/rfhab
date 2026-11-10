package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_sincrounitats */
public interface SincroUnitatsFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_sincrounitats";

    public static final String _TABLE_MODEL = "sincroUnitats";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField SINCROUNITATSID = new LongField(_TABLE_MODEL, "sincrounitatsId", "sincrounitatsid", 19);  // PK
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final TimestampField DATADARRERASINCRO = new TimestampField(_TABLE_MODEL, "dataDarreraSincro", "datadarrerasincro", 29);
    public static final TimestampField DATAPRIMERASINCRO = new TimestampField(_TABLE_MODEL, "dataPrimeraSincro", "dataprimerasincro", 29);
    public static final StringField CODIENTITAT = new StringField(_TABLE_MODEL, "codiEntitat", "codientitat", 50);
    public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions", 2147483647);
    public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariId", "usuariid", 19);

    public static final Field<?>[] ALL_SINCROUNITATS_FIELDS = {
        SINCROUNITATSID,
        DATACREACIO,
        DATADARRERASINCRO,
        DATAPRIMERASINCRO,
        CODIENTITAT,
        OBSERVACIONS,
        USUARIID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        SINCROUNITATSID
    };
}
