package es.caib.rfhab.model.fields;

import org.fundaciobit.genapp.common.query.*;

/** Informació dels camps de la taula de BBDD rfh_digitalib */
public interface ScanWebFields extends java.io.Serializable {
    public static final String _TABLE_SQL = "rfh_digitalib";

    public static final String _TABLE_MODEL = "scanWeb";

    public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;

    public static final LongField DIGITALID = new LongField(_TABLE_MODEL, "digitalID", "digitalid", 19);  // PK
    public static final LongField TRANSACTIONID = new LongField(_TABLE_MODEL, "transactionID", "transactionid", 19);
    public static final StringField TRANSACTIONWEBID = new StringField(_TABLE_MODEL, "transactionWebID", "transactionwebid", 255);
    public static final LongField STATUS = new LongField(_TABLE_MODEL, "status", "status", 19);
    public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid", 19);
    public static final StringField FILEINFO = new StringField(_TABLE_MODEL, "fileInfo", "fileinfo", 2147483647);
    public static final StringField SIGNEDFILEINFO = new StringField(_TABLE_MODEL, "signedFileInfo", "signedfileinfo", 2147483647);
    public static final StringField METADADES = new StringField(_TABLE_MODEL, "metadades", "metadades", 2147483647);
    public static final StringField MISSATGE = new StringField(_TABLE_MODEL, "missatge", "missatge", 2147483647);
    public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid", 19);
    public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio", 29);
    public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid", 19);

    public static final Field<?>[] ALL_SCANWEB_FIELDS = {
        DIGITALID,
        TRANSACTIONID,
        TRANSACTIONWEBID,
        STATUS,
        FITXERID,
        FILEINFO,
        SIGNEDFILEINFO,
        METADADES,
        MISSATGE,
        USUARIID,
        DATACREACIO,
        ENTITATID
    };

    public static final Field<?>[] PRIMARYKEY_FIELDS = {
        DIGITALID
    };
}
