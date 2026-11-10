package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_unitat */
public interface UnitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_unitat";

    public static final String _TABLE_MODEL = "unitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField UNITATID = new LongField(_TABLE_MODEL, "unitatID", "unitatid", 19);  // PK
    public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi", 50);
    public static final IntegerField VERSIO = new IntegerField(_TABLE_MODEL, "versio", "versio", 10);
    public static final StringField DENOMINACIO = new StringField(_TABLE_MODEL, "denominacio", "denominacio", 255);
    public static final StringField COOFICIAL = new StringField(_TABLE_MODEL, "cooficial", "cooficial", 255);
    public static final StringField ARREL = new StringField(_TABLE_MODEL, "arrel", "arrel", 50);
    public static final IntegerField ARRELVERSIO = new IntegerField(_TABLE_MODEL, "arrelVersio", "arrelversio", 10);
    public static final StringField SUPERIOR = new StringField(_TABLE_MODEL, "superior", "superior", 50);
    public static final IntegerField SUPERIORVERSIO = new IntegerField(_TABLE_MODEL, "superiorVersio", "superiorversio", 10);
    public static final StringField ESTAT = new StringField(_TABLE_MODEL, "estat", "estat", 5);

    public static final Field<?>[] ALL_UNITAT_FIELDS = {
        UNITATID,
        CODI,
        VERSIO,
        DENOMINACIO,
        COOFICIAL,
        ARREL,
        ARRELVERSIO,
        SUPERIOR,
        SUPERIORVERSIO,
        ESTAT
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        UNITATID
    };
}
