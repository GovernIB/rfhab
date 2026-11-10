package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_usuarientitat */
public interface UsuariEntitatFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_usuarientitat";

    public static final String _TABLE_MODEL = "usuariEntitat";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField USUARIENTITATID = new LongField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid", 19);  // PK
    public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid", 19);
    public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid", 19);
    public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu", 1);

    public static final Field<?>[] ALL_USUARIENTITAT_FIELDS = {
        USUARIENTITATID,
        ENTITATID,
        USUARIID,
        ACTIU
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        USUARIENTITATID
    };
}
