package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_autoritzacio */
public interface AutoritzacioFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_autoritzacio";

    public static final String _TABLE_MODEL = "autoritzacio";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField AUTORITZACIOID = new LongField(_TABLE_MODEL, "autoritzacioID", "autoritzacioid", 19);  // PK
    public static final LongField LLOCID = new LongField(_TABLE_MODEL, "llocID", "llocid", 19);
    public static final StringField CODISIA = new StringField(_TABLE_MODEL, "codiSia", "codisia", 50);
    public static final StringField PROCEDIMENT = new StringField(_TABLE_MODEL, "procediment", "procediment", 255);
    public static final StringField CAI = new StringField(_TABLE_MODEL, "cai", "cai", 255);
    public static final DateField DATAINICI = new DateField(_TABLE_MODEL, "dataInici", "datainici", 13);
    public static final DateField DATAFI = new DateField(_TABLE_MODEL, "dataFi", "datafi", 13);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final StringField OBSERVACIONS = new StringField(_TABLE_MODEL, "observacions", "observacions", 2147483647);
    public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid", 19);
    public static final LongField FUNCIONARIID = new LongField(_TABLE_MODEL, "funcionariID", "funcionariid", 19);

    public static final Field<?>[] ALL_AUTORITZACIO_FIELDS = {
        AUTORITZACIOID,
        LLOCID,
        CODISIA,
        PROCEDIMENT,
        CAI,
        DATAINICI,
        DATAFI,
        DATACREACIO,
        OBSERVACIONS,
        USUARIID,
        FUNCIONARIID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        AUTORITZACIOID
    };
}
